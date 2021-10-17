package com.contentstack.sdk;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ContentstackUnitTest {
    private static final Dotenv env = Dotenv.load();
    private static Contentstack contentstack;

    @BeforeAll
    public static void setUp() {
        contentstack = new Contentstack.Stack(env.get("apiKey"), env.get("deliveryToken"), env.get("environment")).build();
    }

    @Test
    void contentType() {
        ContentType contentType = contentstack.contentType("contentTypeUid");
        Assertions.assertEquals("contentTypeUid", contentType.contentTypeUid);
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
    void sync() {
    }
}
