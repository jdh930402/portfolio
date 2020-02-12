<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<section class="banner-area organic-breadcrumb">
		<div class="container">
			<div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
				<div class="col-first">
					<h1>관리자페이지</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- End Banner Area -->
	
	<div class="container">
		<div class="row">
			<div class="col-xl-3 col-lg-4 col-md-5">
				<div class="sidebar-categories">
					<div class="head">관리자 메뉴</div>
					<ul class="main-categories">
						<li class="main-nav-list"><h4>회원관리</h4>
						<c:set var="servletPath" value="${ requestScope['javax.servlet.forward.servlet_path']}"/>
								<li class="main-nav-list child <c:if test="${servletPath eq '/userInfoManager'}">active</c:if>"><a href="${pageContext.request.contextPath}/userInfoManager">회원정보 조회</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/cancelreturn/list'}">active</c:if>"><a href="${pageContext.request.contextPath}/cancelreturn/list">구매액 상위 회원 조회</a></li>
						</li>

						<li class="main-nav-list"><h4>상품관리</h4>
								<li class="main-nav-list child <c:if test="${servletPath eq '/product/register'}">active</c:if>"><a href="${pageContext.request.contextPath}/product/register">등록 관리</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/addressManage'}">active</c:if>"><a href="${pageContext.request.contextPath}/addressManage" class="border-bottom-0">재고 관리</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/addressManage'}">active</c:if>"><a href="${pageContext.request.contextPath}/addressManage" class="border-bottom-0">Q&A 관리</a></li>
						</li>
						
						<li class="main-nav-list"><h4>주문관리</h4>
								<li class="main-nav-list child <c:if test="${servletPath eq '/login/userModify'}">active</c:if>"><a href="${pageContext.request.contextPath}/login/userModify">교환 관리</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/addressManage'}">active</c:if>"><a href="${pageContext.request.contextPath}/addressManage" class="border-bottom-0">환불 관리</a></li>
								<li class="main-nav-list child <c:if test="${servletPath eq '/addressManage'}">active</c:if>"><a href="${pageContext.request.contextPath}/addressManage" class="border-bottom-0">배송 관리</a></li>
						</li>
					</ul>
				</div>
			</div>	
