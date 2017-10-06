<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>

<spring:url value="/resources/core/css/scen0.css" var="scen0css" />
<link href="${scen0css}" rel="stylesheet" />

<spring:url value="/resources/core/js/scen0.js" var="scen0js" />
<script src="${scen0js}"></script>

<div id="bg" class="bg" >
<div id="playership" class="ship">

</div>
<div id="usership" class="ship">

</div>


</div>

<form id="scen0form" > 
<input type="hidden" id="username" name="username" value="${username}" >
<input type="hidden" id="playername" name="playername" value="${playername}" >
</form>