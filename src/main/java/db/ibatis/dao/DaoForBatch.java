package db.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import log.LogUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import db.ibatis.DBConst;
import db.ibatis.GameObject;
import db.ibatis.IGameObject;
import db.ibatis.OperateType;

/**
 * 实现了批处理
 * Created by xiewen on 2016/8/29.
 */
@Repository
public class DaoForBatch  implements IDao {

    @Autowired
    private SqlMapClientTemplate sqlMapClientTemplate;
    
    @Override
    public IGameObject insertAtOnce(IGameObject obj) {
        return (IGameObject)sqlMapClientTemplate.insert(obj.getClass().getSimpleName()+"."+ OperateType.INSERT.getOptStr(),obj);
    }

    @Override
    public int updateAtOnce(IGameObject obj) {
        return  sqlMapClientTemplate.update(obj.getClass().getSimpleName()+"."+ OperateType.UPDATE.getOptStr(),obj);
    }

    @Override
    public int deleteAtOnce(IGameObject obj) {
        return  sqlMapClientTemplate.delete(obj.getClass().getSimpleName()+"."+ OperateType.DELETE.getOptStr(),obj);
    }
    
    /**
     * spring首选批处理提交方式，失败的话，会自动回滚
     * @param dataList
     */
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Transactional
    public void batchOpt(final List<? extends IGameObject> dataList) {
        if(dataList == null || dataList.size() == 0) {
            return;
        }
        // 执行回调
        sqlMapClientTemplate.execute(new SqlMapClientCallback() {

            // 实现回调接口
            public Object doInSqlMapClient(SqlMapExecutor executor)  throws SQLException {

                long startTime = System.currentTimeMillis();
                int count = 0;
                // 开始批处理
                executor.startBatch();
                for (IGameObject  gameObject : dataList) {
                        StringBuilder sb = new StringBuilder();
                        String tableName = gameObject.getClass().getSimpleName();
                        if(gameObject.getBatchOpt() == OperateType.UPDATE){
                            executor.update(sb.append(tableName).append(DBConst.STR_DIAN).append(OperateType.UPDATE.getOptStr()).toString(), gameObject);
                        }else if(gameObject.getBatchOpt() == OperateType.INSERT){
                            executor.insert(sb.append(tableName).append(DBConst.STR_DIAN).append(OperateType.INSERT.getOptStr()).toString(), gameObject);
                        }else if(gameObject.getBatchOpt() == OperateType.DELETE){
                            executor.delete(sb.append(tableName).append(DBConst.STR_DIAN).append(OperateType.DELETE.getOptStr()).toString(), gameObject);
                        }else{
                            LogUtil.error("批处理操作类型不存在"+gameObject.getBatchOpt());
                            continue;
                        }

                        if (++count % DBConst.BATCH_COUNT == 0) {
                            executor.executeBatch();
                            executor.startBatch();
                        }
                    }
                executor.executeBatch();
                long logTime = System.currentTimeMillis() - startTime;
                LogUtil.info("=========batch db execute time :"+logTime+"dataLen:"+dataList.size());
                return count;
            }
        });
    }

    @Override
    public int updateBySql(String sql) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("exeSqlSting", sql);
        int rtn = 0;
        try {
            rtn = sqlMapClientTemplate.update("sqlexcutor.exeSqlString", paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }



    public IGameObject getByPk(IGameObject gameObject)  {
        try {
            return (GameObject)sqlMapClientTemplate.queryForObject(gameObject.getClass().getSimpleName() + ".getByPk",gameObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getObjectByCondition(Class<?> thisClass,String condition)   {
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("condition", condition);
        try {
            return (T)sqlMapClientTemplate.queryForObject(thisClass.getSimpleName()+".getListByCondition", paraMap);
        } catch (Exception e) {
            LogUtil.error("查询条件错误,"+condition,e);
        }
        return null;
    }

    public <T> List<T> getListByCondition(Class<?> thisClass,String condition)   {
        Map paraMap = new HashMap();
        paraMap.put("condition", condition);
        try {
            return (List<T>)sqlMapClientTemplate.queryForList(thisClass.getSimpleName()+".getListByCondition", paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HashMap> getListBySql(String sql)   {
        Map<String, String> paraMap = new HashMap<String,String>();
        paraMap.put("sql", sql);
        List<HashMap> list = null;
        try {
            list = sqlMapClientTemplate.queryForList("SqlExcutor.selectBySql", paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteBySql(String sql)   {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("exeSqlSting", sql);
        int rtn = 0;
        try {
            rtn = sqlMapClientTemplate.delete("SqlExcutor.exeSqlString", paraMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rtn;
    }

    @Override
    public void insertBySql(String sql)   {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("exeSqlSting", sql);
        try {
            sqlMapClientTemplate.insert("SqlExcutor.exeSqlString", paraMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
