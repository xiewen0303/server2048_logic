package db.batching.spring;

import db.batching.BatchConfig; 

/**
 * Spring框架统一加载配置<br>
 * 	bean id="batchCreationConfig" class="com.pps.game.db.batching.BatchCreationConfigSpringWrapper"  <br>
 * 		constructor-arg type="java.lang.String" value="batchCreation.xml"/ <br>
 * 		constructor-arg type="int" value="60000"/ <br>
 * 	/bean<br>
 * <br>
 *
 */
public class BatchConfigWrapper {

	public BatchConfigWrapper(String path,int delayedTime){
		
		BatchConfig.load(path,delayedTime);
	}
}
