package db.ibatis.innerMessage;

import db.ibatis.IGameObject;
import db.ibatis.OperateType;
import db.ibatis.TaskProcessService;

import java.util.Map;

/**
 * 添加db操作到队列中(执行批处理)
 * Created by xiewen on 2016/8/12.
 */
public class DBProcessBatchBean implements  Runnable {

    private Map<String,Map<OperateType,IGameObject>>  processBeans = null;

    public DBProcessBatchBean(Map<String,Map<OperateType,IGameObject>> processBeans) {
        this.processBeans = processBeans;
    }

    @Override
    public void run() {
        TaskProcessService.getTaskProcessService().doAllUpdateForBatch(processBeans);
    }
}
