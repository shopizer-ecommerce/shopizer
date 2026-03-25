# CI/CD Quick Start Guide

## Overview
The Shopizer backend now has a complete CI/CD pipeline using GitHub Actions.

## What's Automated

### On Every Push/PR
- ✅ Build and compile
- ✅ Run all unit tests
- ✅ Generate code coverage reports (30% line, 37% branch minimum)
- ✅ Static code analysis (SpotBugs)
- ✅ Code style checks (Checkstyle)
- ✅ Security vulnerability scanning (OWASP)
- ✅ Secret detection (TruffleHog)

### On Push to `develop` or `main`
- ✅ All of the above, plus:
- 🐳 Docker image build
- 🚀 Automatic deployment (when configured)

## Local Development

### Build and Test
```bash
cd shopizer
mvn clean install
mvn test
```

### Check Code Coverage
```bash
mvn jacoco:report
open target/site/jacoco/index.html
```

### Run Security Scan
```bash
mvn dependency-check:check
open target/dependency-check-report.html
```

### Run Code Quality Checks
```bash
mvn spotbugs:check
mvn checkstyle:check
```

### Build Docker Image
```bash
cd sm-shop
docker build -t shopizer:local .
docker run -p 8080:8080 shopizer:local
```

## Branch Strategy

```
main (production)
  ↑
  └── Pull Request (requires approval)
       ↑
develop (staging)
  ↑
  └── feature/* branches
```

### Workflow
1. Create feature branch from `develop`
2. Make changes and commit
3. Push to GitHub - CI runs automatically
4. Create PR to `develop` - CI runs again
5. After approval, merge to `develop` - Deploys to staging
6. Create PR from `develop` to `main` for production release

## GitHub Actions Workflow

### View Pipeline Status
1. Go to repository on GitHub
2. Click "Actions" tab
3. See all workflow runs

### Pipeline Jobs
1. **build-and-test** (5-8 min) - Compile, test, coverage
2. **code-quality** (3-5 min) - SpotBugs, Checkstyle, SonarCloud
3. **security-scan** (5-10 min) - OWASP, secret scanning
4. **docker-build** (3-5 min) - Build and scan Docker image
5. **deploy-staging** (2-3 min) - Deploy to staging (develop branch)
6. **deploy-production** (2-3 min) - Deploy to production (main branch)

## Required Secrets (Optional)

Configure in: Settings → Secrets and variables → Actions

### For Docker Hub
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_PASSWORD`

### For AWS Deployment
- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`

### For Code Quality
- `SONAR_TOKEN`

### For Notifications
- `SLACK_WEBHOOK_URL`

## Troubleshooting

### Build Fails Locally
```bash
# Clean Maven cache
rm -rf ~/.m2/repository
mvn clean install -U

# Check Java version
java -version  # Should be 17
```

### Tests Fail
```bash
# Run specific test
mvn test -Dtest=YourTestClass

# Skip tests temporarily (not recommended)
mvn clean install -DskipTests
```

### Coverage Below Threshold
```bash
# Check current coverage
mvn jacoco:report
# Add more tests to increase coverage
```

### Security Vulnerabilities Found
```bash
# View detailed report
mvn dependency-check:check
open target/dependency-check-report.html

# Update vulnerable dependencies
mvn versions:display-dependency-updates
```

## Monitoring

### Build Status Badge
Add to README.md:
```markdown
![CI/CD](https://github.com/YOUR_ORG/shopizer/workflows/Shopizer%20CI/CD%20Pipeline/badge.svg)
```

### Coverage Badge
After setting up Codecov:
```markdown
[![codecov](https://codecov.io/gh/YOUR_ORG/shopizer/branch/main/graph/badge.svg)](https://codecov.io/gh/YOUR_ORG/shopizer)
```

## Best Practices

### Before Committing
1. Run tests locally: `mvn test`
2. Check code style: `mvn checkstyle:check`
3. Verify build: `mvn clean install`

### Writing Tests
- Maintain minimum 30% line coverage
- Write unit tests for new features
- Use meaningful test names
- Mock external dependencies

### Security
- Never commit secrets or credentials
- Use environment variables for sensitive data
- Review dependency-check reports regularly
- Update dependencies weekly (Dependabot PRs)

## Common Commands

```bash
# Full build with all checks
mvn clean install

# Quick build (skip tests)
mvn clean install -DskipTests

# Run only tests
mvn test

# Generate all reports
mvn clean verify site

# Update dependencies
mvn versions:display-dependency-updates

# Check for security issues
mvn dependency-check:check

# Format code (if configured)
mvn spotless:apply
```

## Getting Help

- **Pipeline Issues**: Check GitHub Actions logs
- **Build Issues**: Review Maven output
- **Test Failures**: Check test reports in `target/surefire-reports/`
- **Coverage Issues**: Review JaCoCo report in `target/site/jacoco/`

## Next Steps

1. ✅ Push code to test the pipeline
2. ⚙️ Configure optional secrets for full functionality
3. 🏗️ Set up AWS infrastructure for deployments
4. 📊 Configure monitoring and alerting
5. 📚 Update team documentation

---

**Questions?** Check the detailed plan in `CI_CD_IMPLEMENTATION_PLAN.md`
