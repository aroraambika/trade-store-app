package com.trading.store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trading.store.request.bean.TradeStoreRequest;
import com.trading.store.service.TradingStoreService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class TradingStoreController {
	
	@Autowired 
	TradingStoreService tradingStoreService;
	
	/*
	 * 
	 * 
	 * accepts request in following JSON  format
	 * {
		    "tradeId":"T1",
		    "version":1,
		    "counterPartyId":"CP-2",
		    "bookId":"B3",
		    "maturityDate":"2021-10-22",
		    "createdDate":"2021-08-21",
		    "expiredFlag":"N"
		} 
	 * and process the trade
	 * 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/processTrade", method = RequestMethod.POST, consumes = "application/json")
	String processTradeTransmission(@RequestBody TradeStoreRequest tradeRequest) {

		log.info("Processing of Trade is started....");
		String response = null;
		try {
			response = tradingStoreService.processTrade(tradeRequest);
			log.info("Processing of Trade is ended.");
		} catch (Exception e) {
			log.error("Exception occured.. " + e.getMessage());
			return e.getMessage();
		}
		return response;
	}	
	
	
	/**
	 * Scheduled job for every midnight at 12:01 AM , checks for maturity date 
	 * and then update expired flag 
	 * 
	 * */
	
	@Scheduled(cron = "0 1 0 1/1 * ?")
	void runScheduledJobForAutoUpdateExpiredFlag() {
		log.info("Auto Update scheduler started....");
		try {
			int rowsUpdated = tradingStoreService.autoUpdateExpiredFlag();
		log.info("Scheduler Job Ended: "+rowsUpdated+" rows updated for Expired. ");		
		}catch (Exception e) {
			log.error("Exception occured.. " + e.getMessage());
		}
	}

	
	
}
