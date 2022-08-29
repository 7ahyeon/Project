package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import item.ItemsVO;
import project.CommonJdbcUtil;

public class OrderDAO {

	// 수정함/
		private static final String DRIVER = "oracle.jdbc.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@192.168.18.24:1521:xe";
		private static final String USER = "siren";
		private static final String PASSWORD = "siren";

		
		private static Connection conn = CommonJdbcUtil.getConnection();
		private static PreparedStatement pstmt;
		private static ResultSet rs;
		
		
		
//		public static Connection getConnection() throws SQLException {
//			return DriverManager.getConnection(URL, USER, PASSWORD);
//		}
		
		
//		static {
//			try {
//				Class.forName(DRIVER);
//			} catch (ClassNotFoundException e) {
//				System.out.println("[예외발생-OrderDAO] JDBC 드라이버 로딩 실패!!!");
//				e.printStackTrace();
//			}
//		}
//	
//	public static List<OrderVO> selectDateOrderLog(int modifyDateStart, int modifyDateEnd) {
//		List<ItemsVO> list = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("(SELECT ITEM_NAME, PRICE FROM ITEMS WHERE ITEM_CODE = ?),  ");
//			sql.append("FROM ITEMS_LOG ");
//			sql.append("WHERE MODIFYDATE > TO_DATE(?, 'YYYYMMDD') ");
//			sql.append("AND MODIFYDATE <= TO_DATE(?||' 235959', 'YYYYMMDD HH24MISS') ");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, managerCode);
//			pstmt.setInt(2, modifyDateStart);
//			pstmt.setInt(3, modifyDateEnd);
//			rs = pstmt.executeQuery();
//			
//			list = new ArrayList<ItemsVO>();
//			while (rs.next()) {
//				ItemsVO vo = new ItemsVO(
//						rs.getString("NICKNAME"), 
//						rs.getString("ITEM_CODE"), 
//						rs.getString("BEAN_CODE"), 
//						rs.getString("ITEM_NAME"), 
//						rs.getString("INTRODUCTION"), 
//						rs.getInt("PRICE"),
//						rs.getString("MODIFY_TYPE"), 
//						rs.getString("MODYFYDATE"));
//				list.add(vo);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	public static ArrayList<String> getItemVOByItemCode (String itemCode) { /// 이거 나중에 리스트가 아니라 걍 ItemsVO 객체로 받는거로 수정해 
		
//		ItemsVO vo = null;
		ArrayList<String> list = new ArrayList<>();
		StringBuilder sql = null;
		
		
		try {
			sql = new StringBuilder();
			sql.append("SELECT * FROM ITEMS ");
			sql.append("WHERE ITEM_CODE = ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			
			pstmt.setString(1, itemCode);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
//				vo = new ItemsVO(rs.getString("ITEM_CODE"), rs.getString("ITEM_NAME"), rs.getString("INTRODUCTION"), rs.getInt("PRICE"));
				list.add(rs.getString("ITEM_CODE"));
				list.add(rs.getString("ITEM_NAME"));
				list.add(rs.getString("INTRODUCTION"));
				list.add(String.valueOf(rs.getInt("PRICE")));
				
				
			} else System.err.println("OrderDAO.getItemVOByItemCode_ 해당하는 아이템을 찾을 수 없습니다.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("리스트 생성오류");
		}
		return list;
	}
	
	
	
	
	
	public static ArrayList<String>[] getItemVOByCategory (String category) {

		ArrayList<String>[] arr = null;
				
		try {
			
//			conn = getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM ITEMS ");
			sql.append("	WHERE ITEM_CODE LIKE ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			
			pstmt.setString(1, "%"+category+"%");
			rs = pstmt.executeQuery();
			
			rs.next(); 
			Integer listLength = rs.getInt(1); 
			
			arr = new ArrayList[listLength]; 
			
			for(int i = 0; i < arr.length; i++) {
				arr[i] = new ArrayList<>();
			}
			
			
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM ITEMS ");
			sql.append("	WHERE ITEM_CODE LIKE ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			
			pstmt.setString(1, "%"+category+"%");
			
			rs = pstmt.executeQuery();
			
			
			for (int i = 0; i < arr.length; i++) {
				
				rs.next();
				arr[i].add(rs.getString("ITEM_CODE"));
				arr[i].add(rs.getString("ITEM_NAME"));
				arr[i].add(rs.getString("INTRODUCTION"));
				arr[i].add(rs.getString("PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	
	public static ArrayList<String>[] getBeanVOs () {
		
		ArrayList<String>[] arr = null;
		
		while (true) {
			
			try {
				
//				conn = getConnection();
				
				StringBuilder selectBean = new StringBuilder();
				selectBean.append("SELECT COUNT(*) FROM BEANS ");
				pstmt = conn.prepareStatement(String.valueOf(selectBean));
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				
				rs.next();
				int countBean = rs.getInt(1);
				
				selectBean = new StringBuilder();
				selectBean.append("SELECT * FROM BEANS ");
				pstmt = conn.prepareStatement(String.valueOf(selectBean));
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				
				arr = new ArrayList[countBean -1];
				
				
				for (int i = 0; i < arr.length; i++) {
					
					rs.next();
					arr[i] = new ArrayList<>();
					arr[i].add(rs.getString("BEAN_CODE"));
					arr[i].add(rs.getString("BEAN_NAME"));
					arr[i].add(rs.getString("COO"));
					arr[i].add(rs.getString("DESCRIPTION"));
				}
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return arr;
			
		}
	}
	
	public static int insertOrders (String custCode, int totalPrice) {
		
		int orderCode = 0;
		
		try {
			
			StringBuilder sql = null;
			
			if (custCode.contains("NON")) {
				
				sql = new StringBuilder();
				sql.append("INSERT INTO ORDERS (ORDER_CODE, NON_CUST_CODE, TOTAL_PRICE) ");
				sql.append("VALUES (ORDERSEQ.NEXTVAL, ?, ?) ");
				pstmt = conn.prepareStatement(String.valueOf(sql));
				pstmt.setString(1, custCode);
				pstmt.setInt(2, totalPrice);
				
			} else {
				
				sql = new StringBuilder();
				sql.append("INSERT INTO ORDERS (ORDER_CODE, CUST_CODE, TOTAL_PRICE) ");
				sql.append("VALUES (ORDERSEQ.NEXTVAL, ?, ?) ");
				pstmt = conn.prepareStatement(String.valueOf(sql));
				pstmt.setString(1, custCode);
				pstmt.setInt(2, totalPrice);
			}

				
			pstmt.executeUpdate(); // 처음부터 orderseq.nextval 안쓰고 중간에 쓰면 오류남
			
			
			if (custCode.contains("NON")) {
				
				sql = new StringBuilder();
				sql.append("SELECT ORDER_CODE FROM ORDERS ");
				sql.append("WHERE ORDER_CODE = (SELECT MAX(NVL(ORDER_CODE, 0)) FROM ORDERS WHERE NON_CUST_CODE = ?) ");
				pstmt = conn.prepareStatement(String.valueOf(sql));
				pstmt.setString(1, custCode);
			
			} else {
				
				sql = new StringBuilder();
				sql.append("SELECT ORDER_CODE FROM ORDERS ");
				sql.append("WHERE ORDER_CODE = (SELECT MAX(NVL(ORDER_CODE, 0)) FROM ORDERS WHERE CUST_CODE = ?) ");
				pstmt = conn.prepareStatement(String.valueOf(sql));
				pstmt.setString(1, custCode);
				
			}
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				orderCode = rs.getInt("ORDER_CODE");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderCode;
	}
	
	
	public static String createNonCustCode () {
		
		String nonCustCode = null;
		
		try {
			
			String sql = "SELECT 'NON' || NON_CUSTSEQ.NEXTVAL FROM DUAL ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				nonCustCode = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nonCustCode;
	}
	
	public static void insertOrders (String custCode) {
		
		String orderCode = null;
		
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ORDERS ");
			sql.append("VALUES (ORDERSEQ.NEXTVAL, ?) ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, custCode);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int insertOptions (String category, ArrayList<String> itemVO, OrderVO order, String custCode, int orderCode) {
		
		String beanCode = null;
		int optionCode = -1;
			
		try {
			
			StringBuilder sql = null;
			
//			if (category.equals("COF")) {
				
				if (custCode.contains("NON")) {
					
					
					sql = new StringBuilder();
					sql.append("INSERT INTO OPTIONS ");
					sql.append("VALUES (OPTIONSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
					pstmt = conn.prepareStatement(String.valueOf(sql));
					
					pstmt.setString(1, itemVO.get(0));
					pstmt.setString(2, null);
					pstmt.setInt(3, orderCode);
					pstmt.setString(4, order.getCupSize());
					pstmt.setString(5, order.getCupSelect());
					pstmt.setString(6, order.getHotIce());
					pstmt.setString(7, order.getIce());
					pstmt.setString(8, order.getEspresso());
					pstmt.setInt(9, order.getAddShot());
					pstmt.setString(10, order.getSyrup());
					pstmt.setInt(11, order.getDrinkAmount());
					pstmt.setString(12, order.getMilk());
					pstmt.setInt(13, order.getItemPrice());
					pstmt.setString(14, order.getBean());
					pstmt.setString(15, order.getCategory());
					pstmt.setString(16, custCode);
					
					int result = pstmt.executeUpdate();
					
					
				} else {
					
					sql = new StringBuilder();
					sql.append("INSERT INTO OPTIONS ");
					sql.append("VALUES (OPTIONSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
					pstmt = conn.prepareStatement(String.valueOf(sql));
					
					pstmt.setString(1, itemVO.get(0));
					pstmt.setString(2, custCode);
					pstmt.setInt(3, orderCode);
					pstmt.setString(4, order.getCupSize());
					pstmt.setString(5, order.getCupSelect());
					pstmt.setString(6, order.getHotIce());
					pstmt.setString(7, order.getIce());
					pstmt.setString(8, order.getEspresso());
					pstmt.setInt(9, order.getAddShot());
					pstmt.setString(10, order.getSyrup());
					pstmt.setInt(11, order.getDrinkAmount());
					pstmt.setString(12, order.getMilk());
					pstmt.setInt(13, order.getItemPrice());
					pstmt.setString(14, order.getBean());
					pstmt.setString(15, order.getCategory());
					pstmt.setString(16, null);
					
					int result = pstmt.executeUpdate();
					
				
					sql = new StringBuilder();
					sql.append("SELECT MAX(OPTION_CODE) FROM OPTIONS WHERE CUST_CODE = ? "); // 근데 OPTION.NEXTVAL가 최초 1회 실행되어야 정상적으로 진행됨
					
					pstmt = conn.prepareStatement(String.valueOf(sql));
					pstmt.setString(1, custCode);
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						optionCode = rs.getInt(1);
					} else System.err.println("OrderDAO.insertOptions에서 옵션코드 시퀀스 조회 불가 없음");
			
				}
//			}
			
			if (category.equals("TEA")) {
				
			}
			if (category.equals("FRA")) {
				
			}
			if (category.equals("AID")) {
				
			}
			if (category.equals("ETC")) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return optionCode;
	}
	
	
	
	public static void insertFavorite (OrderVO order, String custCode, int optionCode, String name) {
		
		
		try {
			
			System.out.println("즐거찾기에 등록할 옵션코드 : " + optionCode);
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO FAVORITE ");
			sql.append("VALUES (FAVORITESEQ.NEXTVAL, ?, ?, ? ) ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			
			pstmt.setString(1, custCode);
			pstmt.setInt(2, optionCode);
			pstmt.setString(3, name);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public static ArrayList<FavoriteVO> getFavorites(String custCode) {
		ArrayList <FavoriteVO> list = null;
		StringBuilder sql = null;
		FavoriteVO vo;
		
		try {
			
			
			list = new ArrayList<>();
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM FAVORITE ");
			sql.append("WHERE CUST_CODE = ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));	
			pstmt.setString(1, custCode);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
					
				vo = new FavoriteVO();
				vo.setCustCode(rs.getString("CUST_CODE"));
				vo.setFavoriteCode(rs.getInt("FAVORITE_CODE"));
				vo.setFavoriteName(rs.getString("FAVORITE_NAME"));
				vo.setOptionCode(rs.getInt("OPTION_CODE"));
				
				list.add(vo);
				
			}
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}



	public static OrderVO getOrder(int optionCode) {
		
		StringBuilder sql = null;
		OrderVO vo = null;
		
		try {
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM OPTIONS ");
			sql.append("WHERE OPTION_CODE = ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setInt(1, optionCode);
			rs = pstmt.executeQuery();
			
			ArrayList<String> list = null;
			String itemCode = null;
			if (rs.next()) { // 데이터베이스에 두번 접속하면 안됨... 만약 이 IF 문 내에서 다른 메소드로 데이터베이스에 또 접근하려하면 오류남.. 이 오류때문에 3시간 고민.. 오늘을 잊지말자
				
				
				itemCode = rs.getString("ITEM_CODE");
				
				vo = new OrderVO(rs.getString("CATEGORY"), rs.getInt("OPTION_CODE"), rs.getString("ITEM_CODE"), rs.getString("BEAN_NAME"), rs.getString("CUP_SIZE"), rs.getString("CUP_SELECT"), 
						rs.getString("HOT_ICE"), rs.getString("ESPRESSO"), rs.getInt("ADD_SHOT"), rs.getString("MILK"), rs.getString("SYRUP"), rs.getInt("DRINK_AMOUNT")
						, rs.getString("ICE"), rs.getInt("ITEM_PRICE"), list);
			
			} else {
				System.err.println("OrderDAO.getOrder_ 불러올 옵션정보가 없습니다.");
			}
			
			list = getItemVOByItemCode(itemCode);
			vo.setChoice(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오류났다");
		}
		
		return vo;
	}
	
	
	
	public static ArrayList<OrderVO> getOptionsAll(String custCode) {
		
		StringBuilder sql = null;
		ArrayList<OrderVO> list = new ArrayList<>();
		OrderVO vo = null;
		
		try {
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM OPTIONS ");
			sql.append("WHERE CUST_CODE = ? ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, custCode);
			rs = pstmt.executeQuery();
			
			ArrayList<String> itemList = new ArrayList<>();
			ArrayList<String> itemCodeList = new ArrayList<>();
			
			while (rs.next()) {
				
				vo = new OrderVO(rs.getString("CATEGORY"), rs.getInt("OPTION_CODE"), rs.getString("ITEM_CODE"), rs.getString("BEAN_NAME"), rs.getString("CUP_SIZE"), rs.getString("CUP_SELECT"), 
										rs.getString("HOT_ICE"), rs.getString("ESPRESSO"), rs.getInt("ADD_SHOT"), rs.getString("MILK"), rs.getString("SYRUP"), rs.getInt("CUP_AMOUNT")
										, rs.getString("ICE"), rs.getInt("ITEM_PRICE"), itemList);
				itemCodeList.add(rs.getString("ITEM_CODE"));
				list.add(vo);
			
			}
			
			for (int i = 0; i < itemCodeList.size(); i++) {
				list.get(i).setChoice(getItemVOByItemCode(itemCodeList.get(i)));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("내가 바로 오류다");
		}
		
		return list;
	}





	public static ArrayList<TestOrderVO> getOrdersAll(String custCode) {
		
		StringBuilder sql = null;
		ArrayList<TestOrderVO> list = new ArrayList<>();
		TestOrderVO vo = null;
		
		try {
			
			sql = new StringBuilder();
			sql.append("SELECT ORDER_CODE, CUST_CODE, TOTAL_PRICE, TO_CHAR(ORDER_DATE, 'YYYY MM DD HH24 MI SS') AS \"ORDER_DATE\" FROM ORDERS ");
			sql.append("WHERE CUST_CODE = ? ");
			sql.append("ORDER BY ORDER_DATE ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, custCode);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				
				vo = new TestOrderVO(rs.getInt("ORDER_CODE"), rs.getString("CUST_CODE"), rs.getInt("TOTAL_PRICE"), rs.getString("ORDER_DATE"));
				list.add(vo);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("내가 바로 오류다");
		}
		
		return list;
	}





	public static ArrayList<OrderVO> getOptionsByOrderCode(int orderCode) {
		
		StringBuilder sql = null;
		ArrayList<OrderVO> list = new ArrayList<>();
		OrderVO vo = null;
		
		try {
			
			sql = new StringBuilder();
			sql.append("SELECT * FROM OPTIONS ");
			sql.append("WHERE ORDER_CODE = ? ");
			sql.append("ORDER BY ORDER_CODE ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setInt(1, orderCode);
			rs = pstmt.executeQuery();
			
			ArrayList<String> itemList = new ArrayList<>();
			ArrayList<String> itemCodeList = new ArrayList<>();
			
			while (rs.next()) {
				
				vo = new OrderVO(rs.getString("CATEGORY"), rs.getInt("OPTION_CODE"), rs.getString("ITEM_CODE"), rs.getString("BEAN_NAME"), rs.getString("CUP_SIZE"), rs.getString("CUP_SELECT"), 
										rs.getString("HOT_ICE"), rs.getString("ESPRESSO"), rs.getInt("ADD_SHOT"), rs.getString("MILK"), rs.getString("SYRUP"), rs.getInt("DRINK_AMOUNT")
										, rs.getString("ICE"), rs.getInt("ITEM_PRICE"), itemList);
				itemCodeList.add(rs.getString("ITEM_CODE"));
				list.add(vo);
			
			}
			
			for (int i = 0; i < itemCodeList.size(); i++) {
				list.get(i).setChoice(getItemVOByItemCode(itemCodeList.get(i)));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("내가 바로 오류다");
		}
		
		return list;
		
	}





	public static void insertNonCustomer(String custCode, int orderCode, String phoneNum) {
		
		StringBuilder sql = null;
		
		try {
			
			sql = new StringBuilder();
			sql.append("INSERT INTO NON_CUSTOMERS ");
			sql.append("VALUES (?, ?, ?) ");
			pstmt = conn.prepareStatement(String.valueOf(sql));
			pstmt.setString(1, custCode);
			pstmt.setInt(2, orderCode);
			pstmt.setString(3, phoneNum);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


}

