package order;

public class OptionsVO {
	
	private String category;
	private int optionCode;
	private String itemCode;
	private String beanName;
	private String cupSize;
	private String cupSelect;
	private String hotIce;
	private String espresso;
	private int addShot;
	private String milk;
	private String syrup;
	private int drinkAmount = 1;
	private String ice;
	
	
	
	
	
	
	
	public OptionsVO(String category, int optionCode, String itemCode, String beanName, String cupSize,
			String cupSelect, String hotIce, String espresso, int addShot, String milk, String syrup, int drinkAmount,
			String ice) {
		super();
		this.category = category;
		this.optionCode = optionCode;
		this.itemCode = itemCode;
		this.beanName = beanName;
		this.cupSize = cupSize;
		this.cupSelect = cupSelect;
		this.hotIce = hotIce;
		this.espresso = espresso;
		this.addShot = addShot;
		this.milk = milk;
		this.syrup = syrup;
		this.drinkAmount = drinkAmount;
		this.ice = ice;
	}
	@Override
	public String toString() {
		return "OptionsVO [category=" + category + ", optionCode=" + optionCode + ", itemCode=" + itemCode
				+ ", beanCode=" + beanName + ", cupSize=" + cupSize + ", cupSelect=" + cupSelect + ", hotIce=" + hotIce
				+ ", espresso=" + espresso + ", addShot=" + addShot + ", milk=" + milk + ", syrup=" + syrup
				+ ", drinkAmount=" + drinkAmount + ", ice=" + ice + "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(int optionCode) {
		this.optionCode = optionCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getBeanCode() {
		return beanName;
	}
	public void setBeanCode(String beanCode) {
		this.beanName = beanCode;
	}
	public String getCupSize() {
		return cupSize;
	}
	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}
	public String getCupSelect() {
		return cupSelect;
	}
	public void setCupSelect(String cupSelect) {
		this.cupSelect = cupSelect;
	}
	public String getHotIce() {
		return hotIce;
	}
	public void setHotIce(String hotIce) {
		this.hotIce = hotIce;
	}
	public String getEspresso() {
		return espresso;
	}
	public void setEspresso(String espresso) {
		this.espresso = espresso;
	}
	public int getAddShot() {
		return addShot;
	}
	public void setAddShot(int addShot) {
		this.addShot = addShot;
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
	public int getDrinkAmount() {
		return drinkAmount;
	}
	public void setDrinkAmount(int drinkAmount) {
		this.drinkAmount = drinkAmount;
	}
	public String getIce() {
		return ice;
	}
	public void setIce(String ice) {
		this.ice = ice;
	}
	
	
	
	
	
	
	

}
