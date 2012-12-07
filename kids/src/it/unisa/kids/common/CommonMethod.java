package it.unisa.kids.common;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            System.out.println("Errore nel parseGregorianCalendar: " + date + " is not parsering!");
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
     * @return an java.sql.Date if transformation was possible, null otherweise
     * @throws ParseException 
     */
    public static String parseString(GregorianCalendar gregorian) {
        String toReturn;
        if(gregorian != null) {
            toReturn = new String(gregorian.get(Calendar.YEAR) + "-" + 
                    gregorian.get(Calendar.MONTH) + "-" + gregorian.get(Calendar.DAY_OF_MONTH));
        } else {
            toReturn = null;
        }
        return toReturn;
    }
}
