package com.salesmanager.shop.store.facade.product;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.services.catalog.product.RecentlyViewedService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.RecentlyViewed;
import com.salesmanager.core.model.catalog.product.description.ProductDescription;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableRecentlyViewedProduct;
import com.salesmanager.shop.store.controller.product.facade.RecentlyViewedFacade;

@Service("recentlyViewedFacade")
public class RecentlyViewedFacadeImpl implements RecentlyViewedFacade {

    @Inject
    private RecentlyViewedService recentlyViewedService;

    @Override
    public void recordView(Long productId, Long customerId, String sessionId) {
        recentlyViewedService.recordView(productId, customerId, sessionId);
    }

    @Override
    public List<ReadableRecentlyViewedProduct> getRecentlyViewed(Long customerId, String sessionId,
            MerchantStore store, Language language) {
        return recentlyViewedService.getRecentlyViewed(customerId, sessionId)
                .stream()
                .map(rv -> toDto(rv, language))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadableRecentlyViewedProduct> getMostViewed(Pageable pageable,
            MerchantStore store, Language language) {
        return recentlyViewedService.getMostViewed(pageable)
                .stream()
                .map(row -> {
                    ReadableRecentlyViewedProduct dto = new ReadableRecentlyViewedProduct();
                    dto.setId(((Number) row[0]).longValue());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private ReadableRecentlyViewedProduct toDto(RecentlyViewed rv, Language language) {
        Product p = rv.getProduct();
        ReadableRecentlyViewedProduct dto = new ReadableRecentlyViewedProduct();
        dto.setId(p.getId());
        dto.setViewedAt(rv.getViewedAt());

        // resolve name for requested language
        if (p.getDescriptions() != null) {
            p.getDescriptions().stream()
                    .filter(d -> d.getLanguage() != null && d.getLanguage().getId().equals(language.getId()))
                    .findFirst()
                    .map(ProductDescription::getName)
                    .ifPresent(dto::setName);
        }

        // first product image
        if (p.getImages() != null && !p.getImages().isEmpty()) {
            dto.setImage(p.getImages().iterator().next().getProductImageUrl());
        }

        // price from availability
        if (p.getAvailabilities() != null) {
            p.getAvailabilities().stream()
                    .flatMap(a -> a.getPrices().stream())
                    .filter(pr -> pr.isDefaultPrice())
                    .findFirst()
                    .ifPresent(pr -> dto.setPrice(pr.getProductPriceAmount().doubleValue()));
        }

        return dto;
    }
}
