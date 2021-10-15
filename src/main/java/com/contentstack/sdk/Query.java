package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Query extends CDAConnection {

    private final String exceptionMsg = "Query Exception";
    protected JSONObject params = null;
    private JSONObject queryParams = null;
    protected HashMap<String, Object> stackHeader = null;
    protected String contentTypeUid;
    String errorString = "Exception Occurred";

    private JSONObject mainJSON = null;
    private JSONObject queryValue = null;
    private JSONArray objectUidForInclude = null;
    private JSONArray objectUidForExcept = null;
    private JSONArray objectUidForOnly = null;
    private boolean isJsonProper = true;
    private HashMap<String, Object> errorHashMap;
    private JSONObject onlyJsonObject;
    private JSONObject exceptJsonObject;


    protected Query(String contentType, HashMap<String, String> headers) {
        this.contentTypeUid = contentType;
        this.stackHeader = new HashMap<>();
        this.stackHeader.putAll(headers);
        this.params = new JSONObject();
        this.queryParams = new JSONObject();
        this.queryValue = new JSONObject();

    }


    public void setHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            stackHeader.put(key, value);
        }
    }


    public void removeHeader(String key) {
        if (!key.isEmpty()) {
            stackHeader.remove(key);
        }
    }

    public String getContentType() {
        return contentTypeUid;
    }


    public Query where(String key, Object value) {
        try {
            if (key != null && value != null) {
                queryParams.put(key, value);
            } else {
                throwException("where", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("where", exceptionMsg, e);
        }

        return this;
    }


    public Query addQuery(String key, String value) {
        try {
            if (key != null && value != null) {

                params.put(key, value);
            } else {
                throwException("and", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("and", exceptionMsg, e);
        }
        return this;
    }


    public Query removeQuery(String key) {
        try {
            if (params.has(key)) {
                params.remove(key);
            }
        } catch (Exception e) {
            throwException("and", exceptionMsg, e);
        }
        return this;
    }


    public Query and(ArrayList<Query> queryObjects) {
        if (queryObjects != null && queryObjects.size() > 0) {
            try {
                JSONArray orValueJson = new JSONArray();
                int count = queryObjects.size();

                for (int i = 0; i < count; i++) {
                    orValueJson.put(queryObjects.get(i).queryParams);
                }
                queryParams.put("$and", orValueJson);

            } catch (Exception e) {
                throwException("and", exceptionMsg, e);
            }
        } else {
            throwException("and", exceptionMsg, null);
        }

        return this;
    }


    public Query or(ArrayList<Query> queryObjects) {
        if (queryObjects != null && queryObjects.size() > 0) {
            try {
                JSONArray orValueJson = new JSONArray();
                int count = queryObjects.size();

                for (int i = 0; i < count; i++) {
                    orValueJson.put(queryObjects.get(i).queryParams);
                }

                queryParams.put("$or", orValueJson);

            } catch (Exception e) {
                throwException("or", exceptionMsg, e);
            }
        } else {
            throwException("or", exceptionMsg, null);
        }

        return this;
    }


    public Query lessThan(String key, Object value) {
        if (key != null && value != null) {
            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$lt", value);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$lt", value);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("lessThan", exceptionMsg, e);
            }
        } else {
            throwException("lessThan", exceptionMsg, null);
        }

        return this;
    }


    public Query lessThanOrEqualTo(String key, Object value) {

        if (key != null && value != null) {

            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$lte", value);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$lte", value);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("lessThanOrEqualTo", exceptionMsg, e);
            }
        } else {
            throwException("lessThanOrEqualTo", exceptionMsg, null);
        }
        return this;
    }


    public Query greaterThan(String key, Object value) {

        if (key != null && value != null) {
            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$gt", value);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$gt", value);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("greaterThan", exceptionMsg, e);
            }
        } else {
            throwException("greaterThan", exceptionMsg, null);
        }
        return this;
    }


    public Query greaterThanOrEqualTo(String key, Object value) {

        if (key != null && value != null) {
            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$gte", value);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$gte", value);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("greaterThanOrEqualTo", exceptionMsg, e);
            }
        } else {
            throwException("greaterThanOrEqualTo", exceptionMsg, null);
        }
        return this;
    }


    public Query notEqualTo(String key, Object value) {

        if (key != null && value != null) {

            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$ne", value);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$ne", value);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("notEqualTo", null, e);
            }

        } else {
            throwException("notEqualTo", exceptionMsg, null);
        }

        return this;
    }


    public Query containedIn(String key, Object[] values) {

        if (key != null && values != null) {
            try {
                JSONArray valuesArray = new JSONArray();
                int length = values.length;
                for (int i = 0; i < length; i++) {
                    valuesArray.put(values[i]);
                }
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$in", valuesArray);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$in", valuesArray);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("containedIn", exceptionMsg, e);
            }
        } else {
            throwException("containedIn", exceptionMsg, null);
        }

        return this;
    }


    public Query notContainedIn(String key, Object[] values) {

        if (key != null && values != null) {
            try {
                JSONArray valuesArray = new JSONArray();
                int length = values.length;
                for (int i = 0; i < length; i++) {
                    valuesArray.put(values[i]);
                }
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$nin", valuesArray);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$nin", valuesArray);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("containedIn", exceptionMsg, e);
            }
        } else {
            throwException("containedIn", exceptionMsg, null);
        }

        return this;
    }


    public Query exists(String key) {

        if (key != null) {
            try {
                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$exists", true);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$exists", true);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("exists", exceptionMsg, e);
            }
        } else {
            throwException("exists", exceptionMsg, null);
        }
        return this;
    }


    public Query notExists(String key) {

        if (key != null) {
            try {

                if (queryParams.isNull(key)) {
                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$exists", false);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {

                    queryValue.put("$exists", false);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("notExists", exceptionMsg, e);
            }
        } else {
            throwException("notExists", exceptionMsg, null);
        }

        return this;
    }


    public Query includeReference(String key) {
        if (objectUidForInclude == null) {
            objectUidForInclude = new JSONArray();
        }
        objectUidForInclude.put(key);
        return this;
    }


    public Query tags(String[] tags) {
        try {
            if (tags != null) {

                String tagsvalue = null;
                int count = tags.length;
                for (int i = 0; i < count; i++) {
                    tagsvalue = tagsvalue + "," + tags[i];
                }
                params.put("tags", tagsvalue);
            } else {
                throwException("tags", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("tags", exceptionMsg, e);
        }
        return this;
    }


    public Query ascending(String key) {
        if (key != null) {
            try {
                params.put("asc", key);
            } catch (Exception e) {
                throwException("ascending", exceptionMsg, e);
            }
        } else {
            throwException("ascending", exceptionMsg, null);
        }
        return this;
    }


    public Query descending(String key) {
        if (key != null) {
            try {
                params.put("desc", key);
            } catch (Exception e) {
                throwException("descending", exceptionMsg, e);
            }
        } else {
            throwException("descending", exceptionMsg, null);
        }
        return this;
    }


    public Query except(ArrayList<String> fieldUid) {
        try {
            if (fieldUid != null && fieldUid.size() > 0) {
                if (objectUidForExcept == null) {
                    objectUidForExcept = new JSONArray();
                }
                int count = fieldUid.size();
                for (int i = 0; i < count; i++) {
                    objectUidForExcept.put(fieldUid.get(i));
                }
            } else {
                throwException("except", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("except", exceptionMsg, e);
        }
        return this;
    }


    public Query except(String[] fieldUids) {
        try {
            if (fieldUids != null && fieldUids.length > 0) {
                if (objectUidForExcept == null) {
                    objectUidForExcept = new JSONArray();
                }
                int count = fieldUids.length;
                for (int i = 0; i < count; i++) {
                    objectUidForExcept.put(fieldUids[i]);
                }
            } else {
                throwException("except", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("except", exceptionMsg, e);
        }
        return this;
    }


    public Query only(String[] fieldUid) {
        try {
            if (fieldUid != null && fieldUid.length > 0) {
                if (objectUidForOnly == null) {
                    objectUidForOnly = new JSONArray();
                }
                int count = fieldUid.length;
                for (int i = 0; i < count; i++) {
                    objectUidForOnly.put(fieldUid[i]);
                }
            } else {
                throwException("only", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("only", exceptionMsg, e);
        }
        return this;
    }


    public Query onlyWithReferenceUid(ArrayList<String> fieldUid, String referenceFieldUid) {
        try {
            if (fieldUid != null && referenceFieldUid != null) {
                if (onlyJsonObject == null) {
                    onlyJsonObject = new JSONObject();
                }
                JSONArray fieldValueArray = new JSONArray();
                int count = fieldUid.size();
                for (int i = 0; i < count; i++) {
                    fieldValueArray.put(fieldUid.get(i));
                }

                onlyJsonObject.put(referenceFieldUid, fieldValueArray);
                if (objectUidForInclude == null) {
                    objectUidForInclude = new JSONArray();
                }
                objectUidForInclude.put(referenceFieldUid);

            } else {
                throwException("onlyWithReferenceUid", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("onlyWithReferenceUid", exceptionMsg, e);
        }
        return this;
    }


    public Query exceptWithReferenceUid(ArrayList<String> fieldUid, String referenceFieldUid) {
        try {
            if (fieldUid != null && referenceFieldUid != null) {
                if (exceptJsonObject == null) {
                    exceptJsonObject = new JSONObject();
                }
                JSONArray fieldValueArray = new JSONArray();
                int count = fieldUid.size();
                for (int i = 0; i < count; i++) {
                    fieldValueArray.put(fieldUid.get(i));
                }
                exceptJsonObject.put(referenceFieldUid, fieldValueArray);
                if (objectUidForInclude == null) {
                    objectUidForInclude = new JSONArray();
                }
                objectUidForInclude.put(referenceFieldUid);
            } else {
                throwException("exceptWithReferenceUid", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("exceptWithReferenceUid", exceptionMsg, e);
        }
        return this;
    }


    public Query count() {
        try {
            params.put("count", "true");
        } catch (Exception e) {
            throwException("count", exceptionMsg, e);
        }
        return this;
    }


    public Query includeCount() {
        try {
            params.put("include_count", "true");
        } catch (Exception e) {
            throwException("includeCount", exceptionMsg, e);
        }
        return this;
    }


    public Query includeContentType() {
        try {
            if (params.has("include_schema")) {
                params.remove("include_schema");
            }
            params.put("include_content_type", true);
            params.put("include_global_field_schema", true);
        } catch (Exception e) {
            throwException("include_content_type", exceptionMsg, e);
        }
        return this;
    }


    public Query includeOwner() {
        try {
            params.put("include_owner", true);
        } catch (Exception e) {
            throwException("includeUser", exceptionMsg, e);
        }
        return this;
    }


    public Query skip(int number) {
        try {
            params.put("skip", number);
        } catch (Exception e) {
            throwException("skip", exceptionMsg, e);
        }
        return this;
    }


    public Query limit(int number) {
        try {
            params.put("limit", number);
        } catch (Exception e) {
            throwException("limit", exceptionMsg, e);
        }
        return this;
    }


    public Query regex(String key, String regex) {

        if (key != null && regex != null) {
            try {
                if (queryParams.isNull(key)) {

                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$regex", regex);
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$regex", regex);
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("matches", exceptionMsg, e);
            }
        } else {
            throwException("matches", exceptionMsg, null);
        }
        return this;
    }


    public Query regex(String key, String regex, String modifiers) {
        if (key != null && regex != null) {

            try {
                if (queryParams.isNull(key)) {

                    if (queryValue.length() > 0) {
                        queryValue = new JSONObject();
                    }
                    queryValue.put("$regex", regex);
                    if (modifiers != null) {
                        queryValue.put("$options", modifiers);
                    }
                    queryParams.put(key, queryValue);
                } else if (queryParams.has(key)) {
                    queryValue.put("$regex", regex);
                    if (modifiers != null) {
                        queryValue.put("$options", modifiers);
                    }
                    queryParams.put(key, queryValue);
                }
            } catch (Exception e) {
                throwException("matches", exceptionMsg, e);
            }
        } else {
            throwException("matches", exceptionMsg, null);
        }
        return this;
    }


    public Query locale(String locale) {

        if (locale != null && params != null) {
            try {
                params.put("locale", locale);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return this;
    }


    public Query search(String value) {

        if (value != null) {
            try {
                if (params.isNull(value)) {
                    params.put("typeahead", value);
                }
            } catch (Exception e) {
                throwException("value", exceptionMsg, e);
            }
        } else {
            throwException("value", exceptionMsg, null);
        }

        return this;
    }


    public Query find(ResultCallBack callback) {
        Error error = null;
        execQuery(null, callback, false);
        return this;
    }


    public Query findOne(ResultCallBack callBack) {
        Error error = null;

        int limit = -1;
        if (params != null && params.has("limit")) {
            limit = (int) params.get("limit");
        }
        params.put("limit", 1);

        execQuery(callBack, null, false);

        if (limit != -1) {
            params.put("limit", limit);
        }

        return this;
    }


    private void throwException(String queryName, String messageString, Exception e) {
        isJsonProper = false;
        errorString = messageString;
        errorHashMap = new HashMap<String, Object>();
        if (e != null) {
            errorHashMap.put(queryName, e.toString());
        }
    }


    protected void setQueryJson(ResultCallBack callback) {
        try {

            if (queryParams != null && queryParams.length() > 0) {
                params.put("query", queryParams);
            }

            if (objectUidForExcept != null && objectUidForExcept.length() > 0) {
                //JSONObject exceptValueJson = new JSONObject();
                //exceptValueJson.put("BASE", objectUidForExcept);
                params.put("except[BASE][]", objectUidForExcept);
                objectUidForExcept = null;

            }

            if (objectUidForOnly != null && objectUidForOnly.length() > 0) {
                //JSONObject onlyValueJson = new JSONObject();
                //onlyValueJson.put("BASE", objectUidForOnly);
                params.put("only[BASE][]", objectUidForOnly);
                objectUidForOnly = null;

            }

            if (onlyJsonObject != null && onlyJsonObject.length() > 0) {
                params.put("only", onlyJsonObject);
                onlyJsonObject = null;
            }

            if (exceptJsonObject != null && exceptJsonObject.length() > 0) {
                params.put("except", exceptJsonObject);
                exceptJsonObject = null;
            }

            if (objectUidForInclude != null && objectUidForInclude.length() > 0) {
                params.put("include[]", objectUidForInclude);
                objectUidForInclude = null;
            }

        } catch (Exception e) {
            throwException("find", exceptionMsg, e);
        }
    }


    protected void execQuery(ResultCallBack callBack, ResultCallBack callback, boolean isFromLocal) {
        try {

            //String URL = "/" + contentTypeInstance.stackInstance.VERSION + "/content_types/" + formName + "/entries";
//            queryResultCallback = callback;
//            singleQueryResultCallback = callBack;
//            setQueryJson(callback);
//            //LinkedHashMap<String, Object> headers = getHeader(localHeader);
//            if (headers.size() < 1) {
//                throwException("find", exceptionMsg, null);
//            } else {
//                if (headers.containsKey("environment")) {
//                    params.put("environment", headers.get("environment"));
//                }
//                JSONObject mainQueryJson = new JSONObject();
//                mainQueryJson.put("query", params);
//                fetchFromNetwork(URL, headers, mainQueryJson, callback, callBack);
//            }


        } catch (Exception e) {
            e.printStackTrace();
            throwException("find", exceptionMsg, e);
        }

    }


    public Query addParam(String key, String value) {
        try {
            if (key != null) {
                params.put(key, value == null ? JSONObject.NULL : value);
            } else {
                throwException("and", exceptionMsg, null);
            }
        } catch (Exception e) {
            throwException("and", exceptionMsg, e);
        }
        return this;
    }


    public Query includeReferenceContentTypUid() {
        try {
            params.put("include_reference_content_type_uid", "true");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }


    public Query whereIn(String key, Query queryObject) {
        if (key != null) {
            try {
                JSONObject inQueryObj = new JSONObject();
                inQueryObj.put("$in_query", queryObject.queryParams.toString());
                queryParams.put(key, inQueryObj);
            } catch (Exception e) {
                throwException("in_query", exceptionMsg, e);
            }
        } else {
            throwException("in_query", exceptionMsg, null);
        }
        return this;
    }


    public Query whereNotIn(String key, Query queryObject) {

        if (key != null) {
            try {
                JSONObject inQueryObj = new JSONObject();
                inQueryObj.put("$nin_query", queryObject.queryParams.toString());
                queryParams.put(key, inQueryObj);
            } catch (Exception e) {
                throwException("nin_query", exceptionMsg, e);
            }
        } else {
            throwException("nin_query", exceptionMsg, null);
        }
        return this;
    }


    public Query includeFallback() {
        params.put("include_fallback", true);
        return this;
    }

    public Query includeEmbeddedItems() {
        params.put("include_embedded_items[]", "BASE");
        return this;
    }

    public Query includeBranch() {
        params.put("include_branch", true);
        return this;
    }

}

