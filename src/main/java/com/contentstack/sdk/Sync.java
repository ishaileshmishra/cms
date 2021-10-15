package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.enums.PublishType;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import retrofit2.Call;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.contentstack.sdk.Constants.convertUTCToISO;

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
        try {
            queryParams.put("init", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initiate(callBack);
    }


    public void paginate(
            @NotNull String pagination_token,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        try {
            queryParams.put("init", true);
            queryParams.put("pagination_token", pagination_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.initiate(callBack);
    }


    public void sync(
            @NotNull String sync_token,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }
        try {
            queryParams.put("init", true);
            queryParams.put("sync_token", sync_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.initiate(callBack);
    }


    public void byDate(
            @NotNull Date from_date,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        try {
            queryParams.put("init", true);
            queryParams.put("start_from", startFromDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.initiate(callBack);
    }


    public void byContentType(
            @NotNull String contentType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }
        try {
            queryParams.put("init", true);
            queryParams.put("content_type_uid", contentType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.initiate(callBack);
    }


    public void byLocale(@NotNull String languageCode, ResultCallBack callBack) {

        if (queryParams == null) {
            queryParams = new HashMap<>();
        }
        try {
            queryParams.put("init", true);
            queryParams.put("locale", languageCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        this.initiate(callBack);
    }

    public void byPublishType(
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        if (queryParams == null) {
            queryParams = new HashMap<>();
        }

        try {
            queryParams.put("init", true);
            queryParams.put("type", publishType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.initiate(callBack);
    }


    public void all(
            @NotNull String contentType,
            @NotNull Date from_date,
            @NotNull String languageCode,
            @NotNull PublishType publishType,
            @NotNull ResultCallBack callBack) {
        String startFromDate = convertUTCToISO(from_date);

        if (queryParams == null) {
            queryParams = new HashMap<>();
        }
        try {
            queryParams.put("init", "true");
            queryParams.put("start_from", startFromDate);
            queryParams.put("content_type_uid", contentType);
            queryParams.put("type", publishType);
            queryParams.put("locale", languageCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.initiate(callBack);
    }


    private void initiate(ResultCallBack callBack) {
        Map<String, Object> queryParam = calculateHeader(headerMap, queryParams);
        Call<ResponseBody> request = service.synchronization(headerMap, queryParam);
        request(request, callBack);
    }

}
