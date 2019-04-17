package com.broodcamp.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

/**
 * @author Edward P. Legaspi
 */
@Service
public class ScheduleTaskService {

	TaskScheduler scheduler;
	Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

	public ScheduleTaskService(TaskScheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * Schedules a cron job every 10 seconds.
	 * 
	 * @param id
	 * @param task
	 */
	public void scheduleCron(int id, Runnable task) {
		ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger("0/10 * * * * *", TimeZone.getTimeZone(TimeZone.getDefault().getID())));
		jobsMap.put(id, scheduledTask);
	}

	/**
	 * Schedules a cron job every 10 seconds.
	 * 
	 * @param id
	 * @param task
	 */
	public void schedulePeriod(int id, Runnable task) {
		ScheduledFuture<?> periodicSchedule = scheduler.schedule(task, new PeriodicTrigger(10, TimeUnit.SECONDS));
		jobsMap.put(id, periodicSchedule);
	}

	public void removeTaskFromScheduler(int id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if (scheduledTask != null) {
			scheduledTask.cancel(true);
			jobsMap.put(id, null);
		}
	}

	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		// Get all tasks from DB and reschedule them in case of context restarted
	}
}