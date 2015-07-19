package com.stan.util.multidbs;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import com.stan.util.multidbs.dao.DatabaseInfoDao;
import com.stan.util.multidbs.daoImpl.DatabaseInfoDaoImpl;
import com.stan.util.multidbs.filter.DbFilter;
import com.stan.util.multidbs.model.DatabaseInfo;
import com.stan.util.multidbs.utils.HibernateUtil;

public class DbManager {

	private static Logger logger = Logger.getLogger(DbManager.class);

	private static Map<Integer, SessionFactory> dbCache = new HashMap<Integer, SessionFactory>();

	private static DatabaseInfoDao DatabaseDao = new DatabaseInfoDaoImpl();

	private static DbFilter dbfilter;

	public static SessionFactory masterSessionFactory;

	public static void init(String masterConfig, String nodeConfig) {

		logger.debug("初始化数据库配置文件:<" + masterConfig + "><" + nodeConfig + ">");

		HibernateUtil.initMasterConfig(masterConfig);
		HibernateUtil.initNodeConfig(nodeConfig);

		masterSessionFactory = HibernateUtil.buildMasterSessionFactory();

		List<DatabaseInfo> dblist = DatabaseDao.findAllDbs();

		for (DatabaseInfo db : dblist) {
			dbCache.put(db.getId(), HibernateUtil.buildNodeSessionFactory(db));
			logger.debug("初始化数据库缓存:<" +  db + ">");
		}
	}

	public static void setFilter(DbFilter filter) {
		dbfilter = filter;
	}

	public static SessionFactory getNodeSessionFactory(Long pkID) {
		return dbfilter.getFilterDb(pkID, dbCache);
	}

	public static Collection<SessionFactory> getAllNodeSessionFactory() {
		return Collections.unmodifiableCollection(dbCache.values());
	}

}
