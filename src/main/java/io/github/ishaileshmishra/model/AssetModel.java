package io.github.ishaileshmishra.model;

import io.github.ishaileshmishra.CMS;

/**
 * <p>AssetModel class.</p>
 *
 * @author shaileshmishra
 * @version $Id: $Id
 */
public class AssetModel {


    void showTest() {
        CMS CMS = new CMS
                .Stack("", "", "environment").build();
        CMS.assetLibrary();
    }
}
