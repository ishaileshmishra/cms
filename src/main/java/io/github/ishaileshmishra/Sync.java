package io.github.ishaileshmishra;

import io.github.ishaileshmishra.callback.ResultCallBack;
import io.github.ishaileshmishra.enums.PublishType;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.github.ishaileshmishra.Constants.convertUTCToISO;

/**
 * <p>Sync class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class Sync extends CDAConnection {

    private HashMap<String, Object> queryParams = null;
    private final HashMap<String, String> headerMap;
    private final Service service;

    /**
     * <p>Constructor for Sync.</p>
     *
     * @param service a {@link io.github.ishaileshmishra.Service} object
     * @param headerMap a {@link java.util.HashMap} object
     */
    public Sync(
            @NotNull Service service,
            @NotNull HashMap<String, String> headerMap) {
        this.headerMap = headerMap;
        this.service = service;
    }

    /**
     * <p>init.</p>
     *
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void init(ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }
        queryParams.put("init", true);
        initiate(callBack);
    }


    /**
     * <p>paginate.</p>
     *
     * @param paginationToken a {@link java.lang.String} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void paginate(
            @NotNull String paginationToken,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", true);
        queryParams.put("pagination_token", paginationToken);


        this.initiate(callBack);
    }


    /**
     * <p>sync.</p>
     *
     * @param syncToken a {@link java.lang.String} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void sync(
            @NotNull String syncToken,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", true);
        queryParams.put("sync_token", syncToken);


        this.initiate(callBack);
    }


    /**
     * <p>byDate.</p>
     *
     * @param fromDate a {@link java.util.Date} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void byDate(
            @NotNull Date fromDate,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(fromDate);
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", true);
        queryParams.put("start_from", startFromDate);

        this.initiate(callBack);
    }


    /**
     * <p>byContentType.</p>
     *
     * @param contentType a {@link java.lang.String} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void byContentType(
            @NotNull String contentType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", true);
        queryParams.put("content_type_uid", contentType);


        this.initiate(callBack);
    }


    /**
     * <p>byLocale.</p>
     *
     * @param languageCode a {@link java.lang.String} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void byLocale(@NotNull String languageCode, ResultCallBack callBack) {

        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", true);
        queryParams.put("locale", languageCode);


        this.initiate(callBack);
    }

    /**
     * <p>byPublishType.</p>
     *
     * @param publishType a {@link io.github.ishaileshmishra.enums.PublishType} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void byPublishType(
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }


        queryParams.put("init", true);
        queryParams.put("type", publishType);


        this.initiate(callBack);
    }


    /**
     * <p>all.</p>
     *
     * @param contentType a {@link java.lang.String} object
     * @param from_date a {@link java.util.Date} object
     * @param languageCode a {@link java.lang.String} object
     * @param publishType a {@link io.github.ishaileshmishra.enums.PublishType} object
     * @param callBack a {@link io.github.ishaileshmishra.callback.ResultCallBack} object
     */
    public void all(@NotNull String contentType, @NotNull Date from_date,
                    @NotNull String languageCode,
                    @NotNull PublishType publishType,
                    @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);

        if (queryParams == null) {
            queryParams = new HashMap<String, Object>();
        }

        queryParams.put("init", "true");
        queryParams.put("start_from", startFromDate);
        queryParams.put("content_type_uid", contentType);
        queryParams.put("type", publishType);
        queryParams.put("locale", languageCode);


        this.initiate(callBack);
    }


    private void initiate(ResultCallBack callBack) {
        Map<String, Object> queryParam = removeEnv(headerMap, queryParams);
        Call<ResponseBody> request = service.sync(headerMap, queryParam);
        request(request, callBack);
    }

}
