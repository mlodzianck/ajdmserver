<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<div align="center">
<table>
	<tbody>
		<c:forEach items="${serversList}" var="server">
			<tr>
				<td>
				<table border="1" cellspacing="0" cellpadding="3" frame="hsides"
					rules="rows" width="100%">
					<tbody>
						<tr>
							<td colspan="2" class="sidelinksheader2" align="center"><b>Server
							<c:out value="${server['serverDomain']}" /></b></td>
							<c:forEach items="${server}" var="val">
								<tr>
									<td class="serverattrname"><c:out value="${val.key}" /></td>
									<td><c:out value="${val.value}" /></td>
								</tr>
							</c:forEach>
						</tr>


					</tbody>
				</table>
				</td>
			</tr>
			<tr><td>
			<table class="snavi"> 
				<tr>
				<td><a
					href="<c:url value="/app">
						<c:param name="action" value="getRunningScriptsForDomain"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
						</c:url>"
					class="actionlink"> Get running scripts </a></td>
					<td>
					 <a
					href="<c:url value="/app">
						<c:param name="action" value="getScriptsForDomain"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
							</c:url>"
					class="actionlink">Get configured scripts </a> 
					</td>
					<td>
					<a
					href="<c:url value="/app">
						<c:param name="action" value="removeServer"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
							</c:url>"
					class="actionlink">Remove server </a> 
					</td>
					<td>
					<a
					href="<c:url value="/app">
						<c:param name="action" value="restartServer"/>
						<c:param name="domain" value="${server['serverDomain']}"/>
							</c:url>"
					class="actionlink">Restart server</a></td>
					</tr>
					</table>
			</td></tr>
			<tr><td>&nbsp;</td></tr>
		</c:forEach>
		<tr>
			<td colspan="2" align="center"><a class="aimp"
				href="<c:url value="/app">
						<c:param name="action" value="reinstallServers"/>
							</c:url>"
				class="actionlink">Reinstall all servers</a></td>

		</tr>
	</tbody>
</table>


</div>