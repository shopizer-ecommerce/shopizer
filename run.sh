#!/bin/bash
set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

log() { echo "[$(date '+%H:%M:%S')] $1"; }

log "Stopping any process on port 8080..."
lsof -ti :8080 | xargs kill -9 2>/dev/null || true

log "Starting MySQL..."
brew services start mysql
sleep 2
log "MySQL started."

log "Setting up database..."
/usr/local/opt/mysql/bin/mysql -u root <<'SQL'
CREATE DATABASE IF NOT EXISTS SALESMANAGER CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'shopizer'@'localhost' IDENTIFIED BY 'shopizer-password';
GRANT ALL ON SALESMANAGER.* TO 'shopizer'@'localhost';
FLUSH PRIVILEGES;
SQL
log "Database ready."

log "Building project (skipping tests)..."
./mvnw clean install -DskipTests 2>&1 | grep -E "BUILD|ERROR|INFO.*Building|INFO.*SUCCESS|INFO.*FAILURE"
log "Build complete."

log "Starting Shopizer on port 8080..."
cd sm-shop
../mvnw spring-boot:run 2>&1 | while IFS= read -r line; do
  echo "[$(date '+%H:%M:%S')] $line"
done
