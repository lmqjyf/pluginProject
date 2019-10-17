package com.bitcoin.juwan.baselibrary;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * FileName：LaunchModelManager
 * Create By：liumengqiang
 * Description：TODO
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class LaunchModelManager {
    /**
     * String : Activity路径名
     * PluginActivity
     */
    private List<PluginActivityItem> activityList = new ArrayList<>();

    private LaunchModelManager() {

    }

    private static final class CreateManager {
        public static LaunchModelManager instance = new LaunchModelManager();
    }

    public static LaunchModelManager getInstance() {
        return CreateManager.instance;
    }

    /**
     * 是否需要跳转到新的Activity
     *
     * @param activity
     * @param launchModel
     * @return
     */
    public boolean checkLaunchModel(ProxyActivity activity, int launchModel, String activityReallyName) {

        //先遍历一遍数组，获取index的值
        int index = -1;
        for (int i = 0; i < activityList.size(); i++) {
            if (activityReallyName.equals(activityList.get(i).getActivityReallyName())) {
                index = i;
            }
        }

        if (index == -1) { //不包含，可直接加入
            Log.e("----:", "不包含" + activityList.size() + " " + activityReallyName);
            activityList.add(new PluginActivityItem(activity, launchModel, activityReallyName));
            return true;
        } else { //包含该Activity，此时需要判断启动模式
            return handleJudpe(index, activity, launchModel, activityReallyName);
        }
    }

    private boolean handleJudpe(int index, ProxyActivity activity, int launchModel, String activityReallyName) {
        switch (launchModel) {
            case 0: { //标准模式
                activityList.add(new PluginActivityItem(activity, launchModel, activityReallyName)); //直接启动
                return true;
            }
            case 1: { //SingleTop模式
                return handleSingleTop(index, activity, launchModel, activityReallyName);
            }
            case 2: { //SingleTask模式
                return handleSingleTask(index);
            }
            case 3: { //SingInstance模式
                return true;
            }
        }
        return true;
    }

    private boolean handleSingleTop(int index, ProxyActivity activity, int launchModel, String activityReallyName) {
        if (index == activityList.size() - 1) { //是在顶层  此时直接复用此Activity，不用跳转到新的Activity
            return false;
        } else {
            activityList.add(new PluginActivityItem(activity, launchModel, activityReallyName)); //直接启动
            return true;
        }
    }

    private boolean handleSingleTask(int index) {
        Log.e("----:", "包含" + index);
        //移除删除index之后的所有元素，并且元素内的Activity执行finish方法
        Iterator<PluginActivityItem> iterator = activityList.iterator();
        int flagIndex = 0;
        while (iterator.hasNext()) {
            PluginActivityItem item = iterator.next();
            if(flagIndex >= index) {
                item.getActivity().finish();
                iterator.remove();
            }
            flagIndex += 1;
        }
        return false;
    }

    public void clearPluginActivity() {
        activityList.clear();
    }
}
