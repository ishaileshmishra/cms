package io.github.ishaileshmishra.model;

import io.github.ishaileshmishra.CMS;

public class AssetModel {


    void showTest() {
        CMS CMS = new CMS
                .Stack("", "", "environment").build();
        CMS.assetLibrary();
    }
}
