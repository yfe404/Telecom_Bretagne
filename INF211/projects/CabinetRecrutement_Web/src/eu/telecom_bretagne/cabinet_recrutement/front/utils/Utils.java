package eu.telecom_bretagne.cabinet_recrutement.front.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Utils
{
	//-----------------------------------------------------------------------------
	private static DateFormat formatDeDate        = new SimpleDateFormat("dd/MM/yyyy"); 
	private static DateFormat formatDeDateFluxRSS = new SimpleDateFormat("EEE, d MMM, yyyy", Locale.ENGLISH);
	//-----------------------------------------------------------------------------
	/**
	 * Convertit une date (chaîne de caractères) exprimée au format "dd/MM/yyyy"
	 * (exemple 05/09/2011 ou 5/9/2011) en une instance de la classe Date.<br/>
	 * L'opération inverse est assurée par la méthode
	 * {@link eu.telecom_bretagne.cabinet_recrutement.front.utils.date2String}
	 * @param dateString la date au format {@code String}.
	 * @return la date dans une instance de {@link java.util.Date}.
	 */
	public static Date string2Date(String dateString)
	{
		Date d = null;
		try
    {
	    d = formatDeDate.parse(dateString);
    }
    catch (ParseException e)
    {
	    e.printStackTrace();
    }
    return d;
	}
	//-----------------------------------------------------------------------------
	/**
	 * Convertit une date (instance de {@link java.util.Date}) en une chaîne de
	 * caractères exprimée au format "dd/MM/yyyy" (exemple 05/09/2011).<br/>
	 * L'opération inverse est assurée par la méthode
	 * {@link eu.telecom_bretagne.cabinet_recrutement.front.utils.string2Date}
	 * @param uneDate la date au format {@code java.util.Date}.
	 * @return la date formatée dans une chaîne de caractères.
	 */
	public static String date2String(Date uneDate)
	{
		return formatDeDate.format(uneDate);
	}
	//-----------------------------------------------------------------------------
	/**
	 * Convertit une date (instance de {@link java.util.Date}) en une chaîne de
	 * caractères exprimée au format "EEE, d MMM, yyyy" (exemple Wed, Jul 4, 2011).<br/>
	 * <i><u>Note</u> : ce format de date est celui des flux RSS.</i>
	 * @param uneDate la date au format {@code java.util.Date}.
	 * @return la date formatée dans une chaîne de caractères.
	 */
	public static String date2StringRSS(Date uneDate)
	{
		return formatDeDateFluxRSS.format(uneDate);
	}
	//-----------------------------------------------------------------------------
	/**
	 * Transformation des retours chariot (\n) au format HTML (<br/>).
	 * @param contenu
	 * @return
	 */
	public static String text2HTML(String contenu)
	{
		return contenu.replace("\n", "<br/>").trim();
	}
	//-----------------------------------------------------------------------------
}
