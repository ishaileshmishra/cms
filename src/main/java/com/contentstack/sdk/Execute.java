package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class Execute {

    final void get(Call<ResponseBody> request, ResultCallBack callback) {
        try {
            Response<ResponseBody> response = request.execute();
            if (response.isSuccessful()) {
                callback.onSuccess(response);
            } else {
                Error error = new Gson().fromJson(response.errorBody().string(), Error.class);
                callback.onFailure(error);
            }
        } catch (IOException e) {
            callback.onFailure(new Error());
        }
    }

}
