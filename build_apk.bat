@echo off
echo Building Xposed Module APK...
echo.

REM Check if Java is available
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 8 or higher and add it to your PATH
    echo Download from: https://adoptium.net/
    pause
    exit /b 1
)

REM Check if Android SDK is available
if not defined ANDROID_HOME (
    echo WARNING: ANDROID_HOME is not set
    echo Please set ANDROID_HOME to your Android SDK location
    echo Example: set ANDROID_HOME=C:\Android\Sdk
    pause
)

REM Try to build the APK
echo Attempting to build APK...
call gradlew.bat assembleDebug

if %errorlevel% equ 0 (
    echo.
    echo SUCCESS: APK built successfully!
    echo APK location: app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo To install the APK:
    echo 1. Enable USB Debugging on your Android device
    echo 2. Connect your device via USB
    echo 3. Run: adb install app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo To use as Xposed module:
    echo 1. Install Xposed Framework on your device
    echo 2. Install this APK
    echo 3. Enable the module in Xposed Installer
    echo 4. Reboot your device
) else (
    echo.
    echo ERROR: Build failed
    echo Please check the error messages above
)

pause
