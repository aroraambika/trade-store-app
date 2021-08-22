package com.trading.store.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trading.store.query.TradeSQLQueryGenerator;
import com.trading.store.request.bean.TradeStoreRequest;

@Repository
public class TradeStoreDaoImpl implements TradeStoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	//checks the trade version for corresponding input Trade ID
	public Integer findTradeVersion(String tradeId) {
		String sql = TradeSQLQueryGenerator.QUERY_FOR_TRADE_VERSION;
		return jdbcTemplate.queryForObject(sql, new Object[] { tradeId }, Integer.class);

	}

	@Override
	//updates the record if same version is received for particular trade
	public int updateTradeRecord(TradeStoreRequest trade) {
		String sql = TradeSQLQueryGenerator.QUERY_FOR_TRADE_UPDATE;
		return jdbcTemplate.update(sql, trade.getCounterPartyId(), trade.getBookId(), trade.getMaturityDate(),
				trade.getCreatedDate(), trade.getExpiredFlag(), trade.getTradeId());
	}

	@Override
	//auto update the expired column as per maturity date validation
	public int autoUpdateExpiredRecord() {
		String sql = TradeSQLQueryGenerator.QUERY_FOR_EXPIRED_UPDATE;
		return jdbcTemplate.update(sql);
	}

}
