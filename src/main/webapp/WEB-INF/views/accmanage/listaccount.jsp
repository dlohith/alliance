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
	<h2 class="dataTableHeading">Account Management</h2>
	<br />
</header>

<br/>

<!-- div class="container">
<input type=button class="Modify Account"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/modifyaccount'"
				value='Modify'>
<sec:authorize access="hasAnyRole('ROLE_SYSTEM_ADMIN')">
<input type=button class="Add Account"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/addaccount'"
				value='add'>
<input type=button class="Add Account"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/deleteaccount'"
				value='delete'>
</sec:authorize>
</div -->

<!-- check checkbox when click delete account -->
<script>
	$(document).ready(function() {
		$("#dlgConfirm").hide();
	});
	
	$(function() {		
		$("input[name='deleteaccount']").button().click(function(event) {
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
							$("#accountform")[0].submit();
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


<!-- check when click selectall -->
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
		<c:when test="${not empty loginIDList}">

			<form method="POST" id="accountform">

				<input class="submit" type=button class="submit"
					onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/addaccount'"
					value='Add Account'> <!-- check addaccount url -->
				<input class="submit" type="submit" name="deleteaccount" value="Delete Account"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/acc/deleteaccount'" />
				<!-- check deleteaccount url -->
				
				<hr />
				
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listaccount">
					<thead>
						<tr>
							<th align="left"></th>
							<th align="left">UserID</th>
							<th align="left">Account Type</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="login" items="${loginIDList}">
							<tr>
								<td width="10%"><input type="checkbox" class="selected" name="selected"
									value='<c:out value="${login}"></c:out>' /></td>
									<td width="10%"><label><c:out value="${login}"></c:out></label></td>
								<td width="10%"><label>Checking</label></td>
									
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="dlgConfirm" title="Confirmation">Do you want to delete the selected accounts?</div>
			</form>
		</c:when>
		<c:otherwise>
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/addaccount"
				value='Add Account'><!-- check addaccount url -->
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/acc/deleteaccount'"
				value='Delete Account'><!-- check deleteaccount url -->
			<hr />
			<br />
			No Accounts
		</c:otherwise>
	</c:choose>
</div>
<br />