<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.RedirectionHelper"%>
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

	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
			.getInstance().getRemoteInterface("ServiceEntreprise");

	Entreprise entreprise = serviceEntreprise
			.getEntreprise(userId);
%>

<div class="container main-container">
	<div class="row">
		<div class="col-md-offset-1 col-md-8">

			<h3 class="col-sm-offset-4">Mon entreprise</h3>
			<br />

			<form class="form-horizontal" method='post'
				action='AjoutEntrepriseServlet'>
				<input type='hidden' name='id' value="<%=userId%>">

				<div class="form-group">
					<label for="inputNom" class="col-sm-4 control-label">Nom</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='nom' id="inputNom"
							value="<%=entreprise.getNom()%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputDescriptif" class="col-sm-4 control-label">Descriptif</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='descriptif'
							id="inputDescriptif" value="<%=entreprise.getDescriptif()%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputAdresse" class="col-sm-4 control-label">Adresse
						postale</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='adresse_postale'
							id="inputAdresse" value="<%=entreprise.getAdressePostale()%>" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<button type="submit" class="btn btn-default">Mettre à
							jour</button>
						<a
							href="<%=AssetsLocator.urlForServlet("SupprimerEntreprise") %>?id=<%=userId%>"
							class="btn btn-danger">Supprimer </a>
					</div>
				</div>
			</form>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->

<%@include file="../../footer.jsp"%>
