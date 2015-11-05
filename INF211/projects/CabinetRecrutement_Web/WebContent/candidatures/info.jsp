<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.RedirectionHelper"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils"%>
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
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceCandidature");
	Candidature candidature = serviceCandidature.getCandidature(Integer.parseInt(request.getParameter("id")));
%>

<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Information sur la
			candidature de : <%=candidature.getPrenom()%>&nbsp;<%=candidature.getNom()%></span>
	</div>

	<div class="row">
		<div class="col-md-12">
			<dl>
				<dt>Identifiant</dt>
				<dd><%=candidature.getId()%></dd>
				<dt>Nom</dt>
				<dd><%=candidature.getNom()%></dd>
				<dt>Prenom</dt>
				<dd><%=candidature.getPrenom()%></dd>
				<dt>Date de Naissance</dt>
				<dd><%=Utils.date2String(candidature.getDateNaissance())%></dd>
				<dt>Adresse Postale</dt>
				<dd><%=candidature.getAdressePostale()%></dd>
				<dt>Courriel</dt>
				<dd><%=candidature.getAdresseEmail()%>
				</dd>
				<dt>CV</dt>
				<dd><%=candidature.getCv()%>
				</dd>
				<dt>Niveau de qualification</dt>
				<dd><%=candidature.getNiveauQualificationBean().getIntitule()%>
				</dd>
				<dt>Secteurs d'activité</dt>
				<dd>
					<%
						for (SecteurActivite secteurActivite : candidature.getSecteurActivites()) {
					%>
					<%=secteurActivite.getIntitule() + " "%>
					<%
						}
					%>
				</dd>

				<dt>Date de dépot</dt>
				<dd><%=Utils.date2String(candidature.getDateDepot())%>
				</dd>
			</dl>
		</div>
	</div>

</div>

<%@include file="../footer.jsp"%>