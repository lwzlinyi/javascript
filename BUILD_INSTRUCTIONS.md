# 项目打包说明

## 项目结构

```
SimpleHotspot/
├── app/
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/
│           │   └── com/example/hotspot/
│           │       └── HotspotReceiver.java
│           ├── res/
│           │   └── values/
│           │       └── strings.xml
│           └── assets/
│               ├── module.prop
│               └── xposed_init
├── build.gradle
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
├── gradlew
├── settings.gradle
└── README.md
```

## 使用步骤

### 步骤1：在线编译
1. 注册在线编译平台（如Appcircle.io、GitHub Actions等）
2. 上传项目文件
3. 配置构建脚本
4. 开始构建

### 步骤2：获取APK
1. 下载构建好的APK文件
2. 安装到设备

### 步骤3：激活模块
1. 安装Xposed框架
2. 在Xposed Installer中激活本模块
3. 重启设备

## 注意事项

- 确保设备已root
- 确保Xposed框架已安装
- 确保目标蓝牙设备已配对
- 确保时间窗口正确