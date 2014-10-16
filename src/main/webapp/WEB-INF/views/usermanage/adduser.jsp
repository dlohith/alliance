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
				<td><label>Password:</label></td>
				<td><form:input type='password' class="text" name='password' path='password' /></td>
				<td><font color="red"><form:errors path="password" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Repeat Password:</label></td>
				<td><form:input type='password' class="text" name='repeatPassword' path='repeatPassword' /></td>
				<td><font color="red"><form:errors path="repeatPassword" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Email ID:</label></td>
				<td><form:input type='email' class="text" path='emailId' name='emailId' value='' /></td>
				<td><font color="red"><form:errors path="emailId" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Phone Number:</label></td>
				<td><form:input type='text' class="text" path='phoneNo' name='phoneNo' value='' /></td>
				<td><font color="red"><form:errors path="phoneNo" cssClass="errors" /></font></td>
			</tr>
			<tr>
				<td><label>Role:</label></td>
				<td class="special"><form:checkboxes class ="checkbox" element="li" items="${availableRoles}" itemLabel="name" itemValue="id" path="roleList" /></td>
				<td><font color="red"><form:errors path="roleList" cssClass="errors" /></font></td>
			</tr>
			<tr>
			<td></td>
				<td colspan='2' align="left"><input class="submituser" name="submit" type="submit"
					value="Add user" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/listuser'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>
