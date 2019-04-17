package com.broodcamp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Edward P. Legaspi
 */
@Component
public class LoadOnStartUp {

	@Autowired
	private ScheduleTaskService scheduleTaskService;

	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		scheduleTaskService.scheduleCron(1, new RunnableJob("Cron"));
		scheduleTaskService.schedulePeriod(2, new RunnableJob("Period"));
	}
}
