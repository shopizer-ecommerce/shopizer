package com.salesmanager.core.business.services.merchant;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.merchant.MerchantRepository;
import com.salesmanager.core.business.services.catalog.product.manufacturer.ManufacturerService;
import com.salesmanager.core.business.services.catalog.product.type.ProductTypeService;
import com.salesmanager.core.business.services.common.generic.SalesManagerEntityServiceImpl;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.model.merchant.MerchantStore;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("merchantService")
public class MerchantStoreServiceImpl extends SalesManagerEntityServiceImpl<Integer, MerchantStore>
        implements MerchantStoreService {


    @Inject
    protected ProductTypeService productTypeService;

    @Inject
    private TaxClassService taxClassService;

    @Inject
    private ManufacturerService manufacturerService;

    private MerchantRepository merchantRepository;

    @Inject
    public MerchantStoreServiceImpl(MerchantRepository merchantRepository) {
        super(merchantRepository);
        this.merchantRepository = merchantRepository;
    }


    public MerchantStore getMerchantStore(String merchantStoreCode) throws ServiceException {
        return merchantRepository.findByCode(merchantStoreCode);
    }

    @Override
    public void saveOrUpdate(MerchantStore store) throws ServiceException {

        super.save(store);

    }


    @Override
    public MerchantStore getByCode(String code) throws ServiceException {

        return merchantRepository.findByCode(code);
    }

}
