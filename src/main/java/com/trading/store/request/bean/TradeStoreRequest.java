package com.trading.store.request.bean;

import lombok.Data;

@Data
public class TradeStoreRequest {
	
	protected String tradeId;
	protected Integer version;
	protected String counterPartyId;
	protected String bookId;
	protected String maturityDate;
	protected String createdDate;
	protected String expiredFlag;
	
		
}
