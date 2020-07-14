<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript"
	src="${pageContext.request.contextPath }/contenteditor/nicEdit.js"></script>
<script type="text/javascript">
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()

	});
</script>
<div
	class="row mx-auto justify-content-center align-items-center flex-column ">
	<br>

	<div style="margin: 400px, 500px, 5px, 300px">
		<form:form action="${pageContext.request.contextPath }/updateContent"
			modelAttribute="contentModel" method="POST">
			<input type="hidden" name="writer.userName" id="writer.userName"
				value='<security:authentication property="principal.username" />' />
			<form:hidden path="id" />


			<form:input path="title" PlaceHolder="عنوان" class="form-control" />
			<br>
			<div>
				<form:textarea path="htmlContent"
					style="width:1200px ; height: 600px;"
					class="form-control z-depth-2" />

			</div>
			<input type="submit" value="ویرایش محتوا" class="btn btn-info">
			<br>
			<br>


			<c:if test="${contentModel.createdTime!=null }">
				<form:hidden path="createdTime" />
			</c:if>
			<c:if test="${contentModel.editedTime!=null }">
				<form:hidden path="editedTime" />
			</c:if>
		</form:form>
	</div>



</div>
