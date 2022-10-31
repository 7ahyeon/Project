package com.mystudy.house.vo;

public class ProductInsertInfoImgVO {
	private String productInfo;
	private int productNum;
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public ProductInsertInfoImgVO(String productInfo, int productNum) {
		super();
		this.productInfo = productInfo;
		this.productNum = productNum;
	}
	
	

	
}
