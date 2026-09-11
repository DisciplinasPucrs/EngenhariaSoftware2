package com.engsoft2.currency_conversion_service;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeFallback implements CurrencyExchangeProxy{
    @Override
    public CurrencyConversion retrieveExchangeValue(String from, String to) {
        return new CurrencyConversion(0L,from,to,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,"circuit-breaker fallback");
        //Implementação mais adequada seria retornar dados em cache de
        //uma requisição anterior com sucesso
    }
}
