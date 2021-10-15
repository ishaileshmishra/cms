package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class SyncTestCase {

    @Test
    void testRunSync() {
        Contentstack stack = new Contentstack.Stack(
                "blt2334343443", "cs4i34ifnerifnerin", "dev").build();
        stack.sync().init(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) throws IOException {

            }

            @Override
            public void onFailure(Error error) {

            }
        });
    }

}
