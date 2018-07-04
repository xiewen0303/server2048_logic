package db.ibatis.bean;

import db.ibatis.GameObject;

public class UuidInfo extends GameObject{

	private Integer type;
	private Long id;
	private String name;

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPkString() {
	return this.getClass().getSimpleName()
				+"_"+type
				;
	} 
}