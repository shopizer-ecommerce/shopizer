# Shopizer — Build & Run Plan

## Prerequisites

```bash
java -version    # needs Java 11
mvn -version     # needs Maven 3.6+
```

If Java 11 isn't your default:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v11)
```

> **No `mvn` on PATH?** Use the Maven wrapper `./mvnw` instead of `mvn` in all commands below.

---

## Option A — Quickest: Run with H2 (no DB setup needed)

**Step 1: Build all modules from the root**
```bash
cd "/Users/pranjalgupta/local disk/Technogise/shopizer-suite/shopizer"
./mvnw clean install -DskipTests
```

**Step 2: Run with H2 profile**
```bash
cd sm-shop
mvn spring-boot:run -Dspring-boot.run.profiles=docker
```

- App: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Option B — Run with MySQL

**Step 1: Create the database**
```sql
CREATE DATABASE SALESMANAGER;
CREATE USER shopizer IDENTIFIED BY 'very-long-shopizer-password';
GRANT ALL ON SALESMANAGER.* TO shopizer;
FLUSH PRIVILEGES;
```

**Step 2: Update credentials** in `sm-shop/src/main/resources/profiles/local/database.properties`
```properties
db.jdbcUrl=jdbc:mysql://127.0.0.1:3306/SALESMANAGER?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
db.user=shopizer
db.password=very-long-shopizer-password
```

**Step 3: Build & run**
```bash
cd "/Users/pranjalgupta/local disk/Technogise/shopizer-suite/shopizer"
./mvnw clean install -DskipTests
cd sm-shop
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

---

## Option C — Build a runnable JAR

```bash
cd "/Users/pranjalgupta/local disk/Technogise/shopizer-suite/shopizer"
./mvnw clean package -DskipTests

# Run it (H2 profile)
java -jar sm-shop/target/shopizer.jar --spring.profiles.active=docker
```

---

## Option D — Docker

```bash
cd "/Users/pranjalgupta/local disk/Technogise/shopizer-suite/shopizer"
./mvnw clean package -DskipTests

cd sm-shop
docker build -t shopizer .
docker run -p 8080:8080 shopizer
```

- App: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Build Order

Maven builds modules in this dependency order automatically:
```
sm-core-model → sm-core-modules → sm-core → sm-shop-model → sm-shop
```
Always run `./mvnw` from the **root** `shopizer/` directory, not from a submodule.

---

## Known Issues & Fixes

### 1. `mvn` not found
`mvn` is not installed globally. Use the Maven wrapper instead:
```bash
./mvnw clean package -DskipTests   # instead of: mvn clean package -DskipTests
```

### 2. Docker — `database.properties` not found (FileNotFoundException)
**Error:**
```
java.io.FileNotFoundException: class path resource [database.properties] cannot be opened because it does not exist
```
**Cause:** The `default` Spring profile in `sm-core` expects `database.properties` at the classpath root, but the JAR only bundles it under `profiles/docker/`, `profiles/local/`, etc.

**Fix (already applied):** A copy of the H2 config was placed at:
```
sm-shop/src/main/resources/database.properties
```
This file is now included in the JAR at the classpath root. **Do not delete it.**

If it ever goes missing, recreate it by copying:
```bash
cp sm-shop/src/main/resources/profiles/docker/database.properties \
   sm-shop/src/main/resources/database.properties
```
Then rebuild:
```bash
./mvnw package -DskipTests -pl sm-shop -am
```

### 3. Docker — `mailSender.protocol` placeholder not resolved
**Error:**
```
Could not resolve placeholder 'mailSender.protocol'
```
**Cause:** Running the container with `--spring.profiles.active=docker` activates a profile that has no matching `PropertyPlaceholderConfigurer` in `shopizer-core-config.xml`. The supported profiles in that XML are: `default`, `local`, `gcp`, `cloud`, `mysql`, `firebase`.

**Fix:** Run the Docker container **without** specifying a Spring profile (uses `default`):
```bash
docker run -p 8080:8080 shopizer
```
Not like this:
```bash
# WRONG — causes mailSender error
docker run -p 8080:8080 shopizer java -jar /opt/app/shopizer.jar --spring.profiles.active=docker
```

### 4. MySQL not running (Option B)
**Error:** `MySQL connection refused`

**Fix:** Start MySQL first:
```bash
brew services start mysql
```
Wait a few seconds, then verify:
```bash
mysql -u root -e "SELECT 1;"
```

---

## Common Issues

| Problem | Fix |
|---------|-----|
| `Java version mismatch` | Set `JAVA_HOME` to Java 11 |
| `Port 8080 in use` | Add `-Dserver.port=9090` to the run command |
| `H2 lock error` | Delete `sm-shop/SALESMANAGER.h2.db` and restart |
| `MySQL connection refused` | Ensure MySQL is running and credentials match |
| `OutOfMemoryError` during build | Add `-Xmx2g` to `MAVEN_OPTS` |

---

> **Recommended starting point**: Option D (Docker) — uses H2, no DB setup, runs in a container.
