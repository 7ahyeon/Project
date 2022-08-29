package nutrition;

public class NutritionVO {

	private String itemCode;
	private int kcal;
	private int sodium;
	private int cholesterol;
	private int sugar;
	private int protein;
	private int caffeine;
	
	public NutritionVO() {
	}
	
	public NutritionVO(String itemCode, int kcal, int sodium, int cholesterol, int sugar, int protein, int caffeine) {
		this.itemCode = itemCode;
		this.kcal = kcal;
		this.sodium = sodium;
		this.cholesterol = cholesterol;
		this.sugar = sugar;
		this.protein = protein;
		this.caffeine = caffeine;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public int getKcal() {
		return kcal;
	}
	
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	
	public int getSodium() {
		return sodium;
	}
	
	public void setSodium(int sodium) {
		this.sodium = sodium;
	}
	
	public int getCholesterol() {
		return cholesterol;
	}
	
	public void setCholesterol(int cholesterol) {
		this.cholesterol = cholesterol;
	}
	
	public int getSugar() {
		return sugar;
	}
	
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	
	public int getProtein() {
		return protein;
	}
	
	public void setProtein(int protein) {
		this.protein = protein;
	}
	
	public int getCaffeine() {
		return caffeine;
	}
	
	public void setCaffeine(int caffeine) {
		this.caffeine = caffeine;
	}
	
}
