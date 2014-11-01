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


<form:form name="merchantPayments" autocomplete="off" class="form" method="POST" modelAttribute="merchantDebitFundsBackingBean" action="${pageContext.servletContext.contextPath}/auth/trans/merchantpayments">
	<div id="form">
		<table>		
			<tr>
				<td> <label>Customer/Merchant ID:</label> </td>
				<td><ul>
					<form:select path="userLoginID" items="${userList}">
					</form:select>
					</ul>
				</td> <form:errors path="userLoginID" cssClass="errors" />
			</tr>
			<tr>
				<td width="20%"><label>Amount to be paid:</label></td>
				<td><form:input type='text' class="text" path='amount' name ='amount' value=''/></td>
				<td><font color="red"><form:errors path="amount" cssClass="errors" /></font></td>
			</tr>
			<tr>	
				<td colspan='2' align="left"><input class="authorization" name="authorization" type="submit"
					value="Get Authorization" onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans/merchantpayments'">
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans/merchantpayments'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>
