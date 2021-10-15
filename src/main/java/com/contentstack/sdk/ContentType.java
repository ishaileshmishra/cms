package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Call;

import java.util.HashMap;


public class ContentType extends CDAConnection {


    protected String contentTypeUid;
    protected Service service;
    private HashMap<String, String> stackHeader;

    private ContentType() {
        throw new IllegalAccessError("Invalid constructor access");
    }

    protected ContentType(@NotNull Service service, @NotNull String contentTypeUid,
                          @NotNull HashMap<String, String> headerMap) {
        if (contentTypeUid.isEmpty()) {
            throw new IllegalArgumentException("content_type_uid can not be empty");
        }
        this.service = service;
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
        Entry entry = new Entry(contentTypeUid, this.stackHeader);
        entry.setUid(entryUid);
        return entry;
    }


    protected Entry entry() {
        return new Entry(contentTypeUid, stackHeader);
    }


    public Query query() {
        return new Query(contentTypeUid, stackHeader);
    }


    public void fetch(JSONObject params, final ResultCallBack callback) {
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

