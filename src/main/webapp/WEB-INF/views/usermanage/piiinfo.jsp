<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	
	<font color = "red"><c:out value="${piierror }"></c:out></font>
	
	<form method="post">
	<table>
	<tr><td>
		 <label>Login id : </label> </td> <td><input name="loginid" id="loginid"> </td> </tr>
		 
		 <tr>
		 <td></td><td>
		 
		 <input class="submit" type="submit"
					name="userpii" value="Get User PII"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/user/piiinfo'" />
					</td>
					</tr>
		</table>
	
	</form>
	
	<c:choose>
	 	<c:when test="${user ne null }">
	 	
	 	
	 	
	
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
</c:when>
	
	</c:choose>