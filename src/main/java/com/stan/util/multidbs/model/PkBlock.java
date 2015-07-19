package com.stan.util.multidbs.model;

/**
 * 依据PrimaryKey 生成的数据块信息
 * 
 * @author Stan Wang
 *
 */
public class PkBlock {

	private int id;

	private int start;

	private int end;

	private int db;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getDb() {
		return db;
	}

	public void setDb(int db) {
		this.db = db;
	}

}
