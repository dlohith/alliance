<%@page import="com.asu.alliancebank.domain.impl.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>

<style type="text/css">
.submit {
	background-color: #808080;
	color: #FFFFFF;
	width: 150px;
	height: 30px;
	border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	margin-bottom: 5px;
}
</style>

<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/form.css" />
	<br/>
	<br/>

	<div id="form">
		<table>
			<tr> <td colspan="3" align="left"><h1>My Profile</h1></td></tr>
			<tr>
				<td width="20%"><label>First Name:</label></td>
				<td><label>${user.firstName}</label></td>
			</tr>
			<tr>
				<td width="20%"><label>Last Name:</label></td>
				<td><label>${user.lastName}</label></td>
			</tr>
			<tr>
				<td><label>Login ID:</label></td>
				<td><label>${user.loginID}</label></td>
				<td></td>
			</tr>
			<tr>
				<td><label>Email ID:</label></td>
				<td><label>${user.emailId}</label></td>
			</tr>
			<tr>
				<td><label>Phone Number:</label></td>
				<td><label>${user.phoneNo}</label></td>
			</tr>
			<tr>
				<td><label>Role:</label></td>
				<td class="special"><ul>
				 <c:forEach items="${user.authorities}" var="auth">
        			<li>
            		<label>${auth.authority}</label>
            		</li>
            	</c:forEach>
				</ul> 
			</tr>
		</table>
	</div>
