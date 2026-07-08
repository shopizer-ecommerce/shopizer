# MySQL Docker setup

This guide explains how to run a local MySQL database for Shopizer development.
The default Shopizer configuration remains unchanged and uses H2 unless you
choose to point the application at MySQL.

## Prerequisites

Install these tools before you start:

- Docker
- Docker Compose

## Create a local environment file

From the repository root, copy the example file:

```bash
cp .env.example .env
```

The example values are safe for local development only. Do not commit your
`.env` file.

## Start MySQL

Start the MySQL 8.0 service:

```bash
docker compose --env-file .env up -d
```

Docker Compose creates a `mysql` service and stores the database files in a
persistent Docker volume.

## Check status

Check whether the container is running and healthy:

```bash
docker compose ps
```

If you need more detail, inspect the MySQL logs:

```bash
docker compose logs mysql
```

## Stop MySQL

Stop and remove the container:

```bash
docker compose down
```

The database data remains available because it is stored in a Docker volume.

## Remove MySQL data

To stop the container and remove the database volume:

```bash
docker compose down -v
```

Use this when you want a fresh local database.

## Connect Shopizer to MySQL

The main `sm-shop/src/main/resources/database.properties` file uses H2 by
default. To run Shopizer against this local MySQL container, use datasource
settings that match your `.env` file.

With the default `.env.example` values, the MySQL settings are:

```properties
db.jdbcUrl=jdbc:mysql://127.0.0.1:3306/SALESMANAGER?autoReconnect=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
db.user=shopizer
db.password=shopizer
db.driverClass=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
db.preferredTestQuery=SELECT 1
db.schema=SALESMANAGER
hibernate.hbm2ddl.auto=update
```

If you change `MYSQL_PORT`, `MYSQL_DATABASE`, `MYSQL_USER`, or
`MYSQL_PASSWORD` in `.env`, update the datasource URL, username, and password
to match.

## Troubleshooting

### Port 3306 is already in use

Change `MYSQL_PORT` in `.env` to another local port, for example:

```env
MYSQL_PORT=3307
```

Restart MySQL:

```bash
docker compose --env-file .env up -d
```

Then update the Shopizer JDBC URL to use the same port:

```properties
db.jdbcUrl=jdbc:mysql://127.0.0.1:3307/SALESMANAGER?autoReconnect=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8
```

### Container is unhealthy

Check the status and logs:

```bash
docker compose ps
docker compose logs mysql
```

MySQL can take a short time to initialize on the first start. If it remains
unhealthy, confirm that the values in `.env` are present and that the configured
port is available.

### Wrong credentials

Make sure the Shopizer datasource username and password match `MYSQL_USER` and
`MYSQL_PASSWORD` from `.env`.

If you changed credentials after the first container start, the old credentials
may still exist in the Docker volume. Remove the volume and start again:

```bash
docker compose down -v
docker compose --env-file .env up -d
```

### MySQL data persists

`docker compose down` removes the container but keeps the MySQL data volume.
This is expected. Use this command only when you want to delete the local
database data:

```bash
docker compose down -v
```
