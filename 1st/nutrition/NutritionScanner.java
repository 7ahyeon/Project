package nutrition;

import java.util.List;
import java.util.Scanner;

import item.ItemsDAO;
import item.ItemsScanner;


public class NutritionScanner extends NutritionDAO{

	static Scanner sc = new Scanner(System.in);
	final static String REGEX = "[0-9]+";

	private static String choiceSC;
	private static int choice = 10;

	private static String exitSC;
	private static int exit = 2;

	private static String itemCode;
	private static String updateItemCode;
	private static String scan;
	
	private static int kcal;
	private static int sodium;
	private static int sugar;
	private static int protein;
	private static int caffeine;
	private static int cholesterol;

	private static int result;
	
	public static void nutritionManager() {
		while(true) {
			System.out.println();
			System.out.println(">> 원하시는 작업을 선택해주세요" + "\n"
					+ "[1] 영양정보 등록" + "\n"
					+ "[2] 영양정보 수정" + "\n"
					+ "[3] 영양정보 삭제" + "\n"
					+ "[4] 영양정보 조회" + "\n"
					+ "[0] 뒤로 가기"); 
			
			choiceSelect(4);
		
			if(choice == 0) {
				System.out.println();
				System.out.println(">> [영양 정보] 종료");
				System.out.println();
				break;
			} if (choice == 1) {
				insertNutrition();		
				continue;
			} if (choice == 2) {
				updateNutrition();
				System.out.println();
				System.out.println(">> [영양정보 수정] 종료");
				System.out.println();
				continue;
			} if (choice == 3) {
				deleteNutrition();
				System.out.println();
				System.out.println(">> [영양정보 삭제] 종료");
				System.out.println();
				continue;
			} if (choice == 4) {
				dispNutrition();
				System.out.println();
				System.out.println(">> [상품 조회] 종료");
				System.out.println();
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
					+ "[1] 재진행" + "\n" 
					+ "[0] 종료");
			System.out.print("입력 > ");
			exitSC = sc.nextLine();
			
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
			
			if(ItemsDAO.selectCode(itemCode) == null) {
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
	
	
	//영양정보 등록
	private static void insertNutrition() {
		while(true) {
			System.out.println();
			System.out.println("[영양 정보 등록]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 등록할 상품 정보를 차례대로 입력해주세요");
			itemCodeScan();
			insertKcal();
			insertSodium();
			insertCholesterol();
			insertSugar();
			insertProtein();
			insertCaffeine();
			
			insertNutritionDAO(itemCode, kcal, sodium, cholesterol, sugar, protein, caffeine);
			System.out.println();
			System.out.println("[등록된 영양 정보]");
			selectNutritionAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void insertKcal() {
		while(true) {
			System.out.print("칼로리 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				kcal = Integer.parseInt(scan);
				if ((int)(Math.log10(kcal)+1 ) > 3 ||(int)(Math.log10(kcal)+1) < 0) {
					System.err.println("[오류] 칼로리 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	private static void insertSodium() {
		while(true) {
			System.out.print("나트륨 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				sodium = Integer.parseInt(scan);
				if ((int)(Math.log10(sodium)+1 ) > 3 ||(int)(Math.log10(sodium)+1) < 0) {
					System.err.println("[오류] 나트륨 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	private static void insertCholesterol() {
		while(true) {
			System.out.print("포화지방 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				cholesterol = Integer.parseInt(scan);
				if ((int)(Math.log10(cholesterol)+1 ) > 3 ||(int)(Math.log10(cholesterol)+1) < 0) {
					System.err.println("[오류] 포화지방 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	private static void insertSugar() {
		while(true) {
			System.out.print("당류 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				sugar = Integer.parseInt(scan);
				if ((int)(Math.log10(sugar)+1 ) > 3 ||(int)(Math.log10(sugar)+1) < 0) {
					System.err.println("[오류] 당류 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}

	private static void insertProtein() {
		while(true) {
			System.out.print("단백질 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				protein = Integer.parseInt(scan);
				if ((int)(Math.log10(protein)+1 ) > 3 ||(int)(Math.log10(protein)+1) < 0) {
					System.err.println("[오류] 단백질 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	private static void insertCaffeine() {
		while(true) {
			System.out.print("카페인 : ");
			scan = sc.nextLine();
			
			if (scan.matches(REGEX)) {
				caffeine = Integer.parseInt(scan);
				if ((int)(Math.log10(caffeine)+1 ) > 3 ||(int)(Math.log10(caffeine)+1) < 0) {
					System.err.println("[오류] 카페인 입력 범위를 벗어났습니다.");
					continue;
				} else {
					break;
				}
			} else {
				System.err.println("[오류] 3자리 내의 숫자를 입력해주십시오.");
				continue;
			}
		}
	}
	
	private static void updateNutrition() {
		while(true) {
			System.out.println();
			System.out.print("[영양정보 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 수정할 상품코드를 입력해주세요");
			itemCodeScan();
			
			System.out.println();
			System.out.println("[수정할 영양 정보]");
			selectNutritionAllDisp(itemCode);
			updateNutritionChoice(itemCode);
			break;
		}
	}
	
	private static void updateNutritionAll(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[전체 영양정보 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}

			insertKcal();
			insertSodium();
			insertCholesterol();
			insertSugar();
			insertProtein();
			insertCaffeine();
				
			updateNutritionDAO(itemCode, kcal, sodium, cholesterol, sugar, protein, caffeine);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(updateItemCode);
			
			break;
			
		}
	}
	//영양 정보 수정
	private static void updateNutritionChoice(String itemCode) {
		while(true) {
			System.out.println(">> 수정할 정보를 선택해주세요\r\n"
					+ "[1] 칼로리" + "\n"
					+ "[2] 나트륨 " + "\n"
					+ "[3] 포화지방"  + "\n"
					+ "[4] 당류"   + "\n"
					+ "[5] 단백질" + "\n"
					+ "[6] 카페인" + "\n"
					+ "[7] 전체 수정" + "\n"
					+ "[0] 종료");
			choiceSelect(7);
			
			if (choice == 7) {
				updateNutritionAll(itemCode);
			} if (choice == 1) {
				updateNutritionKcal(itemCode);
			} if (choice == 2) {
				updateNutritionSodium(itemCode);
			} if (choice == 3) {
				updateNutritionCholesterol(itemCode);
			} if (choice == 4) {
				updateNutritionSugar(itemCode);
			} if (choice == 5) {
				updateNutritionProtein(itemCode);
			} if (choice == 6) {
				updateNutritionCaffeine(itemCode);
			} if (choice == 0) {
				break;
			}
		}
	}

	private static void updateNutritionKcal(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[칼로리 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertKcal();
			updateNutritionKcalDAO(itemCode, kcal);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}

	private static void updateNutritionSodium(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[나트륨 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertSodium();
			updateNutritionSodiumDAO(itemCode, sodium);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}
	
	private static void updateNutritionCholesterol(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[포화지방 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertCholesterol();
			updateNutritionCholesterolDAO(itemCode, cholesterol);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}
	
	private static void updateNutritionSugar(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[당류 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertSugar();
			updateNutritionSugarDAO(itemCode, sugar);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}
	
	private static void updateNutritionProtein(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[단백질 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertProtein();
			updateNutritionProteinDAO(itemCode, protein);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}

	private static void updateNutritionCaffeine(String itemCode) {
		while(true) {
			System.out.println();
			System.out.print("[카페인 수정]");
			
			exitSelect();
			if (exit == 0) {
				break;
			}
			insertCaffeine();
			updateNutritionCaffeineDAO(itemCode, caffeine);
			System.out.println();
			System.out.println("[수정한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			break;
		}
	}
	
	private static void deleteNutrition() {
		while(true) {
			System.out.println();
			System.out.print("[상품 삭제]");
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
			
			System.out.println(">> 삭제할 상품코드를 입력해주세요");
			itemCodeScan();
			
			System.out.println("[선택한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			
			while(true) {
				System.out.println(itemCode + "를 삭제하시겠습니까?\r\n"
						+ "[1] 삭제" + "\n"
						+ "[0] 취소");
				System.out.print("입력 > ");
				exitSC = sc.nextLine();
				
				if (exitSC.matches(REGEX)) {
					exit =  Integer.parseInt(exitSC);
					if ( exit > 1 || exit < 0) {
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
			
			deleteNutritionDAO(itemCode);

			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void selectNutritionAllDisp(String itemCode) {
		NutritionDAO dao = new NutritionDAO();
		List<NutritionVO> list = dao.selectNutritionAll(itemCode);
		
		for (NutritionVO vo : list) {
			System.out.println(
					"상품코드 : " + vo.getItemCode() + "\n " 
							+ " 칼로리 : " + vo.getKcal() + "\n " 
							+ " 나트륨 : " + vo.getSodium() + "\n " 
							+ " 포화지방 : " + vo.getCholesterol() + "\n " 
							+ " 당류 : " + vo.getSugar() + "\n " 
							+ " 단백질 : " + vo.getProtein() + "\n " 
							+ " 카페인 : " + vo.getCaffeine() 
					);
			}
		}
	private static void selectNutritionAllDisp() {
		NutritionDAO dao = new NutritionDAO();
		List<NutritionVO> list = dao.selectNutritionAll();
		
		for (NutritionVO vo : list) {
			System.out.println(
					"상품코드 : " + vo.getItemCode() + "\n " 
							+ " 칼로리 : " + vo.getKcal() + "\n " 
							+ " 나트륨 : " + vo.getSodium() + "\n " 
							+ " 포화지방 : " + vo.getCholesterol() + "\n " 
							+ " 당류 : " + vo.getSugar() + "\n " 
							+ " 단백질 : " + vo.getProtein() + "\n " 
							+ " 카페인 : " + vo.getCaffeine() 
					);
		}
	}
	
	
	private static void dispNutrition() {
		while(true) {
			System.out.println();
			System.out.println("[영양정보 조회]\r\n"
					+ "[1] 개별 조회" + "\n"
					+ "[2] 전체 조회" + "\n"
					+ "[0] 종료");
			choiceSelect(2);
			if (choice == 0) {
				break;
			} if (choice == 1) {
				dispNutritionItemCode();	
				System.out.println();
				System.out.println("[개별 조회] 종료");
				System.out.println();
			} if (choice == 2) {
				dispNutritionAll();
				System.out.println();
				System.out.println("[전체 조회] 종료");
				System.out.println();
			}
		}
	}
	
	private static void dispNutritionItemCode() {
		while(true) {
			System.out.println(">> 조회할 영양 정보의 코드를 입력해주세요");
			itemCodeScan();
			
			System.out.println();
			System.out.println("[조회한 영양 정보]");
			selectNutritionAllDisp(itemCode);
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
	private static void dispNutritionAll() {
		while(true) {
			System.out.println("[조회한 영양 정보]");
			selectNutritionAllDisp();
			
			exitSelectAgain();
			if (exit == 0) {
				break;
			}
		}
	}
	
}
