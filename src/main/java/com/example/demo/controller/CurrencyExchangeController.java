package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExchangeValue;
import com.example.demo.repo.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepo evRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exValue = evRepo.findByFromAndTo(from, to);
		exValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return exValue;
	}
}

//http://localhost:8000/currency-exchange/from/USD/to/CNY
//http://localhost:8001/currency-exchange/from/EUR/to/CNY
