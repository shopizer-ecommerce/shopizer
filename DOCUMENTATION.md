# Shopizer — Complete Project Documentation

> Version: 3.2.5 | Java 11+ | Spring Boot 2.5.12 | License: Apache 2.0

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Architecture Diagram](#2-architecture-diagram)
3. [Module Structure](#3-module-structure)
4. [Technology Stack](#4-technology-stack)
5. [Getting Started](#5-getting-started)
6. [Configuration](#6-configuration)
7. [API Reference](#7-api-reference)
8. [Security Model](#8-security-model)
9. [Database](#9-database)
10. [Storage & File Management](#10-storage--file-management)
11. [Payment Integrations](#11-payment-integrations)
12. [Deployment](#12-deployment)
13. [CI/CD](#13-cicd)
14. [Project Directory Map](#14-project-directory-map)

---

## 1. Project Overview

Shopizer is a **Java-based open source headless e-commerce platform** that exposes a full REST API for building custom storefronts, admin panels, and mobile apps.

**Core capabilities:**

| Domain | Description |
|---|---|
| Catalog | Products, categories, manufacturers, attributes, variants |
| Shopping Cart | Session-based and persistent cart management |
| Checkout | Order creation, totals, shipping calculation |
| Orders | Order lifecycle, status history, payment, shipping |
| Customer | Registration, authentication, address book, reviews |
| Merchant Store | Multi-store support, store configuration |
| Users | Admin users, roles, permissions |
| Content | CMS pages, banners, files |
| Tax | Tax classes and rates per zone |
| Search | Elasticsearch-powered product search and autocomplete |

---

## 2. Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                        CLIENT LAYER                             │
│                                                                 │
│   ┌──────────────┐   ┌──────────────┐   ┌──────────────────┐   │
│   │  React Shop  │   │  Admin UI    │   │  Mobile / Custom │   │
│   │  (Docker)    │   │  (Docker)    │   │  Frontend        │   │
│   └──────┬───────┘   └──────┬───────┘   └────────┬─────────┘   │
└──────────┼─────────────────┼────────────────────┼─────────────┘
           │                 │                    │
           └─────────────────▼────────────────────┘
                             │  REST API (JSON)
                             │  http://localhost:8080/api/v1/...
                             │
┌────────────────────────────▼────────────────────────────────────┐
│                     sm-shop (Spring Boot App)                   │
│                                                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │  REST API    │  │  Security    │  │  Filters             │  │
│  │  Controllers │  │  (JWT/Spring)│  │  (CORS, XSS)         │  │
│  └──────┬───────┘  └──────┬───────┘  └──────────────────────┘  │
│         │                 │                                     │
│  ┌──────▼─────────────────▼──────────────────────────────────┐  │
│  │                   Facade Layer                            │  │
│  │  (product, order, customer, cart, store, catalog, ...)    │  │
│  └──────────────────────────┬────────────────────────────────┘  │
│                             │                                   │
│  ┌──────────────────────────▼────────────────────────────────┐  │
│  │                   sm-core (Services)                      │  │
│  │  Business logic, service interfaces & implementations     │  │
│  └──────────────────────────┬────────────────────────────────┘  │
│                             │                                   │
│  ┌──────────────────────────▼────────────────────────────────┐  │
│  │              sm-core-model (JPA Entities)                 │  │
│  │  Hibernate entities, repositories, domain model           │  │
│  └──────────────────────────┬────────────────────────────────┘  │
└────────────────────────────┼────────────────────────────────────┘
                             │
           ┌─────────────────┼──────────────────┐
           │                 │                  │
    ┌──────▼──────┐  ┌───────▼──────┐  ┌────────▼───────┐
    │  H2 (dev)   │  │  MySQL       │  │  PostgreSQL    │
    │  (embedded) │  │  (prod)      │  │  (optional)    │
    └─────────────┘  └──────────────┘  └────────────────┘
```

### Request Flow

```
HTTP Request
    │
    ▼
CorsFilter ──► XssFilter
    │
    ▼
AuthenticationTokenFilter (JWT validation)
    │
    ▼
REST Controller (api/v1/...)
    │
    ▼
Facade (business orchestration)
    │
    ▼
Core Service
    │
    ▼
JPA Repository ──► Database
```

---

## 3. Module Structure

```
shopizer/                        ← Root Maven parent (POM)
├── sm-core-model/               ← JPA entities, domain model
├── sm-core-modules/             ← Pluggable external modules
├── sm-core/                     ← Business services & logic
├── sm-shop-model/               ← REST API request/response models (DTOs)
└── sm-shop/                     ← Spring Boot application (entry point)
```

### Module Dependency Chain

```
sm-shop
  └── sm-core
        └── sm-core-model
  └── sm-shop-model
  └── sm-core-modules
```

| Module | Artifact ID | Purpose |
|---|---|---|
| `sm-core-model` | `sm-core-model` | Hibernate JPA entities, DB schema |
| `sm-core-modules` | `sm-core-modules` | Pluggable integrations (payment, shipping, search) |
| `sm-core` | `sm-core` | Service layer, business logic |
| `sm-shop-model` | `sm-shop-model` | DTO models for REST API |
| `sm-shop` | `sm-shop` | Spring Boot app, controllers, security, config |

---

## 4. Technology Stack

| Category | Technology | Version |
|---|---|---|
| Language | Java | 11 (supports 17+) |
| Framework | Spring Boot | 2.5.12 |
| Security | Spring Security + JWT | jjwt 0.8.0 |
| Persistence | Spring Data JPA + Hibernate | — |
| Database (default) | H2 (embedded) | — |
| Database (prod) | MySQL | 8.0.21 |
| Database (optional) | PostgreSQL | 42.2.18 |
| Caching | Infinispan + EhCache | 9.4.18.Final |
| Rules Engine | Drools | 7.32.0.Final |
| Search | Elasticsearch | 7.5.2 |
| API Docs | Springfox Swagger 2 | 2.9.2 |
| Object Mapping | MapStruct | 1.3.0.Final |
| Cloud Storage | AWS S3 / GCP Storage | — |
| Email | AWS SES / SMTP | — |
| Build | Maven Wrapper | — |
| Containerization | Docker | — |
| CI | CircleCI | — |
| XSS Protection | OWASP AntiSamy | 1.6.7 |
| Password Policy | Passay | 1.6.0 |
| Geo IP | MaxMind GeoIP2 | 2.7.0 |

---

## 5. Getting Started

### Prerequisites

- Java 11 or 17+
- Maven 3.6+ (or use included `mvnw`)
- MySQL (for production) or use default H2 embedded DB

### Build & Run (Local)

```bash
# Clone the repository
git clone https://github.com/shopizer-ecommerce/shopizer.git
cd shopizer

# Build all modules
./mvnw clean install

# Run the application
cd sm-shop
./mvnw spring-boot:run
```

Application starts at: `http://localhost:8080`
Swagger UI: `http://localhost:8080/swagger-ui.html`

### Run with Docker

```bash
# Backend API
docker run -p 8080:8080 shopizerecomm/shopizer:latest

# Admin UI (requires backend running)
docker run \
  -e "APP_BASE_URL=http://localhost:8080/api" \
  -p 82:80 shopizerecomm/shopizer-admin

# React Storefront (requires backend running)
docker run \
  -e "APP_MERCHANT=DEFAULT" \
  -e "APP_BASE_URL=http://localhost:8080" \
  -p 80:80 shopizerecomm/shopizer-shop-reactjs
```

---

## 6. Configuration

### Key Configuration Files

| File | Purpose |
|---|---|
| `sm-shop/src/main/resources/application.properties` | Server port, logging, multipart, actuator |
| `sm-shop/src/main/resources/database.properties` | DB connection (default MySQL) |
| `sm-shop/src/main/resources/shopizer-properties.properties` | App-level feature flags |
| `sm-shop/src/main/resources/vault.properties` | Secrets (API keys) |

### application.properties — Key Settings

```properties
server.port=8080
spring.jpa.properties.hibernate.default_schema=SALESMANAGER
spring.servlet.multipart.max-file-size=4MB
spring.servlet.multipart.max-request-size=10MB
management.endpoints.web.exposure.include=*
```

### database.properties — MySQL (Default)

```properties
db.jdbcUrl=jdbc:mysql://127.0.0.1:3306/SALESMANAGER?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
db.user=root
db.password=password
db.driverClass=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.hbm2ddl.auto=update
db.schema=SALESMANAGER
```

### Spring Profiles

| Profile | Location | Use Case |
|---|---|---|
| `local` | `profiles/local/` | Local development |
| `mysql` | `profiles/mysql/` | MySQL production |
| `docker` | `profiles/docker/` | Docker container |
| `gcp` | `profiles/gcp/` | Google Cloud Platform |
| `cloud` | `profiles/cloud/` | Generic cloud |
| `dependency` | `profiles/dependency/` | External dependency config |

Activate a profile:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

### Feature Flags (shopizer-properties.properties)

| Property | Default | Description |
|---|---|---|
| `INDEX_PRODUCTS` | `true` | Enable Elasticsearch product indexing |
| `MULTIPLE_PRICE_AVAILABILITY` | `false` | Multiple pricing tiers |
| `POPULATE_TEST_DATA` | `false` | Seed test data on startup |
| `CROP_UPLOADED_IMAGES` | `false` | Auto-crop product images |
| `VALIDATE_CREDIT_CARD` | `false` | Enable credit card validation |
| `ORDER_EMAIL_API` | `true` | Send order emails via API |
| `MAIL_SEND_ORDER_UPDATES` | `true` | Email customers on order updates |

---

## 7. API Reference

Base URL: `http://localhost:8080/api/v1`
Full interactive docs: `http://localhost:8080/swagger-ui.html`

### API Endpoint Groups

```
/api/v1/
├── /auth                    ← JWT authentication (login)
├── /customer/               ← Customer CRUD, auth, reviews, newsletter
├── /store/                  ← Merchant store management
├── /product/                ← Products, images, attributes, variants
├── /category/               ← Product categories
├── /catalog/                ← Catalog management
├── /cart/                   ← Shopping cart operations
├── /order/                  ← Orders, payments, shipping, totals
├── /user/                   ← Admin users, roles
├── /content/                ← CMS content, files
├── /tax/                    ← Tax classes and rates
├── /shipping/               ← Shipping configuration
├── /payment/                ← Payment modules
├── /search/                 ← Product search
├── /references/             ← Countries, zones, currencies
├── /system/                 ← Optin, contact, modules, configs
└── /marketplace/            ← Marketplace features
```

### Authentication Flow

```
POST /api/v1/auth/login
Body: { "username": "admin@shopizer.com", "password": "password" }

Response: { "token": "eyJhbGci..." }

# Use token in subsequent requests:
Authorization: Bearer eyJhbGci...
```

### Example: Get Products

```
GET /api/v1/products?store=DEFAULT&lang=en&page=0&count=10
Authorization: Bearer <token>
```

### Example: Create Cart

```
POST /api/v1/cart
Body: {
  "product": 1,
  "quantity": 2,
  "store": "DEFAULT"
}
```

---

## 8. Security Model

### Authentication Types

```
┌─────────────────────────────────────────────┐
│              Security Entry Points          │
│                                             │
│  /api/v1/auth/**   → Public (login)         │
│  /api/v1/**        → JWT Bearer Token       │
│  /admin/**         → Session-based (admin)  │
└─────────────────────────────────────────────┘
```

### JWT Token Flow

```
Client ──POST /auth/login──► AuthenticateUserApi
                                    │
                              Validate credentials
                                    │
                              JWTTokenUtil.generateToken()
                                    │
                              ◄── { token: "..." }

Client ──GET /api/v1/...──► AuthenticationTokenFilter
  Header: Bearer <token>          │
                             Validate JWT
                                    │
                             Set SecurityContext
                                    │
                             ◄── Response
```

### Roles

| Role | Access |
|---|---|
| `ROLE_ADMIN` | Full admin access |
| `ROLE_STORE_ADMIN` | Store-level admin |
| `ROLE_CUSTOMER` | Customer-facing APIs |
| `ROLE_ANONYMOUS` | Public catalog/search |

### Security Features

- JWT stateless authentication
- XSS filtering via OWASP AntiSamy (`XssFilter`)
- CORS filter (`CorsFilter`)
- Password policy enforcement via Passay
- Method-level security (`@PreAuthorize`)

---

## 9. Database

### Default Schema: `SALESMANAGER`

Shopizer uses Hibernate with `hbm2ddl.auto=update` — schema is auto-created/updated on startup.

### Supported Databases

| Database | Status | Driver |
|---|---|---|
| H2 (embedded) | Default (dev) | Built-in |
| MySQL 8 | Supported (prod) | `mysql-connector-java:8.0.21` |
| PostgreSQL | Supported | `postgresql:42.2.18` |
| Oracle | Commented out | `ojdbc8:18.3.0.0` |

### Connection Pool (HikariCP)

```properties
db.initialPoolSize=4
db.minPoolSize=4
db.maxPoolSize=4
```

### Key Domain Entities (sm-core-model)

```
MerchantStore          ← Store configuration
Product                ← Product catalog
ProductCategory        ← Category hierarchy
Customer               ← Customer accounts
Order                  ← Order records
ShoppingCart           ← Cart sessions
User                   ← Admin users
Language / Currency    ← Reference data
Country / Zone         ← Geographic reference
TaxClass / TaxRate     ← Tax configuration
```

---

## 10. Storage & File Management

### Local Storage (Default)

Files stored under `sm-shop/files/`:
```
files/
├── repos/    ← General file repository
└── store/    ← Store-specific files (images, downloads)
```

### Cloud Storage Options

| Provider | Module |
|---|---|
| AWS S3 | `aws-java-sdk-s3:1.11.640` |
| Google Cloud Storage | `google-cloud-storage:1.74.0` |

Configure via `profiles/cloud/` or `profiles/gcp/` database.properties.

### Image Configuration

```properties
PRODUCT_IMAGE_WIDTH_SIZE=1000
PRODUCT_IMAGE_HEIGHT_SIZE=1100
PRODUCT_IMAGE_MAX_HEIGHT_SIZE=2000
PRODUCT_IMAGE_MAX_WIDTH_SIZE=4000
PRODUCT_IMAGE_MAX_SIZE=9000000   # bytes (~9MB)
IMAGE_FORMATS=jpg|png|gif
```

---

## 11. Payment Integrations

| Provider | Library | Version |
|---|---|---|
| PayPal | `merchantsdk` | 2.6.109 |
| Stripe | `stripe-java` | 19.5.0 |
| Braintree | `braintree-java` | 2.73.0 |

Payment modules are configured via `bundles/payment.properties`.

PayPal endpoints:
```properties
PAYPAL_EXPRESSCHECKOUT_SANDBOX=https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=
PAYPAL_EXPRESSCHECKOUT_PRODUCTION=https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=
```

---

## 12. Deployment

### Docker Build

```dockerfile
FROM adoptopenjdk/openjdk11-openj9:alpine
RUN mkdir /opt/app && mkdir /files
COPY target/shopizer.jar /opt/app
COPY SALESMANAGER.h2.db /
COPY ./files /files
CMD ["java", "-jar", "/opt/app/shopizer.jar"]
```

Build and run:
```bash
cd sm-shop
./mvnw clean package
docker build -t shopizer .
docker run -p 8080:8080 shopizer
```

### Docker Compose (Full Stack)

```yaml
version: '3'
services:
  shopizer:
    image: shopizerecomm/shopizer:latest
    ports:
      - "8080:8080"

  shopizer-admin:
    image: shopizerecomm/shopizer-admin
    environment:
      - APP_BASE_URL=http://shopizer:8080/api
    ports:
      - "82:80"

  shopizer-shop:
    image: shopizerecomm/shopizer-shop-reactjs
    environment:
      - APP_MERCHANT=DEFAULT
      - APP_BASE_URL=http://shopizer:8080
    ports:
      - "80:80"
```

### Health Check

```
GET http://localhost:8080/actuator/health
```

All actuator endpoints are exposed:
```properties
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```

---

## 13. CI/CD

Shopizer uses **CircleCI** for continuous integration.

Config: `.circleci/config.yml`

Pipeline steps:
1. Checkout source
2. Maven build (`mvnw clean install`)
3. Run tests
4. Code coverage check (JaCoCo)
   - Lines: 30% minimum
   - Branches: 37% minimum

---

## 14. Project Directory Map

```
shopizer/
├── pom.xml                          ← Root parent POM (v3.2.5)
├── mvnw / mvnw.cmd                  ← Maven wrapper
├── README.md
├── LICENSE.md                       ← Apache 2.0
├── RELEASE-NOTES.md
├── .circleci/config.yml             ← CI pipeline
│
├── sm-core-model/                   ← JPA entities
│   └── src/main/java/
│
├── sm-core-modules/                 ← External pluggable modules
│   └── src/main/java/
│
├── sm-core/                         ← Business services
│   └── src/main/java/
│   └── src/test/
│
├── sm-shop-model/                   ← REST DTOs
│   └── src/main/java/
│
└── sm-shop/                         ← Spring Boot application
    ├── pom.xml
    ├── Dockerfile
    ├── SALESMANAGER.mv.db           ← H2 dev database
    ├── files/
    │   ├── repos/
    │   └── store/
    └── src/main/
        ├── java/com/salesmanager/shop/
        │   ├── application/         ← Spring Boot entry point + config
        │   │   ├── ShopApplication.java
        │   │   └── config/          ← Security, Swagger, async, web config
        │   ├── store/
        │   │   ├── api/v1/          ← REST controllers (all endpoints)
        │   │   ├── facade/          ← Business orchestration layer
        │   │   ├── controller/      ← MVC controllers
        │   │   ├── security/        ← JWT, auth filters
        │   │   └── model/           ← Internal models
        │   ├── mapper/              ← MapStruct mappers
        │   ├── populator/           ← Entity ↔ DTO converters
        │   ├── filter/              ← CORS, XSS filters
        │   ├── utils/               ← Utility classes
        │   ├── constants/           ← App constants
        │   └── init/                ← Data initialization
        └── resources/
            ├── application.properties
            ├── database.properties
            ├── shopizer-properties.properties
            ├── vault.properties
            ├── bundles/             ← i18n (EN/FR), payment, shipping
            ├── profiles/            ← Per-environment DB configs
            └── spring/              ← Spring XML configs
```

---

## Links

- Website: http://www.shopizer.com
- GitHub: https://github.com/shopizer-ecommerce/shopizer
- API Docs (local): http://localhost:8080/swagger-ui.html
- Documentation: https://shopizer-ecommerce.github.io/documentation/
- Docker Hub: https://hub.docker.com/r/shopizerecomm/shopizer
- Slack: https://shopizer.slack.com
- Stack Overflow: https://stackoverflow.com/questions/tagged/shopizer
