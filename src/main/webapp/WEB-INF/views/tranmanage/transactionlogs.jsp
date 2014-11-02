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
								<td width="25%" align="left"><c:out value="${transactionCredit.transactionId}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionCredit.loginId}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionCredit.amount}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionCredit.status}"></c:out></td>
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
	
	
	
<h1>Debits</h1>

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
								<td width="25%" align="left"><c:out value="${transactionDebit.transactionId}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionDebit.loginId}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionDebit.amount}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionDebit.status}"></c:out></td>
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
	
<h1>Merchant Transactions</h1>

<div class="container">
	<c:choose>
		<c:when test="${not empty transactionMerchantList}">

				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listTransactionDebit">
					<thead>
						<tr>
							<th>Request ID</th>
							<th>Merchant ID</th>
							<th>User ID</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="transactionMerchant" items="${transactionMerchantList}">
							<tr>
								<td width="35%" align="left"><c:out value="${transactionMerchant.requestID}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionMerchant.merchantID}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionMerchant.userLoginID}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionMerchant.amount}"></c:out></td>
								<td width="15%" align="center">
								<c:choose>
      								<c:when test="${transactionMerchant.status == 1}">SUCCESS</c:when>
      								<c:when test="${transactionMerchant.status == 2}">PENDING</c:when>
      								<c:when test="${transactionMerchant.status == 3}">FAILURE</c:when>
      								<c:otherwise>UNKNOWN</c:otherwise>
								</c:choose>
								</td>
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
	
<h1>Transfer Funds</h1>

<div class="container">
	<c:choose>
		<c:when test="${not empty transactionTransferList}">

				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listTransactionDebit">
					<thead>
						<tr>
							<th>Transaction ID</th>
							<th>From</th>
							<th>To</th>
							<th>Amount</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="transactionTransfer" items="${transactionTransferList}">
							<tr>
								<td width="25%" align="left"><c:out value="${transactionTransfer.transactionID}"></c:out></td>
								<td width="25%" align="center"><c:out value="${transactionTransfer.fromUserID}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionTransfer.toUserID}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionTransfer.amount}"></c:out></td>
								<td width="15%" align="center"><c:out value="${transactionTransfer.status}"></c:out></td>
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