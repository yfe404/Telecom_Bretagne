<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>

<%
  // Récupération du paramètre (id) passé par l'URL : http://localhost:8080/infos_entreprises.jsp?id=1
  // Attention : la valeur récupérée, même numérique, est sous la forme d'une chaîne de caractères.
  String idString = request.getParameter("id");
%>


<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cabinet de recrutement : liste des entreprises référencées</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
  </head>

  <body>
  
		<%
		  // Test en cas d'appel incorrect
		  if(idString == null)
		  {
		    %>
		    <p class="erreur">Erreur : il n'y a aucune entreprise qui corresponde à cette recherche.</p>
		    <%
		  }
		  else // C'est à priori correct...
		  {
		  	// Transformation de la chaine "idString" en un entier
        int id = Integer.parseInt(idString);
		  	// Récupération du service (bean session)
		    IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
		  	// Appel de la fonctionnalité désirée auprès du service
		    Entreprise entreprise = serviceEntreprise.getEntreprise(id);
		    %>
		    
		    <!-- Affichage des information récupérées -->
		    
		    <h2>Infos entreprise :</h2>

		    <table id="affichage">
		      <tr>
		        <th style="width: 170px;">Identifiant :</th>
		        <td>
		          ENT_<%=entreprise.getId()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Nom :</th>
		        <td>
		          <%=entreprise.getNom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Descriptif :</th>
		        <td>
		          <%=Utils.text2HTML(entreprise.getDescriptif())%>
		        </td>
		      </tr>
		      <tr>
		        <th>Adresse postale (ville) :</th>
		        <td>
		          <%=entreprise.getAdressePostale()%>
		        </td>
		      </tr>
		    </table>
        
        <a href="liste_entreprises.jsp">Retour à la liste des entreprises</a>

		    <%
		  }
		%>
		
  </body>
  
</html>

