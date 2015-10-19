<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature"%>

<%
  // Récupération du paramètre (id) passé par l'URL : http://localhost:8080/infos_candidature.jsp?id=1
  // Attention : la valeur récupérée, même numérique, est sous la forme d'une chaîne de caractères.
  String idString = request.getParameter("id");
%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cabinet de recrutement : liste des candidatures
	référencées</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>

	<%
		  // Test en cas d'appel incorrect
		  if(idString == null)
		  {
		    %>
	<p class="erreur">Erreur : il n'y a aucune candidature qui
		corresponde à cette recherche.</p>
	<%
		  }
		  else // C'est à priori correct...
		  {
		  	// Transformation de la chaine "idString" en un entier
        int id = Integer.parseInt(idString);
		  	// Récupération du service (bean session)
		    IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
		  	// Appel de la fonctionnalité désirée auprès du service
		    Candidature candidature = serviceCandidature.getCandidature(id);
		    %>

	<!-- Affichage des information récupérées -->

	<h2>Infos candidature :</h2>

	<table id="affichage">
		<tr>
			<th style="width: 170px;">Identifiant :</th>
			<td>CAND_<%=candidature.getId()%>
			</td>
		</tr>
	</table>

	<a href="liste_candidatures.jsp">Retour à la liste des candidatures</a>

	<%
		  }
		%>

</body>

</html>

