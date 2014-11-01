<%@page import="com.asu.alliancebank.domain.impl.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false" %>

<style type="text/css">
.submit {
	background-color: #808080;
	color: #FFFFFF;
	width: 120px;
	height: 30px;
	border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	margin-bottom: 5px;
}
</style>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/form.css" />


<div class="container">
	<c:choose>
		<c:when test="${not empty authorizeList}">


				
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listaccount">
					<thead>
						<tr>
							<th align="left"></th>
							<th align="left">Merchant ID</th>
							<th align="left">User ID</th>																		
							<th align="left">Amount</th>
							<th align="left">Authorize</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="auth" items="${authorizeList}">
							<tr>								
								<td width="15%" align="left"><c:out value="${auth.merchantID}"></c:out></td>
								<td width="15%" align="left"><c:out value="${auth.userLoginID}"></c:out></td>								
								<td width="20%" align="center"><c:out value="${auth.amount}"></c:out></td>
								<td align="left"><input class="authorize" name="authorize" type="button"
					value="Approve" onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans/approve/${auth.requestID}'"></td>
								<td align="left"><input class="reject" name="reject" type="button"
					value="Reject" onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans/reject/${auth.requestID}'"></td>	
						</c:forEach>
					</tbody>
				</table>				
		</c:when>
		<c:otherwise>						
			<br />
			No Logs to Authorize
		</c:otherwise>
	</c:choose>
</div>
<br />