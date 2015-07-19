package com.stan.util.multidbs.dao;

import java.util.List;

import com.stan.util.multidbs.model.DatabaseInfo;

public interface DatabaseInfoDao {
	
	List<DatabaseInfo> findAllDbs();
	
}
