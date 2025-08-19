#!/usr/bin/env bash

TARGET_FILE="build.gradle.kts"

set -euo pipefail

echo "=== Update Project Version ==="

echo "= First, extract the current version ="

CURRENT_VERSION=$(grep -oP 'version\s*=\s*"\K[^"]+' "$TARGET_FILE")

echo "Current version: $CURRENT_VERSION"

echo "= Now, check the version to update ="

if [ "$#" -ne 1 ]; then
  echo "Not enough arguments provided."
  echo "Usage: $0 <new_version>"
  exit 1
fi

TARGET_VERSION="$1"

echo "Target version: $TARGET_VERSION"

if [ "$CURRENT_VERSION" = "$TARGET_VERSION" ]; then
  echo "Version is already up to date."
  exit 0
fi

echo "= Updating version in $TARGET_FILE ="

sed -i "s/version = \"$CURRENT_VERSION\"/version = \"$TARGET_VERSION\"/" "$TARGET_FILE"

echo "Version updated from $CURRENT_VERSION to $TARGET_VERSION in $TARGET_FILE"
echo "=== Version Update Complete ==="
