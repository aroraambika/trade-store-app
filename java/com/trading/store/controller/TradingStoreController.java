package com.trading.store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trading.store.request.bean.TradeStoreRequest;
import com.trading.store.service.TradingStoreService;

@RestController
public class TradingStoreController {
	
	@Autowired 
	TradingStoreService tradingStoreService;
	
	@RequestMapping(value="/processTrade",method = RequestMethod.POST, consumes ="application/json" )
	String processTradeTransmission(@RequestBody TradeStoreRequest tradeRequest ) {
		String response = tradingStoreService.processTrade(tradeRequest);
		return response;
	}

	
	
}
