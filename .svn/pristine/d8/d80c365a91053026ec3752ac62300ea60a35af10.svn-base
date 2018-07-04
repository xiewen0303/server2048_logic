package db.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import log.LogUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import db.ibatis.GameObject;
import db.ibatis.IGameObject;

@Repository
public class Dao implements IDao{

	@Autowired
	private SqlMapClient sqlMapClient;
	
	public IGameObject getByPk(IGameObject gameObject)  {
        try {
			return (GameObject)sqlMapClient.queryForObject(gameObject.getClass().getSimpleName() + ".getByPk",gameObject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public <T> T getObjectByCondition(Class<?> thisClass,String condition)   {
		Map<String,Object> paraMap = new HashMap<>();
		paraMap.put("condition", condition);
		try {
			return (T)sqlMapClient.queryForObject(thisClass.getSimpleName()+".getListByCondition", paraMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public <T> List<T> getListByCondition(Class<?> thisClass,String condition)   {
		Map paraMap = new HashMap();
		paraMap.put("condition", condition);
		try {
			return (List<T>)sqlMapClient.queryForList(thisClass.getSimpleName()+".getListByCondition", paraMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public GameObject insertAtOnce(IGameObject obj)   {
		try {
			return  (GameObject)sqlMapClient.insert(obj.getClass().getSimpleName()+".insert", obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateAtOnce(IGameObject obj)   {
		try {
			return sqlMapClient.update(obj.getClass().getSimpleName()+".update", obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBySql(String sql)   {
		Map<String, String> paraMap = new HashMap<String,String>();
		paraMap.put("exeSqlSting", sql);
		int rtn = 0;
		try {
			rtn = sqlMapClient.update("sqlexcutor.exeSqlString", paraMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	public int deleteAtOnce(IGameObject obj)   {
		try {
			return sqlMapClient.delete(obj.getClass().getSimpleName()+".delete", obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
 
	public List<HashMap> getListBySql(String sql)   {
		Map<String, String> paraMap = new HashMap<String,String>();
		paraMap.put("sql", sql);
		List<HashMap> list = null;
		try {
			list = sqlMapClient.queryForList("SqlExcutor.selectBySql", paraMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
 
	public int deleteBySql(String sql)   {
		Map<String, String> paraMap = new HashMap<String,String>();
		paraMap.put("exeSqlSting", sql);
		int rtn = 0;
		try {
			rtn = sqlMapClient.delete("SqlExcutor.exeSqlString", paraMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtn;
	}
	@Override
	public void insertBySql(String sql)   {
		Map<String, String> paraMap = new HashMap<String,String>();
		paraMap.put("exeSqlSting", sql);
		try {
			sqlMapClient.insert("SqlExcutor.exeSqlString", paraMap);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 批处理
	 * @param dataList
	 */
	public void batchOpt(final List<? extends IGameObject> dataList) {
		LogUtil.debug("=======not implements this mothed!");
	}
}