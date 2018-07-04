package db.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import db.ibatis.IGameObject;

public interface IDao {
	
	/**
	 * 添加数据
	 * @param obj
	 * @return
	 * @throws Exception 
	 * @date 2016-3-17 下午2:41:59
	 */
	public IGameObject insertAtOnce(IGameObject obj);
	
	/**
	 * 更新
	 * @param obj
	 * @return
	 * @throws Exception 
	 * @date 2016-3-17 下午2:41:50
	 */
	public int updateAtOnce(IGameObject obj);
	
	/**
	 * 删除
	 * @param obj
	 * @return
	 * @throws Exception 
	 * @date 2016-3-17 下午2:41:30
	 */
	public int deleteAtOnce(IGameObject obj) ;
	 
	/**
	 * 自定义更新
	 * @param sql
	 * @return
	 * @throws Exception 
	 * @date 2016-3-17 下午2:41:36
	 */
	public int updateBySql(String sql);
	
    /**
     * 根据主键寻找记录
     * @param gameObject
     * @return
     */
    public IGameObject getByPk(IGameObject gameObject) ;
    
    /**
     * 自定义条件查询
     * @param <T>
     * @param thisClass
     * @param condition
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> getListByCondition(Class<?> thisClass, String condition) ;
    
    /**
     * 自定义查询
     * @param thisClass
     * @param condition
     * @return
     * @throws Exception 
     * @date 2016-3-17 下午2:41:11
     */
    public <T> T getObjectByCondition(Class<?> thisClass,String condition) ;
   
    /**
     * 自定义sql查询
     * @param sql
     * @return
     * @throws DataAccessException
     */
	public List<HashMap> getListBySql(String sql);

	/**
	 * 自定义删除
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteBySql(String sql) ;
	
	/**
	 * 自定义sql添加数据
	 * @param sql
	 * @throws Exception 
	 * @date 2016-3-17 下午2:40:32
	 */
	public void insertBySql(String sql) ;

	/**
	 * 批处理
	 * @param dataList
     */
	public void batchOpt(final List<? extends IGameObject> dataList);
}
