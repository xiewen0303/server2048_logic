package db.ibatis;

/** 
 * DB操作的类型
 */
public enum  OperateType {
	/**
	 * 更新
	 */
	UPDATE("update"),
	/**
	 * 新增
	 */
	INSERT("insert"),
	/**
	 * 删除
	 */
	DELETE("delete");

	private String optStr;

	OperateType(String optStr){
		this.optStr = optStr;
	}

	/**
	 * 获取操作对应的字符串
	 * @return
     */
	public String getOptStr(){
		return optStr;
	}
}
