package testcase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 	corePoolSize
 *	核心线程数，核心线程会一直存活，即使没有任务需要处理。当线程数小于核心线程数时，即使现有的线程空闲，线程池也会优先创建新线程来处理任务，而不是直接交给现有的线程处理。
 *	核心线程在allowCoreThreadTimeout被设置为true时会超时退出，默认情况下不会退出。
 *	maxPoolSize
 *	当线程数大于或等于核心线程，且任务队列已满时，线程池会创建新的线程，直到线程数量达到maxPoolSize。如果线程数已等于maxPoolSize，且任务队列已满，则已超出线程池的处理能力，线程池会拒绝处理任务而抛出异常。
 *	keepAliveTime
 *	当线程空闲时间达到keepAliveTime，该线程会退出，直到线程数量等于corePoolSize。如果allowCoreThreadTimeout设置为true，则所有线程均会退出直到线程数量为0。
 *	allowCoreThreadTimeout
 *	是否允许核心线程空闲退出，默认值为false。
 *	queueCapacity
 *	任务队列容量。从maxPoolSize的描述上可以看出，任务队列的容量会影响到线程的变化，因此任务队列的长度也需要恰当的设置。
 */
class ExecuteAction extends Thread {
	private long uid;

	ExecuteAction(long uid) {
		super();
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(10);
			++T.i;
			if(T.datas.contains(T.i)){
				System.exit(0);
			}
			System.out.println(Thread.currentThread().getId() +"===="+(T.i));
			T.datas.add(T.i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


public class T {
	static List<Integer> datas = new ArrayList<>();
	
	static Map<Long,Future<?>> executors = new ConcurrentHashMap<>();
	static BlockingQueue<ExecuteAction> workQueueTemp = new ArrayBlockingQueue<>(100000);
	
	static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);
	static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 10, 1000, TimeUnit.MILLISECONDS, workQueue);

	public static int i = 0;
	
	/**
	 * @param args 
	 * @date 2016-3-19 下午2:27:25 
	 */
	public static void main(String[] args) {
		start();
		
		long uid = 100000;
		
		final ExecuteAction runnable =	new ExecuteAction(uid);
			 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					add(runnable);
				}
			}
		}).start();
		
		
	}
	
	public static void start(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						ext(workQueueTemp.take());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private static void ext(ExecuteAction take) {
		try {
			long uid = take.getUid();
			Future<?>  future =  executors.get(uid);
			if(future.isDone()){
				Future<?> newFuture = poolExecutor.submit(take);
				executors.put(uid, newFuture);
			}else{
				workQueueTemp.put(take);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void add(ExecuteAction executeAction) {
		try {
			long uid = executeAction.getUid();
			Future<?>  future =  executors.get(uid);
			
			if(future == null ||  future.isDone()){
				future =  poolExecutor.submit(executeAction);
				executors.put(uid, future);
			}else{
				workQueueTemp.put(executeAction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
