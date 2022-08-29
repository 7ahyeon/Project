package item;

public class ItemsVO {

	private String itemCode;
	private String beanCode;
	private String beanName;
	private String coo;
	private String description;
	private String itemName;
	private String introduction;
	private int price;
	private String managerCode;
	private String modifyType;
	private String modifyDate;
	
	
	
	
	public ItemsVO() { }

	



	public ItemsVO(String beanCode, String beanName, String coo, String description) {
		super();
		this.beanCode = beanCode;
		this.beanName = beanName;
		this.coo = coo;
		this.description = description;
	}





	public ItemsVO(String itemCode, String beanCode, String itemName, String introduction, int price) {
		super();
		this.itemCode = itemCode;
		this.beanCode = beanCode;
		this.itemName = itemName;
		this.introduction = introduction;
		this.price = price;
	}




	public ItemsVO(String managerCode, String itemCode, String beanCode, String itemName, String introduction, int price,
			String modifyType, String modifyDate) {
		super();
		this.itemCode = itemCode;
		this.beanCode = beanCode;
		this.itemName = itemName;
		this.introduction = introduction;
		this.price = price;
		this.managerCode = managerCode;
		this.modifyType = modifyType;
		this.modifyDate = modifyDate;
	}
	
	public ItemsVO(String itemCode, String beanCode, String itemName, String introduction, int price,
			String managerCode, String modifyType, String modifyDate) {
		super();
		this.itemCode = itemCode;
		this.beanCode = beanCode;
		this.itemName = itemName;
		this.introduction = introduction;
		this.price = price;
		this.managerCode = managerCode;
		this.modifyType = modifyType;
		this.modifyDate = modifyDate;
	}




	public String getBeanName() {
		return beanName;
	}





	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}





	public String getCoo() {
		return coo;
	}





	public void setCoo(String coo) {
		this.coo = coo;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public String getItemCode() {
		return itemCode;
	}




	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}




	public String getBeanCode() {
		return beanCode;
	}




	public void setBeanCode(String beanCode) {
		this.beanCode = beanCode;
	}




	public String getItemName() {
		return itemName;
	}




	public void setItemName(String itemName) {
		this.itemName = itemName;
	}




	public String getIntroduction() {
		return introduction;
	}




	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}




	public String getManagerCode() {
		return managerCode;
	}




	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}




	public int getPrice() {
		return price;
	}




	public void setPrice(int price) {
		this.price = price;
	}




	public String getModifyType() {
		return modifyType;
	}




	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}




	public String getModifyDate() {
		return modifyDate;
	}




	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}



}
