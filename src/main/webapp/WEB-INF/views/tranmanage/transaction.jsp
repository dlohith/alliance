<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<font color="red"><h2> <c:out value="${error}"></c:out></h2></font>

<form method="POST" id="userform">

	<table>
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
	<tr>
		<td>
				<input class="submit" type="button"
					name="Merchantfund" value="Merchant Fund"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/merchantpayments'" />
		</td> 
	</tr>			
	<tr>
		<td>
				<input class="submit" type="button"
					name="AuthorizePayments" value="Authorize My Payments"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/authorizepayments'" />
		</td> 
	</tr>
	<tr>
		<td>
				<input class="submit" type="button"
					name="TransactionLogs" value="Transaction Logs"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/trans/transactionlogs'" />	
		</td> 
	</tr>		
	</table>				
</form>