package nutrition;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.CommonJdbcUtil;



public class NutritionDAO {
	
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	static Scanner cafe = new Scanner(System.in);
	private static int choice;
	private static String itemCode;
	private static int kcal;
	private static int sodium;
	private static int cholesterol;
	private static int sugar;
	private static int protein;
	private static int caffeine;
	
	private static int resultInsert;
	private static int resultUpdate;
	
	static {
		try {
			Class.forName(CommonJdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//영양정보 입력
	public static void insertNutritionDAO(String itemCode, int kcal, int sodium, int cholesterol, int sugar, int protein, int caffeine) {	
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "INSERT INTO NUTRITION ";
			sql += "	(ITEM_CODE, KCAL, SODIUM, CHOLESTEROL, SUGAR, PROTEIN, CAFFEINE)";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, itemCode);
			pstmt.setInt(2, kcal);
			pstmt.setInt(3, sodium);
			pstmt.setInt(4, cholesterol);
			pstmt.setInt(5, sugar);
			pstmt.setInt(6, protein);
			pstmt.setInt(7, caffeine);		
			
			resultInsert += pstmt.executeUpdate();
			System.out.println("영양 정보 등록 총 " + resultInsert + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
		}
	
	//영양정보 수정
	public static void updateNutritionDAO(String itemCode, int updateKcal, int updateSodium, int updateCholesterol, int updateSugar, int updateProtein, int updateCaffeine) {
			
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET KCAL = ? ";
			sql += "		, SODIUM = ? ";
			sql += "		, CHOLESTEROL  = ? ";
			sql += "		, SUGAR = ? ";
			sql += "		, PROTEIN = ? ";
			sql += "		, CAFFEINE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(7, itemCode);
			pstmt.setInt(1, updateKcal);
			pstmt.setInt(2, updateSodium);
			pstmt.setInt(3, updateCholesterol);
			pstmt.setInt(4, updateSugar);
			pstmt.setInt(5, updateProtein);
			pstmt.setInt(6, updateCaffeine);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//단백질값 수정
	public static void updateNutritionProteinDAO(String itemCode, int updateProtein) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET PROTEIN = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateProtein);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//당값 수정
	public static void updateNutritionSugarDAO(String itemCode, int updateSugar) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET SUGAR = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateSugar);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//포화지방값 수정
	public static void updateNutritionCholesterolDAO(String itemCode, int updateCholesterol) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET CHOLESTEROL = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateCholesterol);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//나트륨값 수정
	public static void updateNutritionSodiumDAO(String itemCode, int updateSodium) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET SODIUM = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateSodium);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//칼로리값 수정
	public static void updateNutritionKcalDAO(String itemCode, int updateKcal) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET KCAL = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateKcal);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}

	public static void updateNutritionCaffeineDAO(String itemCode, int updateCaffeine) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET CAFFEINE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setInt(1, updateCaffeine);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}
	
	//상품코드값 수정	
	public static void updateNutritionItemCodeDAO(String itemCode, String updateItemCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "UPDATE NUTRITION ";
			sql += "	SET ITEM_CODE = ? ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, itemCode);
			pstmt.setString(1, updateItemCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 수정 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
			}
		}
	
	//영양정보 삭제
	public static void deleteNutritionDAO(String itemCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
			
			String sql = "";
			sql += "DELETE NUTRITION ";
			sql += "WHERE ITEM_CODE = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, itemCode);
			
			resultUpdate += pstmt.executeUpdate();
			System.out.println("영양 정보 삭제 총 " + resultUpdate + "건을 완료했습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJdbcUtil.close(conn, pstmt);
		}
	}

	//특정 상품 영양정보 전체 조회
	public static List<NutritionVO> selectNutritionAll(String itemCode) {
		List<NutritionVO> list = null;
		try {
			conn = CommonJdbcUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ITEM_CODE, KCAL, SODIUM, CHOLESTEROL, SUGAR, PROTEIN, CAFFEINE ");
			sql.append("FROM NUTRITION ");
			sql.append("WHERE ITEM_CODE = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, itemCode);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NutritionVO>();
			while (rs.next()) {
				NutritionVO vo = new NutritionVO(
						rs.getString("ITEM_CODE"), 
						rs.getInt("KCAL"), 
						rs.getInt("SODIUM"), 
						rs.getInt("CHOLESTEROL"), 
						rs.getInt("SUGAR"), 
						rs.getInt("PROTEIN"),
						rs.getInt("CAFFEINE"));		
				list.add(vo);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//전체 상품 영양정보 전체 조회
	public static List<NutritionVO> selectNutritionAll() {
		List<NutritionVO> list = null;
		try {
			conn = CommonJdbcUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ITEM_CODE, KCAL, SODIUM, CHOLESTEROL, SUGAR, PROTEIN, CAFFEINE ");
			sql.append("FROM NUTRITION ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NutritionVO>();
			while (rs.next()) {
				NutritionVO vo = new NutritionVO(
						rs.getString("ITEM_CODE"), 
						rs.getInt("KCAL"), 
						rs.getInt("SODIUM"), 
						rs.getInt("CHOLESTEROL"), 
						rs.getInt("SUGAR"), 
						rs.getInt("PROTEIN"),
						rs.getInt("CAFFEINE"));	
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String selectNutritionCode(String itemCode) {
		
		try {
			conn = CommonJdbcUtil.getConnection();
	
			String sql = "";
			sql += "SELECT ITEM_CODE ";
			sql += "FROM NUTRITION ";
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
	
}
