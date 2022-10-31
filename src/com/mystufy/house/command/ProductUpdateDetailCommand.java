package com.mystufy.house.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryVO;
import com.mystudy.house.vo.ProductUpdateListVO;

public class ProductUpdateDetailCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		ProductUpdateListVO vo = PartnercenterDAO.getProductUpdateList(productNum);
		request.setAttribute("productUpdateList", vo);
		int cnt = PartnercenterDAO.getProductListCount(id);
		request.setAttribute("cnt", cnt);
		List<CategoryVO> categoryList = PartnercenterDAO.getCategory();
		request.setAttribute("categoryList", categoryList);
		return "/WEB-INF/partnercenter/productUpdateDetail.jsp";
	}
}
