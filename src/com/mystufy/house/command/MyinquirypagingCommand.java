package com.mystufy.house.command;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.mystudy.common.Paging;
import com.mystudy.house.dao.MyinquiryDAO;
import com.mystudy.house.vo.MyinquiryVO;
import com.mystudy.house.vo.MyinquirycommentVO;

public class MyinquirypagingCommand implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("id");
		String id = "1";
		
		Paging p = new Paging();
		
		// 전체 문의글 수 조회
		p.setTotalRecord(MyinquiryDAO.getInquiryTotalCount());
		p.setTotalPage();
		
		// 현재 페이지 cPage 설정 current page
		String cPage = request.getParameter("cPage");
		if (cPage != null) {
			p.setNowPage(Integer.parseInt(cPage));
		}
		
		// 현재 페이지 게시글의 시작 번호 begin 마지막 번호 end 설정
		p.setEnd(p.getNowPage() * p.getNumPerPage());
		p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
		
		if (p.getEnd() > p.getTotalRecord()) {
			p.setEnd(p.getTotalRecord());
		}
		
		// 블록 계산하기
		// 블록 시작 페이지 beginPage 마지막 페이지 endPage 설정 (현재 페이지 번호 사용)
		int nowPage = p.getNowPage();
		int beginPage = (nowPage - 1) / p.getNumPerBlock() * p.getNumPerBlock() + 1;
		
		p.setBeginPage(beginPage);
		p.setEndPage(beginPage + p.getNumPerBlock() - 1);
		
		if (p.getEndPage() > p.getTotalPage()) {
			p.setEndPage(p.getTotalPage());
		}
		
		// DB 데이터 조회
		// begin end 사용 문의글 데이터 조회
		List<MyinquiryVO> list = MyinquiryDAO.getInquiryListPage(p.getBegin(), p.getEnd(), id);
		
		// EL JSTL 사용을 위해 scope에 데이터 등록 (page 영역)
		request.setAttribute("inquiryListPage", list);
		request.setAttribute("pvo", p);
		
		return "/WEB-INF/myshopping/myinquiry.jsp";
	}
}
