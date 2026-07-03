# Java 11 to Java 17 Migration Plan

## Epic: Migrate Shopizer from Java 11 to Java 17

**Epic Description:** 
Modernize the Shopizer e-commerce platform by migrating from Java 11 to Java 17. This migration includes updating the Java version configuration, upgrading incompatible dependencies, updating Docker and CI configurations, and ensuring all tests pass with the new Java version.

**Epic Owner:** [To be assigned]
**Target Completion:** [To be determined]
**Risk Level:** High (due to dependency upgrades)

---

## Phase 1: Assessment and Planning

### Task 1.1: Dependency Compatibility Analysis
- **Priority:** High
- **Estimate:** 2-3 days
- **Description:** Research and document Java 17 compatibility for all current dependencies
- **Acceptance Criteria:**
  - Document all dependencies and their current versions
  - Identify dependencies with known Java 17 compatibility issues
  - Research required upgrade versions for incompatible dependencies
  - Document any breaking changes in dependency upgrades
- **Dependencies:** None

### Task 1.2: Create Feature Branch
- **Priority:** High
- **Estimate:** 0.5 days
- **Description:** Create a feature branch for the migration work
- **Acceptance Criteria:**
  - Branch created from main (3.2.7)
  - Branch named `feature/java17-migration`
  - Team notified of branch creation
- **Dependencies:** Task 1.1

### Task 1.3: Backup Current Configuration
- **Priority:** Medium
- **Estimate:** 0.5 days
- **Description:** Document and backup all current Java version configurations
- **Acceptance Criteria:**
  - Document all files containing Java version references
  - Create backup of current pom.xml files
  - Document current Docker and CI configurations
- **Dependencies:** Task 1.2

---

## Phase 2: Core Java Version Update

### Task 2.1: Update Root POM Java Version
- **Priority:** High
- **Estimate:** 0.5 days
- **Description:** Update Java version in root pom.xml from 11 to 17
- **Acceptance Criteria:**
  - `<java.version>11</java.version>` changed to `<java.version>17</java.version>` in root pom.xml
  - Maven compiler source and target properties updated
  - Code compiles successfully
- **Dependencies:** Task 1.3

### Task 2.2: Update Module POM Java Versions
- **Priority:** High
- **Estimate:** 0.5 days
- **Description:** Update Java version in all module pom.xml files
- **Acceptance Criteria:**
  - sm-core-model/pom.xml: `<java.version>11</java.version>` → `<java.version>17</java.version>`
  - sm-shop-model/pom.xml: `<source>11</source>` → `<source>17</source>` in maven-javadoc-plugin
  - All modules compile successfully
- **Dependencies:** Task 2.1

### Task 2.3: Update Docker Configuration
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Update Dockerfile to use Java 17 base image
- **Acceptance Criteria:**
  - sm-shop/Dockerfile: Replace `adoptopenjdk/openjdk11-openj9:alpine` with `eclipse-temurin:17-jre-alpine`
  - Docker image builds successfully
  - Application starts correctly in Docker container
- **Dependencies:** Task 2.2

### Task 2.4: Update CI Configuration
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Update CircleCI configuration to use Java 17
- **Acceptance Criteria:**
  - .circleci/config.yml: Replace `shopizerecomm/ci:java11` with `cimg/openjdk:17.0`
  - CI pipeline runs successfully with Java 17
  - All CI checks pass
- **Dependencies:** Task 2.3

---

## Phase 3: Critical Dependency Upgrades

### Task 3.1: Upgrade Drools to Java 17 Compatible Version
- **Priority:** Critical
- **Estimate:** 3-5 days
- **Description:** Upgrade Drools from 7.32.0.Final to 8.x+ for Java 17 compatibility
- **Acceptance Criteria:**
  - Drools upgraded to version 8.0.0.Final or higher
  - Update all Drools dependencies (kie-ci, drools-decisiontables, drools-core, drools-compiler, kie-spring)
  - Update any Drools-related code for breaking changes
  - All rule files still work correctly
  - Tests pass with new Drools version
- **Dependencies:** Task 2.4
- **Notes:** This is the highest-risk dependency upgrade

### Task 3.2: Migrate javax.mail to jakarta.mail
- **Priority:** Critical
- **Estimate:** 2-3 days
- **Description:** Replace javax.mail with jakarta.mail for Java 17 compatibility
- **Acceptance Criteria:**
  - Remove javax.mail dependency (version 1.4.7)
  - Add jakarta.mail dependency (version 2.0.1 or higher)
  - Update all imports from javax.mail.* to jakarta.mail.*
  - Update any code that uses javax.mail APIs
  - Email functionality still works correctly
  - Tests pass
- **Dependencies:** Task 2.4
- **Notes:** This is a namespace change requiring code updates

### Task 3.3: Upgrade Infinispan
- **Priority:** High
- **Estimate:** 2-3 days
- **Description:** Upgrade Infinispan from 9.4.18.Final to 11.x+ for Java 17 compatibility
- **Acceptance Criteria:**
  - Infinispan upgraded to version 11.0.0.Final or higher
  - Update all Infinispan dependencies (infinispan-core, infinispan-cachestore-jdbc, infinispan-tree)
  - Update any Infinispan configuration for breaking changes
  - Cache functionality still works correctly
  - Tests pass
- **Dependencies:** Task 3.1

---

## Phase 4: Additional Dependency Upgrades

### Task 4.1: Upgrade Guava
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Upgrade Guava from 27.1-jre to 31.x+ for better Java 17 support
- **Acceptance Criteria:**
  - Guava upgraded to version 31.1-jre or higher
  - Any deprecated API usage updated
  - Tests pass
- **Dependencies:** Task 3.3

### Task 4.2: Upgrade MapStruct
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Upgrade MapStruct from 1.3.0.Final to 1.5.x+ for Java 17 support
- **Acceptance Criteria:**
  - MapStruct upgraded to version 1.5.2.Final or higher
  - Update MapStruct processor configuration if needed
  - All mapping code still works correctly
  - Tests pass
- **Dependencies:** Task 3.3

### Task 4.3: Upgrade Commons Libraries
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Upgrade Apache Commons libraries to latest stable versions
- **Acceptance Criteria:**
  - commons-lang upgraded from 3.5 to 3.12.x+
  - commons-io upgraded from 2.7 to 2.11.x+
  - commons-fileupload upgraded from 1.3.3 to 1.5.x+
  - commons-validator upgraded from 1.5.1 to 1.7.x+
  - Tests pass
- **Dependencies:** Task 3.3

### Task 4.4: Upgrade JWT Library
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Upgrade JWT library from 0.8.0 to 0.11.x+
- **Acceptance Criteria:**
  - JWT library upgraded to version 0.11.5 or higher
  - Update any API changes
  - Authentication still works correctly
  - Tests pass
- **Dependencies:** Task 3.3

### Task 4.5: Upgrade Apache HttpClient
- **Priority:** Low
- **Estimate:** 1 day
- **Description:** Upgrade Apache HttpClient from 4.5.2 to 5.x+
- **Acceptance Criteria:**
  - HttpClient upgraded to version 5.2.x or higher
  - Update any code using deprecated APIs
  - HTTP functionality still works correctly
  - Tests pass
- **Dependencies:** Task 4.3

### Task 4.6: Replace JSON Simple
- **Priority:** Low
- **Estimate:** 2 days
- **Description:** Replace unmaintained json-simple with a modern JSON library
- **Acceptance Criteria:**
  - Remove json-simple dependency (version 1.1.1)
  - Add Jackson or Gson for JSON processing
  - Update all JSON-related code
  - JSON functionality still works correctly
  - Tests pass
- **Dependencies:** Task 4.3

---

## Phase 5: Testing and Validation

### Task 5.1: Run Unit Tests
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Execute full unit test suite with Java 17
- **Acceptance Criteria:**
  - All unit tests pass
  - Test coverage report generated
  - No test failures related to Java version or dependency upgrades
- **Dependencies:** All Phase 4 tasks

### Task 5.2: Run Integration Tests
- **Priority:** High
- **Estimate:** 2 days
- **Description:** Execute full integration test suite with Java 17
- **Acceptance Criteria:**
  - All integration tests pass
  - Database connectivity verified
  - External service integrations verified
- **Dependencies:** Task 5.1

### Task 5.3: Manual Testing
- **Priority:** High
- **Estimate:** 3 days
- **Description:** Perform manual testing of critical functionality
- **Acceptance Criteria:**
  - Product catalog browsing works
  - Shopping cart functionality works
  - Checkout process works
  - Customer authentication works
  - Order processing works
  - Email notifications work
  - Shipping calculations work
- **Dependencies:** Task 5.2

### Task 5.4: Performance Testing
- **Priority:** Medium
- **Estimate:** 2 days
- **Description:** Run performance tests to ensure no degradation
- **Acceptance Criteria:**
  - Performance metrics comparable to Java 11 baseline
  - No memory leaks detected
  - Response times within acceptable range
- **Dependencies:** Task 5.3

---

## Phase 6: Documentation and Deployment

### Task 6.1: Update Documentation
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Update all documentation to reflect Java 17 requirements
- **Acceptance Criteria:**
  - README.md updated with Java 17 requirement
  - Developer documentation updated
  - Deployment documentation updated
  - Docker documentation updated
- **Dependencies:** Task 5.4

### Task 6.2: Create Rollback Plan
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Document rollback procedure in case of issues
- **Acceptance Criteria:**
  - Rollback procedure documented
  - Rollback tested in staging environment
  - Team trained on rollback process
- **Dependencies:** Task 6.1

### Task 6.3: Staging Deployment
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Deploy Java 17 version to staging environment
- **Acceptance Criteria:**
  - Application deployed successfully to staging
  - Smoke tests pass in staging
  - Monitoring configured
- **Dependencies:** Task 6.2

### Task 6.4: Production Deployment
- **Priority:** High
- **Estimate:** 1 day
- **Description:** Deploy Java 17 version to production
- **Acceptance Criteria:**
  - Application deployed successfully to production
  - Production smoke tests pass
  - Monitoring confirms normal operation
  - No critical errors in logs
- **Dependencies:** Task 6.3

---

## Phase 7: Post-Deployment

### Task 7.1: Monitor Production
- **Priority:** High
- **Estimate:** 3 days
- **Description:** Monitor production for issues after deployment
- **Acceptance Criteria:**
  - Application logs reviewed for 3 days
  - Error rates within normal range
  - Performance metrics normal
  - No customer-reported issues
- **Dependencies:** Task 6.4

### Task 7.2: Cleanup Old Branch
- **Priority:** Low
- **Estimate:** 0.5 days
- **Description:** Delete feature branch after successful deployment
- **Acceptance Criteria:**
  - Feature branch deleted
  - Repository cleaned up
- **Dependencies:** Task 7.1

### Task 7.3: Retrospective
- **Priority:** Medium
- **Estimate:** 1 day
- **Description:** Conduct retrospective on migration process
- **Acceptance Criteria:**
  - Retrospective meeting held
  - Lessons learned documented
  - Process improvements identified
- **Dependencies:** Task 7.1

---

## Summary

**Total Estimated Duration:** 35-45 days
**Total Tasks:** 23
**Critical Path:** Tasks 1.1 → 1.2 → 1.3 → 2.1 → 2.2 → 2.3 → 2.4 → 3.1 → 3.2 → 3.3 → 4.1 → 5.1 → 5.2 → 5.3 → 5.4 → 6.1 → 6.2 → 6.3 → 6.4 → 7.1

**Key Risks:**
1. Drools upgrade may require significant code changes
2. javax.mail to jakarta.mail migration requires namespace changes throughout codebase
3. Some dependencies may have breaking changes that require code updates
4. Potential performance regression with Java 17
5. Docker image size may increase with Java 17

**Success Criteria:**
- All tests pass with Java 17
- No functionality regression
- Performance comparable to or better than Java 11
- Documentation updated
- Smooth deployment to production
