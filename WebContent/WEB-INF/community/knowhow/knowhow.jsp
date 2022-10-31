<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>오늘의집 - 노하우</title>
	<%@ include file="/WEB-INF/common/style.jspf"%>
  	<style>
  		.community {
  			color: #35C5F0;
  			font-weight: bold;
  		}
  		.store {
  			color: black;
  		}
  		.knowhow {
  			color: #35C5F0;
			font-weight: bold;
  		}
  		.picture, .home, .qna {
  			color: black;
  		}
	</style>
</head>
<body>
	<div class="container">
	<c:if test="${empty id }">
		<%@ include file="/WEB-INF/common/guestMenu.jspf" %>
	</c:if>
	<c:if test="${not empty id }">
		<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
	</c:if>
	<%@ include file="/WEB-INF/common/communityMenu.jspf" %>
		<div class="container-fluid">
			<div class="pt-4">
				<h5 class="mb-0"><b>테마별 노하우</b></h5>
			</div>
			<div class="pb-3 pt-2">
				<c:if test="${empty category }">
					<button type="button" class="btn" id="btn" onclick="location.href='knowhow.do'">전체</button>
				</c:if>
				<c:if test="${not empty category }">
					<button type="button" class="btn btn-light" onclick="location.href='knowhow.do'">전체</button>
				</c:if>
				<c:forEach items="${menu }" var="m" varStatus="status">
					<c:if test="${category == status.index }">
						<button type="button" class="btn" id="btn" onclick="location.href='knowhow.do?category=${status.index }'">${m }</button>
					</c:if>
					<c:if test="${category != status.index }">
						<button type="button" class="btn btn-light" onclick="location.href='knowhow.do?category=${status.index }'">${m }</button>
					</c:if>
				</c:forEach>
			</div>
			<!-- 사진에 북마크 아이콘: https://www.w3schools.com/bootstrap4/bootstrap_cards.asp -->
			<c:if test="${empty list }">
			<div class="text-center p-4">
     			<h5>앗! 찾으시는 결과가 없네요.</h5>
     		</div>	
			</c:if>
			<c:if test="${not empty list }">
			<div class="row pb-4">
				<c:forEach items="${list }" var="vo">
					<div class="col-sm-3 pt-3">
    				<div class="thumbnail pb-2">
    					<a href="knowhowDetail.do?idx=${vo.knowhowIdx }">			
    						<img style="border-radius: 10px" src="${pageContext.request.contextPath}/img/knowhowThumbnail/${vo.thumbnail }" width="260px" height="180px">
    					</a>
    				</div>
    				<div class="title"><b>${vo.subject }</b></div>
    				<div class="info">
    					<small>${vo.id }<br>
    					조회수 ${vo.views } 스크랩 ${vo.scrapCnt }</small>
    				</div>
    			</div>
				</c:forEach>
    		</div>
    		<ul class="pagination justify-content-center pb-2">
   		 		<li class="page-item"><a class="page-link">이전</a></li>
    			<li class="page-item active"><a class="page-link">1</a></li>
    			<li class="page-item"><a class="page-link">2</a></li>
    			<li class="page-item"><a class="page-link">3</a></li>
    			<li class="page-item"><a class="page-link">다음</a></li>
  			</ul>
  			</c:if>
    	</div>
	</div>
</body>
</html>