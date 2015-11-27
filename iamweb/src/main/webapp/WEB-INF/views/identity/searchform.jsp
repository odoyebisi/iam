<%@ include file="../fragments/header.jsp"%>

<%@ include file="../fragments/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>Current user : ${pageContext.request.userPrincipal.name}</h2>
			</c:if>
		</div>
		<spring:url value="/identity/search/result" var="identityActionUrl" />
		
		<div class="row">
			<h2>Search Identity</h2>
		</div>
		<div class="row">

			<p>Search an identity to the Identity Authentication
				Management system:</p>

			<form:form class="form-horizontal" method="post"
				modelAttribute="searchForm" action="${identityActionUrl}">
				
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
			
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">

						<button type="submit" class="btn-lg btn-primary pull-right">Search</button>

					</div>
				</div>

			</form:form>

		</div>
	</div>
	<%@ include file="../fragments/footer.jsp"%>