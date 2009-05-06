<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%-- 
--%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- 
--%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><%-- 
--%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%><%-- 
--%>
<div align="center">
<table class="serverslist">
	
		<tr>
			
			<table>
				<td>
					<table>
						<tr>
							<td colspan="2" align="center" class="sidelinksheader">
								<b>Script Detail </b><c:out value="${script['Script ID']}" />
							</td>
						</tr>
						<c:forEach items="${script}" var="val">
						<tr>
							<td class="serverattrname"><c:out value="${val.key}" /></td>
							<td><c:out value="${val.value}" /></td>
						</tr>
						</c:forEach>
					</table>
				</td>
				
				
		</tr>
		<tr>
			
			<table>
			
				<td>
					<table>
						<tr>
							<td colspan="2" align="center" class="sidelinksheader">
								<b>Raw Agi request  </b><c:out value="${script['Script ID']}" />
							</td>
						</tr>
						<c:forEach items="${scriptRaw}" var="val">
						<tr>
							<td class="serverattrname"><c:out value="${val.key}" /></td>
							<td><c:out value="${val.value}" /></td>
						</tr>
						</c:forEach>
					</table>
				</td>
				</table>
				
		</tr>
		
		
		

				<tr>
				<td  align="center">
							&nbsp;</td>
				
					<td  align="center">
							<a
							href="<c:url value="/app">
						<c:param name="action" value="killScript"/>
						<c:param name="scriptid" value="${script['Script ID']}"/>
							</c:url>"
							class="actionlink">Kill script</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
							<hr width="80%" align="center" size="1">
					</td>
				</tr>
				

				
			





			</table>
</div>