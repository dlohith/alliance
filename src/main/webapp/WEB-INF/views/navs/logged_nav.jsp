<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="currentPage" type="java.lang.String" scope="request" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<div id="header">
	<!-- Nav -->
	<nav id="nav">
		<ul>
		<sec:authorize access="hasAnyRole('ROLE_BANK_EMPLOYEE', 'ROLE_SYSTEM_ADMIN', 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
			<li class="current"><a href="welcome">Home</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BANK_EMPLOYEE', 'ROLE_SYSTEM_ADMIN', 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
			<li><a href="">Account Management</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_BANK_EMPLOYEE', 'ROLE_SYSTEM_ADMIN', 'ROLE_MERCHANT', 'ROLE_INDIVIDUAL_CUSTOMER')">
			<li><a href="">Transaction Management</a></li>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
						<li>
				<a href="">User Management</a>
				<ul>
					<li><a href="user/adduser">Create User</a></li>
					<li><a href="#">Modify User</a></li>
					<li><a href="#">Delete User</a></li>
				</ul>
			</li>
		</sec:authorize>
		</ul>
	</nav>	
</div>