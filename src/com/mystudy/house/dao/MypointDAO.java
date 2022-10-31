package com.mystudy.house.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mystudy.house.mybatis.DBService;
import com.mystudy.house.vo.MyinquiryVO;
import com.mystudy.house.vo.MyinquirycommentVO;
import com.mystudy.house.vo.MyinquirypageVO;
import com.mystudy.house.vo.MypointVO;

public class MypointDAO {
	
	// 포인트 조회(아이디)
	public static int getMyPoint(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int point = ss.selectOne("house.myPoint", id);
		ss.close();
		return point;
	}
	// 포인트 내역 조회(아이디)
	public static List<MypointVO> getMyPointList(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<MypointVO> pointList = ss.selectList("house.myPointList",id);
		ss.close();
		return pointList;
	}
	// 포인트 적립 사용 주문 상품 조회
	public static String getMyPointProduct(int orderNum) {
		SqlSession ss = DBService.getFactory().openSession();
		String pointProduct = ss.selectOne("house.myPointProduct",orderNum);
		ss.close();
		return pointProduct;
	}
	
	
}








