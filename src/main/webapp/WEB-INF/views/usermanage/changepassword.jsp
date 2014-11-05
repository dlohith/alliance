<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<form:form name="changePasswordForm" autocomplete="off" class="form" method="POST" modelAttribute="changePasswordBackingBean" action="${pageContext.servletContext.contextPath}/changepassword">
	<div id="form">
		<table>
			<tr> <td colspan="3" align="left"><h1>Change password</h1></td></tr>
			
			<tr>
				<td width="20%"><label>Login id :</label></td>
				<td><form:input type='text' class="password" path='loginId' name ='loginId' value=''/></td>
				<td><font color="red"><form:errors path="loginId" cssClass="errors"  /><c:out value="${loginerror}"></c:out></font></td>
			</tr>
			<tr>
				<td width="20%"><label>Old Password:</label></td>
				<td><form:input type='password' class="password" path='oldPassword' name ='oldPassword' value=''/></td>
				<td><font color="red"><form:errors path="oldPassword" cssClass="errors" /><c:out value="${oldpasserror}"></c:out></font></td>
			</tr>
			<tr>
				<td width="20%"><label>Password :</label></td>
				<td><form:input type='password' class="password" path='newPassword' name ='newPassword' value=''/></td>
				<td><font color="red"><form:errors path="newPassword" cssClass="errors"  /><c:out value="${newpasserror}"></c:out></font></td>
			</tr>
			<tr>
				<td width="20%"><label>Repeat Password:</label></td>
				<td><form:input type='password' class="password" path='repeatNewPassword' name ='repeatNewPassword' value=''/></td>
				<td><font color="red"><form:errors path="repeatNewPassword" cssClass="errors" /><c:out value="${repeatnewpasserror}"></c:out></font></td>
			</tr>
			<tr>
				<br/>
				<td colspan='2' align="left"><input class="submit"
					name="submit" type="submit" value="Submit" /></td>
			</tr>
		</table>
	</div>
</form:form>

