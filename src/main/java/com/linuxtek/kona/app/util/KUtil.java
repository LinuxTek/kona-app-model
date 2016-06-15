package com.linuxtek.kona.app.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KDateUtil;

public class KUtil {
	private static Logger logger = Logger.getLogger(KUtil.class);
	
	public static String uuid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	public static String year() {
		Integer year = KDateUtil.getYear(new Date());
		return year.toString();
	}
    
    public static String toString(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return KClassUtil.toString(obj);
    }
    
    public static String formatDate(Date date) {
        String f = "MM/dd/yyyy";
        return formatDate(date, f);
    }

    public static String formatDate(Date date, String format) {
        return formatDate(date, format, null);
    }
    
    public static String formatDate(Date date, String format, String timeZone) {
        if (date == null) return "";
        
        Locale locale = Locale.getDefault();
        
        if (timeZone == null) {
            timeZone = "America/New_York";
        }
        
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        return KDateUtil.format(date, format, locale, tz);
    }
    
    public static String formatCurrency(BigDecimal bd) {
        //Locale locale = new Locale("en_US");
        return formatCurrency(bd, Locale.US);
    }
    
    public static String formatCurrency(BigDecimal bd, Locale locale) {
        if (bd == null)
            return (null);

        double d = bd.doubleValue();
        NumberFormat form = NumberFormat.getCurrencyInstance(locale);
        return (form.format(d));
    }

}
