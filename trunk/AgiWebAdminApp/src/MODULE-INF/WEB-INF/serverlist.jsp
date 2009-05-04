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
		<h1>Servers list</h1>
		<br>
		<a
			href="<c:url value="servman2">
			<c:param name="action" value="reinstallServers"/>
		</c:url>"
			class="actionlink"> Reinstall all servers </a> <br>
		<br>
		<table class="serverslist">
			<c:forEach items="${serversList}" var="server">
				<tr>

					<td>
					<table>
						<tr>
							<td colspan="2" class="servertitle">Server :: <c:out
								value="${server['serverDomain']}" /></td>
						</tr>
						<c:forEach items="${server}" var="val">

							<tr>
								<td class="serverattrname"><c:out value="${val.key}" /></td>
								<td class="serverattrval"><c:out value="${val.value}" /></td>
							</tr>

						</c:forEach>

					</table>
					</td>
				</tr>
				<tr>
					<td align="center"><a
						href="<c:url value="servman2">
						<c:param name="action" value="getRunningScripts"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
						</c:url>"
						class="actionlink"> Get running scripts for domain </a></td>
				</tr>
				<tr>
					<td align="center"><a
						href="<c:url value="servman2">
						<c:param name="action" value="getScripts"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
				</c:url>"
						class="actionlink">Get configured scripts for domain </a></td>

				</tr>


			</c:forEach>
		</table>
		</td>
		<td width="10%" class="lrcolumn">&nbsp;</td>
	</tr>
</table>
</body>
</html>