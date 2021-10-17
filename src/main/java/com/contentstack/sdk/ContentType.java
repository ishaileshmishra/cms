package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.util.HashMap;


public class ContentType extends CDAConnection {

    protected String contentTypeUid;
    protected Retrofit retrofit;
    protected Service service;
    protected CDAConnection instance;
    private HashMap<String, String> stackHeader;

    private ContentType() throws IllegalAccessException {
        throw new IllegalAccessException("Invalid Access");
    }

    protected ContentType(@NotNull Retrofit retrofit,
                          @NotNull HashMap<String, String> headerMap,
                          @NotNull String contentTypeUid) {
        if (contentTypeUid.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument, contentTypeUid can not be empty");
        }
        this.retrofit = retrofit;
        this.service = retrofit.create(Service.class);
        this.contentTypeUid = contentTypeUid;
        this.stackHeader = headerMap;
    }


    public void setHeader(String key, String value) {
        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("Header key or value can not be empty");
        }
        this.stackHeader.put(key, value);
    }

    public void removeHeader(String key) {
        this.stackHeader.remove(key);
    }


    public Entry entry(@NotNull String entryUid) {
        return new Entry(this.retrofit, this.stackHeader, contentTypeUid, entryUid);
    }

    public Query query() {
        return new Query(contentTypeUid, stackHeader);
    }


    public void fetch(JSONObject params, final ResultCallBack callback) throws IllegalAccessException {
        if (this.contentTypeUid == null || this.contentTypeUid.isEmpty()) {
            throw new IllegalAccessException("Please provide content_type_uid");
        }
        if (this.stackHeader.containsKey("environment")) {
            params.put("environment", this.stackHeader.get("environment"));
        }
        Call<ResponseBody> request = this.service.singleContentType(this.contentTypeUid, this.stackHeader, params);
        request(request, callback);

    }


    private void fetchContentTypes(JSONObject params, final ResultCallBack callback) {
        params = Constants.processParams(params);
        if (this.stackHeader.containsKey("environment")) {
            params.put("environment", this.stackHeader.get("environment"));
        }
        Call<ResponseBody> request = this.service.allContentTypes(this.stackHeader, params);
        request(request, callback);
    }


}

