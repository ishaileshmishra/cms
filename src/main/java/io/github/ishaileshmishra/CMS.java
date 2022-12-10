package io.github.ishaileshmishra;

import io.github.ishaileshmishra.enums.Region;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Objects;

public class CMS extends CDAConnection {

    private final HashMap<String, String> headerMap;
    private final Service service;
    protected Retrofit retrofit;

    public ContentType contentType(@NotNull String contentTypeUid) {
        return new ContentType(retrofit, headerMap, contentTypeUid);
    }

    public Asset asset(String uid) {
        return new Asset(uid);
    }

    public AssetLibrary assetLibrary() {
        AssetLibrary library = new AssetLibrary();
        library.setStackInstance(this.service, headerMap);
        return library;
    }

    public Sync sync() {
        return new Sync(service, headerMap);
    }

    protected CMS() throws IllegalAccessException {
        throw new IllegalAccessException("Illegal Access contentstack=private");
    }

    private CMS(Stack builder) {
        this.headerMap = builder.headerMap;
        this.retrofit = builder.retrofit;
        this.service = builder.service;
    }

    public static class Stack extends CDAConnection {
        private String branch;
        private CDAConnection instance;
        private String HOST = Constants.HOST;
        private Region region = Region.US;
        private Service service = null;
        private Retrofit retrofit;
        private final HashMap<String, String> headerMap;


        public Stack(String apiKey, String deliveryToken, String environment) {
            headerMap = new HashMap<>();
            _validate(apiKey, deliveryToken, environment);
            headerMap.put("api_key", apiKey);
            headerMap.put("access_token", deliveryToken);
            headerMap.put("environment", environment);
        }

        private void _validate(String apiKey, String deliveryToken, String environment) {
            Objects.requireNonNull(apiKey, "apiKey Can not be Null");
            Objects.requireNonNull(deliveryToken, "deliveryToken Can not be Null");
            Objects.requireNonNull(environment, "environment Can not be Null");
        }

        public Stack setBranch(@NotNull String _branch) {
            this.branch = _branch;
            return this;
        }

        public Stack setRegion(Region region) {
            this.region = region;
            return this;
        }

        public Stack setSchema(@NotNull String schema) {
            Constants.SCHEMA = schema;
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
                this.headerMap.put("environment", _environment);
            }
            return this;
        }


        public CMS build() {
            this.HOST = setConfig(region, this.HOST);
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SCHEMA + "" + this.HOST + "/")
                    .build();
            this.service = retrofit.create(Service.class);
            return new CMS(this);
        }

        private String setConfig(Region region, String host) {
            if (!region.name().isEmpty()) {
                if (!region.name().equalsIgnoreCase("us")) {
                    if (host.equalsIgnoreCase("cdn.contentstack.io")) {
                        host = "cdn.contentstack.com";
                    }
                    host = region + "-" + host;
                }
            }
            return host;
        }


    }


}
