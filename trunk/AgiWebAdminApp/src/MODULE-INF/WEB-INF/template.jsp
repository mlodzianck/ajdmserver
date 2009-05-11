<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>ajdmserver</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<link href="<c:url value="/css/css_uax.css"/>" rel="stylesheet" type="text/css"/>
<link rel="icon" type="image/png" href="<c:url value="/images/animated_favicon1.gif"/>"/>

</head>
<body>

<!--main_end-->
<div id="main">


<div id="top">
<!-- 
<a href="#">Link3</a> <a href="#">Link2</a> <a
	href="#">Link1</a>
 -->
</div>
<div id="header">


<div id="banner">
<div class="logo"><a href="<c:url value="/app"/>">ajdmserver</a></div>
</div>



<div id="content"><!--navi_left-->
<div class="col1">

<h4 class="sidelinksheader clear">MENU</h4>
<ul>
	<li><a href="<c:url value="/app"/>">Home </a></li>
	
	<li><a href="<c:url value=""> 
					<c:param name="action" value="getServers"/>
				</c:url>">Configured Servers</a></li>
	
	<li><a href="<c:url value=""> 
					<c:param name="action" value="getScripts"/>
				</c:url>">Configured Scripts</a></li>
	
	<li><a href="<c:url value=""> 
					<c:param name="action" value="getRunningScripts"/>
				</c:url>">Running Scripts </a></li>
</ul>

<br />

<h4 class="sidelinksheader clear">MISC</h4>
<ul>
	<li><a href="http://code.google.com/p/ajdmserver/w/list">Documentation</a></li>
	<li><a href="http://asterisk-java.org">Asterisk-Java Home Page</a></li>


</ul>

</div>



<!--content-->
<div class="col2">

<h4 class="title1"><c:out value="${pageTitle}" /></h4>


<!--div dla tekstowych tresci-->
<div id="text">

<c:import url="${contentPage}" />

</div>



</div>


</div>
</div>



<div id="footer"><a class="left" href="http://www.ajdmserver.pl/"
	target="_blank">&copy;2009 ajdmserver</a> <a class="right"
	href="http://www.satanandco.pl/" target="_blank">Satan&amp;Co.</a></div>


</div>
<!--main_end-->


</body>
</html>