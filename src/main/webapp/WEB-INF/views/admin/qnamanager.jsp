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
	
			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					<!-- check password area Start -->
					<!-- check password area End -->
					<!-- print UserData area Start -->
					<h2 class="title">Q&A 관리</h2>
						<div class="container">
				            <div class="cart_inner">
			                    <table class="product-table text-center">
			                    	<colgroup>
										<col width="8%">
										<col width="12%">
										<col width="35%">
										<col width="20%">
										<col width="20%">
			                    	</colgroup>
			                    	<thead>
			                    		<tr>
			                    			<th>번호</th>
			                    			<th>답변상태</th>
			                    			<th>문의제목</th>
			                    			<th>작성자</th>
			                    			<th>작성일</th>
			                    		</tr>
			                    	</thead>
			                    	<tbody>
			                    		<c:set var="qnaLength" value="${fn:length(qna)}"></c:set>
										<c:if test="${fn:length(qna) > 0}">
										<c:forEach var="qna" items="${qna}" varStatus="status">
											<tr>
												<td>${qnaLength - ((pagination.page - 1) * 10 + status.index)}</td>
												<td>
													<c:if test="${qna.is_answer == 0}">답변대기</c:if>
													<c:if test="${qna.is_answer == 1}">답변완료</c:if>
												</td>

												<c:set var="titleLength" value="${fn:length(qna.title)}"/>
												<c:set var = "subTitle" value="${fn:substring(qna.title, 0, 15)}..."/>
												<td>
													<a href="${pageContext.request.contextPath}/qnamanager/qnaanswer?id=${qna.id}&gnum=${qna.gnum}">
														<c:if test="${titleLength > 15}">
															${subTitle}
														</c:if>
														<c:if test="${titleLength <= 15}">
															${qna.title}
														</c:if>
													</a>
												</td>
												
												<c:set var = "subEmail" value="${fn:substring(qna.user_email, 0, 2)}***"/>
												<c:set var="subDomain"  value="${fn:substring(qna.user_email, fn:indexOf(qna.user_email,'@'), fn:length(qna.user_email))}"/>
												<td>${subEmail}${subDomain}</td>
												<td>${qna.regdate}</td>
											</tr>

										</c:forEach>
									</c:if>
		                    		
		                    		
		                    		
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
	                     	<li class="page-item <c:if test='${pagination.page == page}'>active</c:if>"><a href="qnamanager?page=${page}" class="page-link">${page}</a></li>
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
	<script src="${pageContext.request.contextPath}/resources/js/qnamanager.js"></script>
</body>
</html>