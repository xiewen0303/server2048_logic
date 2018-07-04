package db.ibatis.bean;

import db.ibatis.GameObject;

public class CopyInfo extends GameObject{

	private Long uid;
	private Integer mapTplId;
	private Integer state;
	private String childCopys;

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getMapTplId() {
		return mapTplId;
	}
	public void setMapTplId(Integer mapTplId) {
		this.mapTplId = mapTplId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getChildCopys() {
		return childCopys;
	}
	public void setChildCopys(String childCopys) {
		this.childCopys = childCopys;
	}

	@Override
	public String getPkString() {
	return this.getClass().getSimpleName()
				+"_"+mapTplId
				+"_"+uid
				;
	} 
}