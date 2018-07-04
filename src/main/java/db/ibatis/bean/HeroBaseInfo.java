package db.ibatis.bean;

import db.ibatis.GameObject;

public class HeroBaseInfo extends GameObject{

	private Long uid;
	private String nickName;
	private Integer serverId;
	private String accountId;
	private Long createTime;
	private Integer level;
	private Integer gold;
	private Integer diamond;
	private Integer vipLevel;
	private Integer exp;
	private Long lastLoginTime;

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getServerId() {
		return serverId;
	}
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Integer getDiamond() {
		return diamond;
	}
	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	public Integer getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String getPkString() {
	return this.getClass().getSimpleName()
				+"_"+uid
				;
	} 
}