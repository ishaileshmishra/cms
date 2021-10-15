package com.contentstack.sdk.callback;

import com.contentstack.sdk.model.Error;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

public interface ResultCallBack {

    void onSuccess(Response<ResponseBody> body) throws IOException;

    void onFailure(Error error);
}
