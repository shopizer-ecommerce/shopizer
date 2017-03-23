package com.salesmanager.admin.data;

import com.salesmanager.core.model.system.MerchantConfiguration;

import java.io.Serializable;
import java.util.List;

/**
 * Created by umesh on 3/21/17.
 */
public class ConfigListWrapper implements Serializable{

    private List<MerchantConfiguration> merchantConfigs;

    public List<MerchantConfiguration> getMerchantConfigs()
    {
        return merchantConfigs;
    }

    public void setMerchantConfigs(List<MerchantConfiguration> merchantConfigs)
    {
        this.merchantConfigs = merchantConfigs;
    }
}
