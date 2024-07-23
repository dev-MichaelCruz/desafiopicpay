package br.com.michaelcruz.desafiopicpay.repository;

import br.com.michaelcruz.desafiopicpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findAByCpfCnpjOrEmail(String s, String email);
}
