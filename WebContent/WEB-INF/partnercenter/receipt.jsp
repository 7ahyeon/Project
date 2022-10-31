<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터 - 문의/후기 - 상품문의</title>
<%@ include file="../common/style.jspf" %>
  <style>
  .nav-link {
  	color: black;
  }
  .inquiryReview, .inquiry {
  	color: #35C5F0;
  }
  .head {
  	width:100%
  }
  .head th, td {
  	text-align: left;
  	padding: 10px;
  }
  thead th {
  	padding:30px;
  }
  input[type=text], textarea {
	width:500px;
	border: 1px solid lightgray;
	font-size: 0.8em;
	padding:5px 10px;
	}
  input[type=number] {
	width:100px;
	border: 1px solid lightgray;
	}
	input[type=date] {
	width:150px;
	border: 1px solid lightgray;
	}
	select {
		border: 1px solid lightgray;
		font-size: 0.8em;
		padding: 5px 10px;
	}
	.tb {
		width:100%;
		margin-bottom: 50px;
	}
	.tb th, .tb td {
		text-align:center;
		white-space: nowrap;
	}
	.tb td {
		font-size: 0.9em;
	}

  </style>
</head>
<body>
<%@ include file="../common/partnerMenu.jspf" %>
<%@ include file="../common/inquiryReviewMenu.jspf" %>
<div class="row text-center">
	<div class="col-sm" style="margin:50px 50px 0 50px;padding:20px;font-size: 0.9em;background-color: WhiteSmoke;white-space: nowrap;">
	<table class="head">
		<tbody>
			<tr>
				<td colspan="2">
					<p class="text-right">
						<span style="font-weight:bold;font-size:1.3em;">
							매출현황
						</span>
						<br>
						<span style="font-size: 0.8em;">
							고객의 문의에 답변을 해주세요.<br>
							고객과의 소통이 저희 서비스를 풍요롭게 해줍니다.
						</span>
					</p>
					<p class="border-bottom text-muted font-weight-bold" style="font-size:0.8em;padding-bottom:10px;margin-bottom:0;">
						<span class="text-danger">
							- 개인정보는 상담 이외의 목적으로 사용을 금합니다.<br>
							- 상품문의를 통한 별도의 판매행위는 계약사항에 위반되며 엄격히 금지하고 있습니다.<br>
						</span>
							- 상품문의는 파트너사의 얼굴입니다. 고객의 문의에 친절하게 응답해주세요.
					</p>
				</td>
			</tr>
			<tr>
				<th>처리상태</th>
				<td>
					<select name="categoryName">
			            <option>전체</option>
			            <option value="cat">고양이</option>
			            <option value="bear">곰돌이</option>
			        </select>
				</td>
			</tr>
			<tr>
				<th>기간</th>
				<td>
					<input type="date" name="regdate1">
					&nbsp;~&nbsp;
					<input type="date" name="regdate2">
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">오늘</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">1주일</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">1달</button>
					<button type="button" class="btn btn-outline-secondary" style="font-size:0.8em;padding:3px 4px;">전체</button>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" name="productName">
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</div>

<div class="row">
<div class="col-sm" style="margin:0px 50px;">
<div id="container">
	<p class="font-weight-bold text-secondary" style="font-size:1em;">
		검색결과 총 <span style="color:#35C5F0;">0</span> 건
	</p>
	<hr style="border:1px solid #35C5F0;">
	<table class="tb">
		<thead style="background-color:#35C5F0;color:white;">
			<tr>
				<th>번호</th>
				<th>문의유형</th>
				<th>답변상태</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>번호</td>
				<td>문의유형</td>
				<td>답변상태</td>
				<td>내용</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
		</tbody>
	</table>
</div>
</div>
</div>
<%@ include file="../common/footer.jspf" %>
</body>
</html>