<%@ include file="../fragments/header.jsp"%>
<%@ include file="../fragments/navbar.jsp"%>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<div class="container">
		<div class="row">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h2>Current user : ${pageContext.request.userPrincipal.name}</h2>
			</c:if>
		</div>

		<div class="row">
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
		</div>

		<div class="row">
			<h2>View Identity</h2>
			<p>View an identity in the Identity Authentication Management
				system:</p>
			<div class="row">
				<label class="col-sm-2">Id</label>
				<div class="col-sm-10">${identity.id}</div>
			</div>
			<div class="row">
				<label class="col-sm-2">First Name</label>
				<div class="col-sm-10">${identity.firstName}</div>
			</div>
			<div class="row">
				<label class="col-sm-2">Last Name</label>
				<div class="col-sm-10">${identity.lastName}</div>
			</div>
			<div class="row">
				<label class="col-sm-2">Email</label>
				<div class="col-sm-10">${identity.email}</div>
			</div>
			<div class="row">
				<label class="col-sm-2">Date</label>
				<div class="col-sm-10"><fmt:formatDate pattern="dd-MM-yyyy" 
            value="${identity.birthDate}" /></div>
			</div>
		</div>
	</div>
	<%@ include file="../fragments/footer.jsp"%>