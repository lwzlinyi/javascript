#!/bin/bash
# Gradle wrapper script
export ANDROID_HOME=${ANDROID_HOME:-/tmp/android-sdk}
export PATH=$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH
gradle "$@"