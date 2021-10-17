package com.contentstack.sdk;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

public class Constants {

    public static final String SDK_VERSION = "v2.0.0";
    public static String SCHEMA = "https://";
    public static String HOST = "cdn.contentstack.io";

    public static JSONObject processParams(JSONObject params) {
        if (params == null) {
            params = new JSONObject();
        }

        Iterator<String> keys = params.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = params.opt(key);
            params.put(key, value);
        }

        return params;
    }


    public static String convertUTCToISO(Date date) {
        final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat(PATTERN, Locale.US);
        dateFormat.setTimeZone(tz);
        return dateFormat.format(date);
    }


}
