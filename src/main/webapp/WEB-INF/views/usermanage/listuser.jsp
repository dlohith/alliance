<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/dataTables.jqueryui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/form.css" />
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/dataTables.jqueryui.js"></script>
<!-- <script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery-1.11.1.min.js"></script> -->


<header>
	<h2 class="dataTableHeading">User Management</h2>
	<br />
</header>

<script>
	$(document).ready(function() {
		$("#dlgConfirm").hide();
	});
	
	$(function() {		
		$("input[name='deleteuser']").button().click(function(event) {
			if ($("form input:checkbox").is(":checked")) {
				event.preventDefault();
				$("#dlgConfirm").dialog({
					resizable : false,
					height : 'auto',
					width : 350,
					modal : true,
					buttons : {
						Submit : function() {
							$(this).dialog("close");
							$("#userform")[0].submit();
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				});
			}
		});
		
	});
</script>

<!--  
	Author Lohith Dwaraka  
	Used to list the users
-->



<script type="text/javascript" charset="utf8">
	$(document).ready(function() {
		$('#selectall').click(function() {
			$('.selected').prop('checked', isChecked('selectall'));
		});
	});
	function isChecked(checkboxId) {
		var id = '#' + checkboxId;
		return $(id).is(":checked");
	}
	function resetSelectAll() {
		// if all checkbox are selected, check the selectall checkbox
		// and viceversa
		if ($(".selected").length == $(".selected:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}

		if ($(".selected:checked").length > 0) {
			$('#edit').attr("disabled", false);
		} else {
			$('#edit').attr("disabled", true);
		}
	}
	$(document).ready(function() {
		$("input[type=submit]").button().click(function(event) {

		});
	});
	$(document).ready(function() {
		$("input[type=a]").button().click(function(event) {
			event.preventDefault();
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("ul.pagination1").quickPagination({
			pageSize : "10"
		});
		$("ul.pagination2").quickPagination({
			pageSize : "10"
		});

	});
</script>
<script type="text/javascript" charset="utf8">
	$(document).ready(function() {
		activeTable = $('.dataTable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bAutoWidth" : false
		});
	});
	
	$(document).ready(function() {
		$("input[type=button]").button().click(function(event) {
			event.preventDefault();
		});
	});
</script>




<br />



<div class="container">
	<c:choose>
		<c:when test="${not empty userList}">

			<form method="POST" id="userform">

				<input class="submit" type=button class="submit"
					onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/adduser'"
					value='Add User'> <input class="submit" type="submit"
					name="deleteuser" value="Delete User"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/user/deleteuser'" />
					<input class="submit" type="button"
					name="piiinfo" value="PII Info"
					onclick="location.href='${pageContext.servletContext.contextPath}/auth/user/piiinfo'" />
				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listuser">
					<thead>
						<tr>
							<th align="left"><input type="checkbox" id="selectall">All</th>
							<th align="left">First Name</th>
							<th>Last Name</th>
							<th>Login</th>
							<th>Email ID</th>
							<th>Phone Number</th>
							<th>Role</th>
							<th>Action</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td width="10%"><input type="checkbox" class="selected" name="selected"
									value='<c:out value="${user.loginID}"></c:out>' /></td>
								<td width="15%" align="left"><c:out value="${user.firstName}"></c:out></td>
								<td width="15%" align="left"><c:out value="${user.lastName}"></c:out></td>
								<td width="15%" align="center"><input name="usernames"
									type="hidden" value="<c:out value="${user.loginID}"></c:out>" />
									<c:out value="${user.loginID}"></c:out></td>
								<td width="25%" align="center"><c:out value="${user.emailId}"></c:out></td>
								<td width="25%" align="center"><c:out value="${user.phoneNo}"></c:out></td>
								<td width="25%" align="center"><c:forEach var="authorities"
										items="${user.authorities}">
										<c:out value="${authorities.authority}"></c:out> :
										</c:forEach></td>
								<td><input type=button class="submit"
									onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/modifyuser/<c:out value="${user.loginID}"></c:out>'"
									value='Modify User'></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="dlgConfirm" title="Confirmation">Do you want to delete the selected users?</div>
			</form>
		</c:when>
		<c:otherwise>
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/user/adduser'"
				value='Add User'>
			<hr />
			<br />
			No Users
		</c:otherwise>
	</c:choose>
</div>
<br />

