package db.ibatis.innerMessage;

import com.game.util.ToolUtils;
import db.ibatis.IGameObject;
import db.ibatis.OperateType;
import db.ibatis.TaskPrepareService;

/**
 * 添加db操作到队列中
 * Created by xiewen on 2016/8/12.
 */
public class DBPrepareBean implements  Runnable {

    private IGameObject gameObject;
    private OperateType operateType;

    public DBPrepareBean(IGameObject gameObject, OperateType operateType) {
        this.gameObject = gameObject;
        this.operateType = operateType;
    }

    @Override
    public void run() {
        gameObject.setOperateTime(ToolUtils.getNowTime());
        TaskPrepareService.getTaskPrepareService().putBean(operateType,gameObject);
    }
}
