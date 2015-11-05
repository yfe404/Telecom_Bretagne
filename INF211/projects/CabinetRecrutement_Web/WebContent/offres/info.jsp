<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                java.util.List"%>

<%@include file="../header.jsp"%>

<%
	Integer idOffreEmploi = Integer.parseInt(request.getParameter("id"));

	IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceOffreEmploi");
	OffreEmploi offreEmploi = serviceOffreEmploi.getOffreEmploi(idOffreEmploi);

	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceIndexation");
%>

<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Information sur
			l'offre d'emploi : <%=offreEmploi.getTitre()%></span>
	</div>

	<div class="row">
		<div class="col-md-12">
			<dl>
				<dt>Numéro</dt>
				<dd><%=offreEmploi.getId()%></dd>
				<dt>Titre</dt>
				<dd><%=offreEmploi.getTitre()%></dd>
				<dt>Entreprise</dt>
				<dd><%=offreEmploi.getEntrepriseBean().getDescriptif()%></dd>
				<dt>Mission</dt>
				<dd><%=offreEmploi.getDescriptionMission()%></dd>
				<dt>Profil recherché</dt>
				<dd><%=offreEmploi.getProfilRecherche()%></dd>
				<dt>Lieu de la mission</dt>
				<dd><%=offreEmploi.getEntrepriseBean().getAdressePostale()%>
				</dd>
				<dt>Niveau de qualification</dt>
				<dd><%=offreEmploi.getNiveauQualificationBean().getIntitule()%>
				</dd>
				<dt>Secteurs d'activité</dt>
				<dd>
					<%
						for (SecteurActivite secteurActivite : offreEmploi.getSecteurActivites()) {
					%>
					<%=secteurActivite.getIntitule() + " "%>
					<%
						}
					%>
				</dd>

				<dt>Date de dépot</dt>
				<dd><%=offreEmploi.getDateDepot()%>
				</dd>
			</dl>

			<%
				if (isCandidat && serviceIndexation.isCandidatureMatchingOffreEmploi(userId, idOffreEmploi)) {
			%>
			<a class="btn btn-default"
				href="<%=AssetsLocator.urlForJSP("entreprises/message")%>?idOffreEmploi=<%=idOffreEmploi%>">Candidater</a>
			<%
				}
			%>
		</div>
	</div>

</div>

<%@include file="../footer.jsp"%>