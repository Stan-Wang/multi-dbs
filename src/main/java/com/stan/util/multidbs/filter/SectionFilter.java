package com.stan.util.multidbs.filter;

import java.util.Map;

import org.hibernate.SessionFactory;

/**
 * 以固定Section大小判定主键所属数据库
 * 
 * @author Stan Wang
 *
 */
public class SectionFilter implements DbFilter {
	
	private int sectionCount;
	
	public SectionFilter(int SectionSize){
		sectionCount = SectionSize;
	}

	@Override
	public SessionFactory getFilterDb(Long pkID, Map<Integer, SessionFactory> dbCache) {
		return dbCache.get(getDBKey(pkID));
	}

	private Object getDBKey(Long pkID) {
		return (int) (pkID / sectionCount) + 1;
	}

}
