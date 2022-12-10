package io.github.ishaileshmishra;

import io.github.ishaileshmishra.callback.ResultCallBack;
import io.github.ishaileshmishra.model.Error;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;

public class CDAConnection {

    final protected void request(Call<ResponseBody> request, ResultCallBack callback) {
        try {
            Response<ResponseBody> response = request.execute();
            if (response.isSuccessful()) {
                callback.onSuccess(response);
            } else {
                assert response.errorBody() != null;
                //Error error = new Gson().fromJson(response.errorBody().string(), Error.class);
                //callback.onFailure(error);
            }
        } catch (IOException e) {
            Error error = new Error("No Error Message Found", 400,
                    "No Error Details Found");
            callback.onFailure(error);
        }
    }


    final protected HashMap<String, Object> removeEnv(
            HashMap<String, String> headers,
            HashMap<String, Object> params) {
        if (headers.containsKey("environment")) {
            params.put("environment", headers.get("environment"));
        }
        return params;
    }

}
