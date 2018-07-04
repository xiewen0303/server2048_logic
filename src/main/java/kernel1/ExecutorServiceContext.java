package kernel1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceContext {
	
	private int load; //这地方会不会达到int最大数,
	
	private ExecutorService executorService;

	public ExecutorServiceContext(){
		executorService = Executors.newSingleThreadExecutor(new CoreThreadFactory("business"));
	}
	
	
	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
	
	
}
