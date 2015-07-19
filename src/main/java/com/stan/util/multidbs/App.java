package com.stan.util.multidbs;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.stan.util.multidbs.filter.SectionFilter;
import com.stan.util.multidbs.model.testDemo;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		DbManager.init("/hibernate.cfg.xml", "/hibernate_node.cfg.xml");
		DbManager.setFilter(new SectionFilter(2));
		
		
		
		Collection<SessionFactory> list = DbManager.getAllNodeSessionFactory();
		
		for(SessionFactory fa : list){
			showdemo(fa);
		}
		
	}

	private static void showdemo(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		testDemo demo = (testDemo) session.createCriteria(testDemo.class).list().get(0);
		System.out.println(demo.getDbname());
		tr.commit();
	}
}
