package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.enums.PublishType;
import com.contentstack.sdk.enums.Region;
import com.contentstack.sdk.model.Error;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.Date;
import java.util.HashMap;

import static com.contentstack.sdk.Constants.convertUTCToISO;

public class Contentstack extends Execute {


    //required parameters
    private final String apiKey;
    private final String accessToken;
    private final String environment;
    private final HashMap<String, String> headerMap;

    //optional parameters
    private final String branch;
    private String host;
    private final CDAService service;
    protected Retrofit retrofit;
    protected Region region = Region.US;

    // synchronization
    private JSONObject syncParams = null;

    public String getApiKey() {
        return apiKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getBranch() {
        return this.branch;
    }

    public Region getRegion() {
        return this.region;
    }

    public String getHost() {
        return this.host;
    }

    public ContentType contentType(@NotNull String contentTypeUid) {
        return new ContentType(service, contentTypeUid, headerMap);
    }

    public Asset asset(String uid) {
        return new Asset(uid);
    }


    public AssetLibrary assetLibrary() {
        AssetLibrary library = new AssetLibrary();
        library.setStackInstance(this.service, headerMap);
        return library;
    }

    public void getContentTypes(JSONObject param, final ResultCallBack callBack) {
        param = Constants.processParams(param);
        if (headerMap.containsKey("environment")) {
            param.put("environment", headerMap.get("environment"));
            param.put("include_count", true);
        }
        try {
            Response<ResponseBody> response = service.allContentTypes(headerMap, param).execute();
            if (response.isSuccessful()) {
                callBack.onSuccess(response);
            } else {
                Error error = new Gson().fromJson(response.errorBody().string(), Error.class);
                callBack.onFailure(error);
            }
        } catch (Exception e) {
            Error error = new Error("Exception", 400, e.getLocalizedMessage());
            callBack.onFailure(error);
        }
    }


    public void sync(ResultCallBack callBack) {
        if (syncParams == null) {
            syncParams = new JSONObject();
        }
        try {
            syncParams.put("init", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestSync(callBack);
    }


    public void syncPaginationToken(
            @NotNull String pagination_token,
            @NotNull ResultCallBack callBack) {
        if (syncParams == null) {
            syncParams = new JSONObject();
        }

        try {
            syncParams.put("init", true);
            syncParams.put("pagination_token", pagination_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.requestSync(callBack);
    }


    public void syncToken(
            @NotNull String sync_token,
            @NotNull ResultCallBack callBack) {
        if (syncParams == null) {
            syncParams = new JSONObject();
        }
        try {
            syncParams.put("init", true);
            syncParams.put("sync_token", sync_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.requestSync(callBack);
    }


    public void syncFromDate(
            @NotNull Date from_date,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);
        if (syncParams == null) {
            syncParams = new JSONObject();
        }

        try {
            syncParams.put("init", true);
            syncParams.put("start_from", startFromDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.requestSync(callBack);
    }


    public void syncContentType(
            @NotNull String contentType,
            @NotNull ResultCallBack callBack) {
        if (syncParams == null) {
            syncParams = new JSONObject();
        }
        try {
            syncParams.put("init", true);
            syncParams.put("content_type_uid", contentType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.requestSync(callBack);
    }


    public void syncLocale(@NotNull String languageCode, ResultCallBack callBack) {

        if (syncParams == null) {
            syncParams = new JSONObject();
        }
        try {
            syncParams.put("init", true);
            syncParams.put("locale", languageCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        this.requestSync(callBack);
    }

    public void syncPublishType(
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        if (syncParams == null) {
            syncParams = new JSONObject();
        }

        try {
            syncParams.put("init", true);
            syncParams.put("type", publishType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.requestSync(callBack);
    }


    public void sync(
            @NotNull String contentType,
            @NotNull Date from_date,
            @NotNull String languageCode,
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);

        if (syncParams == null) {
            syncParams = new JSONObject();
        }
        try {
            syncParams.put("init", true);
            syncParams.put("start_from", startFromDate);
            syncParams.put("content_type_uid", contentType);
            syncParams.put("type", publishType);
            syncParams.put("locale", languageCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.requestSync(callBack);
    }


    private void requestSync(ResultCallBack callBack) {

        if (headerMap.containsKey("environment")) {
            syncParams.put("environment", headerMap.get("environment"));
        }
        Call<ResponseBody> request = service.synchronization(headerMap, syncParams);
        get(request, callBack);

    }


    private Contentstack(Stack builder) {
        this.apiKey = builder.apiKey;
        this.accessToken = builder.accessToken;
        this.environment = builder.environment;
        this.branch = builder.branch;
        this.region = builder.region;
        this.headerMap = builder.headerMap;
        this.retrofit = builder.retrofit;
        this.service = builder.service;
    }

    //Builder Class
    public static class Stack {

        // required parameters
        private String apiKey = null;
        private String accessToken = null;
        private String environment = null;

        // optional parameters
        private String branch;
        private String SCHEMA = "https://";
        private String HOST = "cdn.contentstack.io";
        private Region region = Region.US;
        private CDAService service = null;
        private Retrofit retrofit;
        private HashMap<String, String> headerMap = null;

        public Stack(@NotNull String apiKey, @NotNull String accessToken, @NotNull String environment) {
            this.apiKey = apiKey;
            this.accessToken = accessToken;
            this.environment = environment;

            headerMap = new HashMap<>();
            headerMap.put("api_key", this.apiKey);
            headerMap.put("access_token", this.accessToken);
            headerMap.put("environment", this.environment);
        }

        public Stack setBranch(@NotNull String _branch) {
            this.branch = _branch;
            return this;
        }

        public Stack setRegion(Region region) {
            this.region = region;
            return this;
        }

        public Stack setHost(@NotNull String _hostName) {
            if (!_hostName.isEmpty()) {
                HOST = _hostName;
            }
            return this;
        }

        public Stack setEnvironment(@NotNull String _environment) {
            if (!_environment.isEmpty()) {
                this.environment = _environment;
            }
            return this;
        }


        public Contentstack build() {
            // Build url // do all the calculations here
            HOST = setConfig(region, this.HOST);
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(SCHEMA + "" + this.HOST + "/")
                    .build();

            this.service = retrofit.create(CDAService.class);
            return new Contentstack(this);
        }

        private String setConfig(Region region, String host) {
            String alterHost = host;
            if (!region.name().isEmpty()) {
                if (!region.name().equalsIgnoreCase("us")) {
                    if (host.equalsIgnoreCase("cdn.contentstack.io")) {
                        host = "cdn.contentstack.com";
                    }
                    alterHost = region + "-" + host;
                }
            }
            return alterHost;
        }


    }


}
