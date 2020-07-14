<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h2>${contentModel.title }</h2>
${contentModel.htmlContent }


<form:form action="${pageContext.request.contextPath }/updateComment"
	method="post" modelAttribute="contentModel">
	<form:hidden path="id" />
	<form:hidden path="title" />
	<form:hidden path="htmlContent" />
	<c:if test="${contentModel.createdTime!=null }">
		<form:hidden path="createdTime" />
	</c:if>
	<c:if test="${contentModel.editedTime!=null }">
		<form:hidden path="editedTime" />
	</c:if>
	<input type="hidden" name="writer.userName" id="writer.userName"
		value="${contentModel.writer.userName }" />
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>نظرها</th>
				<th>پاسخ به نظرها</th>
				<th>عمل ها</th>
			</tr>
		</thead>
		<c:forEach items="${contentModel.comments}" var="comment"
			varStatus="commentIndex">
			<tr
				<c:if test="${commentIndex.index%2 == 0 }"> class="table-light"</c:if>
				class="table-primary">
				<td>${commentIndex.count}
				<form:hidden	path="comments[${commentIndex.index}].id" /> 
				<form:hidden	path="comments[${commentIndex.index}].comment" /> 
					<input type="hidden"
					name="comments[${commentIndex.index}].contentModel.id" id="comments[${commentIndex.index}].contentModel.id"
					value="${comment.contentModel.id }" />
						<input type="hidden"
					name="comments[${commentIndex.index}].user.userName" id="comments[${commentIndex.index}].user.userName"
					value="${comment.user.userName }" /> <c:if
						test="${comment.createdTime!=null }">
						<form:hidden path="comments[${commentIndex.index}].createdTime" />
					</c:if>

				</td>

				<td><div>${comment.comment }</div></td>
				<td><c:forEach var="reply" items="${comment.replies }"
						varStatus="replyIndex">
						<div>
							<form:hidden
								path="comments[${commentIndex.index}].replies[${replyIndex.index}].id" />
								<form:hidden
								path="comments[${commentIndex.index}].replies[${replyIndex.index}].replyComment" />
					<input type="hidden"
					name="comments[${commentIndex.index}].replies[${replyIndex.index}].user.userName" id="comments[${commentIndex.index}].replies[${replyIndex.index}].user.userName"
					value="${reply.user.userName }" />
					<input type="hidden"
					name="comments[${commentIndex.index}].replies[${replyIndex.index}].commentModel.id" id="comments[${commentIndex.index}].replies[${replyIndex.index}].commentModel.id"
					value="${reply.commentModel.id }" />
					
					 <c:if
						test="${reply.repliedTime!=null }">
						<form:hidden path="comments[${commentIndex.index}].replies[${replyIndex.index}].repliedTime" />
					</c:if>			
								
								
							${reply.replyComment } |
							<form:checkbox
								path="comments[${commentIndex.index}].replies[${replyIndex.index}].visibility" />
							| <a
								href="${pageContext.request.contextPath }/deleteReply/${reply.id}/${contentModel.id}">Delete</a>
						</div>
					</c:forEach></td>
				<td><form:checkbox
						path="comments[${commentIndex.index}].visibility" /> | <a
					href="${pageContext.request.contextPath }/deleteComment/${comment.id}/${contentModel.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="submit" value="ثبت تغییرات" class="btn btn-warning" />
	<br>
</form:form>

