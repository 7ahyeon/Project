package com.mystudy.house.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mystudy.house.mybatis.DBService;
import com.mystudy.house.vo.MyinquiryVO;
import com.mystudy.house.vo.MyinquirycommentVO;
import com.mystudy.house.vo.MyinquirypageVO;

public class MyinquiryDAO {
	
	// 전체 목록 조회
	public static List<MyinquiryVO> getList() {
		SqlSession ss = DBService.getFactory().openSession();
		List<MyinquiryVO> list = ss.selectList("house.list");
		ss.close();
		return list;
	}
	// 문의 답변 조회
	public static MyinquirycommentVO getInquiryComList(int proinIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		MyinquirycommentVO vo = ss.selectOne("house.inquiryComlist", proinIdx);
		ss.close();
		return vo;
	}
	// 문의/문의 답변 삭제
	public static void deleteInquiryCom(int proinIdx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		ss.delete("house.deleteInquiry", proinIdx);
		ss.delete("house.deleteInquiryCom", proinIdx);
		ss.close();
	}
	
	// 전체 문의글 수 조회
	public static int getInquiryTotalCount() {
		SqlSession ss = DBService.getFactory().openSession();
		int totalCount = ss.selectOne("house.inquiryTotalCount");
		ss.close();
		return totalCount;
	}
	
	// 페이지에 해당하는 문의글 조회
	public static List<MyinquiryVO> getInquiryListPage(int begin, int end, String id) {
		MyinquirypageVO vo = new MyinquirypageVO(begin, end, id);
		SqlSession ss = DBService.getFactory().openSession();
		List<MyinquiryVO> list = ss.selectList("house.inquiryListPage", vo);
		ss.close();
		return list;
	}


	// 문의 목록 조회(아이디)
	public static List<MyinquiryVO> getInquiryList(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<MyinquiryVO> list = ss.selectList("house.inquiryList", id);
		ss.close();
		return list;
	}
	
}








