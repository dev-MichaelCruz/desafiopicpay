package br.com.michaelcruz.desafiopicpay.repository;

import br.com.michaelcruz.desafiopicpay.entities.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
