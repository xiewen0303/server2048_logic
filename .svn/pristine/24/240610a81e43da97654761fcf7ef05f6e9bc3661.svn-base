package com.game.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.service.login.LoginStore;

/**
 * 定时器管理 [spring-task.xml]
 * @author  xiewen
 */
@Component
public class JobManager {

//	private static final Logger logger = LogManager.getLogger(JobManager.class);

	@Autowired
	private LoginStore loginStore;
	/**
	 * 测试 每15秒执行一次
	 */
// 	@Scheduled(cron="0/15 * * * * ?")
	public void doIt(){
		//LogUtil.debug("test Scheduled doIt!");

//		for(IPlayer player : loginStore.getIPlayers()){
//			Player p = (Player)player;
//			LogUtil.debug(p.getUid()+"======================"+p.getCtx().channel());
//		}
	}
}


