package com.game.core;

public abstract class HeartBeat {
	
	private long delayTime;	   //间隔时间
	private long initialDelay; //初始间隔时间
	private long tickTime = System.currentTimeMillis(); //最近执行的一次时间

	/**
	 * 初始化
	 * @param delayTime 间隔时间 （毫秒）
	 * @param initialDelay  初始触发间隔时间(毫秒)
	 */
	public HeartBeat(long delayTime, long initialDelay) {
		super();
		this.delayTime = delayTime;
		this.initialDelay = initialDelay;
	}

	public abstract void tick();

	public long getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(long delayTime) {
		this.delayTime = delayTime;
	}

	public long getInitialDelay() {
		return initialDelay;
	}

	public void setInitialDelay(long initialDelay) {
		this.initialDelay = initialDelay;
	}

	public long getTickTime() {
		return tickTime;
	}

	public void setTickTime(long tickTime) {
		this.tickTime = tickTime;
	}
}
