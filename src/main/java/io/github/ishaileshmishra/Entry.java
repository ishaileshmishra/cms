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

/**
 * <p>Entry class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class Entry extends Queryable {

    protected HashMap<String, String> headers = new HashMap<String, String>();
    protected String uid;
    protected Service service;
    protected final String contentTypeUid;
    protected HashMap<String, Object> entryParam;

    private JSONArray referenceArray;
    private JSONArray objectUidForOnly;
    private JSONArray objectUidForExcept;
    private JSONObject onlyJsonObject;
    private JSONObject exceptJsonObject;

    /**
     * <p>Constructor for Entry.</p>
     *
     * @throws java.lang.IllegalAccessException if any.
     */
    protected Entry() throws IllegalAccessException {
        throw new IllegalAccessException("Invalid Access");
    }


    /**
     * <p>Constructor for Entry.</p>
     *
     * @param retrofit a {@link retrofit2.Retrofit} object
     * @param headers a {@link java.util.HashMap} object
     * @param contentTypeUid a {@link java.lang.String} object
     * @param entryUid a {@link java.lang.String} object
     */
    protected Entry(@NotNull Retrofit retrofit,
                    @NotNull HashMap<String, String> headers,
                    @NotNull String contentTypeUid,
                    @NotNull String entryUid) {
        Objects.requireNonNull(entryUid, "entry uid can not be Null");
        this.service = retrofit.create(Service.class);
        this.headers.putAll(headers);
        this.contentTypeUid = contentTypeUid;
        this.uid = entryUid;
        this.entryParam = new HashMap<String, Object>();
    }

    /**
     * <p>Setter for the field <code>uid</code>.</p>
     *
     * @param uid a {@link java.lang.String} object
     */
    protected void setUid(@NotNull String uid) {
        this.uid = uid;
    }

    /**
     * <p>addHeader.</p>
     *
     * @param key a {@link java.lang.String} object
     * @param value a {@link java.lang.String} object
     */
    public void addHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            this.headers.put(key, value);
        }
    }

    /**
     * <p>removeHeader.</p>
     *
     * @param key a {@link java.lang.String} object
     */
    public void removeHeader(String key) {
        if (!key.isEmpty()) {
            this.headers.remove(key);
        }
    }

    /**
     * <p>setLocale.</p>
     *
     * @param locale a {@link java.lang.String} object
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry setLocale(@NotNull String locale) {
        entryParam.put("locale", locale);
        return this;
    }


    /**
     * <p>except.</p>
     *
     * @param fieldUid an array of {@link java.lang.String} objects
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
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


    /**
     * <p>includeReference.</p>
     *
     * @param referenceField a {@link java.lang.String} object
     */
    public void includeReference(@NotNull String referenceField) {
        if (!referenceField.isEmpty()) {
            if (referenceArray == null) {
                referenceArray = new JSONArray();
            }
            referenceArray.add(referenceField);
            entryParam.put("include[]", referenceArray);
        }
    }


    /**
     * <p>includeReference.</p>
     *
     * @param referenceFields an array of {@link java.lang.String} objects
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
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


    /**
     * <p>only.</p>
     *
     * @param fieldUid an array of {@link java.lang.String} objects
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
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


    /**
     * <p>onlyWithReferenceUid.</p>
     *
     * @param fieldUid a {@link java.util.ArrayList} object
     * @param referenceFieldUid a {@link java.lang.String} object
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
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


    /**
     * <p>exceptWithReferenceUid.</p>
     *
     * @param fieldUid a {@link java.util.ArrayList} object
     * @param referenceFieldUid a {@link java.lang.String} object
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
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


    /**
     * <p>fetch.</p>
     *
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void fetch(ResultCallBack callBack) {
        entryParam.put("environment", this.headers.get("environment"));
        this.headers.remove("environment");
        Call<ResponseBody> request = this.service
                .entry(this.contentTypeUid, this.uid, this.headers, this.entryParam);
        request(request, callBack);
    }


    /**
     * <p>addParam.</p>
     *
     * @param paramKey a {@link java.lang.String} object
     * @param value a {@link java.lang.String} object
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry addParam(@NotNull String paramKey, @NotNull String value) {
        entryParam.put(paramKey, value);
        return this;
    }


    /**
     * <p>includeReferenceContentTypeUID.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry includeReferenceContentTypeUID() {
        entryParam.put("include_reference_content_type_uid", "true");
        return this;
    }


    /**
     * <p>includeContentType.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry includeContentType() {
        if (entryParam.containsKey("include_schema")) {
            entryParam.remove("include_schema");
        }
        entryParam.put("include_content_type", true);
        entryParam.put("include_global_field_schema", true);
        return this;
    }


    /**
     * <p>includeFallback.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry includeFallback() {
        entryParam.put("include_fallback", true);
        return this;
    }


    /**
     * <p>includeEmbeddedItems.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry includeEmbeddedItems() {
        entryParam.put("include_embedded_items[]", "BASE");
        return this;
    }


    /**
     * <p>includeBranch.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Entry} object
     */
    public Entry includeBranch() {
        entryParam.put("include_branch", true);
        return this;
    }


}
