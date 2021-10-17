package com.contentstack.sdk.model;

import com.contentstack.sdk.Contentstack;

public class AssetModel {


    void showTest() {
        Contentstack contentstack = new Contentstack
                .Stack("", "", "environment").build();
        contentstack.assetLibrary();
    }
}
