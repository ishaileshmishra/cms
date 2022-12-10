package io.github.ishaileshmishra;

import io.github.ishaileshmishra.callback.ResultCallBack;
import io.github.ishaileshmishra.model.Error;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import retrofit2.Response;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssetLibraryTestCase {

    static CMS client;

    @BeforeAll
    public static void initBefore() {
        client = new CMS
                .Stack(
                "blt7484376874",
                "cs7487647b4rhybf",
                "product").setHost("cdn.contentstack.io").build();

    }

    @Test void testTunAnExample(){
        client.assetLibrary().fetchAll(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) {
                System.out.println(body);
            }

            @Override
            public void onFailure(Error error) {
                System.out.println(error.getErrorDetail());
            }
        });
    }
}
