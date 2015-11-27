<%@ include file="../fragments/taglibs.jsp"%>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
 <head>
 <meta name="description" content="Identity management system">
<meta name="author" content="Oluwabusayo">
<title>Identity Management System</title>

<spring:url value="/resources/css/bootstrap-3.2.0.min.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<spring:url value="/resources/css/custom.css" var="customCss" />
<link href="${customCss}" rel="stylesheet" />

</head>
<spring:url value="/" var="urlHome" />
<spring:url value="/identity/add" var="urlAddIdentity" />
<spring:url value="/identity/search" var="urlSearchIdentity" />
<spring:url value="/identity/" var="urlIdentity" />

