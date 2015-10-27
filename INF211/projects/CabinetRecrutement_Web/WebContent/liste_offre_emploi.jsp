<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                java.util.List"%>

<%
	// R�cup�ration du service (bean session)
	IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator
			.getInstance().getRemoteInterface("ServiceOffreEmploi");

	List<OffreEmploi> offresEmploi = serviceOffreEmploi
			.listeDesOffresEmploi();
%>

<%@include file="index.jsp"%>

<div class="container main-container">

	<div class="row">
		<h3 class="col-sm-offset-5">Liste des offres d'emploi</h3>
		<br />
	</div>

	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<tr>
					<th>Date d�p�t</th>
					<th>Entreprise</th>
					<th>Titre</th>
					<th>Description mission</th>
					<th>Profil recherche</th>
					<th>Secteurs d'activit�</th>
					<th>Action</th>
				</tr>
				<%
					for (OffreEmploi offreEmploi : offresEmploi) {
				%>
				<tr>
					<td><%=offreEmploi.getDateDepot().toLocaleString()%></td>
					<td><a href="infos_entreprise.jsp?id=<%=offreEmploi.getEntrepriseBean().getId()%>"><%=offreEmploi.getEntrepriseBean().getNom()%></a></td>
					<td><a href="infos_offre_emploi.jsp?id=<%=offreEmploi.getId()%>"><%=offreEmploi.getTitre()%></a></td>
					<td><%=offreEmploi.getDescriptionMission()%></td>
					<td><%=offreEmploi.getProfilRecherche()%></td>
					<td>
						<%
							for (SecteurActivite secteurActivite : offreEmploi
										.getSecteurActivites()) {
						%> <span><%=secteurActivite.getIntitule()%>&nbsp;</span> <%
 	}
 %>
					</td>
					<td><a class="icon-action"
						href="ajout_offre_emploi.jsp?id=<%=offreEmploi.getId()%>"><span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a> <a
						class="icon-action"
						href="SupprimerOffreEmploiServlet?id=<%=offreEmploi.getId()%>"><span
							class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>

<%@include file="footer.jsp"%>
