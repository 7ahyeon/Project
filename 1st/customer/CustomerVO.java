package customer;

public class CustomerVO {

	private String custCode;
	private String id;
	private String level;
	private String password;
	private String e_mail;
	private String phone;
	private String name;
	private String rrn;
	private int stamp;
	
	
	
	
	
	
	
	
	public CustomerVO() {
		super();
	}
	public CustomerVO(String custCode, String id, String level, String password, String e_mail, String phone,
			String name, String rrn, int stamp) {
		super();
		this.custCode = custCode;
		this.id = id;
		this.level = level;
		this.password = password;
		this.e_mail = e_mail;
		this.phone = phone;
		this.name = name;
		this.rrn = rrn;
		this.stamp = stamp;
	}
	@Override
	public String toString() {
		return "CustomerVO [custCode=" + custCode + ", id=" + id + ", level=" + level + ", password=" + password
				+ ", e_mail=" + e_mail + ", phone=" + phone + ", name=" + name + ", rrn=" + rrn
				+ ", stamp=" + stamp + "]";
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String rrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public int getStamp() {
		return stamp;
	}
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	
	
	
	
	
	
	
	
}
