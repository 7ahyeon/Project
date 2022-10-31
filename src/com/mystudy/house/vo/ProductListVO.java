package com.mystudy.house.vo;

public class ProductListVO {
	
	private int productNum;
	private String categoryName, categoryDetail, productName;
	private int stock, productPrice;
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDetail() {
		return categoryDetail;
	}
	public void setCategoryDetail(String categoryDetail) {
		this.categoryDetail = categoryDetail;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public ProductListVO(int productNum, String categoryName, String categoryDetail, String productName, int stock,
			int productPrice) {
		super();
		this.productNum = productNum;
		this.categoryName = categoryName;
		this.categoryDetail = categoryDetail;
		this.productName = productName;
		this.stock = stock;
		this.productPrice = productPrice;
	}
	
}
