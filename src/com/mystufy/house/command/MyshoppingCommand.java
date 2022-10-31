package com.mystufy.house.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mystudy.house.dao.MygradeDAO;
import com.mystudy.house.dao.MyorderlistDAO;
import com.mystudy.house.dao.MypointDAO;
import com.mystudy.house.vo.MyidperiodVO;
import com.mystudy.house.vo.MyorderlistVO;

public class MyshoppingCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 등급
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "1";
		String today = new java.text.SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
		
		Calendar mon = Calendar.getInstance();
		mon.add(Calendar.MONTH , -3);
		String threeMonthAgo = new java.text.SimpleDateFormat("yyyy.MM.dd").format(mon.getTime());
		
		MyidperiodVO vo = new MyidperiodVO(threeMonthAgo, today, id);
		int threeMonthOrderTimes = MygradeDAO.getThreeMonthOrderTimes(vo);
		request.setAttribute("threeMonthOrderTimes", threeMonthOrderTimes);

		int threeMonthTotPrice = MygradeDAO.getThreeMonthTotPrice(vo);
		request.setAttribute("threeMonthTotPrice", threeMonthTotPrice);

		String myGrade = MygradeDAO.getGrade(id);
		request.setAttribute("myGrade", myGrade);
		
		int myPoint = MypointDAO.getMyPoint(id);
		System.out.println(myPoint);
		request.setAttribute("myPoint", myPoint);
		
		// 주문 목록
		
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE , -7);
		String ago = new java.text.SimpleDateFormat("yyyy.MM.dd").format(week.getTime());
		
		
		MyidperiodVO mainOrderVo = new MyidperiodVO(ago, today, id);
		List<MyorderlistVO> orderList = MyorderlistDAO.getoOrderList(mainOrderVo);
		
		request.setAttribute("orderList", orderList);
		
		return "/WEB-INF/myshopping/myshopping.jsp";
	}
}
