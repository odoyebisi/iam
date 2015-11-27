<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="Identity management system">
<meta name="author" content="Oluwabusayo">
<link rel="icon" href="../../favicon.ico">

<title>IAM</title>

<spring:url value="/resources/css/bootstrap-3.2.0.min.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<spring:url value="/resources/css/custom.css" var="customCss" />
<link href="${customCss}" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="resources/css/cover.css" rel="stylesheet">

</head>

<body>

	<div class="site-wrapper">

		<div class="site-wrapper-inner">

			<div class="cover-container">

				<div class="masthead clearfix">
					<div class="inner">
						<h3 class="masthead-brand">IAM</h3>
						<nav>
							<ul class="nav masthead-nav">
								<li class="active"><a href="#">Home</a></li>
								<li ><a href="login/">Login</a></li>
							</ul>
						</nav>
					</div>
				</div>

				<div class="inner cover">
					<h1 class="cover-heading">IAM Spring Project</h1>
					<p class="lead">Welcome to my identity management system.
					</p>
					<p class="lead">
						<a href="login/" class="btn btn-lg btn-default">Login</a>
					</p>
				</div>

				<div class="mastfoot">
					<div class="inner">
						<p>
							Submitted for <a
								href="http://thomas-broussard.fr/work/java/courses/project/advanced.xhtml">Java
								final project</a> by <a href="http://www.oluwabusayo.com">Daniel Oyebisi</a>.
						</p>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="resources/js/bootstrap.min.js"></script>
	
</body>
</html>
