package com.mystudy.house.dao;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mystudy.house.mybatis.DBService;
import com.mystudy.house.vo.CategoryDetailVO;
import com.mystudy.house.vo.CategoryVO;
import com.mystudy.house.vo.DeleteStatusVO;
import com.mystudy.house.vo.ProductInsertImgVO;
import com.mystudy.house.vo.ProductInsertInfoImgVO;
import com.mystudy.house.vo.ProductInsertVO;
import com.mystudy.house.vo.ProductListCategoryVO;
import com.mystudy.house.vo.ProductListVO;
import com.mystudy.house.vo.ProductUpdateListVO;
import com.mystudy.house.vo.ProductUpdatestockVO;

public class PartnercenterDAO {
	// 파트너 등급 조회
	public static int getPartnerGrade(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int partnerGrade = ss.selectOne("house.partnerAccess", id);
		ss.close();
		return partnerGrade;
	}
	
	// 카테고리 조회
	public static List<CategoryVO> getCategory() {
		SqlSession ss = DBService.getFactory().openSession();
		List<CategoryVO> category = ss.selectList("house.categoryList");
		ss.close();
		return category;
	}
	
	// 세부 카테고리 조회
	public static List<CategoryDetailVO> getCategoryDetail(int categoryNum) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CategoryDetailVO> categoryDetail = ss.selectList("house.categoryDetailList", categoryNum);
		ss.close();
		return categoryDetail;
	}

	
	// 상품 등록
	public static int insertProduct(ProductInsertVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("house.insertProduct", vo);
		ss.close();
		return result;
	}
	
	// 상품 번호 조회
	public static int getProductNum(String productName) {
		SqlSession ss = DBService.getFactory().openSession();
		int productNum = ss.selectOne("house.productNum", productName);
		ss.close();
		return productNum;
	}
	// 상품 대표 이미지 등록
	public static int insertProductImg(ProductInsertImgVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("house.insertProductImg", vo);
		ss.close();
		return result;
	}
	// 상품 상세 이미지 등록
	public static int insertProductInfoImg(ProductInsertInfoImgVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("house.insertProductInfoImg", vo);
		ss.close();
		return result;
	}

	// 파트너 번호 조회
	public static int getPartnerNum(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int partnerNum = ss.selectOne("house.partnerNum", id);
		ss.close();
		return partnerNum;
	}
	// 상품 조회 (전체)
	public static List<ProductListVO> getProductListAll(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		List<ProductListVO> productListAll = ss.selectList("house.productListAll", id);
		ss.close();
		return productListAll;
	}
	// 상품 조회 (전체)
	public static int getProductListCount(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int productListCount = ss.selectOne("house.productListCount", id);
		ss.close();
		return productListCount;
	}
	
	// 상품 조회 (카테고리)
	public static List<ProductListVO> getProductListCategory(ProductListCategoryVO vo) {
		SqlSession ss = DBService.getFactory().openSession();
		List<ProductListVO> productListCategory = ss.selectList("house.productListCategory", vo);
		ss.close();
		return productListCategory;
	}
	// 상품 조회 (카테고리)
	public static ProductUpdateListVO getProductUpdateList(int productNum) {
		SqlSession ss = DBService.getFactory().openSession();
		ProductUpdateListVO vo = ss.selectOne("house.productUpdateList", productNum);
		ss.close();
		return vo;
	}
	// 상품 수정
	public static int getProductUpdatego(ProductUpdateListVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("house.updateProduct", vo);
		ss.close();
		return result;
	}
	// 상품 재고 수정
	public static int getProductUpdateStock(ProductUpdatestockVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("house.updateProductStock", vo);
		ss.close();
		return result;
	}
	// 상품 삭제
	public static int getProductDelete(DeleteStatusVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("house.deleteProduct", vo);
		ss.close();
		return result;
	}
	
	
}








