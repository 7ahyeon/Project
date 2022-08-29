package card;

import java.util.List;
import java.util.Scanner;

public class CardScanner extends CardDAO {

	static Scanner sc = new Scanner(System.in);
	private static int choice = 10;
	private static int exit = 2;
	
	private static String cardCode;
	private static String custCode;
	private static String bankName;
	private static String cardNum;
	private static String cardPw;
	
//	private static String bank1 = "국민은행";
//	private static String bank2 = "기업은행";
//	private static String bank3 = "신한은행";
//	private static String bank4 = "농협은행";

	
	public static void methodOfPayment(String cust_code) {
		
		custCode = cust_code;
		
		while(true) {
			System.out.println("---------------------------------------------------------------");
			System.out.println(">> 원하시는 작업을 선택해주세요" + "\n"
					+ "[1] 카드정보 등록" + "\n"
					+ "[2] 카드정보 삭제" + "\n"
					+ "[0] 뒤로 가기"); 
			System.out.println();
			System.out.print("입력 >> "); 
			try {
				choice = Integer.parseInt(sc.next()); 
				System.out.println();
				break;
			} catch(Exception e) {
				System.err.println("[오류] 숫자를 입력해주십시오.");
				System.out.println();
				sc = new Scanner(System.in);
			}
		}		
	
		while(true) {
			if (choice < 0 || choice > 2) {
				System.err.println("[오류] 주어진 선택지를 입력해주십시오."); 
				System.out.println();
				methodOfPayment(custCode);
			} else break;
		}
		
		if(choice == 0) {
			System.out.println(">> [카드정보 등록/삭제] 종료"); 
			System.out.println("---------------------------------------------------------------");
			System.out.println();
		} else if (choice == 1) {
			while(true) {
				insertCard();		
				if (exit == 0) {
					System.out.println();
					System.out.println(">> [카드정보 등록] 종료");
					System.out.println();
					break;
				}
			 }
		} else if (choice == 2) {
				while(true) {
					deleteCard();
					if (exit == 0) {
						System.out.println();
						System.out.println(">> [카드정보 삭제] 종료");
						System.out.println();
						break;
					}
				}
		}
} //end manager


//------------------------------------------------------------------------------------------------

	//카드정보 등록
	private static void insertCard() {
		while(true) {
			System.out.println();
			System.out.println("[카드 정보 등록]");
			
			while(true) {
				System.out.println();
				System.out.println(""
						+ ">> 원하시는 작업을 선택해주세요" + "\n"
						+ "[1] 진행" + "\n" 
						+ "[0] 종료");
				System.out.print("입력 > ");
				try {
					exit = Integer.parseInt(sc.next());
					System.out.println();
					break;
				} catch(Exception e) {
					System.err.println("[오류] 숫자를 입력해주십시오.");
					System.out.println();
					sc = new Scanner(System.in);
				}
			}
			System.out.println();
			if (exit == 0) {
				break;
			}
			while(true) {
				if (exit < 0 || exit > 1) {
					System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
					System.out.println();
					insertCard();
				} else break;
			}
			
			System.out.println(">> 등록할 카드 정보를 차례대로 입력해주세요");
			
			insertBankName();
			insertCardNum();
			insertCardPw();

			insertCardDAO(cardCode, custCode, bankName, cardNum, cardPw);
			System.out.println("[등록된 카드 정보]");
			selectCardAllDisp(custCode);
		
			while(true) {
				System.out.println();
				System.out.println(""
						+ ">> 원하시는 작업을 선택해주세요" + "\n"
						+ "[1] 카드정보 추가 등록 " + "\n" 
						+ "[0] 종료");
				System.out.print("입력 > ");
				try {
					exit = Integer.parseInt(sc.next());
					System.out.println();
					break;
				} catch(Exception e) {
					System.err.println("[오류] 숫자를 입력해주십시오.");
					System.out.println();
					sc = new Scanner(System.in);
				}
			}
			System.out.println();
			if (exit == 0) {
				break;
			}
			while(true) {
				if (exit < 0 || exit > 1) {
					System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
					System.out.println();
					insertCard();
				} else break;
			}
		}

	}	
	
	//은행명 입력
	private static void insertBankName() {
		
		String bank1 = "국민은행";
		String bank2 = "기업은행";
		String bank3 = "신한은행";
		String bank4 = "농협은행";
		int selectBank = 0;
		
		while(true) {
			System.out.println();
			System.out.println(">> 사용하시는 은행을 선택해주세요. ");
			System.out.println(
						     "[1]" + bank1 + "\n"
					       + "[2]" + bank2 + "\n"
					       + "[3]" + bank3 + "\n"
					       + "[4]" + bank4 + "\n");
			System.out.print("입력 > ");
				selectBank = sc.nextInt();
				
				if (selectBank == 1) {
					bankName = bank1;
				} else if (selectBank == 2) {
					bankName = bank2;
				} else if (selectBank == 3) {
					bankName = bank3;
				} else if (selectBank == 4) {
					bankName = bank4;
				}
				
				System.out.println();
				break;
		}				
				
	}
	
	//카드번호 입력
	private static void insertCardNum() {
		while(true) {
			System.out.println(">> 카드번호 16자리를 입력해주세요.");
			System.out.print("입력 > ");
			cardNum = sc.next();
			System.out.println();		
			break;	
		}
		
		while (true) {
			if (cardNum.length() != 16) {
				System.out.println("[오류] 16자리가 아닙니다. 다시 입력해주세요.");
				insertCardNum();
			} else break;			
		}
	}
	
	//카드비밀번호 입력
	private static void insertCardPw() {
		while(true) {
			System.out.println(">>카드비밀번호 4자리를 입력해주세요. ");
			System.out.print("입력 > ");
			cardPw = sc.next();
			System.out.println();		
			break;
		}
		
		while (true) {
			if (cardPw.length() != 4) {
				System.out.println("[오류] 4자리가 아닙니다. 다시 입력해주세요.");
				insertCardPw();
			} else break;			
		}
	}

//------------------------------------------------------------------------------------------------	
	
	private static void deleteCard() {
		while(true) {
			System.out.println();
			System.out.print("[카드정보 삭제]");
			
			while(true) {
				System.out.println();
				System.out.println(""
						+ ">> 원하시는 작업을 선택해주세요" + "\n"
						+ "[1] 진행" + "\n" 
						+ "[0] 종료");
				System.out.print("입력 > ");
				try {
					exit = Integer.parseInt(sc.next());
					System.out.println();
					break;
				} catch(Exception e) {
					System.err.println("[오류] 숫자를 입력해주십시오.");
					System.out.println();
					sc = new Scanner(System.in);
				}
			}
			System.out.println();
			if (exit == 0) {
				break;
			}
			while(true) {
				if (exit < 0 || exit > 1) {
					System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
					System.out.println();
					deleteCard();
				} else break;
			}
			
				
			System.out.println("[카드 정보]");
		
			selectCardAllDisp(custCode);
			
//			while(true) {
//				System.out.println("정말 삭제하시겠습니까?\r\n"
//						+ "[1] 삭제" + "\n"
//						+ "[0] 취소");
//				System.out.print("입력 > ");
//				try {
//					exit = Integer.parseInt(sc.next());
//					System.out.println();
//					break;
//				} catch(Exception e) {
//					System.err.println("[오류] 숫자를 입력해주십시오.");
//					System.out.println();
//					sc = new Scanner(System.in);
//				}
//			} 
			
			System.out.println("삭제하실 카드 정보의 카드코드를 입력해주세요.");			
			System.out.print("입력 > ");
			cardCode = sc.next();
			
			deleteCard2DAO(cardCode, custCode);
			System.out.println("---------------------------------------------------------------");
			
			System.out.println();
			if (exit == 0) {
				break;
			}
			while(true) {
				if (exit < 0 || exit > 1) {
					System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
					System.out.println();
					deleteCard();
				} else break;
			}
		} 
	}	
		

//------------------------------------------------------------------------------------------------

	//전체 정보 조회
	private static void selectCardAllDisp(String custCode) {
		CardDAO dao = new CardDAO();
		List<CardVO> list = dao.selectCardAll(custCode);
		
		for (CardVO vo : list) {
			System.out.println(	
							  " 카드코드 : " + vo.getCardCode() + "\n " 
 						    + " 은행명 : " + vo.getBankName() + "\n " 
							+ " 카드번호 : " + vo.getCardNum() + "\n " 
							+ " 비밀번호 : " + vo.getCardPw()  
							
					);
			System.out.println("---------------------------------------------------------------");
			}

		}
	}		
