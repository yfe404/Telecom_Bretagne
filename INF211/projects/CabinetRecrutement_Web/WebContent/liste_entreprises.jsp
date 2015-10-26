<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                java.util.List"%>

<%
	// Récupération du service (bean session)
	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
			.getInstance().getRemoteInterface("ServiceEntreprise");
	// Appel de la fonctionnalité désirée auprès du service
	List<Entreprise> entreprises = serviceEntreprise
			.listeDesEntreprises();
%>

<%@include file="index.jsp"%>

<div class="container main-container">

	<div class="row">
		<h3 class="col-sm-offset-4">Liste des entreprises référencées</h3>
		<br />
	</div>

	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<tr>
					<th>Identifiant</th>
					<th>Nom</th>
					<th>Adresse postale (ville)</th>
					<th>Action</th>
				</tr>
				<%
					for (Entreprise entreprise : entreprises) {
				%>
				<tr>
					<td>ENT_<%=entreprise.getId()%></td>
					<td><a
						href="infos_entreprise.jsp?id=<%=entreprise.getId()%>"><%=entreprise.getNom()%></a></td>
					<td><%=entreprise.getAdressePostale()%></td>
					<td><a class="icon-action"
						href="ajout_entreprises.jsp?id=<%=entreprise.getId()%>"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
						<a class="icon-action" href="SupprimerEntrepriseServlet?id=<%=entreprise.getId()%>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>

<%@include file="footer.jsp"%>
