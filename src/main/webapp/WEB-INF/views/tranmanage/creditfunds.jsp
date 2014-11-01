<%@page import="com.asu.alliancebank.domain.impl.CreditFunds"%>
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

<form:form name="creditfundsForm" autocomplete="off" class="form" method="POST" modelAttribute="creditFundsBackingBean" action="${pageContext.servletContext.contextPath}/auth/trans/creditfunds">
	<div id="form">
		<table>					
			<tr>
				<td width="20%"><label>Amount:</label></td>
				<td><form:input type='text' class="text" path='amount' name ='amount' value=''/></td>
				<td><font color="red"><form:errors path="amount" cssClass="errors" /><c:out value="${AmountError}"></c:out></font></td>
			</tr>
			<tr>
				<td width="20%"><label>Encrypted:</label></td>
				<td><form:textarea path="encrypt" rows="3" cols="40"></form:textarea></td>
				<td><font color="red"><form:errors path="encrypt" cssClass="errors" /><c:out value="${EncryptionError}"></c:out></font></td>
				
			</tr>	
			<tr height="300" >
				<td></td>
				<td>
					<script type="text/javascript"
				     src="http://www.google.com/recaptcha/api/challenge?k=6Ld7xPsSAAAAAP5roP7kJMeOXc5pupY-CZU7nx5T">
				  	</script>
					<noscript>
						<iframe src="http://www.google.com/recaptcha/api/noscript?k=6Ld7xPsSAAAAAP5roP7kJMeOXc5pupY-CZU7nx5T"
					         height="300" width="500" frameborder="0"></iframe><br>
						<textarea name='recaptchaChallengeField' rows="3" cols="40"></textarea>
						<input type="hidden" name='recaptchaResponseField'
					         value="manual_challenge">
					</noscript>
				</td>
				<td><label> <font color="red"><c:out value="${captchaError}"></c:out></font> </label></td>
	  		
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
			<tr>
			<td></td>
				<td colspan='2' align="left"><input class="otpnext" name="Credit" type="submit"
					value="Submit" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans'"
						value='Cancel'/></td>
			</tr>
					
		</table>
		
	</div>
</form:form>
