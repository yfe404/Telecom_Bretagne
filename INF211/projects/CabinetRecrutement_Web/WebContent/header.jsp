<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	boolean isLogged = session.getAttribute("userId") != null;
	boolean isCandidat = isLogged && session.getAttribute("userType").equals("candidat");
	boolean isEntreprise = isLogged && session.getAttribute("userType").equals("entreprise");
%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cabinet de recrutement</title>

<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/main.css">
<script src="assets/js/jquery-1.11.3.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</head>

<body class="gradient-endless-river main-body">

	<nav class="navbar navbar-fixed-top main-navbar">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp"> <img class="logo"
					alt="" src="assets/images/logo.png"> CoffeeJobs
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a class="navlink" href="liste_entreprises.jsp"><span
							class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
							Entreprises</a></li>

					<li><a class="navlink" href="liste_offre_emploi.jsp"><span
							class="glyphicon glyphicon-certificate" aria-hidden="true"></span>
							Offres d'Emploi</a></li>

					<li><a class="navlink" href="liste_candidatures.jsp"><span
							class="glyphicon glyphicon-user" aria-hidden="true"></span>
							Candidatures</a></li>

					<%
						if (isEntreprise) {
					%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span
							class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
							Mon Espace <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="mon_entreprise.jsp"><span
									class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
									Mon entreprise</a></li>
							<li><a href="liste_offre_emploi_entreprise.jsp"><span
									class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
									Mes offres d'emploi</a></li>
							<li><a
								href="inbox_entreprise.jsp?id=<%=session.getAttribute("userId")%>"><span
									class="glyphicon glyphicon-comment" aria-hidden="true"></span>
									Messages</a></li>
						</ul></li>
					<%
						} else if (isCandidat) {
					%>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span
							class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
							Mon Espace <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="inbox_candidat.jsp?id=<%=session.getAttribute("userId")%>"><span
									class="glyphicon glyphicon-comment" aria-hidden="true"></span>
									Messages</a></li>
						</ul></li>
					<%
						}
					%>
				</ul>

				<%
					if (isLogged) {
				%>
				<!-- 		TODO		<li><a class="navlink"><span -->
				<!-- 						class="glyphicon glyphicon-user" aria-hidden="true"></span> Tesla -->
				<!-- 						Motors</a></li> -->
				<%
					}
				%>
				<form class="navbar-form navbar-right" method="post"
					action="LoginServlet">
					<%
						if (isLogged) {
					%>
					<input type="hidden" name="logout" value="true">
					<button type="submit" class="btn btn-coffee">Déconnexion</button>
					<%
						} else {
					%>
					<div class="form-group">
						<input type="text" name="loginId" class="form-control"
							placeholder="Identifiant">
					</div>
					<button type="submit" class="btn btn-coffee">Connexion</button>
					<%
						}
					%>
				</form>

			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<%
		if (session.getAttribute("errorMessage") != null) {
	%>
	<div class="alert alert-danger main-alert" role="alert">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<span class="glyphicon glyphicon-exclamation" aria-hidden="true"></span>
					<%=session.getAttribute("errorMessage")%>
				</div>
			</div>
		</div>
	</div>
	<%
		}
		session.removeAttribute("errorMessage");
	%>