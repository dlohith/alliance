<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/dataTables.jqueryui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/form.css" />
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/dataTables.jqueryui.js"></script>


<h1>Credits</h1>

<div class="container">
	<c:choose>
		<c:when test="${not empty transactionCreditList}">

				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listTransactionCredit">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>Login ID</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="transactionCredit" items="${transactionCreditList}">
							<tr>
								<td width="15%" align="left"><c:out value="${transactionCredit.transactionId}"></c:out></td>
								<td width="15%" align="left"><c:out value="${transactionCredit.loginId}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionCredit.amount}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionCredit.status}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</c:when>
		<c:otherwise>
			<hr />
			<br />
			No Transaction
		</c:otherwise>
	</c:choose>
	</div>
	
	
	
<h1>Debit</h1>

<div class="container">
	<c:choose>
		<c:when test="${not empty transactionDebitList}">

				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listTransactionDebit">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>Login ID</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="transactionDebit" items="${transactionDebitList}">
							<tr>
								<td width="15%" align="left"><c:out value="${transactionDebit.transactionId}"></c:out></td>
								<td width="15%" align="left"><c:out value="${transactionDebit.loginId}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionDebit.amount}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionDebit.status}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</c:when>
		<c:otherwise>
			<hr />
			<br />
			No Transaction
		</c:otherwise>
	</c:choose>
	</div>