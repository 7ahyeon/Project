package project;

import java.util.Scanner;

import customer.CustomerScanner;
import customer.CustomerVO;
import item.ItemsScanner;
import nutrition.NutritionScanner;
import order.OrderDAO;
import order.OrderScanner;

public class MainPage {
	
	private static Scanner sc = new Scanner(System.in);
	private static CustomerScanner cs = new CustomerScanner();
	private static OrderScanner os = new OrderScanner();
	private static ItemsScanner is = new ItemsScanner();
	private static NutritionScanner ns = new NutritionScanner();

	private static String select;
	
	public static void notLogged() {
		
		while (true) {
			System.out.println("[비회원]");
			
			System.out.println(">> 원하시는 메뉴를 선택해주세요" + "\n"
								+ "[1] 로그인/회원가입" + "\n"
								+ "[2] 주문하기" + "\n"
								+ "[3] 전체메뉴보기" + "\n"
								+ "[0] 종료하기");
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextLine();
			System.out.println();
			
		
			switch (select) {
				case "1" :	
					
					cs.logInMenu();
					
					continue;
					
				case "2" :
					
					os.orderByCategory(null);
					
					continue;
					
				case "3" :
					
					os.showAllMenu();
					System.out.println();
					System.out.println(">> 메인메뉴로 돌아가시려면 아무거나 입력해주세요");
					System.out.print("입력 > ");
					sc.nextLine();
					continue;
					
				case "0" : 
					
					System.exit(0);
					
				case "manager" : 
					
					new ManagerLogin();
					
					continue;
					
				default :
					System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
					System.out.println();
					notLogged();
					continue;
				}
		}
	}
	
	public static void logged(String custCode) {
		
		sc = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("[회원]");
			
			System.out.println(">> 원하시는 메뉴를 선택해주세요" + "\n"
								+ "[1] 주문하기" + "\n"
								+ "[2] 전체메뉴보기" + "\n"
								+ "[3] 내정보" + "\n" // 여기서 회원정보 수정기능 넣기
								+ "[4] 즐겨찾기로 빠른 주문하기" + "\n"
								+ "[5] 내 결제내역 확인하기" + "\n"
								+ "[0] 로그아웃하기" + "\n"
								);
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextLine();
			System.out.println();
			
			switch (select) {
			case "1" :
				
				os.orderByCategory(custCode);
				
				continue;
	
			case "2" :
				
				os.showAllMenu();
				System.out.println();
				System.out.println(">> 메인메뉴로 돌아가시려면 아무거나 입력해주세요");
				System.out.print("입력 > ");
				sc.nextLine();
				continue;
				
			case "3" :
				
				cs = new CustomerScanner();
				cs.custInfo(custCode);
				//cm.custInfoInquire(customer.getCustCode());
				
				
				continue;
				
			case "4" :
				
				os.orderByFavorite(custCode, true);
				
				continue;
				
			case "5" :
				
				cs.checkMyHistory(custCode);
				
				continue;
				
			case "0" :
				
				System.out.println(">> 로그아웃되었습니다.");
				notLogged();
				break;
				
			default :
				System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
				System.out.println();
				notLogged();
				continue;
			}
			break;
		}
		
	}
	
	public static void managerMain() {
		while (true) {
			System.out.println();
			System.out.println("[관리자]");
			
			System.out.println(">> 원하시는 작업을 선택해주세요" + "\n"
								+ "[1] 상품정보 관리" + "\n"
								+ "[2] 영양정보 관리" + "\n"
//								+ "[3] 매출 및 통계확인" + "\n"
								+ "[0] 관리자 계정 로그아웃"); 
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextLine();
			System.out.println();
			
			if (select.equals("1")) {
				is.itemManager();
				continue;
			} if (select.equals("2")) {
				ns.nutritionManager();
				continue;
			} if (select.equals("0")) {
				System.out.println(">> 로그아웃되었습니다.");
				notLogged();
				break;
			} else {
				System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
				notLogged();
				break;
			}
		}
	}
}
