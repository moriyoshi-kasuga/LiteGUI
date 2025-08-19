#!/usr/bin/env bash

TARGET_FILE="build.gradle.kts"
README_FILE="README.md"

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
echo "=== Successfully updated the project version ==="

echo "= Now, update the version in the README.md ="

sed -i "s/v$CURRENT_VERSION/v$TARGET_VERSION/" "$README_FILE"

echo "Version updated from v$CURRENT_VERSION to v$TARGET_VERSION in $README_FILE"

echo "=== Successfully updated the README.md version ==="

echo "= Finally, commit the changes and create a tag ="

git add "$TARGET_FILE" "$README_FILE"
git commit -m "chore: update version to $TARGET_VERSION"
git tag "v$TARGET_VERSION"
echo "Changes committed and tag v$TARGET_VERSION created."

echo "=== Successfully updated the project version and created a tag ==="

echo "= Now, push the changes and the tag (optional) ="

read -rp "Do you want to push the changes and the tag? (y/n): " push_choice

if [[ "$push_choice" == "y" || "$push_choice" == "Y" ]]; then
  git push origin main
  git push origin "v$TARGET_VERSION"
  echo "Changes and tag pushed to the remote repository."
else
  echo "Changes and tag not pushed. You can push them later using 'git push origin main' and 'git push origin v$TARGET_VERSION'."
fi

echo "=== Update process completed successfully ==="
