package log;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
 


/**
 * 内存监控
 */
public class MemoryManager {
	
	private static MemoryManager instance;

	public static MemoryManager getInstance() {
		if (null == instance) {
			instance = new MemoryManager();
		}
		return instance;
	}

	/** 60s后启动 */
	private final static int TIME_DELAY = 60*1000;
	/** 每120s统计,太短的设置可能会导致玩家登陆不了 */
	private final static int TIME_INTERVAL = 120*1000;

	public void initialize() {
		this.init(TIME_DELAY, TIME_INTERVAL);
	}

	private void init(int delayTime, int intervalTime) {
		final Timer timer = new Timer("Memory Monitor", true);
		timer.schedule(new StatTimerTask(), delayTime, intervalTime);
		
		LogUtil.memInfo("内存监控线程将在"+(delayTime / 1000)+"秒后启动，采样间隔为interval="+intervalTime / 1000+" 秒");
		
		LogUtil.info("================内存监控线程将在"+(delayTime / 1000)+"秒后启动，采样间隔为interval="+intervalTime / 1000+" 秒");
	}

	static class StatTimerTask extends TimerTask {
		@Override
		public void run() {
			try {
				Class<?> c = Class.forName("java.nio.Bits");
				Field maxMemory = c.getDeclaredField("maxMemory");
				maxMemory.setAccessible(true);
				Field reserverdMemory = c.getDeclaredField("reservedMemory");
				reserverdMemory.setAccessible(true);
				Long maxMemoryValue = (Long) maxMemory.get(null);
				Long reserverdMemoryValue = (Long) reserverdMemory.get(null);
				long totalHeadMem = Runtime.getRuntime().totalMemory() / (1024 * 1024);
				long totalHeaduseMem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
				long directMaxMem = maxMemoryValue / (1024 * 1024);
//				double directUseMem = reserverdMemoryValue /(double) (1024 * 1024);
				
				LogUtil.memInfo("服务器堆内存总共 "+totalHeadMem+" M,占用堆内存 "+totalHeaduseMem+" M,直接内存总共 "+directMaxMem+" M,占用直接内存 "+reserverdMemoryValue+" M");
			} catch (Exception e) {
				LogUtil.memInfo(e.getMessage(), e);
			}
		}
	}
}
