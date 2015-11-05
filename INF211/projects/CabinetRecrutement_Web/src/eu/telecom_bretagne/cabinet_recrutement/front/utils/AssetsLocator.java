package eu.telecom_bretagne.cabinet_recrutement.front.utils;

public class AssetsLocator {
	public final static String APP_PATH = "http://127.0.0.1:8080/CabinetRecrutement_Web/";
	
	// Assets
	
	public static String urlForImage(String name) {
		return APP_PATH + "assets/images/" + name;
	}
	
	public static String urlForJavascript(String name) {
		return APP_PATH + "assets/js/" + name + ".js";
	}
	
	public static String urlForStylesheet(String name) {
		return APP_PATH + "assets/css/" + name + ".css";
	}
	
	// Java Resources
	
	public static String urlForJSP(String name) {
		return APP_PATH + name + ".jsp";
	}
	
	public static String urlForJSP(String name, Integer id) {
		return APP_PATH + name + ".jsp" + "?id=" + id.toString();
	}
	
	public static String urlForServlet(String name) {
		return APP_PATH + name + "Servlet";
	}
	

}
