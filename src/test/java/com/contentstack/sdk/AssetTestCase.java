package com.contentstack.sdk;

import com.contentstack.sdk.callback.ResultCallBack;
import com.contentstack.sdk.model.Error;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import retrofit2.Response;

import java.io.IOException;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssetTestCase {

    AssetLibrary allAsset = null;
    Logger logger = Logger.getLogger(AssetTestCase.class.getName());

    @BeforeAll
    void testAssetSetup() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String deliveryToken = dotenv.get("DELIVERY_TOKEN");
        String environment = dotenv.get("ENVIRONMENT");

        Contentstack contentstack = new Contentstack.Stack(apiKey, deliveryToken, environment).build();
        allAsset = contentstack.assetLibrary();
    }


    @Test
    void testAllAssets() {
        allAsset.includeFallback().fetchAll(new ResultCallBack() {
            @Override
            public void onSuccess(Response<ResponseBody> body) throws IOException {
                String response = body.body().string();
                logger.info(response);
            }

            @Override
            public void onFailure(Error error) {
                String response = error.getErrorDetail();
                logger.info(response);
            }
        });
    }

}
