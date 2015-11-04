<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                java.util.List"%>

<%
		int idCandidature = (Integer) session.getAttribute("userId");
		int idOffreEmploi = Integer.parseInt(request.getParameter("id"));

		IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
		OffreEmploi offreEmploi = serviceOffreEmploi.getOffreEmploi(idOffreEmploi);
	    
	    IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
%>

<%@include file="header.jsp"%>

<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Liste des offres
			d'emploi</span> <span class="col-md-1"><a
			href="ajout_offre_emploi.jsp" class="btn btn-default">Ajouter</a></span>
	</div>

	<div class="row">
		<div class="col-md-12">
			<ul>
				<li>Numéro : <%= offreEmploi.getId() %></li>
				<li>Titre : <%= offreEmploi.getTitre() %></li>
				<li>Entreprise : <%= offreEmploi.getEntrepriseBean().getDescriptif() %></li>
				<li>Mission : <%= offreEmploi.getDescriptionMission() %></li>
				<li>Profil recherché : <%= offreEmploi.getProfilRecherche() %></li>
				<li>Lieu de la mission : <%= offreEmploi.getEntrepriseBean().getAdressePostale() %> </li>
				<li>Niveau de qualification : <%= offreEmploi.getNiveauQualificationBean().getIntitule() %> </li>
				<li>Secteurs activité : 
				<% for (SecteurActivite secteurActivite :offreEmploi.getSecteurActivites()) { %>
				<%= secteurActivite.getIntitule() + " " %>
				<% } %>
				</li>
				
				<li>Date de dépot :  <%= offreEmploi.getDateDepot() %> </li>
			</ul>
			
			<% if (isCandidat && serviceIndexation.isCandidatureMatchingOffreEmploi(idCandidature, idOffreEmploi)) {  %>
			<a class="btn" href="message_candidature.jsp?idCandidature=<%= idCandidature %>&=idOffreEmploi=<%=idOffreEmploi %>">Candidater</a>
			<% } %>
		</div>
	</div>

</div>

<%@include file="footer.jsp"%>