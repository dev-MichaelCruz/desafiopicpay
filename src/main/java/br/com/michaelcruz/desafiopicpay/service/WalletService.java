package br.com.michaelcruz.desafiopicpay.service;

import br.com.michaelcruz.desafiopicpay.dto.CreateWalletDto;
import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import br.com.michaelcruz.desafiopicpay.exceptions.WalletDataAlreadyExistException;
import br.com.michaelcruz.desafiopicpay.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDto createWalletDto){

        var walletDb = walletRepository.findAByCpfCnpjOrEmail(createWalletDto.cpfCnpj(), createWalletDto.email());
        if(walletDb.isPresent()) {
            throw new WalletDataAlreadyExistException("CPF or CNPJ already exists");
        }

        return  walletRepository.save(createWalletDto.toWallet());
    }
}
