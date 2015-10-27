<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>
<%
	String id = request.getParameter("id");
	boolean isUpdate = (id != null);

	Entreprise entreprise = null;
	// Test en cas d'appel incorrect
	if (id == null) {
		/* todo : remplir ce todo */
	} else // C'est à priori correct...
	{
		// Récupération du service (bean session)
		IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator
				.getInstance().getRemoteInterface("ServiceEntreprise");
		// Appel de la fonctionnalité désirée auprès du service
		entreprise = serviceEntreprise.getEntreprise(Integer
				.parseInt(id));
	}
%>

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
							value="<%=isUpdate ? entreprise.getNom() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputDescriptif" class="col-sm-4 control-label">Descriptif</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='descriptif'
							id="inputDescriptif"
							value="<%=isUpdate ? entreprise.getDescriptif() : ""%>" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputAdresse" class="col-sm-4 control-label">Adresse
						postale</label>
					<div class="col-sm-8">
						<input class="form-control" type='text' name='adresse_postale'
							id="inputAdresse"
							value="<%=isUpdate ? entreprise.getAdressePostale() : ""%>" />
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