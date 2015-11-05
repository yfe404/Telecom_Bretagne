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
	Integer idEntreprise = Integer.parseInt(request.getParameter("id"));

	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
	Entreprise entreprise = serviceEntreprise.getEntreprise(idEntreprise);
%>



<div class="container main-container">

	<div class="row above-table-row">
		<span class="col-md-11 above-table-title">Information sur
			l'entreprise : <%=entreprise.getNom()%></span>
	</div>

	<div class="row">
		<div class="col-md-12">
			<dl>
				<dt>Identifiant</dt>
				<dd>ENT_<%=entreprise.getId()%></dd>
				<dt>Nom</dt>
				<dd><%=entreprise.getNom()%></dd>
				<dt>Descriptif</dt>
				<dd><%=entreprise.getDescriptif()%></dd>
				<dt>Adresse Postale</dt>
				<dd><%=entreprise.getAdressePostale()%></dd>
			</dl>
		</div>
	</div>

</div>

<%@include file="../footer.jsp"%>