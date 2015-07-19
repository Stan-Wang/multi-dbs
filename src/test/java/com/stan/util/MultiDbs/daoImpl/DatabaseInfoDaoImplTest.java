package com.stan.util.MultiDbs.daoImpl;

import org.junit.BeforeClass;
import org.junit.Test;

import com.stan.util.multidbs.dao.DatabaseInfoDao;
import com.stan.util.multidbs.daoImpl.DatabaseInfoDaoImpl;
import com.stan.util.multidbs.filter.SectionFilter;
import com.stan.util.multidbs.DbManager;

public class DatabaseInfoDaoImplTest {
	
	@BeforeClass
	public static void init(){
		DbManager.init("/hibernate.cfg.xml", "/hibernate_node.cfg.xml");
		DbManager.setFilter(new SectionFilter(2));
	}

	@Test
	public void test() {
		DatabaseInfoDao dao = new DatabaseInfoDaoImpl();
		
		long start = System.currentTimeMillis();
		for(int i = 0 ; i<1000000; i++){
			getAll(dao);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public void getAll(DatabaseInfoDao dao){
		dao.findAllDbs();
	}

}
