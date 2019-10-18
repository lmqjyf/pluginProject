package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;

public class ProxyActivity extends IProxyActivity {

    private IActivity iActivity;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String reallyActivity = getIntent().getExtras().getString(PluginConst.REALLY_ACTIVITY_NAME);
        String dexPath = getIntent().getExtras().getString(PluginConst.DEX_PATH);
        int launchModel = getIntent().getExtras().getInt(PluginConst.LAUNCH_MODEL, -1);

        LaunchModelManager.getInstance().addActivity(ProxyActivity.this, launchModel, reallyActivity);


        try {
            Class<?> aClass = PluginManager.getInstance().getPluginItem(dexPath).getClassLoader().loadClass(reallyActivity);
            Object o = aClass.newInstance();
            if(o instanceof IActivity) {
                iActivity = (IActivity) o;
                Bundle bundle = new Bundle();
                bundle.putBoolean(PluginConst.isPlugin, true);
                iActivity.attach(this);
                iActivity.onCreate(bundle);
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
    protected void onStart() {
        if(iActivity != null) {
            iActivity.onStart();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        if(iActivity != null) {
            iActivity.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(iActivity != null) {
            iActivity.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(iActivity != null) {
            iActivity.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(iActivity != null) {
            iActivity.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public ClassLoader getClassLoader() {
        if(getIntent() == null) {
            return  super.getClassLoader();
        } else {
            String stringExtra = getIntent().getExtras().getString(PluginConst.DEX_PATH);
            if(stringExtra == null) {
                return super.getClassLoader();
            } else{
                return PluginManager.getInstance().getPluginItem(stringExtra).getClassLoader();
            }
        }
    }

    @Override
    public Resources getResources() {
        if(getIntent() == null) {
            return super.getResources();
        }
        String stringExtra = getIntent().getExtras().getString(PluginConst.DEX_PATH);
        if(stringExtra == null) {
             return super.getResources();
        } else{
            return PluginManager.getInstance().getPluginItem(stringExtra).getResources();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LaunchModelManager.getInstance().removeLastActivity();
    }
}
