<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<div align="center">
<table>
	<tbody>
		<c:forEach items="${scriptsList}" var="server">
			<tr>
				<td>
				<table border="1" cellspacing="0" cellpadding="3" frame="hsides"
					rules="rows" width="100%">
					<tbody>
						<tr>
							<td colspan="2" align="center" class="sidelinksheader2">
								<b>Script </b><c:out value="${server['Additional prop Bundle-SymbolicName']}"/>
							</td>
						</tr>
						<c:forEach items="${server}" var="val">
						<tr>
							<td class="serverattrname"><c:out value="${val.key}" /></td>
							<td><c:out value="${val.value}" /></td>
						</tr>
						</c:forEach>


					</tbody>
				</table>
				</td>
			</tr>
			
			<tr><td>&nbsp;</td></tr>
		</c:forEach>
		
	</tbody>
</table>


</div>