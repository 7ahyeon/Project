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
	<c:if test="${empty id }">
		<%@ include file="/WEB-INF/common/guestMenu.jspf" %>
	</c:if>
	<c:if test="${not empty id }">
		<%@ include file="/WEB-INF/common/memberMenu.jspf" %>
	</c:if>
	<%@ include file="/WEB-INF/common/communityMenu.jspf" %>
	<div class="container-fluid mt-3 mb-1">
		<div class="mb-4"><a href="knowhow.do">노하우</a> > <a href="knowhow.do?category=${vo.category }">${menu[vo.category] }</a></div>
		<c:if test="${id == vo.id }">
		<div class="mb-4">
			<button class="btn" id="btn" onclick="location.href='knowhowEdit.do?idx=${idx}'">수정</button>
			<button class="btn" id="btn" data-toggle="modal" data-target="#myModal">삭제</button>
			<!-- The Modal -->
			<div class="modal" id="myModal">
  				<div class="modal-dialog">
    				<div class="modal-content">
      				<!-- Modal Header -->
      					<div class="modal-header">
        					<h4 class="modal-title">노하우글 삭제</h4>
        					<button type="button" class="close" data-dismiss="modal">&times;</button>
      					</div>
      				<!-- Modal body -->
      					<div class="modal-body">
        					정말로 삭제하시겠습니까?
      					</div>
      				<!-- Modal footer -->
      					<div class="modal-footer">
        					<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">취소</button>
        					<button type="button" class="btn btn-primary" onclick="location.href='knowhowDelete.do?idx=${idx}'" data-dismiss="modal">삭제</button>
      					</div>
    				</div>
  				</div>
			</div>
		</div>
		</c:if>
		<h3>${vo.subject }</h3>
		<div class="profile row mt-4 mb-2">
    		<div class="col-sm-1" style="text-align: right">
    			<img style="border-radius: 40px" src="${pageContext.request.contextPath}/img/profileImg/${vo.profileImg}" width="40px" height="40px">
    		</div>
    		<div class="col-sm-11">
    			<span><b>${vo.id }</b></span><br>
    			<span><small>${vo.regdate }</small></span>
    		</div>
    	</div>
		<div class="mt-3 mb-3">
			<span>${vo.content }</span>
		</div>
		<div>
		<div style="float:left; color: gray;">좋아요 ${vo.likeCnt } 스크랩 ${vo.scrapCnt} 댓글 ${vo.c1Cnt + vo.c2Cnt} 조회수 ${vo.views }</div>
		<c:if test="${id != vo.id }">
				<div style="float:right;">
		<button type="button" class="btn btn-outline-secondary btn-sm mb-2" onclick="location.href='knowhowLike.do?idx=${idx}'">
    		좋아요
  		</button>
		<button type="button" class="btn btn-outline-secondary btn-sm mb-2" onclick="location.href='knowhowScrap.do?idx=${idx}'">
			스크랩
		</button>
		</div>
		</c:if>
		</div>
		<div style="clear:both">
			<hr>
			<div><b>댓글 ${vo.c1Cnt + vo.c2Cnt}</b></div>
			<form class="mt-3" action="/action_page.php">
  				<div class="row">
    				<div class="col-sm-11"><input type="text" class="form-control" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)" id="comment">
  				</div>
  				<div class="col-sm-1"><button type="submit" class="btn" id="btn">입력</button></div>
  				</div>
			</form>
			<div class="comments mt-4">
				<div class="comment row mb-4">
    				<div class="col-sm-1">
    					<img style="border-radius: 30px" src="../imgs/166592706060596621.png" width="30px" height="30px">
    				</div>
    				<div class="col-sm-11">
    					<span><b>mh24</b></span><br>
    					<span><i>@mh24</i> 단조로울 수 있는 인테리어에 블루 한방울 색을 입혀주니 청량하고 생기있어 보이는 공간이 된것 같아요</span><br>
    					<span>5일 전</span>&nbsp;&nbsp;
    					<a>답글 달기</a>
    				</div>  	
    			</div>
    			<div class="comment row mb-4">
    				<div class="col-sm-1">
    					<img style="border-radius: 30px" src="../imgs/166592706060596621.png" width="30px" height="30px">
    				</div>
    				<div class="col-sm-11">
    					<span><b>mh24</b></span><br>
    					<span>단조로울 수 있는 인테리어에 블루 한방울 색을 입혀주니 청량하고 생기있어 보이는 공간이 된것 같아요</span><br>
    					<span>5일 전</span>&nbsp;&nbsp;
    					<a>답글 달기</a>
    				</div>  	
    			</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>