package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/**
 * author: liumengqiang
 * Date : 2019/10/18
 * Description :
 */
public class StubActivityImp implements IPluginActivity{

    private IPluginActivity iPluginActivity;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public StubActivityImp(Intent intent, Activity activity) {
        handleIntent(intent, activity);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleIntent(Intent intent, Activity activity) {
        String reallyActivity = intent.getExtras().getString(PluginConst.REALLY_ACTIVITY_NAME);
        String dexPath = intent.getExtras().getString(PluginConst.DEX_PATH);
        ActivityStackManager.getInstance().addActivity(activity, intent.getExtras());
        try {
            Class<?> aClass = PluginManager.getInstance().getPluginItem(dexPath).getClassLoader().loadClass(reallyActivity);
            Object o = aClass.newInstance();
            if(o instanceof IPluginActivity) {
                iPluginActivity = (IPluginActivity) o;
                Bundle bundle = new Bundle();
                //设置是插件跳转
                bundle.putBoolean(PluginConst.isPlugin, true);
                iPluginActivity.attach(activity);
                iPluginActivity.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void attach(Activity activity) {

    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {
        if(iPluginActivity != null) {
            iPluginActivity.onStart();
        }
    }

    @Override
    public void onResume() {
        if(iPluginActivity != null) {
            iPluginActivity.onResume();
        }
    }

    @Override
    public void onRestart() {
        if(iPluginActivity != null) {
            iPluginActivity.onRestart();
        }
    }

    @Override
    public void onPause() {
        if(iPluginActivity != null) {
            iPluginActivity.onPause();
        }
    }

    @Override
    public void onStop() {
        if(iPluginActivity != null) {
            iPluginActivity.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(iPluginActivity != null) {
            iPluginActivity.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}