# Shopizer E-Commerce Platform — Documentation

## Overview

Shopizer is an open-source, headless Java e-commerce platform (v3.2.5/3.2.7) built on Spring Boot. It exposes a RESTful API for catalog, cart, checkout, order, customer, user, and merchant management. The project is licensed under Apache 2.0.

---

## Technical Perspective

### Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11+ (tested with 11, 17) |
| Framework | Spring Boot 2.5.12 |
| Persistence | Spring Data JPA, Hibernate (with Ehcache second-level cache) |
| Security | Spring Security, JWT (jjwt 0.8.0) |
| API Docs | Swagger 2 (Springfox 2.9.2) |
| Search | OpenSearch (shopizer-search-opensearch-spring-boot-starter) |
| Rules Engine | Drools 7.32.0 (decision tables for shipping rules) |
| Caching | Infinispan 9.4.18, Ehcache |
| Templating | FreeMarker (email templates) |
| Object Mapping | MapStruct 1.3.0 |
| Build | Maven (with Maven Wrapper `mvnw`) |
| CI/CD | CircleCI |
| Containerization | Docker (AdoptOpenJDK 11 OpenJ9 Alpine) |
| XSS Protection | OWASP AntiSamy 1.6.7 |
| Password Policy | Passay 1.6.0 |

### Database Support

| Database | Profile | Notes |
|---|---|---|
| H2 (default) | embedded | Ships with `SALESMANAGER.h2.db`; zero-config for development |
| MySQL 8 | `mysql`, `local` | `com.mysql.cj.jdbc.Driver`, `MySQL5InnoDBDialect` |
| PostgreSQL | commented in pom | Driver version 42.2.18 available |
| Oracle | commented in pom | ojdbc8 18.3.0.0 available |

Schema name: `SALESMANAGER`. DDL strategy: `hibernate.hbm2ddl.auto=update`.

Database profiles are located at `sm-shop/src/main/resources/profiles/{local,mysql,docker,cloud,gcp,dependency}/database.properties`.

### Cloud & Storage Integrations

- **Content Management (CMS):** Pluggable via `config.cms.method` — supports `default` (Infinispan/local filesystem), `aws` (S3), `gcp` (Google Cloud Storage), `httpd`
- **AWS S3:** `aws-java-sdk-s3` 1.11.640 for asset/image storage
- **AWS SES:** `aws-java-sdk-ses` 1.11.640 for transactional email
- **Google Cloud Storage:** `google-cloud-storage` 1.74.0
- **GeoIP:** MaxMind GeoIP2 2.7.0 for geolocation
- **Google Maps:** `google-maps-services` 0.1.6 for address validation and distance-based shipping

### Payment Gateway Integrations

| Gateway | Library |
|---|---|
| Stripe | stripe-java 19.5.0 (includes Stripe3Payment) |
| PayPal | merchantsdk 2.6.109 (Express Checkout + REST) |
| Braintree | braintree-java 2.73.0 |
| BeanStream | Custom implementation |
| Money Order | Built-in (offline payment) |

### Shipping Integrations

| Provider | Implementation |
|---|---|
| Canada Post | `shipping-canadapost-spring-boot-starter` 2.17.0 |
| UPS | `UPSShippingQuote` |
| USPS | `USPSShippingQuote` |
| Store Pickup | `StorePickupShippingQuote` |
| Custom Weight-Based | `CustomWeightBasedShippingQuote` |
| Price by Distance | `PriceByDistanceShippingQuoteRules` (Drools-driven, uses Google Maps API) |

### Architecture

The project follows a layered, multi-module Maven architecture:

```
shopizer (parent pom)
├── sm-core-model      — JPA entities and domain model
├── sm-core-modules    — SPI interfaces for payment, shipping, and order modules
├── sm-core            — Business services, repositories, and module implementations
├── sm-shop-model      — API DTOs, facade interfaces, and validation
└── sm-shop            — Spring Boot application, REST controllers, security, and configuration
```

**Dependency flow:** `sm-core-model` ← `sm-core-modules` ← `sm-core` ← `sm-shop-model` ← `sm-shop`

**Design patterns used:**
- **Facade pattern:** `sm-shop-model` defines facade interfaces (e.g., `ProductFacade`, `OrderFacade`, `CustomerFacade`); `sm-shop` provides implementations that orchestrate core services
- **Repository pattern:** Spring Data JPA repositories in `sm-core`
- **Populator pattern:** Dedicated populator classes in `sm-shop` for entity-to-DTO conversion
- **SPI/Module pattern:** `sm-core-modules` defines `PaymentModule`, `ShippingQuoteModule` interfaces; `sm-core` provides concrete implementations

### Module Details

#### sm-core-model (186 Java files)
JPA entity classes mapped to the `SALESMANAGER` schema. Key domain entities:
- **Catalog:** `Product`, `Category`, `CategoryDescription`, `Catalog`, `CatalogCategoryEntry`, `Manufacturer`, `ProductAttribute`, `ProductOption`, `ProductOptionValue`, `ProductImage`, `ProductPrice`, `ProductReview`, `ProductVariation`, `ProductVariant`, `ProductAvailability`, `DigitalProduct`, `ProductRelationship`, `ProductType`
- **Order:** `Order`, `OrderProduct`, `OrderProductPrice`, `OrderProductAttribute`, `OrderProductDownload`, `OrderTotal`, `OrderStatusHistory`, `OrderAttribute`, `OrderAccount`
- **Customer:** `Customer`, `CustomerAttribute`, `CustomerOption`, `CustomerOptionValue`, `CustomerReview`, `CustomerOptin`, `UserConnection`
- **Payment:** `Transaction`
- **Shopping Cart:** `ShoppingCart`, `ShoppingCartItem`, `ShoppingCartAttributeItem`
- **Tax:** `TaxClass`, `TaxRate`
- **Content:** CMS content entities
- **User/Security:** `User`, `Group`, `Permission` (in `com.salesmanager.core.model.user`)
- **Merchant:** `MerchantStore`
- **Reference Data:** `Country`, `Zone`, `Language`, `Currency`, `GeoZone`
- **System:** `MerchantConfiguration`, `SystemConfiguration`, `IntegrationModule`, `Optin`

#### sm-core-modules (14 Java files)
Service Provider Interfaces for extensible integrations:
- `PaymentModule` — interface for payment gateway implementations
- `ShippingQuoteModule` — interface for shipping rate providers
- `ShippingQuotePrePostProcessModule` — pre/post processing hooks for shipping
- `OrderTotalPostProcessorModule` — order total calculation hooks
- `Encryption`, `GeoLocation` — utility interfaces
- `CustomShippingQuotesConfiguration`, `Packaging` — shipping configuration models

#### sm-core (357 Java files)
Business logic layer containing:
- **Services:** `ProductService`, `CategoryService`, `OrderService`, `CustomerService`, `PaymentService`, `ShippingService`, `ShippingQuoteService`, `TaxService`, `ContentService`, `UserService`, `GroupService`, `PermissionService`, `SearchService`, `EmailService`, `MerchantStoreService`, `ShoppingCartService`, `ShoppingCartCalculationService`, `PricingService`, `CatalogService`, `ManufacturerService`, `ProductReviewService`, `ProductAvailabilityService`, `ProductInventoryService`, `ProductVariantService`, `ProductVariantGroupService`, `ProductVariationService`, `ProductImageService`, `ProductPriceService`, `ProductRelationshipService`, `ProductTypeService`, `DigitalProductService`, `ProductAttributeService`, `ProductOptionService`, `ProductOptionValueService`, `ProductOptionSetService`, `TransactionService`, `TaxClassService`, `TaxRateService`, `CountryService`, `ZoneService`, `LanguageService`, `CurrencyService`, `CustomerReviewService`, `CustomerAttributeService`, `CustomerOptionService`, `CustomerOptionValueService`, `CustomerOptionSetService`, `CustomerOptinService`, `OptinService`, `MerchantConfigurationService`, `SystemConfigurationService`, `OrderStatusHistoryService`, `OrderTotalService`, `OrderProductDownloadService`, `ShippingOriginService`, `MarketPlaceService`, `CatalogEntryService`
- **Repositories:** Spring Data JPA repositories for all entities (in `com.salesmanager.core.business.repositories`)
- **Module implementations:** Payment (Stripe, Stripe3, PayPal Express, PayPal REST, Braintree, BeanStream, MoneyOrder), Shipping (UPS, USPS, Canada Post, Store Pickup, Custom Weight-Based, Price by Distance)
- **CMS implementations:** Local filesystem, Infinispan, AWS S3, Google Cloud Storage
- **Email templates:** FreeMarker templates for checkout, order status, password reset, new user, contact, invoice, reviews, stock notifications, marketing
- **Drools rules:** Shipping decision rules (`ShippingDecision.drl`, `PriceByDistance.drl`), manufacturer-shipping order total rules
- **Search:** OpenSearch integration for product indexing and querying
- **Configuration:** Database connection pooling, event listeners (product events)

#### sm-shop-model (322 Java files)
API-layer models and facade interfaces:
- **DTOs/API models:** Request/response objects for all API resources (products, categories, orders, customers, carts, shipping, tax, content, users, stores, manufacturers, catalogs, marketplace)
- **Facade interfaces:** `ProductFacade`, `ProductDefinitionFacade`, `ProductVariantFacade`, `ProductVariantGroupFacade`, `ProductVariationFacade`, `ProductOptionFacade`, `ProductOptionSetFacade`, `ProductPriceFacade`, `ProductTypeFacade`, `ProductInventoryFacade`, `ProductCommonFacade`, `ProductItemsFacade`, `CategoryFacade`, `CatalogFacade`, `OrderFacade`, `CustomerFacade`, `ShoppingCartFacade`, `UserFacade`, `ManufacturerFacade`, `TaxFacade`, `ContentFacade`, `ShippingFacade`, `ShippingModuleConfigurationFacade`, `ConfigurationsFacade`
- **Validation:** Input validation classes
- **Versioned models:** Order models in `v0` and `v1` packages

#### sm-shop (325 Java files)
The deployable Spring Boot application:
- **Entry point:** `ShopApplication` (`@SpringBootApplication`)
- **REST API controllers** (all under `com.salesmanager.shop.store.api`):
  - `v1/product/` — `ProductApi`, `ProductImageApi`, `ProductPriceApi`, `ProductReviewApi`, `ProductInventoryApi`, `ProductRelationshipApi`, `ProductTypeApi`, `ProductAttributeOptionApi`, `ProductGroupApi`, `ProductManufacturerApi`, `ProductPropertySetApi`
  - `v1/category/` — `CategoryApi`
  - `v1/catalog/` — `CatalogApi`
  - `v1/order/` — `OrderApi`, `OrderTotalApi`, `OrderPaymentApi`, `OrderStatusHistoryApi`, `OrderShippingApi`
  - `v1/customer/` — `CustomerApi`, `AuthenticateCustomerApi`, `ResetCustomerPasswordApi`, `CustomerReviewApi`, `CustomerNewsletterApi`
  - `v1/shoppingCart/` — `ShoppingCartApi`
  - `v1/user/` — `UserApi`, `AuthenticateUserApi`, `ResetUserPasswordApi`
  - `v1/store/` — `MerchantStoreApi`
  - `v1/payment/` — `PaymentApi`
  - `v1/shipping/` — `ShippingConfigurationApi`, `ShippingExpeditionApi`
  - `v1/tax/` — `TaxRatesApi`, `TaxClassApi`
  - `v1/content/` — `ContentApi`, `ContentAdministrationApi`
  - `v1/search/` — `SearchApi`
  - `v1/marketplace/` — `MarketPlaceApi`
  - `v1/security/` — `SecurityApi`
  - `v1/references/` — `ReferencesApi`
  - `v1/configurations/` — `ConfigurationsApi`, `CacheApi`
  - `v1/system/` — `SearchToolsApi`, `ContactApi`, `PublicConfigsApi`, `OptinApi`, `ModulesApi`
  - `v2/product/` — `ProductVariationApi`, `ProductVariantApi`, `ProductVariantGroupApi`
  - `v0/` — Legacy endpoints (`SystemRESTController`, `StoreContactRESTController`)
- **Security:** `MultipleEntryPointsSecurityConfig` (JWT-based auth for API, separate admin/customer auth chains)
- **Facade implementations:** In `com.salesmanager.shop.store.facade` — implement all facade interfaces from `sm-shop-model`
- **Mappers:** MapStruct-based mappers for entity-to-DTO conversion
- **Populators:** Manual populator classes for complex transformations
- **Filters:** Request filters for merchant store resolution
- **Configuration:** `WebConfig`, `AsyncConfig`, `DocumentationConfiguration` (Swagger), `ShopApplicationConfiguration`, `LocationImageConfig`
- **Data initialization:** `InitializationLoader`, `InitData` for bootstrapping default data

### Build & Run

```bash
# Build all modules
cd shopizer
./mvnw clean install

# Run the application
cd sm-shop
./mvnw spring-boot:run
```

Application starts on port `8080`. Swagger UI: `http://localhost:8080/swagger-ui.html`

**Docker:**
```bash
docker run -p 8080:8080 shopizerecomm/shopizer:latest
```

**Dockerfile** uses `adoptopenjdk/openjdk11-openj9:alpine`, copies the built JAR and H2 database file.

### Configuration Profiles

| Profile | Purpose |
|---|---|
| `local` | MySQL local development |
| `mysql` | MySQL with configurable credentials |
| `docker` | Docker-based deployment |
| `cloud` | Cloud deployment |
| `gcp` | Google Cloud Platform |
| `aws` | AWS (S3/SES storage and email) |
| `dependency` | External dependency mode |

### Monitoring & Actuator

Spring Boot Actuator is enabled with all endpoints exposed (`management.endpoints.web.exposure.include=*`). Health checks show details for all components (Elasticsearch and mail health checks are disabled by default).

---

## Functional Perspective

### Product Management
- Full product lifecycle: create, update, delete, list with pagination
- Product definitions with multilingual descriptions
- Product types and categorization
- Product attributes with configurable options and option values (e.g., size, color)
- Product option sets for grouping attributes
- Product variations and variants (v2 API) for SKU-level management
- Product variant groups for organizing variants
- Product images: upload, manage, and serve product media
- Product pricing: multiple prices per product, price management API
- Product availability and inventory tracking
- Product relationships (related products, cross-sell, up-sell)
- Product reviews and ratings
- Digital/downloadable products
- Product groups for merchandising
- Manufacturer/brand management

### Catalog & Categories
- Hierarchical category tree with parent-child relationships
- Category descriptions with multilingual support
- Catalog management: group categories into named catalogs
- Catalog entries linking categories to catalogs
- Marketplace catalog support

### Shopping Cart
- Create and manage shopping carts
- Add/remove/update cart items
- Cart attribute items for product options
- Cart calculation service for totals, taxes, and shipping estimates

### Order Management
- Complete order lifecycle: creation, status tracking, history
- Order products with prices, attributes, and downloadable items
- Order totals calculation (subtotal, tax, shipping, discounts)
- Order status history tracking
- Order accounts for recurring/subscription orders
- Order attributes for custom metadata
- File history for order-related documents

### Checkout
- Shopping cart to order conversion
- Shipping quote calculation during checkout
- Tax calculation based on zones and tax classes
- Payment processing integration
- Order confirmation emails (FreeMarker templates)
- Downloadable product delivery post-checkout

### Payment Processing
- Multiple payment gateway support: Stripe, Stripe 3D Secure, PayPal Express Checkout, PayPal REST, Braintree, BeanStream, Money Order
- Transaction management: authorize, capture, refund
- Configurable payment modules per merchant store
- Credit card validation (configurable)
- Payment configuration API for admin

### Shipping
- Multiple shipping providers: Canada Post, UPS, USPS, Store Pickup, Custom Weight-Based
- Distance-based shipping pricing (Drools rules + Google Maps API)
- Shipping quote pre/post processing hooks
- Shipping origin configuration per store
- Shipping expedition management
- Configurable shipping zones and rules
- Packaging configuration

### Tax Management
- Tax classes for product categorization
- Tax rates configurable by zone/country
- Tax calculation integrated into cart and order totals

### Customer Management
- Customer registration and authentication (JWT-based)
- Customer profiles with addresses
- Customer attributes (custom fields with options)
- Customer reviews
- Customer opt-in / newsletter subscription
- Password reset flow with email notifications

### User & Access Management
- Admin user management with roles and permissions
- User authentication with JWT tokens
- User groups and permission-based access control
- Password reset with email link
- Separate authentication chains for admin users and customers

### Merchant / Store Management
- Multi-store support: each `MerchantStore` is an independent storefront
- Store configuration: currency, language, country, zone
- Merchant-level configuration for payment, shipping, and tax modules
- Store-level CMS and content management

### Content Management (CMS)
- Static content and page management
- Content assets (images, files) with pluggable storage backends
- Content boxes for reusable content blocks
- Content administration API

### Search
- Product search powered by OpenSearch
- Full-text search with configurable query templates (name, description, tags)
- Autocomplete/keyword search
- Category faceting in search results
- Search index management tools (reindex API)

### Email Notifications
- Transactional emails via SMTP or AWS SES
- FreeMarker templates for: order confirmation, order status updates, password reset, new user/customer welcome, contact form, invoice, review notifications, stock alerts (low stock, out of stock), marketing emails

### Internationalization
- Multi-language support for products, categories, manufacturers, and content
- Reference data: countries, zones, languages, currencies
- Localized resource bundles (English, French)

### Marketplace
- Marketplace catalog support for multi-vendor scenarios
- Catalog descriptions per marketplace

### System & Configuration
- System configuration management
- Integration module configuration (payment, shipping, search)
- Cache management API
- Public configuration endpoint for storefront apps
- Contact form API
- Opt-in management for newsletters/marketing
- GeoIP-based location detection (MaxMind)
- Google Maps integration for address validation
- reCAPTCHA support for form protection
