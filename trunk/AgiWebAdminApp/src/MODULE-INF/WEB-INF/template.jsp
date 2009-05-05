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
<link href="<c:url value="/css/css_uax.css"/>" rel="stylesheet"
	type="text/css">
</head>
<body>
<div id="main">
<div id="header">


<div id="banner">
<div class="logo">Asterisk-Java dmServer Bundle<br />
admin panel</div>

</div>

<div id="content">
<div class="col1">
<div class="sidelinksheader">MENU</div>
<ul>
	<li><a href="<c:url value="/app"/>">Home </a></li>
	
	<li><a href="<c:url value=""> 
					<c:param name="action" value="getServers"/>
				</c:url>">Configured Servers</a></li>
	
	<li><a>Configured Scripts</a></li>
	
	<li><a>Running Scripts </a></li>
	
	<li>&nbsp;</li>
	
	<li><a href="http://code.google.com/p/ajdmserver/w/list">Documentation</a></li>


</ul>
</div>
<div class="col2"><span class="title1">&#9674; <c:out
	value="${pageTitle}" /></span><br>
<br>
<c:import url="${contentPage}" /></div>


</div>
</div>
<div id="footer"> <a
	href="http://www.maadesigns.co.uk/services/web-design-services.htm"
	target="_blank">website design</a> by <a
	href="http://www.maadesigns.co.uk/" target="_blank">www.maadesigns.co.uk</a>
</div>

</div>
</body>
</html>