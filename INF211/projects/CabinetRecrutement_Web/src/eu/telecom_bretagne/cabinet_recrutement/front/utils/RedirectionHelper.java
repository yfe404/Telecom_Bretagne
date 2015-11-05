package eu.telecom_bretagne.cabinet_recrutement.front.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RedirectionHelper {
	
	public static void redirectUnauthorized(HttpSession session, HttpServletResponse response) throws IOException {
		session.setAttribute("errorMessage", "Accès non autorisé.");
		response.sendRedirect(AssetsLocator.urlForJSP("index"));
	}

}
