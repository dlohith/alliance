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
<form:form name="authcustreq" autocomplete="off" class="form" method="POST" modelAttribute="authorizeCustRequestBackingBean" action="${pageContext.servletContext.contextPath}/auth/trans/authorizecustreq">
	<div id="form">
		<table>
			<tr>
				<td> <label>Select User</label> </td>
				<td><ul>
					<form:select path="userLoginID" items="${userList}">
					</form:select>
					</ul>
				</td> <form:errors path="userLoginID" cssClass="errors" />
			</tr>
			<tr>
				<td> <label>Select Bank Employee</label> </td>
				<td><ul>
					<form:select path="employeeLoginID" items="${employeeList}">
					</form:select>
					</ul>
				</td> <form:errors path="employeeLoginID" cssClass="errors" />
			</tr>
			<tr>	
				<td colspan='2' align="left"><input class="submitauth" name="submit" type="submit"
					value="submit" />
					<input class="cancelauth" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans'"
						value='Cancel'/></td>
			</tr>
		</table>
	</div>
</form:form>