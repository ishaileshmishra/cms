package io.github.ishaileshmishra;

import okhttp3.ResponseBody;
import org.json.simple.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Service interface.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public interface Service {

    /**
     * <p>singleContentType.</p>
     *
     * @param _uid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link org.json.simple.JSONObject} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/content_types/{content_type_uid}")
    Call<ResponseBody> singleContentType(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    /**
     * <p>allContentTypes.</p>
     *
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link org.json.simple.JSONObject} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/content_types")
    Call<ResponseBody> allContentTypes(
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    /**
     * <p>sync.</p>
     *
     * @param header a {@link java.util.Map} object
     * @param query a {@link java.util.Map} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/stacks/sync")
    Call<ResponseBody> sync(
            @HeaderMap Map<String, String> header,
            @QueryMap Map<String, Object> query);

    /**
     * <p>globalFields.</p>
     *
     * @param _uid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/global_fields")
    Call<ResponseBody> globalFields(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header);

    /**
     * <p>globalField.</p>
     *
     * @param globalFieldUid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/global_fields/{global_field_uid}")
    Call<ResponseBody> globalField(
            @Path("global_field_uid") String globalFieldUid,
            @HeaderMap HashMap<String, String> header);

    /**
     * <p>singleAsset.</p>
     *
     * @param _uid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link org.json.simple.JSONObject} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/assets/{asset_uid}")
    Call<ResponseBody> singleAsset(
            @Path("asset_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);

    /**
     * <p>allAssets.</p>
     *
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link java.util.Map} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/assets")
    Call<ResponseBody> allAssets(
            @HeaderMap HashMap<String, String> header,
            @QueryMap Map<String, Object> query);

    /**
     * <p>entry.</p>
     *
     * @param _uid a {@link java.lang.String} object
     * @param _entryUid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link java.util.Map} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/content_types/{content_type_uid}/entries/{entry_uid}")
    Call<ResponseBody> entry(
            @Path("content_type_uid") String _uid,
            @Path("entry_uid") String _entryUid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap Map<String, Object> query);

    /**
     * <p>findQuery.</p>
     *
     * @param _uid a {@link java.lang.String} object
     * @param header a {@link java.util.HashMap} object
     * @param query a {@link org.json.simple.JSONObject} object
     * @return a {@link retrofit2.Call} object
     */
    @GET("v3/content_types/{content_type_uid}/entries")
    Call<ResponseBody> findQuery(
            @Path("content_type_uid") String _uid,
            @HeaderMap HashMap<String, String> header,
            @QueryMap JSONObject query);


}
