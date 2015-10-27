<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification,
                eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite, java.util.List"%>
<%
	String id = request.getParameter("id");
	boolean isUpdate = (id != null);

	Candidature candidature = null;

	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator
			.getInstance().getRemoteInterface("ServiceIndexation");
	List<NiveauQualification> niveauxQualification = serviceIndexation
			.listeDesNiveauxQualification();

	// Test en cas d'appel incorrect
	if (id == null) {
		/* todo : remplir ce todo */
	} else // C'est à priori correct...
	{
		// Récupération des services (bean sessions) nécessaires
		IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator
				.getInstance().getRemoteInterface("ServiceCandidature");

		// Appel de la fonctionnalité désirée auprès du service
		candidature = serviceCandidature.getCandidature(Integer
				.parseInt(id));

	}
%>entreprise.getAdressePostale()

<%@include file="index.jsp"%>

<div class="container main-container">
	<div class="row">
		<div class="col-md-offset-1 col-md-8">

			<h3 class="col-sm-offset-4"><%=isUpdate ? "Mise à jour " : "Ajout "%>
				d'une entreprise
			</h3>
			<br />

			<form class="form-horizontal" method='post'
				action='AjoutEntrepriseServlet'>
				<%-- 				<input type='hidden' value="<%=id%>" name='id'> Nom : <input --%>
				<!-- 					type='text' name='nom' -->
				<%-- 					value="<%=isUpdate ? entreprise.getNom() : ""%>" /> <br /> --%>
				<!-- 				Descriptif : <input type='text' name='descriptif' -->
				<%-- 					value="<%=isUpdate ? entreprise.getDescriptif() : ""%>" /> <br /> --%>
				<!-- 				Adresse postale : <input type='text' name='adresse_postale' -->
				<%-- 					value="<%=isUpdate ? entreprise.getAdressePostale() : ""%>" /> <br /> --%>
				<input type='hidden' value="<%=id%>" name='id'>




				<div class="form-group">
					<label for="inputNom" class="col-sm-4 control-label">Nom</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='nom' id="inputNom"
							value="<%=isUpdate ? "" : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputDescriptif" class="col-sm-4 control-label">Descriptif</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='descriptif' id="inputDescriptif"
					value="<%=isUpdate ? "" : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputAdresse" class="col-sm-4 control-label">Adresse postale</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='adresse_postale' id="inputAdresse"
					value="<%=isUpdate ? "" : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<button type="submit" class="btn btn-default">Envoyer</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</div>

<%@include file="footer.jsp"%>