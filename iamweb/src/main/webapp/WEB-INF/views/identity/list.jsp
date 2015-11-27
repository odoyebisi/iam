<%@ include file="../fragments/header.jsp"%>

<%@ include file="../fragments/navbar.jsp"%>

<div class="container">
	<div class="row">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>Current user : ${pageContext.request.userPrincipal.name}</h2>
		</c:if>


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

		<c:choose>
			<c:when test="${action == 'search'}">
				<spring:url value="Search Results " var="headerTitle" />
			</c:when>
			<c:otherwise>
				<spring:url value="Manage Identities " var="headerTitle" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty identities}">
				<h2>Sorry, there are no identities to display</h2>
				<br />
			</c:when>
			<c:otherwise>
				<h2>${headerTitle}</h2>
				<p>The list of identities in the IAM system:</p>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>#ID</th>
							<th>First name</th>
							<th>Last name</th>
							<th>Email</th>
							<th>Birth Date</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${identities}" var="identity">
							<tr>
								<td>${identity.id}</td>
								<td>${identity.firstName}</td>
								<td>${identity.lastName}</td>
								<td>${identity.email}</td>
								<td><fmt:formatDate pattern="dd-MM-yyyy"
										value="${identity.birthDate}" /></td>
								<td><spring:url value="/identity/${identity.id}"
										var="identityUrl" /> <spring:url
										value="/identity/${identity.id}/delete" var="deleteUrl" /> <spring:url
										value="/identity/${identity.id}/update" var="updateUrl" />

									<button class="btn btn-info"
										onclick="location.href='${identityUrl}'">View</button>
									<button class="btn btn-primary"
										onclick="location.href='${updateUrl}'">Update</button>
									<button class="btn btn-danger"
										onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br />
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@ include file="../fragments/footer.jsp"%>