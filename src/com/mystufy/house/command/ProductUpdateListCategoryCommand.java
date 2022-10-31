package com.mystufy.house.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.ProductListCategoryVO;
import com.mystudy.house.vo.ProductListVO;

public class ProductUpdateListCategoryCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "800do";
		
		int categoryDetail = Integer.parseInt(request.getParameter("categoryDetail"));
		System.out.println(categoryDetail);
		ProductListCategoryVO vo = new ProductListCategoryVO(id, categoryDetail);
		
		List<ProductListVO> productList = PartnercenterDAO.getProductListCategory(vo);
		System.out.println(productList.get(0).getCategoryDetail());
		String result = makeJson(productList);
		return result;
	}
	private String makeJson(List<ProductListVO> list) {
		StringBuilder result = new StringBuilder();
		
		result.append("{ \"list\" : [");
		for (ProductListVO vo : list) {
			result.append("{");
			result.append("\"productNum\" : " + vo.getProductNum() + ",");
			result.append("\"categoryName\" : \"" + vo.getCategoryName() + "\",");
			result.append("\"categoryDetail\" : " + vo.getCategoryDetail() + ",");
			result.append("\"productName\" : \"" + vo.getProductName() + "\",");
			result.append("\"stock\" : \"" + vo.getStock() + "\",");
			result.append("\"productPrice\" : \"" + vo.getProductPrice() + "\",");
			result.append("},");
		}
		result.deleteCharAt(result.length() - 1);
		result.append("]}");
		
		return result.toString();
	}
	
}
