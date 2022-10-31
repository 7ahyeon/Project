package com.mystudy.house.vo;

public class DeleteStatusVO {
	private String del;
	private int productNum;
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public DeleteStatusVO(String del, int productNum) {
		super();
		this.del = del;
		this.productNum = productNum;
	}
	
}
