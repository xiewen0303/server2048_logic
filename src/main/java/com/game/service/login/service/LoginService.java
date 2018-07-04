package com.game.service.login.service;

import java.util.List;

import log.LogUtil;
import message.Login;
import message.Login.SC_LoginFinish;
import message.common.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.constant.AppCode;
import com.game.context.ServiceManger;
import com.game.core.AbstractService;
import com.game.core.IPlayer;
import com.game.service.bag.service.BagService;
import com.game.service.hero.bean.Hero;
import com.game.service.hero.service.HeroService;
import com.game.service.login.LoginStore;
import com.game.service.map.model.ParentCopy;
import com.game.service.map.service.CopyService;
import com.game.util.MessageUtil;
import com.game.util.ToolUtils;

@Service
public class LoginService extends AbstractService {
	
	@Autowired
	private LoginStore loginStore;

    @Autowired
    private HeroService heroService;
	
	public void login(IPlayer player, String accountId) {
		player.setAccountId(accountId);
		Login.SC_Login.Builder scMsg = Login.SC_Login.newBuilder();
        //如果不在线，处理
        Hero hero = heroService.getHeroForAccount(accountId);
        if(hero == null){
        	scMsg.setIsCreate(true);
        	MessageUtil.send2Client(player, scMsg.build(),AppCode.SUCCESS);
        	return;
        }
        
//      hero = heroService.createHero(accountId);
        boolean flagOnLogin = loginStore.isOnlineAccount(accountId);
        long uid = hero.getUid();
        //挤号操作
        if(flagOnLogin) {
        	IPlayer oldPlayer = loginStore.getIPlayer(uid);
        	if(oldPlayer != null){
        		MessageUtil.sendMessageSynAndClose(oldPlayer, scMsg.build(),AppCode.LOGIN_OUT);
        	}
        }else{
        	 //加载登陆数据
            ServiceManger.getServcieManger().load(uid);
        }
        
        player.setUid(uid);
        loginStore.addLoginPlayer(player,accountId);
        
        ServiceManger.getServcieManger().login(player.getUid());
        MessageUtil.send2Client(player, scMsg.build(),AppCode.SUCCESS);
	}
	
	public void createRole(IPlayer player,String roleName){
		Login.SC_CreateRole.Builder scMsg = Login.SC_CreateRole.newBuilder();
		Hero hero = heroService.getHeroForNickName(roleName);
        if(hero != null){
        	LogUtil.error("角色名重复！");
        	MessageUtil.send2Client(player, scMsg.build(),AppCode.RENAME);
        	return;
        }
        
   		String accountId = player.getAccountId();
   		hero = heroService.createHero(accountId,roleName);
        long uid = hero.getUid();
        
        //加载登陆数据
        ServiceManger.getServcieManger().load(uid);
        
        player.setUid(uid);
        loginStore.addLoginPlayer(player,accountId);
        
        ServiceManger.getServcieManger().login(player.getUid());
        
        MessageUtil.send2Client(player, scMsg.build(),AppCode.SUCCESS);
	}

	public void setLoginStore(LoginStore loginStore) {
		this.loginStore = loginStore;
	}

    @Override
    public void init() {

    }

    @Override
    public void login(long uid) {
    	SC_LoginFinish.Builder scMsg = SC_LoginFinish.newBuilder();
		BagService bagService = ServiceManger.getService(BagService.class);
		//推送背包数据
		scMsg.addAllItemInfos(bagService.getBCBagItems(uid));
		
		//推送已经研发过了的数据
		scMsg.addAllItemId(bagService.getHeroOtherDataExtend(uid).getCreateItemList());
		
		//副本信息
		List<ParentCopy> parentCopys = ServiceManger.getService(CopyService.class).getParentCopys(uid);
		if(!ToolUtils.isEmpty(parentCopys)){
			for (ParentCopy parentCopy : parentCopys) {
				Common.ParentInfo.Builder parentInfo = Common.ParentInfo.newBuilder();
				parentInfo.setTplId(parentCopy.getMapTplId());
				parentInfo.setState(parentCopy.getState());
				scMsg.addParentInfos(parentInfo.build());
			}
		}
		
		//角色名字
		HeroService heroServcie = ServiceManger.getService(HeroService.class);
		Hero hero = heroServcie.getHero(uid);
		if(hero != null){
			scMsg.setRoleName(hero.getNickName());	
		}
		
		//闯关最大关卡
		CopyService copyService = ServiceManger.getService(CopyService.class);
		int maxCopy = copyService.getMaxCopy(uid);
		scMsg.setMaxCopyId(maxCopy);
		
		MessageUtil.send2Client(uid,scMsg.build(),AppCode.SUCCESS);
    }

    @Override
    public void offline(long uid) {
    	Hero hero = heroService.getHero(uid);
    	if(hero == null){
    		LogUtil.error("offline hero is not exits");
    		return;
    	}
        loginStore.offline(uid,hero.getAccountId());
    }

    @Override
    public void exit() {

    }
}