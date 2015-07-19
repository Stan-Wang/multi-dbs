package com.stan.util.multidbs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

/**
 * 
 * 数据库信息实体类
 * 
 * @author Stan Wang
 *
 */
@Entity
@DynamicUpdate(true)
@Table(appliesTo = "DatabaseInfo")
public class DatabaseInfo {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "dbName")
	private String dbName;
	@Column(name = "user")
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "ip")
	private String ip;
	@Column(name = "port")
	private String port;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "DatabaseInfo [dbName=" + dbName + ", user=" + user + ", password=" + password + ", ip=" + ip + ", port=" + port + "]";
	}

}
