package com.broodcamp.scheduler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Edward P. Legaspi
 */
@Slf4j
public class RunnableJob implements Runnable {

	private String name;

	public RunnableJob(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		log.debug("RunnableJob.name={}", name);
	}

}
