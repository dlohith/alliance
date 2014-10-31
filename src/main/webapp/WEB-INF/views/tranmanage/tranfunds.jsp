<%@page import="com.asu.alliancebank.domain.impl.TransferFunds"%>
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

<form:form name="transferfundsForm" autocomplete="off" class="form" method="POST" modelAttribute="transferFundsBackingBean" action="${pageContext.servletContext.contextPath}/auth/trans/tranfunds">
	<div id="form">
		<table>		
			<tr>
				<td width="20%"><label>From Account ID:</label></td>
				<td><form:input type='text' class="text" path='fromAccountId' name ='fromAccountId' disabled="true" value=''/></td>
				<td><font color="red"><form:errors path="fromAccountId" cssClass="errors"  /></font></td>
			</tr>
			<tr>
				<td width="20%"><label>To Account ID:</label></td>
				<td width="25%" align="center"><form:select class="select" path='toAccountId' name ='toAccountId' value=''/>
				<c:forEach var="username" items="${userNamesList}">
										<c:out value="${username}"></c:out> :
										</c:forEach></td>
				<td><font color="red"><form:errors path="toAccountId" cssClass="errors" /></font></td>
			</tr>				
			<tr>
				<td width="20%"><label>Amount:</label></td>
				<td><form:input type='text' class="text" path='amount' name ='amount' value=''/></td>
				<td><font color="red"><form:errors path="amount" cssClass="errors" /></font></td>
			</tr>
			
			<tr>
			<td></td>
				<td colspan='2' align="left"><input class="otpnext" name="Next" type="button"
					value="Next" onClick="location.href='${pageContext.servletContext.contextPath}/auth/otp/otp'" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/welcome'"
						value='Cancel'/></td>
			</tr>		
		</table>
		
	</div>
</form:form>
