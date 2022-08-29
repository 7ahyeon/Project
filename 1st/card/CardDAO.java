package card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import item.ItemsVO;
import project.CommonJdbcUtil;

public class CardDAO {

	private static Connection conn = CommonJdbcUtil.getConnection();
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	private static int resultInsert;
	private static int resultUpdate;
	

	
	static {
		try {
			Class.forName(CommonJdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<CardVO> selectCardDAO(String custCode) {
		List<CardVO> list = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM CARD ");
			sql.append("WHERE CUST_CODE = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,  custCode);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CardVO>();
			
			while (rs.next()) {
				CardVO vo = new CardVO(
						rs.getString("BANK_NAME"), 
						rs.getString("CARD_NUM"),
						rs.getString("PASSWORD"));
				
				list.add(vo);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	
	}
	//카드정보 입력
	public static void  insertCardDAO(String cardCode, String custCode, String bankName, String cardNum , String cardPw) {
	
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "INSERT INTO CARD ";
			sql += "	(CARD_CODE, CUST_CODE, BANK_NAME, CARD_NUM, PASSWORD) ";
			sql += "	VALUES ((SELECT NVL(MAX(CARD_CODE), 0) + 1 FROM CARD), ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custCode); //로그인한 고객정보로 custCode 자동입력
			pstmt.setString(2, bankName);
			pstmt.setString(3,  cardNum);
			pstmt.setString(4,  cardPw);
			
			resultInsert += pstmt.executeUpdate();
			System.out.println("카드 정보 등록 " + resultInsert + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}	
	}

	//카드정보 삭제
	public static void deleteCardDAO(String custCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "DELETE CARD ";
			sql += "WHERE CUST_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, custCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("카드 정보 삭제 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//카드정보 삭제
	public static void deleteCard2DAO(String cardCode, String custCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "DELETE CARD ";
			sql += "WHERE CUST_CODE = ? ";
			sql += "AND CARD_CODE = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int i = 1;
			pstmt.setString(i++, custCode);
			pstmt.setString(i++, cardCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("카드 정보 삭제 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//카드정보 수정
	public static void updateCardDAO(String custCode, String updateBankName, String updateCardCode, String updateCardPw) {
				
		try {
			
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE CARD ";
			sql += "	SET BANK_NAME = ?, ";
			sql += "	SET CARD_CODE = ?, ";
			sql += "	SET PASSWORD = ?, ";
			sql += "	WHERE CUST_CODE = ?";
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(4, custCode);
			pstmt.setString(1, updateBankName);
			pstmt.setString(2, updateCardCode);
			pstmt.setString(3, updateCardPw);
			
			resultUpdate += pstmt.executeUpdate();
			
			System.out.println("카드 정보 수정 " + resultInsert + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
		}
	
	//은행명 수정
	public static void updateBanknameDAO(String custCode, String updateBankName) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE CARD ";
			sql += "	SET BANK_NAME = ? ";
			sql += "WHERE CUST_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, custCode);
			pstmt.setString(1, updateBankName);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("카드 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//카드번호 수정
	public static void updateCardCodeDAO(String custCode, String updateCardCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE CARD ";
			sql += "	SET CARD_CODE = ? ";
			sql += "WHERE CUST_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, custCode);
			pstmt.setString(1, updateCardCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("카드 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//카드비밀번호 수정
	public static void updateCardPwDAO(String custCode, String updateCardPw) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE CARD ";
			sql += "	SET PASSWORD = ? ";
			sql += "WHERE CUST_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, custCode);
			pstmt.setString(1, updateCardPw);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("카드 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//고객의 카드정보 전체 조회 (카드번호)
	public static List<CardVO> selectCardAll(String custCode) {
		List<CardVO> list = null;
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += ("SELECT * ");
			sql += ("FROM CARD ");
			sql += ("WHERE CUST_CODE = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, custCode);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CardVO>();
			while (rs.next()) {
				CardVO vo = new CardVO(
						rs.getString("CARD_CODE"), 
						rs.getString("CUST_CODE"), 
						rs.getString("BANK_NAME"), 
						rs.getString("CARD_NUM"), 
						rs.getString("PASSWORD"));		
				list.add(vo);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//카드번호 중복입력 방지
	public static ArrayList<String> getCardCodes() {
		
		ArrayList<String> list = null;
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			list = new ArrayList<>();
			
			String getCardCode = "SELECT CARD_CODE FROM CARD ";
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(getCardCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	
	
	
	

	
	
	
	
} //class end