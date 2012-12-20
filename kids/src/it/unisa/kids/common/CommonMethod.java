package it.unisa.kids.common;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 * Classe creata per contenere tutti i metodi di utilizzo secondario,
 *  in comune a tutti i sottosistemi
 */
public class CommonMethod {
    
    /**
     * Transforms a String to an GregorianCalendar to use it in kids-system
     * 
     * @param date string to transforms. Must be in format aaaa-mm-gg
     * @return an gregoriancalendar if transformation was possible, null otherweise
     * @throws ParseException 
     */
    public static GregorianCalendar parseGregorianCalendar(String date) {
        GregorianCalendar gregorian;
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat("yy-MM-dd");
            java.util.Date parsed = df.parse(date);
            gregorian = new GregorianCalendar();
            gregorian.setTime(parsed);
        } catch(ParseException pe) {
            gregorian = null;
        }
        
        return gregorian;
    }
    
    /**
     * Transforms a java.sql.Date get from the database to an GregorianCalendar to use it in kids-system
     * 
     * @param date java.sql.Date get from the database
     * @return an gregoriancalendar if transformation was possible, null otherweise
     * @throws ParseException 
     */
    public static GregorianCalendar parseGregorianCalendar(java.sql.Date date) {
        GregorianCalendar gregorian;
        if(date != null) {
            gregorian = new GregorianCalendar();
            gregorian.setTime(date);
        } else {
            gregorian = null;
        }
        
        return gregorian;
    }
    
    /**
     * Transforms a GregorianCalendar to an java.sql.Date to storage in the database
     * 
     * @param gregorian gregoriancalendar to transform
     * @return an java.sql.Date if transformation was possible, null otherweise
     * @throws ParseException 
     */
    public static java.sql.Date parseDate(GregorianCalendar gregorian) {
        java.sql.Date dateToSet;
        if(gregorian != null) {
            dateToSet = new java.sql.Date(gregorian.getTimeInMillis());
        } else {
            dateToSet = null;
        }
        return dateToSet;
    }

    /**
     * Transforms a GregorianCalendar to an java.sql.Date to storage in the database
     * 
     * @param gregorian gregoriancalendar to transform
     * @return an java.sql.Date if transformation was possible, an empty string otherweise
     * @throws ParseException 
     */
    public static String parseString(GregorianCalendar gregorian) {
        String toReturn;
        if(gregorian != null) {
            toReturn = gregorian.get(Calendar.YEAR)
                    + "-" + (gregorian.get(Calendar.MONTH) + 1)
                    + "-" + gregorian.get(Calendar.DAY_OF_MONTH);
        } else {
            toReturn = "";
        }
        return toReturn;
    }
    
    public static void sendMessageRedirect(HttpServletRequest pRequest, HttpServletResponse pResponse, String pMsg, String pForwardTo)
            throws ServletException, IOException {
        pRequest.setAttribute("message", pMsg);
        pRequest.getRequestDispatcher(pForwardTo).forward(pRequest, pResponse);
    }
    
    public static void checkAddToJSON(JSONObject jObj, String key, Object value) {
        if (value != null) {
            jObj.put(key, value);
        } else {
            jObj.put(key, "");
        }
    }
}
