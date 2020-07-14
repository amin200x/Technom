<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>نام کاربری</th>
				<th>آدرس ایمیل</th>
				<th>عملیات ویرایش و حذف</th>
			</tr>

		</thead>

		<c:forEach items="${ users}" var="user" varStatus="userIndex">
			<tr
				<c:if test="${userIndex.index%2 == 0 }"> class="table-light"</c:if>
				class="table-success">
				<td>${ userIndex.count}</td>
				<td>${user.userName }</td>
				<td>${user.email }</td>
				<td><a
					href="${pageContext.request.contextPath }/updateUserForm/${user.userName}">ویرایش</a>
					| <a
					href="${pageContext.request.contextPath }/deleteUser/${user.userName}"
					onclick="if(confirm('Do you want to remove the user?') == false) return false;">حذف</a>
				</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>