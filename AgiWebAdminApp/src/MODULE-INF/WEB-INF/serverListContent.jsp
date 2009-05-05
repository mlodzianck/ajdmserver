<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<div align="center">
<table class="serverslist">
	<c:forEach items="${serversList}" var="server">
		<tr>
			<table>
				<td>
					<table>
						<tr>
							<td colspan="2" align="center" class="sidelinksheader">
								<b>Server <c:out value="${server['serverDomain']}" /></b>
							</td>
						</tr>
						<c:forEach items="${server}" var="val">
						<tr>
							<td class="serverattrname"><c:out value="${val.key}" /></td>
							<td><c:out value="${val.value}" /></td>
						</tr>
						</c:forEach>
					</table>
				</td>
				<td>
				<table>
					<tr>
						<td align="center"><a
							href="<c:url value="/app">
						<c:param name="action" value="getRunningScripts"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
						</c:url>"
							class="actionlink"> Get running scripts for domain </a></td>
					</tr>
					<tr>
						<td align="center"><a
							href="<c:url value="/app">
						<c:param name="action" value="getScripts"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
							</c:url>"
							class="actionlink">Get configured scripts for domain </a></td>
					</tr>
				</table>
				</td>
		</tr>


				<tr>
					<td colspan="2">
							<hr width="80%" align="center" size="1">
					</td>
				</tr>

				
				</c:forEach>





			</table>
</div>