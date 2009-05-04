<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AJ-DM Admin</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="<c:url value="/css/css_uax.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<div id="main">
<div id="header">


<div id="banner">
<div class="logo"><a
	href="http://www.maadesigns.co.uk/services/identity-designs/logo-design-services.htm">Asterisk-Java
dmServer Bundle admin panel</a></div>

</div>

<div id="content">
<div class="col1">
<div class="sidelinksheader">Side Links</div>
<ul>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/book_illustrations.htm"
		target="_blank">Book Illustration</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/portrait_illustrations_service.htm"
		target="_blank">Portraits Illustration</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/fashion_illustrations_service.htm"
		target="_blank">Fashion Illustration</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/digital_illustrations.htm"
		target="_blank">Digital Illustration</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/cartoon_character_drawing.htm"
		target="_blank">Cartoon Illustration</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/technical_illustrations_illustrations_service.htm"
		target="_blank">Technical Illustrations</a></li>

	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/story_board_illustrations_service.htm"
		target="_blank">Story Board Illustrations</a></li>
	<li><a
		href="http://www.maadesigns.co.uk/services/illustrations/medical_illustration_service.htm"
		target="_blank">Medical Illustrations</a></li>


</ul>
</div>
<div class="col2"><span class="title1">&#9674; <c:out
	value="${pageTitle}" /></span>
	<c:import url="${contentPage}"/>


</div>


</div>
</div>
<div id="footer">:: <a href="#">Home</a> :: <a href="#">About
Us</a> :: <a href="#">Services</a> :: <a href="#">Products</a> :: <a
	href="#">Contact Us</a> :: <a
	href="http://www.maadesigns.co.uk/services/web-design-services.htm"
	target="_blank">website design</a> by <a
	href="http://www.maadesigns.co.uk/" target="_blank">www.maadesigns.co.uk</a>
</div>

</div>
</body>
</html>