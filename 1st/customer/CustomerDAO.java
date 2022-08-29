package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import item.ItemsVO;
import project.CommonJdbcUtil;
import project.MainPage;

public class CustomerDAO {
	
	private static Connection conn = CommonJdbcUtil.getConnection();
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private Scanner sc = new Scanner(System.in);
	
	private static int result;
	
	private static String password;
	private static String custId;
	
	private MainPage main = new MainPage();
	
	static {
		try {
			Class.forName(CommonJdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] CustomerDAO JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	

	
	public static String selectCustIdDAO(String custId) {
		
		try {
			String sql = "";
			sql += "SELECT CUST_ID ";
			sql += "FROM CUSTOMERS ";
			sql += "WHERE CUST_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custId);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String str = rs.getString("CUST_ID");
				return str;					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String selectRRNDAO(String rrn) {
		
		try {
			String sql = "";
			sql += "SELECT RRN ";
			sql += "FROM CUSTOMERS ";
			sql += "WHERE RRN = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rrn);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String str = rs.getString("RRN");
				return str;					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static ArrayList<String> getCustId() { 
		
		ArrayList<String> list = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CUST_ID FROM CUSTOMERS ");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			list = new ArrayList<String>();
			
			while(rs.next()) {
				list.add(rs.getString("CUST_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void insertGenderDAO(String custId, String gender) {
		try {
			String sql = "";
			sql += "UPDATE CUSTOMERS ";
			sql += "	SET GENDER = ? ";
			sql += "WHERE CUST_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gender);
			pstmt.setString(2, custId);
			
			result += pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
	}
	
	public static void insertCustomerDAO(String custId, String password, String email, String phone, String name, String rrn) {
		
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO CUSTOMERS (");
			sql.append("    CUST_CODE, CUST_ID, PASSWORD, EMAIL, PHONE_NUMBER, CUST_NAME, RRN) ");
			sql.append("VALUES (CUSTSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?) ");	
			
			pstmt = conn.prepareStatement(String.valueOf(sql));
			
			pstmt.setString(1, custId);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			pstmt.setString(5, name);
			pstmt.setString(6, rrn);
			
			
			result = pstmt.executeUpdate();
			System.out.println(">> 회원가입을 축하드립니다!");
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
	}
	
	
	public static CustomerVO getCustomerByCustId (String custId) { // 아이디로 비밀번호 찾을 때 
		
		CustomerVO cust = null;
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM CUSTOMERS ");
			sql.append("WHERE CUST_ID = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, custId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cust = new CustomerVO(
						rs.getString("CUST_CODE"), 
						rs.getString("CUST_LEVEL"), 
						rs.getString("CUST_ID"), 
						rs.getString("PASSWORD"), 
						rs.getString("EMAIL"), 
						rs.getString("PHONE_NUMBER"), 
						rs.getString("CUST_NAME"), 
						rs.getString("RRN"), 
						rs.getInt("STAMP"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}
	
	
	public static CustomerVO getCustomerByCustCode (String custCode) { // 아이디로 비밀번호 찾을 때 
		
		CustomerVO customer = null;
		
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "SELECT CUST_CODE, CUST_ID, CUST_LEVEL, PASSWORD, EMAIL, PHONE_NUMBER, CUST_NAME, RRN, STAMP FROM CUSTOMERS WHERE CUST_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custCode);
			
			rs = pstmt.executeQuery();
			if (!rs.next()) { // rs.next() 가 false인데 rs.next()를 호출하면 마지막행 다음의 결과 에러뜸
				customer = null;
			} else {
				customer = new CustomerVO(rs.getString("CUST_CODE"), 
						rs.getString("CUST_ID"), 
						rs.getString("CUST_LEVEL"), 
						rs.getString("PASSWORD"),
						rs.getString("EMAIL"), 
						rs.getString("PHONE_NUMBER"), 
						rs.getString("CUST_NAME"), 
						rs.getString("RRN"), 
						rs.getInt("STAMP"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	
	
	
	
	
	public static void updatePhone (String custCode, String phone) {
		
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CUSTOMERS ");
			sql.append("SET PHONE_NUMBER = ? ");
			sql.append("WHERE CUST_CODE = ? ");
			
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, phone);
			pstmt.setString(2, custCode);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmail (String custCode, String email) {
		
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE CUSTOMERS ");
			sql.append("SET EMAIL = ? ");
			sql.append("WHERE CUST_CODE = ? ");
			
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, email);
			pstmt.setString(2, custCode);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void deleteCustomer(String custCode) {
		
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "DELETE FROM CARD WHERE CUST_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custCode);
			pstmt.executeUpdate();
			
			System.out.println("자식테이블 값 삭제");
			
			sql = "DELETE FROM CUSTOMERS WHERE CUST_CODE = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custCode);
			
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static boolean tryLoginDAO(String custId, String password) {
		
		boolean result = false;
		try {
			
			String getCustID = "SELECT * FROM CUSTOMERS WHERE CUST_ID = ? AND PASSWORD = ?"; 
			
			conn = CommonJdbcUtil.getConnection();
			pstmt = conn.prepareStatement(getCustID);
			pstmt.setString(1, custId);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}


	
	public static String getCustIdByPN(String rrn, String phoneNumber) {
		
		try {
			String sql = "SELECT CUST_ID FROM CUSTOMERS WHERE RRN = ? AND PHONE_NUMBER = ? "; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rrn);
			pstmt.setString(2, phoneNumber);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) custId = rs.getString("CUST_ID");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return custId;
	}
	
	public static String getCustIdByEMail(String rrn, String eMail) {
				
		try {
			String sql = "SELECT CUST_ID FROM CUSTOMERS WHERE RRN = ? AND EMAIL = ? "; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rrn);
			pstmt.setString(2, eMail);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) custId = rs.getString("CUST_ID");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return custId;
	}
	
	
	public static String getPwByPN(String custId, String rrn, String phoneNumber) {
	
		try {
			String sql = "SELECT PASSWORD FROM CUSTOMERS WHERE CUST_ID = ? AND RRN = ? AND PHONE_NUMBER = ? "; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custId);
			pstmt.setString(2, rrn);
			pstmt.setString(3, phoneNumber);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) password = rs.getString("PASSWORD");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}
	
	
	
	public static String getPwByEMail(String custId, String rrn, String eMail) {
		
		try {
			String sql = "SELECT PASSWORD FROM CUSTOMERS WHERE CUST_ID = ? AND RRN = ? AND EMAIL = ? "; 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custId);
			pstmt.setString(2, rrn);
			pstmt.setString(3, eMail);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) password = rs.getString("PASSWORD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return password;
		
	}

	public static void UpdateCoupon(String custCode, int stampPlus) {
		
		int stamp = 0;
		try {
			
			String sql = "SELECT STAMP FROM CUSTOMERS WHERE CUST_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custCode);
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				
				stamp = rs.getInt("STAMP");
			}
			System.out.println("현재 스탬프 : " + stamp); 
			
			stamp += stampPlus;
			
			sql = "UPDATE CUSTOMERS SET STAMP = ? WHERE CUST_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  stamp);
			pstmt.setString(2,  custCode);
			pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
