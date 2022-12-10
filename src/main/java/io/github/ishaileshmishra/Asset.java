package io.github.ishaileshmishra;

import io.github.ishaileshmishra.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import retrofit2.Call;

import java.util.HashMap;


/**
 * <p>Asset class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class Asset extends CDAConnection {

    public JSONObject urlQueries = new JSONObject();
    protected HashMap stackHeader;
    protected String assetUid;
    protected Service service = null;

    /**
     * <p>Constructor for Asset.</p>
     *
     * @param service
     *         a {@link io.github.ishaileshmishra.Service} object
     * @param assetUid
     *         a {@link java.lang.String} object
     * @param header
     *         a {@link java.util.HashMap} object
     */
    protected Asset(Service service, @NotNull String assetUid, @NotNull HashMap<String, String> header) {
        this.service = service;
        this.assetUid = assetUid;
        this.stackHeader = new HashMap();
        this.stackHeader.putAll(header);
    }

    /**
     * <p>fetch.</p>
     *
     * @param callback
     *         a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void fetch(ResultCallBack callback) {
        if (this.stackHeader.containsKey("environment")) {
            urlQueries.put("environment", this.stackHeader.get("environment"));
            this.stackHeader.remove("environment");
        }
        Call<ResponseBody> request = this.service.singleAsset(assetUid, this.stackHeader, urlQueries);
        request(request, callback);
    }

    /**
     * <p>Constructor for Asset.</p>
     *
     * @param uid
     *         a {@link java.lang.String} object
     */
    public Asset(String uid) {
        this.assetUid = uid;
    }

    private Asset() throws IllegalAccessException {
        throw new IllegalAccessException("private=asset");
    }


    /**
     * <p>setHeader.</p>
     *
     * @param headerKey
     *         a {@link java.lang.String} object
     * @param headerValue
     *         a {@link java.lang.String} object
     */
    public void setHeader(String headerKey, String headerValue) {
        if (!headerKey.isEmpty() && !headerValue.isEmpty()) {
            removeHeader(headerKey);
            this.stackHeader.put(headerKey, headerValue);
        }
    }


    /**
     * <p>removeHeader.</p>
     *
     * @param headerKey
     *         a {@link java.lang.String} object
     */
    public void removeHeader(String headerKey) {
        if (this.stackHeader != null) {
            if (!headerKey.isEmpty()) {
                this.stackHeader.remove(headerKey);
            }
        }
    }


    /**
     * <p>setUid.</p>
     *
     * @param assetUid
     *         a {@link java.lang.String} object
     */
    protected void setUid(String assetUid) {
        if (!assetUid.isEmpty()) {
            this.assetUid = assetUid;
        }
    }


    /**
     * <p>includeDimension.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Asset} object
     */
    public Asset includeDimension() {
        urlQueries.put("include_dimension", true);
        return this;
    }

    /**
     * <p>addParam.</p>
     *
     * @param paramKey
     *         a {@link java.lang.String} object
     * @param value
     *         a {@link java.lang.String} object
     * @return a {@link io.github.ishaileshmishra.Asset} object
     */
    public Asset addParam(String paramKey, String value) {
        if (paramKey != null && value != null) {
            urlQueries.put(paramKey, value);
        }
        return this;
    }


    /**
     * <p>includeFallback.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Asset} object
     */
    public Asset includeFallback() {
        urlQueries.put("include_fallback", true);
        return this;
    }


    /**
     * <p>includeBranch.</p>
     *
     * @return a {@link io.github.ishaileshmishra.Asset} object
     */
    public Asset includeBranch() {
        urlQueries.put("include_branch", true);
        return this;
    }

}
