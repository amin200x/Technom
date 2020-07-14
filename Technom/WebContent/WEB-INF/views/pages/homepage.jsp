<%@page
	import="org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<div class="container-fluid ">

	<div class="row justify-content-center ">
		<c:forEach var="item" items="${contents }">
			<div class="col-md-3 mb-4 card text-right " >
				<h4 class="d-flex justify-content-end text-secondary">${item.title }</h4>
				<h6 class="d-flex justify-content-start text-success">${item.persianDate }</h6>

				<c:set var="param1" value="src=\""></c:set>
				<c:set var="param2" value="\""></c:set>
				<c:set var="subStr1"
					value="${fn:substringAfter(item.htmlContent, param1)} "></c:set>
				<c:set var="imageUrl" value="${fn:substringBefore(subStr1, param2)}"></c:set>

				<c:choose>
					<c:when test="${ fn:length(imageUrl) > 0 }">
						<img alt="${item.title }" src="${ imageUrl}"
							class="rounded float-right img-thumbnail"
							style="height: 250px; width: 350px; align-self: center">

					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/images/noimage.jpg" alt="بدون تصویر"
						class="rounded float-right img-thumbnail" style="height: 250px; width: 350px; align-self: center">
					</c:otherwise>

				</c:choose>

				<p>
					<sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasRole('ADMIN')">
							<a class="btn btn-warning" role="button"
								href="${pageContext.request.contextPath }/updateContentForm/${item.id}">ویرایش</a>
					&nbsp;&nbsp; <a class="btn btn-danger" role="button"
								href="${pageContext.request.contextPath }/deleteContent/${item.id}"
								onclick="if(confirm('Do you really want to delete') == false) return false;">حذف</a>
						</sec:authorize>
					</sec:authorize>
					&nbsp;&nbsp; <a class="btn btn-primary"
						href="${pageContext.request.contextPath}/showContent/${item.id}"
						role="button">بیشتر</a>
				</p>
			</div>&nbsp;&nbsp;
		</c:forEach>
	</div>

</div>


<!-- logoutUrl -->
