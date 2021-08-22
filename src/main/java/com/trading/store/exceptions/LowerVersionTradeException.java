package com.trading.store.exceptions;

public class LowerVersionTradeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LowerVersionTradeException(String str) {
		super(str);
	}

}
