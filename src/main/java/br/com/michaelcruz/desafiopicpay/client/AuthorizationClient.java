package br.com.michaelcruz.desafiopicpay.client;

import br.com.michaelcruz.desafiopicpay.dto.AuthorizationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="authorization", url="${client.authorization-service.url}")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDto> isAuthorized();
}
