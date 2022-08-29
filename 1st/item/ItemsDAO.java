package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.CommonJdbcUtil;


public class ItemsDAO {
	
	private static Connection conn = CommonJdbcUtil.getConnection();
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	static Scanner sc = new Scanner(System.in);
	private static int choice = 10;
	
	private static String managerCode;
	private static int modifyDateStart;
	private static int modifyDateEnd;
	private static int price;
	private static int resultInsert;
	private static int resultUpdate;
	private static int resultDelete;
	private static int exit = 2;
	
	static {
		try {
			Class.forName(CommonJdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] ItemsDAO JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	

	
	public static void insertItemDAO(String itemCode, String beanCode, String itemName, String introduction, int price) {
		
		
		try {
			String sql = "";
			sql += "INSERT INTO ITEMS ";
			sql += "	(ITEM_CODE, BEAN_CODE, ITEM_NAME, INTRODUCTION, PRICE)";
			sql += "VALUES (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, itemCode);
			pstmt.setString(2, beanCode);
			pstmt.setString(3, itemName);
			pstmt.setString(4, introduction);
			pstmt.setInt(5, price);
			
			resultInsert += pstmt.executeUpdate();
			System.out.println("상품 등록 총 " + resultInsert + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
		}
		
	public static void updateItemDAO(String itemCode, String updateItemCode, String updateBeanCode, 
							String updateItemName, String updateIntroduction, int updatePrice) {
		
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET ITEM_CODE = ? ";
			sql += "		, BEAN_CODE = ? ";
			sql += "		, ITEM_NAME = ? ";
			sql += "		, INTRODUCTION = ? ";
			sql += "		, PRICE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(6, itemCode);
			pstmt.setString(1, updateItemCode);
			pstmt.setString(2, updateBeanCode);
			pstmt.setString(3, updateItemName);
			pstmt.setString(4, updateIntroduction);
			pstmt.setInt(5, updatePrice);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
	}
	
	public static void updateItemPriceDAO(String itemCode, int updatePrice) {
		
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET PRICE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updatePrice);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	public static void updateItemIntroductionDAO(String itemCode, String updateIntroduction) {
		
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET INTRODUCTION = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setString(1, updateIntroduction);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	public static void updateItemNameDAO(String itemCode, String updateItemName) {
		
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET ITEM_NAME = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setString(1, updateItemName);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	public static void updateItemCodeDAO(String itemCode, String updateItemCode) {
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET ITEM_CODE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setString(1, updateItemCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	public static void updateBeanCodeDAO(String itemCode, String updateBeanCode) {
		
		try {
			String sql = "";
			sql += "UPDATE ITEMS ";
			sql += "	SET BEAN_CODE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setString(1, updateBeanCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("상품 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	public static void deleteItemDAO(String itemCode) {
		
		
		try {
			String sql = "";
			sql += "DELETE ITEMS ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, itemCode);
			
			resultDelete += pstmt.executeUpdate();
			System.out.println("상품 삭제 총 " + resultDelete + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} finally {
//			CommonJdbcUtil.close(conn, pstmt);
//		}
		}
	}
	
	
			
		public static List<ItemsVO> selectAll(String itemCode) {
			List<ItemsVO> list = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT ITEM_CODE, BEAN_CODE, ITEM_NAME, INTRODUCTION, PRICE ");
				sql.append("FROM ITEMS ");
				sql.append("WHERE ITEM_CODE = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, itemCode);
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<ItemsVO>();
				while (rs.next()) {
					ItemsVO vo = new ItemsVO(
							rs.getString("ITEM_CODE"), 
							rs.getString("BEAN_CODE"), 
							rs.getString("ITEM_NAME"), 
							rs.getString("INTRODUCTION"), 
							rs.getInt("PRICE"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public static String selectCode(String itemCode) {
			
			try {
				String sql = "";
				sql += "SELECT ITEM_CODE ";
				sql += "FROM ITEMS ";
				sql += "WHERE ITEM_CODE = ? ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, itemCode);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String str = rs.getString("ITEM_CODE");
					return str;					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		
		}
		public static String selectNickname(String managerCode) {
			
			try {
				String sql = "";
				sql += "SELECT NICK_NAME ";
				sql += "FROM MANAGERS ";
				sql += "WHERE MANAGER_CODE = ? ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, managerCode);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String str = rs.getString("MANAGER_CODE");
					return str;					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public static String selectBeanCode(String beanCode) {
			
			try {
				String sql = "";
				sql += "SELECT BEAN_CODE ";
				sql += "FROM BEANS ";
				sql += "WHERE BEAN_CODE = ? ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, beanCode);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String str = rs.getString("BEAN_CODE");
					return str;					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		public static List<ItemsVO> selectAllItem() {
			List<ItemsVO> list = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT ITEM_CODE, BEAN_CODE, ITEM_NAME, INTRODUCTION, PRICE ");
				sql.append("FROM ITEMS ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<ItemsVO>();
				while (rs.next()) {
					ItemsVO vo = new ItemsVO(
							rs.getString("ITEM_CODE"), 
							rs.getString("BEAN_CODE"), 
							rs.getString("ITEM_NAME"), 
							rs.getString("INTRODUCTION"), 
							rs.getInt("PRICE"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	
		public static List<ItemsVO> selectBeans() {
			List<ItemsVO> list = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT BEAN_CODE, BEAN_NAME, COO, DESCRIPTION ");
				sql.append("FROM BEANS ");
				
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				
				list = new ArrayList<ItemsVO>();
				while (rs.next()) {
					ItemsVO vo = new ItemsVO(
							rs.getString("BEAN_CODE"), 
							rs.getString("BEAN_NAME"), 
							rs.getString("COO"), 
							rs.getString("DESCRIPTION"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		public static List<ItemsVO> selectItemsLog() {
			List<ItemsVO> list = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT MANAGER_CODE, ITEM_CODE, BEAN_CODE, ITEM_NAME, INTRODUCTION, PRICE, MODIFY_TYPE,TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') ");
				sql.append("FROM ITEMS_LOG ");
				
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				
				list = new ArrayList<ItemsVO>();
				while (rs.next()) {
					ItemsVO vo = new ItemsVO(
							rs.getString("MANAGER_CODE"), 
							rs.getString("ITEM_CODE"), 
							rs.getString("BEAN_CODE"), 
							rs.getString("ITEM_NAME"), 
							rs.getString("INTRODUCTION"), 
							rs.getInt("PRICE"),
							rs.getString("MODIFY_TYPE"), 
							rs.getString("TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS')"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public static List<ItemsVO> selectDateItemsLog(int modifyDateStart, int modifyDateEnd) {
			List<ItemsVO> list = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("MANAGER_CODE, ITEM_CODE, BEAN_CODE, ITEM_NAME, INTRODUCTION, PRICE, MODIFY_TYPE,TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS') ");
				sql.append("FROM ITEMS_LOG ");
				sql.append("WHERE MODIFYDATE > TO_DATE(?, 'YYYYMMDD') ");
				sql.append("AND MODIFYDATE <= TO_DATE(?||' 235959', 'YYYYMMDD HH24MISS') ");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, modifyDateStart);
				pstmt.setInt(2, modifyDateEnd);
				rs = pstmt.executeQuery();
				
				list = new ArrayList<ItemsVO>();
				while (rs.next()) {
					ItemsVO vo = new ItemsVO(
							rs.getString("MANAGER_CODE"), 
							rs.getString("ITEM_CODE"), 
							rs.getString("BEAN_CODE"), 
							rs.getString("ITEM_NAME"), 
							rs.getString("INTRODUCTION"), 
							rs.getInt("PRICE"),
							rs.getString("MODIFY_TYPE"), 
							rs.getString("TO_CHAR(MODIFY_DATE, 'YYYY/MM/DD HH24:MI:SS')"));
					list.add(vo);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	

}
