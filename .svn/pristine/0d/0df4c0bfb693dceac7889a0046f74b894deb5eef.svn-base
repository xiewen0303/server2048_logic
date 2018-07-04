package com.game.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import db.ibatis.dao.IDao;

@Repository
public class Daos {

	private static IDao dao;

	public static IDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(@Qualifier("daoForBatch")IDao dao) {
		Daos.dao = dao;
	}
}