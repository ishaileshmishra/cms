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

public class Sync extends CDAConnection {

    private HashMap<String, Object> queryParams = null;
    private final HashMap<String, String> headerMap;
    private final Service service;

    public Sync(
            @NotNull Service service,
            @NotNull HashMap<String, String> headerMap) {
        this.headerMap = headerMap;
        this.service = service;
    }

    public void init(ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }
        queryParams.put("init", true);
        initiate(callBack);
    }


    public void paginate(
            @NotNull String pagination_token,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        queryParams.put("init", true);
        queryParams.put("pagination_token", pagination_token);


        this.initiate(callBack);
    }


    public void sync(
            @NotNull String sync_token,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        queryParams.put("init", true);
        queryParams.put("sync_token", sync_token);


        this.initiate(callBack);
    }


    public void byDate(
            @NotNull Date from_date,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        queryParams.put("init", true);
        queryParams.put("start_from", startFromDate);

        this.initiate(callBack);
    }


    public void byContentType(
            @NotNull String contentType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        queryParams.put("init", true);
        queryParams.put("content_type_uid", contentType);


        this.initiate(callBack);
    }


    public void byLocale(@NotNull String languageCode, ResultCallBack callBack) {

        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        queryParams.put("init", true);
        queryParams.put("locale", languageCode);


        this.initiate(callBack);
    }

    public void byPublishType(
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }


        queryParams.put("init", true);
        queryParams.put("type", publishType);


        this.initiate(callBack);
    }


    public void all(@NotNull String contentType, @NotNull Date from_date,
                    @NotNull String languageCode,
                    @NotNull PublishType publishType,
                    @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);

        if (queryParams == null) {
            queryParams = new HashMap<>();
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
