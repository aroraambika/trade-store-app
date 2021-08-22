package com.trading.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.trading.store.dao.TradeStoreDao;
import com.trading.store.exceptions.LowerVersionTradeException;
import com.trading.store.request.bean.TradeStoreRequest;
import com.trading.store.service.TradingStoreService;
import com.trading.store.utils.TradeConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeStoreServiceTest {

	@MockBean
	private TradeStoreDao tradeStoreDao;
	
	@Autowired
	TradingStoreService tradeStoreService;
	
	@org.junit.Before
	public void setup(){
		Mockito.when(tradeStoreDao.findTradeVersion("T1")).thenReturn(1);
	}
	
	@Test
	public void testProcessTrade() throws ParseException, LowerVersionTradeException {
		log.info("Testing Service layer method: processTrade()");
		TradeStoreRequest trade = new TradeStoreRequest();
		trade.setTradeId("T1");
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate("2021-09-22");
		trade.setCreatedDate("2021-08-22");
		trade.setExpiredFlag("N");
		trade.setVersion(1);
		assertThat(tradeStoreService.processTrade(trade)).isNotNull();
	}

	@Test(expected = LowerVersionTradeException.class)
	public void testForLowerVersionErrorProcessTrade() throws ParseException, LowerVersionTradeException {
		log.info("Testing Service layer for : LowerVersionTradeException");
		TradeStoreRequest trade = new TradeStoreRequest();
		trade.setTradeId("T1");
		trade.setBookId(null);
		trade.setCounterPartyId(null);
		trade.setMaturityDate(null);
		trade.setCreatedDate(null);
		trade.setExpiredFlag(null);
		trade.setVersion(0);
		assertThat(tradeStoreService.processTrade(trade));
	}
	
	@Test
	public void testForValidateMaturityDate() throws ParseException, LowerVersionTradeException {
		log.info("Testing Service layer for : ValidateMaturityDate");
		TradeStoreRequest trade = new TradeStoreRequest();
		trade.setTradeId("T1");
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate("2015-03-30");
		trade.setCreatedDate("2021-08-22");
		trade.setExpiredFlag("N");
		trade.setVersion(1);
		assertThat(tradeStoreService.processTrade(trade)).isEqualTo(TradeConstants.INVALID_MATURITY_OF_TRADE);
	}
	
	@Test
	public void testAutoUpdateExpiredFlag() {
		log.info("Testing Service layer method: autoUpdateExpiredFlag()");
		assertThat(tradeStoreService.autoUpdateExpiredFlag()).isNotNull();
	}
	
	
}
