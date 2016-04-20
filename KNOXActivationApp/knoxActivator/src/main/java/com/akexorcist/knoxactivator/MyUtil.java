package com.akexorcist.knoxactivator;

import android.app.enterprise.EnterpriseDeviceManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 2/19/2016 AD.
 */
public class MyUtil {
    @SuppressWarnings("WrongConstant")
    public static boolean isMDMAPISupported(Context context, MyConstant.MDMVersion requiredVersion) {
        EnterpriseDeviceManager mEDM = (EnterpriseDeviceManager) context.getSystemService(EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
        return !(requiredVersion != null && mEDM.getEnterpriseSdkVer().ordinal() < requiredVersion.ordinal());
    }

    public static void setELMDevice(Context context, boolean isElm) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyConstant.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(MyConstant.ELM, isElm);
        editor.apply();
    }

    public static void setAdminDevice(Context context, boolean isAdmin) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyConstant.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(MyConstant.ADMIN, isAdmin);
        editor.apply();
    }

    public static String getDoSelfUninstall(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MyConstant.DO_SELF_UNINSTALL, null);
    }

    public static boolean isELMDevice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.ELM, false);
    }

    public static boolean isAdminDevice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MyConstant.MY_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(MyConstant.ADMIN, false);
    }

    public static List<String> getNonSystemAppList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> appList = packageManager.getInstalledPackages(0);

        List<String> installedAppList = new ArrayList<>();
        for (PackageInfo packageInfo : appList) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageInfo.packageName, 0);
                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    // System Apps
                } else {
                    installedAppList.add(packageInfo.packageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return installedAppList;
    }

    public static List<String> getAllAppList(Context context) {
        List<String> appList = new ArrayList<>();
        List<PackageInfo> applicationInfoList = context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo : applicationInfoList) {
            appList.add(packageInfo.packageName);
        }
        return appList;
    }
}
