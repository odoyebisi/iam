<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapjs" />
<script src="${bootstrapjs}"></script>
<spring:url value="/resources/js/hello.js" var="coreJs" />

<script src="${coreJs}"></script>

</body>
</html>
