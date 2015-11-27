<%@ include file="../fragments/header.jsp"%>
 
<body class="grey">

    <div class="container">

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		
      <form class="form-signin" name='loginForm'
			action="<c:url value='/j_spring_security_check' />" method='POST'>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Username</label>
        <input type="text" id="inputUsername"  name='username' class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword"  name='password' class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        
       <%--  <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>
      </form>

    </div> <!-- /container -->


  <%@ include file="../fragments/footer.jsp"%>
