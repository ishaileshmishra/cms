package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;

import java.util.HashMap;


public class Asset extends CDAConnection {

    public JSONObject urlQueries = new JSONObject();
    protected HashMap<String, String> stackHeader;
    protected String assetUid;
    protected Service service = null;

    protected Asset(Service service, @NotNull String assetUid, @NotNull HashMap<String, String> header) {
        this.service = service;
        this.assetUid = assetUid;
        this.stackHeader = new HashMap<>();
        this.stackHeader.putAll(header);
    }

    public void fetch(ResultCallBack callback) {
        if (this.stackHeader.containsKey("environment")) {
            urlQueries.put("environment", this.stackHeader.get("environment"));
            this.stackHeader.remove("environment");
        }
        Call<ResponseBody> request = this.service.singleAsset(assetUid, this.stackHeader, urlQueries);
        request(request, callback);
    }

    public Asset(String uid) {
        this.assetUid = uid;
    }

    private Asset() throws IllegalAccessException {
        throw new IllegalAccessException("private=asset");
    }


    public void setHeader(String headerKey, String headerValue) {
        if (!headerKey.isEmpty() && !headerValue.isEmpty()) {
            removeHeader(headerKey);
            this.stackHeader.put(headerKey, headerValue);
        }
    }


    public void removeHeader(String headerKey) {
        if (this.stackHeader != null) {
            if (!headerKey.isEmpty()) {
                this.stackHeader.remove(headerKey);
            }
        }
    }


    protected void setUid(String assetUid) {
        if (!assetUid.isEmpty()) {
            this.assetUid = assetUid;
        }
    }


    public Asset includeDimension() {
        urlQueries.put("include_dimension", true);
        return this;
    }

    public Asset addParam(String paramKey, String value) {
        if (paramKey != null && value != null) {
            urlQueries.put(paramKey, value);
        }
        return this;
    }


    public Asset includeFallback() {
        urlQueries.put("include_fallback", true);
        return this;
    }


    public Asset includeBranch() {
        urlQueries.put("include_branch", true);
        return this;
    }

}
