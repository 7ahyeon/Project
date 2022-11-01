package com.mystudy.house.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mystudy.house.dao.MyinquiryDAO;
import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryVO;
import com.mystudy.house.vo.MyinquiryVO;
import com.mystudy.house.vo.MyinquirycommentVO;
import com.mystudy.house.vo.ProductListVO;

public class ProductUpdateListCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";
		List<ProductListVO> productList = PartnercenterDAO.getProductListAll(id);
		request.setAttribute("productList", productList);
		int cnt = PartnercenterDAO.getProductListCount(id);
		request.setAttribute("cnt", cnt);
		return "/WEB-INF/partnercenter/productUpdate.jsp";
	}
}
