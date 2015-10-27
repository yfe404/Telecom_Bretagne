<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.NiveauQualification"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.data.model.SecteurActivite"%>
<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.OffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                java.util.List"%>
<%
	Candidature candidature = null;

	String id = request.getParameter("id");
	boolean isUpdate = (id != null);

	// Récupération de la candidature si màj.
	if (isUpdate) {
		IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator
				.getInstance().getRemoteInterface("ServiceCandidature");
		candidature = serviceCandidature.getCandidature(Integer
				.parseInt(id));
	}

	// Liste des entreprises
	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
			.getInstance().getRemoteInterface("ServiceEntreprise");
	List<Entreprise> entreprises = serviceEntreprise
			.listeDesEntreprises();

	// Liste des secteurs d'activité et des niveaux de qualification
	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator
			.getInstance().getRemoteInterface("ServiceIndexation");
	List<SecteurActivite> secteursActivite = serviceIndexation
			.listeDesSecteursActivite();
	List<NiveauQualification> niveauxQualification = serviceIndexation
			.listeDesNiveauxQualification();
%>

<%@include file="header.jsp"%>

<div class="container main-container">
	<div class="row">
		<div class="col-md-offset-1 col-md-8">

			<h3 class="col-sm-offset-4"><%=isUpdate ? "Mise à jour " : "Ajout "%>
				d'une candidature
			</h3>
			<br />

			<form class="form-horizontal" method='post'
				action='AjoutCandidatureServlet'>
				<%
					if (isUpdate) {
				%>
				<input type='hidden' value="<%=id%>" name='id'>
				<%
					}
				%>

				<div class="form-group">
					<label for="inputNom" class="col-sm-4 control-label">Nom</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='nom' id="inputNom"
							value="<%=isUpdate ? candidature.getNom() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputPrenom" class="col-sm-4 control-label">Prenom</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='prenom'
							id="inputPrenom"
							value="<%=isUpdate ? candidature.getPrenom() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputDateNaissance" class="col-sm-4 control-label">Date
						de Naissance</label>
					<div class="col-sm-8">
						<input class="form-control" type='date' name='dateNaissance'
							id="inputDateNaissance"
							value="<%=isUpdate ? candidature.getDateNaissance() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputCourriel" class="col-sm-4 control-label">Courriel</label>
					<div class="col-sm-8">
						<input class="form-control" type='email' name='courriel'
							id="inputCourriel"
							value="<%=isUpdate ? candidature.getAdresseEmail() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputAdressePostale" class="col-sm-4 control-label">Adresse
						Postale</label>
					<div class="col-sm-8">
						<textarea class="form-control" name='adressePostale'
							id="inputAdressePostale"><%=isUpdate ? candidature.getAdressePostale() : ""%></textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="inputCV" class="col-sm-4 control-label">CV</label>
					<div class="col-sm-8">
						<textarea class="form-control" name='cv' id="inputCV"><%=isUpdate ? candidature.getCv() : ""%></textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="inputNiveauQualification"
						class="col-sm-4 control-label">Niveau de Qualification</label>
					<div class="col-sm-8">
						<select class="form-control" name="niveauQualification"
							id="inputNiveauQualification">
							<%
								for (NiveauQualification niveauQualification : niveauxQualification) {
							%>
							<option value=<%=niveauQualification.getId()%>><%=niveauQualification.getIntitule()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="inputSecteursActivite" class="col-sm-4 control-label">Secteurs
						d'Activité</label>
					<div class="col-sm-8">
						<%
							for (SecteurActivite secteurActivite : secteursActivite) {
						%>
						<label class="checkbox-inline"> <input type="checkbox"
							name="secteursActivite" value="<%=secteurActivite.getId()%>">
							<%=secteurActivite.getIntitule()%>
						</label>
						<%
							}
						%>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-8">
						<button type="submit" class="btn btn-default">Envoyer</button>
					</div>
				</div>
			</form>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->

<%@include file="footer.jsp"%>