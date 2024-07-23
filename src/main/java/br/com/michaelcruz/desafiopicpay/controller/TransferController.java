package br.com.michaelcruz.desafiopicpay.controller;

import br.com.michaelcruz.desafiopicpay.dto.TransferDto;
import br.com.michaelcruz.desafiopicpay.entities.Transfer;
import br.com.michaelcruz.desafiopicpay.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto transferDto) {
        Transfer response = transferService.transfer(transferDto);
        return ResponseEntity.ok(response);
    }

}
