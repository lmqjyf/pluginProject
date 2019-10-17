package com.bitcoin.juwan.baselibrary;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * FileName：PluginActivityItem
 * Create By：liumengqiang
 * Description：TODO
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PluginActivityItem {

    private ProxyActivity activity;

    private int launchModel;

    private String activityReallyName;

    public String getActivityReallyName() {
        return activityReallyName;
    }

    public void setActivityReallyName(String activityReallyName) {
        this.activityReallyName = activityReallyName;
    }

    public PluginActivityItem(ProxyActivity activity, int launchModel, String activityReallyName) {
        this.activity = activity;
        this.launchModel = launchModel;
        this.activityReallyName = activityReallyName;
    }

    public ProxyActivity getActivity() {
        return activity;
    }

    public void setActivity(ProxyActivity activity) {
        this.activity = activity;
    }

    public int getLaunchModel() {
        return launchModel;
    }

    public void setLaunchModel(int launchModel) {
        this.launchModel = launchModel;
    }
}
