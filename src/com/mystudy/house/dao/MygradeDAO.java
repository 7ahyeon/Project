package com.mystudy.house.dao;


import org.apache.ibatis.session.SqlSession;

import com.mystudy.house.mybatis.DBService;
import com.mystudy.house.vo.MyidperiodVO;

public class MygradeDAO {
	
	// 회원 등급 조회
	public static String getGrade(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		String grade= ss.selectOne("house.grade", id);
		ss.close();
		return grade;
	}

	// 최근 3개월 실적(구매 횟수) 조회
	public static int getThreeMonthOrderTimes(MyidperiodVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		int threeMonthOrderTimes = ss.selectOne("house.threeMonthOrderTimes", vo);
		ss.close();
		return threeMonthOrderTimes;
	}
	
	// 최근 3개월 실적(실결제 금액) 조회
	public static int getThreeMonthTotPrice(MyidperiodVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		int threeMonthTotPrice = 0;
		if (ss.selectOne("house.threeMonthTotPrice", vo) != null) {
			threeMonthTotPrice = ss.selectOne("house.threeMonthTotPrice", vo);
		} 
		ss.close();
		return threeMonthTotPrice;
	}


	
	
}








