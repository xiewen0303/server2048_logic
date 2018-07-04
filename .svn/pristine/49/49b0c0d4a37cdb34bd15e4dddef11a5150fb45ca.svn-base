package kernel1;

import java.util.concurrent.ExecutorService;

/**
 * 对应key分配线程池
 * @author XieWen
 * @date 2016-3-29 下午5:59:07
 */
public class BusinessExecutor {
	
	private ExecutorService executorService;
	
	public BusinessExecutor() {
		this.executorService = TaskExecutors.getExecutorService();
	}
	
	 public void execute(Runnable runnable){
		 executorService.execute(runnable);
	 }
}