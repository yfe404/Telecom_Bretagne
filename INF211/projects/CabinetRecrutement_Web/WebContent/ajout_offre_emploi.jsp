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
	OffreEmploi offreEmploi = null;

	String id = request.getParameter("id");
	boolean isUpdate = (id != null);

	// Récupération de l'offre d'emploi si màj.
	if (id != null) {
		IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator
				.getInstance().getRemoteInterface("ServiceOffreEmploi");
		offreEmploi = serviceOffreEmploi.getOffreEmploi(Integer
				.parseInt(id));
	}

	// Liste des entreprises
	IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
			.getInstance().getRemoteInterface("ServiceEntreprise");
	List<Entreprise> entreprises = serviceEntreprise
			.listeDesEntreprises();

	// Liste des secteurs d'activité
	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
	List<SecteurActivite> secteursActivite = serviceIndexation.listeDesSecteursActivite();
%>

<%@include file="index.jsp"%>

<div class="container main-container">
	<div class="row">
		<div class="col-md-offset-1 col-md-8">

			<h3 class="col-sm-offset-4"><%=isUpdate ? "Mise à jour " : "Ajout "%>
				d'une offre d'emploi
			</h3>
			<br />

			<form class="form-horizontal" method='post'
				action='AjoutOffreEmploiServlet'>
				<%
					if (isUpdate) {
				%>
				<input type='hidden' value="<%=id%>" name='id'>
				<%
					}
				%>

				<div class="form-group">
					<label for="inputEntreprise" class="col-sm-4 control-label">Entreprise</label>
					<div class="col-sm-8">
						<%
							if (isUpdate) {
						%>
						<input class="form-control" type='text' name='entreprise'
							id="inputEntreprise"
							value="<%=offreEmploi.getEntrepriseBean().getNom()%>" disabled />
						<%
							} else {
						%>
						<select class="form-control" name="entreprise"
							id="inputEntreprise">
							<%
								for (Entreprise entreprise : entreprises) {
							%>
							<option value=<%=entreprise.getId()%>><%=entreprise.getNom()%></option>
							<%
								}
							%>
						</select>
						<%
							}
						%>
					</div>
				</div>

				<div class="form-group">
					<label for="inputNom" class="col-sm-4 control-label">Titre</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='titre'
							id="inputTitre"
							value="<%=isUpdate ? offreEmploi.getTitre() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputDescriptionMission" class="col-sm-4 control-label">Description
						Mission</label>
					<div class="col-sm-8">
						<textarea class="form-control" name='descriptionMission'
							id="inputDescriptionMission"><%=isUpdate ? offreEmploi.getDescriptionMission() : ""%></textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="inputProfilRecherche" class="col-sm-4 control-label">Profil
						Recherché</label>
					<div class="col-sm-8">
						<textarea class="form-control" name='profilRecherche'
							id="inputProfilRecherche"><%=isUpdate ? offreEmploi.getProfilRecherche() : ""%></textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="inputSecteurActivites" class="col-sm-4 control-label">Secteurs
						d'Activité</label>
					<div class="col-sm-8">
						<%
							for (SecteurActivite secteurActivite : secteursActivite) {
						%>
						<label class="checkbox-inline"> <input type="checkbox"
							id="inputSecteurActivites"> <%=secteurActivite.getIntitule()%>
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
	</div>
</div>

<%@include file="footer.jsp"%>