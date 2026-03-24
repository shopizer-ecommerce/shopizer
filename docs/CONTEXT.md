# Shopizer Backend — Business & Technical Context

> For full suite architecture, see [`../../docs/DOCUMENTATION.md`](../../docs/DOCUMENTATION.md) and [`../../docs/ARCHITECTURE_DIAGRAM.md`](../../docs/ARCHITECTURE_DIAGRAM.md)

---

## What This Repo Does

This is the **core backend API** for the Shopizer e-commerce platform. It exposes a REST API consumed by both the admin dashboard and the customer storefront. It handles everything: product catalog, orders, customers, payments, shipping, tax, CMS content, and multi-store support.

---

## Business Domain

| Domain | What it manages |
|--------|----------------|
| Store | Merchant store configuration, branding, locale, currency |
| Catalog | Products, categories, brands (manufacturers), product options/variants |
| Orders | Order lifecycle: cart → checkout → payment → fulfillment |
| Customers | Registration, profiles, addresses, reviews, loyalty |
| Payments | Stripe, PayPal, Braintree, Beanstream, Money Order |
| Shipping | Canada Post, UPS, Shiprocket, weight-based rules, store pickup |
| Tax | Tax classes and rates per zone |
| Content | CMS pages, boxes, banners, file uploads |
| Users | Admin/merchant user management with role-based access |
| Marketplace | Multi-store / marketplace mode (multiple merchants) |

---

## Technical Stack

| Concern | Technology |
|---------|-----------|
| Language | Java 11 |
| Framework | Spring Boot 2.5.12 |
| ORM | Hibernate (via Spring Data JPA) |
| Database | H2 (dev), MySQL, PostgreSQL (prod) |
| Auth | JWT (jjwt 0.8.0) — separate tokens for admin vs customer |
| Cache | Infinispan 9.4.18 + EhCache |
| Rules Engine | Drools 7.32.0 (pricing/shipping rules) |
| Search | Elasticsearch 7.5.2 (optional) |
| File Storage | Local filesystem / AWS S3 / Google Cloud Storage |
| Email | SMTP / AWS SES |
| API Docs | Swagger 2 at `/swagger-ui.html` |
| Build | Maven (use `./mvnw` — no global `mvn` installed) |

---

## Module Structure

```
shopizer/
├── sm-core-model/      # JPA entities — Product, Order, Customer, Store, etc.
├── sm-core-modules/    # Business modules — payment, shipping, search, email
├── sm-core/            # Core services and repositories
├── sm-shop-model/      # REST DTOs (request/response objects)
└── sm-shop/            # Spring Boot app — controllers, security, config
```

Build order is automatic: `sm-core-model → sm-core-modules → sm-core → sm-shop-model → sm-shop`

---

## Key API Endpoints

| Area | Path |
|------|------|
| Admin login | `POST /api/v1/private/login` |
| Customer login | `POST /api/v1/auth/login` |
| Products | `/api/v1/product` |
| Categories | `/api/v1/category` |
| Orders | `/api/v1/order` |
| Cart | `/api/v1/cart` |
| Customers | `/api/v1/customer` |
| Store config | `/api/v1/store` |
| Shipping | `/api/v1/shipping` |
| Tax | `/api/v1/tax` |
| Content/CMS | `/api/v1/content` |
| Swagger UI | `/swagger-ui.html` |

---

## Spring Profiles

| Profile | Database | Use when |
|---------|----------|---------|
| `default` | H2 (needs `database.properties` at classpath root) | Docker run without profile |
| `local` | MySQL (local) | Local dev with MySQL |
| `docker` | H2 file-based | `spring-boot:run` with H2 |
| `mysql` | MySQL | Production MySQL |
| `gcp` | Cloud SQL | GCP deployment |
| `cloud` | Generic cloud DB | Cloud deployment |

> **Important:** The `docker` profile only exists in `sm-shop`. There is no `docker` profile in `sm-core`. Running the JAR with `--spring.profiles.active=docker` will fail with a `mailSender.protocol` error. Run without a profile flag to use `default`.

---

## Important Files

| File | Purpose |
|------|---------|
| `sm-shop/src/main/resources/database.properties` | Root-level H2 config (required for `default` profile — manually added, do not delete) |
| `sm-shop/src/main/resources/profiles/local/database.properties` | MySQL credentials for local dev |
| `sm-core/src/main/resources/email.properties` | SMTP mail sender config |
| `sm-core/src/main/resources/shopizer-core.properties` | Core app settings |
| `sm-shop/src/main/resources/application.properties` | Port, logging, multipart config |
| `sm-shop/Dockerfile` | Docker image definition |

---

## Security Model

- Two separate JWT auth flows: one for admin users, one for customers
- Roles: `SUPERADMIN`, `ADMIN`, `STORE_ADMIN`, `RETAIL_ADMIN`, `CATALOGUE_MANAGER`, etc.
- JWT filter intercepts all `/api/v1/private/**` and `/api/v1/auth/**` routes
- CORS handled by `CorsFilter.java` — allows requests from the two frontends

---

## Known Gotchas

1. **No global `mvn`** — always use `./mvnw`
2. **`database.properties` at classpath root** — manually copied from `profiles/docker/`, required for the `default` Spring profile. See `RUNBOOK.md`.
3. **`docker` profile doesn't exist in `sm-core`** — don't pass `--spring.profiles.active=docker` to the JAR/container
4. **Build from root only** — always run `./mvnw` from `shopizer/`, never from a submodule
5. **Drools rules** — pricing and shipping rules are in `.drl` files under `sm-core-modules`; changes require a rebuild
