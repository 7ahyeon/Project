<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>모두의집 - 나의 쇼핑홈</title>
	<%@ include file="../common/style.jspf" %>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <style>
  		.nav-link {
	 		color: black;
		}
  		.myshopping, .myshoppinghome {
  			color: #35C5F0;
			font-weight: bold;
  		}
  		 
  		.bi-coin, .bi-gem, .bi-chevron-right {
  			font-size: 20px;
  			color: #35C5F0;
  			font-weight: bolder;
  		}
  		.row {
  			margin-top:30px;
  			margin-bottom:30px;
  		}
  		.col-sm {
		  	padding:15px 20px;
		}
		.order {
		 	 margin-top:20px;
		 	 color: #35C5F0;
	  	}
		.btn-group .btn {
		  	padding:15px 27px;
		 }
		 #date, #orderstatus {
		 	padding: 5px;
		 	margin: 5px;
		 	font-size:1em;
		 }
		 #date, #confirm {
		 	color: #35C5F0;
			font-weight: bold;
		 }
		 #dateday {
		 	background-color: #35C5F0;
		 	color:white;
		 	border: 1px solid white;
		 	border-radius:30px;
		 	font-size:0.9em;
		 	margin: 10px 5px 10px 10px;
		 }
		 .grade, .point, .order, .ordercharge {
		 	color: #35C5F0;
		 }
		 #res, #post {
		 	color: #35C5F0;
		 	font-size:0.9em;
		 	font-weight: bold;
		 }
		 .orderList {
		 	font-weight: bold;
		 	margin: 0px 20px;
		 }
		 #shipping, #review {
		 	border-radius:5px;
		 	padding: 10px 30px;
		 	font-size:0.9em;
		 	font-weight: bold;
		 }
		 #shipping {
		 	border: 1px solid #35C5F0;
		 	color: #35C5F0;
		 	background-color:white;
		 	margin-bottom:5px;
		 	
		 }
		 #review {
		 	border: 1px solid white;
		 	color: white;
		 	background-color:#35C5F0;
		 }
		#notOrder {
  			padding:100px;
  		}
	</style>
</head>
<body>
<%@ include file="../common/memberMenu.jspf" %>
<%@ include file="../common/mypageMenu.jspf" %>
<%@ include file="../common/myshoppingMenu.jspf" %>
<%@ include file="gradeModal.jspf" %>

	<div class="container">
		<div class="container-fluid">
			<div class="row text-center">
				<div class="col-sm">
					<div class="btn-group">
	      				<div class="btn border text-decoration-none text-muted" onclick="location.href='mypoint.do';">
	      					<span><i class="bi bi-coin"></i>&nbsp;포인트&nbsp;</span>
	      					<span class="point font-weight-bold">${myPoint }&nbsp;P</span>
	      				</div>
		      				<div class="btn border text-decoration-none text-muted" data-toggle="modal" data-target="#myModal">
		      					<i class="bi bi-gem"></i>&nbsp;구매등급&nbsp;
		      					<span class="grade font-weight-bold">${myGrade }</span>
		      				</div>
	      			</div>
	      		</div>
			</div>
			<div class="row text-center">
	      		<div class="col-sm" id="box">
	      			<div class="btn-group" id="orders">
		      			<div class="btn font-weight-bold border-top border-bottom border-left text-nowrap text-decoration-none">
		      				<span>입금대기</span>
		      				<p class="order  ">0</p>
		      			</div>
		      			<div class="btn btn-link border-top border-bottom align-items-center">
		      				<i class="bi bi-chevron-right"></i>
		      			</div>
		      			<div class="btn font-weight-bold border-top border-bottom text-nowrap text-decoration-none">
		      				<span>결제완료</span>
		      				<p class="order">0</p>
		      			</div>
		      			<div class="btn btn-link border-top border-bottom align-items-center">
		      				<i class="bi bi-chevron-right"></i>
		      			</div>
		      			<div class="btn font-weight-bold border-top border-bottom text-nowrap text-decoration-none">
		      				<span>배송준비</span>
		      				<p class="order">0</p>
		     			 </div>
		    			<div class="btn btn-link border-top border-bottom align-items-center">
		      				<i class="bi bi-chevron-right"></i>
		      			</div>
		      			<div class="btn font-weight-bold border-top border-bottom text-nowrap text-decoration-none">
		      				<span>배송중</span>
		      				<p class="order  ">0</p>
		      			</div>
		    			<div class="btn btn-link border-top border-bottom align-items-center">
		      				<i class="bi bi-chevron-right"></i>
		      			</div>
					    <div class="btn font-weight-bold border-top border-bottom text-nowrap text-decoration-none">
					   		<span>배송완료</span>
		      			<p class="order  ">0</p>
		      			</div>
		    			<div class="btn btn-link border-top border-bottom align-items-center">
		      				<i class="bi bi-chevron-right"></i>
		      			</div>
					    <div class="btn font-weight-bold border-top border-bottom border-right text-nowrap text-decoration-none">
					   		<span>구매확정</span>
		      			<p class="order">0</p>
		      			</div>
		      		</div>
		      	</div>
			</div>  
			<div class="border" style="margin-bottom:50px;">
				<div class="row" >
					<div class="col-sm " id="orderdate">
						<div class="btn-group" style="margin-left:10px;">
							<button id="date" type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
			   					기간
			  				</button>
			  				<div class="dropdown-menu">
								<a class="dropdown-item" href="#">1개월 전</a>
								<a class="dropdown-item" href="#">3개월 전</a>
								<a class="dropdown-item" href="#">6개월 전</a>
								<a class="dropdown-item" href="#">1년 전</a>
								<a class="dropdown-item" href="#">2년 전</a>
								<a class="dropdown-item" href="#">3년 전</a>
			  				</div>
						</div>
		     			<div class="btn-group">
			  				<button id="orderstatus" type="button" class="btn btn-light text-muted dropdown-toggle" data-toggle="dropdown">
				   				주문상태
				  			</button>
				  			<div class="dropdown-menu">
								<a class="dropdown-item" href="#">입금대기</a>
								<a class="dropdown-item" href="#">결제완료</a>
								<a class="dropdown-item" href="#">배송준비</a>
								<a class="dropdown-item" href="#">배송중</a>
								<a class="dropdown-item" href="#">배송완료</a>
								<a class="dropdown-item" href="#">구매확정</a>
				  			</div>
		 	 			</div>
						<div>
							<button type="button" id="dateday">1개월 전&nbsp;x&nbsp;</button>
							<span id="res">초기화</span>
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${!empty orderList }">
						<c:forEach var="vo" items="${orderList }" varStatus="status">
							<div class="orderList">
								<p>${vo.orderNum } | ${vo.regdate.substring(0,10) }</p>
								<hr>
								<button type="button" class="btn btn-light" disabled>
									<span id="confirm">구매확정 · ${vo.regdate.substring(0,10)}</span>
									<span style="font-weight:bold;">결제완료</span>
								</button>
								<div class="row justify-content-between">
									<div class="col-sm">
										<div class="text-left font-weight-bold">
										<img align="left" style="position:relative;" src="${vo.imageFile }" width="100px" height="100px"/>
										<p style="position:absolute;margin:25px 150px;white-space:nowrap;">
											<span style="font-size:0.8em;">${vo.companyName }<br></span>
											<span>${vo.productName }</span>
										</p>
										</div>
									</div>
									
									<div class="col-sm">
										<p style="position:absolute;margin:25px 150px;white-space:nowrap;">
											<span style="font-size:0.8em;color:gray">${vo.categoryDetail }
											<br>${vo.productPrice }원 | ${vo.count }개</span>
											<br><span id="post">일반택배</span>
										</p>
										<p style="position:absolute;margin:10px 300px;white-space:nowrap;">
											<button id="shipping">
												배송추적
											</button><br>  
											<button id="review">
												리뷰작성
											</button>
										</p>
									</div>
								</div>
								<div class="row justify-content-between">
									<div class="col-sm bg-light">
										<p class="text-center font-weight-bold" style="font-size:0.8em;padding-top:10px;">
											<span style="color:gray">선결제배송비 ${vo.shipping }원</span><br>
											<span style="color:darkgray">${vo.companyName }</span> 
											<span style="color:#35C5F0">${vo.companyPhone }</span>
										</p>
									</div>
								</div>
							</div>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<p id="notOrder" class="text-center">
							일주일 내 주문 내역이 존재하지 않습니다.
						</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
<%@ include file="../common/footer.jspf" %>
</body>
</html>