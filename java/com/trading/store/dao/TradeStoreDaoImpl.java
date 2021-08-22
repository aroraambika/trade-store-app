package com.trading.store.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trading.store.query.TradeSQLQueryGenerator;

@Repository
public class TradeStoreDaoImpl implements TradeStoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Integer findTradeVersion(String tradeId) {
		 String sql = TradeSQLQueryGenerator.QUERY_FOR_TRADE_VERSION;
		 Integer result = jdbcTemplate.queryForObject(sql, new Object[] {tradeId}, Integer.class);
		 return result;
	}

	
}
