# Feature: Recently Viewed Products

**Status:** Implemented  
**Branch:** `F-createDOc`  
**Repos affected:** `shopizer` · `shopizer-shop-reactjs`

---

## Overview

Tracks products viewed by both logged-in customers and guest users, and surfaces a "Recently Viewed" section on the product detail page and home page. Up to 10 products are shown per user, always sorted by most recently viewed.

---

## Commits

| Repo | Commit | Description |
|---|---|---|
| shopizer | `b1c8963` | DB migration script for MySQL/production |
| shopizer | `a39cb2c` | Backend implementation (entity → controller) |
| shopizer | `123f9e0` | Implementation plan document |
| shopizer-shop-reactjs | `d88213f` | React storefront implementation |

---

## Architecture

```
Browser
  │
  ├── POST /api/v1/products/{id}/view       ← called on product page load
  └── GET  /api/v1/customer/recently-viewed ← called to render the section
            │
        RecentlyViewedApi          (sm-shop · store/api/v1/product)
            │
        RecentlyViewedFacade       (sm-shop · store/facade/product)
            │
        RecentlyViewedService      (sm-core · business/services/catalog/product)
            │
        RecentlyViewedRepository   (sm-core · business/repositories/catalog/product)
            │
        RecentlyViewed entity      (sm-core-model · model/catalog/product)
```

---

## Database

**Table: `RECENTLY_VIEWED`**

| Column | Type | Notes |
|---|---|---|
| `RECENTLY_VIEWED_ID` | BIGINT PK | Hibernate TABLE sequencer |
| `PRODUCT_ID` | BIGINT FK | References `PRODUCT` |
| `CUSTOMER_ID` | BIGINT nullable | NULL for guest users |
| `SESSION_ID` | VARCHAR(255) nullable | Guest identifier from localStorage |
| `VIEWED_AT` | DATETIME | Updated on every view (upsert) |

**Indexes:** `(CUSTOMER_ID, VIEWED_AT DESC)` · `(SESSION_ID, VIEWED_AT DESC)`

**Migration:**  
- Local/H2: handled automatically by `hibernate.hbm2ddl.auto=update`  
- MySQL/production: run `sm-core/src/main/resources/db/migration/V1__add_recently_viewed.sql`

---

## API

### `POST /api/v1/products/{id}/view`
Records a product view. Called fire-and-forget when a product page loads.

| | |
|---|---|
| Auth | Optional (JWT or guest) |
| Header | `X-Session-Id: <uuid>` (required for guests) |
| Body | None |
| Response | `204 No Content` |

### `GET /api/v1/customer/recently-viewed`
Returns up to 10 recently viewed products for the current user, sorted latest first.

| | |
|---|---|
| Auth | Optional |
| Header | `X-Session-Id: <uuid>` (for guests) |
| Query params | `store` (default: `DEFAULT`), `lang` (default: `en`) |
| Response | `200 OK` — array of `ReadableRecentlyViewedProduct` |

```json
[
  {
    "id": 42,
    "name": "Blue T-Shirt",
    "image": "/api/v1/products/42/image/0",
    "price": 29.99,
    "viewedAt": "2026-03-25T05:00:00.000+00:00"
  }
]
```

### `GET /api/v1/private/analytics/most-viewed`
Returns top 20 most viewed products across all users. Admin only.

| | |
|---|---|
| Auth | Required — ADMIN role |
| Response | `200 OK` — same shape as above |

---

## Files Changed

### `shopizer` (backend)

| File | Module | Type |
|---|---|---|
| `model/catalog/product/RecentlyViewed.java` | sm-core-model | New — JPA entity |
| `repositories/catalog/product/RecentlyViewedRepository.java` | sm-core | New — Spring Data repository |
| `services/catalog/product/RecentlyViewedService.java` | sm-core | New — service interface |
| `services/catalog/product/RecentlyViewedServiceImpl.java` | sm-core | New — upsert logic |
| `model/catalog/product/ReadableRecentlyViewedProduct.java` | sm-shop-model | New — response DTO |
| `store/controller/product/facade/RecentlyViewedFacade.java` | sm-shop-model | New — facade interface |
| `store/facade/product/RecentlyViewedFacadeImpl.java` | sm-shop | New — entity → DTO mapping |
| `store/api/v1/product/RecentlyViewedApi.java` | sm-shop | New — REST controller |
| `db/migration/V1__add_recently_viewed.sql` | sm-core | New — MySQL migration |

### `shopizer-shop-reactjs` (React storefront)

| File | Type |
|---|---|
| `src/redux/actions/recentlyViewedActions.js` | New — `recordView` + `fetchRecentlyViewed` actions |
| `src/redux/reducers/recentlyViewedReducer.js` | New — state slice |
| `src/redux/reducers/rootReducer.js` | Modified — registered `recentlyViewed` slice |
| `src/components/product/RecentlyViewed.js` | New — grid UI component |
| `src/pages/product-details/ProductDetail.js` | Modified — dispatch `recordView` on mount + render component |
| `src/pages/home/Home.js` | Modified — render component above newsletter |

---

## Key Design Decisions

**Upsert instead of insert**  
Each user+product pair has at most one row. Revisiting a product updates `VIEWED_AT` rather than adding a new row. This keeps the table bounded and ensures the top-10 query always reflects recency correctly.

**Guest tracking via sessionId**  
Guest users are identified by a UUID stored in `localStorage` and sent as `X-Session-Id` header. The backend branches on `customerId != null` to decide which identifier to use.

**Fire-and-forget POST**  
The `recordView` call from React swallows errors silently. A failed view recording should never break the product page experience.

**No login merge**  
Guest history and logged-in history are tracked separately. Merging them at login time is out of scope for this version.

---

## How to Test

1. Open any product page — a `POST /api/v1/products/{id}/view` call fires automatically
2. Navigate to the home page or another product page — the "Recently Viewed" section appears with the product just visited
3. Repeat with multiple products — section shows up to 10, latest first
4. Clear `localStorage` (or use a different browser) to simulate a new guest session

---

## Time Spent

| Task | Actual |
|---|---|
| Planning + architecture | 1h |
| Backend (entity → controller) | 4h |
| React (redux + component + wiring) | 2h |
| DB migration script | 0.5h |
| Documentation | 0.5h |
| **Total** | **~8h** |
