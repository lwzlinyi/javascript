# 项目打包说明

## 项目结构

```
SimpleHotspot/
├── .github/
│   └── workflows/
│       └── android.yml
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

### 步骤1：创建GitHub仓库
1. 登录GitHub (https://github.com)
2. 点击右上角 "+" → "New repository"
3. 仓库名：`CarHotspotXposed`
4. 选择Public或Private
5. 不要初始化README、.gitignore或license
6. 点击"Create repository"

### 步骤2：上传项目文件
**方法A：使用Git命令行**
```bash
cd /root/.openclaw/workspace/SimpleHotspot
git init
git add .
git commit -m "Initial commit - Car Hotspot Xposed Module"
git branch -M main
git remote add origin https://github.com/yourusername/CarHotspotXposed.git
git push -u origin main
```

**方法B：使用GitHub网页**
1. 进入你的仓库页面
2. 点击"upload an existing file"
3. 拖拽整个`SimpleHotspot`文件夹
4. 点击"Commit changes"

### 步骤3：GitHub Actions自动构建
- 推送代码后，Actions会自动运行
- 在GitHub仓库的"Actions"标签页查看构建状态
- 构建完成后，Artifacts区域会显示APK文件

### 步骤4：下载APK
1. 进入GitHub仓库 → Actions
2. 选择最新的工作流运行
3. 点击"Artifacts"部分
4. 下载`CarHotspotAPK`压缩包
5. 解压得到`app-release-unsigned.apk`

### 步骤5：安装使用
1. 安装Xposed框架
2. 安装APK
3. 在Xposed Installer中激活模块
4. 重启设备
5. 连接蓝牙设备BC:82:5D:A4:D3:E1，8:00-20:00自动开启热点

## 注意事项

- 确保设备已root
- 确保Xposed框架已安装
- 确保目标蓝牙设备已配对
- 确保时间窗口正确
- APK未签名，生产环境需要签名