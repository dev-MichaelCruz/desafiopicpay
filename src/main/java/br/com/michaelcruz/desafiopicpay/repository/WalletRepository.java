package br.com.michaelcruz.desafiopicpay.repository;

import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
