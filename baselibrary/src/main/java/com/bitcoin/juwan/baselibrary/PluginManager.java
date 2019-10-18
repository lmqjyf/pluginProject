package com.bitcoin.juwan.baselibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

import dalvik.system.DexClassLoader;

/**
 * FileName：PluginManager
 * Create By：liumengqiang
 * Description：插件管理
 */
public class PluginManager {
    private volatile static PluginManager instance = null;

    /**
     * String : 插件的DexPath路径
     * PlugItem：详见类描述
     */
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

    /**
     * 加载插件APK
     * @param apkPath APK或者jar或者dex的目录
     */
    public void loadPluginApk(String apkPath) {
        //Dex优化后的缓存目录
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

    /**
     * 获取插件DexPath对应的相关信息{@link #loadPluginApk(String)}
     * @param dexPath
     * @return
     */
    public PluginItem getPluginItem(String dexPath) {
        return pluginItemHashMap.get(dexPath);
    }

    /**
     * 适用：宿主到插件，插件到插件（注：插件到宿主跳转不适用）
     * @param context
     * @param bundle
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startActivity(Context context, Bundle bundle) {
        boolean isCanJump = ActivityStackManager.getInstance().checkCanStartNewActivity(bundle);


        Log.e("-----", "" + isCanJump);
        if (isCanJump) {
            Intent intent = new Intent(context, ProxyActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
