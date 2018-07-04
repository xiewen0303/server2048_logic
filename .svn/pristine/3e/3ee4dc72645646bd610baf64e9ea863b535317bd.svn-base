package testcase.testBatchAndOnce;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import db.ibatis.IGameObject;
import db.ibatis.bean.HeroBaseInfo;
import log.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**test
 * Created by xiewen on 2016/8/26.
 */
@Repository
public class TestDaos implements IHelpDaos {

    @Autowired
    private SqlMapClient sqlMapClient;
    /**
     * spring首选批处理提交方式，失败的话，会自动回滚
     * @param dataList
     */
    @Transactional
    public void batchSaveX(final List<? extends  IGameObject> dataList) {
        if (dataList == null || dataList.size() == 0) {
            LogUtil.debug("持久化的数据为空");
            return;
        }
        SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate(sqlMapClient);
        // 执行回调
        sqlMapClientTemplate.execute(new SqlMapClientCallback() {

                // 实现回调接口
                public Object doInSqlMapClient(SqlMapExecutor executor)  throws SQLException {

                    long startTime = System.currentTimeMillis();
                    int count = 0;
                    // 开始批处理
                        executor.startBatch();
                        for (IGameObject gameObject : dataList) {
                            executor.insert("HeroBaseInfo.insert", gameObject);

                            if (++count % 100 == 0) {
                                executor.executeBatch();
                                executor.startBatch();
                            }
                        }
                    long logTime = System.currentTimeMillis() - startTime;
                    LogUtil.info("execute time :"+logTime);
                    return count;
                }
            });
    }



    @Transactional
    public void batchSave(List<? extends IGameObject> dataList) {
        if (dataList == null || dataList.size() == 0) {
            LogUtil.debug("持久化的数据为空");
            return;
        }
        long startTime = System.currentTimeMillis();

        try {
            sqlMapClient.startBatch();
            int insertNum = 0;
            for (IGameObject gameObject : dataList) {

                if (++insertNum % 500 == 0) {
                    sqlMapClient.executeBatch();

                    sqlMapClient.startBatch();
                }
                sqlMapClient.insert("HeroBaseInfo.insert", gameObject);
            }

            sqlMapClient.executeBatch();
            long logTime = System.currentTimeMillis() - startTime;
            LogUtil.info("批处理时间"+logTime);
        } catch (SQLException e) {
            LogUtil.error("批处理错误",e);
        }
    }

    public void batchSaveOnce(IGameObject gameObject){

    }

    public void select(){
        try {
            HeroBaseInfo heroBaseInfo = new HeroBaseInfo();
            heroBaseInfo.setUid(10L);
            sqlMapClient.queryForObject("HeroBaseInfo.getByPk",heroBaseInfo);
            System.out.println("coming......");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
