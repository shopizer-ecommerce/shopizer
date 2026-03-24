package com.salesmanager.test.shop.unit.facade;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.catalog.pricing.PricingService;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.relationship.ProductRelationshipService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.ProductCriteria;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationship;
import com.salesmanager.core.model.catalog.product.relationship.ProductRelationshipType;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.ReadableProduct;
import com.salesmanager.shop.model.catalog.product.ReadableProductList;
import com.salesmanager.shop.store.facade.product.ProductFacadeImpl;
import com.salesmanager.shop.utils.ImageFilePath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductFacadeImplTest {

    @InjectMocks
    private ProductFacadeImpl productFacade;

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private PricingService pricingService;

    @Mock
    private ProductRelationshipService productRelationshipService;

    @Mock
    private ProductAttributeService productAttributeService;

    @Mock
    private ImageFilePath imageUtils;

    private MerchantStore store;
    private Language language;

    @Before
    public void setUp() {
        store = new MerchantStore();
        store.setCode("DEFAULT");
        language = new Language();
        language.setCode("en");
        // inject the @Qualifier("img") field manually
        ReflectionTestUtils.setField(productFacade, "imageUtils", imageUtils);
    }

    @Test
    public void getProduct_whenProductNotFound_returnsNull() throws Exception {
        when(productService.getBySku(eq("UNKNOWN"), eq(store), eq(language))).thenReturn(null);

        ReadableProduct result = productFacade.getProduct(store, "UNKNOWN", language);

        assertNull(result);
    }

    @Test
    public void getProduct_byId_delegatesToService() {
        Product product = new Product();
        product.setId(1L);
        when(productService.findOne(1L, store)).thenReturn(product);

        Product result = productFacade.getProduct(1L, store);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    public void getProduct_byId_whenNotFound_returnsNull() {
        when(productService.findOne(99L, store)).thenReturn(null);

        Product result = productFacade.getProduct(99L, store);

        assertNull(result);
    }

    @Test
    public void getProductListsByCriterias_withNoCategoryIds_returnsProductList() throws Exception {
        ProductCriteria criteria = new ProductCriteria();
        criteria.setStartPage(0);
        criteria.setMaxCount(10);

        Product p = new Product();
        p.setSortOrder(1);
        Page<Product> page = new PageImpl<>(Collections.singletonList(p));

        when(productService.listByStore(eq(store), eq(language), eq(criteria), eq(0), eq(10)))
                .thenReturn(page);

        ReadableProductList result = productFacade.getProductListsByCriterias(store, language, criteria);

        assertNotNull(result);
        assertEquals(1, result.getRecordsTotal());
    }

    @Test
    public void getProductListsByCriterias_withEmptyResults_returnsEmptyList() throws Exception {
        ProductCriteria criteria = new ProductCriteria();
        criteria.setStartPage(0);
        criteria.setMaxCount(10);

        Page<Product> emptyPage = new PageImpl<>(Collections.emptyList());
        when(productService.listByStore(eq(store), eq(language), eq(criteria), eq(0), eq(10)))
                .thenReturn(emptyPage);

        ReadableProductList result = productFacade.getProductListsByCriterias(store, language, criteria);

        assertEquals(0, result.getProducts().size());
        assertEquals(0L, result.getRecordsTotal());
    }

    @Test
    public void relatedItems_whenNoRelationships_returnsNull() throws Exception {
        Product product = new Product();
        when(productRelationshipService.getByType(store, product, ProductRelationshipType.RELATED_ITEM))
                .thenReturn(Collections.emptyList());

        List<ReadableProduct> result = productFacade.relatedItems(store, product, language);

        assertNull(result);
    }

    @Test
    public void relatedItems_whenRelationshipsExist_returnsList() throws Exception {
        Product product = new Product();
        product.setMerchantStore(store);

        Product related = new Product();
        related.setMerchantStore(store);

        ProductRelationship rel = new ProductRelationship();
        rel.setRelatedProduct(related);

        when(productRelationshipService.getByType(store, product, ProductRelationshipType.RELATED_ITEM))
                .thenReturn(Arrays.asList(rel));

        List<ReadableProduct> result = productFacade.relatedItems(store, product, language);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void getProductListsByCriterias_withNullCriteria_throws() throws Exception {
        productFacade.getProductListsByCriterias(store, language, null);
    }
}
