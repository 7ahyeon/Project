package com.mystudy.house.vo;

public class ProductUpdatestockVO {
	private int stock, productNum;

	public ProductUpdatestockVO(int stock, int productNum) {
		super();
		this.stock = stock;
		this.productNum = productNum;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
}
