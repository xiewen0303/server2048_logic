package com.game.service.login;

import log.LogUtil;
import message.Chat;
import message.Login;
import message.MessageCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.game.constant.AppCode;
import com.game.constant.ModuleName;
import com.game.core.IPlayer;
import com.game.mapping.annotation.EasyMapping;
import com.game.mapping.annotation.EasyWorker;
import com.game.mapping.constant.EasyGroup;
import com.game.msg.IMessageReadable;
import com.game.service.login.service.LoginService;
import com.game.util.MessageUtil;

/**
 * 登录
 * @author XieWen
 * @date 2016-3-15 下午2:40:28
 */
@Controller
@EasyWorker(moduleName=ModuleName.LOGIN,groupName = EasyGroup.LOGING)
public class LoginAction {
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 登录
	 */
	@EasyMapping(mapping= MessageCode.CS_Login)
	public void login(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		Login.CS_Login  loginMsg = msg.getMessage();
		String accountId = loginMsg.getAccountId();
		loginService.login(player,accountId);
	}
	
	/**
	 * 创号 
	 */
	@EasyMapping(mapping= MessageCode.CS_CreateRole)
	public void createRole(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		Login.CS_CreateRole  loginMsg = msg.getMessage();
		String roleName = loginMsg.getRoleName();
		loginService.createRole(player,roleName);
	}

	/**
	 * chat
	 */
	@EasyMapping(mapping= MessageCode.CS_ChatMessage)
	public void chat(IMessageReadable  msg) {
		IPlayer player = msg.getIPlayer();
		Chat.CS_ChatMessage  csMsg = msg.getMessage();
		String msgContent = csMsg.getMsg();

		LogUtil.debug("userId:"+player.getUid()+"================"+msgContent);

		Chat.SC_ChatMessage.Builder  scMsg = Chat.SC_ChatMessage.newBuilder();
//		scMsg.setRet(MessageUtil.getRetInfo(1,"xxx"));

		//broadcast all online users  TODO
		MessageUtil.send2Client(player, scMsg.build(),AppCode.SUCCESS);
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}