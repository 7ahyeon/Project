package com.mystudy.house.vo;

public class ProductInsertImgVO {
	private int productNum;
	private String imagefile;
	
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public ProductInsertImgVO(int productNum, String imagefile) {
		super();
		this.productNum = productNum;
		this.imagefile = imagefile;
	}
	

	
}
