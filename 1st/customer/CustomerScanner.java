package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.CardDAO;
import card.CardScanner;
import card.CardVO;
import item.ItemsScanner;
import order.OrderDAO;
import order.OrderScanner;
import order.OrderVO;
import order.TestOrderVO;
import project.MainPage;

public class CustomerScanner extends CustomerDAO{

	static Scanner sc = new Scanner(System.in);
	final static String REGEX = "[0-9]+";
	
	private static String choiceSC;
	private static int choice = 10;
	
	private static String exitSC;
	private static int exit = 2;
	
	private static MainPage main = new MainPage();
	static CustomerVO customer;
	static List <CardVO> cardList;
	static CardScanner card;
	
	private static int select;

	private static String custId;
	private static String password;
	private static String eMail;
	private static String custName;
	
	private static String phoneNumberSC;
	private static String phoneNumber;
	
	private static String rrnSC;
	private static String rrn;
	

	public static  void signUp() {
		while(true) {
			
			System.out.println();
			System.out.println("[회원 가입]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			custNameScan();
			rrnScanOverlap();
			custIdScanOverlap();
			passwordScan();
			phoneNumberScan();
			eMailScan();
			
			insertCustomerDAO(custId, password, eMail, phoneNumber, custName, rrn);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}

	public void tryLogin() {
		System.out.println();
		System.out.println("[로그인]");
		
		System.out.println(">> 로그인을 시도합니다. 아이디와 비밀번호를 입력해주세요.");
		
		custIdScan();
		passwordScan();
		
		if (tryLoginDAO(custId, password)) {
			
			System.out.println("[성공] " + custId + "님, 환영합니다!");
			main.logged(CustomerDAO.getCustomerByCustId(custId).getCustCode());
		} else {
			System.out.println();
			System.err.println("[실패] 아이디와 비밀번호를 확인해주세요.");
			System.out.println();
			logInMenu();
		}
	}
	
	public void findPassword() {
		while(true) {
			System.out.println();
			System.out.println("[비밀번호 찾기]");
			System.out.println(">> 비밀번호를 찾고자하는 아이디를 입력해주세요");
			
			custIdScan();
			
			if (selectCustIdDAO(custId) == null) {
				System.err.print(">> 입력하신 아이디를 찾을 수 없습니다.");
				System.out.println("[1] 재시도" + "\n" 
						+ "[2] 아이디 찾기" + "\n" 
						+ "[0] 뒤로 가기");
				
				System.out.println();
				System.out.print("입력 > ");
				select = sc.nextInt();
				System.out.println();
				
				if(select == 1) {
					continue;
				} if (select == 2) {
					findId();
					break;
				} if (select == 0) {
					logInMenu();
					break;
				}
			}
			
			System.out.print(">> 비밀번호를 찾을 방법을 선택해주세요" + "\n" 
					+ "[1] 휴대폰번호로 찾기" + "\n" 
					+ "[2] 이메일로 찾기" + "\n"
					+ "[0] 뒤로 가기");
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			System.out.println(">> 회원가입시 기입한 정보를 입력해주세요");
			
			rrnScan();
			
			if (select == 0) {
				logInMenu();
				break;
			} if (select == 1) {
				phoneNumberScan();
				password = getPwByPN(custId, rrn, phoneNumber);
			} if (select == 2) {
				eMailScan();
				password = getPwByEMail(custId, rrn, eMail);
			}
			
			System.out.println(select);
			
			if (password == null) {
				System.err.println(">> 입력하신 정보와 일치하는 회원이 없습니다");
				continue;
			} else {
				System.out.println("회원님의 비밀번호는 " + password + "입니다");
				break;
			}
		}
	}
	
	public void findId () {
		while(true) {
			System.out.println();
			System.out.println("[아이디 찾기]");
			
			System.out.print(">> 아이디를 찾을 방법을 선택해주세요" + "\n" 
					+ "[1] 휴대폰번호로 찾기" + "\n" 
					+ "[2] 이메일로 찾기" + "\n"
					+ "[0] 뒤로 가기");
			
			choiceSelect(2);
			
			System.out.println(">> 회원가입시 기입한 정보를 입력해주세요");
			
			rrnScan();
			
			if (choice == 0) {
				break;
			} if (choice == 1) {
				phoneNumberScan();
				custId = getCustIdByPN(rrn, phoneNumber);
			} if (choice == 2) {
				eMailScan();
				custId = getCustIdByEMail(rrn, eMail);
			}
			
			if (custId == null) {
				System.err.println(">> 입력하신 정보와 일치하는 회원이 없습니다");
				continue;
			} else {
				System.out.println("회원님의 아이디는 " + custId + "입니다"); 
				break;
			}
		}
	}
	
	public void logInMenu() {
		while(true) {
			System.out.println("[로그인 / 회원가입]");
			
			System.out.println(">> 요청하실 메뉴를 입력해주세요" + "\n"
					+ "[1] 로그인 " + "\n" 
					+ "[2] 회원가입" + "\n" 
					+ "[3] 아이디 찾기" + "\n" 
					+ "[4] 비밀번호 찾기" + "\n" 
					+ "[0] 뒤로 가기");
			
			choiceSelect(4);
			
			if (choice == 0) {
				break;
			} if (choice == 1) {
				tryLogin();
				System.out.println();
				System.out.println(">> [로그인] 종료");
				System.out.println();
			} if (choice == 2) {
				signUp();
				System.out.println();
				System.out.println(">> [회원가입] 종료");
				System.out.println();
			} if (choice == 3) {
				findId();
				System.out.println();
				System.out.println(">> [아이디 찾기] 종료");
				System.out.println();
			} if (choice == 4) {
				findPassword();
				System.out.println();
				System.out.println(">> [비밀번호 찾기] 종료");
				System.out.println();
			}
		}
	}
	
	private static void custNameScan() {
		while(true) {
			System.out.print("성함 : ");
			custName = sc.next();
			
			if (custName.getBytes().length > 30 || custName.getBytes().length <= 0) {
				System.err.println("[오류] 성함 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	private static void rrnScan() {
		while(true) {
			System.out.print("주민등록번호 : ");
			rrnSC = sc.next();
			
			if (rrnSC.getBytes().length != 13) {
				System.err.println("[오류] 주민등록번호 입력 범위를 벗어났습니다.");
				continue;
			} else {
				switch (rrnSC.substring(6, 7)) {
				case "1" : case "2" : case "3" : case "4" :
					break;
				default : 
					System.err.println("[오류] 주민등록번호 7번째 숫자가 잘못 입력되었습니다.");			
					break;
				}
				rrn = rrnSC.substring(0, 6) + "-" + rrnSC.substring(6);
			}
			break;
		}
	}
	private static void rrnScanOverlap() {
		while(true) {
			System.out.print("주민등록번호 : ");
			rrnSC = sc.next();
			
			if (rrnSC.getBytes().length != 13) {
				System.err.println("[오류] 주민등록번호 입력 범위를 벗어났습니다.");
				continue;
			} else {
				switch (rrnSC.substring(6, 7)) {
				case "1" : case "2" : case "3" : case "4" :
					break;
				default : 
					System.err.println("[오류] 주민등록번호 7번째 숫자가 잘못 입력되었습니다.");			
					break;
				}
				if (selectRRNDAO(rrn) != null) {
					System.err.println("[오류] 이미 가입된 회원입니다.");
					continue;
				}
				rrn = rrnSC.substring(0, 6) + "-" + rrnSC.substring(6);
			}
			break;
		}
	}
	
	private static void custIdScan() {
		while(true) {
			System.out.print("아이디 : ");
			custId = sc.next();
			System.out.println();
			
			if (custId.getBytes().length > 40 || custId.getBytes().length <= 0) {
				System.err.println("[오류] 아이디 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	private static void custIdScanOverlap() {
		while(true) {
			System.out.println("아이디 : ");
			custId = sc.next();
			
			if (selectCustIdDAO(custId) != null) {
				System.err.println("[오류] 이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
				continue;
			}
			if (custId.getBytes().length > 40 || custId.getBytes().length <= 0) {
				System.err.println("[오류] 아이디 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void passwordScan() {
		while(true) {
			System.out.print("비밀번호 : ");
			password = sc.next();
			System.out.println();
			
			if (password.getBytes().length > 40 || password.getBytes().length <= 0) {
				System.err.println("[오류] 비밀번호는 1자 이상 40자 미만으로 입력해주십시오.");
				continue;
			}
			break;
		}
	}
	
	private static void phoneNumberScan() {
		while(true) {
			System.out.print("폰 번호 [(-) 제외하고 입력] : ");
			phoneNumberSC = sc.next();
			
			if (phoneNumberSC.getBytes().length != 11) {
				System.err.println("[오류] 폰 번호 입력 범위를 벗어났습니다.");
				continue;
			} else {
				if (phoneNumberSC.substring(0, 3).equals("010")) {
				} else {
					System.err.println("[오류] 010으로 시작하는 번호를 입력해주십시오.");			
					continue;
				}
				phoneNumber = phoneNumberSC.substring(0, 3) + "-" + phoneNumberSC.substring(3,7) + "-" + phoneNumberSC.substring(7);
			}
			break;
		}
	}
	
	private static void eMailScan() {
		while(true) {
			System.out.println("이메일 : ");
			eMail = sc.next();
			if (eMail.getBytes().length > 30 || eMail.getBytes().length <= 0) {
				System.err.println("[오류] 이메일은 30자 미만으로 입력해주십시오.");
				continue;
			} else {
				if (eMail.contains("@")) {
					break;
				} else {
					System.err.println("[오류] 올바른 이메일 형식을 입력해주십시오.");
					continue;
				}
			}
		}
	}
	public static void choiceSelect(int i) {
		while(true) {
			System.out.println();
			System.out.print("입력 > ");
			choiceSC = sc.next();
			
			if (choiceSC.matches(REGEX)) {
				if (choiceSC.getBytes().length == 1) {
					choice =  Integer.parseInt(choiceSC);
					if ( choice > i || choice < 0) {
						System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
						continue;
					} else {
						break;
					}
				} else {
					System.err.println("[오류] 한자리 숫자를 입력해주십시오.");
					continue;
				}
			} else {
				System.err.println("[오류] 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	
	public static void exitSelect() {
		while(true) {
			System.out.println(""
					+ ">> 원하시는 메뉴를 선택해주세요" + "\n"
					+ "[1] 진행" + "\n" 
					+ "[0] 종료");
			System.out.println();
			System.out.print("입력 > ");
			exitSC = sc.next();
			System.out.println();
			
			if (exitSC.matches(REGEX)) {
				if (exitSC.getBytes().length == 1) {
					exit =  Integer.parseInt(exitSC);
					if ( exit > 1 || exit < 0) {
						System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
						continue;
					} else {
						break;
					}
				} else {
					System.err.println("[오류] 한자리 숫자를 입력해주십시오.");
					continue;
				}
			} else {
				System.err.println("[오류] 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	public static void exitSelectAgain() {
		while(true) {
			System.out.println(""
					+ ">> 원하시는 메뉴를 선택해주세요" + "\n"
					+ "[1] 재진행" + "\n" 
					+ "[0] 종료");
			System.out.print("입력 > ");
			exitSC = sc.next();
			
			if (exitSC.matches(REGEX)) {
				if (exitSC.getBytes().length == 1) {
					exit =  Integer.parseInt(exitSC);
					if ( exit > 1 || exit < 0) {
						System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
						continue;
					} else {
						break;
					}
				} else {
					System.err.println("[오류] 한자리 숫자를 입력해주십시오.");
					continue;
				}
			} else {
				System.err.println("[오류] 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	public static void checkMyHistory (String custCode) {
		while(true) {
			ArrayList<TestOrderVO> orderList = new ArrayList<>();
			ArrayList<OrderVO> optionList = new ArrayList<>();
			System.out.print(">> 고객님의 주문내역을 확인합니다" + "\n"
					+ "[1] 전체 주문내역 조회"  + "\n" 
					+ "[2] 기간별 주문내역 조회"  + "\n"
					+ "[0] 뒤로 가기");
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				System.out.print("[전체 주문내역 조회]");
				System.out.println();
				orderList = OrderDAO.getOrdersAll(custCode);
				
			} if (select == 2) {
				System.out.println("[기간별 주문내역 조회]");
				dispDateOrderLog(custCode);
			} if (select == 0) {
				break;
			}
			System.out.println(">> 조회결과입니다");
			
			OrderScanner os = new OrderScanner();
			
			
			for (TestOrderVO vo : orderList) {
				
				String orderDate = vo.getOrderDate();
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("주문 날짜 " + orderDate.substring(0, 4) + "년 " + orderDate.substring(5, 7) + "월 " + orderDate.substring(8, 10) + "일 " 
									+ orderDate.substring(11, 13) + ":" + orderDate.substring(14, 16) + ":" + orderDate.substring(17, 19));
				System.out.println("총 결제금액 : " + vo.gettotalPrice());
				
				optionList = OrderDAO.getOptionsByOrderCode(vo.getOrderCode());
				
				int num = 1;
				for (OrderVO order : optionList) {
					
					System.out.println();
					System.out.println("옵션 " + num);
					os.showEssenialOptions(order);
					os.showDetailOptions(order);
				}
			}
//			ItemsScanner.exitSelectAgain();
//			if (exit == 0) {
//				break;
//			}
			
			}
		}
	
	
	private static void dispDateOrderLog(String custcode) {
		while(true) {
		
			System.out.println("[기간별 조회]");
				
			ItemsScanner.exitSelect();
			if (exit == 0) {
				break;
			}
			
			
			System.out.println(">> 조회할 기간을 입력해주세요");
				
			ItemsScanner.modifyDateStartScan();
			ItemsScanner.modifyDateEndScan();
			
			System.out.println();
			
//			selectDateItemsLogDisp(modifyDateStart, modifyDateEnd);
			
//			ItemsScanner.exitSelectAgain();
//			if (exit == 0) {
//				break;
//			}
			
		}
	}
	
	
	private static void showHistory(ArrayList<OrderVO> orderList) {
		
		
	}


//	private static ArrayList<OrderVO> checkMyHistoryAll(String custCode) {
//		
//		ArrayList<OrderVO> orderList;
//		
//		OrderDAO.getOrdersAll(custCode);
//		
//	}


	private static void checkMyHistoryByPeriod(String custCode) {
		
	}


	public static void custInfo(String custCode) {
		
		customer = getCustomerByCustCode(custCode);
		
		System.out.printf("%s %n%s %n%s %n%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n%s | %s %n%s %n%8s %n%s %n%s %n%s"
						,"---------------------------------------------------------------"
						, "고객님의 정보를 조회한 결과입니다"
						,"---------------------------------------------------------------"
						, "아이디", customer.getId()
						, "회원등급", customer.getLevel()
						, "이름", customer.getName()
						, "이메일", customer.getE_mail()
						, "주민등록번호", customer.rrn()
						, "전화번호", customer.getPhone()
						, "보유 스탬프 (스탬프 10개 적립시 아메리카노 한잔 무료) ", customer.getStamp()
						, "-------------"
						, "등록된 결제수단" 
						, "-------------"
						);
		System.out.println();
		showCardList(custCode);
		System.out.println();
		System.out.println(
						"---------------------------------------------------------------"
				);
		
		System.out.println();
		System.out.print(">> 회원정보(이메일, 전화번호)를 수정하시겠습니까?" + "\n"
							+ "[1] 회원정보(이메일, 휴대폰번호) 수정하기" + "\n" 
							+ "[2] 결제카드 추가/삭제" + "\n" 
							+ "[3] 계정 삭제 " + "\n" 
							+ "[0] 뒤로 가기" + "\n"
							+ "입력 > ");
		
		select = sc.nextInt();
		System.out.println();
		
		if (select == 1) {
			custInfoUpdate(customer);
		} if (select == 2)	{
			card.methodOfPayment(custCode); // 민석씨한테 받아서 추가
		} if (select == 3)	{
			deleteAccount(customer);
		} if (select == 0) {
			return;
		}
	}
	
	
	private static void showCardList(String custCode) {
		
		cardList = new ArrayList<>();
		cardList = CardDAO.selectCardDAO(custCode);
		
		for (CardVO vo : cardList) {
			System.out.printf("%8s | %s %n"
					, vo.getBankName(), vo.getCardNum().substring(12, 16));
		}
	}

	public static void custInfoUpdate(CustomerVO customer) { // 이메일, 전화번호, 비밀번호 수정가능
		
		
		System.out.print(">> 회원정보를 수정하기 위해서는 비밀번호 인증이 필요합니다" + "\n"
						+ "[1] 비밀번호 인증 진행" + "\n" 
						+ "[0] 뒤로 가기");
		
		System.out.println();
		System.out.print("입력 > ");
		select = sc.nextInt();
		System.out.println();
		
		if (select == 0) {
			return;
		}
		
		while (true) {
			System.out.println();
			System.out.print(">> 비밀번호를 입력해주세요" + "\n" 
			+ "입력 > ");
			
			passwordScan();
			
			if (!password.equals(customer.getPassword())) {
				
				System.err.println("비밀번호가 틀렸습니다");
				System.out.println();
				
				continue;
				
				
			} else {
				break;
			}
		}
			
		while (true) {
			
			System.out.print(">> 수정하고자 하는 정보를 선택해주세요" + "\n" 
							+ "[1] 이메일" + "\n" 
							+ "[2] 전화번호" + "\n"
							+ "[0] 뒤로 가기");
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				updateEmail(customer.getCustCode());
			} if (select == 2) {
				updatePhoneNumber(customer.getCustCode());
			} if (select == 0) {
				custInfo(customer.getCustCode());
				break;
			}
			
			System.out.print(">> 수정을 완료하였습니다. 다음 요청을 입력해주세요" + "\n"
							+ "[1] 다른정보 수정하기" + "\n" 
							+ "[0] 메인메뉴로 돌아가기");
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				continue;
			} if (select == 0) {
				custInfo(customer.getCustCode());
				break;
			} 
		}
	}
	
	
	private static void updatePhoneNumber(String custCode) {
		
		String before = customer.getPhone();
		String after = null;
		
		System.out.print(">> 변경하실 전화번호를 입력해주세요 [(-) 제외하고 입력]" + "\n");
		
		phoneNumberScan();
		
		updatePhone(custCode, phoneNumber);
		
		after = getCustomerByCustCode(custCode).getPhone();
		
		System.out.println("변경 전 > " + before);
		System.out.println("변경 후 > " + after);
		
	}


	private static void updateEmail(String custCode) {
		
		String before = customer.getE_mail();
		String after = null;
		
		System.out.print(">> 변경하실 이메일을 입력해주세요" + "\n" 
							+ "입력 > ");
		
		String email = sc.next();
		System.out.println();
		
		CustomerDAO.updateEmail(custCode, email);
		
		after = CustomerDAO.getCustomerByCustCode(custCode).getE_mail();
		
		System.out.println("변경 전 > " + before);
		System.out.println("변경 후 > " + after);
		
	}
	
	
	public static void deleteAccount(CustomerVO customer) {
		
		
		System.out.print(">> 계정삭제 확인을 위해 비밀번호를 입력해주세요" + "\n");
		
		passwordScan();
		
		if (!password.equals(customer.getPassword())) {
			
			System.err.println("[오류] 비밀번호가 틀립니다. 다시 입력해주세요");
			System.out.print("[1] 비밀번호 다시 입력" + "\n"
							+ "[0] 메인메뉴로 나가기");
			
			System.out.println();
			System.out.print("입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				deleteAccount(customer);
				return;
				
			} else return;
		}
		
		System.err.print(">> 계정을 삭제하면 현재 가지고 계신 스탬프를 모두 잃게 됩니다. 정말 삭제하시겠습니까?" + "\n"
							+ "현재 고객님의 스탬프 : " + customer.getStamp() + "개" + "\n"
							+ "[1] 계정삭제" + "\n" 
							+ "[0] 삭제 취소하고 메인메뉴로 나가기");
		
		System.out.println();
		System.out.print("입력 > ");
		select = sc.nextInt();
		System.out.println();
		
		if (select == 1) {
			
			CustomerDAO.deleteCustomer(customer.getCustCode());
			
			System.err.println(">> 계정이 삭제되었습니다.");
			
			MainPage main = new MainPage();
//			customer = null;
//			main.logged(null);
			main.notLogged();
			return;
		} else return;
		
	}
	
	
	
}
