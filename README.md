# Xposed Module APK Builder

This is a complete Xposed module project that can be built into an APK for installation on Android devices with Xposed Framework.

## Prerequisites

Before building the APK, you need to install:

1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://adoptium.net/
   - Add Java to your system PATH

2. **Android SDK**
   - Download Android Studio: https://developer.android.com/studio
   - Or download command line tools: https://developer.android.com/studio#command-tools
   - Set ANDROID_HOME environment variable to SDK location

## Project Structure

```
XposedModule/
├── app/
│   ├── build.gradle              # App-level build configuration
│   ├── src/main/
│   │   ├── AndroidManifest.xml   # App manifest with Xposed metadata
│   │   ├── java/com/example/xposedmodule/
│   │   │   ├── MainActivity.java  # Main activity (optional UI)
│   │   │   └── MainHook.java      # Xposed hook implementation
│   │   ├── assets/
│   │   │   └── xposed_init       # Points to main hook class
│   │   └── res/                  # Resources (layouts, strings, etc.)
├── build.gradle                  # Project-level build configuration
├── settings.gradle               # Project settings
└── gradlew.bat                   # Gradle wrapper for Windows
```

## Building the APK

### Method 1: Using the batch script (Recommended)
```cmd
build_apk.bat
```

### Method 2: Using Gradle directly
```cmd
gradlew.bat assembleDebug
```

## Installing and Using the Module

1. **Install the APK on your Android device:**
   ```cmd
   adb install app\build\outputs\apk\debug\app-debug.apk
   ```

2. **Enable Xposed Framework:**
   - Install Xposed Framework on your device (requires root)
   - Use LSPosed or EdXposed for newer Android versions

3. **Activate the module:**
   - Open Xposed Installer/LSPosed Manager
   - Find "Xposed Module" in the modules list
   - Enable it
   - Reboot your device

## Module Functionality

This example module includes:

- **SystemUI Hooks**: Hooks into Android SystemUI processes
- **Activity Logging**: Logs when activities are created
- **Basic UI**: Simple interface to confirm module installation

## Customizing the Module

To modify the module behavior, edit `MainHook.java`:

```java
public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // Add your hooks here
        if (lpparam.packageName.equals("com.target.app")) {
            // Hook into specific app
        }
    }
}
```

## Troubleshooting

- **Build fails**: Ensure Java and Android SDK are properly installed
- **Module not detected**: Check that `xposed_init` file contains correct class name
- **Hooks not working**: Verify Xposed Framework is active and module is enabled

## Files Created

After successful build, the APK will be located at:
`app\build\outputs\apk\debug\app-debug.apk`
