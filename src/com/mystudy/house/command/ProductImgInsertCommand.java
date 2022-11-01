package com.mystudy.house.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.mystudy.house.dao.PartnercenterDAO;
import com.mystudy.house.vo.ProductInsertImgVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductImgInsertCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "C:/Users/itwill/Downloads/house/img/product";
		int size = 10 * 1024 * 1024;
		System.out.println("insertimg");
		
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
		String uploadPath = "/house/img/product/" + fileName;
		ProductInsertImgVO vo = new ProductInsertImgVO(productNum, uploadPath);
		PartnercenterDAO.insertProductImg(vo);
		
		return "null";
	}
	
}

