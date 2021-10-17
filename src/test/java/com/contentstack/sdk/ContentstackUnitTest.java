package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

public class ContentstackUnitTest {
    private static final Dotenv env = Dotenv.load();
    private static Contentstack contentstack;

    @BeforeAll
    public static void setUp() {
        contentstack = new Contentstack.Stack(env.get("apiKey"),
                env.get("deliveryToken"), env.get("environment")).build();
    }

    @Test
    void contentType() {
        ContentType contentType = contentstack.contentType("product");
        Assertions.assertEquals("product", contentType.contentTypeUid);
    }

    @Test
    void asset() {
        Asset asset = contentstack.asset("assetUid");
        Assertions.assertEquals("assetUid", asset.assetUid);
    }

    @Test
    void assetLibrary() {
        AssetLibrary asset = contentstack.assetLibrary();
        Assertions.assertTrue(asset.urlQueries.isEmpty());
    }

    @Test
    void entry() {
        Entry entry = contentstack.contentType("product").entry("bltfce1580ccc988d68");
        entry.includeBranch().fetch(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) throws IOException {
                System.out.println(body.body().string());
            }

            @Override
            public void onFailure(Error error) {
                System.out.println(error.getErrorDetail());
            }
        });
    }

    @Test
    void query() {
        Query query = contentstack.contentType("product").query();
        query.find(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) throws IOException {
                System.out.println(body.body().string());
            }

            @Override
            public void onFailure(Error error) {
                System.out.println(error.getErrorDetail());
            }
        });
    }
}
