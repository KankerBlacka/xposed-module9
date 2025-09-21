package com.example.xposedmodule;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MainHook implements IXposedHookLoadPackage {
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // Log that our module is loaded
        XposedBridge.log("XposedModule loaded for package: " + lpparam.packageName);
        
        // Example: Hook into SystemUI to modify status bar
        if (lpparam.packageName.equals("com.android.systemui")) {
            hookSystemUI(lpparam);
        }
        
        // Example: Hook into any app to log method calls
        hookGeneralMethods(lpparam);
    }
    
    private void hookSystemUI(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            // Hook into StatusBarManagerService
            Class<?> statusBarClass = XposedHelpers.findClass("com.android.systemui.statusbar.phone.StatusBar", lpparam.classLoader);
            
            XposedHelpers.findAndHookMethod(statusBarClass, "onCreate", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("SystemUI StatusBar onCreate called");
                    // You can add your modifications here
                }
            });
            
        } catch (Exception e) {
            XposedBridge.log("Error hooking SystemUI: " + e.getMessage());
        }
    }
    
    private void hookGeneralMethods(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            // Hook into Activity.onCreate to log activity launches
            XposedHelpers.findAndHookMethod("android.app.Activity", lpparam.classLoader, "onCreate", 
                android.os.Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    android.app.Activity activity = (android.app.Activity) param.thisObject;
                    XposedBridge.log("Activity launched: " + activity.getClass().getSimpleName());
                }
            });
            
        } catch (Exception e) {
            XposedBridge.log("Error hooking general methods: " + e.getMessage());
        }
    }
}
