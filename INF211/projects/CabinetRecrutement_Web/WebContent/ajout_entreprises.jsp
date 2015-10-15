<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>
<%
	String id = request.getParameter("id");
	boolean isUpdate = (id != null);

	Entreprise entreprise = null;
	// Test en cas d'appel incorrect
	if (id == null) {
		/* todo : remplir ce todo */
	} else // C'est à priori correct...
	{
		// Récupération du service (bean session)
		IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
				.getInstance().getRemoteInterface("ServiceEntreprise");
		// Appel de la fonctionnalité désirée auprès du service
		entreprise = serviceEntreprise.getEntreprise(Integer
				.parseInt(id));
	}
%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cabinet de recrutement - Ajout d'une entreprise</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>
	<h2><%=isUpdate ? "Mise à jour " : "Ajout "%>
		d'une entreprise :
	</h2>


	<form method='post' action='AjoutEntrepriseServlet'>
		<input type='hidden' value="<%=id%>" name='id'> 
		Nom : <input type='text'
			name='nom' value="<%=isUpdate ? entreprise.getNom() : ""%>" /> <br />
		Descriptif : <input type='text' name='descriptif' value="<%=isUpdate ? entreprise.getDescriptif() : ""%>"/> <br /> Adresse
		postale : <input type='text' name='adresse_postale' value="<%=isUpdate ? entreprise.getAdressePostale() : ""%>" /> <br />
		<button type='submit'>Envoyer</button>
	</form>

</body>

</html>
