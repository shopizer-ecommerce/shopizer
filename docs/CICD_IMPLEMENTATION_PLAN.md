# CI/CD Implementation Plan - Shopizer Backend

## Overview
This document outlines the CI/CD implementation strategy for the Shopizer backend (Java/Spring Boot e-commerce platform).

## Current State
- **Technology Stack**: Java 11/17, Spring Boot 2.5.12, Maven, H2/MySQL
- **Existing CI**: CircleCI configuration present (`.circleci/config.yml`)
- **Containerization**: Docker support available
- **Modules**: Multi-module Maven project (sm-core, sm-core-model, sm-core-modules, sm-shop-model, sm-shop)

---

## CI/CD Pipeline Stages

### 1. **Build Stage**
**Objective**: Compile code and create artifacts

**Steps**:
- Checkout source code
- Set up Java 17 environment
- Cache Maven dependencies
- Run `mvn clean install`
- Generate build artifacts (JAR files)
- Store artifacts for downstream stages

**Tools**: Maven, Docker

---

### 2. **Test Stage**
**Objective**: Ensure code quality and functionality

**Steps**:
- **Unit Tests**: Run `mvn test`
- **Integration Tests**: Execute integration test suite
- **Code Coverage**: Generate JaCoCo reports (target: 30% lines, 37% branches)
- **Static Analysis**: Run SpotBugs for code quality
- **Test Reports**: Publish test results

**Quality Gates**:
- All tests must pass
- Minimum code coverage thresholds met
- No critical bugs from static analysis

---

### 3. **Security Scan Stage**
**Objective**: Identify vulnerabilities

**Steps**:
- **Dependency Check**: OWASP dependency-check-maven plugin
- **Container Scan**: Trivy/Snyk for Docker image vulnerabilities
- **Secret Detection**: GitGuardian/TruffleHog
- **SAST**: SonarQube/SonarCloud analysis

**Quality Gates**:
- No high/critical vulnerabilities
- Security score above threshold

---

### 4. **Build Docker Image Stage**
**Objective**: Create containerized application

**Steps**:
- Build Docker image using `sm-shop/Dockerfile`
- Tag image with:
  - `latest`
  - Git commit SHA
  - Version from `pom.xml` (3.2.5)
  - Branch name
- Run container smoke tests
- Scan image for vulnerabilities

**Artifacts**:
- Docker image: `shopizerecomm/shopizer:<tag>`

---

### 5. **Push to Registry Stage**
**Objective**: Store Docker images

**Steps**:
- Authenticate to Docker Hub
- Push tagged images
- Update image metadata
- Clean up old images (retention policy)

**Registries**:
- Docker Hub (current)
- AWS ECR (recommended for production)

---

### 6. **Deploy to Staging Stage**
**Objective**: Deploy to staging environment

**Steps**:
- Deploy to staging Kubernetes/ECS cluster
- Run database migrations
- Health check validation
- Smoke tests on staging

**Environment**:
- Staging database (MySQL)
- Staging configurations
- Limited resources

---

### 7. **Integration Tests (Staging)**
**Objective**: Validate end-to-end functionality

**Steps**:
- API integration tests
- Database connectivity tests
- External service integration tests
- Performance baseline tests

---

### 8. **Deploy to Production Stage**
**Objective**: Release to production (manual approval)

**Steps**:
- Manual approval gate
- Blue-green deployment
- Database migrations (if needed)
- Health checks
- Rollback capability

**Deployment Strategy**:
- Blue-green or canary deployment
- Zero-downtime deployment
- Automated rollback on failure

---

## Implementation Options

### Option 1: GitHub Actions (Recommended)

**Pros**:
- Native GitHub integration
- Free for public repos
- Easy to maintain
- Good marketplace ecosystem

**Configuration**: `.github/workflows/ci-cd.yml`

```yaml
name: CI/CD Pipeline

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  build-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: 'maven'
      - name: Build and Test
        run: mvn clean install
      - name: Upload Coverage
        uses: codecov/codecov-action@v3

  security-scan:
    needs: build-test
    runs-on: ubuntu-latest
    steps:
      - name: OWASP Dependency Check
        run: mvn dependency-check:check
      - name: Trivy Scan
        uses: aquasecurity/trivy-action@master

  docker-build-push:
    needs: [build-test, security-scan]
    runs-on: ubuntu-latest
    steps:
      - name: Build Docker Image
        run: docker build -t shopizer:${{ github.sha }} ./sm-shop
      - name: Push to Registry
        run: docker push shopizer:${{ github.sha }}

  deploy-staging:
    needs: docker-build-push
    if: github.ref == 'refs/heads/develop'
    runs-on: ubuntu-latest
    steps:
      - name: Deploy to Staging
        run: kubectl apply -f k8s/staging/

  deploy-production:
    needs: docker-build-push
    if: github.ref == 'refs/heads/main'
    environment: production
    runs-on: ubuntu-latest
    steps:
      - name: Deploy to Production
        run: kubectl apply -f k8s/production/
```

---

### Option 2: GitLab CI/CD

**Configuration**: `.gitlab-ci.yml`

```yaml
stages:
  - build
  - test
  - security
  - package
  - deploy-staging
  - deploy-production

variables:
  MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

build:
  stage: build
  image: maven:3.8-openjdk-17
  script:
    - mvn clean compile
  cache:
    paths:
      - .m2/repository

test:
  stage: test
  image: maven:3.8-openjdk-17
  script:
    - mvn test
  artifacts:
    reports:
      junit: target/surefire-reports/TEST-*.xml

security:
  stage: security
  script:
    - mvn dependency-check:check

docker-build:
  stage: package
  script:
    - docker build -t shopizer:$CI_COMMIT_SHA ./sm-shop
    - docker push shopizer:$CI_COMMIT_SHA
```

---

### Option 3: Jenkins Pipeline

**Configuration**: `Jenkinsfile`

```groovy
pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8'
        jdk 'JDK 17'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Security Scan') {
            steps {
                sh 'mvn dependency-check:check'
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    docker.build("shopizer:${env.BUILD_ID}", "./sm-shop")
                }
            }
        }
        
        stage('Deploy to Staging') {
            when {
                branch 'develop'
            }
            steps {
                sh 'kubectl apply -f k8s/staging/'
            }
        }
        
        stage('Deploy to Production') {
            when {
                branch 'main'
            }
            steps {
                input 'Deploy to Production?'
                sh 'kubectl apply -f k8s/production/'
            }
        }
    }
}
```

---

### Option 4: AWS CodePipeline

**Components**:
- **CodeCommit/GitHub**: Source control
- **CodeBuild**: Build and test
- **CodeDeploy**: Deployment to ECS/EKS
- **ECR**: Container registry

**buildspec.yml**:
```yaml
version: 0.2

phases:
  pre_build:
    commands:
      - echo Logging in to Amazon ECR...
      - aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REGISTRY
  
  build:
    commands:
      - echo Build started on `date`
      - mvn clean install
      - docker build -t shopizer:$CODEBUILD_RESOLVED_SOURCE_VERSION ./sm-shop
      - docker tag shopizer:$CODEBUILD_RESOLVED_SOURCE_VERSION $ECR_REGISTRY/shopizer:latest
  
  post_build:
    commands:
      - docker push $ECR_REGISTRY/shopizer:latest
      - echo Build completed on `date`

artifacts:
  files:
    - target/*.jar
```

---

## Environment Configuration

### Development
- **Branch**: `develop`
- **Trigger**: On every push
- **Deploy**: Auto-deploy to dev environment
- **Database**: H2 in-memory or dev MySQL

### Staging
- **Branch**: `develop` (after tests pass)
- **Trigger**: On merge to develop
- **Deploy**: Auto-deploy after approval
- **Database**: Staging MySQL with test data

### Production
- **Branch**: `main`
- **Trigger**: On merge to main
- **Deploy**: Manual approval required
- **Database**: Production MySQL

---

## Required Secrets/Variables

### CI/CD Platform
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_PASSWORD`
- `AWS_ACCESS_KEY_ID` (if using AWS)
- `AWS_SECRET_ACCESS_KEY`
- `SONAR_TOKEN` (for SonarQube)
- `KUBECONFIG` (for Kubernetes deployments)

### Application
- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`
- `SMTP_CONFIG`

---

## Monitoring & Notifications

### Notifications
- **Slack**: Pipeline status updates
- **Email**: Deployment notifications
- **PagerDuty**: Production failures

### Monitoring
- **Application**: Spring Boot Actuator + Prometheus
- **Infrastructure**: CloudWatch/Datadog
- **Logs**: ELK Stack or CloudWatch Logs
- **APM**: New Relic/Datadog APM

---

## Rollback Strategy

### Automated Rollback Triggers
- Health check failures
- Error rate > 5%
- Response time > 2s (p95)

### Manual Rollback
- Revert to previous Docker image tag
- Database rollback scripts (if schema changes)
- Feature flag toggle

---

## Best Practices

1. **Branch Strategy**
   - `main`: Production-ready code
   - `develop`: Integration branch
   - `feature/*`: Feature branches
   - `hotfix/*`: Emergency fixes

2. **Versioning**
   - Semantic versioning (MAJOR.MINOR.PATCH)
   - Git tags for releases
   - Docker image tags match version

3. **Testing**
   - Unit tests run on every commit
   - Integration tests on PR
   - E2E tests before production deploy

4. **Security**
   - Scan dependencies weekly
   - Rotate secrets quarterly
   - Least privilege access

5. **Documentation**
   - Update CHANGELOG.md
   - API documentation auto-generated
   - Deployment runbooks

---

## Implementation Timeline

### Phase 1: Foundation (Week 1-2)
- Set up CI pipeline (build + test)
- Configure code coverage
- Set up Docker builds

### Phase 2: Quality & Security (Week 3)
- Add security scanning
- Integrate SonarQube
- Set up quality gates

### Phase 3: Deployment (Week 4-5)
- Configure staging deployment
- Set up production deployment
- Implement rollback mechanism

### Phase 4: Monitoring (Week 6)
- Set up monitoring dashboards
- Configure alerts
- Document runbooks

---

## Success Metrics

- **Build Time**: < 10 minutes
- **Deployment Frequency**: Multiple times per day
- **Lead Time**: < 1 hour (commit to production)
- **MTTR**: < 30 minutes
- **Change Failure Rate**: < 15%
- **Test Coverage**: > 30% (current baseline)

---

## Maintenance

### Weekly
- Review failed builds
- Update dependencies
- Check security alerts

### Monthly
- Review pipeline performance
- Update documentation
- Optimize build times

### Quarterly
- Review and update secrets
- Audit access controls
- Update CI/CD tools

---

## Next Steps

1. Choose CI/CD platform (GitHub Actions recommended)
2. Create pipeline configuration files
3. Set up staging environment
4. Configure secrets and variables
5. Test pipeline with feature branch
6. Document deployment process
7. Train team on new workflow
8. Monitor and iterate

---

## References

- [CircleCI Current Config](.circleci/config.yml)
- [Dockerfile](sm-shop/Dockerfile)
- [Maven POM](pom.xml)
- [Spring Boot Deployment Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html)
