#!/bin/bash

# Gradle wrapper for Bitrise

# Set Android SDK paths
export ANDROID_HOME=${ANDROID_HOME:-/opt/android-sdk-linux}
export ANDROID_SDK_ROOT=${ANDROID_SDK_ROOT:-/opt/android-sdk-linux}
export PATH="$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH"

# Determine Gradle distribution
GRADLE_VERSION="7.5"
GRADLE_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_HOME="$HOME/.gradle/gradle-${GRADLE_VERSION}"
GRADLE_BIN="$GRADLE_HOME/bin/gradle"

# Download and install Gradle if not present
if [ ! -x "$GRADLE_BIN" ]; then
    echo "Downloading Gradle ${GRADLE_VERSION}..."
    mkdir -p "$GRADLE_HOME"
    curl -sL "$GRADLE_URL" -o /tmp/gradle.zip
    unzip -q /tmp/gradle.zip -d "$HOME/.gradle"
    rm /tmp/gradle.zip
    echo "Gradle ${GRADLE_VERSION} installed"
fi

# Run Gradle
exec "$GRADLE_BIN" "$@"