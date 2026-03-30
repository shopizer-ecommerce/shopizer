# Java 11 → Java 21 LTS Upgrade Plan

## Current State
- Java 11, Spring Boot 2.7.x, Spring 5.x
- Hibernate 5.x, Jakarta EE (javax.* namespace)

## Target State
- Java 21 LTS, Spring Boot 3.2.x, Spring 6.x
- Hibernate 6.x, Jakarta EE 10 (jakarta.* namespace)

---

## Phase 1 — Preparation

**1.1 Dependency audit**
- Run `mvnw dependency:tree` and identify all deps incompatible with Spring Boot 3.x
- Key known breaking changes:
  - `javax.*` → `jakarta.*` (all imports across the codebase)
  - Hibernate 5 → 6 (query syntax changes, `@Type` annotations)
  - SpringFox Swagger 2 → SpringDoc OpenAPI 3 (SpringFox is dead on Spring 6)
  - `spring-security` config style changed (no more `WebSecurityConfigurerAdapter`)
  - `spring-boot-starter-tomcat` scope changes

**1.2 Tooling**
- Set up a `java21-upgrade` branch
- Configure CI to build against Java 21 (update `ci.yml` to `java-version: '21'`)
- Use OpenRewrite migration recipe to automate `javax` → `jakarta` rename:
  ```bash
  ./mvnw rewrite:run -Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_2
  ```

---

## Phase 2 — Code Migration

**2.1 pom.xml changes**
- Spring Boot parent: `2.7.x` → `3.2.x`
- Java version: `11` → `21`
- Replace SpringFox with SpringDoc:
  ```xml
  <!-- Remove -->
  <dependency>springfox-swagger2</dependency>
  <dependency>springfox-swagger-ui</dependency>

  <!-- Add -->
  <dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
  </dependency>
  ```

**2.2 javax → jakarta**
- All `import javax.servlet.*` → `jakarta.servlet.*`
- All `import javax.persistence.*` → `jakarta.persistence.*`
- All `import javax.validation.*` → `jakarta.validation.*`
- All `import javax.inject.*` → `jakarta.inject.*`
- Affects: controllers, filters, populators, facades, models

**2.3 Spring Security**
- Replace `WebSecurityConfigurerAdapter` (removed in Spring 6) with `SecurityFilterChain` bean pattern
- Update `HttpSecurity` lambda DSL (`.and()` chaining removed)

**2.4 Hibernate 6**
- `@Type(type = "...")` → `@JdbcTypeCode` / `@Type(value = ...)`
- Native query result mapping changes
- `CriteriaQuery` API updates

**2.5 Other**
- `spring.jpa.hibernate.use-new-id-generator-mappings` property removed
- `management.endpoints` config key changes
- JWT library (`jjwt`) — upgrade to 0.12.x (API changed significantly)

---

## Phase 3 — Testing Plan

### Automated Tests (baseline)
- Run existing unit tests: `DateUtilTest`, `FileNameUtilsTest`, etc.
- Run integration tests against H2 (default profile)
- Fix any compilation/runtime failures before manual testing

### Manual Testing (required — cannot rely on automation alone)

**3.1 Authentication & Security**
- [ ] Login with valid credentials → JWT token returned
- [ ] Login with invalid credentials → 401 returned
- [ ] Access protected endpoint without token → 401
- [ ] Access protected endpoint with expired token → 401
- [ ] Admin-only endpoint with non-admin token → 403

**3.2 Swagger / API Docs**
- [ ] `http://localhost:8080/swagger-ui.html` loads (now served by SpringDoc)
- [ ] All API groups visible (catalog, order, customer, etc.)
- [ ] Try-it-out works for at least one GET and one POST

**3.3 Core Business Flows**
- [ ] Create a product via API → verify in DB
- [ ] Add product to cart → verify cart response
- [ ] Place an order → verify order created with correct status
- [ ] Retrieve order stats → verify counts and revenue match DB

**3.4 Database**
- [ ] App starts and Hibernate auto-creates/updates schema without errors
- [ ] No `HibernateException` in logs on startup
- [ ] Run a query-heavy operation (product list with filters) and verify results

**3.5 Admin Dashboard**
- [ ] Login to admin at `http://localhost:30300`
- [ ] Order Stats dashboard loads with data
- [ ] Navigate to Products, Orders, Customers — data visible

**3.6 Regression — Known Risk Areas**
- [ ] `XssFilter` / `SanitizeUtils` — verify requests with special chars still sanitized
- [ ] File upload (product images) — multipart still works
- [ ] Email templates (Freemarker) — verify no template rendering errors in logs
- [ ] `SearchServiceImpl.loadClassPathResource` — verify classpath fix still works in fat jar

**3.7 Performance sanity**
- [ ] App startup time (should be comparable to Java 11 baseline)
- [ ] Hit `/api/v1/private/orders` with 100 records — response under 2s

---

## Phase 4 — Rollout

1. Merge `java21-upgrade` branch → `rishabh-workshop`
2. CI builds and pushes new Docker image
3. Deploy to k8s: `kubectl rollout restart deployment/shopizer -n shopizer`
4. Monitor logs for 10 mins: `kubectl logs -f deployment/shopizer -n shopizer`
5. Run manual test checklist (Phase 3) against the deployed instance
6. If stable for 24h → merge to `main`

---

## Risk Areas (highest to lowest)

| Risk | Likelihood | Mitigation |
|------|-----------|------------|
| SpringFox → SpringDoc migration breaks Swagger | High | Test Swagger UI first after migration |
| `javax` → `jakarta` missed imports | High | OpenRewrite automates most; grep for remaining `javax` |
| JWT library API change | Medium | Pin `jjwt` to 0.12.x, update token generation/parsing code |
| Hibernate 6 query changes | Medium | Run full integration test suite, check HQL queries manually |
| Spring Security config | Medium | Test all auth flows manually |
