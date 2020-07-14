<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/signin.css"
	title="style" />

<div class="text-center">
	<c:if test="${errorMessage !=null }">
		<h5 class="text-danger">${errorMessage }</h5>
	</c:if>

	<br>
	<form:form action="${pageContext.request.contextPath}/resetPassword"
		method="post" modelAttribute="user" class="form-signin">

		<input type="hidden" value="resetPassword001" id="fullName"
			name="fullName" />
		<form:errors path="fullName" class="text-warning"></form:errors>
		<form:input path="userName" PlaceHolder="نام کاربری"
			class="form-control" />
		<form:errors path="userName" class="text-warning"></form:errors>

		<form:input path="email" PlaceHolder="ایمیل" class="form-control" />
		<form:errors path="email" class="text-warning"></form:errors>

		<form:password path="password" PlaceHolder="رمز ورود جدید"
			class="form-control" />
		<form:errors path="password" class="text-warning"></form:errors>

		<form:password path="confirmPassword" PlaceHolder="تایید رمز ورود"
			class="form-control" />
		<!-- <form:label path="enabled">فعال:</form:label>
		<form:checkbox path="enabled" />
		<br />
		<form:label path="authority.authorityType">اختیار:</form:label>
		<form:select path="authority.authorityType">
			<form:options items="${ authorities}" />
		</form:select>
-->
		<input type="submit" value="ثبت"
			class="btn btn-lg btn-success btn-block" />
	</form:form>
</div>
