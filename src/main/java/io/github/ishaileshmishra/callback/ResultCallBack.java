package io.github.ishaileshmishra.callback;

import io.github.ishaileshmishra.model.Error;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

public interface ResultCallBack {

    void onSuccess(Response<ResponseBody> body) throws IOException;

    void onFailure(Error error);
}
