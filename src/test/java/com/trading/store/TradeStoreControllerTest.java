package com.trading.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito.Then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.trading.store.controller.TradingStoreController;
import com.trading.store.exceptions.LowerVersionTradeException;
import com.trading.store.request.bean.TradeStoreRequest;
import com.trading.store.service.TradingStoreService;
import com.trading.store.utils.TradeConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeStoreControllerTest {

	
	@InjectMocks
	TradingStoreController tradingStoreController;
	
	@Mock
	TradingStoreService tradingStoreService;
	
	
	@Test
	public void testProcessTade() throws ParseException, LowerVersionTradeException {
		log.info("Testing Controller layer method: processTradeTransmission()");
		TradeStoreRequest trade = new TradeStoreRequest();
		trade.setTradeId("T1");
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate("2021-09-22");
		trade.setCreatedDate("2021-08-22");
		trade.setExpiredFlag("N");
		trade.setVersion(1);
		Mockito.when(tradingStoreService.processTrade(trade)).thenReturn(TradeConstants.SUCCESS_MSG);
		assertThat(tradingStoreService.processTrade(trade)).isNotNull();
	}
	
	@Test
	public void testAutoUpdateExpired() throws ParseException, LowerVersionTradeException {
		log.info("Testing Controller layer method: runScheduledJobForAutoUpdateExpiredFlag()");
		Mockito.when(tradingStoreService.autoUpdateExpiredFlag()).thenReturn(1);
		assertThat(tradingStoreService.autoUpdateExpiredFlag()).isNotNull();
	}
	
	
	
}
