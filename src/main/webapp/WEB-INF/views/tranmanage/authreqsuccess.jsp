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

	<div id="form">
		<table>
			<tr> <td colspan="3" align="left"><h1>Request has been created for you. Please be patient, an internal bank employee will be taking care of your request soon</h1></td></tr>

			<tr><td><input class="Transaction" type=button
						onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans'"
						value='Go Back'/></td></tr>
		</table>
	</div>
