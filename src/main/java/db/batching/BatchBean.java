package db.batching;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置的实体类 
 *
 */
public class BatchBean {

	@SuppressWarnings("rawtypes")
	private Class clz;
	private String sql;
	private String operation;
	
	/*
	 * 批处理对象的属性,根据配置文件按顺序排列
	 */
	private List<String> getterList = new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	public Class getClz() {
		return clz;
	}

	@SuppressWarnings("rawtypes")
	public void setClz(Class clz) {
		this.clz = clz;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}


	public List<String> getGetterList() {
		return getterList;
	}
	
	public void add(String getter){
		this.getterList.add(getter);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
