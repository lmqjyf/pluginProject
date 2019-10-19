package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bitcoin.juwan.baselibrary.plugin.IPluginActivity;
import com.bitcoin.juwan.baselibrary.plugin.PluginConst;
import com.bitcoin.juwan.baselibrary.plugin.PluginManager;

/**
 * FileName：PluginBaseActivity
 * Create By：liumengqiang
 * Description：所有的插件Activity都要继承该类
 */
public class PluginBaseActivity extends AppCompatActivity implements IPluginActivity {

    protected Activity proxy;

    boolean isPlugin = false; // 是否是插件运行

    private int launchModel = -1;

    @Override
    public void attach(Activity activity) {
        proxy = activity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if(saveInstanceState != null) {
            isPlugin = saveInstanceState.getBoolean(PluginConst.isPlugin, false);
        }
        if (!isPlugin) {
            super.onCreate(saveInstanceState);
            proxy = this;
        } else {

        }
    }


    @Override
    public void onStart() {
        if (!isPlugin) {
            super.onStart();
        } else {

        }
    }

    @Override
    public void onResume() {
        if (!isPlugin) {
            super.onResume();
        } else {

        }
    }

    @Override
    public void onRestart() {
        if (!isPlugin) {
            super.onRestart();
        } else {

        }
    }

    @Override
    public void onPause() {
        if (!isPlugin) {
            super.onPause();
        } else {

        }
    }

    @Override
    public void onStop() {
        if (!isPlugin) {
            super.onStop();
        } else {

        }
    }

    @Override
    public void onDestroy() {
        if (!isPlugin) {
            super.onDestroy();
        } else {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    //下面是几个生命周期之外的重写函数
    @Override
    public void setContentView(int layoutResID) {//设置contentView分情况
        if (!isPlugin) {
            super.setContentView(layoutResID);
        } else {
            proxy.setContentView(layoutResID);
        }
    }
    @Override
    public View findViewById(int id) {
        if (!isPlugin) {
            return super.findViewById(id);
        } else {
            return proxy.findViewById(id);
        }
    }

    /**
     * 同一个插件之间跳转
     * @param intent
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void startActivity(Intent intent) {
        if(!isPlugin) {
            super.startActivity(intent);
        } else {
            Bundle bundle = setBundleData(intent.getExtras(), PluginConst.Plugin_1_ApkDex, intent.getComponent().getClassName(), PluginConst.LaunchModel.SINGLE_TASK);
            PluginManager.getInstance().startActivity(proxy, bundle);
        }
    }

    /**
     * 一个插件跳Activity转到另一个插件Activity
     * @param bundleParam
     * @param dexPath
     * @param reallyActivityName
     * @param launchModel
     */
    public  void startOtherPluginActivity(Bundle bundleParam, String dexPath, String reallyActivityName, int launchModel) {
        Bundle bundle = setBundleData(bundleParam, dexPath, reallyActivityName, launchModel);
        PluginManager.getInstance().startActivity(proxy, bundle);
    }

    /**
     * @param bundleParam
     * @param dexPath
     * @param reallyActivityName
     * @param launchModel
     * @return
     */
    private Bundle setBundleData(Bundle bundleParam, String dexPath, String reallyActivityName, int launchModel) {
        Bundle bundle = bundleParam == null ? new Bundle() : bundleParam;
        bundle.putString(PluginConst.DEX_PATH, dexPath);
        bundle.putString(PluginConst.REALLY_ACTIVITY_NAME, reallyActivityName);
        bundle.putInt(PluginConst.LAUNCH_MODEL, launchModel);
        return  bundle;
    }
}
