# CI/CD Implementation Plan - Shopizer Backend

**Date**: March 25, 2026  
**Project**: Shopizer E-commerce Backend  
**Technology**: Java 17, Spring Boot 2.5.12, Maven, MySQL

---

## Current State

### Existing Infrastructure
- ✅ GitHub Actions workflows configured
  - `.github/workflows/ci-cd.yml` (8-stage pipeline)
  - `.github/workflows/pr-validation.yml` (PR checks)
- ✅ Docker support (`sm-shop/Dockerfile`)
- ✅ Multi-module Maven project
- ✅ CircleCI legacy config (`.circleci/config.yml`)

### Technology Stack
- **Language**: Java 17
- **Framework**: Spring Boot 2.5.12
- **Build Tool**: Maven
- **Database**: MySQL (H2 for dev)
- **Container**: Docker
- **Modules**: sm-core, sm-core-model, sm-core-modules, sm-shop-model, sm-shop

---

## Implementation Phases

### Phase 1: Validate & Fix Existing Pipeline (Week 1)

#### 1.1 Audit Current Setup
- [ ] Test GitHub Actions workflows on develop branch
- [ ] Verify all 8 jobs execute successfully
- [ ] Check Docker image builds correctly
- [ ] Validate test execution and coverage reports

#### 1.2 Fix Configuration Issues
- [ ] Standardize Java version to 17 (currently mixed 11/17)
- [ ] Add missing Maven plugins to `pom.xml`:
  - JaCoCo (code coverage)
  - SpotBugs (static analysis)
  - OWASP Dependency-Check (security)
  - Checkstyle (code style)
- [ ] Create `dependency-check-suppressions.xml`
- [ ] Fix any failing unit tests

#### 1.3 Configure GitHub Secrets
```
Required Secrets:
- DOCKERHUB_USERNAME
- DOCKERHUB_PASSWORD
- AWS_ACCESS_KEY_ID
- AWS_SECRET_ACCESS_KEY
- SONAR_TOKEN
- SLACK_WEBHOOK_URL
```

**Action Items**:
- Add secrets in GitHub repository settings
- Document secret rotation policy
- Set up AWS IAM user with minimal permissions

---

### Phase 2: Enhance Build & Test (Week 2)

#### 2.1 Optimize Maven Build
- [ ] Add JaCoCo plugin configuration
- [ ] Configure SpotBugs with custom rules
- [ ] Enable Checkstyle enforcement
- [ ] Set up parallel test execution
- [ ] Optimize Maven dependency caching

#### 2.2 Improve Test Coverage
**Current**: 30% lines, 37% branches  
**Target**: 40%+ lines, 45%+ branches

- [ ] Add integration tests for critical APIs
- [ ] Set up Testcontainers for database tests
- [ ] Configure test data fixtures
- [ ] Add API contract tests

#### 2.3 Performance Testing
- [ ] Set up JMeter test scripts
- [ ] Define performance baselines
- [ ] Run load tests on staging
- [ ] Add performance gates to pipeline

---

### Phase 3: Security Hardening (Week 3)

#### 3.1 Dependency Scanning
- [ ] Configure OWASP Dependency-Check
- [ ] Set CVSS threshold to 7 (fail build)
- [ ] Enable Dependabot for automated updates
- [ ] Create vulnerability suppression file

#### 3.2 Container Security
- [ ] Trivy scanning (already configured)
- [ ] Add Snyk container scanning
- [ ] Implement Docker image signing
- [ ] Scan base images regularly

#### 3.3 Code Security
- [ ] TruffleHog secret scanning (already configured)
- [ ] SonarCloud SAST analysis
- [ ] Remove hardcoded credentials
- [ ] Implement secret management (AWS Secrets Manager)

---

### Phase 4: Deployment Automation (Week 4-5)

#### 4.1 Infrastructure as Code
- [ ] Create Terraform modules for:
  - ECS clusters (staging/production)
  - RDS MySQL instances
  - Application Load Balancers
  - VPC and networking
  - CloudWatch alarms
- [ ] Set up S3 backend for Terraform state
- [ ] Create separate AWS accounts for environments

#### 4.2 Environment Configuration

**Staging Environment**:
```yaml
Cluster: shopizer-staging
Service: shopizer-backend
Database: shopizer-staging.xxxxx.rds.amazonaws.com
URL: https://staging.shopizer.com
Resources: 1 vCPU, 2GB RAM
```

**Production Environment**:
```yaml
Cluster: shopizer-production
Service: shopizer-backend
Database: shopizer-production.xxxxx.rds.amazonaws.com
URL: https://shopizer.com
Resources: 2 vCPU, 4GB RAM
Auto-scaling: 2-10 instances
```

#### 4.3 Database Migration
- [ ] Add Flyway dependency to `pom.xml`
- [ ] Create migration scripts in `src/main/resources/db/migration`
- [ ] Test migrations on staging
- [ ] Implement rollback scripts
- [ ] Automate backup before production deploy

#### 4.4 Deployment Strategy
- [ ] Implement blue-green deployment
- [ ] Configure health check endpoints
- [ ] Set up automated rollback triggers
- [ ] Add deployment approval gates for production

---

### Phase 5: Monitoring & Observability (Week 6)

#### 5.1 Application Monitoring
- [ ] Enable Spring Boot Actuator
- [ ] Configure Prometheus metrics export
- [ ] Set up Grafana dashboards
- [ ] Add custom business metrics (orders, revenue)

#### 5.2 Logging
- [ ] Configure structured JSON logging
- [ ] Set up CloudWatch Logs
- [ ] Create log aggregation queries
- [ ] Configure log retention policies

#### 5.3 Alerting Rules
```
Critical Alerts:
- Health check failures (immediate)
- Error rate > 5% (5 min window)
- Response time > 2s p95 (10 min window)
- CPU > 80% (15 min window)
- Memory > 85% (15 min window)
- Disk space < 20% (immediate)
```

#### 5.4 Notification Channels
- [ ] Slack integration for pipeline status
- [ ] Email for deployment notifications
- [ ] PagerDuty for production incidents
- [ ] Status page integration

---

### Phase 6: Documentation & Training (Week 7)

#### 6.1 Documentation
- [ ] Deployment runbook
- [ ] Rollback procedures
- [ ] Troubleshooting guide
- [ ] Architecture diagrams
- [ ] API documentation (Swagger)

#### 6.2 Team Training
- [ ] CI/CD workflow walkthrough
- [ ] How to trigger deployments
- [ ] Monitoring dashboard usage
- [ ] Incident response procedures
- [ ] Rollback execution

---

## Pipeline Architecture

### Current Pipeline (8 Jobs)

```
1. build-and-test
   ├── Checkout code
   ├── Setup JDK 17
   ├── Maven build
   ├── Run tests
   ├── Generate coverage
   └── Upload artifacts

2. code-quality (depends on: build-and-test)
   ├── SpotBugs analysis
   └── SonarCloud scan

3. security-scan (depends on: build-and-test)
   ├── OWASP Dependency Check
   ├── TruffleHog secret scan
   └── Upload reports

4. docker-build (depends on: build-and-test, security-scan)
   ├── Download artifacts
   ├── Build Docker image
   ├── Push to Docker Hub
   └── Trivy vulnerability scan

5. deploy-staging (depends on: docker-build, branch: develop)
   ├── Configure AWS credentials
   ├── Update ECS service
   ├── Wait for stability
   └── Run smoke tests

6. deploy-production (depends on: docker-build, branch: main)
   ├── Manual approval
   ├── Configure AWS credentials
   ├── Update ECS service
   ├── Wait for stability
   ├── Health checks
   └── Slack notification
```

---

## Quick Wins (Immediate Actions)

### Week 1 Priority Tasks
1. **Test existing pipeline**
   ```bash
   git checkout develop
   git commit --allow-empty -m "Test CI/CD pipeline"
   git push origin develop
   ```

2. **Add GitHub secrets**
   - Navigate to: Settings → Secrets and variables → Actions
   - Add all required secrets

3. **Fix Java version in pom.xml**
   ```xml
   <properties>
       <java.version>17</java.version>
   </properties>
   ```

4. **Enable branch protection**
   - Require PR reviews (minimum 1)
   - Require status checks to pass
   - Require branches to be up to date

5. **Set up Dependabot**
   - Create `.github/dependabot.yml`
   - Enable security updates

---

## Required Configuration Updates

### 1. Root pom.xml Updates

Add to `<build><plugins>`:

```xml
<!-- JaCoCo for code coverage -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.10</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>

<!-- SpotBugs for static analysis -->
<plugin>
    <groupId>com.github.spotbugs</groupId>
    <artifactId>spotbugs-maven-plugin</artifactId>
    <version>4.7.3.5</version>
</plugin>

<!-- OWASP Dependency Check -->
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>8.4.0</version>
    <configuration>
        <failBuildOnCVSS>7</failBuildOnCVSS>
    </configuration>
</plugin>
```

### 2. Create dependency-check-suppressions.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<suppressions xmlns="https://jeremylong.github.io/DependencyCheck/dependency-suppression.1.3.xsd">
    <!-- Add known false positives here -->
</suppressions>
```

### 3. Create .github/dependabot.yml

```yaml
version: 2
updates:
  - package-ecosystem: "maven"
    directory: "/"
    schedule:
      interval: "weekly"
    open-pull-requests-limit: 10
```

---

## Infrastructure Requirements

### AWS Resources Needed

**Staging**:
- ECS Cluster (Fargate)
- RDS MySQL (db.t3.small)
- Application Load Balancer
- CloudWatch Log Groups
- ECR Repository (optional)

**Production**:
- ECS Cluster (Fargate with auto-scaling)
- RDS MySQL (db.t3.medium, Multi-AZ)
- Application Load Balancer
- CloudWatch Log Groups + Alarms
- ECR Repository (optional)

**Estimated Monthly Cost**:
- Staging: ~$150-200
- Production: ~$400-500

---

## Success Metrics

### Pipeline Performance
- **Build Time**: < 10 minutes (current baseline: TBD)
- **Test Execution**: < 5 minutes
- **Docker Build**: < 3 minutes
- **Deployment Time**: < 5 minutes

### Deployment Metrics
- **Deployment Frequency**: Multiple times per day
- **Lead Time**: < 1 hour (commit to production)
- **MTTR**: < 30 minutes
- **Change Failure Rate**: < 15%

### Quality Metrics
- **Test Coverage**: > 40% (current: 30%)
- **Code Quality**: SonarCloud Quality Gate passing
- **Security**: Zero high/critical vulnerabilities
- **Uptime**: 99.9% availability

---

## Rollback Strategy

### Automated Rollback Triggers
- Health check failures (3 consecutive)
- Error rate > 10% (5 min window)
- Response time > 5s p95 (5 min window)

### Manual Rollback Procedure
1. Identify previous stable version
2. Update ECS task definition to previous image tag
3. Force new deployment
4. Verify health checks
5. Monitor error rates
6. Rollback database migrations if needed

### Rollback Commands
```bash
# Rollback to previous Docker image
aws ecs update-service \
  --cluster shopizer-production \
  --service shopizer-backend \
  --task-definition shopizer-backend:PREVIOUS_VERSION \
  --force-new-deployment

# Verify rollback
aws ecs describe-services \
  --cluster shopizer-production \
  --services shopizer-backend
```

---

## Risk Mitigation

### Identified Risks

1. **Database Migration Failures**
   - Mitigation: Test on staging, automated backups, rollback scripts

2. **Deployment Downtime**
   - Mitigation: Blue-green deployment, health checks

3. **Security Vulnerabilities**
   - Mitigation: Automated scanning, dependency updates

4. **Configuration Drift**
   - Mitigation: Infrastructure as Code, version control

5. **Secret Exposure**
   - Mitigation: Secret scanning, AWS Secrets Manager

---

## Maintenance Schedule

### Daily
- Monitor pipeline failures
- Review security alerts
- Check deployment status

### Weekly
- Review failed builds
- Update dependencies (Dependabot PRs)
- Check performance metrics

### Monthly
- Review pipeline performance
- Update documentation
- Optimize build times
- Security audit

### Quarterly
- Rotate AWS credentials
- Review IAM permissions
- Update CI/CD tools
- Disaster recovery drill

---

## Next Steps

### Immediate (This Week)
1. ✅ Create this implementation plan
2. Test existing GitHub Actions pipeline
3. Configure required GitHub secrets
4. Fix Java version inconsistencies
5. Enable branch protection rules

### Short Term (Next 2 Weeks)
1. Add missing Maven plugins
2. Improve test coverage
3. Set up SonarCloud integration
4. Configure Dependabot

### Medium Term (Next Month)
1. Create AWS infrastructure with Terraform
2. Set up staging environment
3. Implement database migrations
4. Configure monitoring and alerting

### Long Term (Next Quarter)
1. Optimize pipeline performance
2. Implement advanced deployment strategies
3. Set up comprehensive monitoring
4. Train team on CI/CD workflows

---

## References

- [Existing CI/CD Workflow](.github/workflows/ci-cd.yml)
- [PR Validation Workflow](.github/workflows/pr-validation.yml)
- [Dockerfile](sm-shop/Dockerfile)
- [Maven POM](pom.xml)
- [Spring Boot Deployment Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html)
- [AWS ECS Best Practices](https://docs.aws.amazon.com/AmazonECS/latest/bestpracticesguide/)

---

## Contact & Support

**Project Lead**: TBD  
**DevOps Team**: TBD  
**Slack Channel**: #shopizer-cicd  
**Documentation**: `/docs/CICD_IMPLEMENTATION_PLAN.md`

---

**Last Updated**: March 25, 2026  
**Version**: 1.0  
**Status**: Planning Phase
