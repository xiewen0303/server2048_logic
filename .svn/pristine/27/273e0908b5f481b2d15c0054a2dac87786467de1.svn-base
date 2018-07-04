package db.ibatis.bean;

import db.ibatis.GameObject;

public class HeroOtherDataBase extends GameObject{

	private Long uid;
	private Integer nowCtplId;
	private String createItems;

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getNowCtplId() {
		return nowCtplId;
	}
	public void setNowCtplId(Integer nowCtplId) {
		this.nowCtplId = nowCtplId;
	}
	public String getCreateItems() {
		return createItems;
	}
	public void setCreateItems(String createItems) {
		this.createItems = createItems;
	}

	@Override
	public String getPkString() {
	return this.getClass().getSimpleName()
				+"_"+uid
				;
	} 
}