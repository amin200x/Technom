<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="container-fluid ">
	<h6 class="d-flex justify-content-start text-success"
		style="font-family: Yekan;">
		<span class="text-danger">${contentModel.persianDate }
			&nbsp;&nbsp; </span>
		<c:if test="${contentModel.writer.fullName != null}">
			<span>نویسنده :${contentModel.writer.fullName}</span>
		</c:if>

	</h6>
	<div class="row justify-content-end">
		<div class="col-md-12 justify-content-end ">


			<h2>${ contentModel.title}</h2>
			<div>${contentModel.htmlContent }</div>

			<br>

			<form:form
				action="${pageContext.request.contextPath }/addComment/${contentModel.id }"
				method="post" modelAttribute="commentModel">
				<div class="form-group" style="margin-left: 70%">
					<form:hidden path="id" />
					<sec:authorize access="isAuthenticated()">
						<input type="hidden" name="user.userName" id="user.userName"
							value='<sec:authentication property="principal.username" />' />
					</sec:authorize>
				</div>
				<div style="margin-left: 30%">
					<form:textarea path="comment" PlaceHolder="نظر" required="true"
						class="form-control" rows="7" col="12" dir="rtl" />
				</div>
				<br>

				<input type="submit" value="ثبت نظر" class="btn btn-secondary">
			</form:form>


			<sec:authorize access="hasRole('ADMIN')">

				<form:form name="updateCommentReply"
					action="${pageContext.request.contextPath }/updateComment"
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


					<button type="submit" class="btn btn-warning"
						name="updateCommentReply">ثبت تغییرات</button>
					<c:forEach items="${contentModel.comments}" var="comment"
						varStatus="commentIndex">
						<div
							class="border border-primary rounded p-3 mb-2 bg-secondary text-white"
							style="margin-right: 10px; margin-left: 5px; background-color: #a4a4c1;">
							<div class="text-info">
								<h5>
									<span class="d-flex justify-content-end">${comment.user.fullName }</span>
									<span class="d-flex justify-content-start">${comment.persianDate }</span>
								</h5>


							</div>

							<h5 style="word-wrap: break-word;">${comment.comment }</h5>
							<form:hidden path="comments[${commentIndex.index}].id" />
							<form:hidden path="comments[${commentIndex.index}].comment" />
							<input type="hidden"
								name="comments[${commentIndex.index}].contentModel.id"
								id="comments[${commentIndex.index}].contentModel.id"
								value="${comment.contentModel.id }" /> <input type="hidden"
								name="comments[${commentIndex.index}].user.userName"
								id="comments[${commentIndex.index}].user.userName"
								value="${comment.user.userName }" />
							<c:if test="${comment.createdTime!=null }">
								<form:hidden path="comments[${commentIndex.index}].createdTime" />
							</c:if>


							<form:checkbox path="comments[${commentIndex.index}].visibility" />
							| <a
								href="${pageContext.request.contextPath }/deleteComment/${comment.id}/${contentModel.id}">Delete</a>
						</div>


						<c:forEach var="reply" items="${comment.replies }"
							varStatus="replyIndex">

							<form:hidden
								path="comments[${commentIndex.index}].replies[${replyIndex.index}].id" />

							<form:hidden
								path="comments[${commentIndex.index}].replies[${replyIndex.index}].replyComment" />
							<input type="hidden"
								name="comments[${commentIndex.index}].replies[${replyIndex.index}].user.userName"
								id="comments[${commentIndex.index}].replies[${replyIndex.index}].user.userName"
								value="${reply.user.userName }" />
							<input type="hidden"
								name="comments[${commentIndex.index}].replies[${replyIndex.index}].commentModel.id"
								id="comments[${commentIndex.index}].replies[${replyIndex.index}].commentModel.id"
								value="${reply.commentModel.id }" />
								 <c:if
						test="${reply.repliedTime!=null }">
						<form:hidden path="comments[${commentIndex.index}].replies[${replyIndex.index}].repliedTime" />
					</c:if>		
							<div
								class="border border-secondary rounded p-3 mb-2 bg-light text-dark"
								style="margin-right: 20px; margin-left: 10px;">
								<div class="text-info">
									<h5>
										<span class="d-flex justify-content-end">${reply.user.fullName }</span>
										<span class="d-flex justify-content-start">${reply.persianDate }</span>

									</h5>

								</div>
								<h5 style="word-wrap: break-word;">${reply.replyComment }</h5>

								<form:checkbox
									path="comments[${commentIndex.index}].replies[${replyIndex.index}].visibility" />
								| <a
									href="${pageContext.request.contextPath }/deleteReply/${reply.id}/${contentModel.id}">Delete</a>
							</div>

						</c:forEach>


					</c:forEach>

					<br>

				</form:form>


			</sec:authorize>
			<sec:authorize access="!hasRole('ADMIN')">
				<c:forEach var="comment" items="${contentModel.comments }"
					varStatus="commentIndex" >
					<c:if test="${comment.visibility == true }">
						
						<div
							class="border border-primary rounded p-3 mb-2 bg-secondary text-white"
							style="margin-right: 10px; margin-left: 5px; background-color: #a4a4c1;"">
							<div class="text-info">
								<h5>
									<span class="d-flex justify-content-end">${comment.user.fullName }</span>
									<span class="d-flex justify-content-start">${comment.persianDate }</span>
								</h5>


							</div>
							<h5 style="word-wrap: break-word;">${comment.comment }</h5>

						</div>

					<div id="replyForm${commentIndex.index}" name="replyForm">
						<form:form
							action="${pageContext.request.contextPath }/replyToComment/${comment.id }/${contentId }"
							method="post" modelAttribute="replyCommentModel" >
							<div class="form-group" style="margin-left: 70%">
								<sec:authorize access="isAuthenticated()">
									<input type="hidden" name="user.userName" id="user.userName"
										value='<sec:authentication property="principal.username" />' />
								</sec:authorize>


							</div>
							<div class="form-group" style="margin-left: 30%">
								<form:textarea path="replyComment" PlaceHolder="پاسخ  به نظر"
									required="true" class="form-control" rows="5" col="12"
									dir="rtl" />
							</div>

							<input type="submit" value="ثبت پاسخ" class="btn btn-secondary" />

						</form:form>
						</div>
						<a href="javascript:void(0);"
							onclick="showThisReplyForm('replyForm${commentIndex.index}', 'replyLink${commentIndex.index}')"
							name="replyLink" id="replyLink${commentIndex.index}">پاسخ</a>
						<c:forEach var="reply" items="${comment.replies }"
							varStatus="replyIndex">
							<c:if test="${reply.visibility == true }">
								<div
									class="border border-secondary rounded p-3 mb-2 bg-light text-dark"
									style="margin-right: 20px; margin-left: 10px;">
									<div class="text-info">
										<h5>
											<span class="d-flex justify-content-end">${reply.user.fullName }</span>
											<span class="d-flex justify-content-start">${reply.persianDate }</span>

										</h5>

									</div>
									<h5 style="word-wrap: break-word;">${reply.replyComment }</h5>
								</div>
							</c:if>

						</c:forEach>
						<br>
					</c:if>
				</c:forEach>
			</sec:authorize>
		</div>
	</div>
</div>




<script type="text/javascript">
	var replyForms = document.getElementsByName("replyForm");
	var replyLinks = document.getElementsByName("replyLink");
	
	for (var i = 0; i < replyForms.length; i++) {
		replyForms[i].style.display = "none";
	}

	function showThisReplyForm(formId, linkId ) {
		for (var i = 0; i < replyForms.length; i++) {
			replyForms[i].style.display = "none";
		}

		document.getElementById(formId).style.display = "inherit";
		document.getElementById(linkId).style.display = "none";
		//replyForms[index].style.display = "inherit";
		//replyLinks[index].style.display = "none";
	}
</script>