package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.enums.Region;
import com.contentstack.sdk.model.Error;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.HashMap;

public class Contentstack extends CDAConnection {


    private final HashMap<String, String> headerMap;

    //optional parameters
    private final String branch;
    private String host;
    private final Service service;
    protected Retrofit retrofit;
    protected Region region = Region.US;

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

    public Sync sync() {
        return new Sync(service, headerMap);
    }


    private Contentstack(Stack builder) {
        this.branch = builder.branch;
        this.region = builder.region;
        this.headerMap = builder.headerMap;
        this.retrofit = builder.retrofit;
        this.service = builder.service;
    }

    //Builder Class
    public static class Stack {

        // optional parameters
        private String branch;
        private final String SCHEMA = "https://";
        private String HOST = "cdn.contentstack.io";
        private Region region = Region.US;
        private Service service = null;
        private Retrofit retrofit;
        private final HashMap<String, String> headerMap;

        public Stack(@NotNull String apiKey, @NotNull String accessToken, @NotNull String environment) {
            headerMap = new HashMap<>();
            headerMap.put("api_key", apiKey);
            headerMap.put("access_token", accessToken);
            headerMap.put("environment", environment);
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

//        public Stack setEnvironment(@NotNull String _environment) {
//            if (!_environment.isEmpty()) {
//                this.environment = _environment;
//            }
//            return this;
//        }


        public Contentstack build() {
            // Build url // do all the calculations here
            HOST = setConfig(region, this.HOST);
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(SCHEMA + "" + this.HOST + "/")
                    .build();

            this.service = retrofit.create(Service.class);
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
