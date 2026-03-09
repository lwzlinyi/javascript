# Project information for GitHub Actions

## Repository URL
When you create the repository, the URL will be:
https://github.com/yourusername/CarHotspotXposed.git

## Workflow URL
After pushing code, the workflow will run at:
https://github.com/yourusername/CarHotspotXposed/actions

## APK Download Location
After successful build, APK will be available at:
https://github.com/yourusername/CarHotspotXposed/actions/artifacts

## Quick Setup Commands

```bash
# 1. Clone this repository
git clone https://github.com/yourusername/CarHotspotXposed.git
cd CarHotspotXposed

# 2. Make gradlew executable (if needed)
chmod +x gradlew

# 3. Build APK locally (optional)
./gradlew assembleRelease

# 4. Find APK
ls -la app/build/outputs/apk/release/
```

## Build Status Badge
You can add this badge to your README.md:

```markdown
![Build Status](https://github.com/yourusername/CarHotspotXposed/actions/workflows/android.yml/badge.svg)
```

## Troubleshooting

### 常见问题
1. **Gradle下载慢** - 检查网络连接
2. **构建失败** - 检查Android SDK配置
3. **权限错误** - 检查gradlew执行权限
4. **Xposed API找不到** - 检查compileOnly依赖

### 解决方法
1. **清理缓存**: `./gradlew clean`
2. **重新构建**: `./gradlew assembleRelease`
3. **检查SDK**: `sdkmanager --list`
4. **查看日志**: GitHub Actions日志

## 开发说明

### 代码结构
- `app/src/main/java/com/example/hotspot/HotspotReceiver.java` - 核心接收器
- `app/src/main/AndroidManifest.xml` - 应用配置
- `app/src/main/assets/xposed_init` - Xposed模块入口
- `app/src/main/assets/module.prop` - 模块配置

### 修改配置
- 修改蓝牙MAC地址：修改`HotspotReceiver.java`中的TARGET_BLUETOOTH_MAC
- 修改时间窗口：修改START_HOUR和END_HOUR
- 修改热点配置：修改HOTSPOT_SSID和HOTSPOT_PASSWORD

### 测试
1. 本地构建：./gradlew assembleDebug
2. 安装到设备：adb install app/build/outputs/apk/debug/app-debug.apk
3. 测试功能：连接目标蓝牙设备

## 依赖库

- AndroidX AppCompat: 1.3.0
- Xposed API: 82

## 版本信息

- 编译SDK：30
- 构建工具：30.0.3
- 最小SDK：14
- 目标SDK：30

## 授权

MIT License (开源)