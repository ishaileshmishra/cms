package io.github.ishaileshmishra.callback;

import io.github.ishaileshmishra.model.Error;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

/**
 * <p>ResultCallBack interface.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public interface ResultCallBack {

    /**
     * <p>onSuccess.</p>
     *
     * @param body a {@link retrofit2.Response} object
     * @throws java.io.IOException if any.
     */
    void onSuccess(Response<ResponseBody> body) throws IOException;

    /**
     * <p>onFailure.</p>
     *
     * @param error a {@link io.github.ishaileshmishra.model.Error} object
     */
    void onFailure(Error error);
}
