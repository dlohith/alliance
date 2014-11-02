<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="currentPage" type="java.lang.String" scope="request" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<div id="header">
	<!-- Nav -->
	<nav id="nav">
		<ul>
		<sec:authorize access="hasAnyRole('ROLE_BANK_EMPLOYEE', 'ROLE_SYSTEM_ADMIN', 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
			<li ${currentPage == "home" ? "class=\"current\"" : ""}><a href="${pageContext.servletContext.contextPath}/auth/welcome">Home</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
			<li ${currentPage == "accountmanagement" ? "class=\"current\"" : ""}>
			<a href="${pageContext.servletContext.contextPath}/auth/acc/listaccount">Account Management</a>
					<ul>
						<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
							<li><a href="${pageContext.servletContext.contextPath}/auth/acc/addaccount">Add Account</a></li>
							<li><a href="${pageContext.servletContext.contextPath}/auth/acc/listaccount">Delete Account</a></li>
						</sec:authorize>
					</ul>
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BANK_EMPLOYEE', 'ROLE_SYSTEM_ADMIN', 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
			<li ${currentPage == "transactionmanagement" ? "class=\"current\"" : ""}><a href="${pageContext.servletContext.contextPath}/auth/trans">Transaction Management</a>
			<sec:authorize access="hasAnyRole('ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
				<ul>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/tranfunds">Transfer Funds</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/creditfunds">Credit Funds</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/debitfunds">Debit Funds</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/merchantpayments">Merchant Funds</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/authorizepayments">Authorize My Payments</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/trans/transactionlogs">View Transaction Logs</a></li>
				
				</ul>
			</sec:authorize>	
			</li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
			<li ${currentPage == "usermanagement" ? "class=\"current\"" : ""}>
				<a href="${pageContext.servletContext.contextPath}/auth/user/listuser">User Management</a>
				<ul>
					<li><a href="${pageContext.servletContext.contextPath}/auth/user/adduser">Create User</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/user/listuser">Modify User</a></li>
					<li><a href="${pageContext.servletContext.contextPath}/auth/user/listuser">Delete User</a></li>
				</ul>
			</li>
		</sec:authorize>
		</ul>
	</nav>	
</div>