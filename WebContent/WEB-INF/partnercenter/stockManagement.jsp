<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터 - 재고 관리</title>
<%@ include file="../common/style.jspf" %>
  <style>
  .nav-link {
  	color: black;
  }
  .productManager, .stockManagement {
  	color: #35C5F0;
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
<%@ include file="../common/productMenu.jspf" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<div class="row text-center">
	<div class="col-sm" style="margin:50px;padding:20px;font-size: 0.9em;background-color: WhiteSmoke;white-space: nowrap;">
		<form method="post" action="productUpdate.do" >
			<table class="head">
				<tbody>
					<tr>
						<td colspan="2">
							<p class="border-bottom text-muted font-weight-bold" style="font-size:0.8em;padding-bottom:10px;margin-bottom:0;">
								- 상품에 대한 재고를 쉽게 관리할 수 있는 페이지입니다.<br>
								<span class="text-danger">
									- 품절된 옵션 및 품절 임박 옵션을 잘 관리해주세요. (품절:0건/품절임박:0건)
								</span>
								<br>
								- 품절임박은 옵션 재고가 5개 이하 남은 제품을 의미합니다.<br>
								- 기타 수정사항은 상품 수정에서 수정해주세요.
							</p>
						</td>
					</tr>
					<tr>
						<th>판매</th>
						<td>
							<select name="categoryName">
					            <option value="">전체</option>
					            <option value="forSale">판매중</option>
					            <option value="lowInStock">품절 임박</option>
					            <option value="soldOut">품절</option>
					        </select>
						</td>
					</tr>
					<tr>
						<th>카테고리</th>
						<td>
							<select name="categoryNum" title="카테고리" id="select1" onchange="category_select(this)">
						            <option value="">카테고리 선택</option>
								<c:forEach var="vo" items="${categoryList }">
						            <option value="${vo.categoryNum }">${vo.categoryName }</option>
						         </c:forEach>
					        </select>
					        
							<select id ="categoryDetail" title="세부 카테고리" name="categoryDetail" style="display=none;">
						            <option value="">세부 카테고리 선택</option>
					        </select>
						</td>
					</tr>
					<tr>
						<th>상품번호</th>
						<td>
							<input type="number" name="productNum">
						</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td>
							<input type="text" name="productName">
							<span style="margin:20px;">
								<input type="button" value="검색" onclick="sendData(this.form)">
								<input type="reset" value="초기화">
							</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</div>

<div class="row">
<div class="col-sm" style="margin:0px 50px;">
<div id="container">
	<p class="font-weight-bold text-secondary" style="font-size:1em;">
		검색결과 총 <span style="color:#35C5F0;">${cnt }</span> 건</p>
	<hr style="border:1px solid #35C5F0;">
	<table class="tb">
		<thead style="background-color:#35C5F0;color:white;">
			<tr>
				<th>상품번호</th>
				<th>카테고리</th>
				<th>세부 카테고리</th>
				<th>상품명</th>
				<th>상품 가격</th>
				<th>재고 수량</th>
			</tr>
		</thead>
		<tbody id="listUp">
			<c:choose>
				<c:when test="${!empty productList }">
					<c:forEach var="vo" items="${productList }" varStatus="status">
						<tr>
							<td>
								<a href="#">${vo.productNum }</a>
							</td>
							<td>${vo.categoryName }</td>
							<td>${vo.categoryDetail }</td>
							<td>${vo.productName }</td>
							<td>${vo.productPrice }</td>
							<td>
								<form>
									<input type="hidden" name="productNum" value="${vo.productNum }">
									<input type="number" name="stock" title="재고 수량" value="${vo.stock }" placeholder="${vo.stock }">&nbsp;개
									<button type="button" onclick="stockUpdate(this.form)" class="btn btn-sm btn-outline-primary font-weight-bold" style="margin:10px;">
										수정
									</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td class="text-center">
							아직 등록된 상품이 존재하지 않습니다.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
</div>
</div>
<%@ include file="../common/footer.jspf" %>
<script>
	function stockUpdate(tfm) {
		let firstForm = tfm;
		var stockVal = firstForm.elements[1].value;
		if (stockVal != null){
			if (stockVal > 0 || stockVal < 9999999) {
				let dataStock = {
						"productNum" : firstForm.elements[0].value,
						"stock" : firstForm.elements[1].value
				}
				$.ajax({
					  type:'POST',
					  url:'productStockUpdate.do',
					  data: dataStock,
					  success: function(data) {
						  Swal.fire(
								  '수정완료',
								  '재고가 성공적으로 수정되었습니다.',
								  'success'
								)
					  },
					  error : function(jqXHR, textStatus, errorThrown) {
						  
					  }
			    });
			} else {
				Swal.fire('재고는 0개부터 9999999개까지 등록 가능합니다.');
				return;
			}
		}
	}
	
</script>
</body>
</html>