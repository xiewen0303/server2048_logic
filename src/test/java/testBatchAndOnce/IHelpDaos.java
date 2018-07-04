package testcase.testBatchAndOnce;

import db.ibatis.IGameObject;

import java.util.List;

/**
 * xxx
 * Created by xiewen on 2016/8/26.
 */
public interface IHelpDaos {

    public void batchSaveX(final List<? extends IGameObject> dataList);

    public void batchSave(List<? extends IGameObject> listData);

    public void batchSaveOnce(IGameObject gameObject);

    public void select();
}
