<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  .home, .picture, .qna {
  color: black;
  }
  </style>
</head>
<body>
<div class="container">
<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
<%@ include file="/WEB-INF/common/communityMenu.jspf" %>
<div class="container-fluid mt-3 mb-1">
<button type="button" class="btn  mt-2" id="btn" data-toggle="collapse" data-target="#demo">노하우 작성 가이드</button>
  <div id="demo" class="collapse border">
    <ul class="mt-3 pr-2">
    <li>간단한 자기소개 후, 인테리어 노하우를 담은 정보성 콘텐츠를 작성해주세요. (사진 최소 15장 이상)</li>
    <li>자기소개는 평수, 공간, 가족형태를 기본으로 합니다. (ex. "5평 원룸에 사는 독립 6개월차 자취생이에요")</li>
    <li>사진 속 제품정보를 본문에 최대한 적어주세요. (제품분류/브랜드/제품명 순서)</li>
    <li>사진 첨부 시 용량은 장당 최대 20MB까지 업로드할 수 있고, jpg, png, webp, heif, heic, gif 포맷을 지원합니다. (*GIF의 경우 권장 용량 1MB, 용량 제한 10MB)</li>
    <li>DIY 콘텐츠인 경우 준비물과 총비용, 소요시간, 난이도(상/중/하)를 작성해주세요.</li>
    <li>정보를 많이 입력할수록 검색 결과에 많이 노출되어 조회수가 올라갑니다.</li>
    <li>브랜드 혹은 제품 홍보 목적의 콘텐츠는 오픈이 불가합니다.</li>
    <li>작성해주신 노하우는 오늘의집 에디터 검수를 통해 오픈 여부가 결정되며, 결과는 10일 내에 댓글로 안내드립니다. (커버사진과 제목은 에디터에 의해 변경될 수 있습니다.)</li>
    </ul>
  </div>
  <div>
  <form action="goKnowhowEdit.do" method="post" >
    <div class="form-group form-inline mt-3">
    <label for="sel1">카테고리(필수):&nbsp;&nbsp;&nbsp;</label>
      <select class="form-control" name="category">
        <option selected disabled>선택해주세요</option>
        <c:forEach items="${menu }" var="m" varStatus="status">
        <c:if test="${vo.category == status.index }">
        	<option value=${vo.category } selected>${m }</option>
        </c:if>
        <c:if test="${vo.category != status.index }">
        	<option value=${vo.category }>${m }</option>
        </c:if>
        </c:forEach>
      </select>   
    </div>
	<div class="form-group row">
      <div class="col-sm-1"><label for="subject">제목:</label></div>
      <div class="col-sm-11"><input type="text" value="${vo.subject }" class="form-control" id="subject" placeholder="제목을 입력하세요" name="subject"></div>
    </div>
    <div class="form-group">
      <label for="content">내용:&nbsp;</label>
  <textarea class="form-control" rows="20" id="content" name="content">${vo.content }</textarea>
    </div>
    <div class="form-group">
      <input type="file" class="form-control-file border" name="file">
    </div>
    <input type="hidden" name="idx" value="${vo.knowhowIdx }">
    <button type="submit" class="btn mt-2 mb-4" id="btn">수정하기</button>
  </form>
  </div>
</div>
</div>
</body>
</html>