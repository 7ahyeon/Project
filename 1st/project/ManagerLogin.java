package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerLogin extends ManagerDAO {
	
	private static Connection conn = CommonJdbcUtil.getConnection();
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	static Scanner sc = new Scanner(System.in);
	
	private static String managerId;
	private static String managerPw;
	private static String nickName;
	
	private static void managerIdScan() {
		while(true) {
			System.out.println("아이디 : ");
			managerId = sc.nextLine();
			
			if (managerId.getBytes().length > 40 || managerId.getBytes().length <= 0) {
				System.err.println("[오류] 아이디 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void managerPwScan() {
		while(true) {
			System.out.println("비밀번호 : ");
			managerPw = sc.nextLine();
			
			if (managerPw.getBytes().length > 40 || managerPw.getBytes().length <= 0) {
				System.err.println("[오류] 비밀번호는 1자 이상 40자 미만으로 입력해주십시오.");
				continue;
			}
			break;
		}
	}

	
	public ManagerLogin() {
		
		while(true) {
			System.err.println(">> 관리자 계정으로 로그인을 시도합니다.");
			managerIdScan();
			managerPwScan();
			
			nickName = managerLoginDAO(managerId, managerPw);
			
			if(nickName != null) {
				System.out.println("[성공] " + nickName + "님, 환영합니다!");
				MainPage main = new MainPage();
				main.managerMain();
				break;
			} else {
				System.err.println("[실패] 아이디와 비밀번호를 확인해주세요");
				continue;
			}
		}
	}

}
