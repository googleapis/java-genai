#!/bin/bash
# Script to run Java GenAI SDK Interaction examples using Maven.

ORIGINAL_DIR=$(pwd)

declare -A FAILED_EXAMPLES

# --- Install the package if needed ---
if [ -z "$(find target -name 'google-genai-*.jar')" ]; then
  echo "Project JAR not found in target/. Running 'mvn install'..."
  mvn install -Dclirr.skip=true
else
  echo "Project JAR found. Skipping 'mvn install'."
fi

cd examples
echo "Compiling the examples..."
mvn compile
if [ $? -ne 0 ]; then
  echo "----------------------------------------"
  echo "ERROR: Maven compilation failed. Exiting."
  echo "----------------------------------------"
  cd "$ORIGINAL_DIR"
  exit 1
fi

declare -a TARGETS=()
for file in src/main/java/com/google/genai/examples/Interaction*.java; do
  if [ -f "$file" ]; then
    TARGETS+=("$(basename "$file" .java)")
  fi
done

if [ ${#TARGETS[@]} -eq 0 ]; then
  echo "No interaction examples found matching Interaction*.java"
  cd "$ORIGINAL_DIR"
  exit 0
fi

for target in "${TARGETS[@]}"; do
  echo "========================================"
  echo "Running: $target"
  echo "========================================"
  
  mvn exec:java -Dexec.mainClass="com.google.genai.examples.$target"
  
  if [ $? -ne 0 ]; then
    echo "ERROR: $target failed."
    FAILED_EXAMPLES["$target"]="Failed"
  else
    echo "SUCCESS: $target completed."
  fi
done

cd "$ORIGINAL_DIR"

echo "========================================"
if [ ${#FAILED_EXAMPLES[@]} -eq 0 ]; then
  echo "All interaction examples passed! ✅"
  exit 0
else
  echo "The following examples failed: ❌"
  for failed in "${!FAILED_EXAMPLES[@]}"; do
    echo "  - $failed"
  done
  exit 1
fi
