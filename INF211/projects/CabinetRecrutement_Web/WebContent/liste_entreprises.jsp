<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                java.util.List"%>

<%
  // Récupération du service (bean session)
	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
// Appel de la fonctionnalité désirée auprès du service
	List<Entreprise> entreprises = serviceEntreprise.listeDesEntreprises();
%>

<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cabinet de recrutement : liste des entreprises référencées</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>

  <body>
  
		<h2>Liste des entreprises référencées :</h2>

		<table id="affichage">
		  <tr>
		    <th>Identifiant</th>
		    <th>Nom</th>
		    <th>Adresse postale (ville)</th>
		  </tr>
		  <%
		  for(Entreprise entreprise : entreprises)
		  {
		    %>
		    <tr>
		     <td>ENT_<%=entreprise.getId()%></td>
		     <td><a href="infos_entreprise.jsp?id=<%=entreprise.getId()%>"><%=entreprise.getNom()%></a></td>
		     <td><%=entreprise.getAdressePostale()%></td>
		    </tr>
		    <%
		  }
		  %>
		</table>

    <a href="index.jsp">Retour au menu</a>

  </body>
  
</html>
