# Shopizer — Continuous Delivery Plan

> A local-first CD strategy with progressive delivery concepts, automated quality gates, and a clear path to production when you're ready.

---

## 1. Executive Summary

This plan is designed for a locally-run project that wants a professional-grade delivery pipeline without cloud infrastructure costs. Everything runs via GitHub Actions and Docker on your local machine.

When you're ready to go to production, this pipeline is designed to scale — just swap the local deploy step for any cloud provider.

---

## 2. Environments

| Environment | Purpose | Where it Runs | Trigger |
|---|---|---|---|
| **CI** | Build, test, quality gates | GitHub Actions runners | Every PR |
| **Local** | Development & manual testing | Your machine (Docker) | `docker compose up` |

---

## 3. Pipeline Architecture

```
PR Created/Updated
        ↓
   ┌────────────┐
   │   Build     │  ./mvnw package -DskipTests
   └─────┬──────┘
         ↓
   ┌────────────┐
   │   Test      │  ./mvnw verify
   └─────┬──────┘
         ↓
   ┌────────────┐
   │  Image Scan │  Trivy vulnerability scan
   └─────┬──────┘
         ↓
   ┌────────────────┐
   │  Docker Build   │  Build & push to GitHub Container Registry (GHCR)
   └─────┬──────────┘
         ↓
   ┌────────────────┐
   │  Smoke Test     │  Start container, hit health endpoint, tear down
   └─────┬──────────┘
         ↓
   ✅ PR is safe to merge
```

---

## 3.1 Deployment Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────────┐
│                        GITHUB ACTIONS (CI/CD)                       │
│                                                                     │
│  ┌──────────┐  ┌──────────┐  ┌───────────┐  ┌──────────────────┐  │
│  │  Build   │─▶│  Test    │─▶│  Trivy    │─▶│  Docker Build    │  │
│  │          │  │          │  │  Scan     │  │  & Push to GHCR  │  │
│  └──────────┘  └──────────┘  └───────────┘  └────────┬─────────┘  │
│                                                       │            │
│                                              ┌────────▼─────────┐  │
│                                              │  Smoke Test      │  │
│                                              │  (start container│  │
│                                              │   + health check)│  │
│                                              └──────────────────┘  │
└──────────────────────────────────────────────────────┬──────────────┘
                                                       │
                     Image available at                │
                     ghcr.io/<user>/shopizer:<sha>     │
                                                       │
┌──────────────────────────────────────────────────────▼──────────────┐
│                         LOCAL MACHINE                                │
│                                                                     │
│  docker compose up                                                  │
│                                                                     │
│  ┌──────────────────────────────────────────────────────────────┐   │
│  │                      Docker Compose                          │   │
│  │                                                              │   │
│  │  ┌─────────────────────┐      ┌──────────────────────────┐  │   │
│  │  │  Shopizer App       │      │  H2 Database             │  │   │
│  │  │                     │      │  (embedded, file-based)   │  │   │
│  │  │  Port: 8080         │─────▶│                          │  │   │
│  │  │  Image: ghcr.io/    │      │  Volume: ./data          │  │   │
│  │  │   <user>/shopizer   │      └──────────────────────────┘  │   │
│  │  └─────────────────────┘                                    │   │
│  │            │                                                 │   │
│  │            ▼                                                 │   │
│  │  localhost:8080/swagger-ui.html                              │   │
│  └──────────────────────────────────────────────────────────────┘   │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────┐
│                    GITHUB CONTAINER REGISTRY                        │
│                                                                     │
│  ghcr.io/<user>/shopizer:abc1234   (git SHA tagged)                │
│  ghcr.io/<user>/shopizer:latest                                    │
│                                                                     │
│  → Free for public repos                                           │
│  → Pull from anywhere: local, server, cloud                        │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 4. What Makes This Different

### 4.1 Container Smoke Test in CI

Most pet project pipelines stop at "tests pass." Ours actually boots the Docker container in CI, hits the health endpoint, and verifies the app starts correctly. This catches:
- Missing environment variables
- Broken Dockerfiles
- Startup crashes that unit tests don't cover

### 4.2 Vulnerability Scanning (Free)

Every PR gets a Trivy scan on the Docker image. You'll know about CVEs before merging — not after something breaks.

### 4.3 Immutable, Pullable Artifacts

Every merge produces a Docker image tagged with the git SHA, pushed to GHCR (free for public repos). You can pull and run any version locally:

```bash
docker pull ghcr.io/<user>/shopizer:abc1234
docker run -p 8080:8080 ghcr.io/<user>/shopizer:abc1234
```

### 4.4 Production-Ready When You Are

The pipeline is structured so that adding a real deployment target later is a single extra step — just add a deploy job after the smoke test. No rearchitecting needed.

---

## 5. GitHub Actions Workflow

### 5.1 Build & Test (every PR)

```yaml
- Build:       ./mvnw -B package -DskipTests
- Test:        ./mvnw -B verify
```

### 5.2 Docker Build & Push (on merge to main branch)

```yaml
- Build Docker image
- Tag with git SHA + 'latest'
- Push to GHCR
```

### 5.3 Security Scan

```yaml
- Trivy scan on built image
- Fail PR if critical/high CVEs found
```

### 5.4 Smoke Test

```yaml
- Start container from built image
- Wait for health endpoint (localhost:8080)
- Verify HTTP 200 response
- Tear down container
```

---

## 6. Local Development Workflow

Pull the latest tested image and run locally:

```bash
# Pull latest stable image
docker pull ghcr.io/<user>/shopizer:latest

# Or run with docker compose
docker compose up
```

Or build from source:

```bash
./mvnw clean package -DskipTests
docker build -t shopizer:local ./sm-shop
docker run -p 8080:8080 shopizer:local
```

---

## 7. Security (Zero Cost)

| Practice | How |
|---|---|
| No secrets in code | GitHub Actions secrets for any tokens |
| Image scanning | Trivy (free, runs in CI) |
| Dependency audit | `mvn dependency-check:check` (optional step) |
| Least-privilege | `permissions: contents: read` in workflows |

---

## 8. Cost

| Component | Cost |
|---|---|
| GitHub Actions | Free (2,000 min/month for private repos, unlimited for public) |
| GHCR | Free for public repos |
| Local Docker | Free |
| **Total** | **$0** |

---

## 9. Future: Path to Production

When you're ready to deploy for real, add one job to the pipeline:

```
Current pipeline → [Deploy to Cloud]
```

Options:
- **Railway / Render / Fly.io** — cheapest, single command deploy
- **AWS ECS Fargate** — production-grade, auto-scaling
- **DigitalOcean App Platform** — simple, affordable

No pipeline changes needed — just add a deploy step that pulls the already-built image from GHCR.

---

## 10. Success Metrics

| Metric | Target |
|---|---|
| Every PR has passing CI | 100% |
| Build + test time | < 10 minutes |
| Zero critical CVEs in images | Always |
| Any version reproducible from git SHA | Always |

---

*Designed for Shopizer running locally with Docker. Pipeline scales to cloud deployment with zero rearchitecting.*
