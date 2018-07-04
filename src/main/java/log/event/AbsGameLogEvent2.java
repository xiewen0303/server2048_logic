package log.event;
 
import java.util.EventObject;

import com.game.constant.GlobalConst;
 
/**
 * 日志事件基础类
 * @author DaoZheng Yuan
 * 2014年12月1日 下午2:55:13
 */
public class AbsGameLogEvent2 extends EventObject{

	private static final long serialVersionUID = 1L;
	
	private int type;
	
	public AbsGameLogEvent2(int type) {
		super(GlobalConst.TONGYONG_EVENT_SOURCE);
		this.type = type;
	}

	/**
	 * 日志大类类型
	 * @return
	 */
	public int getType() {
		return type;
	}

	
}
