<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Spring MVC 4 + Ajax Hello World</title>

<c:url var="home" value="/" scope="request" />

<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />


<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs" />
<spring:url value="/resources/core/js/welcome.js"
	var="welcomeJs" />
<script src="${jqueryJs}"></script>
<script src="${welcomeJs}"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 4 Ajax Test.</a>
		</div>
	</div>
</nav>

<div class="container" style="min-height: 500px">

	<div class="starter-template">
		<h1>Login</h1>
		<br>

		<div id="feedback"></div>

		<form class="form-horizontal" id="search-form"  >
			<div class="form-group form-group-lg" id="usernamelabel" >
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type=text class="form-control" id="username" name="username">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" >
					<button id="bth-search" class="btn btn-primary btn-lg">Login</button>
				</div>
			</div>
		</form>

	</div>

</div>

<script>

</script>

</body>
</html>