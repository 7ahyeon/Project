package order;

import java.sql.Date;

public class TestOrderVO {

	
	private int orderCode;
	private String custCode;
	private int totalPrice;
	private String orderDate;
	
	
	
	
	@Override
	public String toString() {
		return "TestOrderVO [orderCode=" + orderCode + ", custCode=" + custCode + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + "]";
	}
	public TestOrderVO() {
		super();
	}
	public TestOrderVO(int orderCode, String custCode, int totalPrice, String orderDate) {
		super();
		this.orderCode = orderCode;
		this.custCode = custCode;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public String getcustCode() {
		return custCode;
	}
	public void setcustCode(String custCode) {
		this.custCode = custCode;
	}
	public int gettotalPrice() {
		return totalPrice;
	}
	public void settotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	
}
