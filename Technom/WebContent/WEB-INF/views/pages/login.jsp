<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/signin.css"
	title="style" />


<div class="text-center">
	<form method="post" action="${loginUrl }" class="form-signin"
		id="loginForm">

		<h1 class="h3 mb-3 font-weight-normal">ورود به سایت</h1>
		<c:if test="${param.error != null }">
			<span style="color: red">نام کاربری یا رمز ورود اشتباه است!</span>
		</c:if>

		<label for="username" class="sr-only">نام کاربری</label> <input
			type="text" id="username" name="username" class="form-control"
			placeholder="نام کاربری" required autofocus> <label
			for="password" class="sr-only">رمز ورود</label> <input
			type="password" id="password" name="password" class="form-control"
			placeholder="رمز ورود" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" name='remember-me'>
				من را بخاطر بسپار
			</label>
		</div>
		<input name="${_csrf.parameterName }" type="hidden"
			value="${_csrf.token }" />

		<button class="btn btn-lg btn-primary btn-block" type="submit">ورود</button>
		<a href="addUserForm" class="text-info ">ثبت نام</a><br> <a
			href="resetPasswordForm" class="text-warning ">فراموشی رمز ورود!</a>
		<p class="mt-5 mb-3 text-muted">&copy; 2020</p>
	</form>

</div>


