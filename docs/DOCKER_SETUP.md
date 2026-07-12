# Running Shopizer with Docker Compose (MySQL)

This guide describes how to run the complete Shopizer stack locally with a
single command using Docker Compose:

| Service         | Image                                   | Default URL              |
|-----------------|------------------------------------------|--------------------------|
| MySQL 8         | `mysql:8.0`                              | `localhost:3306`         |
| Headless API    | `shopizerecomm/shopizer`                 | http://localhost:8080    |
| Admin UI        | `shopizerecomm/shopizer-admin`           | http://localhost:82      |
| React shop      | `shopizerecomm/shopizer-shop-reactjs`    | http://localhost:80      |

By default the Shopizer backend runs against an embedded H2 database. The
`docker-compose.yml` at the root of this repository instead activates the
`mysql` Spring profile and points the backend at a MySQL 8 container, which
is closer to a production-like environment.

## Prerequisites

- Docker Engine 20.10+ and Docker Compose v2 (`docker compose` command)
- Ports 80, 82, 3306 and 8080 available (all configurable, see below)

## Quick start

```
cp .env.example .env
# edit .env and set your own passwords
docker compose up -d
```

The first startup takes a few minutes: MySQL initializes, then the backend
creates the schema in the `SALESMANAGER` database
(`hibernate.hbm2ddl.auto=update`).

Verify everything is up:

```
docker compose ps
docker compose logs -f shopizer
```

Then open:

- Swagger / headless API: http://localhost:8080/swagger-ui.html
- Admin UI: http://localhost:82 (default credentials: `admin@shopizer.com` / `password`)
- React sample shop: http://localhost:80

## How the MySQL integration works

The backend image runs `java -jar /opt/app/shopizer.jar`. The compose file
overrides that command to:

1. Activate the `mysql` Spring profile (`-Dspring.profiles.active=mysql`),
   which makes Shopizer load `profiles/mysql/database.properties` and the
   MySQL JDBC driver/Hibernate dialect configured there.
2. Supply the connection settings as JVM system properties
   (`-Ddb.jdbcUrl`, `-Ddb.user`, `-Ddb.password`). These placeholders are
   intentionally left unset in the profile's properties file and are
   resolved from system properties at startup.

The JDBC URL uses the compose service name `mysql` as hostname, so no extra
network configuration is required.

## Configuration

All settings live in `.env` (see `.env.example` for the full list):

- `MYSQL_*` — database name, credentials and host port
- `SHOPIZER_API_PORT`, `SHOPIZER_ADMIN_PORT`, `SHOPIZER_SHOP_PORT` — host ports
- `ADMIN_APP_BASE_URL`, `SHOP_APP_BASE_URL` — the URLs the admin UI and shop
  (running in your browser) use to call the API. If you change
  `SHOPIZER_API_PORT`, update these accordingly.

## Data persistence

MySQL data is stored in the named volume `shopizer-mysql-data` and survives
`docker compose down`. To start from a clean database:

```
docker compose down -v
```

## Troubleshooting

- **Backend restarts / cannot connect to MySQL**: the backend waits for the
  MySQL healthcheck, but on slow machines the first schema creation can take
  a while. Check `docker compose logs shopizer`.
- **Port already in use**: change the corresponding `*_PORT` variable in
  `.env`.
- **Admin or shop cannot reach the API**: make sure `ADMIN_APP_BASE_URL` /
  `SHOP_APP_BASE_URL` point to a URL reachable from your browser (not the
  internal service name).

  Note: the published image's mysql profile does not define elasticsearch.security.password, so the compose file passes -Delasticsearch.security.password=NO. The value is unused because elasticsearch.security.enabled=false, but without it the API fails at startup with "Could not resolve placeholder 'elasticsearch.security.password'".

## Building the backend image locally (optional)

To run your own build instead of the published image:

```
./mvnw clean install -DskipTests
cd sm-shop
docker build -t shopizer:local .
```

Then replace `image: shopizerecomm/shopizer:latest` with `image: shopizer:local`
in `docker-compose.yml`.
