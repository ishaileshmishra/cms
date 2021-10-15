package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;

import java.util.HashMap;


public class Asset extends Execute {

    public JSONObject urlQueries = new JSONObject();
    protected HashMap<String, String> stackHeader;
    protected String assetUid = null;
    protected CDAService service = null;

    protected Asset(
            CDAService service,
            @NotNull String assetUid,
            @NotNull HashMap<String, String> header) {
        this.service = service;
        this.assetUid = assetUid;
        this.stackHeader = new HashMap<>();
        this.stackHeader.putAll(header);
    }

    public Asset(String uid) {
        this.assetUid = uid;
    }

    private Asset() throws IllegalAccessException {
        throw new IllegalAccessException("private=asset");
    }


    public void setHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            removeHeader(key);
            this.stackHeader.put(key, value);
        }
    }


    public void removeHeader(String key) {
        if (this.stackHeader != null) {
            if (!key.isEmpty()) {
                this.stackHeader.remove(key);
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


    public void fetch(ResultCallBack callback) {
        if (this.stackHeader.containsKey("environment")) {
            urlQueries.put("environment", this.stackHeader.get("environment"));
            this.stackHeader.remove("environment");
        }
        Call<ResponseBody> request = this.service.singleAsset(assetUid, this.stackHeader, urlQueries);
        get(request, callback);
    }


    public Asset addParam(String key, String value) {
        if (key != null && value != null) {
            urlQueries.put(key, value);
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
