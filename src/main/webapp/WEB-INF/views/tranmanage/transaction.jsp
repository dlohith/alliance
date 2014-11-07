<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h2><font color="red"> <c:out value="${error}"></c:out></font></h2>

<form method="POST" id="userform">

	<table>
	<sec:authorize access="hasAnyRole( 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
	<tr>
		<td>
			<input class="submit" type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/trans/tranfunds'"
				value='Transfer Fund'>
		</td> 
	</tr>
	
	<tr>
		<td>		
				<input class="submit" type=button
					name="Credit" value="Credit Transfer"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/creditfunds'" />
		</td> 
	</tr>			
	<tr>
		<td>
				<input class="submit" type="button"
					name="Debit" value="Debit Transfer"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/debitfunds'" >
		</td> 
	</tr>			
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_MERCHANT')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="Merchantfund" value="Merchant Fund"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/merchantpayments'" />
		</td> 
	</tr>	
	</sec:authorize>		
	<sec:authorize access="hasAnyRole('ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER','ROLE_BANK_EMPLOYEE')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="AuthorizePayments" value="Authorize My Payments"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/authorizepayments'" />
		</td> 
	</tr>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_INDIVIDUAL_CUSTOMER')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="TransactionLogs" value="Request Bank Employee Authorization"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/addauthrequest'" />	
		</td> 
	</tr>	
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_MERCHANT','ROLE_INDIVIDUAL_CUSTOMER')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="TransactionLogs" value="View Balance"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/viewbalance'" />	
		</td> 
	</tr>	
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER','ROLE_BANK_EMPLOYEE','ROLE_SYSTEM_ADMIN')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="TransactionLogs" value="Transaction Logs"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/transactionlogs'" />	
		</td> 
	</tr>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
	<tr>
		<td>
				<input class="submit" type="button"
					name="TransactionLogs" value="Review Bank Employee Authorization"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/authorizecustreq'" />	
		</td> 
	</tr>		
	</sec:authorize>
	</table>				
</form>