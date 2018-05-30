package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ExchangeValue;
import com.example.demo.repo.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepo evRepo;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exValue = evRepo.findByFromAndTo(from, to);
		exValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		logger.info("{}", exValue);
		return exValue;
	}
}

//http://localhost:8000/currency-exchange/from/USD/to/CNY
//http://localhost:8001/currency-exchange/from/EUR/to/CNY	(-Dserver.port=8001)
//http://localhost:8002/currency-exchange/from/EUR/to/CNY	(-Dserver.port=8002)
