package kernel1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author XieWen
 * @date 2016-4-1 下午6:31:39
 */
public class GroupExecutor {
	
	/**
	 *
	 */
	private Map<Object,BusinessExecutor> groupExecutors = new ConcurrentHashMap<>();
	
	/**
	 * 获得工作线程
	 * @param key
	 * @return 
	 * @date 2016-4-1 下午5:51:11
	 */
	public BusinessExecutor getBusinessExecutor(Object key){
		BusinessExecutor  businessExecutor = groupExecutors.get(key);
		if(businessExecutor == null){
			businessExecutor = new BusinessExecutor();
			groupExecutors.put(key, businessExecutor);
		}
		return businessExecutor;
	}
	
	/**
	 * 删除
	 * @param key 
	 * @date 2016-4-1 下午5:46:44
	 */
	public void removeGroupExecutor(Object key){
		groupExecutors.remove(key);
	}
}
