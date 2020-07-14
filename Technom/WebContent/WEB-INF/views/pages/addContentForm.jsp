<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
	<form
		action="${pageContext.request.contextPath }/uploadFile?${_csrf.parameterName}=${_csrf.token}"
		method="POST" enctype="multipart/form-data">
		<input type="file" name="imageFile" /> <input type="submit"
			value="آپلود" class="btn btn-secondary" /> <br>
	</form>
	<br>
	<form:form action="${pageContext.request.contextPath }/addContent"
		modelAttribute="contentModel" method="POST">
		<input type="hidden" name="writer.userName" id="writer.userName"
			value='<security:authentication property="principal.username" />' />
		<form:input path="title" PlaceHolder="Title" class="form-control" required="required"/>
		<br>

		<div>
			<form:textarea path="htmlContent"
				style="width:1200px ; height: 600px;" class="form-control z-depth-1" />
			
		</div>
		<br>
		<input type="submit" value="ثبت محتوای جدید" class="btn btn-success" />
		<br>
	</form:form>


</div>
