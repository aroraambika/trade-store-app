package com.trading.store.query;


public class TradeSQLQueryGenerator {
	
	public static final String QUERY_FOR_TRADE_VERSION="select VERSION from TRADE_STORE where TRADE_ID=?";  
	
	
	public static final String QUERY_FOR_TRADE_UPDATE=" update TRADE_STORE set COUNTER_PARTY_ID=?,BOOK_ID=?,MATURITY_DATE=?,CREATED_DATE=?,EXPIRED=? where TRADE_ID=?";
	
	
	public static final String QUERY_FOR_EXPIRED_UPDATE="update TRADE_STORE set EXPIRED='Y' where MATURITY_DATE<CURRENT_DATE()";
	
	
	
	
}
