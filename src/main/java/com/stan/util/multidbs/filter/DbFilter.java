package com.stan.util.multidbs.filter;

import java.util.Map;

import org.hibernate.SessionFactory;

public interface DbFilter {
	
	public SessionFactory getFilterDb(Long pkID,Map<Integer,SessionFactory> dbCache);

}
