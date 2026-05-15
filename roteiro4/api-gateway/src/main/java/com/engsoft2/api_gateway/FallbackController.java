package com.engsoft2.api_gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("path")
    public ResponseEntity<String> currencyConversionFallback() {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Serviços temporariamente indisponíveis. Tente novamente.");
            //Implementação mais adequada seria retornar dados em cache de
            //uma requisição anterior com sucesso
    }
}
