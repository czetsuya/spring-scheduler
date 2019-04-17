package com.broodcamp.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Edward P. Legaspi
 */
@Component
@Slf4j
public class FixedRunnable {

	/**
	 * Runs after 10 seconds this method has finish running.
	 */
	@Scheduled(fixedDelay = 10000)
	public void fixDelay() {
		log.debug("Fixed Delay");
	}

	/**
	 * Runs every 15 seconds
	 */
	@Scheduled(fixedRate = 15000)
	public void fixRate() {
		log.debug("Fixed Rate");
	}
}
