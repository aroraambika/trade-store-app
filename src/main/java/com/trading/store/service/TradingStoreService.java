package com.trading.store.service;

import java.text.ParseException;

import com.trading.store.exceptions.LowerVersionTradeException;
import com.trading.store.request.bean.TradeStoreRequest;

public interface TradingStoreService {

	public String processTrade(TradeStoreRequest trade) throws ParseException, LowerVersionTradeException;

	public int autoUpdateExpiredFlag();

}
