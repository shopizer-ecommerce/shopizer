# Shopizer Test Coverage Report

**Generated:** 2026-03-24  
**Version:** 3.2.7  
**Branch:** kiro-devlop  
**JaCoCo Report:** `sm-shop/target/site/jacoco/index.html`

---

## Overall Coverage (sm-shop — JaCoCo Actual)

| Metric | Missed | Total | Coverage |
|--------|--------|-------|----------|
| Instructions | 59,385 | 60,057 | **1%** |
| Branches | 4,349 | 4,402 | **1%** |
| Complexity | 4,322 | 4,360 | 1% |
| Lines | 13,691 | 13,832 | 1% |
| Methods | 2,136 | 2,159 | 1% |
| Classes | 295 | 300 | 2% |

> Configured thresholds in root `pom.xml`: Line ≥ 30%, Branch ≥ 37%. Current coverage is well below both targets — the JaCoCo `check` goal will fail on a full build. These numbers reflect unit tests only (no Spring context loaded), which exercise a small slice of the codebase.

---

## Test Suite Summary

| Module | Test Files | Test Methods | Type |
|--------|-----------|-------------|------|
| `sm-shop` (unit — new) | 4 | 39 | Pure unit / Mockito |
| `sm-shop` (integration — existing) | 11 | ~39 | Spring Boot REST |
| `sm-core` (active) | 8 | ~18 | Unit + Integration |
| `sm-core` (ignored) | 12 | ~15 | `@Ignore`d |
| `sm-core-model` | 0 | 0 | — |
| `sm-core-modules` | 0 | 0 | — |
| **Total active** | **35** | **~96** | |

---

## New Unit Tests Added (2026-03-24)

All 39 tests pass. No Spring context required — fast, isolated.

| Test Class | Location | Tests | Classes Covered |
|------------|----------|-------|-----------------|
| `DateUtilTest` | `unit/utils` | 19 | `DateUtil` |
| `FileNameUtilsTest` | `unit/utils` | 6 | `FileNameUtils` |
| `SanitizeUtilsTest` | `unit/utils` | 6 | `SanitizeUtils` |
| `ProductFacadeImplTest` | `unit/facade` | 8 | `ProductFacadeImpl` |

### DateUtilTest — 19 tests
| Test | Covers |
|------|--------|
| `generateTimeStamp_returnsNonNull` | `generateTimeStamp()` |
| `generateTimeStamp_hasExpectedLength` | `generateTimeStamp()` format |
| `formatDate_withNullReturnsToday` | null guard |
| `formatDate_withDateReturnsFormattedString` | `yyyy-MM-dd` pattern |
| `formatYear_withNullReturnsNull` | null guard |
| `formatYear_withDateReturnsYear` | year format |
| `formatLongDate_withNullReturnsNull` | null guard |
| `formatLongDate_withDateReturnsNonNull` | long date format |
| `getDate_returnsNonNull` | parse valid date |
| `getDate_withInvalidFormat_throws` | parse exception |
| `addDaysToCurrentDate_returnsDateInFuture` | positive offset |
| `addDaysToCurrentDate_returnsDateInPast` | negative offset |
| `dateBeforeEqualsDate_withNullFirstDate_returnsTrue` | null guard |
| `dateBeforeEqualsDate_withNullSecondDate_returnsTrue` | null guard |
| `dateBeforeEqualsDate_firstBeforeSecond_returnsTrue` | before case |
| `dateBeforeEqualsDate_firstAfterSecond_returnsFalse` | after case |
| `dateBeforeEqualsDate_equalDates_returnsTrue` | equal case |
| `getPresentDate_returnsNonNull` | present date |
| `getPresentYear_returnsFourDigitYear` | year format |

### FileNameUtilsTest — 6 tests
| Test | Covers |
|------|--------|
| `validFileName_withValidName_returnsTrue` | happy path |
| `validFileName_withNoExtension_returnsFalse` | missing extension |
| `validFileName_withNoBaseName_returnsFalse` | missing base name |
| `validFileName_withEmptyString_returnsFalse` | empty input |
| `validFileName_withDotOnly_returnsFalse` | dot-only edge case |
| `validFileName_withMultipleDots_returnsTrue` | multi-dot name |

### SanitizeUtilsTest — 6 tests
| Test | Covers |
|------|--------|
| `getSafeRequestParamString_withCleanInput_returnsSameValue` | clean passthrough |
| `getSafeRequestParamString_withNull_returnsEmpty` | null guard |
| `getSafeRequestParamString_withEmptyString_returnsEmpty` | empty guard |
| `getSafeRequestParamString_stripsBlacklistedChars` | `;` `%` removal |
| `getSafeRequestParamString_withAlphanumeric_preservesContent` | safe chars preserved |
| `getSafeRequestParamString_withScriptTag_stripsAngleBrackets` | XSS `<>` removal |

### ProductFacadeImplTest — 8 tests
| Test | Covers |
|------|--------|
| `getProduct_whenProductNotFound_returnsNull` | null SKU lookup |
| `getProduct_byId_delegatesToService` | ID-based lookup |
| `getProduct_byId_whenNotFound_returnsNull` | null ID lookup |
| `getProductListsByCriterias_withNoCategoryIds_returnsProductList` | list with results |
| `getProductListsByCriterias_withEmptyResults_returnsEmptyList` | empty page |
| `relatedItems_whenNoRelationships_returnsNull` | empty relationships |
| `relatedItems_whenRelationshipsExist_returnsList` | populated relationships |
| `getProductListsByCriterias_withNullCriteria_throws` | null validation |

---

## Package-Level Coverage (sm-shop — JaCoCo)

| Package | Instruction Cov. | Branch Cov. | Lines Covered / Total |
|---------|-----------------|-------------|----------------------|
| `store.facade.product` | 3% | 3% | 36 / 1,070 |
| `utils` | **8%** | **11%** | 58 / 795 |
| `populator.catalog` | 6% | 6% | 47 / 623 |
| `mapper.catalog.product` | 0% | 0% | 0 / 871 |
| `store.controller.order.facade` | 0% | 0% | 0 / 799 |
| `mapper.catalog` | 0% | 0% | 0 / 657 |
| `populator.order` | 0% | 0% | 0 / 668 |
| `populator.customer` | 0% | 0% | 0 / 602 |
| `store.controller.shoppingCart.facade` | 0% | 0% | 0 / 503 |
| `store.api.v1.product` | 0% | 0% | 0 / 485 |
| All other packages | 0% | 0% | 0 |

> `utils` package has the highest coverage (8% instructions, 11% branches) due to the new `DateUtil`, `FileNameUtils`, and `SanitizeUtils` unit tests.

---

## Existing sm-shop Integration Tests

| Test Class | Tests | Area |
|------------|-------|------|
| `ShoppingCartAPIIntegrationTest` | 7 | Cart REST API |
| `CategoryManagementAPIIntegrationTest` | 7 | Category REST API |
| `ProductManagementAPIIntegrationTest` | 7 | Product REST API |
| `ProductV2ManagementAPIIntegrationTest` | 1 | Product v2 REST API |
| `UserApiIntegrationTest` | 2 | User REST API |
| `MerchantStoreApiIntegrationTest` | 3 | Store REST API |
| `TaxRateIntegrationTest` | 2 | Tax REST API |
| `CustomerRegistrationIntegrationTest` | 1 | Customer REST API |
| `ActuatorTest` | 1 | Health endpoint |
| `OptinApiIntegrationTest` | 1 | Optin REST API |
| `GeneratePasswordTest` | 1 | Password encoder |

### Skipped / Broken
| Test Class | Reason |
|------------|--------|
| `SearchApiIntegrationTest` | `@Test` commented out |
| `OrderApiIntegrationTest` | No `@Test` annotations |

---

## sm-core Tests

### Active
| Test Class | Tests | Type |
|------------|-------|------|
| `DataUtilsTest` | 9 | Unit |
| `CategoryTest` | 1 | Integration |
| `ProductTest` | 1 | Integration |
| `ProductNextGenTest` | 1 | Integration |
| `ManufacturerTest` | 1 | Integration |
| `ShoppingCartTest` | 1 | Integration |
| `ModulesTest` | 1 | Integration |
| `UtilsTestCase` | 1 | Integration |

### Ignored
| Test Class | Reason |
|------------|--------|
| `OrderTest` | `@Ignore` on class |
| `CustomerTest` | `@Ignore` on class |
| `ReferencesTest` | `@Ignore` on class |
| `ShippingQuoteByWeightTest` | `@Ignore` on class |
| `ShippingMethodDecisionTest` | `@Ignore` on class |
| `StaticContentTest` | `@Ignore` on class |
| `ContentImagesTest` | `@Ignore` on class |
| `SendEmailTest` | `@Ignore` on class |
| `UtilsTestCase.testCache` | `@Ignore` on method |
| `UtilsTestCase.testCurrency` | `@Ignore` on method |

---

## Coverage Gaps (Priority Order)

| Area | Module | Impact |
|------|--------|--------|
| `mapper.catalog.product` | sm-shop | 871 lines, 0% |
| `store.controller.order.facade` | sm-shop | 799 lines, 0% |
| `mapper.catalog` | sm-shop | 657 lines, 0% |
| `populator.order` | sm-shop | 668 lines, 0% |
| `populator.customer` | sm-shop | 602 lines, 0% |
| `store.controller.shoppingCart.facade` | sm-shop | 503 lines, 0% |
| `store.api.v1.product` | sm-shop | 485 lines, 0% |
| Order management | sm-core | `OrderTest` is `@Ignore`d |
| Customer management | sm-core | `CustomerTest` is `@Ignore`d |
| Shipping logic | sm-core | All shipping tests `@Ignore`d |
| `sm-core-model` | sm-core-model | 186 source files, 0 tests |

---

## How to Run & View Coverage

```bash
# Run new unit tests only (fast, no DB)
cd sm-shop
./mvnw test -Dtest="DateUtilTest,FileNameUtilsTest,SanitizeUtilsTest,ProductFacadeImplTest"

# Run all sm-shop tests
./mvnw clean test

# Open JaCoCo HTML report
open target/site/jacoco/index.html
```
