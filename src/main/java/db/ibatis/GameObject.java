package db.ibatis;
 
import log.LogUtil;

import com.game.context.Daos;

import db.ibatis.innerMessage.DBDispatch;
import db.ibatis.innerMessage.DBPrepareBean;
 
public abstract class GameObject implements IGameObject,Cloneable {
	
	/**
	 * 操作类型，分为增删改三种，见OperateType常量
	 */
	private transient long operateTime;

	private transient  OperateType operateType;

	public void insert() {
		DBDispatch.dispatchRec(createDBPrepareBean(OperateType.INSERT));
	}

	public void update() {
		DBDispatch.dispatchRec(createDBPrepareBean(OperateType.UPDATE));
	}

	public void delete() {
		DBDispatch.dispatchRec(createDBPrepareBean(OperateType.DELETE));
	}
	/**
	 * 需要将对象地址克隆再操作，防止添加队列之后，主键被修改。
	 * @param operateType
	 * @return
	 */
	public DBPrepareBean createDBPrepareBean(OperateType operateType){
		return new DBPrepareBean(this.clone(),operateType);
	}

	public IGameObject insertAtOnce(){
		return Daos.getDao().insertAtOnce(this);
	}

	public int updateAtOnce() {
			return Daos.getDao().updateAtOnce(this);
	}

	public int deleteAtOnce(){
			return Daos.getDao().deleteAtOnce(this);
	}

	public long getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	public void setBatchOpt(OperateType operateType) {
		this.operateType = operateType;
	}

	@Override
	public OperateType getBatchOpt() {
		return operateType;
	}

	@Override
	public abstract String getPkString();
	
	@Override
	protected GameObject clone()  {
		try {
			return (GameObject)super.clone();
		} catch (CloneNotSupportedException e) {
			LogUtil.error("GameObject clone fail,id = {}",this.getPkString());
			e.printStackTrace();
		}
		return null;
	}
}
