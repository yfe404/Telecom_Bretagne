<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<%@include file="../../header.jsp"%>

<%
	if (!isEntreprise) {
		session.setAttribute("errorMessage", "Accès non autorisé.");
		response.sendRedirect("index.jsp");
		return;
	}

	IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceOffreEmploi");
	List<OffreEmploi> offresEmploi = serviceOffreEmploi
			.listeDesOffresEmploi((Integer) session.getAttribute("userId"));

	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceIndexation");
%>

<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Liste de mes offres
			d'emploi</span> <span class="col-md-1"><a
			href="<%=AssetsLocator.urlForJSP("offres/add")%>"
			class="btn btn-default">Ajouter</a></span>
	</div>

	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<tr>
					<th>Numéro</th>
					<th>Date de Dépôt</th>
					<th>Entreprise</th>
					<th>Titre</th>
					<th>Niveau Qualification</th>
					<th>Candidatures Potentielles</th>
					<th>Action</th>
				</tr>
				<%
					for (OffreEmploi offreEmploi : offresEmploi) {
						List<Candidature> matchingCandidatures = serviceIndexation
								.getMatchingCandidaturesForOffreEmploi(offreEmploi.getId());
				%>
				<tr>
					<td><%=offreEmploi.getId()%></td>
					<td><%=Utils.date2String(offreEmploi.getDateDepot())%></td>
					<td><a
						href="<%=AssetsLocator.urlForJSP("entreprises/info", offreEmploi.getEntrepriseBean().getId())%>"><%=offreEmploi.getEntrepriseBean().getNom()%></a></td>
					<td><a
						href="<%=AssetsLocator.urlForJSP("offres/info", offreEmploi.getId())%>"><%=offreEmploi.getTitre()%></a></td>
					<td><%=offreEmploi.getNiveauQualificationBean().getIntitule()%></td>
					<td>
						<%
							for (Candidature candidature : matchingCandidatures) {
						%> <a
						href="<%=AssetsLocator.urlForJSP("candidatures/message")%>?idCandidature=<%=candidature.getId()%>&idOffreEmploi=<%=offreEmploi.getId()%>"><%=candidature.getPrenom()%> <%=candidature.getNom()%></a>
						<%
							}
						%>
					</td>
					<td><a class="icon-action"
						href="<%=AssetsLocator.urlForJSP("offres/add", offreEmploi.getId())%>"><span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a> <a
						class="icon-action"
						href="<%=AssetsLocator.urlForServlet("SupprimerOffreEmploi", offreEmploi.getId())%>"><span
							class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>

<%@include file="../../footer.jsp"%>
