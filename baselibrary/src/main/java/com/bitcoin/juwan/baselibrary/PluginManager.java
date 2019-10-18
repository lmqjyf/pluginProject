package com.bitcoin.juwan.baselibrary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import dalvik.system.DexClassLoader;

/**
 * FileName：PluginManager
 * Create By：liumengqiang
 * Description：TODO
 */
public class PluginManager {
    private volatile static PluginManager instance = null;

    private HashMap<String, PluginItem> pluginItemHashMap;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }

    private Context context;

    public void init(Context context) {
        this.context = context.getApplicationContext();
        pluginItemHashMap = new HashMap<>();
    }

    public void loadPluginApk(String apkPath) {
        //取出更改路径下的Activity
        File odexFile = context.getDir("odex", Context.MODE_PRIVATE);
        //创建DexClassLoader加载器
        DexClassLoader dexClassLoader = new DexClassLoader(apkPath, odexFile.getAbsolutePath(), null, context.getClassLoader());
        //创建AssetManager，然后创建Resources
        Resources resources = null;
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            resources = new Resources(assetManager,
                    context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        pluginItemHashMap.put(apkPath, new PluginItem(apkPath, dexClassLoader, resources));
    }

    public PluginItem getPluginItem(String dexPath) {
        return pluginItemHashMap.get(dexPath);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startActivity(Context context, Bundle bundle) {
        String reallyActivityName = bundle.getString(PluginConst.REALLY_ACTIVITY_NAME);
        int launchModel = bundle.getInt(PluginConst.LAUNCH_MODEL, -1);
        boolean isCanJump = LaunchModelManager.getInstance().checkLaunchModel(launchModel, reallyActivityName);
        Log.e("-----:", reallyActivityName + " " + launchModel + " " + isCanJump);
        if(!isCanJump) {
            return;
        }
        Intent intent = new Intent(context, ProxyActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
