package com.mystufy.house.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryDetailVO;
import com.mystudy.house.vo.CategoryVO;

public class CategoryCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		int categoryNum = Integer.parseInt(request.getParameter("categoryNum"));
		List<CategoryDetailVO> categoryDetailList = PartnercenterDAO.getCategoryDetail(categoryNum);
		String result = makeJson(categoryDetailList);
		
		return result;
	}
	
	private String makeJson(List<CategoryDetailVO> list) {
		StringBuilder result = new StringBuilder();
		
		result.append("{ \"list\" : [");
		for (CategoryDetailVO vo : list) {
			result.append("{");
			result.append("\"categoryDetailNum\" : " + vo.getCategoryDetailNum() + ",");
			result.append("\"categoryNum\" : " + vo.getCategoryNum() + ",");
			result.append("\"categoryDetail\" : \"" + vo.getCategoryDetail() + "\"");
			result.append("},");
		}
		result.deleteCharAt(result.length() - 1);
		result.append("]}");
		
		return result.toString();
	}
}

