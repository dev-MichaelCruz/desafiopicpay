package br.com.michaelcruz.desafiopicpay.repository;

import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findAByCpfCnpjOrEmail(String s, String email);
}
