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
	<title>주문목록/배송조회 - My Shop</title>

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

<body id="category">

	<!-- Start Header Area -->
	<%@include file="include/header.jsp"%>
	<!-- End Header Area -->
	<%@include file="include/mypage.jsp" %>		

			<div class="col-xl-9 col-lg-8 col-md-7">
				<!-- Start Best Seller -->
				<section class="lattest-product-area pb-40">
					<div class="row">
					
					
					<!-- check password area Start -->
					<c:if test="${check != 'y'}">
					<h2 class="title">회원정보 확인</h2>
						<div class="container">
				            <div class="cart_inner">
				                <div class="table-responsive">
     	                  			<p><strong>${email}</strong>님의 정보를 보호하기위해 비밀번호를 재확인 합니다.</p>
     	                  			<form name="modifyForm" action="${pageContext.request.contextPath}/login/userModify" method="post">
					                    <table class="userModify-table">
					                    	<tbody>
					                    		<tr>
					                    			<td class="td_title">아이디(이메일)</td>
					                    			<td class="td_content">${email}</td>
					                    		</tr>
					                    		<tr>
					                    			<td class="td_title">비밀번호</td>
					                    			<td class="td_content"><input id="checkPass" type="password" name="password"> <span id="passwordMessage" class="message"></span></td>
					                    		</tr>
					                    	</tbody>
					                    </table>
			                    		<div class="btn-group">
			                    			<input type="hidden" name="check" value="y">
			                    			<a id="btnSubmit" class="gray_btn small_btn" href="confirm">확인</a></td>
			                    		</div>
				                    </form>
				                </div>
				            </div>
				        </div>
				    </c:if>
					<!-- check password area End -->
					
				<!-- print UserData area Start -->
				<c:if test="${check == 'y'}">
		    		
		    		<!-- modal alert area Start -->
		    		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLongTitle">회원탈퇴 요청</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <form name="modalForm" action="${pageContext.request.contextPath}/login/withdrawal" method="post">
					      <div class="modal-body">
					      	<table>
						      	<tr>
						      		<th colspan="2">탈퇴 시점으로 3개월간 동일 이메일로 가입 불가하며, 개인정보는 3개월간 유효합니다.</th>
						      	</tr>
						      	<tr>
						      		<td>비밀번호 확인</td>
						      		<td><input type="password" name="modalPassword"></td>
						      	</tr>
						      	<tr>
						      		<td colspan="2"><span id="modalMessage" class="message"></span></td>
						      	</tr>
					      	</table>
					      </div>
					      <div class="modal-footer">
						        <button id="btnOk" type="button" class="btn btn-primary" >네</button>
						        <button id="btnNo" type="button" class="btn btn-secondary" data-dismiss="modal">아니요</button>
					      </div>
					      </form>
					    </div>
					  </div>
					</div>	
		    		<!-- modal alert area End -->
					
					<h2 class="title">회원정보 수정</h2>
						<div class="container">
				            <div class="cart_inner">
				                <div class="table-responsive">
				                    <table id="userModify-table" class="userModify-table">
				                    	<tbody>
				                    		<tr>
				                    			<td class="td_title">아이디(이메일)</td>
				                    			<td class="td_content"><input class="content-input" type="email" name="email" value="${email}" readonly="readonly">
					                    			<div class="modify-btn"><a id="btnEmail" class="gray_btn small_btn" href="index">수정</a><a id="btnEmailCancel" class="gray_btn small_btn display-none" href="index">취소</a></div>
					                    			<div>
					                    				<span id="emailMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">비밀번호</td>
				                    			<td class="td_content">
					                    			<table>
					                    				<tr class="modify-pwd">
						                    				<td colspan="2"><input type="password" name="password" placeholder="현재 비밀번호 확인">
								                    			<div class="modify-btn">
						                    						<a id="btnPwd" class="gray_btn small_btn" href="index">수정</a>
						                    					</div>
						                    				</td>
					                    				</tr>
					                    				
					                    				<tr class="modify-rePwd display-none">
						                    				<td>신규 비밀번호</td>
						                    				<td><input type="password" name="newPassword"></td>
					                    				</tr>
					                    				<tr>
					                    					<td colspan="2"><span id="newPasswordMessage" class="message"></span></td>
					                    				</tr>
					                    				
					                    				<tr class="modify-rePwd display-none">
					                    					<td>신규 비밀번호 확인</td>
						                    				<td><input type="password" name="rePassword"></td>
					                    				</tr>
					                    				<tr>
					                    					<td colspan="2"><span id="rePasswordMessage" class="message"></span></td>
					                    				</tr>
					                    				
					                    			</table>
					                    			<div>
					                    				<span id="passwordMessage" class="message"></span>
					                    			</div>
					                    			<div class="modify-btnGroup modify-btn display-none">
						                    			<a id="btnPwdSave" class="gray_btn small_btn" href="index">저장</a>
						                    			<a id="btnPwdCancel" class="gray_btn small_btn" href="index">취소</a>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">이름</td>
				                    			<td class="td_content"><input class="content-input" type="text" name="name" value="${user.name}" readonly="readonly"> <span id="nameMessage" class="message"></span></td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">핸드폰번호</td>
				                    			<td class="td_content"><input class="content-input" type="text" name="tel" value="${user.tel}" readonly="readonly"> 
					                    			<div class="modify-btn">
						                    			<a id="btnTel" class="gray_btn  small_btn" href="index">수정</a>
						                    			<a id="btnTelCancel" class="gray_btn small_btn display-none" href="index">취소</a>
					                    			</div>
					                    			<div>
					                    			<span id="telMessage" class="message"></span>
					                    			</div>
				                    			</td>
				                    		</tr>
				                    		<tr>
				                    			<td class="td_title">가입일</td>
				                    			<td class="td_content"><input class="content-input" type="text" name="regdate" value="${user.regdate}" readonly="readonly"></td>
				                    		</tr>
				                    		
				                    	</tbody>
				                    </table>
		                    		<div class="btn-group">
		                    			<a id="btnCancel" class="blue_btn small_btn" href="index">나가기</a>
		                    			<a id="btnWithdraw" class="float-right" href="#exampleModalLong" data-toggle="modal">회원탈퇴</a>
		                    		</div>
			                	</div>
			                </div>
		                </div>
					
					</c:if>
					<!-- print UserData area End -->
					</div>
				</section>
				<!-- End Best Seller -->
			</div>
		</div>
	</div>
	<!-- start footer Area -->
	<%@include file="include/footer.jsp" %>
	<!-- End footer Area -->

	<script src="${pageContext.request.contextPath}/resources/js/vendor/jquery-2.2.4.min.js"></script>
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
	<script src="${pageContext.request.contextPath}/resources/js/usermodify.js"></script>
</body>

</html>