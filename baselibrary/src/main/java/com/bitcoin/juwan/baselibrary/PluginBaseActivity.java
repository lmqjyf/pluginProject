package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * FileName：PluginBaseActivity
 * Create By：liumengqiang
 * Description：TODO
 */
public class PluginBaseActivity extends AppCompatActivity implements IActivity {

    protected Activity proxy;

    boolean isPlugin = false;

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

    @Override
    public void startActivity(Intent intent) {
        if(!isPlugin) {
            super.startActivity(intent);
        } else {
            PluginManager.getInstance().startActivity(proxy, intent.getComponent().getClassName(), PluginConst.Plugin_1_ApkDex);
        }
    }
}
