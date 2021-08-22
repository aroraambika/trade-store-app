package com.trading.store.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.store.dao.TradeStoreDao;
import com.trading.store.exceptions.LowerVersionTradeException;
import com.trading.store.request.bean.TradeStoreRequest;
import com.trading.store.utils.TradeConstants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class TradingStoreServiceImpl implements TradingStoreService {

	@Autowired
	TradeStoreDao tradeStoreDao;
	
	//process the trade as per its verion and maturity date validation 
	public String processTrade(TradeStoreRequest trade) throws ParseException, LowerVersionTradeException {
			
		boolean insertionFlag = validateTradeVersion(trade);
		if (!insertionFlag) {
			log.error("Exception occured : Trade with lower version is invalid");
			throw new LowerVersionTradeException(TradeConstants.INVALID_VERSION_OF_TRADE);

		} else {
			insertionFlag = validateMaturityDate(trade);
			if (!insertionFlag) {
				log.error("Trade with older maturity date is not allowed!");
				return TradeConstants.INVALID_MATURITY_OF_TRADE;
			} else {
				log.info("Records update is in progress..");
				int rows= tradeStoreDao.updateTradeRecord(trade);
				return rows>0 ?TradeConstants.SUCCESS_MSG:TradeConstants.ERROR_MSG;
			}

		}
	}

	//validates whether received trade has lower version or not 
	private boolean validateTradeVersion(TradeStoreRequest trade) {
		// find the version of existing trade
		log.info("Vaidation of version of trade is in progress...");
		Integer version = tradeStoreDao.findTradeVersion(trade.getTradeId());
		if (version != null && version == trade.getVersion()) {
			return true;
		}
		return false;
	}

	//validates whether received Maturity Date is greater than todays' date or not
	private boolean validateMaturityDate(TradeStoreRequest trade) throws ParseException {
		log.info("Vaidation of Maturity Date of trade is in progress...");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = simpleDateFormat.format(new Date());
		Date requestDate = simpleDateFormat.parse(trade.getMaturityDate());
		Date currentDate = simpleDateFormat.parse(formattedDate);

		if (requestDate.after(currentDate) || requestDate.equals(currentDate)) {
			return true;
		}
		return false;

	}

	
	@Override
	public int autoUpdateExpiredFlag() {
		log.info("Job is in progress....");
		return tradeStoreDao.autoUpdateExpiredRecord();
	}

}
