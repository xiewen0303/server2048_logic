package db.ibatis.bean;

import db.ibatis.GameObject;

public class ItemInfo extends GameObject{

	private Long uuid;
	private Long uid;
	private Integer tplId;
	private Integer count;

	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getTplId() {
		return tplId;
	}
	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String getPkString() {
	return this.getClass().getSimpleName()
				+"_"+uuid
				;
	} 
}