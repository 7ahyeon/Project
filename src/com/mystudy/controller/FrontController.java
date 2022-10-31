package com.mystudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystufy.house.command.CategoryCommand;
import com.mystufy.house.command.Command;
import com.mystufy.house.command.InquiryCommand;
import com.mystufy.house.command.MyinquiryCommand;
import com.mystufy.house.command.MyinquirydeleteCommand;
import com.mystufy.house.command.MypointCommand;
import com.mystufy.house.command.MyshoppingCommand;
import com.mystufy.house.command.OrderBTNCommand;
import com.mystufy.house.command.OrderInTransitCommand;
import com.mystufy.house.command.OrderShippingCommand;
import com.mystufy.house.command.OrderUnconfirmedCommand;
import com.mystufy.house.command.PartnercenterCommand;
import com.mystufy.house.command.ProductDeleteCommand;
import com.mystufy.house.command.ProductDeleteConfirmCommand;
import com.mystufy.house.command.ProductImgInsertCommand;
import com.mystufy.house.command.ProductInfoImgInsertCommand;
import com.mystufy.house.command.ProductInsertCommand;
import com.mystufy.house.command.ProductListCommand;
import com.mystufy.house.command.ProductUpdateCommand;
import com.mystufy.house.command.ProductUpdateDetailCommand;
import com.mystufy.house.command.ProductUpdateGoCommand;
import com.mystufy.house.command.ProductUpdateListCategoryCommand;
import com.mystufy.house.command.ProductUpdateListCommand;
import com.mystufy.house.command.ReceiptCommand;
import com.mystufy.house.command.ReviewCommand;
import com.mystufy.house.command.StockManagementCommand;
import com.mystufy.house.command.productStockUpdateCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		System.out.println("doget");
		Command command = null;
		if (com.equals("/myshopping.do")) {
			command = new MyshoppingCommand();
		} 
		
		
		else if (com.equals("/myinquiry.do")) {
			command = new MyinquiryCommand();
		} else if (com.equals("/myinquirydelete.do")) {
			command = new MyinquirydeleteCommand();
		} 
		
		else if (com.equals("/mypoint.do")) {
			command = new MypointCommand();
		} 
		
		
		else if (com.equals("/partnercenter.do")) {
			command = new PartnercenterCommand();
		} else if (com.equals("/category.do")) {
			command = new CategoryCommand();
		} else if (com.equals("/productInsert.do")) {
			command = new ProductInsertCommand();
		} else if (com.equals("/productInfoImgInsert.do")) {
			command = new ProductInfoImgInsertCommand();
		} else if (com.equals("/productImgInsert.do")) {
			command = new ProductImgInsertCommand();
		} else if (com.equals("/productUpdate.do")) {
			command = new ProductUpdateCommand();
		} else if (com.equals("/productUpdateList.do")) {
			command = new ProductUpdateListCommand();
		} else if (com.equals("/productUpdateListCategory.do")) {
			command = new ProductUpdateListCategoryCommand();
		} else if (com.equals("/productUpdateDetail.do")) {
			command = new ProductUpdateDetailCommand();
		} else if (com.equals("/productUpdateGo.do")) {
			command = new ProductUpdateGoCommand();
		} else if (com.equals("/productDelete.do")) {
			command = new ProductDeleteCommand();
		} else if (com.equals("/productDeleteConfirm.do")) {
			command = new ProductDeleteConfirmCommand();
		} else if (com.equals("/productList.do")) {
			command = new ProductListCommand();
		} else if (com.equals("/stockManagement.do")) {
			command = new StockManagementCommand();
		} else if (com.equals("/productStockUpdate.do")) {
			command = new productStockUpdateCommand();
		} else if (com.equals("/orderShipping.do")) {
			command = new OrderShippingCommand();
		} else if (com.equals("/orderUncomfirmed.do")) {
			command = new OrderUnconfirmedCommand();
		} else if (com.equals("/orderUncomfirmed.do")) {
			command = new OrderUnconfirmedCommand();
		} else if (com.equals("/orderBTN.do")) {
			command = new OrderBTNCommand();
		} else if (com.equals("/orderInTransit.do")) {
			command = new OrderInTransitCommand();
		} else if (com.equals("/inquiry.do")) {
			command = new InquiryCommand();
		} else if (com.equals("/review.do")) {
			command = new ReviewCommand();
		} else if (com.equals("/receipt.do")) {
			command = new ReceiptCommand();
		}
		
		String path = command.exec(request, response);
		if (path == null || path.startsWith("/WEB-INF") ) {
			try {
				request.getRequestDispatcher(path).forward(request, response);
			} catch(Exception e) {
			}
		} else {
			PrintWriter out = response.getWriter();
			out.print(path);
		}
	};
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
}

