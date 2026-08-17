package com.engsoft2.roteiro0;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CurrencyExchangeRepository repository;
    private Environment environment;
  
    public CurrencyConversionController(CurrencyExchangeRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with from {} to {}", from, to);
        CurrencyExchange currencyExchange = getCurrencyExchange(from, to);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }

    private CurrencyExchange getCurrencyExchange(String from, String to) {
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new ResourceNotFoundException("From " + from + "To " + to + " not found");
        }
        return currencyExchange;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
            @PathVariable BigDecimal quantity) {
        logger.info("calculateCurrencyConversion called with from {} to {} with quantity {}", from, to, quantity);
        CurrencyExchange currencyExchange = getCurrencyExchange(from, to);
        return new CurrencyConversion(
            currencyExchange.getId(),
            from, to, quantity,
            currencyExchange.getConversionMultiple(),
            quantity.multiply(currencyExchange.getConversionMultiple()),
            currencyExchange.getEnvironment());
    }

}
