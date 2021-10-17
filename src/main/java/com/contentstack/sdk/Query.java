package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Query extends Queryable {

    private final String exceptionMsg = "Query Exception";
    protected Service service;
    protected JSONObject params = null;
    private JSONObject queryParams = null;
    protected HashMap<String, Object> header = null;
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


    protected Query(@NotNull String contentType,
                    @NotNull HashMap<String, String> headers) {
        this.header = new HashMap<>();
        this.contentTypeUid = contentType;
        this.header.putAll(headers);
        this.params = new JSONObject();
        this.queryParams = new JSONObject();
        this.queryValue = new JSONObject();
    }


    public void setHeader(@NotNull String key, @NotNull String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            header.put(key, value);
        }
    }


    public void removeHeader(@NotNull String key) {
        if (!key.isEmpty()) {
            header.remove(key);
        }
    }

    public String getContentType() {
        return contentTypeUid;
    }


    public Query where(@NotNull String key, @NotNull Object value) {
        queryParams.put(key, value);
        return this;
    }


    public Query addQuery(@NotNull String key, @NotNull String value) {
        params.put(key, value);
        return this;
    }


    public Query removeQuery(@NotNull String queryKey) {
        if (params.has(queryKey)) {
            params.remove(queryKey);
        }
        return this;
    }


    public Query and(ArrayList<Query> queryObjects) {
        if (queryObjects != null && queryObjects.size() > 0) {
            JSONArray orValueJson = new JSONArray();
            for (Query queryObject : queryObjects) {
                orValueJson.put(queryObject.queryParams);
            }
            queryParams.put("$and", orValueJson);
        }
        return this;
    }


    public Query or(@NotNull ArrayList<Query> queryObjects) {
        JSONArray orValueJson = new JSONArray();
        for (Query queryObject : queryObjects) {
            orValueJson.put(queryObject.queryParams);
        }
        queryParams.put("$or", orValueJson);
        return this;
    }


    public Query lessThan(@NotNull String key, @NotNull Object value) {
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
        return this;
    }


    public Query lessThanOrEqualTo(@NotNull String key, @NotNull Object value) {
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
        return this;
    }


    public Query greaterThan(@NotNull String key, @NotNull Object value) {
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
        return this;
    }


    public Query greaterThanOrEqualTo(@NotNull String key, @NotNull Object value) {
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
        return this;
    }


    public Query notEqualTo(@NotNull String key, @NotNull Object value) {
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
        return this;
    }


    public Query containedIn(@NotNull String key, @NotNull Object[] values) {
        JSONArray valuesArray = new JSONArray();
        for (Object value : values) {
            valuesArray.put(value);
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
        return this;
    }


    public Query notContainedIn(@NotNull String key, @NotNull Object[] values) {
        JSONArray valuesArray = new JSONArray();
        for (Object value : values) {
            valuesArray.put(value);
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
        return this;
    }


    public Query exists(@NotNull String key) {
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
        return this;
    }


    public Query notExists(@NotNull String key) {
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
        return this;
    }


    public Query includeReference(@NotNull String key) {
        if (objectUidForInclude == null) {
            objectUidForInclude = new JSONArray();
        }
        objectUidForInclude.put(key);
        return this;
    }


    public Query tags(@NotNull String[] tags) {
        String tagsvalue = null;
        for (String tag : tags) {
            tagsvalue = tagsvalue + "," + tag;
        }
        params.put("tags", tagsvalue);
        return this;
    }


    public Query ascending(@NotNull String key) {
        params.put("asc", key);
        return this;
    }


    public Query descending(@NotNull String key) {
        params.put("desc", key);
        return this;
    }


    public Query except(@NotNull ArrayList<String> fieldUid) {
        if (fieldUid.size() > 0) {
            if (objectUidForExcept == null) {
                objectUidForExcept = new JSONArray();
            }
            for (String s : fieldUid) {
                objectUidForExcept.put(s);
            }
        }
        return this;
    }


    public Query except(@NotNull String[] fieldUids) {
        if (fieldUids.length > 0) {
            if (objectUidForExcept == null) {
                objectUidForExcept = new JSONArray();
            }
            for (String fieldUid : fieldUids) {
                objectUidForExcept.put(fieldUid);
            }
        }
        return this;
    }


    public Query only(@NotNull String[] fieldUid) {
        if (fieldUid.length > 0) {
            if (objectUidForOnly == null) {
                objectUidForOnly = new JSONArray();
            }
            int count = fieldUid.length;
            for (int i = 0; i < count; i++) {
                objectUidForOnly.put(fieldUid[i]);
            }
        }
        return this;
    }


    public Query onlyWithReferenceUid(@NotNull ArrayList<String> fieldUid, String referenceFieldUid) {
        if (referenceFieldUid != null) {
            if (onlyJsonObject == null) {
                onlyJsonObject = new JSONObject();
            }
            JSONArray fieldValueArray = new JSONArray();
            for (String s : fieldUid) {
                fieldValueArray.put(s);
            }
            onlyJsonObject.put(referenceFieldUid, fieldValueArray);
            if (objectUidForInclude == null) {
                objectUidForInclude = new JSONArray();
            }
            objectUidForInclude.put(referenceFieldUid);

        }
        return this;
    }


    public Query exceptWithReferenceUid(@NotNull ArrayList<String> fieldUid, String referenceFieldUid) {
        if (referenceFieldUid != null) {
            if (exceptJsonObject == null) {
                exceptJsonObject = new JSONObject();
            }
            JSONArray fieldValueArray = new JSONArray();
            for (String s : fieldUid) {
                fieldValueArray.put(s);
            }
            exceptJsonObject.put(referenceFieldUid, fieldValueArray);
            if (objectUidForInclude == null) {
                objectUidForInclude = new JSONArray();
            }
            objectUidForInclude.put(referenceFieldUid);
        }

        return this;
    }


    public Query count() {
        params.put("count", "true");
        return this;
    }


    public Query includeCount() {
        params.put("include_count", "true");
        return this;
    }


    public Query includeContentType() {
        if (params.has("include_schema")) {
            params.remove("include_schema");
        }
        params.put("include_content_type", true);
        params.put("include_global_field_schema", true);
        return this;
    }


    public Query includeOwner() {
        params.put("include_owner", true);
        return this;
    }


    public Query skip(int number) {
        params.put("skip", number);
        return this;
    }


    public Query limit(int number) {
        params.put("limit", number);
        return this;
    }


    public Query regex(@NotNull String key, @NotNull String regex) {
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
        return this;
    }


    public Query regex(@NotNull String key, @NotNull String regex, String modifiers) {
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

        return this;
    }


    public Query locale(@NotNull String locale) {
        params.put("locale", locale);
        return this;
    }


    public Query search(@NotNull String value) {
        if (params.isNull(value)) {
            params.put("typeahead", value);
        }
        return this;
    }


    public Query find(ResultCallBack callback) {
        Error error = null;
        execQuery(null, callback, false);
        return this;
    }


    public Query findOne(ResultCallBack callBack) {
        int limit = -1;
        if (params != null && params.has("limit")) {
            limit = (int) params.get("limit");
        }
        assert params != null;
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
                params.put("except[BASE][]", objectUidForExcept);
                objectUidForExcept = null;

            }
            if (objectUidForOnly != null && objectUidForOnly.length() > 0) {
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

//           String URL = "/" + contentTypeInstance.stackInstance.VERSION + "/content_types/" + formName + "/entries";
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


    public Query addParam(@NotNull String key, @NotNull String value) {
        params.put(key, value);
        return this;
    }


    public Query includeReferenceContentTypUid() {
        params.put("include_reference_content_type_uid", "true");
        return this;
    }


    public Query whereIn(@NotNull String key, @NotNull Query queryObject) {
        JSONObject inQueryObj = new JSONObject();
        inQueryObj.put("$in_query", queryObject.queryParams.toString());
        queryParams.put(key, inQueryObj);
        return this;
    }


    public Query whereNotIn(@NotNull String key, @NotNull Query queryObject) {
        JSONObject inQueryObj = new JSONObject();
        inQueryObj.put("$nin_query", queryObject.queryParams.toString());
        queryParams.put(key, inQueryObj);
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

