package br.com.michaelcruz.desafiopicpay.dto;

import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import br.com.michaelcruz.desafiopicpay.entities.WalletType;

public record CreateWalletDto(
        String fullName,
        String cpfCnpj,
        String email,
        String password,
        WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
    }
}
