<%@ include file="../fragments/header.jsp"%>
<%@ include file="../fragments/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>Current user : ${pageContext.request.userPrincipal.name}</h2>
			</c:if>
		</div>
		<spring:url value="/identity" var="identityActionUrl" />

		<c:choose>
			<c:when test="${identityForm['new']}">
				<spring:url value="Add " var="actionName" />
				<spring:url value="identityForm" var="model" />
			</c:when>
			<c:otherwise>
				<spring:url value="Update " var="actionName" />
				<spring:url value="identityForm" var="model" />
			</c:otherwise>
		</c:choose>

		<div class="row">
			<h2>${actionName}Identity</h2>
		</div>
		<div class="row">

			<p>${actionName} an identity to the Identity Authentication
				Management system:</p>

			<form:form class="form-horizontal" method="post"
				modelAttribute="${model}" action="${identityActionUrl}">

				<c:if test="${action != 'search'}">
					<form:hidden path="id" />
				</c:if>

				<form:hidden path="id" />

				<spring:bind path="firstName">
					<div class="form-group">
						<label class="col-sm-2 control-label">First Name</label>
						<div class="col-sm-10">
							<form:input path="firstName" type="text" class="form-control "
								id="firstName" placeholder="First Name" />
							<form:errors path="firstName" class="control-label" />
						</div>
					</div>
				</spring:bind>

				<spring:bind path="lastName">
					<div class="form-group">
						<label class="col-sm-2 control-label">Last Name</label>
						<div class="col-sm-10">
							<form:input path="lastName" type="text" class="form-control "
								id="lastName" placeholder="Last Name" />
							<form:errors path="lastName" class="control-label" />
						</div>
					</div>
				</spring:bind>

				<spring:bind path="email">
					<div class="form-group">
						<label class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<form:input path="email" class="form-control" id="email"
								placeholder="Email" />
							<form:errors path="email" class="control-label" />
						</div>
					</div>
				</spring:bind>

				<c:if test="${action != 'search'}">
					<spring:bind path="birthDate">
						<div class="form-group">
							<label class="col-sm-2 control-label">Birth Date:</label>
							<div class="col-sm-10">
								<form:input type="date" path="birthDate" />
								<fmt:formatDate pattern="dd-MM-yyyy" value="${now}" />
								<form:errors path="birthDate" class="control-label" />
							</div>
						</div>
					</spring:bind>

				</c:if>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">

						<button type="submit" class="btn-lg btn-primary pull-right">${actionName}</button>

					</div>
				</div>

			</form:form>

		</div>
	</div>
	<%@ include file="../fragments/footer.jsp"%>