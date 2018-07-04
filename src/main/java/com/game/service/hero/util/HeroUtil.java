package com.game.service.hero.util;

import db.ibatis.bean.HeroBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.core.IPlayer;
import com.game.service.hero.HeroStore;
import com.game.service.hero.bean.Hero;
import com.game.service.login.LoginStore;

import java.util.List;

@Component
public class HeroUtil {
	
	private static HeroStore heroStore;
	
	private static LoginStore playerStore;

	public static List<Long> getAllOnlines(){
		return heroStore.getAllOnlineUids();
	}

	/**
	 * 判定玩家是否在线
	 * @param uid
	 * @return 
	 * @date 2016-4-15 下午5:54:58
	 */
	public static boolean isOnline(long uid) {
		return heroStore.getHero(uid) != null;
	}
	

	public static IPlayer getPlayer(long uid) {
		return playerStore.getIPlayer(uid);
	}
	
	@Autowired
	public  void setHeroStore(HeroStore heroStore) {
		HeroUtil.heroStore = heroStore;
	}
	@Autowired
	public void setPlayerStore(LoginStore playerStore) {
		HeroUtil.playerStore = playerStore;
	}

	public static HeroBaseInfo coverHeroBaseInfo(Hero hero) {
		HeroBaseInfo heroBaseInfo = new HeroBaseInfo();
		heroBaseInfo.setUid(hero.getUid());
		heroBaseInfo.setAccountId(hero.getAccountId());
		heroBaseInfo.setCreateTime(hero.getCreateTime());
		heroBaseInfo.setLastLoginTime(hero.getLastLoginTime());
		heroBaseInfo.setNickName(hero.getNickName());
		return heroBaseInfo;
	}

	public static Hero coverHero(HeroBaseInfo heroBaseInfo) {
		Hero hero = new Hero();
		hero.setUid(heroBaseInfo.getUid());
		hero.setAccountId(heroBaseInfo.getAccountId());
		hero.setCreateTime(heroBaseInfo.getCreateTime());
		hero.setLastLoginTime(heroBaseInfo.getLastLoginTime());
		hero.setNickName(heroBaseInfo.getNickName());
		return hero;
	}
}