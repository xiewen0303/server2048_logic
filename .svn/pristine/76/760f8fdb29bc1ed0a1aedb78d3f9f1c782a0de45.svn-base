package db.ibatis;

/**
 * bean操作
 */
public interface IGameObject{

	/**
	 * 获得操作时间戳
	 * @return
	 */
	public long getOperateTime();
	
	/**
	 * 设置操作时间戳
	 * @param operateTime
	 */
	public void setOperateTime(long operateTime);
    
    /**
     * 主键的字符串组合，用于缓存机制的key值
     * @return
     */
    public String getPkString();
	/**
	 * 异步新增
	 */
    public void insert();
   
    /**
	 * 立即新增  用于离线处理
     * @return
     */
    public IGameObject insertAtOnce();

    /**
     * 异步更新
     */
    public void update();
    /**
     * 立即更新  用于离线处理
     */
    public int updateAtOnce();

    /**
     * 异步删除
     */
    public void delete();
    /**
     * 立即删除   用于离线处理
     */
    public int deleteAtOnce();

	/**
	 * 设置批处理操作类型
	 * @param operateType {@link OperateType
     */
	public void setBatchOpt(OperateType operateType);

	/**
	 * 获得批处理类型
	 * @return   {@link OperateType}
     */
	public OperateType getBatchOpt();
}
