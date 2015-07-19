package com.stan.util.MultiDbs.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.stan.util.multidbs.model.DatabaseInfo;
import com.stan.util.multidbs.model.testDemo;
import com.stan.util.multidbs.utils.HibernateUtil;

public class HibernateUtilTest {

	@Test
	public void buildNodeSessionFactory() {
		HibernateUtil.initNodeConfig("/hibernate_node.cfg.xml");

		DatabaseInfo di = new DatabaseInfo();

		di.setDbName("db1");
		di.setIp("127.0.0.1");
		di.setPassword("2946093");
		di.setPort("3306");
		di.setUser("root");

		SessionFactory factory = HibernateUtil.buildNodeSessionFactory(di);

		Session session = factory.getCurrentSession();

		testDemo demo = new testDemo();
		demo.setDbname("db1");

		Transaction tr = session.beginTransaction();
		session.save(demo);
		tr.commit();
	}

}
