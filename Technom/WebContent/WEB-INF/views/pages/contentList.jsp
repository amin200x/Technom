<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>عنوان محتوا</th>
			<th scope="col">نظرات</th>
			<th>عملیات</th>
		</tr>
	</thead>
	<c:forEach var="item" items="${contents }" varStatus="itemIndex">
		<tr <c:if test="${itemIndex.count%2 == 0 }"> class="table-info"</c:if>
			class="table-light">
			<td scope="row"><a
				href="${pageContext.request.contextPath }/showContent/${item.id}">${item.title }</a>
			</td>
			<td><a
				href="${pageContext.request.contextPath }/comments/${item.id}">لیست
					نظرات</a></td>

			<td><a
				href="${pageContext.request.contextPath }/updateContentForm/${item.id}">ویرایش</a>
				| <a
				href="${pageContext.request.contextPath }/deleteContent/${item.id}"
				onclick="if(confirm('Do you really want to delete') == false) return false;">حذف</a>
			</td>
		</tr>

	</c:forEach>
</table>
