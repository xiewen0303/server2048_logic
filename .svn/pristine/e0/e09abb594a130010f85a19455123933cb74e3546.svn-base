package com.game.service.hero;


import org.springframework.stereotype.Component;

import com.game.context.IStore;
import com.game.service.hero.bean.Hero;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HeroStore implements IStore {

    private Map<Long,Hero> heros = new ConcurrentHashMap<>();

    public void addHero(Hero hero) {
        heros.put(hero.getUid(),hero);
    }

    public Hero getHero(long uid) {
        return heros.get(uid);
    }

    public List<Long> getAllOnlineUids() {
        return new ArrayList<>(heros.keySet());
    }

    public List<Hero> getAllOnlineHeros() {
        return new ArrayList<>(heros.values());
    }

    public void offline(long uid){
        heros.remove(uid);
    }
}