package com.mystufy.house.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryVO;
import com.mystudy.house.vo.ProductListVO;

public class StockManagementCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";

		List<CategoryVO> categoryList = PartnercenterDAO.getCategory();
		request.setAttribute("categoryList", categoryList);
		List<ProductListVO> productList = PartnercenterDAO.getProductListAll(id);
		request.setAttribute("productList", productList);
		
		return "/WEB-INF/partnercenter/stockManagement.jsp";
	}
	
}

