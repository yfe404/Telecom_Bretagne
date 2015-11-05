<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.RedirectionHelper"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.MessageOffreEmploi"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.MessageCandidature"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageOffreEmploi"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidature"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                java.util.List"%>

<%@include file="../../header.jsp"%>

<%
	if (!isEntreprise) {
		RedirectionHelper.redirectUnauthorized(session, response);
		return;
	}

	IServiceMessageCandidature serviceMessageCandidature = (IServiceMessageCandidature) ServicesLocator
			.getInstance().getRemoteInterface("ServiceMessageCandidature");
	IServiceMessageOffreEmploi serviceMessageOffreEmploi = (IServiceMessageOffreEmploi) ServicesLocator
			.getInstance().getRemoteInterface("ServiceMessageOffreEmploi");

	List<MessageCandidature> messagesCandidature = serviceMessageCandidature
			.listeDesMessagesCandidatureByEntreprise(userId);
	List<MessageOffreEmploi> messagesOffreEmploi = serviceMessageOffreEmploi
			.listeDesMessageOffreEmploiByEntreprise(userId);
%>

<div class="container main-container">

	<div class="row">
		<h3 class="col-sm-offset-4">Messages recus</h3>
		<br />
	</div>

	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<tr>
					<th>Numero</th>
					<th>Date</th>
					<th>Expediteur</th>
					<th>Offre Emploi</th>
					<th>Message</th>
				</tr>
				<%
					for (MessageOffreEmploi message : messagesOffreEmploi) {
				%>
				<tr>
					<td><%=message.getId()%></td>
					<td><%=new SimpleDateFormat("dd MMM yyyy").format(message.getDateEnvoi())%></td>
					<td><%=message.getCandidatureBean().getPrenom()%> <%=message.getCandidatureBean().getNom()%></td>
					<td><%=message.getOffreEmploiBean().getTitre()%>
					<td><%=message.getCorpsMessage()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

	<div class="row">
		<h3 class="col-sm-offset-4">Messages envoyes</h3>
		<br />
	</div>

	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<tr>
					<th>Numero</th>
					<th>Date</th>
					<th>Destinataire</th>
					<th>Offre Emploi</th>
					<th>Message</th>
				</tr>
				<%
					for (MessageCandidature message : messagesCandidature) {
				%>
				<tr>
					<td><%=message.getId()%></td>
					<td><%=new SimpleDateFormat("dd MMM yyyy").format(message.getDateEnvoi())%></td>
					<td><%=message.getCandidatureBean().getPrenom()%> <%=message.getCandidatureBean().getNom()%></td>
					<td><%=message.getOffreEmploiBean().getTitre()%>
					<td><%=message.getCorpsMessage()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>
<!-- /.container -->

<%@include file="../../footer.jsp"%>
