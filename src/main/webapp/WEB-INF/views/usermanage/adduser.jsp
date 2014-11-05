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

<form:form name="addUserForm" autocomplete="off" class="form" method="POST" modelAttribute="userBackingBean" action="${pageContext.servletContext.contextPath}/auth/user/adduser">
	<div id="form">
		<table>
			<tr> <td colspan="3" align="left"><h1>Add a User</h1></td></tr>
			<tr>
				<td width="20%"><label>First Name:</label></td>
				<td><form:input type='text' class="text" path='firstName' name ='firstName' value=''/></td>
				<td><font color="red"><form:errors path="firstName" cssClass="errors"  /></font></td>
			</tr>
			<tr>
				<td width="20%"><label>Last Name:</label></td>
				<td><form:input type='text' class="text" path='lastName' name ='lastName' value=''/></td>
				<td><font color="red"><form:errors path="lastName" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Login ID:</label></td>
				<td><form:input type='text' class="text" path='loginID' name ='loginID' value='' /></td>
				<td><font color="red"><form:errors path="loginID" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Email ID:</label></td>
				<td><form:input type='email' class="text" path='emailId' name='emailId' value='' /></td>
				<td><font color="red"><form:errors path="emailId" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Phone Number:</label></td>
				<td><form:input type='text' class="text" path='phoneNo' name='phoneNo' value='' maxlength="10"/></td>
				<td><font color="red"><form:errors path="phoneNo" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Role:</label></td>
				<td class="special"><form:checkboxes class ="checkbox" element="li" items="${availableRoles}" itemLabel="name" itemValue="id" path="roleList" /></td>
				<td><font color="red"><form:errors path="roleList" cssClass="errors" /></font></td>
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
			<td>
			</td>
				<td colspan='2' align="left"><input class="submituser" name="submit" type="submit"
					value="Add user" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/listuser'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>
