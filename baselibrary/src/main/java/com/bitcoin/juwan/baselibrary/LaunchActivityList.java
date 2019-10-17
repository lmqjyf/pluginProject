package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName：LaunchActivityList
 * Create By：liumengqiang
 * Description：TODO
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LaunchActivityList {

    /**
     * 添加
     * @param activityItem
     */
    public void addActivityItem(PluginActivityItem activityItem) {

    }

    /**
     * 删除
     * @param activityItem
     */
    public void remove(PluginActivityItem activityItem) {

    }

    public class PluginActivityItem {
        private PluginBaseActivity activity;

        private int launchModel;

        public PluginActivityItem(PluginBaseActivity activity, int launchModel) {
            this.activity = activity;
            this.launchModel = launchModel;
        }

        public PluginBaseActivity getActivity() {
            return activity;
        }

        public void setActivity(PluginBaseActivity activity) {
            this.activity = activity;
        }

        public int getLaunchModel() {
            return launchModel;
        }

        public void setLaunchModel(int launchModel) {
            this.launchModel = launchModel;
        }
    }
}
