package com.engsoft2.roteiro0;

import org.springframework.data.repository.CrudRepository;

public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
