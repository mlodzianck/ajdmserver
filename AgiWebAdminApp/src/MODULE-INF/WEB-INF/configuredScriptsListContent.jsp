<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<div align="center">
<table class="serverslist">
	<c:forEach items="${scriptsList}" var="server">
		<tr>
			<table>
				<td>
					<table>
						<tr>
							<td colspan="2" align="center" class="sidelinksheader">
								<b>Script </b>${scriptName}
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
				
				
		</tr>


				<tr>
					<td colspan="2">
							<hr width="80%" align="center" size="1">
					</td>
				</tr>

				
				</c:forEach>





			</table>
</div>