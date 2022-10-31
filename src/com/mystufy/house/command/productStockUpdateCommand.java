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
import com.mystudy.house.vo.ProductUpdatestockVO;

public class productStockUpdateCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";

		int productNum = Integer.parseInt(request.getParameter("productNum"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		System.out.println(productNum);
		ProductUpdatestockVO vo = new ProductUpdatestockVO(stock, productNum);
		PartnercenterDAO.getProductUpdateStock(vo);
		int cnt = PartnercenterDAO.getProductListCount(id);
		request.setAttribute("cnt", cnt);
		return "/WEB-INF/partnercenter/stockManagement.jsp";
	}
	
}

