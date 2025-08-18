#!/usr/bin/env bash

echo "=== Build and Copy Plugin ==="

./gradlew plugin:buildAndCopy

echo "=== Run Server ==="

# if error occurs during execution, run `./gradlew cleanPaperCache` to clear the cache
./gradlew runServer
