package com.mystudy.house.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryDetailVO;
import com.mystudy.house.vo.CategoryVO;
import com.mystudy.house.vo.DeleteStatusVO;
import com.mystudy.house.vo.ProductInsertImgVO;
import com.mystudy.house.vo.ProductInsertVO;
import com.mystudy.house.vo.ProductListVO;

public class ProductDeleteConfirmCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		//String pw = (String) session.getAttribute("pw");
		String id = "800do";
		String pw = "1234";
	
		String userPw = request.getParameter("userPw");
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		System.out.println(productNum);
		String tf = "";
		if (pw.equals(userPw)) {
			tf = "true";
			DeleteStatusVO vo = new DeleteStatusVO("삭제", productNum);
			PartnercenterDAO.getProductDelete(vo);
		} else {
			tf = "false";
		}
		StringBuilder result = new StringBuilder();
		result.append("{\"result\" : " + tf + "}");			
		String confirm = result.toString();
		
		return confirm;
	}

}

