# Recently Viewed Products — Implementation Plan

## 1. Architecture

```
Browser/App
    │
    ├── POST /api/v1/products/{id}/view  (fire & forget)
    └── GET  /api/v1/customer/recently-viewed
            │
        [sm-shop] RecentlyViewedApi (Controller)
            │
        [sm-shop] RecentlyViewedFacade
            │
        [sm-core] RecentlyViewedService
            │
        [sm-core-model] RecentlyViewed entity + Repository
```

---

## 2. Database Schema

```sql
CREATE TABLE RECENTLY_VIEWED (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id  BIGINT       NOT NULL,
    customer_id BIGINT       NULL,          -- NULL for guests
    session_id  VARCHAR(255) NULL,          -- guest identifier
    viewed_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_rv_product  FOREIGN KEY (product_id)  REFERENCES PRODUCT(id),
    CONSTRAINT fk_rv_customer FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id),
    INDEX idx_rv_customer (customer_id, viewed_at DESC),
    INDEX idx_rv_session  (session_id,  viewed_at DESC)
);
```

---

## 3. API Contract

### POST `/api/v1/products/{id}/view`
- Auth: optional (JWT or guest)
- Header: `X-Session-Id: <uuid>` (required for guests)
- Body: none
- Response: `204 No Content`

### GET `/api/v1/customer/recently-viewed`
- Auth: optional
- Header: `X-Session-Id: <uuid>` (for guests)
- Response `200 OK`:
```json
[
  {
    "id": 42,
    "name": "Blue T-Shirt",
    "image": "/api/v1/products/42/image/0",
    "price": 29.99,
    "viewedAt": "2026-03-24T16:00:00Z"
  }
]
```

### GET `/api/v1/admin/analytics/most-viewed` (bonus)
- Auth: ADMIN role required
- Response: same shape, sorted by view count desc

---

## 4. File-Level Changes

### sm-core-model
```
src/main/java/com/salesmanager/core/model/catalog/
└── RecentlyViewed.java
```

### sm-core
```
src/main/java/com/salesmanager/core/
├── business/repository/catalog/
│   └── RecentlyViewedRepository.java
└── business/services/catalog/
    ├── RecentlyViewedService.java
    └── impl/RecentlyViewedServiceImpl.java
```

### sm-shop-model
```
src/main/java/com/salesmanager/shop/model/catalog/
└── RecentlyViewedProduct.java
```

### sm-shop
```
src/main/java/com/salesmanager/shop/
├── store/api/v1/catalog/
│   └── RecentlyViewedApi.java
└── store/facade/catalog/
    ├── RecentlyViewedFacade.java
    └── impl/RecentlyViewedFacadeImpl.java
```

---

## 5. Backend Class Structure

### Entity — `sm-core-model/.../RecentlyViewed.java`
```java
@Entity
@Table(name = "RECENTLY_VIEWED")
public class RecentlyViewed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "viewed_at", nullable = false)
    private Instant viewedAt = Instant.now();
    // getters/setters
}
```

### Repository — `sm-core/.../RecentlyViewedRepository.java`
```java
public interface RecentlyViewedRepository extends JpaRepository<RecentlyViewed, Long> {

    List<RecentlyViewed> findTop10ByCustomerIdOrderByViewedAtDesc(Long customerId);
    List<RecentlyViewed> findTop10BySessionIdOrderByViewedAtDesc(String sessionId);

    Optional<RecentlyViewed> findByCustomerIdAndProductId(Long customerId, Long productId);
    Optional<RecentlyViewed> findBySessionIdAndProductId(String sessionId, Long productId);

    @Query("SELECT rv.product.id, COUNT(rv) as views FROM RecentlyViewed rv " +
           "GROUP BY rv.product.id ORDER BY views DESC")
    List<Object[]> findMostViewed(Pageable pageable);
}
```

### Service — `sm-core/.../RecentlyViewedServiceImpl.java`
```java
@Service
public class RecentlyViewedServiceImpl implements RecentlyViewedService {

    @Autowired private RecentlyViewedRepository repo;
    @Autowired private ProductRepository productRepo;

    @Transactional
    public void recordView(Long productId, Long customerId, String sessionId) {
        Optional<RecentlyViewed> existing = customerId != null
            ? repo.findByCustomerIdAndProductId(customerId, productId)
            : repo.findBySessionIdAndProductId(sessionId, productId);

        RecentlyViewed rv = existing.orElseGet(() -> {
            RecentlyViewed n = new RecentlyViewed();
            n.setProduct(productRepo.getOne(productId));
            n.setCustomerId(customerId);
            n.setSessionId(sessionId);
            return n;
        });
        rv.setViewedAt(Instant.now());
        repo.save(rv);
    }

    public List<RecentlyViewed> getRecentlyViewed(Long customerId, String sessionId) {
        return customerId != null
            ? repo.findTop10ByCustomerIdOrderByViewedAtDesc(customerId)
            : repo.findTop10BySessionIdOrderByViewedAtDesc(sessionId);
    }
}
```

### Facade — `sm-shop/.../RecentlyViewedFacadeImpl.java`
```java
@Service
public class RecentlyViewedFacadeImpl implements RecentlyViewedFacade {

    @Autowired private RecentlyViewedService service;

    public void recordView(Long productId, Long customerId, String sessionId) {
        service.recordView(productId, customerId, sessionId);
    }

    public List<RecentlyViewedProduct> getRecentlyViewed(
            Long customerId, String sessionId, MerchantStore store, Language language) {
        return service.getRecentlyViewed(customerId, sessionId)
            .stream()
            .map(rv -> toDto(rv, language))
            .collect(Collectors.toList());
    }

    private RecentlyViewedProduct toDto(RecentlyViewed rv, Language language) {
        Product p = rv.getProduct();
        RecentlyViewedProduct dto = new RecentlyViewedProduct();
        dto.setId(p.getId());
        dto.setName(p.getDescriptions().stream()
            .filter(d -> d.getLanguage().equals(language))
            .findFirst().map(ProductDescription::getName).orElse(""));
        dto.setPrice(p.getPrice());
        dto.setViewedAt(rv.getViewedAt());
        return dto;
    }
}
```

### Controller — `sm-shop/.../RecentlyViewedApi.java`
```java
@RestController
@RequestMapping("/api/v1")
public class RecentlyViewedApi {

    @Autowired private RecentlyViewedFacade facade;
    @Autowired private CustomerService customerService;
    @Autowired private StoreFacade storeFacade;
    @Autowired private LanguageService languageService;

    private Long resolveCustomerId(Principal principal) {
        if (principal == null) return null;
        return customerService.getByNick(principal.getName()).getId();
    }

    @PostMapping("/products/{id}/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void recordView(
            @PathVariable Long id,
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId,
            Principal principal) {
        facade.recordView(id, resolveCustomerId(principal), sessionId);
    }

    @GetMapping("/customer/recently-viewed")
    public List<RecentlyViewedProduct> getRecentlyViewed(
            @RequestHeader(value = "X-Session-Id", required = false) String sessionId,
            Principal principal,
            @RequestParam(defaultValue = "DEFAULT") String store,
            @RequestParam(defaultValue = "en") String lang) {
        MerchantStore merchantStore = storeFacade.get(store);
        Language language = languageService.getByCode(lang);
        return facade.getRecentlyViewed(resolveCustomerId(principal), sessionId, merchantStore, language);
    }

    @GetMapping("/admin/analytics/most-viewed")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RecentlyViewedProduct> mostViewed() {
        return facade.getMostViewed(PageRequest.of(0, 20));
    }
}
```

---

## 6. Implementation Order

1. **sm-core-model** — `RecentlyViewed` entity + DB migration script
2. **sm-core** — `RecentlyViewedRepository` → `RecentlyViewedService` → `RecentlyViewedServiceImpl`
3. **sm-shop-model** — `RecentlyViewedProduct` DTO
4. **sm-shop** — `RecentlyViewedFacade` → `RecentlyViewedFacadeImpl` → `RecentlyViewedApi`

---

## 7. Risk Analysis

| Risk | Impact | Mitigation |
|---|---|---|
| Guest session collision | Medium | Use `crypto.randomUUID()`, persist in localStorage |
| Unbounded table growth | High | Upsert pattern (1 row per user+product); add periodic cleanup job |
| N+1 on product fetch | Medium | Use `JOIN FETCH` in repository query |
| Breaking existing APIs | Low | New endpoints only, no changes to existing routes |
| Null principal for guests | Low | Explicit null check in `resolveCustomerId()` |

---

## 8. Time Estimate

| Task | Effort |
|---|---|
| DB schema + migration | 1h |
| Backend (entity → controller) | 4h |
| Integration testing | 2h |
| Bonus analytics endpoint | 1h |
| **Total** | **~8h** |
