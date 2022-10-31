package com.mystudy.house.vo;


public class MyinquirypageVO {
	private int begin, end;
	private String id;
	
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MyinquirypageVO(int begin, int end, String id) {
		super();
		this.begin = begin;
		this.end = end;
		this.id = id;
	}
	
	
	
	
}
