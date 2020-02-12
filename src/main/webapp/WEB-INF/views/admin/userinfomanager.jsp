<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>유저정보 관리 - My Shop</title>

	<!--
            CSS
            ========================================================= -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/linearicons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themify-icons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nice-select.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nouislider.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<script type="text/javascript">

</script>
<body id="category">
	<!-- Start Header Area -->
	<%@include file="../include/header.jsp"%>
	<!-- End Header Area -->
	<%@include file="include/manager.jsp" %>
	
		<!-- modal alert area Start -->
   		<div class="modal fade" id="kickOutmodalLong" tabindex="-1" role="dialog" aria-labelledby="modalLongTitle" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle">강제탈퇴 요청</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <form name="modalForm" action="${pageContext.request.contextPath}/kickOut" method="post">
		      <div class="modal-body">
		      	<table>
			      	<tr>
			      		<th colspan="2">회원을 강제 탈퇴시키겠습니까?</th>
			      	</tr>
			      	<tr>
			      		<td>관리자 비밀번호 확인</td>
			      		<td><input type="password" name="modalPassword"></td>
			      	</tr>
			      	<tr>
			      		<td colspan="2"><span id="modalMessage" class="message"></span></td>
			      	</tr>
		      	</table>
		      </div>
		      <div class="modal-footer">
		      		<input type="hidden" name="email">
			        <button id="btnOk" type="button" class="btn btn-primary" >네</button>
			        <button id="btnNo" type="button" class="btn btn-secondary" data-dismiss="modal">아니요</button>
		      </div>
		      </form>
		    </div>
		  </div>
		</div>	
 		<!-- modal alert area End -->
 		
			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					<!-- check password area Start -->
					<!-- check password area End -->
					<!-- print UserData area Start -->
					<h2 class="title">유저정보 관리</h2>
						<div class="container">
				            <div class="cart_inner">
			                    <table class="product-table text-center">
			                    	<colgroup>
			                    		<col width="10%">
			                    		<col width="20%">
			                    		<col width="15%">
			                    		<col width="15%">
			                    		<col width="25$">
			                    		<col width="10%">
			                    		<col width="5%">
			                    	</colgroup>
			                    	<thead>
			                    		<tr>
			                    			<th>번호</th>
			                    			<th>이메일</th>
			                    			<th>이름</th>
			                    			<th>전화번호</th>
			                    			<th>가입일</th>
			                    			<th>회원유무</th>
			                    			<th>탈퇴</th>
			                    		</tr>
			                    	</thead>
			                    	<tbody id="userInfoClear">
		                    		<c:set var="userInfoLength" value="${fn:length(userInfo)}"/>
			                    	<c:choose>
				                    	<c:when test="${userInfoLength == 0}">
				                    		<tr>
				                    			<td colspan="6">회원정보가 존재하지 않습니다.</td>
				                    		</tr>
				                    	</c:when>
				                    	<c:otherwise>
				                    		<c:forEach var="user" items="${userInfo}" varStatus="status">
					                    		<tr>
					                    			<td>${userInfoCount - (pagination.page-1)*pagination.length - status.index}</td>
					                    			<td>${user.email}</td>
					                    			<td>${user.name}</td>
					                    			<td>${user.tel}</td>
					                    			<td>${user.regdate}</td>
					                    			<c:choose>
						                    			<c:when test="${user.withdrawal == 0}">
					                    					<td>회원</td>
							                    			<td><a class="btnModal" href="#kickOutmodalLong" data-toggle="modal"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
						                    			</c:when>
						                    			<c:otherwise>
						                    				<td>탈퇴회원</td>
							                    			<td></td>
						                    			</c:otherwise>
					                    			</c:choose>
					                    		</tr>
				                    		</c:forEach>
				                    	</c:otherwise>	
			                    	</c:choose>
			                    	</tbody>
			                    </table>
			                </div>
		                </div>
					</div>
				</section>
				<nav class="blog-pagination justify-content-center d-flex">
	                 <ul class="pagination">
	                 	<c:if test="${pagination.currentBlock != 1 }">
	                     <li class="page-item">
	                         <a href="userInfoManager?page=${pagination.startPage - pagination.pageLength}" class="page-link" aria-label="Previous">
	                             <span aria-hidden="true">
	                                 <span class="lnr lnr-chevron-left"></span>
	                             </span>
	                         </a>
	                     </li>
	                     </c:if>
	                     
	                     <c:forEach var="page" begin="${pagination.startPage}" end="${pagination.endPage}">
	                     	<li class="page-item <c:if test='${pagination.page == page}'>active</c:if>"><a href="userInfoManager?page=${page}" class="page-link">${page}</a></li>
	                     </c:forEach>
	                     
	                     <c:if test="${pagination.currentBlock != pagination.totalBlock}">
	                     <li class="page-item">
	                         <a href="userInfoManager?page=${pagination.endPage+1}" class="page-link" aria-label="Next">
	                             <span aria-hidden="true">
	                                 <span class="lnr lnr-chevron-right"></span>
	                             </span>
	                         </a>
	                     </li>
	                     </c:if>
	                 </ul>
                 </nav>
				<!-- End Best Seller -->
			</div>
		</div>
	</div>
	<!-- start footer Area -->
	<%@include file="../include/footer.jsp" %>
	<!-- End footer Area -->

	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/vendor/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/nouislider.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
	<!--gmaps Js-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gmaps.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/userinfo.js"></script>
</body>
</html>