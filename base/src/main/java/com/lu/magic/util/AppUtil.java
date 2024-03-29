package com.lu.magic.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import com.lu.magic.util.log.LogUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @Author: Lu
 * Date: 2022/02/21
 * Description:
 */
public class AppUtil {
    private static Context sContext;

    private AppUtil() {

    }

    public static void attachContext(Context ctx) {
        Context context = ctx.getApplicationContext();
        if (context == null) {
            sContext = ctx;
        } else {
            sContext = context;
        }
    }

    public static void setContext(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        if (sContext == null) {
            sContext = getApplicationByReflect();
        }
        return sContext;
    }

    public static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi") Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("can't find application from ActivityThread!!!");
            }
            return (Application) app;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSHA1(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1) {
                    hexString.append("0");
                }
                if (i >= publicKey.length - 1) {
                    hexString.append(appendString);
                } else {
                    hexString.append(appendString + ":");
                }
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getVersionName() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return "";
    }

    public static int getVersionCode() {
        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            LogUtil.e(e);
            return -1;
        }
    }

    private static Resources getResources() {
        return getContext().getResources();
    }

    public static ClassLoader getClassLoader() {
        return getContext().getClassLoader();
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static int getViewId(String idName) {
        return getResources().getIdentifier(idName, "id", getPackageName());
    }

    public static int getLayoutId(String idName) {
        return getResources().getIdentifier(idName, "layout", getPackageName());
    }

    public static int getStringId(String idName) {
        return getResources().getIdentifier(idName, "string", getPackageName());
    }

    public static int getDrawableId(String idName) {
        return getResources().getIdentifier(idName, "drawable", getPackageName());
    }

    public static int getDimenId(String idName) {
        return getResources().getIdentifier(idName, "dimen", getPackageName());
    }

    public static int getMipmapId(String idName) {
        return getResources().getIdentifier(idName, "mipmap", getPackageName());
    }
}
