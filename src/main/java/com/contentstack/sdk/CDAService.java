package com.contentstack.sdk;

import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.Map;

public interface CDAService {

    @GET("v3/content_types/{content_type_uid}")
    Call<ResponseBody> singleContentType(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    @GET("v3/content_types")
    Call<ResponseBody> allContentTypes(
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    @GET("v3/stacks/sync")
    Call<ResponseBody> synchronization(
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    @GET("v3/global_fields")
    Call<ResponseBody> globalFields(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header);

    @GET("v3/global_fields/{global_field_uid}")
    Call<ResponseBody> globalField(
            @Path("global_field_uid") String globalFieldUid,
            @HeaderMap HashMap<String, String> header);

    @GET("v3/assets/{asset_uid}")
    Call<ResponseBody> singleAsset(
            @Path("asset_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    @GET("v3/assets")
    Call<ResponseBody> allAssets(
            @HeaderMap HashMap<String, String> header,
            @QueryMap Map<String, Object> query);

    @GET("v3/content_types/{content_type_uid}/entries/{entry_uid}")
    Call<ResponseBody> getEntry(
            @Path("content_type_uid") String _uid,
            @Path("entry_uid") String _entryUid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    @GET("v3/content_types/{content_type_uid}/entries")
    Call<ResponseBody> findQuery(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);


}
