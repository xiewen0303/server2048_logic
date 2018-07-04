package com.game.service.hero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.context.Daos;
import com.game.core.AbstractService;
import com.game.gen.GenUtil;
import com.game.gen.PrimaryType;
import com.game.service.hero.HeroStore;
import com.game.service.hero.bean.Hero;
import com.game.service.hero.util.HeroUtil;
import com.game.util.TimeUtil;

import db.ibatis.bean.HeroBaseInfo;

@Service
public class HeroService extends AbstractService {
	
	@Autowired
	private HeroStore heroStore;

	public Hero createHero(String accountId, String roleName) {
		long uid = GenUtil.getGenKey(PrimaryType.HERO);
		long nowTime = TimeUtil.getNowTime();
		Hero hero = new Hero();
		hero.setUid(uid);
		hero.setAccountId(accountId);
		hero.setCreateTime(nowTime);
		hero.setLastLoginTime(nowTime);
		hero.setNickName(roleName);
		heroStore.addHero(hero);

		HeroBaseInfo heroBaseInfo = HeroUtil.coverHeroBaseInfo(hero);
		heroBaseInfo.insert();
		return hero;
	}

	public void setHeroStore(HeroStore heroStore) {
		this.heroStore = heroStore;
	}
	
	public Hero getHero(long uid) {
		return heroStore.getHero(uid);
	}

	public Hero getHeroForAccount(String accountId) {
		return loadHeroBaseInfo("where account_id='"+accountId+"'");
	}
	
	public Hero getHeroForNickName(String nickName) {
		return loadHeroBaseInfo("where nick_name='"+nickName+"'");
	}

	private Hero loadHeroBaseInfo(String condition) {
		Hero hero = null;
		HeroBaseInfo heroBaseInfo = Daos.getDao().getObjectByCondition(HeroBaseInfo.class,condition);
		if (heroBaseInfo != null){
			hero = HeroUtil.coverHero(heroBaseInfo);
			heroStore.addHero(hero);
		}
		return hero;
	}

	@Override
	public void init() {

	}

	@Override
	public void login(long uid) {

	}

	@Override
	public void offline(long uid) {
		heroStore.offline(uid);
	}

	@Override
	public void exit() {

	}
}
