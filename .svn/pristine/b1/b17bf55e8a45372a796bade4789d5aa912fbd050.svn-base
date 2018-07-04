package kernel1;

import java.util.concurrent.ExecutorService;

import com.game.util.RandomUtil;

public class TaskExecutors {
	
	public static ExecutorServiceContext[] executors;
	
	/**
	 * 任务池线程数
	 * @param size
	 */
	public static void init(int size){
		executors = new ExecutorServiceContext[size];
		for (int i = 0; i < 3; i++) {
			executors[i] =  new ExecutorServiceContext();
		}
	}
	
	/**
	 * 获得对应的线程池
	 * @return 
	 * @date 2016-3-29 下午6:03:45  //这个地方的线程数有问题。
	 */
	public static ExecutorService getExecutorService(){
		int i = RandomUtil.getRandom(3);  //TODO 这个地方应该用均衡计算，获得当前工作数最少的。
		return executors[i].getExecutorService();
	}
}
