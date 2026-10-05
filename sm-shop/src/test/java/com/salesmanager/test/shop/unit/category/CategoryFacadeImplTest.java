package com.salesmanager.test.shop.unit.category;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.catalog.category.CategoryService;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.model.catalog.category.Category;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.mapper.catalog.ReadableCategoryMapper;
import com.salesmanager.shop.populator.catalog.PersistableCategoryPopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.facade.category.CategoryFacadeImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryFacadeImplTest {

    @Mock private CategoryService categoryService;
    @Mock private MerchantStoreService merchantStoreService;
    @Mock private PersistableCategoryPopulator persistableCatagoryPopulator;
    @Mock private ReadableCategoryMapper readableCategoryMapper;

    @InjectMocks private CategoryFacadeImpl categoryFacade;

    @Test
    public void deleteCategory_sameStore_invokesStoreScoped_andDeletes() throws ServiceException {
        int storeId = 1;
        Long categoryId = 42L;
        MerchantStore store = new MerchantStore();
        store.setId(storeId);
        Category category = new Category();
        category.setId(categoryId);
        when(categoryService.getById(categoryId, storeId)).thenReturn(category);

        categoryFacade.deleteCategory(categoryId, store);

        verify(categoryService).getById(categoryId, storeId);
        verify(categoryService).delete(category);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteCategory_differentStore_throwsAndNeverDeletes() throws ServiceException {
        int storeId = 1;
        Long categoryId = 99L;
        MerchantStore store = new MerchantStore();
        store.setId(storeId);
        when(categoryService.getById(categoryId, storeId)).thenReturn(null);

        try {
            categoryFacade.deleteCategory(categoryId, store);
        } finally {
            verify(categoryService).getById(categoryId, storeId);
            verifyNoMoreInteractions(categoryService);
        }
    }
}
