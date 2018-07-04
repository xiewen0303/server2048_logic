package db.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import log.LogUtil;

import com.alibaba.fastjson.JSONObject;
import com.game.context.Daos;

/**
 * 接受DB更新的请求队列，并做合并操作。
 */
public class TaskProcessService {

	private static TaskProcessService taskProcessService = new TaskProcessService();

	public static TaskProcessService getTaskProcessService() {
		return taskProcessService;
	}

	/**
	 * 向DB更新所有队列中的数据
	 */
	public void doAllUpdate(Map<String,Map<OperateType,IGameObject>> processBeans) {
		for (Map<OperateType,IGameObject> OperateBeans : processBeans.values()) {
				IGameObject gameObject = null;
				 //增删改操作，只执行其中一个。遇到同时存在多个的时候，也只需要按照优先级执行其中之一就可以。
				if((gameObject = OperateBeans.get(OperateType.DELETE)) != null){
					 gameObject.deleteAtOnce();
					LogUtil.debug("delete db:"+gameObject.getPkString());
				}else if((gameObject = OperateBeans.get(OperateType.INSERT)) != null){
					 //需要保证数据最新数据为新新增
					IGameObject updateObj = OperateBeans.get(OperateType.UPDATE);
					 if(updateObj != null && updateObj.getOperateTime() > gameObject.getOperateTime()){
						 gameObject = updateObj;
					 }
					 gameObject.insertAtOnce();
					LogUtil.debug("insert db:"+gameObject.getPkString());
				}else if((gameObject = OperateBeans.get(OperateType.UPDATE)) != null){
					 gameObject.updateAtOnce();
					 LogUtil.debug("update db:"+gameObject.getPkString());
				}else{
					 LogUtil.error("doAllUpdate 发生异常pkString=" + JSONObject.toJSONString(OperateBeans));
				}
		 }
	}

	/**
	 * 向DB更新所有队列中的数据
	 **/
	public void doAllUpdateForBatch(Map<String,Map<OperateType,IGameObject>> processBeans) {
		List<IGameObject> optDatas = new ArrayList<>();

		for (Map<OperateType,IGameObject> operateBeans : processBeans.values()) {
			IGameObject gameObject = null;
			//增删改操作，只执行其中一个。遇到同时存在多个的时候，也只需要按照优先级执行其中之一就可以。
			if((gameObject = operateBeans.get(OperateType.DELETE)) != null) {

				IGameObject insertObject = operateBeans.get(OperateType.INSERT);
				//如果先删除，在insert。那么只需要执行update
				if (insertObject != null  &&  insertObject.getOperateTime() > gameObject.getOperateTime()) {
					gameObject.setBatchOpt(OperateType.UPDATE);
					optDatas.add(gameObject);
					LogUtil.debug("update db:"+gameObject.getPkString());
				}else{
					gameObject.setBatchOpt(OperateType.DELETE);
					optDatas.add(gameObject);
					LogUtil.debug("delete db:"+gameObject.getPkString());
				}

			}else if((gameObject = operateBeans.get(OperateType.INSERT)) != null){
				//需要保证数据最新数据为新新增
				IGameObject updateObj = operateBeans.get(OperateType.UPDATE);
				if(updateObj != null && updateObj.getOperateTime() > gameObject.getOperateTime()){
					gameObject = updateObj;
				}
				gameObject.setBatchOpt(OperateType.INSERT);
				optDatas.add(gameObject);
				LogUtil.debug("insert db:"+gameObject.getPkString());

			}else if((gameObject = operateBeans.get(OperateType.UPDATE)) != null){
				gameObject.setBatchOpt(OperateType.UPDATE);
				optDatas.add(gameObject);
				LogUtil.debug("update db:"+gameObject.getPkString());

			}else{
				LogUtil.error("doAllUpdate 发生异常pkString=" + JSONObject.toJSONString(operateBeans));
			}
		}

		Daos.getDao().batchOpt(optDatas);

	}
}
