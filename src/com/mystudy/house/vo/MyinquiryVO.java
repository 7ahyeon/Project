package com.mystudy.house.vo;

import java.sql.Date;

public class MyinquiryVO {
	private int proinIdx;
	private Date qdate;
	private String productName, contents, id;
	
	public int getProinIdx() {
		return proinIdx;
	}
	public void setProinIdx(int proinIdx) {
		this.proinIdx = proinIdx;
	}
	public Date getQdate() {
		return qdate;
	}
	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
