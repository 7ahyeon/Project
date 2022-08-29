package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerDAO {

	private static Connection conn = CommonJdbcUtil.getConnection();
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	static Scanner sc = new Scanner(System.in);
	
	static {
		try {
			Class.forName(CommonJdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외] ItemsDAO JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
public static String managerLoginDAO(String managerId, String managerPw) {
		try {
			String sql = "";
			sql += "SELECT NICK_NAME ";
			sql += "FROM MANAGERS ";
			sql += "WHERE MANAGER_ID = ? ";
			sql += "AND PASSWORD = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, managerId);
			pstmt.setString(2, managerPw);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String str = rs.getString("NICK_NAME");
				return str;					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		} finally {
//			CommonJdbcUtil.close(conn, pstmt);
//			}
		return null;
		}

}
