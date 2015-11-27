<spring:url value="/identity/add" var="urlAddIdentity" />
<spring:url value="/identity/search" var="urlSearchIdentity" />
<spring:url value="/identity/" var="urlIdentity" />

<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/iamweb/identity"> IAM </a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="${urlIdentity}">Home</a></li>
					<li><a href="${urlAddIdentity }">Add Identity</a></li>
					<li><a href="${urlSearchIdentity }">Search Identity</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<button type="button" class="btn btn-danger navbar-btn"
							onclick="formSubmit()">
							<span class="glyphicon glyphicon-remove"></span> Logout
						</button>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
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