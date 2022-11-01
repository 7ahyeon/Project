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
import com.mystudy.house.vo.ProductInsertImgVO;
import com.mystudy.house.vo.ProductInsertVO;
import com.mystudy.house.vo.ProductListVO;

public class ProductDeleteCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		//String pw = (String) session.getAttribute("pw");
		String id = "800do";
		String pw = "1234";
		
		List<CategoryVO> categoryList = PartnercenterDAO.getCategory();
		request.setAttribute("categoryList", categoryList);
		List<ProductListVO> productList = PartnercenterDAO.getProductListAll(id);
		request.setAttribute("productList", productList);
		int cnt = PartnercenterDAO.getProductListCount(id);
		request.setAttribute("cnt", cnt);
		return "/WEB-INF/partnercenter/productDelete.jsp";
	}

}

