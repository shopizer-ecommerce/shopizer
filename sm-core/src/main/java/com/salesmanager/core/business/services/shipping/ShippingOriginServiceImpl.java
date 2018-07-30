package com.salesmanager.core.business.services.shipping;

import com.salesmanager.core.business.repositories.shipping.ShippingOriginRepository;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shipping.ShippingOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service("shippingOriginService")
public class ShippingOriginServiceImpl extends SalesManagerEntityServiceImpl<Long, ShippingOrigin> implements ShippingOriginService {

    private ShippingOriginRepository shippingOriginRepository;

    @Inject
    public ShippingOriginServiceImpl(ShippingOriginRepository shippingOriginRepository) {
        super(shippingOriginRepository);
        this.shippingOriginRepository = shippingOriginRepository;
    }

    @Override
    public ShippingOrigin getByStore(MerchantStore store) {
        // TODO Auto-generated method stub
        ShippingOrigin origin = shippingOriginRepository.findByStore(store.getId());
        return origin;
    }

}
