package kernel1;

public class TestExecutor {
	
	
	public static void main(String[] args) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getId());
			}
		};
		
		TaskExecutors.init(3);
		GroupExecutor groupExecutor = new GroupExecutor();
		
		
		for (int i = 0; i < 10000; i++) {
			
			long uid = i/3;
			BusinessExecutor executor = groupExecutor.getBusinessExecutor(uid);
			executor.execute(runnable);
			System.out.println(i);
		}
		
		
	}
}