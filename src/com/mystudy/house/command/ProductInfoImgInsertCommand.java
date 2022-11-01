package com.mystudy.house.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.CategoryDetailVO;
import com.mystudy.house.vo.ProductInsertInfoImgVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductInfoImgInsertCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "C:/Users/itwill/Downloads/house/img/productInfo";
		int size = 10 * 1024 * 1024;
		System.out.println("insertimginfo");
		String fileName = "";
		int productNum = 0;
		
		try{
			// 파일업로드 및 업로드 후 파일명 가져옴
			MultipartRequest multi = new MultipartRequest(request, path, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement(); 
			fileName = multi.getFilesystemName(file); 
			productNum = Integer.parseInt(multi.getParameter("productNum"));
		}catch(Exception e){
			e.printStackTrace();
		}
		String uploadPath = "/house/img/productInfo/" + fileName;
		ProductInsertInfoImgVO vo = new ProductInsertInfoImgVO(uploadPath, productNum);
		PartnercenterDAO.insertProductInfoImg(vo);
		
		PrintWriter out = response.getWriter();
		out.println("<html><form name='frm' action='partnercenter.do' method='post'>" );
		out.println("</form></html>");
		out.print("<script>frm.submit();</script>");
		out.close();
		return null;
	}
}

