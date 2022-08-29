package order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.CardDAO;
import card.CardVO;
import customer.CustomerDAO;
import customer.CustomerScanner;
import customer.CustomerVO;
import project.MainPage;
// 수정함

public class OrderScanner {

	private final String[] options = {"", "얼음", "원두", "에스프레소", "우유", "시럽", "샷추가", "수량"};
	private ArrayList<OrderVO> orderList = new ArrayList<OrderVO>(); // 주문한 애들을 담음(커피1, 카파멜마키아토1)
	private ArrayList<String>[] arr;
	private static MainPage main = new MainPage();
	
	private final int priceDecaffein = 300;
	private final int priceLowFatMilk = 300;
	private final int priceFreeMilk = 500;
	private final int PriceSoyMilk = 500;
	private final int PriceSyrup = 500;
	private final int PriceAddShot = 500;
	
	private static int totalPrice = 0;
//	private String korean;
	
	private Scanner sc = new Scanner(System.in);
	
	private static int select;
	
	public OrderScanner () {
		
	}
	

	
	
	public void orderByCategory(String custCode) {
		
		OrderVO order;
		
				
		order = new OrderVO();
		
		order.setBean(null); // 공통항목이 아닌 옵션은 주문 첫페이지로 올때마다 null로 바꿔주기
		
		System.out.println(">> 음료 유형을 선택해주세요" + "\n"
				+ "[1] 커피(COFFEE)" + "\n"
				+ "[2] 차(TEA)" + "\n"
				+ "[3] 프라푸치노(PRAPPUCCINO)" + "\n"
				+ "[4] 에이드(AID)" + "\n" 
				+ "[5] 기타음료" + "\n"
				+ "[0] 메인메뉴로 나가기");
		System.out.print("입력 > ");
		
		if (selectCategory(order, custCode)) {
			return;
		}
		
		
		
		while (true) {
			
		System.out.println("[1] 음료선택 [2] 뒤로가기");
		System.out.print("입력 > ");
		
			select = sc.nextInt();
			System.out.println();
			
			if (select == 2) {
				orderByCategory(custCode);
				return;
			} else if (select == 1) {
				break;
			} else {
				System.err.println("** 옳바른 수를 입력해주세요");
			}
		}
		
		
		while (true) {
			
			System.out.println(">> 주문하실 음료 번호를 입력해주세요");
			
			System.out.print("입력 > ");
			String drink = sc.next();
			
			char c = String.valueOf(arr.length).charAt(0);
			
			if (drink.length() > 2) {
				System.err.println("** 옳은 선택지를 골라주세요");
				continue;
			}  else if (drink.charAt(0) < '1' || drink.charAt(0) > c) {
				System.err.println("** 옳은 선택지를 골라주세요");
				continue;
			}
			System.out.println();
			order.setChoice(arr[Integer.parseInt(drink)-1]); // 선택한 음료에 대한 ITEM 정보를 담은 리스트를 OrderVO 객체에 저장
			break;
		}
		
		
		
		
		while (true) {
			
			System.out.println("선택하신 음료 : " + order.getChoice().get(1) + "\n" // 필수 선택항목 선택
					+ ">> 필수 선택항목을 골라주세요" + "\n");
			
			selectEssentialOptions(order);
			showEssenialOptions(order);
			
			System.out.printf("%s%n%-20s%-20s%-20s%-20s%n",">> 상세주문옵션을 선택하시겠습니까?", "[1] 상세주문옵션 선택안함", "[2] 상세주문옵션 선택", "[3] 필수주문옵션 다시 선택", "[4] 상품 다시고르기");
			
			System.out.print("입력 > ");
			int select = sc.nextInt();
			if	(select == 1)	{
				
				orderList.add(order);
				break;
				
			} else if (select == 2) {
				
				detailOptions(order);
				orderList.add(order);
				break;
				
			} else if (select == 3) {
				
				continue;
				
			} else if (select == 4) {
				
				orderByCategory(custCode);
				return;
				
			} else {
				System.err.printf("%n%s%n%n", "** 옳바른 번호를 입력해주세요");
				continue;
			}
		}
			
		
		checkOrder(custCode);
		
		
	}
	
	
	private void checkOrder(String custCode) {
		
		int num = 1;
		
		totalPrice = 0;
		
		for (OrderVO vo : orderList) {
			
			vo.setItemPrice(0);
			System.out.printf("%s %n%s %n"
								,"-----------------------------------------------"
								,"음료 (" + num + ")"
								);
			
		
			if (vo.getCategory().contains("COF")) {
				costCoffee(vo);
			}
			if (vo.getCategory().contains("TEA")) {
				costTea(vo);
			}
			if (vo.getCategory().contains("FRA")) {
				costFrappucino(vo);
			}
			if (vo.getCategory().contains("AID")) {
				costAid(vo);
			}
			if (vo.getCategory().contains("ETC")) {
				costEtc(vo);
			}
		
			showEssenialOptions(vo);
			showDetailOptions(vo);
		
			System.out.println("금액 : " + vo.getItemPrice());
			
			totalPrice += vo.getItemPrice();
			num++;
		}
		
		
		
		System.out.printf("%s %n%s %n%s %n"
							, "-----------------------------------------------"
							, "장바구니에 담긴 총 금액 : " + totalPrice
							, "-----------------------------------------------"
							);
		
		
		while (true) {
			
			if (totalPrice == 0) {
				System.out.println(">> 장바구니에 담긴 음료가 없습니다. 음료를 선택해주세요" + "\n" 
								+ "[1] 음료 선택하러 가기" + "\t" + "[0] 메인메뉴로 나가기" + "\n" 
								+ "입력 > ");
				
				select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					orderByCategory(custCode);
					return;
					
				} else if (select == 0) {
					return;
					
				} else {
					System.err.println("옳은 번호를 골라주세요");
				}
			} else {
				
				break;
			}
		}
		
		while (true) {
			
			if (custCode != null) {
				System.out.println();
				System.out.print(">> 다른 음료를 추가로 고르시겠습니까?" + "\n"
								+ "[1] 이대로 결제하기" + "\t" + "[2] 주문음료 추가하기" + "\t" + "[3] 즐겨찾기에 등록된 주문옵션 불러오기" + "\t" + "[4] 장바구니에 담긴 음료 삭제하기" + "\n"
								+ "입력 > ");
				select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					
					
					
					payment(custCode);
					return;
					
				} else if (select == 2) {
					
					orderByCategory(custCode);
					return;
					
				} else if (select == 3) {
					
					orderByFavorite(custCode, false);
					return;
					
				} else if (select == 4) {
					
					deleteFrombascket(orderList);
					checkOrder(custCode);
					return;
					
				}
			} else {

				System.out.println();
				System.out.print(">> 다른 음료를 추가로 고르시겠습니까?" + "\n"
								+ "[1] 이대로 결제하기" + "\t" + "[2] 주문음료 추가하기" + "\t" + "[3] 장바구니에 담긴 음료 삭제하기" + "\n"
								+ "입력 > ");
				select = sc.nextInt();
				System.out.println();
				
				
				if (select == 1) {
					
					payment(custCode);
					return;
					
				} else if (select == 2) {
					
					orderByCategory(custCode);
					return;
					
				} else if (select == 3) {
					
					deleteFrombascket(orderList);
					checkOrder(custCode);
					return;
					
				}
			}
		}
	}
	
	
	
	private void deleteFrombascket(ArrayList<OrderVO> orderList) {
		
		System.out.println(">> 장바구니에서 삭제할 음료를 골라주세요");
		
		for (int i = 0; i < orderList.size(); i++) {
			
			System.out.print("[" + (i+1) + "] " + orderList.get(0).getChoice().get(1) + "\t");
		}
		
		System.out.print("[0] 뒤로가기" + "\n" + "입력 > ");
		
		select = sc.nextInt();
		System.out.println();
		
		if (select == 0) {
			return;
		} else {
			orderList.remove(select -1);
		}
		
		System.out.println(">> 해당 음료선택을 장바구니에서 제외하였습니다");
		System.out.println();
		
	}




	private boolean selectCategory(OrderVO order, String custCode) {

		boolean bool = false;
		
		while (true) {
			
			select = sc.nextInt();
			System.out.println();
		
			if (select == 1) {
				arr = showSingleMenu("COF", "커피");
				order.setCategory("COF");
				order.setBean("Arabica"); 
				//				korean = "커피";
				System.out.println();
				
				break;
				
			} else if (select == 2) {
			
				arr = showSingleMenu("TEA", "차");
				order.setCategory("TEA");
				//				korean = "차";
				System.out.println();
				
				break;
				
			} else if (select == 3) {
		
				arr = showSingleMenu("FRA", "프라푸치노");
				order.setCategory("FRA");
				order.setBean("Arabica"); 
				//				korean = "커피";
				System.out.println();
				
				break;
				
			} else if (select == 4) {
			
				arr = showSingleMenu("AID", "에이드");
				order.setCategory("AID");
	//			order.setBean("Arabica"); 
				//				korean = "커피";
				System.out.println();
				
				break;
				
			} else if (select == 5) {
			
				arr = showSingleMenu("ETC", "기타음료");
				order.setCategory("ETC");
	//			order.setBean("Arabica"); 
				//				korean = "커피";
				System.out.println();
				
				break;
				
			} else if (select == 0) {
				
				bool = true;
				break;
				
			} else {
			
				System.err.println("** 옳은 숫자를 입력해주세요");
				orderByCategory(custCode);
				break;
			}
		}
		
		return bool;
	}


	private boolean selectEssentialOptions(OrderVO order) { 
		
		boolean bool = false;
		
		order.setCupSize(optionScript("컵 사이즈", "TALL", "GRANDE", "VENTI")); 
		order.setCupSelect(optionScript("컵 선택", "매장컵", "개인컵", "일회용컵")); 
		
		if (order.getChoice().get(0).contains("HOT")) {
			order.setHotIce("HOT");
			
		} else if (order.getChoice().get(0).contains("ICE")) {
			order.setHotIce("ICE");
		
		} else { 
			order.setHotIce(optionScript("HOT 또는 ICE", "HOT", "ICE", null));
		} 
		
		return bool;
		
	}
	
		
		
	public void showEssenialOptions (OrderVO order) {
			
		System.out.printf("%s %n%s %n%s %n%10s%s %n%10s%s %n%10s%s %n%s %n%n"
							, "----------------------------"
							, "선택하신 항목 확인"
							, "----------------------------"
							, "음료 |", "  "+order.getChoice().get(1)+"("+order.getHotIce()+")"
							, "컵 사이즈 |", "  "+(order.getCupSize())
							, "컵 선택 |", "  "+(order.getCupSelect())
							, "----------------------------"
							);
		
	}

	
	public void showAllMenu () {
		
		showSingleMenu("COF", "커피");
		showSingleMenu("TEA", "차");
		showSingleMenu("FRA", "프라푸치노");
		showSingleMenu("AID", "에이드");
		showSingleMenu("ETC", "기타음료");

	}
	
	
	
	
	public ArrayList<String>[] showSingleMenu(String category, String korean) {

		ArrayList<String>[] arr = OrderDAO.getItemVOByCategory(category);
				
			System.out.println();
			
			System.out.printf("%10s", korean+"("+category+")");
			System.out.printf("%15s", "");
			System.out.printf("%80s", "");
			System.out.printf("%8s", "HOT");
			System.out.printf("%8s%n", "ICE");
			System.out.println("---------------------------------------------------------------------------");
			int drink_num = 1;
			for (ArrayList<String> list : arr) {
				
				if (list.get(0).contains(category)) { 
					System.out.printf("%-5s|", "["+drink_num+"]");
					System.out.printf("%-15s|", list.get(1));
					System.out.printf("%-85s|", list.get(2));
					onlyIceOnlyHot(list.get(3));
				}
				
				drink_num++;
			}
		return arr;
	}
	
	
	
	
	
	public void showDetailOptions(OrderVO order) {
		
		System.out.println("상세옵션 선택 현황" + "\n");
		
		if(order.getCategory().equals("COF") || order.getCategory().equals("FRA")) {
		
			System.out.printf("%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n%8s | %s %n"
								, options[1], (order.getIce()==null) ? "보통" : order.getIce()
								, options[2], (order.getBean()==null) ? "Arabica" : order.getBean()
								, options[3], (order.getEspresso()==null) ? "블론드" : order.getEspresso()
								, options[4], (order.getMilk()==null) ? "선택안함" : order.getMilk()
								, options[5], (order.getSyrup()==null) ? "선택안함" : order.getSyrup()
								, options[6], (order.getAddShot()==0) ? "안함" : order.getAddShot() + "샷 추가 (+500/1샷)"
								, options[7], (order.getDrinkAmount()==1) ? "1잔" : order.getDrinkAmount() + "잔"
								);
		} else if (order.getCategory().equals("AID") || order.getCategory().equals("TEA")) {
			
			System.out.printf("%8s | %s %n%8s | %s %n"
							, options[1], (order.getIce()==null) ? "보통" : order.getIce()
							, options[7], (order.getDrinkAmount()==1) ? "1잔" : order.getDrinkAmount() + "잔"
							);
			
		} else if (order.getCategory().equals("ETC")) {
			
			System.out.printf("%8s | %s %n%8s | %s %n%8s | %s %n"
							, options[1], (order.getIce()==null) ? "보통" : order.getIce()
							, options[4], (order.getMilk()==null) ? "선택안함" : order.getMilk()
							, options[7], (order.getDrinkAmount()==1) ? "1잔" : order.getDrinkAmount() + "잔"
					);
		}
				
	}

	


	
	private void detailOptions(OrderVO order) {
		
		boolean bool = true;
		
		while (bool) {
			
			System.out.println("--------------------------------------------------------------------------------");
			showDetailOptions(order);
			System.out.printf("%n%s %n%s %n"
								, "--------------------------------------------------------------------------------"
								, ">> 원하시는 옵션을 선택하시고 선택이 끝나시면 숫자 '0'을 입력해주세요"
								);
			
			if (order.getCategory().equals("COF")) {
				bool = detailOptionCOF(order);
			} else if (order.getCategory().equals("TEA")) {
				bool = detailOptionTEA(order);
			} else if (order.getCategory().equals("FRA")) {
				bool = detailOptionFRA(order);
			} else if (order.getCategory().equals("AID")) {
				bool = detailOptionAID(order);
			} else if (order.getCategory().equals("ETC")) {
				bool = detailOptionETC(order);
			}
		}
		
	}


	private boolean detailOptionCOF (OrderVO order) {
	
	boolean result = true;
	
	System.out.printf("%8s %8s %8s %8s %8s %8s %8s%n %s"
						, "[1] " + options[1], "[2] " + options[2], "[3] " + options[3],  "[4] " + options[4], "[5] " + options[5], "[6] " + options[6], "[7] " + options[7]
						, "입력 > "
						);
	select = sc.nextInt();
	System.out.println();
	
	switch (select) { 
		
		case 0 : 
			
			result = false;
			break;
			
		case 1:
			
			order.setIce(optionScript(options[1], "적게", "보통", "많게"));
			break;
			
		case 2:
			
			selectBean(OrderDAO.getBeanVOs(), order);
			break;
			
		case 3:
			
			order.setEspresso(optionScript(options[3], "블론드", "디카페인", "(1/2)디카페인"));
			break;


		case 4:

			order.setMilk(optionScript(options[4], "일반우유", "저지방우유", "무지방우유", "두유"));
			break;

		case 5:

			order.setSyrup(optionScript(options[5], "바닐라시럽", "헤이즐넛시럽", "카라멜시럽", "초콜릿시럽"));
			break;
			
		case 6:
			
			order.setAddShot(returnIntDetailOption(options[6]));				
			break;

			
		case 7:
			
			order.setDrinkAmount(returnIntDetailOption(options[7]));				
			break;

	
	}
	return result;
}

	
	private boolean detailOptionETC(OrderVO order) {
		boolean result = true;
		
		System.out.printf("%8s %8s%n %8s%n %s"
							, "[1] " + options[1], "[2] " + options[4], "[3] " + options[7]
							, "입력 > "
							);
		select = sc.nextInt();
		System.out.println();
		
		switch (select) { 
			
			case 0 : 
				
				result = false;
				break;
				
				

			case 1:

				order.setIce(optionScript(options[1], "적게", "보통", "많게"));		
				break;

				
			case 2:
				
				order.setMilk(optionScript(options[4], "일반우유", "저지방우유", "무지방우유", "두유"));
				break;
				
			case 3:
				
				order.setDrinkAmount(returnIntDetailOption(options[7]));					
				break;


		
		}
		return result;
	}


	private boolean detailOptionAID(OrderVO order) {
		
		boolean result = true;
		
		System.out.printf("%8s %8s%n %s"
							, "[1] " + options[1], "[2] " + options[7]
							, "입력 > "
							);
		select = sc.nextInt();
		System.out.println();
		
		switch (select) { 
			
			case 0 : 
				
				result = false;
				break;
				
			case 1:
				
				order.setIce(optionScript(options[1], "적게", "보통", "많게"));
				break;
				
			
			case 2:
				
				order.setDrinkAmount(returnIntDetailOption(options[7]));				
				break;

		
		}
		return result;
	}


	private boolean detailOptionFRA(OrderVO order) {
		boolean result = true;
		
		System.out.printf("%8s %8s %8s %8s %8s %8s %8s%n %s"
							, "[1] " + options[1], "[2] " + options[2], "[3] " + options[3],  "[4] " + options[4], "[5] " + options[5], "[6] " + options[6], "[7] " + options[7]
							, "입력 > "
							);
		select = sc.nextInt();
		System.out.println();
		
		switch (select) { 
			
			case 0 : 
				
				result = false;
				break;
				
			case 1:
				
				order.setIce(optionScript(options[1], "적게", "보통", "많게"));
				break;
				
			case 2:
				
				selectBean(OrderDAO.getBeanVOs(), order);
				break;
				
			case 3:
				
				order.setEspresso(optionScript(options[3], "블론드", "디카페인", "(1/2)디카페인"));
				break;


			case 4:

				order.setMilk(optionScript(options[4], "일반우유", "저지방우유", "무지방우유", "두유"));
				break;

			case 5:

				order.setSyrup(optionScript(options[5], "바닐라시럽", "헤이즐넛시럽", "카라멜시럽", "초콜릿시럽"));
				break;
				
			case 6:
				
				order.setAddShot(returnIntDetailOption(options[6]));				
				break;

				
			case 7:
				
				order.setDrinkAmount(returnIntDetailOption(options[7]));				
				break;

		
		}
		return result;
	}


	private boolean detailOptionTEA(OrderVO order) {
		
		boolean result = true;
		
		System.out.printf("%8s %8s%n %s"
				, "[1] " + options[1],  "[2] " + options[7]
				, "입력 > "
				);
		
		select = sc.nextInt();
		System.out.println();
		
		switch (select) { 
		
		case 0 : 
			
			result = false;
			break;
			
		case 1:
			
			order.setIce(optionScript(options[1], "적게", "보통", "많게"));
			break;
		
		case 2 :
			order.setDrinkAmount(returnIntDetailOption(options[7]));	
		}
		
		return result;
	}



	
	
	
	
	private void payment(String custCode) {
		
		System.out.println("%% 결제화면");
		System.out.println();
		
		
		System.out.printf("%s %n%s %n%s"
							, "-----------------------------------------------"
							, "결제하실 금액 : " + totalPrice
							, "-----------------------------------------------"
							);
		
		
		System.out.print("\n" + ">> 결제화면입니다. 선택을 확인해주시고 결제를 진행해주세요" + "\n"
				+ "[1] 결제수단 선택하기" + "\n" + "[2] 메뉴 추가/변경하기" + "\n" + "\n" + "[0] 결제취소하고 메인메뉴로 돌아가기" + "\n"
				+ "입력 > ");
		
		int select = sc.nextInt();
		System.out.println();
		
		
		switch (select) {
		case 1:
			
			break;

		case 2:
			
			checkOrder(custCode);
			return;
			
		case 0:
			
			totalPrice = 0;
			orderList = new ArrayList<>();
			
			return;
		}
		
		
			
		CustomerVO customer = null;
		// 결제할 때 비회원 주문은 비회원 주문 인식용(CUST_CODE 앞에 'NON이 붙음) custCode 생성
		if (custCode != null) {
			customer = CustomerDAO.getCustomerByCustCode(custCode);
		} else {
			custCode = OrderDAO.createNonCustCode();
		}
		
		System.out.print(">> 결제수단을 선택해주세요" + "\n" + 
							"[1] 카드로 결제하기" + "\t" + "[2] 핸드폰 소액결제하기" + "\n" 
							+ "입력 > ");
		
		select = sc.nextInt();
		System.out.println();
		
		
		if (select == 1) {
			
			if (!custCode.contains("NON")) {
				
				List<CardVO> cardList = null;
				
				if ((cardList = CardDAO.selectCardDAO(custCode)) != null) {
					System.out.println(">> 결제할 카드를 선택해주세요");
					
					int num = 1;
					
					for (CardVO vo : cardList) {
						System.out.printf("%4s | %8s | %s %n"
								,"[" + num + "] " , vo.getBankName(), vo.getCardNum().substring(12, 16));
						System.out.println();
						num++;
					}
					
					System.out.print("입력 > ");
					sc.nextInt();
					System.out.println();
					
				} else {
					
					sc = new Scanner(System.in);
					System.out.println(">> 등록된 카드가 없습니다. 결제하실 카드의 정보를 입력해주세요");
					System.out.print("카드사 > " );
					sc.nextLine();
					System.out.print("카드번호 > " );
					sc.nextLine();
					System.out.print("카드비밀번호 > " ); // 주문옵션에 결제 수단 안 넣음...
					sc.nextLine();
					
				}
			} else {
				
				System.out.println(">> 결제하실 카드의 정보를 입력해주세요");
				System.out.print("카드사 > " );
				sc.next();
				System.out.print("카드번호 > " );
				sc.next();
				System.out.print("카드비밀번호 > " );
				sc.next();
			}
				
			
		} else if (select == 2) {
			
			if (!custCode.contains("NON")) {
				
				String phone = null;
				customer = CustomerDAO.getCustomerByCustCode(custCode);
				
				while(true) {
					
					
					System.out.print(">> 휴대폰 결제를 진행합니다. 결제하실 휴대폰 번호를 입력해주세요 [(-) 제외하고 입력]" + "\n"
									+ "휴대폰 번호 > ");
					
					phone = sc.next();
					System.out.println();
					
					if (phone.getBytes().length != 11) {
						System.err.println("[오류] 폰 번호 입력 범위를 벗어났습니다.");
						continue;
					} else {
						if (phone.substring(0, 3).equals("010")) {
						} else {
							System.err.println("[오류] 010으로 시작하는 번호를 입력해주십시오.");			
							continue;
						}
					}
					break;
				}
				
				while (true) {
					
					System.out.print(">> 결제 확인을 위해 로그인 시 입력하는 고객님의 비밀번호를 입력해주세요" + "\n" 
									+ "입력 > ");
					String pw = sc.next();
					System.out.println();
					
					if (pw.equals(customer.getPassword())) {
					} else {
						System.out.print("** 비밀번호가 옳지 않습니다. 다시 입력해주세요" + "\n"
										+ "[1] 비밀번호 재입력하기" + "\t" + "[0] 메인메뉴로 돌아가기"
										 + "입력 > ");
						select = sc.nextInt();
						System.out.println();
						
						if (select == 1) {
							continue;
						} else if (select == 0) {
							
							if (custCode.contains("NON")) {
								custCode = null;
							}
							
							return;
						} else {
							System.err.println("** 옳은 숫자를 입력해주세요");
							continue;
						}
					}
					break;
				}
				
				
			} else {
				
				while(true) {
					
					System.out.print(">> 비회원 휴대폰 결제를 진행합니다. 고객님의 휴대폰 번호를 입력해주세요" + "\n"
							+ "휴대폰 번호 > ");
					
					String phone = sc.next();
					System.out.println();
					
					if (phone.getBytes().length != 11) {
						System.err.println("[오류] 폰 번호 입력 범위를 벗어났습니다.");
						continue;
					} else {
						
						if (phone.substring(0, 3).equals("010")) {
						} else {
							System.err.println("[오류] 010으로 시작하는 번호를 입력해주십시오.");			
							continue;
						}
					}
					
					break;
				}
			}
		}
			
			
		String phoneNum = null;
		
		if (!custCode.contains("NON") && customer.getStamp() >= 10) {
			
			System.out.print(">> 보유하신 스탬프 사용이 가능합니다. 사용하시겠습니까? (현재 보유한 스탬프 갯수 : " + customer.getStamp() + ")" + "\n"
					+ "** 스탬프 10개를 현금 4500원처럼 사용가능 **"
					+ "[1] 예" + "\t" + "[2] 아니오");
			
			select = sc.nextInt();
			System.out.println();
			
			System.out.println();
		
			while (true) {
				if (select == 1) {
					CustomerDAO.UpdateCoupon(custCode, -10);
					System.out.println("스탬프가 사용되었습니다"); // 한번에 사용할 스탬프 갯수는 나중에 구현
				} else if (select == 2) {;
				}
			}
		
		} else if (custCode.contains("NON")) {
			
			while (true) {
				System.out.print(">> 회원가입을 하시면 즐겨찾기 등록 및 쿠폰 적립 혜택을 드립니다" + "\n"
									+ "[1] 회원가입하기" + "\t" + "[2] 비회원으로 결제 진행하기" + "\n"
									+ "입력 > ");
				
				select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					
					totalPrice = 0;
					orderList = new ArrayList<>();
					
					new CustomerScanner().signUp();
					return;
					
				} else if (select == 2) {
					
					break;
					
				} else {
					
					System.err.println("** 옳은 숫자를 입력해주세요");
					continue;
				}
			}
			
			
			while (true) {
				
				customer = CustomerDAO.getCustomerByCustCode(custCode);
				
				System.out.print(">> 추후 결제 내역을 확인하실 휴대폰 번호를 입력해주세요" + "\n" 
									+ "입력(-제외) > ");
				
				phoneNum = sc.next();
				System.out.println();
				
				if (phoneNum.getBytes().length != 11) {
					System.err.println("[오류] 폰 번호 입력 범위를 벗어났습니다.");
					continue;
				} else {
					if (phoneNum.substring(0, 3).equals("010")) {
					} else {
						System.err.println("[오류] 010으로 시작하는 번호를 입력해주십시오.");			
						continue;
					}
				}
				
				break;
					
			}
			
		}
		
		while (true) {
			
		System.out.println(">> 결제를 확정하시겠습니까? (확정을 선택하시면 결제가 완료됩니다)" + "\n" 
							+ "[1] 결제 확정" + "\t" + "[2] 결제 취소하고 메인으로 가기" + "\n"
							+ "입력 > ");
		
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				break;
				
			} else if (select == 2) {
				if (custCode.contains("NON")) {
					custCode = null;
				}
				return;
			} else {
				System.err.println("** 옳바른 숫자를 입력해주세요");
			}
		}
		
		int cycle = 1;
		ArrayList<Integer> optionCodeList = new ArrayList<>();
		
		int orderCode = OrderDAO.insertOrders(custCode, totalPrice);
		
		System.out.println("주문코드 : " + orderCode);
		for (OrderVO order : orderList) {
			int optionCode = OrderDAO.insertOptions(order.getCategory(), order.getChoice(), order, custCode, orderCode);
			optionCodeList.add(optionCode);
		}
		
		
		System.out.println(">> 결제가 완료되었습니다!"); // 영수증 보여주기(선택항목, 결제날짜 등등)
		
		totalPrice = 0;
		
		if (custCode.contains("NON")) {
			OrderDAO.insertNonCustomer(custCode, orderCode, phoneNum);
		}
		
		if (!custCode.contains("NON")) {
			System.out.println(">> 스탬프가 " + orderList.size() + "개 적립되었습니다");
			CustomerDAO.UpdateCoupon(custCode, orderList.size());
			
			System.out.print(">> 방금 전 결제한 옵션을 즐겨찾기에 등록하시겠습니까?" + "\n" 
					+ "[1] 즐겨찾기에 등록" + "\t" + "[2] 메인메뉴로 돌아가기" + "\n"
					+ "입력 > ");
			
			select = sc.nextInt();
			
			while (true) {
				
				
				if (select == 1) {
					
					System.out.println(">> 어떤 선택을 즐겨찾기로 등록하시겠습니까?");
					int num = 1;
					for (OrderVO order : orderList) {
						System.out.print("[" + num + "] " + order.getChoice().get(1) + "\t");
						num++;
					}
					System.out.println();
					System.out.print("입력 > ");
					select = sc.nextInt();
					System.out.println();
					
					System.out.print(">> 옵션을 기억하실 즐겨찾기명을 입력해주세요" + "\n" + "입력 > ");
					String favoriteName = sc.next();
					System.out.println();
					
					OrderDAO.insertFavorite(orderList.get(select -1), custCode, optionCodeList.get(select -1), favoriteName);
					
					
					System.out.println(">> 즐겨찾기 등록이 완료되었습니다!"); 
					System.out.print(">> 다른 선택옵션도 즐겨찾기에 등록하시겠습니까?" + "\n"
										+ "[1] 다른 선택옵션도 즐겨찾기에 등록" + "\t" + "[2] 메인메뉴로 돌아가기");
					
					sc = new Scanner(System.in);
					String favorite = sc.nextLine();
					System.out.println();
					if (select == 1) {
						continue;
					} else if (select == 2) {
						
						break;
					}
				}
				else if (select == 2) break;
			}
			
		}
		
		orderList = new ArrayList<>();
		
		System.out.printf("메인메뉴로 돌아갑니다..." + "\n" + ">> 아무키나 눌러서 메인메뉴로 이동하세요" + "\n" + "입력 > "); 
		sc.next();
		sc.nextLine();
		
		if (custCode.contains("NON")) {
			main.notLogged();
			return;
			
		} else {
			main.logged(custCode);
			return;
		}
		
	}


	
	
	public void costCoffee(OrderVO order) {
		
		if (order.getSyrup() != null) {
			order.plusItemPrice(PriceSyrup);
			
		}
		
		if (order.getEspresso()==null) {
		} else if (order.getEspresso().contains("디카페인")) { 
			order.plusItemPrice(priceDecaffein);
			
		}
		
		if (order.getMilk()==null) {
			
		} 	else if (order.getMilk().contains("저지방")) {
			order.plusItemPrice(priceLowFatMilk);
			
		}	else if (order.getMilk().contains("무지방")) {
			order.plusItemPrice(priceFreeMilk);
		}	else if (order.getMilk().contains("두유")) {
			order.plusItemPrice(PriceSoyMilk);
		}
		
		if (order.getSyrup()==null) {
		} 	else {
			order.plusItemPrice(PriceSyrup);
		}
		
		
		
		order.plusItemPrice(PriceAddShot * order.getAddShot() + Integer.parseInt(order.getChoice().get(3)));
		order.setItemPrice(order.getItemPrice() * order.getDrinkAmount());
		
	}
	
	
	private void costTea(OrderVO order) {
		
		order.setItemPrice(Integer.parseInt(order.getChoice().get(3)) * order.getDrinkAmount());
		
	}
	
	
	private void costEtc(OrderVO order) {
		
		if (order.getMilk()==null) {
			
		} 	else if (order.getMilk().contains("저지방")) {
			order.plusItemPrice(priceLowFatMilk);
			
		}	else if (order.getMilk().contains("무지방")) {
			order.plusItemPrice(priceFreeMilk);
		}	else if (order.getMilk().contains("두유")) {
			order.plusItemPrice(PriceSoyMilk);
		}
		
		order.plusItemPrice(Integer.parseInt(order.getChoice().get(3)));
		order.setItemPrice(order.getItemPrice() * order.getDrinkAmount());
	}
	
	private void costAid(OrderVO order) {
		
		order.setItemPrice(Integer.parseInt(order.getChoice().get(3)) * order.getDrinkAmount());
	}
	
	
	
	private void costFrappucino(OrderVO order) {
		
		if (order.getSyrup() != null) {
			order.plusItemPrice(PriceSyrup);
			
		}
		
		if (order.getEspresso()==null) {
		} else if (order.getEspresso().contains("디카페인")) { 
			order.plusItemPrice(priceDecaffein);
			
		}
		
		if (order.getMilk()==null) {
			
		} 	else if (order.getMilk().contains("저지방")) {
			order.plusItemPrice(priceLowFatMilk);
			
		}	else if (order.getMilk().contains("무지방")) {
			order.plusItemPrice(priceFreeMilk);
		}	else if (order.getMilk().contains("두유")) {
			order.plusItemPrice(PriceSoyMilk);
		}
		
		if (order.getSyrup()==null) {
		} 	else {
			order.plusItemPrice(PriceSyrup);
		}
		
		
		
		order.plusItemPrice(PriceAddShot * order.getAddShot() + Integer.parseInt(order.getChoice().get(3)));
		order.setItemPrice(order.getItemPrice() * order.getDrinkAmount());
		
		
	}
	
	





	private int returnIntDetailOption(String object) {
		
		if (object.equals(options[6])) {
			System.out.print(">> 샷추가 횟수를 숫자로 입력해주세요(+500/1샷)" + "\n"
							+ "입력 > ");
		} else if (object.equals(options[7])) {
			System.out.print(">> 주문하실 수량을 숫자로 입력해주세요" + "\n"
					+ "입력 > ");
			
		}
		int num = sc.nextInt();
		System.out.println();
		
		
		return num;
	}
	
	
	
	
	public void onlyIceOnlyHot (String price) {
		
		if (price.contains("ICE")) {
			System.out.printf("%8s|", "");
			System.out.printf("%8s|%n", price);
			
		} else if (price.contains("HOT")) {
			System.out.printf("%8s|", price);
			System.out.printf("%8s|%n", "");
			
		} else {
			System.out.printf("%8s|", price);
			System.out.printf("%8s|%n", price);
		}
	}

	



	private void selectBean(ArrayList<String>[] beans, OrderVO order) {
		
		System.out.println(">> 원두종류를 골라주세요");

		while (true) {
			
				for (int i = 0; i < beans.length; i++) {
					
					System.out.printf("%10s", "["+(i+1)+"] "+ beans[i].get(1));
				}
				System.out.println();
				
				
				try {
					System.out.println("입력 > ");
					int select = sc.nextInt();
					order.setBean(beans[select-1].get(1));
					break;
				
				} catch (Exception e) {
					System.err.println("** 올바른 숫자를 입력해주세요");
					sc = new Scanner(System.in);
				}
		}
		
	}
	
	
	
	
	
	private String optionScript (String option, String select1, String select2, String select3) {
		
		String result = null;
		
		
		while (true) {
			
			if	(select3 == null)	{
				System.out.printf("%s%n%-20s%-20s%n", ">> "+option, "[1] "+select1, "[2] "+select2);
				
				System.out.print("입력 > ");
				int select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					result = select1;
					break;
				} 	else if	(select == 2)	{
					result = select2;
					break;
				}	
			} else {
				System.out.printf("%s%n%-20s%-20s%-20s%n", ">> "+option, "[1] "+select1, "[2] "+select2, "[3] "+select3);
				
				System.out.print("입력 > ");
				int select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					result = select1;
					break;
				} 	else if	(select == 2)	{
					result = select2;
					break;
				}	else if (select == 3)	{
					result = select3;
					break;
				}
			}
			System.err.println("** 올바른 숫자를 입력해주세요");
		}
		return result;
	}
	
	
	private String optionScript (String option, String select1, String price1, String select2, String price2, String select3, String price3) {
		
		String result = null;
		
		
		while (true) {
			
			if	(select3 == null)	{
				System.out.printf("%s%n%-20s%-20s%n", ">> "+option, "[1] "+select1 + price1, "[2] "+select2 + price2);
				
				System.out.print("입력 > ");
				int select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					result = select1;
					break;
				} 	else if	(select == 2)	{
					result = select2;
					break;
				}	
			} else {
				System.out.printf("%s%n%-20s%-20s%-20s%n", ">> "+option, "[1] "+select1 + price1, "[2] "+select2 + price2, "[3] "+select3 +price3);
				
				System.out.print("입력 > ");
				int select = sc.nextInt();
				System.out.println();
				
				if (select == 1) {
					result = select1;
					break;
				} 	else if	(select == 2)	{
					result = select2;
					break;
				}	else if (select == 3)	{
					result = select3;
					break;
				}
			}
			System.err.println("** 올바른 숫자를 입력해주세요");
		}
		
		
		System.out.println();
		
		return result;
	}

	
	private String optionScript (String option, String select1, String select2, String select3
									, String select4) {
		
		String result = null;
		
		
		while (true) {
			System.out.printf("%s%n%-20s%-20s%-20s%-20s%n", ">> "+option, "[1] "+select1, "[2] "+select2, "[3] "+select3, "[4] "+select4);
			
			System.out.print("입력 > ");
			int select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				result = select1;
				break;
			} 	else if	(select == 2)	{
				result = select2;
				break;
			}	else if (select == 3)	{
				result = select3;
				break;
			}	else if (select == 4)	{
				result = select4;
				break;
			}
			
			System.err.println();
		}
		
		System.out.println();
		
		return result;
	}
	
	private String optionScript (String option, String select1, String price1, String select2, String price2, String select3, String price3
			, String select4, String price4) {

		String result = null;
		
		
		while (true) {
		System.out.printf("%s%n%-20s%-20s%-20s%n", ">> "+option, "[1] "+select1, "[2] "+select2, "[3] "+select3, "[4] "+select4);
		
		System.out.print("입력 > ");
		int select = sc.nextInt();
		System.out.println();
		
		if (select == 1) {
		result = select1;
		break;
		} 	else if	(select == 2)	{
		result = select2;
		break;
		}	else if (select == 3)	{
		result = select3;
		break;
		}	else if (select == 4)	{
		result = select4;
		break;
		}
		
		System.err.println();
		}
		
		System.out.println();
		
		return result;
		}


	public void orderByFavorite(String custCode, boolean bool) {
		
		OrderVO order = null;
		
		ArrayList<FavoriteVO> list = OrderDAO.getFavorites(custCode);
		
		System.out.println(">> 빠른 주문을 원하시는 즐겨찾기 항목을 선택해주세요");
		
		if (list.isEmpty() && bool) {
			System.out.print(">> 등록된 즐겨찾기 옵션이 없습니다. 음료를 주문해주시고 즐겨찾기에 등록해주세요" + "\n" + 
							"[1] 음료 주문하러가기" + "[0] 메인메뉴로 나가기" + "\n"
							+ "입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			if (select == 1) {
				
				orderByCategory(custCode);
				
			} else if (select == 0) {
				
				System.out.println(">> 메인메뉴로 이동합니다...");
				return;
			}
			
		} else if (list.isEmpty() && bool) {
			
		} else {
			for (int i = 0; i < list.size(); i++) {
				
				System.out.print("[" + (i+1) + "] " + list.get(i).getFavoriteName());
			}
			System.out.println();
			select = sc.nextInt();
			
			int optionCode = list.get(select-1).getOptionCode();
			System.out.println(optionCode);
			
			order = OrderDAO.getOrder(optionCode);
			
			try {
				order.getCategory();
			} catch (Exception e) {
				System.out.println("OrderScanner.Orderbyfavorite.order.getCategory_ 오류");
			}
			System.out.print(">> 선택하신 옵션에 대해 주문하실 음료 수량을 입력해주세요" + "\n" 
								+ "입력 > ");
			select = sc.nextInt();
			System.out.println();
			
			order.setDrinkAmount(select);
			
			orderList.add(order);
			
			System.out.println(">> 선택하신 옵션이 장바구니에 담겼습니다...");
		}
		
		checkOrder(custCode);
		
		
	}
			
	public void PlusTotal (int num) {
		
		totalPrice += num;
	}



}
