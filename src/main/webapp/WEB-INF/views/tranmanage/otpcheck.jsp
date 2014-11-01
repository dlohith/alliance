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

<form:form name="otpForm" autocomplete="off" class="form" method="POST" modelAttribute="oTPBackingBean" action="${pageContext.servletContext.contextPath}/auth/trans/otp/${transactionId}">
	<div id="form">
		<table>
			<tr> <td colspan="3" align="left"><h1>Validate Transfer fund</h1></td></tr>
			<tr>
				<td width="20%"><label>OTP :</label></td>
				<td><form:input type='text' class="text" path='otp' name ='otp' value=''/></td>
				<td><font color="red"><form:errors path="otp" cssClass="errors"  /><c:out value="${otperror}"></c:out></font></td>
			</tr>
			
			<tr>
				<td colspan='2' align="left"><input class="submituser" name="submit" type="submit"
					value="Validate" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/listuser'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>