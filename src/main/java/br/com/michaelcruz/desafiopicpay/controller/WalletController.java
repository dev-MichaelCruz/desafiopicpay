package br.com.michaelcruz.desafiopicpay.controller;

import br.com.michaelcruz.desafiopicpay.dto.CreateWalletDto;
import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import br.com.michaelcruz.desafiopicpay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto createWalletDto) {
        Wallet wallet = walletService.createWallet(createWalletDto);

        return ResponseEntity.ok(wallet);
    }


}
