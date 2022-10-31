package com.mystudy.house.vo;

public class ProductListCategoryVO {
	
	private String id;
	private int categoryDetail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCategoryDetail() {
		return categoryDetail;
	}
	public void setCategoryDetail(int categoryDetail) {
		this.categoryDetail = categoryDetail;
	}
	public ProductListCategoryVO(String id, int categoryDetail) {
		super();
		this.id = id;
		this.categoryDetail = categoryDetail;
	}
	

	
}
