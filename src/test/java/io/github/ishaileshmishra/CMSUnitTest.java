package io.github.ishaileshmishra;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CMSUnitTest {
    private static final Dotenv env = Dotenv.load();
    private static CMS CMS;

    @BeforeAll
    public static void setUp() {
        CMS = new CMS.Stack(env.get("apiKey"),
                env.get("deliveryToken"), env.get("environment")).build();
    }

    @Test
    void contentType() {
        ContentType contentType = CMS.contentType("product");
        Assertions.assertEquals("product", contentType.contentTypeUid);
    }

    @Test
    void asset() {
        Asset asset = CMS.asset("assetUid");
        Assertions.assertEquals("assetUid", asset.assetUid);
    }

    @Test
    void assetLibrary() {
        AssetLibrary asset = CMS.assetLibrary();
        Assertions.assertTrue(asset.urlQueries.isEmpty());
    }

    @Test
    void entry() {
        Entry entry = CMS.contentType("product").entry("bltfce1580ccc988d68");
    }

    @Test
    void query() {
        Query query = CMS.contentType("product").query();
    }
}
