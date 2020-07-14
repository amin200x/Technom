<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/signin.css"
	title="style" />

<div class="text-center">
	<form:form action="${pageContext.request.contextPath}/updateUser"
		method="post" modelAttribute="user" class="form-signin">
		<form:input path="fullName" PlaceHolder="نام " class="form-control" />
		<form:errors path="fullName"></form:errors>
		<form:input path="userName" PlaceHolder="نام کاربری" readonly="true"
			class="form-control" />
		<form:errors path="userName"></form:errors>
		<form:input path="email" PlaceHolder="ایمیل" class="form-control" />
		<form:errors path="email"></form:errors>
		<form:hidden path="password" />
		<input type="hidden" name="confirmPassword" id="confirmPassword"
			value="${user.password }" />
		<form:label path="enabled" class="form-check-label">فعال</form:label>
		<form:checkbox path="enabled" class="form-check-inputl" />
		<form:select path="authority.authorityType" class="form-control">
			<form:options items="${ authorities}" />
		</form:select>
		<br>

		<button type="submit" class="btn btn-lg btn-success btn-block">بروز
			رسانی</button>
	</form:form>
</div>
