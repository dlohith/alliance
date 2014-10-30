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


<form:form name="addacc" autocomplete="off" class="form" method="POST" modelAttribute="accountBackingBean" action="${pageContext.servletContext.contextPath}/auth/acc/addaccount">
	<div id="form">
		<table>
			<tr><td colspan="3" align="left"><h1>Add Account</h1></td></tr>
			<tr>
				<td><label>Account Type</label></td>
				<td>
				<label>Checking (Support only Checking account)</label>
			</tr>
			<tr>
				<td> <label>Select User</label> </td>
				<td><ul>
					<form:select path="userID" items="${userList}">
					</form:select>
					</ul>
				</td> <form:errors path="userID" cssClass="errors" />
			</tr>
			<tr>	
				<td colspan='2' align="left"><input class="submituser" name="submit" type="submit"
					value="submit" />
					<input class="canceluser" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/listaccount'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>
