<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 집-스토어-나의 쇼핑-주문/배송내역 조회</title>
<%@ include file="../common/style.jspf" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<style>
	.nav-link {
		color: black;
	}
	.myshopping, .mypoint {
		color: #35C5F0;
		font-weight: bold;
	}
	.col-sm {
		padding:15px 30px;
	}
	.pointlist span {
		margin:10px;
	}
	.point {
		color: #35C5F0;
		font-weight: bold;
	}
	#notPointList {
  			padding:100px;
	}
</style>
</head>
<body>
<%@ include file="../common/memberMenu.jspf" %>
<%@ include file="../common/mypageMenu.jspf" %>
<%@ include file="../common/myshoppingMenu.jspf" %>
<div class="row text-center">
	<div class="col-sm border" style="margin:50px 30px;padding:20px;">
		<div class="pointbox" style="font-size:1.2em;font-weight:bolder;">
		사용 가능한 포인트
		<p class="point">${myPoint } &nbsp;P</p>
		</div>
	</div>
</div>
<div class="row" style="margin-left:50px;margin-right:50px;margin-bottom:15px;font-size:1.2em;font-weight:bolder;">
	포인트 내역
</div>
<c:choose>
	<c:when test="${!empty myPointList }">
		<c:forEach var="vo" items="${myPointList }" varStatus="status">
			<c:if test="${vo.addPoint > 0 }">
				<div class="row">
					<div class="col-sm border-top border-bottom" style="margin-left:50px;margin-right:50px;padding-top:20px;">
						<p class="pointlist" style="vertical-align: middle;">
						<span class="date">${vo.regdate }</span>
						<span class="ordertitle" style="font-weight:bolder;">
							<button type="button" class="btn btn-sm btn-primary" disabled>적립</button>
							&nbsp;${myPointProductList[status.index] } 구매 적립
						</span>
						<span class="point">${vo.addPoint }&nbsp;P</span>
						</p>
					</div>
				</div>
			</c:if>
			<c:if test="${vo.pointUse > 0  }">
				<div class="row">
					<div class="col-sm border-bottom" style="margin-left:50px;margin-right:50px;padding-top:20px;">
						<p class="pointlist">
						<span class="date">${vo.regdate }</span>
						<span class="ordertitle font-weight-bold">
							<button type="button" class="btn btn-sm btn-danger" disabled>소멸</button>
							&nbsp;${myPointProductList[status.index] } 구매 사용
						</span>
						<span class="point">${vo.pointUse }&nbsp;P</span>
						</p>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</c:when>
	<c:otherwise>
			<p id="notPointList" class="text-center">
				아직 포인트 내역이 존재하지 않습니다.
			</p>
	</c:otherwise>
</c:choose>

<%@ include file="../common/footer.jspf" %>

</body>
</html>