package io.github.ishaileshmishra;


import io.github.ishaileshmishra.callback.ResultCallBack;
import io.github.ishaileshmishra.enums.ORDER_BY;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>AssetLibrary class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class AssetLibrary extends CDAConnection {
    private final HashMap<String, String> stackHeader;
    public Map<String, Object> urlQueries;
    public Service service;

    /**
     * <p>Constructor for AssetLibrary.</p>
     */
    protected AssetLibrary() {
        this.stackHeader = new HashMap<String, String>();
        this.urlQueries = new HashMap<String, Object>();
    }

    /**
     * <p>fetchAll.</p>
     *
     * @param callback a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void fetchAll(ResultCallBack callback) {
        if (this.stackHeader.containsKey("environment")) {
            urlQueries.put("environment", this.stackHeader.get("environment"));
            this.stackHeader.remove("environment");
        }

        Call<ResponseBody> request = this.service.allAssets(this.stackHeader, urlQueries);
        request(request, callback);
    }

    /**
     * <p>setStackInstance.</p>
     *
     * @param service a {@link io.github.ishaileshmishra.Service} object
     * @param headers a {@link java.util.HashMap} object
     */
    protected void setStackInstance(Service service, HashMap<String, String> headers) {
        this.service = service;
        this.stackHeader.putAll(headers);
    }

    /**
     * <p>setHeader.</p>
     *
     * @param key a {@link java.lang.String} object
     * @param value a {@link java.lang.String} object
     */
    public void setHeader(String key, String value) {
        if (!key.isEmpty() && !value.isEmpty()) {
            stackHeader.put(key, value);
        }
    }


    /**
     * <p>removeHeader.</p>
     *
     * @param key a {@link java.lang.String} object
     */
    public void removeHeader(String key) {
        if (!key.isEmpty()) {
            stackHeader.remove(key);
        }
    }


    /**
     * <p>sort.</p>
     *
     * @param key a {@link java.lang.String} object
     * @param orderby a {@link io.github.ishaileshmishra.enums.ORDER_BY} object
     * @return a {@link io.github.ishaileshmishra.AssetLibrary} object
     */
    public AssetLibrary sort(String key, ORDER_BY orderby) {
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

    /**
     * <p>includeCount.</p>
     *
     * @return a {@link io.github.ishaileshmishra.AssetLibrary} object
     */
    public AssetLibrary includeCount() {
        try {
            urlQueries.put("include_count", "true");
        } catch (Exception e) {
            throwException("includeCount", "Exception while execution", e);
        }
        return this;
    }


    /**
     * <p>includeRelativeUrl.</p>
     *
     * @return a {@link io.github.ishaileshmishra.AssetLibrary} object
     */
    public AssetLibrary includeRelativeUrl() {
        try {
            urlQueries.put("relative_urls", "true");
        } catch (Exception e) {
            throwException("relative_urls", "Exception while execution", e);
        }
        return this;
    }


    /**
     * <p>includeFallback.</p>
     *
     * @return a {@link io.github.ishaileshmishra.AssetLibrary} object
     */
    public AssetLibrary includeFallback() {
        urlQueries.put("include_fallback", true);
        return this;
    }

}
