package com.contentstack.sdk;


import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.enums.ORDERBY;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.HashMap;
import java.util.Map;

public class AssetLibrary extends Execute {
    private HashMap<String, String> stackHeader;
    public Map<String, Object> urlQueries;
    public CDAService service;


    protected AssetLibrary() {
        this.stackHeader = new HashMap<>();
        this.urlQueries = new HashMap<>();
    }

    protected void setStackInstance(CDAService service, HashMap<String, String> headers) {
        this.service = service;
        this.stackHeader.putAll(headers);
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


    public AssetLibrary sort(String key, ORDERBY orderby) {
        try {
            switch (orderby) {
                case ASCENDING:
                    urlQueries.put("asc", key);
                    break;

                case DESCENDING:
                    urlQueries.put("desc", key);
                    break;
            }
        } catch (Exception e) {
            throwException("sort", "Exception while execution", e);
        }

        return this;
    }

    private void throwException(String query, String executionException, Exception e) {
        throw new RuntimeException(query + " throws " + executionException + " - " + e.getLocalizedMessage());
    }

    public AssetLibrary includeCount() {
        try {
            urlQueries.put("include_count", "true");
        } catch (Exception e) {
            throwException("includeCount", "Exception while execution", e);
        }
        return this;
    }


    public AssetLibrary includeRelativeUrl() {
        try {
            urlQueries.put("relative_urls", "true");
        } catch (Exception e) {
            throwException("relative_urls", "Exception while execution", e);
        }
        return this;
    }


    public void fetchAll(ResultCallBack callback) {
        if (this.stackHeader.containsKey("environment")) {
            urlQueries.put("environment", this.stackHeader.get("environment"));
            this.stackHeader.remove("environment");
        }

        Call<ResponseBody> request = this.service.allAssets(this.stackHeader, urlQueries);
        get(request, callback);
    }


    public AssetLibrary includeFallback() {
        urlQueries.put("include_fallback", true);
        return this;
    }

}
