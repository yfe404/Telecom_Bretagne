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

<%@include file="header.jsp"%>

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
					<th>Adresse Postale</th>
					<th>Nombre d'Offres</th>
					<th>Action</th>
				</tr>
				<%
					for (Entreprise entreprise : entreprises) {
				%>
				<tr>
					<td>ENT_<%=entreprise.getId()%></td>
					<td><a href="infos_entreprise.jsp?id=<%=entreprise.getId()%>"><%=entreprise.getNom()%></a></td>
					<td><%=entreprise.getAdressePostale()%></td>
					<td><%=entreprise.getOffreEmplois().size()%></td>
					<td><a class="icon-action" href="#"><span
							class="glyphicon glyphicon-map-marker" data-toggle="modal"
							data-target="#modalMap" aria-hidden="true"></span>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</div>
<!-- /.container -->

<div class="modal fade" id="modalMap" tabIndex="">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Localisation</h4>
			</div>
			<div class="modal-body">
				<!-- TODO: Mapbox ou OSM -->
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<%@include file="footer.jsp"%>
