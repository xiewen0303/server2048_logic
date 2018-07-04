package com.game.service.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.game.context.IStore;
import com.game.core.IPlayer;

@Component
public class LoginStore implements IStore {

	/**
	 * accountId  =  uid
	 */
	private Map<String,Long> accountIds = new ConcurrentHashMap<>();

	/**
	 * uid = player
	 */
	private Map<Long,IPlayer> players = new ConcurrentHashMap<Long, IPlayer>();

	public List<IPlayer > getIPlayers(){
		return new ArrayList<>(players.values());
	}

	public IPlayer getIPlayer(long uid){
		return players.get(uid);
	}

	public Long getUid(String accountId){
		return accountIds.get(accountId);
	}

	public boolean isOnlineAccount(String accountId) {
		return accountIds.get(accountId) != null;
	}

	public void addLoginPlayer(IPlayer player,String accountId) {
		players.put(player.getUid(),player);
		accountIds.put(accountId,player.getUid());
	}

	public void offline(long uid,String accountId) {
		accountIds.remove(accountId);
		players.remove(uid);
	}
}
