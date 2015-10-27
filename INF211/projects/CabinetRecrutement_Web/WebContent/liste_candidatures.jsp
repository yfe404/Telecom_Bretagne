<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                java.util.List"%>

<%
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator
			.getInstance().getRemoteInterface("ServiceCandidature");
	List<Candidature> candidatures = serviceCandidature
			.listeDesCandidatures();
%>

<%@include file="header.jsp"%>

<div class="container main-container">

	<div class="row">
		<h3 class="col-sm-offset-4">Liste des candidatures</h3>
		<br />
	</div>

	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<tr>
					<th>Identifiant</th>
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
					<td>CAND_<%=candidature.getId()%></td>
					<td><%=new SimpleDateFormat("dd MMM yyyy")
					.format(candidature.getDateDepot())%></td>
					<td><a
						href="infos_candidature.jsp?id=<%=candidature.getId()%>"><%=candidature.getNom()%></a></td>
					<td><a
						href="infos_candidature.jsp?id=<%=candidature.getId()%>"><%=candidature.getPrenom()%></a></td>
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

<%@include file="footer.jsp"%>