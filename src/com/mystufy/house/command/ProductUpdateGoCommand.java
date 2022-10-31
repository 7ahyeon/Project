package com.mystufy.house.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryDetailVO;
import com.mystudy.house.vo.ProductInsertImgVO;
import com.mystudy.house.vo.ProductInsertVO;
import com.mystudy.house.vo.ProductUpdateListVO;

public class ProductUpdateGoCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		int categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
		int partnerNum = PartnercenterDAO.getPartnerNum(id);
		String productName = request.getParameter("productName");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String categoryDetail = request.getParameter("categoryDetail");
		
		ProductUpdateListVO vo = new ProductUpdateListVO(categoryNum, productName, productPrice, stock,
				categoryDetail, productNum);
		PartnercenterDAO.getProductUpdatego(vo);
		
		PrintWriter out = response.getWriter();
		out.println("<html><form name='frm' action='productUpdate.do' method='post'>" );
		out.println("</form></html>");
		out.print("<script>frm.submit();</script>");
		out.close();
		
		return null;
	}
}

