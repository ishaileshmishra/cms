package io.github.ishaileshmishra;
import io.github.ishaileshmishra.callback.ResultCallBack;
import io.github.ishaileshmishra.model.Error;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

public class SyncTestCase {

    @Test
    void testRunSync() {
        CMS stack = new CMS.Stack(
                "blt2334343443", "cs4i34ifnerifnerin", "dev").build();
        stack.sync().init(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) {

            }

            @Override
            public void onFailure(Error error) {

            }
        });

    }

}
