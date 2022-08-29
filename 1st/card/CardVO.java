package card;

public class CardVO {

	private String cardCode;
	private String custCode;
	private String bankName;
	private String cardNum;
	private String cardPw;
	
	public CardVO() {
	}

	public CardVO(String bankName, String cardNum) {
		this.bankName = bankName;
		this.cardNum = cardNum;
	}
	
	public CardVO(String cardCode, String custCode, String bankName, String cardNum, String cardPw) {
		this.cardCode = cardCode;
		this.custCode = custCode;
		this.bankName = bankName;
		this.cardNum = cardNum;
		this.cardPw = cardPw;
	}

	public CardVO(String bankName, String cardNum, String cardPw) {
		this.bankName = bankName;
		this.cardNum = cardNum;
		this.cardPw = cardPw;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardPw() {
		return cardPw;
	}

	public void setCardPw(String cardPw) {
		this.cardPw = cardPw;
	}

} //class end
