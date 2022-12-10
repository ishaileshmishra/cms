package io.github.ishaileshmishra;

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * <p>Constants class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class Constants {

    /** Constant <code>SDK_VERSION="v2.0.0"</code> */
    public static final String SDK_VERSION = "v2.0.0";
    /** Constant <code>SCHEMA="https://"</code> */
    public static String SCHEMA = "https://";
    /** Constant <code>HOST="cdn.contentstack.io"</code> */
    public static String HOST = "cdn.contentstack.io";

    /**
     * <p>processParams.</p>
     *
     * @param params a {@link org.json.simple.JSONObject} object
     * @return a {@link org.json.simple.JSONObject} object
     */
    public static JSONObject processParams(JSONObject params) {
        if (params == null) {
            params = new JSONObject();
        }

        Set<String> keys = params.keySet();
        for (String kay : keys) {
            Object value = params.get(kay);
            params.put(keys.toString(), value);
        }

        return params;
    }


    /**
     * <p>convertUTCToISO.</p>
     *
     * @param date a {@link java.util.Date} object
     * @return a {@link java.lang.String} object
     */
    public static String convertUTCToISO(Date date) {
        final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat(PATTERN, Locale.US);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(date);
    }


}
