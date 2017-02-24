package com.linuxtek.kona.app.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.comm.entity.KEmailAddress;
import com.linuxtek.kona.app.core.entity.KUser;
import com.linuxtek.kona.util.KClassUtil;
import com.linuxtek.kona.util.KDateUtil;
import com.linuxtek.kona.util.KStringUtil;

public class KUtil {
	private static Logger logger = LoggerFactory.getLogger(KUtil.class);
	
	private static KUtil instance = null;
	
	public static KUtil getInstance() {
		if (instance == null) {
			instance = new KUtil();
		}
		return instance;
	}
	
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
    
    public static String toHtml(String s) {
        if (s == null) {
            return "";
        }
        return KStringUtil.toHtml(s);
    }
    
    public static <Email extends KEmailAddress> String emailFirstName(Email email) {
        if (email == null) return "";
        if (email.getFirstName() != null) {
            return email.getFirstName();
        }

        return "";
    }
    
    public static <User extends KUser> String formatFirstName(User user) {
        if (user == null) return "";
        
        if (user.getFirstName() != null) {
            return user.getFirstName();
        }
        
        if (user.getUsername() != null) {
            return user.getUsername();
        }
        
        if (user.getEmail() != null) {
            return user.getEmail();
        }

        return "";
    }
    
    public String createLink(String baseUrl, String url) {
        if (baseUrl == null) baseUrl = "";
        if (url == null) url = "/";

        if (baseUrl.endsWith("/")) {
            baseUrl = KStringUtil.chop(baseUrl);
        }

        if (!url.startsWith("/")) {
            url = "/" + url;
        }

        String link = baseUrl + url;

        logger.debug("LINK: " + link);

        return link;
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
    
    public static void sleep(Long ms) {
    	try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }

}
