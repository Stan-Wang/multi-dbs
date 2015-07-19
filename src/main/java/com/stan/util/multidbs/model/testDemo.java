package com.stan.util.multidbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

@Entity
@DynamicUpdate(true)
@Table(appliesTo = "testDemo")
public class testDemo {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "dbName")
	private String dbname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

}
