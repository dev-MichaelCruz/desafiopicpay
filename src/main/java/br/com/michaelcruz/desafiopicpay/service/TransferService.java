package br.com.michaelcruz.desafiopicpay.service;

import br.com.michaelcruz.desafiopicpay.dto.TransferDto;
import br.com.michaelcruz.desafiopicpay.entities.Transfer;
import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import br.com.michaelcruz.desafiopicpay.exceptions.InsufficientBalanceException;
import br.com.michaelcruz.desafiopicpay.exceptions.TransferNotAllowedForWalletTypeException;
import br.com.michaelcruz.desafiopicpay.exceptions.TransferNotAuthorizedException;
import br.com.michaelcruz.desafiopicpay.exceptions.WalletNotFoundException;
import br.com.michaelcruz.desafiopicpay.repository.TransferRepository;
import br.com.michaelcruz.desafiopicpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        Transfer transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);

        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNoitifcation(transferResult));

        return transferResult;
    }


    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if(!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalanceEqualOrGreaterThan(transferDto.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }
    }
}
