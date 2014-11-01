<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


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
				<input class="submit" type="submit"
					name="Debit" value="Debit Transfer"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/welcome'" >
		</td> 
	</tr>			
	<tr>
		<td>
				<input class="submit" type="submit"
					name="Merchantfund" value="Merchant fund"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/welcome'" />
		</td> 
	</tr>			
	<tr>
		<td>
				<input class="submit" type="submit"
					name="TransactionLogs" value="Transaction logs"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/trans/transactionLogsList'" />	
		</td> 
	</tr>		
	</table>				
</form>