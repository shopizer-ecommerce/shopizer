# Shopizer Backend: Java 11 → Java 21 LTS + Spring Boot 3.x Upgrade Plan

## Current State Summary

| Component | Current Version |
|---|---|
| Java | 11 |
| Spring Boot | 2.5.12 |
| Maven Wrapper | 3.5.2 |
| Swagger | Springfox 2.9.2 |
| Security | `WebSecurityConfigurerAdapter` (deprecated/removed in Spring 6) |
| JPA/Hibernate | javax.persistence (1,052 imports) |
| Inject | javax.inject (182 imports) |
| Servlet | javax.servlet (110 imports) |
| Validation | javax.validation (123 imports) |
| Cache | EhCache 2.x via hibernate-ehcache |
| Drools | 7.32.0.Final |
| Infinispan | 9.4.18.Final |
| JWT | jjwt 0.8.0 |
| Docker base | `adoptopenjdk/openjdk11-openj9:alpine` |
| CI | CircleCI with `shopizerecomm/ci:java11` image |
| Test files | 37 test classes (sm-core only) |

## Target State

| Component | Target Version | Rationale |
|---|---|---|
| Java | 21 (LTS) | Latest LTS, supported until Sep 2031 |
| Spring Boot | 3.2.x (e.g., 3.2.5) | Latest stable 3.x line with Java 21 support, mature Jakarta EE 10 |
| Spring Framework | 6.1.x (managed by Boot 3.2) | |
| Hibernate | 6.4.x (managed by Boot 3.2) | |

Spring Boot 3.2 is chosen over 3.3+ because 3.2 is the most battle-tested Boot 3 release and has the widest ecosystem compatibility for the libraries this project uses.

---

## Phase 1: Pre-Upgrade Preparation (Estimated: 3–4 days)

### 1.1 Create a dedicated upgrade branch
```
git checkout -b upgrade/java21-spring-boot3
```

### 1.2 Establish a baseline
- Run the full build and all 37 existing tests, record results: `./mvnw clean install`
- Document all current API endpoints by exporting the Swagger JSON from `/v2/api-docs`
- Take a snapshot of the H2 database schema (the `SALESMANAGER.h2.db` file)
- Record application startup time and memory footprint for later comparison

### 1.3 Upgrade Maven Wrapper
Current wrapper is Maven 3.5.2 — Spring Boot 3.2 requires Maven 3.6.3+.
```bash
mvn wrapper:wrapper -Dmaven=3.9.6
```

### 1.4 Upgrade maven-compiler-plugin source/target
In the root `pom.xml`, change:
```xml
<java.version>21</java.version>
```

---

## Phase 2: Jakarta EE Migration — The Big Bang Change (Estimated: 5–7 days)

This is the single largest piece of work. Spring Boot 3 requires Jakarta EE 9+ (namespace `jakarta.*` instead of `javax.*`).

### 2.1 Namespace migration across 351 files

| Old Namespace | New Namespace | File Count |
|---|---|---|
| `javax.persistence.*` | `jakarta.persistence.*` | ~80+ entity files |
| `javax.inject.Inject` | `jakarta.inject.Inject` (or switch to `@Autowired`) | 171 usages |
| `javax.validation.*` | `jakarta.validation.*` | 123 usages |
| `javax.servlet.*` | `jakarta.servlet.*` | 110 usages |
| `javax.annotation.*` | `jakarta.annotation.*` | 12 usages |
| `javax.mail.*` | `jakarta.mail.*` | 8 usages |
| `javax.crypto.*` | No change (part of JDK, not Jakarta) | 6 — skip |
| `javax.imageio.*` | No change (part of JDK) | 2 — skip |
| `javax.ws.*` | `jakarta.ws.*` | 3 usages |

Recommended approach — use OpenRewrite for automated migration:
```xml
<!-- Add to root pom.xml temporarily -->
<plugin>
  <groupId>org.openrewrite.maven</groupId>
  <artifactId>rewrite-maven-plugin</artifactId>
  <version>5.34.0</version>
  <configuration>
    <activeRecipes>
      <recipe>org.openrewrite.java.migrate.jakarta.JavaxMigrationToJakarta</recipe>
    </activeRecipes>
  </configuration>
  <dependencies>
    <dependency>
      <groupId>org.openrewrite.recipe</groupId>
      <artifactId>rewrite-migrate-java</artifactId>
      <version>2.18.1</version>
    </dependency>
  </dependencies>
</plugin>
```
Run: `./mvnw rewrite:run` — then review the diff and remove the plugin.

### 2.2 Update dependency coordinates in all pom.xml files

| Old Dependency | New Dependency |
|---|---|
| `javax.inject:javax.inject` | `jakarta.inject:jakarta.inject-api:2.0.1` |
| `javax.validation:validation-api` | `jakarta.validation:jakarta.validation-api:3.0.2` (managed by Boot) |
| `javax.annotation:javax.annotation-api` | `jakarta.annotation:jakarta.annotation-api:2.1.1` (managed by Boot) |
| `javax.mail:mail` / `javax.mail:javax.mail-api` | `jakarta.mail:jakarta.mail-api` (managed by Boot) |
| `javax.servlet:javax.servlet-api` | Remove — managed by Boot 3 |
| `mysql:mysql-connector-java` | `com.mysql:mysql-connector-j` (new coordinates in Boot 3) |

---

## Phase 3: Spring Boot 3.2 Parent Upgrade (Estimated: 2–3 days)

### 3.1 Update parent POM
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.5</version>
</parent>
```

### 3.2 Property changes in `application.properties`
```properties
# Removed in Boot 3 — now the default behavior
# REMOVE: spring.jpa.hibernate.use-new-id-generator-mappings=true
```

### 3.3 Remove `junit-vintage-engine`
Spring Boot 3 uses JUnit 5 natively. Remove from root `pom.xml`:
```xml
<!-- REMOVE -->
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
</dependency>
```
Migrate any JUnit 4 tests (`@Test` from `org.junit`) to JUnit 5 (`org.junit.jupiter.api`).

---

## Phase 4: Spring Security Rewrite (Estimated: 3–4 days)

`WebSecurityConfigurerAdapter` is removed in Spring Security 6. The `MultipleEntryPointsSecurityConfig.java` has 4 inner classes extending it — all must be rewritten.

### 4.1 Replace each `WebSecurityConfigurerAdapter` with a `SecurityFilterChain` bean

Pattern for each adapter:

```java
// BEFORE (removed API)
@Configuration
@Order(1)
public static class CustomerConfigurationAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception { ... }
}

// AFTER
@Configuration
@Order(1)
public static class CustomerSecurityConfig {
    @Bean
    public SecurityFilterChain customerFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/shop/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/shop/customer/logon*").permitAll()
                // ... rest of rules
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> basic.authenticationEntryPoint(shopAuthenticationEntryPoint()));
        return http.build();
    }
}
```

### 4.2 Key API changes to apply
- `.antMatcher()` → `.securityMatcher()`
- `.antMatchers()` → `.requestMatchers()`
- `.authorizeRequests()` → `.authorizeHttpRequests()`
- `authenticationManagerBean()` → inject `AuthenticationConfiguration` and call `getAuthenticationManager()`
- `WebSecurity.ignoring()` → use `WebSecurityCustomizer` bean or permit in filter chain

---

## Phase 5: Swagger → SpringDoc Migration (Estimated: 1–2 days)

Springfox is dead and incompatible with Spring Boot 3. Replace with SpringDoc OpenAPI.

### 5.1 Remove from all pom.xml files
```xml
<!-- REMOVE -->
<dependency>springfox-swagger2</dependency>
<dependency>springfox-swagger-ui</dependency>
```

### 5.2 Add SpringDoc
In `sm-shop/pom.xml`:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

### 5.3 Replace `DocumentationConfiguration.java`
Delete the existing Springfox config. SpringDoc auto-configures. Add to `application.properties`:
```properties
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### 5.4 Update annotations in API controllers
- `@ApiOperation` → `@Operation`
- `@ApiParam` → `@Parameter`
- `@Api` → `@Tag`
- `@ApiModel` / `@ApiModelProperty` → `@Schema`

---

## Phase 6: Third-Party Dependency Upgrades (Estimated: 3–4 days)

| Library | Current | Target | Notes |
|---|---|---|---|
| Drools | 7.32.0 | 8.44.x or 9.x | Major API changes; verify `kie-spring` compatibility with Spring 6 |
| Infinispan | 9.4.18 | 15.x | Major jump; evaluate if still needed or switch to Spring Cache + Caffeine |
| EhCache | 2.x (via hibernate-ehcache) | EhCache 3.x via `hibernate-jcache` | `hibernate-ehcache` removed in Hibernate 6 |
| jjwt | 0.8.0 | 0.12.5 | API changed: split into `jjwt-api`, `jjwt-impl`, `jjwt-jackson` |
| MapStruct | 1.3.0 | 1.5.5.Final | Jakarta support added in 1.5+ |
| Guava | 27.1-jre | 33.x | Straightforward |
| AWS SDK | 1.11.640 | 1.12.x or AWS SDK v2 | v1 still works on Java 21 |
| Jackson | 2.13.4 | Remove explicit versions — let Boot 3.2 manage |
| Elasticsearch | 7.5.2 | Verify `shopizer-search-opensearch-spring-boot-starter` compatibility |
| commons-fileupload | 1.3.3 | Not needed — Spring Boot 3 uses Servlet 6 multipart natively |

### 6.1 EhCache migration (critical)
Replace in `sm-core/pom.xml`:
```xml
<!-- REMOVE -->
<dependency>hibernate-ehcache</dependency>

<!-- ADD -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-jcache</artifactId>
</dependency>
<dependency>
    <groupId>org.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <classifier>jakarta</classifier>
</dependency>
```
Update `ehcache.xml` configuration to EhCache 3 format.

### 6.2 Shopizer custom starters
These need verification/upgrade for Spring Boot 3 compatibility:
- `shopizer-search-opensearch-spring-boot-starter:1.0.3`
- `shipping-canadapost-spring-boot-starter:2.17.0`
- `shopizer-payment-square-spring-boot-starter:1.0.0`
- `shopizer-commons:1.0.6`

If these are not published for Boot 3, they'll need to be forked/upgraded separately.

---

## Phase 7: Docker & CI Updates (Estimated: 1 day)

### 7.1 Dockerfile
```dockerfile
FROM eclipse-temurin:21-jre-alpine
RUN mkdir /opt/app /files
COPY target/shopizer.jar /opt/app
COPY SALESMANAGER.h2.db /
COPY ./files /files
CMD ["java", "-jar", "/opt/app/shopizer.jar"]
```

### 7.2 CircleCI config
- Change Docker image from `shopizerecomm/ci:java11` to a Java 21 image
- Update the deploy job Docker tag

---

## Phase 8: Compile, Fix, Iterate (Estimated: 3–5 days)

After all the above changes:

1. `./mvnw clean compile` — fix compilation errors module by module in dependency order:
   - `sm-core-model` → `sm-core-modules` → `sm-shop-model` → `sm-core` → `sm-shop`
2. Common issues to expect:
   - Hibernate 6 changed `@Type` annotations (e.g., `@Type(type = "yes_no")` → `@Convert`)
   - `@GeneratedValue` strategy defaults changed
   - `Query` return types are stricter in Hibernate 6
   - Spring's `DefaultListableBeanFactory` is stricter about circular dependencies
3. `./mvnw clean install -DskipTests` — get a clean build first
4. Then run tests: `./mvnw test`

---

## Phase 9: Testing Plan (Post-Upgrade)

Testing cannot rely on the 37 automated tests alone. Here's a layered approach:

### 9.1 Automated Tests (Baseline)
- Run all 37 existing tests, ensure they pass after JUnit 5 migration
- Fix any test failures caused by Hibernate 6 schema generation differences or Spring context changes

### 9.2 Manual API Smoke Testing
Using the Swagger JSON exported in Phase 1 as a reference, manually test every API category through Postman or curl:

| Area | Key Endpoints to Test | What to Verify |
|---|---|---|
| Auth | `POST /api/v1/private/login`, `POST /api/v1/auth/login`, token refresh | JWT generation, token validation, role-based access |
| Catalog | CRUD on products, categories, manufacturers | Entity persistence, JPA relationships, pagination |
| Cart | Add to cart, update quantity, remove item | Session handling, price calculations |
| Checkout | Place order with payment | Full transaction flow, order status transitions |
| Customer | Registration, login, profile update | Validation constraints (`@NotEmpty` etc.), password encoding |
| Merchant/Store | Store CRUD, configuration | Multi-tenant data isolation |
| Content/CMS | Upload images, manage content pages | Multipart file upload (changed in Servlet 6) |
| Search | Product search | OpenSearch integration still works |
| Shipping | Get shipping quotes | Drools rules engine + Canada Post integration |

### 9.3 Security-Focused Manual Testing
Since the entire security config was rewritten:
- Verify unauthenticated access to public endpoints returns data (not 401/403)
- Verify protected endpoints (`/api/v*/private/**`) reject requests without valid JWT
- Verify expired tokens are rejected
- Verify role-based access: admin vs customer endpoints
- Verify CORS behavior if applicable
- Test with the Shopizer admin frontend Docker image to confirm end-to-end auth flow

### 9.4 Database & Schema Validation
- Start the app with a fresh H2 database, let Hibernate auto-generate the schema
- Compare the generated schema against the Phase 1 snapshot — look for:
  - Column type changes (Hibernate 6 maps some types differently)
  - Index name changes
  - Foreign key constraint differences
- If using MySQL in production, repeat this against a MySQL instance

### 9.5 Performance & Startup Regression Testing
- Compare startup time against the Phase 1 baseline
- Run a basic load test (e.g., 50 concurrent users browsing catalog + adding to cart) using a tool like `hey` or `k6`
- Monitor memory usage — Java 21 + Hibernate 6 may have different memory characteristics

### 9.6 Integration Testing with Frontend
- Run the Shopizer React shop (`shopizerecomm/shopizer-shop-reactjs`) against the upgraded backend
- Walk through the full customer journey: browse → search → add to cart → checkout
- Run the Shopizer admin (`shopizerecomm/shopizer-admin`) and verify:
  - Product management
  - Order management
  - Store configuration

### 9.7 Backward Compatibility Check
- Verify the API response structure hasn't changed (compare JSON responses against Phase 1 exports)
- Check that the new Swagger UI at `/swagger-ui.html` documents all the same endpoints

---

## Risk Register

| Risk | Impact | Mitigation |
|---|---|---|
| Shopizer custom starters not Boot 3 compatible | Blocks upgrade | Check source repos early; fork if needed |
| Drools 7 → 8 breaking changes | Shipping/pricing rules break | Test all rule-based calculations thoroughly |
| Infinispan 9 → 15 incompatibility | Cache layer breaks | Consider replacing with Spring Cache + Caffeine as simpler alternative |
| Hibernate 6 schema drift | Data loss or corruption | Schema comparison in Phase 9.4 before any production deployment |
| Third-party payment SDKs (Stripe, Braintree, PayPal) | Payment processing breaks | These SDKs are Java-version-agnostic; low risk but still test |

---

## Estimated Total Timeline

| Phase | Duration |
|---|---|
| Phase 1: Preparation | 3–4 days |
| Phase 2: Jakarta migration | 5–7 days |
| Phase 3: Boot 3.2 parent | 2–3 days |
| Phase 4: Security rewrite | 3–4 days |
| Phase 5: Swagger → SpringDoc | 1–2 days |
| Phase 6: Third-party deps | 3–4 days |
| Phase 7: Docker & CI | 1 day |
| Phase 8: Compile & fix | 3–5 days |
| Phase 9: Testing | 5–7 days |
| **Total** | **~26–37 working days** |

Phases 2 and 3 can partially overlap, and Phase 8 is iterative throughout. The critical path is: Jakarta namespace migration → Spring Boot parent upgrade → Security rewrite → compile fixes → testing.
