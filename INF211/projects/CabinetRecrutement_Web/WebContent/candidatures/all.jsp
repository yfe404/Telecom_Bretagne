<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                java.util.List"%>

<%
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance()
			.getRemoteInterface("ServiceCandidature");
	List<Candidature> candidatures = serviceCandidature.listeDesCandidatures();
%>

<%@include file="../header.jsp"%>

<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Liste des
			candidatures</span>
		<%
			if (!isLogged) {
		%>
		<span class="col-md-1"><a
			href="<%=AssetsLocator.urlForJSP("candidatures/add")%>"
			class="btn btn-default">Ajouter</a></span>
		<%
			}
		%>
	</div>

	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<tr>
					<th>Numéro</th>
					<th>Date de Depôt</th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Adresse Postale</th>
					<th>Courriel</th>
					<th>Niveau Qualification</th>
				</tr>
				<%
					for (Candidature candidature : candidatures) {
				%>
				<tr>
					<td><%=candidature.getId()%></td>
					<td><%=Utils.date2String(candidature.getDateDepot())%></td>
					<td><%=candidature.getNom()%></td>
					<td><%=candidature.getPrenom()%></td>
					<td><%=candidature.getAdressePostale()%></td>
					<td><%=candidature.getAdresseEmail()%></td>
					<td><%=candidature.getNiveauQualificationBean().getIntitule()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>

<%@include file="../footer.jsp"%>