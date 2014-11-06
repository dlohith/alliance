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
			<tr> <td colspan="3" align="left"><h1>My Balance</h1></td></tr>
			<tr>
				<td width="20%"><label>Account ID:</label></td>
				<td><label>${username}</label></td>
			</tr>
			<tr>
				<td width="20%"><label>Account Type:</label></td>
				<td><label>Checking (Support only Checking account)</label></td>
			</tr>
			<tr>
				<td width="20%"><label>Balance:</label></td>
				<td><label>${balance}</label></td>
				<td></td>
			</tr>			
		</table>
	</div>
