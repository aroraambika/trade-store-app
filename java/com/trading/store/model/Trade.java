package com.trading.store.model;

import lombok.Data;

@Data
public class Trade {
	protected String tradeId;
	protected Integer version;
	protected String counterPartyId;
	protected String bookId;
	protected String maturityDate;
	protected String createdDate;
	protected String expiredFlag;
	
}
