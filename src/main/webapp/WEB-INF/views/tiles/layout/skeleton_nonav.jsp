<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<!--
	TXT 2.0 by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->

		<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.dropotron.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/skel.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/skel-layers.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/init.js"></script>
		
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/skel.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/style.css" />
		<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/style-wide.css" />
		
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->

	</head>
	<body>
		<tiles:importAttribute name="currentPage" scope="request" />

		<!-- Header -->
		<div id="header">
			<!-- Logo -->
					<h1><a href="" id="logo">Alliance Bank <em>by Group 1</em></a></h1>
		</div>
		<!-- /Header -->

		<!-- Nav -->
		<tiles:insertAttribute name="navigation" />
		<!-- /Nav -->

		<!-- Main -->
		<div id="main-wrapper">
			<div id="main" class="container">
				<sec:authorize access="isAuthenticated()">
					<div>
						<div class="loggedInMsg">
							Welcome <span class="user" style="margin-left: 5px;"><sec:authentication property="principal.username" /></span>!
							<span><a class="inText" href="${pageContext.servletContext.contextPath}/auth/profile">(Your Profile)</a></span>
						</div>
						<div class="loggedOutLink">
							<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
						</div>
						<hr class="clearLoggedIn">
					</div>
				</sec:authorize>
				<div class="row">
					<div class="12u">

						<!-- Content -->

						<article class="is-page-content">

							<tiles:insertAttribute name="content" />

						</article>

						<!-- /Content -->

					</div>
				</div>
			</div>
		</div>

		<!-- /Main -->

		<!-- Footer -->
		<div id="footer">

			<!-- Copyright -->
			<div class="copyright">
				<ul class="menu">
					<li>&copy; Alliance Bank. All rights reserved</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
				</ul>
			</div>
			<!-- /Copyright -->

		</div>
		<!-- /Footer -->

	</body>
</html>