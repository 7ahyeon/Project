<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의집 - 사진</title>
<%@ include file="/WEB-INF/common/style.jspf"%>
<style>
.community {
	color: #35C5F0;
	font-weight: bold;
}

.store {
	color: black;
}

.picture {
	color: #35C5F0;
	font-weight: bold;
}

.home, .knowhow, .qna {
	color: black;
}
</style>
<script>
	function submit(frm) {
		frm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<c:if test="${empty id }">
			<%@ include file="/WEB-INF/common/guestMenu.jspf"%>
		</c:if>
		<c:if test="${not empty id }">
			<%@ include file="/WEB-INF/common/memberMenu.jspf"%>
		</c:if>
		<%@ include file="/WEB-INF/common/communityMenu.jspf"%>
		<div class="container-fluid">
			<div class="form-inline pt-3">
				<form action="picture.jsp" onchange="submit(this.form)">
					<select class="form-control" name="orders">
						<option selected disabled>정렬</option>
						<option value="0">최근 인기순</option>
						<option value="1">역대 인기순</option>
						<option value="2">최신순</option>
					</select> <select class="form-control" name="residence">
						<option selected disabled>주거형태</option>
						<option value="0">원룸오피스텔</option>
						<option value="1">아파트</option>
						<option value="2">빌라연립</option>
						<option value="3">단독주택</option>
						<option value="4">사무공간</option>
						<option value="5">상업공간</option>
						<option value="6">기타</option>
					</select> <select class="form-control" name="space">
						<option selected disabled>공간</option>
						<option value="0">원룸</option>
						<option value="1">거실</option>
						<option value="2">침실</option>
						<option value="3">주방</option>
						<option value="4">욕실</option>
						<option value="5">아이방</option>
						<option value="6">서재작업실</option>
						<option value="7">베란다</option>
						<option value="8">사무공간</option>
						<option value="9">상업공간</option>
						<option value="10">가구소품</option>
						<option value="11">현관</option>
						<option value="12">외관기타</option>
					</select> <select class="form-control" name="sizes">
						<option selected disabled>평수</option>
						<option value="0">10평 미만</option>
						<option value="1">10평대</option>
						<option value="2">20평대</option>
						<option value="3">30평대</option>
						<option value="4">40평대</option>
						<option value="5">50평 이상</option>
					</select> <select class="form-control" name="style">
						<option selected disabled>스타일</option>
						<option value="0">모던</option>
						<option value="1">북유럽</option>
						<option value="2">빈티지</option>
						<option value="3">내추럴</option>
						<option value="4">프로방스로맨틱</option>
						<option value="5">클래식앤틱</option>
						<option value="6">한국아시아</option>
						<option value="7">유니크</option>
					</select>
				</form>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${list }" var="vo">
			<div class="col-sm-3">
				<div class="profile row mt-4 mb-2">
					<div class="col-sm-2">
						<img style="border-radius: 40px" src="${pageContext.request.contextPath}/img/profileImg/${profileImg }"
							width="40px" height="40px">
					</div>
					<div class="col-sm-10 align-self-center">
						<span><b>${vo.id }</b></span>
					</div>
				</div>
				<div class="thumbnail mb-2">
					<a href="pictureDetail.jsp?idx=1"> <img
						style="border-radius: 5px" src="${pageContext.request.contextPath}/img/profileImg/${profileImg }"
						width="100%" height="100%">
					</a>
				</div>
				<div class="likes row mb-2">
					<div class="col-sm-4">
						<a href="#"><i class="far fa-heart"></i></a> 5
					</div>
					<div class="col-sm-4">
						<a href="#"><i class="far fa-bookmark"></i></a> 2
					</div>
					<div class="col-sm-4">
						<a href="#"><i class="far fa-comment"></i></a> 4
					</div>
				</div>
				<div class="content mb-2">${vo.content }
				</div>
				<div class="comments mb-4 row">
					<div class="col-sm-2">
						<img style="border-radius: 30px" src="../imgs/knowhow/sample.png"
							width="30px" height="30px">
					</div>
					<div class="col-sm-10">
						<span><b>mh24</b></span> <span>단조로울 수 있는 인테리어에 블루 한방울 색을
							입혀주니 청량하고 생기있어 보이는 공간이 된것 같아요</span>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<ul class="pagination justify-content-center pb-2">
			<li class="page-item"><a class="page-link" href="#">이전</a></li>
			<li class="page-item active"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">다음</a></li>
		</ul>
	</div>
</body>
</html>