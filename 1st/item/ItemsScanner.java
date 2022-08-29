package item;

import java.util.List;
import java.util.Scanner;

import project.MainPage;

public class ItemsScanner extends ItemsDAO{

	static Scanner sc = new Scanner(System.in);
	final static String REGEX = "[0-9]+";

	private static String choiceSC;
	private static int choice = 10;

	private static String exitSC;
	private static int exit = 2;

	private static String priceSC;
	private static int price;
	
	private static String modifyDateStartSC;
	private static int modifyDateStart;
	
	private static String dateStr;
	private static int date;
	
	private static String modifyDateEndSC;
	private static int modifyDateEnd;
	
	private static String itemCode;
	private static String updateItemCode;
	
	private static String beanCode;
	private static String itemName;
	private static String introduction;
	
	
public static void itemManager() {
		while(true) {
			System.out.println();
			System.out.println(">> 원하시는 메뉴를 선택해주세요" + "\n"
					+ "[1] 상품정보 등록" + "\n"
					+ "[2] 상품정보 수정" + "\n"
					+ "[3] 상품정보 삭제" + "\n"
					+ "[4] 상품정보 조회" + "\n"
					+ "[5] 상품정보 수정 내역" + "\n"
					+ "[0] 뒤로 가기"); 
			
			choiceSelect(5);
			
			if(choice == 0) {
				System.out.println();
				System.out.println(">> [상품 정보] 종료");
				System.out.println();
				break;
			} if (choice == 1) {
				insertItem();		
				System.out.println();
				System.out.println(">> [상품 등록] 종료");
				System.out.println();
				continue;
			} if (choice == 2) {
				updateItem();
				System.out.println();
				System.out.println(">> [상품 수정] 종료");
				System.out.println();
				continue;
			} if (choice == 3) {
				deleteItem();
				System.out.println();
				System.out.println(">> [상품 삭제] 종료");
				System.out.println();
				continue;
			} if (choice == 4) {
				dispItem();
				System.out.println();
				System.out.println(">> [상품 조회] 종료");
				System.out.println();
				continue;
			} if (choice == 5) {
				dispItemsLog();
				System.out.println();
				System.out.println(">> [상품 수정 내역] 종료");
				System.out.println();
				continue;
			}
		}
	}
	
	private static void insertItem() {
		while(true) {
			System.out.println();
			System.out.println("[상품 등록]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 등록할 상품 정보를 차례대로 입력해주세요");
			
			itemCodeScanOverlap();
			beanCodeScan();
			itemNameScan();
			introductionScan();
			priceScan();
			
			insertItemDAO(itemCode, beanCode, itemName, introduction, price);
			
			System.out.println();
			System.out.println("[등록된 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
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
			exitSC = sc.nextLine();
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
					+ "[1] 진행" + "\n" 
					+ "[0] 종료");
			System.out.println();
			System.out.print("입력 > ");
			exitSC = sc.nextLine();
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
	
	public static void choiceSelect(int i) {
		while(true) {
			System.out.println();
			System.out.print("입력 > ");
			choiceSC = sc.nextLine();
			
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
	
	private static void itemCodeScan() {
		while(true) {
			System.out.print("상품코드 : ");
			itemCode = sc.nextLine();
			
			if(selectCode(itemCode) == null) {
				System.err.println("[오류] 존재하지 않는 상품 코드입니다.");
				continue;
			}
			if (itemCode.getBytes().length > 20 || itemCode.getBytes().length <= 0) {
				System.err.println("[오류] 상품 코드 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void itemCodeScanOverlap() {
		while(true) {
			System.out.print("상품코드 : ");
			itemCode = sc.nextLine();
			
			if (selectCode(itemCode) != null) {
				System.err.println("[오류] 이미 존재하는 상품 코드입니다.");
				continue;
			}
			if (itemCode.getBytes().length > 20 || itemCode.getBytes().length <= 0) {
				System.err.println("[오류] 상품 코드 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void updateItemCodeScanOverlap() {
		while(true) {
			System.out.print("상품코드 : ");
			updateItemCode = sc.nextLine();
			
			if (selectCode(updateItemCode) != null) {
				System.err.println("[오류] 이미 존재하는 상품 코드입니다.");
				continue;
			}
			if (updateItemCode.getBytes().length > 20 || updateItemCode.getBytes().length <= 0) {
				System.err.println("[오류] 상품 코드 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	
	private static void beanCodeScan() {
		while(true) {
			System.out.print("원두코드 : ");
			beanCode = sc.nextLine();
			
			if (selectBeanCode(beanCode) == null) {
				System.err.println("[오류] 존재하지 않는 원두 코드입니다.");
				continue;
			}
			if (beanCode.getBytes().length > 10 || beanCode.getBytes().length <= 0) {
				System.err.println("[오류] 원두코드 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void itemNameScan() {
		while(true) {
			System.out.print("상품명 : ");
			itemName = sc.nextLine();
			
			if (itemName.getBytes().length > 40 || itemName.getBytes().length <= 0) {
				System.err.println("[오류] 상품명 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void introductionScan() {
		while(true) {
			System.out.print("상세정보 : ");
			introduction = sc.nextLine();
			
			if (introduction.getBytes().length > 200 || introduction.getBytes().length < 0) {
				System.err.println("[오류] 상세정보 입력 범위를 벗어났습니다.");
				continue;
			}
			break;
		}
	}
	
	private static void priceScan() {
		while(true) {
			System.out.print("가격 : ");
			priceSC = sc.nextLine();
			
			if (priceSC.matches(REGEX)) {
				price = Integer.parseInt(priceSC);
				if ((int)(Math.log10(price)+1 ) > 8 ||(int)(Math.log10(price)+1) < 0) {
					System.err.println("[오류] 가격 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 8자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}

	private static void updateItem() {
		while(true) {
			System.out.println();
			System.out.print("[상품 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			itemCodeScan();
			
			System.out.println("[수정할 상품]");
			selectAllDisp(itemCode);
			System.out.println();
			System.out.println(">> 수정할 정보를 선택해주세요\r\n"
					+ "[1] 상품코드" + "\n"
					+ "[2] 원두코드 " + "\n"
					+ "[3] 상품명 " + "\n"
					+ "[4] 상세정보"  + "\n"
					+ "[5] 가격"   + "\n"
					+ "[6] 전체 수정" + "\n"
					+ "[0] 종료");
			System.out.print("입력 > ");

			choiceSelect(6);
			
			if (choice == 6) {
				updateItemAll(itemCode);
			} if (choice == 1) {
				updateItemCode(itemCode);
			} if (choice == 2) {
				updateBeanCode(itemCode);
			} if (choice == 3) {
				updateItemName(itemCode);
			} if (choice == 4) {
				updateItemIntroduction(itemCode);
			} if (choice == 5) {
				updateItemPrice(itemCode);
			} if (choice == 0) {
				break;
			}
		}
	}
	
	private static void updateItemAll(String itemCode) {
		while(true) {
			System.out.println();
			System.out.println("[상품 전체 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			System.out.println();
			System.out.println(">> 수정할 상품 정보를 차례대로 입력해주세요");
			updateItemCodeScanOverlap();
			
			System.out.println();
			beansDisp();
			System.out.println();
			
			beanCodeScan();
			itemNameScan();
			introductionScan();
			priceScan();
			
			updateItemDAO(itemCode, updateItemCode, beanCode, itemName, introduction, price);
			System.out.println();
			
			System.err.println("[수정한 상품]");
			selectAllDisp(updateItemCode);

			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void updateItemPrice(String itemCode) {
		while(true) {
			System.out.println();
			System.out.println("[상품 가격 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			System.out.println();
			System.out.println(">> 수정할 가격을 입력해주세요");
			priceScan();
			
			updateItemPriceDAO(itemCode, price);
			System.out.println();
			
			System.out.println("[수정한 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void updateItemIntroduction(String itemCode) {
		while(true) {
			System.out.println();
			System.out.println("[상품 상세정보 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			System.out.println();
			System.out.println(">> 수정할 상세정보를 입력해주세요");
			
			introductionScan();
			
			updateItemIntroductionDAO(itemCode, introduction);
			System.out.println();
			
			System.out.println("[수정한 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void updateItemName(String itemCode) {
		while(true) {
			System.out.println();
			System.out.println("[상품명 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			System.out.println();
			System.out.println(">> 수정할 상품명을 입력해주세요");
			itemNameScan();
			
			updateItemNameDAO(itemCode, itemName);
			System.out.println();
			
			System.err.println("[수정한 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void updateBeanCode(String itemCode) {
		while(true) {
			System.out.println();
			System.out.println("[상품 원두코드 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			System.out.println();
			beansDisp();

			System.out.println();
			System.out.println(">> 수정할 원두코드를 입력해주세요");
			beanCodeScan();
			
			updateBeanCodeDAO(itemCode, beanCode);
			System.out.println();
			
			System.out.println("[수정한 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void updateItemCode(String itemCode) {
		while(true) {
			updateItemCodeScanOverlap();
			
			updateItemCodeDAO(itemCode, updateItemCode);
			System.out.println();
			
			System.out.println("[수정한 상품]");
			selectAllDisp(updateItemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void deleteItem() {
		while(true) {
			System.out.println();
			System.out.print("[상품 삭제]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 삭제할 상품코드를 입력해주세요");
			itemCodeScan();
			
			System.out.println("[선택한 상품]");
			selectAllDisp(itemCode);
			
			while(true) {
				System.out.println();
				System.out.println(itemCode + "를 삭제하시겠습니까?\r\n"
						+ "[1] 삭제" + "\n"
						+ "[0] 취소");
				System.out.print("입력 > ");
				choiceSC = sc.nextLine();
				
				if (choiceSC.matches(REGEX)) {
					choice =  Integer.parseInt(choiceSC);
					if ( choice > 1 || choice < 0) {
						System.err.println("[오류] 주어진 선택지를 입력해주십시오.");
						continue;
					} else {
						break;
					}
				} else {
					System.err.println("[오류] 숫자를 입력해주십시오.");
					continue;
				}
			}
				
			deleteItemDAO(itemCode);
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void selectAllDisp(String itemCode) {
		ItemsDAO dao = new ItemsDAO();
		List<ItemsVO> list = dao.selectAll(itemCode);
		
		for (ItemsVO vo : list) {
			if (vo.getIntroduction() == null) {
				System.out.println(
						" 상품코드 : " + vo.getItemCode() + "\n" 
								+ " 원두코드 : " + vo.getBeanCode() + "\n" 
								+ " 상품명 : " + vo.getItemName() + "\n" 
								+ " 상품정보 : " + "없음" + "\n" 
								+ " 가격 : " + vo.getPrice() 
						);
			} else {
				System.out.println(
						" 상품코드 : " + vo.getItemCode() + "\n" 
								+ " 원두코드 : " + vo.getBeanCode() + "\n" 
								+ " 상품명 : " + vo.getItemName() + "\n" 
								+ " 상품정보 : " + vo.getIntroduction() + "\n" 
								+ " 가격 : " + vo.getPrice() 
						);
			}
		}
	}
	
	private static void dispItem() {
		while(true) {
			System.out.println();
			System.out.println("[상품 조회]\r\n"
					+ "[1] 개별 조회" + "\n"
					+ "[2] 전체 조회" + "\n"
					+ "[0] 종료");
			
			choiceSelect(2);
			
		if (choice == 0) {
			System.out.println();
			break;
		} else if (choice == 1) {
			dispItemCode();		
			System.out.println("[개별 조회] 종료");
		} else if (choice == 2) {
			dispAll();
			System.out.println("[전체 조회] 종료");
			}
		}
	}
	
	private static void dispItemCode() {
		while(true) {
			System.out.println();
			System.out.print("[상품 개별 조회]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 조회할 상품의 코드를 입력해주세요");
			itemCodeScan();
			
			System.out.println();
			System.out.println("[조회한 상품]");
			selectAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	
	private static void dispAll() {
		while(true) {
			System.out.println("[조회한 상품]");
			ItemsDAO dao = new ItemsDAO();
			List<ItemsVO> list = dao.selectAllItem();
			for (ItemsVO vo : list) {
				if (vo.getIntroduction() == null) {
					System.out.println(
							" 상품코드 : " + vo.getItemCode() + "\n" 
									+ " 원두코드 : " + vo.getBeanCode() + "\n" 
									+ " 상품명 : " + vo.getItemName() + "\n" 
									+ " 상품정보 : " + "없음" + "\n " 
									+ " 가격 : " + vo.getPrice() 
							);
					System.out.println();
				} else {
					System.out.println(
							" 상품코드 : " + vo.getItemCode() + "\n" 
									+ " 원두코드 : " + vo.getBeanCode() + "\n" 
									+ " 상품명 : " + vo.getItemName() + "\n" 
									+ " 상품정보 : " + vo.getIntroduction() + "\n" 
									+ " 가격 : " + vo.getPrice() 
							);				
					System.out.println();
				}
			}
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
			
		}
	}
	
	private static void dispItemsLog() {
		while(true) {
			System.out.println();
			System.out.println("[상품 수정 내역 조회]\r\n"
					+ "[1] 전체 조회" + "\n"
					+ "[2] 기간별 조회" + "\n"
					+ "[0] 종료");
			choiceSelect(2);
			
			if (choice == 0) {
				System.out.println();
				break;
			} if (choice == 1) {
				dispAllItemsLog();		
				System.out.println("[전체 조회] 종료");
			} if (choice == 2) {
				dispDateItemsLog();
				System.out.println("[기간별 조회] 종료");
			}
		}
	}
	
	private static void dispAllItemsLog() {
		while(true) {
			System.out.println("[상품 수정 내역]");
			ItemsDAO dao = new ItemsDAO();
			List<ItemsVO> list = dao.selectItemsLog();
			for (ItemsVO vo : list) {
				System.out.println(
					" 관리자 : " + selectNickname(vo.getManagerCode()) + "\n" 
					+ " 상품코드 : " + vo.getItemCode() + "\n" 
					+ " 원두코드 : " + vo.getBeanCode() + "\n" 
					+ " 상품명 : " + vo.getItemName() + "\n" 
					+ " 상품정보 : " + vo.getIntroduction() + "\n" 
					+ " 가격 : " + vo.getPrice() + "\n"
					+ " 타입 : " + vo.getModifyType() + "\n"
					+ " 수정일자 : " + vo.getModifyDate()
					);				
				System.out.println();
			}
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	public static void modifyDateEndScan() {
		while(true) {
			System.out.print("종료 일자(YYYYMMDD) : ");
			modifyDateEndSC = sc.nextLine();
			
			if (modifyDateEndSC.matches(REGEX)) {
				modifyDateEnd = Integer.parseInt(modifyDateEndSC);
				if ((int)(Math.log10(modifyDateEnd)+1 ) > 8 ||(int)(Math.log10(modifyDateEnd)+1) < 0) {
					System.err.println("[오류] 종료 일자 입력 범위를 벗어났습니다.");
					continue;
				} else {
					dateStr = modifyDateEndSC.substring(0, 5);
					date = Integer.parseInt(dateStr);
					if (date > 3000 || date < 2000) {
						System.err.println("[오류] 년도 입력 범위를 벗어났습니다.");
						continue;
					} else {
						dateStr = modifyDateEndSC.substring(5, 7);
						date = Integer.parseInt(dateStr);
						if (date > 12 || date < 1) {
							System.err.println("[오류] 월 입력 범위를 벗어났습니다.");
							continue;
						} else {
							dateStr = modifyDateEndSC.substring(7);
							date = Integer.parseInt(dateStr);
							switch (date) {
							case 1 : case 3 : case 5 :
							case 7 : case 8 : case 10 : case 12 :
								if (date > 31 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateEndScan();
								} 
								break;
							case 2 :
								if (date > 29 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateEndScan();
								} 
								break;
							case 4 : case 6 : case 9 : case 11 :
								if (date > 3 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateEndScan();
								} 
								break;
							default :
								break;
							}
						}
					}
				}
			} else {
				System.err.println("[오류] 8자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	public static void modifyDateStartScan() {
		while(true) {
			System.out.print("시작 일자(YYYYMMDD) : ");
			modifyDateStartSC = sc.nextLine();
			
			if (modifyDateStartSC.matches(REGEX)) {
				modifyDateStart = Integer.parseInt(modifyDateStartSC);
				if ((int)(Math.log10(modifyDateStart)+1 ) > 8 ||(int)(Math.log10(modifyDateStart)+1) < 0) {
					System.err.println("[오류] 시작 일자 입력 범위를 벗어났습니다.");
					continue;
				} else {
					dateStr = modifyDateStartSC.substring(0, 5);
					date = Integer.parseInt(dateStr);
					if (date > 3000 || date < 2000) {
						System.err.println("[오류] 년도 입력 범위를 벗어났습니다.");
						continue;
					} else {
						dateStr = modifyDateStartSC.substring(5, 7);
						date = Integer.parseInt(dateStr);
						if (date > 12 || date < 1) {
							System.err.println("[오류] 월 입력 범위를 벗어났습니다.");
							continue;
						} else {
							dateStr = modifyDateStartSC.substring(7);
							date = Integer.parseInt(dateStr);
							switch (date) {
							case 1 : case 3 : case 5 :
							case 7 : case 8 : case 10 : case 12 :
								if (date > 31 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateStartScan();
								} 
								break;
							case 2 :
								if (date > 29 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateStartScan();
								} 
								break;
							case 4 : case 6 : case 9 : case 11 :
								if (date > 3 || date < 1) {
									System.err.println("[오류] 일 입력 범위를 벗어났습니다.");
									modifyDateStartScan();
								} 
								break;
							default :
								break;
						}
					}
				}
			}
		} else {
			System.err.println("[오류] 8자리 내의 숫자를 입력해주십시오.");
			continue;
			}
		}
	}
	
	private static void dispDateItemsLog() {
		while(true) {
		
			System.out.println("[기간별 조회]");
				
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			
			System.out.println(">> 조회할 기간을 입력해주세요");
				
			modifyDateStartScan();
			modifyDateEndScan();
			
			System.out.println();
			System.out.println("[상품 수정 내역]");
			
			selectDateItemsLogDisp(modifyDateStart, modifyDateEnd);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
			
		}
	}
	
	private static void selectDateItemsLogDisp(int modifyDateStart, int modifyDateEnd) {
		ItemsDAO dao = new ItemsDAO();
		List<ItemsVO> list = dao.selectDateItemsLog(modifyDateStart, modifyDateEnd);
		
		for (ItemsVO vo : list) {
			System.out.println(
					" 관리자 : " + selectNickname(vo.getManagerCode()) + "\n" 
					+ " 상품코드 : " + vo.getItemCode() + "\n" 
					+ " 원두코드 : " + vo.getBeanCode() + "\n" 
					+ " 상품명 : " + vo.getItemName() + "\n" 
					+ " 상품정보 : " + vo.getIntroduction() + "\n" 
					+ " 가격 : " + vo.getPrice() + "\n"
					+ " 타입 : " + vo.getModifyType() + "\n"
					+ " 수정일자 : " + vo.getModifyDate()
					);				
				System.out.println();
		}
	}
	
	private static void beansDisp() {
		ItemsDAO dao = new ItemsDAO();
		List<ItemsVO> list = dao.selectBeans();
		
		for (ItemsVO vo : list) {
			System.out.println(
					" 원두코드 : " + vo.getBeanCode() + "\n" 
							+ " 원두명 : " + vo.getBeanName() + "\n" 
							+ " 원산지 : " + vo.getCoo() + "\n" 
							+ " 설명 : " + vo.getDescription()
					);				
			System.out.println();
		}
	}

	
}