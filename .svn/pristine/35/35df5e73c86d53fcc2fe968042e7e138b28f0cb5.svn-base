package db.ibatis.innerMessage;

import com.game.util.ToolUtils;
import db.ibatis.IGameObject;
import db.ibatis.OperateType;
import db.ibatis.TaskPrepareService;
import db.ibatis.TaskProcessService;

import java.util.Map;

/**
 * 添加db操作到队列中
 * Created by xiewen on 2016/8/12.
 */
public class DBProcessBean implements  Runnable {

    private Map<String,Map<OperateType,IGameObject>>  processBeans = null;

    public DBProcessBean(Map<String,Map<OperateType,IGameObject>> processBeans) {
        this.processBeans = processBeans;
    }

    @Override
    public void run() {
        TaskProcessService.getTaskProcessService().doAllUpdate(processBeans);
    }
}
