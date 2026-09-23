# Invisibles Widgets

一个极简 Android 应用，用完全透明的小组件为桌面壁纸留出一块无遮挡的空间。

## 使用方式

1. 安装并打开应用，点击「添加透明小组件」。按钮会显示请求结果；如果当前桌面不弹出确认页，可点击「返回桌面添加」，长按桌面空白处，从「小组件」列表里选择「透明占位」。
2. 将小组件放到想留白的桌面页。长按小组件并拖动边缘来调整大小；可用范围由桌面启动器决定。
3. 把该页上的应用图标移到其他页面。桌面上的状态栏、导航栏、页面指示器和启动器自带的 Dock 不属于小组件覆盖范围。
4. 透明小组件不会打开应用或拦截桌面手势。状态栏和 Dock 由当前桌面启动器控制；如果启动器提供隐藏选项，可在其设置中关闭。
5. 如果希望按 Home 后显示这张壁纸页，在桌面设置中选择「默认主屏幕」或类似选项。并非所有桌面启动器都提供更改主屏页的功能。

## 构建

需要 JDK 17 或更高版本、Android SDK Platform 36、Android Build Tools 36.0.0。

```sh
./gradlew assembleDebug
```

APK 输出到 `app/build/outputs/apk/debug/app-debug.apk`。Release 构建启用了代码压缩与资源收缩；GitHub Releases 的安装包使用固定签名密钥签名，支持后续版本覆盖安装：

```sh
./gradlew assembleRelease
```

在本地构建可分发的 Release 安装包时，需要配置 `ANDROID_KEYSTORE_PATH` 和 `ANDROID_KEYSTORE_PASSWORD` 环境变量指向项目的签名密钥。未配置时只会生成未签名的 Release APK，不能直接安装。

## 下载

从 [GitHub Releases](https://github.com/Nanchen913/Invisibles-Widgets/releases/latest) 下载最新版本附带的 APK，下载后在 Android 手机上打开并按提示安装。每次推送到 `main` 也会在 GitHub Actions 中生成可下载 90 天的 APK 构建产物。

本项目不请求网络、存储、通知或其他运行时权限；不包含后台服务、分析或广告。小组件不设置周期刷新，由桌面启动器托管。
