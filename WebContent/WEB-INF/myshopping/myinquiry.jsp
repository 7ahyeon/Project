<%@page import="com.mystudy.house.vo.MyinquiryVO"%>
<%@page import="java.util.List"%>
<%@page import="com.mystudy.house.dao.MyinquiryDAO"%>
<%@page import="com.mystudy.common.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집-나의 쇼핑-주문/배송내역 조회</title>
<%@ include file="../common/style.jspf" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<style>
	 	.nav-link {
	 		color: black;
		}
  		.myshopping, .myinquiry {
  			color: #35C5F0;
			font-weight: bold;
  		}
  		.inquiry {
  			margin:20px;
  		}
  		.question, .answer {
  			border-radius:50%;
  			border: 2px solid whitesmoke;
  			color: #35C5F0;
  			width: 40px;
  			height: 40px;
  			font-weight: bold;
  		}
  		.answerContent {
  			padding:20px;
  			font-size: 0.9em;
  			
  		}
  		.content {
  			padding:10px;
  		}
  		.date {
  			position: absolute;
  			right:50px;
  			margin:10px;
  			font-size:0.8em;
  			color: lightgray;
  		}
  		#notInquiry {
  			padding:100px;
  		}
</style>

</head>
<body>
<%@ include file="../common/memberMenu.jspf" %>
<%@ include file="../common/mypageMenu.jspf" %>
<%@ include file="../common/myshoppingMenu.jspf" %>


<div class="container-fluid">
	<c:choose>
		<c:when test="${!empty inquiryList }">
			<c:forEach var="vo" items="${inquiryList }" varStatus="status">
				<div class="text-left">
					<div class="inquiry font-weight-bold">
						<p>
							<c:if test="${empty inquiryComList[status.index].comments }">
								<button type="button" class="btn btn-sm btn-light font-weight-bold" style="margin:10px;" disabled>
									답변대기
								</button>
							</c:if>
							<c:if test="${!empty inquiryComList[status.index].comments }">
								<button type="button" class="btn btn-sm btn-primary font-weight-bold" style="margin:10px;" disabled>
									답변완료
								</button>
							</c:if>
							<span class="date">${vo.qdate }</span>
						</p>
						<p class="border-left" style="font-size:0.8em;padding-left:10px;">
							상품 <a href="#">${vo.productName }</a>
						</p>
						<p>
							<button class="btn question">Q</button>
							<span class="content">
								${vo.contents }
							</span>
						</p>
						<c:if test="${!empty inquiryComList[status.index].comments }">
							<div id="accordion" >
							  <div class="card">
							    <div class="card-header bg-white" id="heading${status.index }"  style="border:none;">
							      <h5 class="mb-0">
							        <button class="btn answer btn-link" data-toggle="collapse" data-target="#collapse${status.index }" aria-expanded="true" aria-controls="collapse${status.index }">
							          A
							        </button>
							      </h5>
							    </div>
							
							    <div id="collapse${status.index }" class="collapse" aria-labelledby="heading${status.index }" data-parent="#accordion">
							      <div class="card-body">
									<p class="answerContent text-muted bg-light">
										${inquiryComList[status.index].comments }
										<br>
										<span style="font-size:0.8em;">${inquiryComList[status.index].adate } · ${inquiryComList[status.index].companyName }</span>
									</p>
							      </div>
							    </div>
							  </div>
							 </div>
						 </c:if>
					 	<form action="myinquirydelete.do" method="post" onsubmit="return inquiryDelete();">
							<button type="submit"  class="btn btn-sm btn-outline-secondary font-weight-bold" style="margin:10px;">
								삭제
							</button>
							<input type="hidden" name="proinIdx" value="${vo.proinIdx }">
						</form>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p id="notInquiry" class="text-center">
				아직 문의 내역이 존재하지 않습니다.
			</p>
		</c:otherwise>
	</c:choose>
	
</div>
<script>
	function inquiryDelete(){
		if (confirm("문의를 삭제하시겠습니까?") == true){
			return true;
		} else {
			return false;
		}
	}
</script>
<%@ include file="../common/footer.jspf" %>
</body>
</html>