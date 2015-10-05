<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Application de gestion du personnel</title>
</head>
<body>
	<h2>Gestion du personnel de LENTREPRISE</h2>

	<p>
		<b>Exécutez la fonctionnalité souhaitée</b>
	</p>

	<table BORDER=0 CELLSPACING=5 WIDTH="85%">
		<tr VALIGN=TOP>
			<td>Annuaire du personnel</td>
			<td VALIGN=TOP WIDTH="30%"><a
				href="/gestpers3Tier-web/AnnuaireServlet"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
		<tr VALIGN=TOP>
			<td>Embaucher une personne</td>
			<td VALIGN=TOP WIDTH="30%"><a
				href="/gestpers3Tier-web/InsertServlet?type=personne"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
		<tr VALIGN=TOP>
			<td>Licencier une personne</td>
			<td VALIGN=TOP WIDTH="30%"><a
				href="/gestpers3Tier-web/RemoveServlet?type=personne"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
		<tr VALIGN=TOP>
			<td>Liste des services</td>
			<td WIDTH="30%"><a href="/gestpers3Tier-web/ServicesServlet"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
		<tr VALIGN=TOP>
			<td>Insérer un service</td>
			<td WIDTH="30%"><a
				href="/gestpers3Tier-web/InsertServlet?type=service"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
				<tr VALIGN=TOP>
			<td>Fermer un service</td>
			<td WIDTH="30%"><a
				href="/gestpers3Tier-web/RemoveServlet?type=service"><img
					SRC="images/execute.gif" HSPACE=4 BORDER=0 align=TOP></img>
					Exécuter</a></td>
		</tr>
	</table>
</body>
</html>
