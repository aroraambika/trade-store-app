package com.trading.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.store.dao.TradeStoreDao;
import com.trading.store.request.bean.TradeStoreRequest;


@Service
public class TradingStoreServiceImpl implements TradingStoreService {
	
	@Autowired
	TradeStoreDao tradeStoreDao;
	

	public String processTrade(TradeStoreRequest trade) {
		boolean insertionFlag = validateTrade(trade); 
		
		return null;
	}
	
	private boolean validateTrade(TradeStoreRequest trade) {
		//find the version of existing trade
		Integer version = tradeStoreDao.findTradeVersion(trade.getTradeId());
		if(version!=null && version!=trade.getVersion()) {
			System.out.println(version);
			return true;
			
		}
		
		
		
		return false;
	}

}
