package br.com.michaelcruz.desafiopicpay.service;

import br.com.michaelcruz.desafiopicpay.client.AuthorizationClient;
import br.com.michaelcruz.desafiopicpay.dto.TransferDto;
import br.com.michaelcruz.desafiopicpay.entities.Transfer;
import br.com.michaelcruz.desafiopicpay.exceptions.PicPayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    public boolean isAuthorized(TransferDto transfer) {
        var response = authorizationClient.isAuthorized();

        if(response.getStatusCode().isError()){
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }
}
