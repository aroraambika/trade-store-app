package com.trading.store.dao;

import com.trading.store.request.bean.TradeStoreRequest;

public interface TradeStoreDao {
	public Integer findTradeVersion(String tradeId);
	public int updateTradeRecord(TradeStoreRequest trade);
	public int autoUpdateExpiredRecord();
	
	
}
