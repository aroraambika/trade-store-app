package com.trading.store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.trading.store.dao.TradeStoreDao;
import com.trading.store.request.bean.TradeStoreRequest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeStoreDaoTest {

	
	@Autowired
	TradeStoreDao tradeStoreDao;
	
	@Test
	public void testTradeVersion() {
		log.info("Testing DAO layer method: findTradeVersion()");
		assertThat(tradeStoreDao.findTradeVersion("T1")).isNotNull();
	}
	
	@Test
	public void testRecordUpdation() {
		log.info("Testing DAO layer method: updateTradeRecord()");
		TradeStoreRequest trade = new TradeStoreRequest();
		trade.setTradeId("T1");
		trade.setBookId("B2");
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate("2021-09-22");
		trade.setCreatedDate("2021-08-22");
		trade.setExpiredFlag("N");
		assertThat(tradeStoreDao.updateTradeRecord(trade)).isNotNull();
	}
	
	@Test
	public void testAutoUpdationExpired() {
		log.info("Testing DAO layer method: autoUpdateExpiredRecord()");
		assertThat(tradeStoreDao.autoUpdateExpiredRecord()).isNotNull();
	}
	
	
}
