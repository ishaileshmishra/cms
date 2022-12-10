package io.github.ishaileshmishra;

import io.github.ishaileshmishra.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Entry extends Queryable {

    protected HashMap<String, String> headers = new HashMap<>();
    protected String uid;
    protected Service service;
    protected final String contentTypeUid;
    protected HashMap<String, Object> entryParam;

    private JSONArray referenceArray;
    private JSONArray objectUidForOnly;
    private JSONArray objectUidForExcept;
    private JSONObject onlyJsonObject;
    private JSONObject exceptJsonObject;

    protected Entry() throws IllegalAccessException {
        throw new IllegalAccessException("Invalid Access");
    }


    protected Entry(@NotNull Retrofit retrofit,
                    @NotNull HashMap<String, String> headers,
                    @NotNull String contentTypeUid,
                    @NotNull String entryUid) {
        Objects.requireNonNull(entryUid, "entry uid can not be Null");
        this.service = retrofit.create(Service.class);
        this.headers.putAll(headers);
        this.contentTypeUid = contentTypeUid;
        this.uid = entryUid;
        this.entryParam = new HashMap<>();
    }

    protected void setUid(@NotNull String uid) {
        this.uid = uid;
    }

    public void addHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            this.headers.put(key, value);
        }
    }

    public void removeHeader(String key) {
        if (!key.isEmpty()) {
            this.headers.remove(key);
        }
    }

    public Entry setLocale(@NotNull String locale) {
        entryParam.put("locale", locale);
        return this;
    }


    public Entry except(@NotNull String[] fieldUid) {
        if (fieldUid.length > 0) {

            if (objectUidForExcept == null) {
                objectUidForExcept = new JSONArray();
            }
            for (String s : fieldUid) {
                objectUidForExcept.add(s);
            }
        }
        return this;
    }


    public void includeReference(@NotNull String referenceField) {
        if (!referenceField.isEmpty()) {
            if (referenceArray == null) {
                referenceArray = new JSONArray();
            }
            referenceArray.add(referenceField);
            entryParam.put("include[]", referenceArray);
        }
    }


    public Entry includeReference(@NotNull String[] referenceFields) {
        if (referenceFields.length > 0) {
            if (referenceArray == null) {
                referenceArray = new JSONArray();
            }
            for (String referenceField : referenceFields) {
                referenceArray.add(referenceField);
            }
            entryParam.put("include[]", referenceArray);
        }
        return this;
    }


    public Entry only(@NotNull String[] fieldUid) {
        if (fieldUid.length > 0) {
            if (objectUidForOnly == null) {
                objectUidForOnly = new JSONArray();
            }
            for (String s : fieldUid) {
                objectUidForOnly.add(s);
            }
        }
        return this;
    }


    public Entry onlyWithReferenceUid(@NotNull ArrayList<String> fieldUid, @NotNull String referenceFieldUid) {
        if (onlyJsonObject == null) {
            onlyJsonObject = new JSONObject();
        }
        JSONArray fieldValueArray = new JSONArray();
        for (String s : fieldUid) {
            fieldValueArray.add(s);
        }
        onlyJsonObject.put(referenceFieldUid, fieldValueArray);
        includeReference(referenceFieldUid);

        return this;
    }


    public Entry exceptWithReferenceUid(@NotNull ArrayList<String> fieldUid,
                                        @NotNull String referenceFieldUid) {
        if (exceptJsonObject == null) {
            exceptJsonObject = new JSONObject();
        }
        JSONArray fieldValueArray = new JSONArray();
        for (String s : fieldUid) {
            fieldValueArray.add(s);
        }
        exceptJsonObject.put(referenceFieldUid, fieldValueArray);
        includeReference(referenceFieldUid);
        return this;
    }


    public void fetch(ResultCallBack callBack) {
        entryParam.put("environment", this.headers.get("environment"));
        this.headers.remove("environment");
        Call<ResponseBody> request = this.service
                .entry(this.contentTypeUid, this.uid, this.headers, this.entryParam);
        request(request, callBack);
    }


    public Entry addParam(@NotNull String paramKey, @NotNull String value) {
        entryParam.put(paramKey, value);
        return this;
    }


    public Entry includeReferenceContentTypeUID() {
        entryParam.put("include_reference_content_type_uid", "true");
        return this;
    }


    public Entry includeContentType() {
        if (entryParam.containsKey("include_schema")) {
            entryParam.remove("include_schema");
        }
        entryParam.put("include_content_type", true);
        entryParam.put("include_global_field_schema", true);
        return this;
    }


    public Entry includeFallback() {
        entryParam.put("include_fallback", true);
        return this;
    }


    public Entry includeEmbeddedItems() {
        entryParam.put("include_embedded_items[]", "BASE");
        return this;
    }


    public Entry includeBranch() {
        entryParam.put("include_branch", true);
        return this;
    }


}
