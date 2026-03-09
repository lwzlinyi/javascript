# Android构建配置文件
# 直接使用系统gradle，不依赖gradlew

# 设置Android SDK环境变量
export ANDROID_HOME=/opt/android-sdk-linux
export ANDROID_SDK_ROOT=/opt/android-sdk-linux
export PATH="$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH"

# 检查系统是否安装了gradle
echo "检查系统gradle版本..."
if ! command -v gradle > /dev/null; then
    echo "错误: 系统未安装gradle"
    echo "请手动安装gradle 7.5或以上版本"
    exit 1
fi

gradle -version
echo ""
echo "开始构建Android项目..."
echo "构建命令: gradle assembleRelease"
echo "构建路径: app/build/outputs/apk/release/"
echo ""

# 清理并构建
gradle clean assembleRelease

# 检查构建结果
if [ $? -eq 0 ]; then
    echo "构建成功!"
    echo ""
    echo "APK文件位置:"
    ls -la app/build/outputs/apk/release/
    echo ""
    echo "可执行命令: gradle assembleRelease"
else
    echo "构建失败!"
    exit 1
fi