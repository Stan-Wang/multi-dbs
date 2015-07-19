package com.stan.util.multidbs.utils;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.stan.util.multidbs.model.DatabaseInfo;

/**
 * 数据库连接配置初始化等相关工具
 * 
 * @author Stan Wang
 *
 */
public class HibernateUtil {
	
	private static Logger logger = Logger.getLogger(HibernateUtil.class);

	private static String masterConfig;
	private static String nodeConfig;

	private final static String URL = "jdbc:mysql://{0}:{1}/{2}?useUnicode=true&amp;characterEncoding=UTF-8&amp;mysqlEncoding=utf-8";
	private final static String CONNECTION = "hibernate.connection.url";
	private final static String USERNAME = "hibernate.connection.username";
	private final static String PASSWORD = "hibernate.connection.password";

	public static void initMasterConfig(String configPath) {
		masterConfig = configPath;
	}

	public static void initNodeConfig(String configPath) {
		nodeConfig = configPath;
	}

	public static SessionFactory buildMasterSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			if (masterConfig != null) {
				configuration.configure(masterConfig);
			} else {
				configuration.configure();
			}
			return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	private static Configuration getNodeConfiguration() {
		Configuration configuration = new Configuration();
		configuration.configure(nodeConfig);
		return configuration;
	}

	public static SessionFactory buildNodeSessionFactory(DatabaseInfo db) {
		
		Configuration configuration = getNodeConfiguration();
		String url = MessageFormat.format(URL, db.getIp(), db.getPort(), db.getDbName());
		configuration.setProperty(CONNECTION, url);
		configuration.setProperty(USERNAME, db.getUser());
		configuration.setProperty(PASSWORD, db.getPassword());
		logger.debug("创建数据库连接工厂 ==> " + url);
		
		return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
	}

}
