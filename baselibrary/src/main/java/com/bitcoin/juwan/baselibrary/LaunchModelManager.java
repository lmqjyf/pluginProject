package com.bitcoin.juwan.baselibrary;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

import java.util.List;

/**
 * FileName：LaunchModelManager
 * Create By：liumengqiang
 * Description：TODO
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LaunchModelManager {
    /**
     * String : 启动模式
     * PluginActivity
     */
    private ArrayMap<String, PluginBaseActivity> activityList = new ArrayMap<>();

    private LaunchModelManager() {

    }

    private static final class CreateManager {
         public static LaunchModelManager instance = new LaunchModelManager();
    }

    public static LaunchModelManager getInstance() {
        return CreateManager.instance;
    }

    public void checkLaunchModel(String activityPath) {

    }

    public void startActivity(PluginBaseActivity activity) {
        String className = activity.getComponentName().getClassName();
    }
}
