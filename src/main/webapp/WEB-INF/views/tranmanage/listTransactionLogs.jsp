<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="currentPage" type="java.lang.String" scope="request" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/dataTables.jqueryui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/form.css" />
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/dataTables.jqueryui.js"></script>

<header>
	<h2 class="dataTableHeading">Transaction Management</h2>
	<br />
</header>

<br/>

<div class="container">
	<c:choose>
		<c:when test="${not empty transactionLogList}">

			<form method="POST" id="transactionLogsform">
				
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listaccount">
					<thead>
						<tr>
							<th align="left"></th>
							<th align="left">Transaction ID</th>
							<th align="left">Transaction Type</th>												
							<th align="left">Amount</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="user" items="${transactionLogList}">
							<tr>								
								<td width="15%" align="left"><c:out value="${transactionLogList.transactionId}"></c:out></td>
								<td width="15%" align="left"><c:out value="${transactionLogList.transactionType}"></c:out></td>								
								<td width="25%" align="center"><c:out value="${transactionLogList.amount}"></c:out></td>																
							</tr>
						</c:forEach>
					</tbody>
				</table>				
			</form>
		</c:when>
		<c:otherwise>						
			<br />
			No Transaction Logs
		</c:otherwise>
	</c:choose>
</div>
<br />