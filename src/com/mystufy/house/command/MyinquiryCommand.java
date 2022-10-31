package com.mystufy.house.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mystudy.house.dao.MyinquiryDAO;
import com.mystudy.house.vo.MyinquiryVO;
import com.mystudy.house.vo.MyinquirycommentVO;

public class MyinquiryCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		
		String id = "1";
		List<MyinquiryVO> inquiryList = MyinquiryDAO.getInquiryList(id);
		request.setAttribute("inquiryList", inquiryList);
		
		List<MyinquirycommentVO> inquiryComList = new ArrayList();
		for (int i = 0; i < inquiryList.size(); i++) {
			int proinIdx = inquiryList.get(i).getProinIdx();
			inquiryComList.add(MyinquiryDAO.getInquiryComList(proinIdx));
		}
		request.setAttribute("inquiryComList", inquiryComList);
		
		
		return "/WEB-INF/myshopping/myinquiry.jsp";
	}
}
