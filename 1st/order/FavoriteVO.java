package order;

public class FavoriteVO {
	
	private String favoriteName;
	private int favoriteCode;
	private String custCode;
	private int optionCode;
	
	
	
	
	@Override
	public String toString() {
		return "FavoriteVO [favoriteName=" + favoriteName + ", favoriteCode=" + favoriteCode + ", custCode=" + custCode
				+ ", optionCode=" + optionCode + "]";
	}
	public FavoriteVO() {
		super();
	}
	public FavoriteVO(String favoriteName, int favoriteCode, String custCode, int optionCode) {
		super();
		this.favoriteName = favoriteName;
		this.favoriteCode = favoriteCode;
		this.custCode = custCode;
		this.optionCode = optionCode;
	}
	public String getFavoriteName() {
		return favoriteName;
	}
	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
	}
	public int getFavoriteCode() {
		return favoriteCode;
	}
	public void setFavoriteCode(int favoriteCode) {
		this.favoriteCode = favoriteCode;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public int getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(int optionCode) {
		this.optionCode = optionCode;
	}
	
	
	
	
	
}
