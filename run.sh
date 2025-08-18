#!/usr/bin/env bash

set -euo pipefail

echo "=== Build and Copy API Plugin ==="

./gradlew plugin:buildAndCopy

echo "=== Build and Copy Example Plugin ==="

./gradlew example:buildAndCopy

echo "=== Run Server ==="

# if error occurs during execution, run `./gradlew cleanPaperCache` to clear the cache
./gradlew runServer
