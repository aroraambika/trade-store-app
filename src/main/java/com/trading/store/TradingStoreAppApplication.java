package com.trading.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradingStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingStoreAppApplication.class, args);
	}

}
