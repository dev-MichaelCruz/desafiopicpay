package br.com.michaelcruz.desafiopicpay.repository;

import br.com.michaelcruz.desafiopicpay.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
