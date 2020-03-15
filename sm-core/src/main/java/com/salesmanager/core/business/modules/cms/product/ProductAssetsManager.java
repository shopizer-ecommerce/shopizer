package com.salesmanager.core.business.modules.cms.product;

import java.io.Serializable;
import com.salesmanager.core.business.modules.cms.common.AssetsManager;

public interface ProductAssetsManager
    extends AssetsManager, ProductImageGet, ProductImagePut, ProductImageRemove, Serializable {

}
