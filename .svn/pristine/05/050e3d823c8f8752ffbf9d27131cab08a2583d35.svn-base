package db.ibatis;

import com.game.util.ToolUtils;
import db.ibatis.innerMessage.DBDispatch;
import db.ibatis.innerMessage.DBProcessBatchBean;
import log.LogUtil;

import java.util.HashMap;
import java.util.Map;


/** 
 * 准备db数据
 */
public class TaskPrepareService {

	/**
	 * Map(数据对象的键名，Map(操作类型，数据对象的键值集合))
	 */
	private HashMap<String,Map<OperateType,IGameObject>> prepareBeans = new HashMap<>();

	private static TaskPrepareService taskPrepareService = new TaskPrepareService();

	public static TaskPrepareService getTaskPrepareService(){
		return taskPrepareService;
	}

	public void putBean(OperateType operateType,IGameObject gameObject) {
		String pkStr = gameObject.getPkString();
		boolean isEmpty = ToolUtils.isEmpty(pkStr);
		if(isEmpty){
			LogUtil.error("Bean:"+gameObject.getClass().getName()+" pkStr is null");
			return;
		}

		Map<OperateType,IGameObject> objs =  prepareBeans.get(pkStr);
 		if(objs == null){
			objs = new HashMap<>();
			prepareBeans.put(pkStr,objs);
		}
		objs.put(operateType,gameObject);

		//如果超过指定长度，设定压库一次
		if(prepareBeans.size() >= DBConst.WAIT_LEN){
			DBDispatch.dispatchPer(new DBProcessBatchBean(copyPrepareBeans()));
		}
	}

	public void moveToTaskProcess(){
		if(prepareBeans.size() == 0){
			return;
		}

		//通知执行线程将数据保存到db中去
		DBDispatch.dispatchPer(new DBProcessBatchBean(copyPrepareBeans()));
	}

	private Map<String,Map<OperateType,IGameObject>>  copyPrepareBeans(){
		Map<String,Map<OperateType,IGameObject>> temps = new HashMap<>(prepareBeans);
		prepareBeans.clear();
		return temps;
	}
}
