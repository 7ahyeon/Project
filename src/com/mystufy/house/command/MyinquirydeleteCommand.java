package com.mystufy.house.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.house.dao.MyinquiryDAO;

public class MyinquirydeleteCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int proinIdx = Integer.parseInt(request.getParameter("proinIdx"));
		MyinquiryDAO.deleteInquiryCom(proinIdx);
		PrintWriter out = response.getWriter();
		out.println("<html><form name='frm' action='myinquiry.do' method='post'>" );
		out.println("</form></html>");
		out.print("<script>frm.submit();</script>");
		out.close();
		
		return null;
	}

}

