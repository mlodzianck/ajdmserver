<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Properties"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/style.css"/>">
</head>
<body>
<table class="maintable">
	<tr>
		<td colspan="2" align="center" ><h1>ajdm Admin page</h1>
		<br><c:out value="${param['action']}"/>  <br>
		<c:out value="${text}"/>  
		</td>
	</tr>
	<tr>
		<td width="10%" class="lrcolumn">&nbsp;</td>
		<td>
		<h1><c:out value="${pageTitle}"/></h1>
		<br>
		<br>
		<table class="serverslist">
		<pre>Total script count: <b><c:out value="${fn:length(scriptList)}"/></b></pre><br>
		<ol>
		<c:forEach items="${scriptList}" var="script">
			<li><p>${script}</p></li>
		</c:forEach>
		</ol>
		</table>
		</td>
		<td width="10%" class="lrcolumn">&nbsp;</td>
	</tr>
</table>
</body>
</html>