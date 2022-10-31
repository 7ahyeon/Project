<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/common/style.jspf"%>
<link href="${pageContext.request.contextPath}/summernote/summernote-bs4.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/summernote/summernote-bs4.js"></script>
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
  $(document).ready(function() {
	  $('#summernote').summernote({
		  height: 300,                 // set editor height
		  minHeight: null,             // set minimum height of editor
		  maxHeight: null,             // set maximum height of editor
		  focus: true,                  // set focus to editable area after initializing summernote
		  toolbar: [
			    // [groupName, [list of button]]
			  ]
	  });
	  
	});
  </script>
</head>
<body>
  <div class="container">
<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
<%@ include file="/WEB-INF/common/communityMenu.jspf"%>
<div class="container-fluid mt-3 mb-1">
  <form action="goPictureWrite.do" method="post">
    <div class="form-group form-inline">
      <select class="form-control mr-2" id="sizes" name="sizes">
        <option selected disabled>평수</option>
        <option value="0">10평 미만</option>
        <option value="1">10평대</option>
        <option value="2">20평대</option>
        <option value="3">30평대</option>
        <option value="4">40평대</option>
        <option value="5">50평 이상</option>
      </select>   
      <select class="form-control mr-2" id="residence" name="residence">
        <option selected disabled>주거형태</option>
        <option value="0">원룸오피스텔</option>
        <option value="1">아파트</option>
        <option value="2">빌라연립</option>
        <option value="3">단독주택</option>
        <option value="4">사무공간</option>
        <option value="5">상업공간</option>
        <option value="6">기타</option>
      </select>  
      <select class="form-control" id="style" name="style">
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
    </div>
    <div class="row border">
    <div class="col form-group mt-4 ml-2">
      <input type="file" class="form-control-file border" name="file">
      <img class="mt-3" src="../imgs/166592706060596621.png" width="200px">
    </div>
    <div class="col">
    <select class="form-control mt-4 mb-3" id="space" name="space">
        <option selected disabled>공간(필수)</option>
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
      </select>  
    <textarea class="form-control mb-4" rows="8" id="summernote" name="editordata">사진에 대해 설명해주세요</textarea>
    </div>
    </div>
    <div class="mb-4">
    <button type="submit" class="btn mt-3" id="btn">올리기</button>
    </div>
  </form>
</div>

</div>
</body>
</html>