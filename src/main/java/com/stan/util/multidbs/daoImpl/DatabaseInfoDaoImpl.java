package com.stan.util.multidbs.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.stan.util.multidbs.dao.DatabaseInfoDao;
import com.stan.util.multidbs.model.DatabaseInfo;
import com.stan.util.multidbs.DbManager;

public class DatabaseInfoDaoImpl implements DatabaseInfoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<DatabaseInfo> findAllDbs() {
		Session session = DbManager.masterSessionFactory.getCurrentSession();
		Transaction ts = session.beginTransaction();
		List<DatabaseInfo> list=session.createCriteria(DatabaseInfo.class).list();
		ts.commit();
		return list;
	}

}
