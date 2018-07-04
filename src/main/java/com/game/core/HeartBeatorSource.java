package com.game.core;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 心跳循环器<br>
 * 实现了IHeartBeat接口
 * @author xiewen
 * 
 */
public class HeartBeatorSource {

	private final static long INTERVAL = 160;
	private final static TimeUnit UNIT = TimeUnit.MILLISECONDS;
	private final static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
	
	private final static CopyOnWriteArrayList<HeartBeat> tasks = new CopyOnWriteArrayList<HeartBeat>();
	
	static {
		schedule(new Runnable() {

			@Override
			public void run() {
				try {
					tick();
				} catch (NullPointerException npe){
					npe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}

			}
		}, System.currentTimeMillis() + 1000, INTERVAL);
	}

	private static void schedule(Runnable command, long scheduledAt, long delay) {
		long now = System.currentTimeMillis();
		if (scheduledAt <= now) {
			scheduledAt += 86400000L;
		}
		service.scheduleWithFixedDelay(command, scheduledAt - now, delay, UNIT);
	}
	
	private static void tick() {
		long now = System.currentTimeMillis();
		Iterator<HeartBeat> ite = tasks.iterator();
		while (ite.hasNext()) {
			try {
				HeartBeat heartBeat = ite.next();
				long initialDelay = heartBeat.getInitialDelay();
				
				long checkTime = heartBeat.getDelayTime() + heartBeat.getTickTime();
				if(initialDelay != 0) {
					checkTime += initialDelay;
					
				}
				
				if(checkTime <= now){
					heartBeat.setTickTime(now);
					heartBeat.tick();
					
					if(initialDelay != 0){
						heartBeat.setInitialDelay(0);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 注册心跳循环服务
	 * @param task
	 */
	public static void add(HeartBeat task) {
		tasks.add(task);
	}

	/**
	 * 移除心跳循环服务
	 * @param task
	 */
	public static void remove(HeartBeat task) {
		tasks.remove(task);
	}
}
