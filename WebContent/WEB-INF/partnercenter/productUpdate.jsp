<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모두의집 - 파트너 센터 - 상품 수정</title>
<%@ include file="../common/style.jspf" %>
  <style>
  .nav-link {
  	color: black;
  }
  .productManager, .productUpdate {
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
								- 상품에 대한 정보를 수정할 수 있는 페이지입니다.<br>
								<span class="text-danger">
									- 품절된 옵션 및 품절 임박 옵션을 잘 관리해주세요. (품절:0건/품절임박:0건)
								</span>
								<br>
								- 품절임박은 옵션 재고가 5개 이하 남은 제품을 의미합니다.<br>
								- 재고는 재고 관리에서 수정해주세요.
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
						<th>재고 수량</th>
						<th>상품 가격</th>
						<th>수정</th>
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
									<td>${vo.stock }</td>
									<td>${vo.productPrice }</td>
									<td>
										<button type="button" onclick="location.href='productUpdateDetail.do?productNum=' + ${vo.productNum };" class="btn btn-sm btn-outline-primary font-weight-bold" style="margin:10px;">
										수정
										</button>
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

	// 카테고리 ajax 처리
	function category_select(categoryN) {
		var categoryNumber = $(categoryN).val();
		
		$.ajax({
			  type:'POST',
			  url:'category.do',
			  data:{categoryNum: categoryNumber},
			  dataType : "json",
			  success: function(args) {
				let alist = args.list;
				let str = "";
				str +=  "<option value=''>세부 카테고리 선택</option>";
				$.each(alist, function(index, vo){
					if (categoryNumber == this.categoryNum){
						str += "<option value='" + this.categoryDetailNum + "'>" + this.categoryDetail + "</option>";
					}
				});
				$("#categoryDetail").html(str);
			  },
			  error : function(jqXHR, textStatus, errorThrown) {
			  }
		});
					
	}
	
	
	function sendData(tfm) {

		// 검색 조건 입력
		let firstForm = document.forms[0];
		let formNum = firstForm.elements.length - 2;
		
		// 상품 번호 값 제한
		var productNum= firstForm.elements[3].value;
		var productNumTrim= firstForm.elements[3].value.trim();
		if (productNumTrim != "") {
			if (productNum > 9999999 || productNum < 1){
				Swal.fire('상품 번호는 1부터 9999999까지 검색 가능합니다.');
				return;
			}
		}
		
		// 검색 조건 중복 선택 불가 (카테고리 제외)
		
		var selCom = 0;
	    for (let i = 0; i < formNum; i++) {
	    	var valueCheck1 = firstForm.elements[i].value.trim();
	    	var value1 = firstForm.elements[i].value;
	    	var name1 = firstForm.elements[i].name;
	    	
	    	for (let t = i + 1; t < formNum; t++) {
	    		var valueCheck2 = firstForm.elements[t].value.trim();
	    		var value2 = firstForm.elements[t].value;
	    		var name2 = firstForm.elements[t].name;
	    		
    			if (i == 1 && t == 2) {
    				if (valueCheck1 != "" && valueCheck2 != ""){
	    				searchInputList = {
	    						"categoryDetail" : value2
	    				};
	    				selCom = 1;
    				}
    			} else if (valueCheck1 != "" && valueCheck2 != "") {
    				Swal.fire('검색 조건은 하나만 입력해주세요.');
					return;
    			} else if (valueCheck1 != "") {
    				if (i == 3){
    					selCom = 2;
    					searchInputList = {
        						"productNum" : value1
        				};
    				} else if (i == 4){
    					selCom = 3;
    					searchInputList = {
    							"productName" : value1
        				};
    				}
    			} 
	    	}
	    }
	    
	    
	}
	
	
	
</script>
</body>
</html>