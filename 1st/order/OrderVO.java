package order;

import java.util.ArrayList;

public class OrderVO {
	
	
	private String category;
	private String cupSize;
	private String cupSelect;
	private String HotIce;
	private ArrayList<String> choice; // index 0 : ITEM_CODE, index 1 : ITEM_NAME , index 2 : DESCRIPTION, index 3 : PRICE
	
	private int optionCode;
	private String itemCode;
	private String bean;
	private String espresso;
	private int addShot;
	private String milk;
	private String syrup;
	private int drinkAmount = 1;
	private String ice;
	private int itemPrice;
	
	
	
	public OrderVO() {
		super();
	}

	public void plusItemPrice (int num) {
		itemPrice += num;
	}
	
	public OrderVO(String category, int optionCode, String itemCode, String bean, String cupSize, String cupSelect, String hotIce,
			String espresso, int addShot, String milk, String syrup, int drinkAmount,
			String ice, int itemPrice, ArrayList<String> choice) {
		super();
		this.category = category;
		this.cupSize = cupSize;
		this.cupSelect = cupSelect;
		HotIce = hotIce;
		this.optionCode = optionCode;
		this.itemCode = itemCode;
		this.bean = bean;
		this.espresso = espresso;
		this.addShot = addShot;
		this.milk = milk;
		this.syrup = syrup;
		this.drinkAmount = drinkAmount;
		this.ice = ice;
		this.itemPrice = itemPrice;
		this.choice = choice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCupSize() {
		return cupSize;
	}
	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}
	@Override
	public String toString() {
		return "OrderVO [category=" + category + ", cupSize=" + cupSize + ", cupSelect=" + cupSelect + ", HotIce="
				+ HotIce + ", choice=" + choice + ", optionCode=" + optionCode + ", itemCode=" + itemCode + ", bean="
				+ bean + ", espresso=" + espresso + ", addShot=" + addShot + ", milk=" + milk + ", syrup=" + syrup
				+ ", drinkAmount=" + drinkAmount + ", ice=" + ice + ", itemPrice=" + itemPrice + "]";
	}

	public String getCupSelect() {
		return cupSelect;
	}
	public void setCupSelect(String cupSelect) {
		this.cupSelect = cupSelect;
	}
	public String getHotIce() {
		return HotIce;
	}
	public void setHotIce(String hotIce) {
		HotIce = hotIce;
	}
	public ArrayList<String> getChoice() {
		return choice;
	}
	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}
	public String getIce() {
		return ice;
	}
	public void setIce(String ice) {
		this.ice = ice;
	}
	public String getBean() {
		return bean;
	}
	public void setBean(String bean) {
		this.bean = bean;
	}
	public String getEspresso() {
		return espresso;
	}
	public void setEspresso(String espresso) {
		this.espresso = espresso;
	}
	public String getMilk() {
		return milk;
	}
	public void setMilk(String milk) {
		this.milk = milk;
	}
	public String getSyrup() {
		return syrup;
	}
	public void setSyrup(String syrup) {
		this.syrup = syrup;
	}
	public int getAddShot() {
		return addShot;
	}
	public void setAddShot(int addShot) {
		this.addShot = addShot;
	}
	public int getDrinkAmount() {
		return drinkAmount;
	}
	public void setDrinkAmount(int drinkAmount) {
		this.drinkAmount = drinkAmount;
	}
	
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	

}
