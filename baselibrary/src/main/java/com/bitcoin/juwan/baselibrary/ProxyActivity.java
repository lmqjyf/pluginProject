package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ProxyActivity extends Activity {

    private IActivity iActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String reallyActivity = getIntent().getStringExtra(PluginConst.REALLY_ACTIVITY_NAME);
        String dexPath = getIntent().getStringExtra(PluginConst.DEX_PATH);
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
            String stringExtra = getIntent().getStringExtra(PluginConst.DEX_PATH);
            if(stringExtra == null) {
                return super.getClassLoader();
            } else{
                return PluginManager.getInstance().getPluginItem(stringExtra).getClassLoader();
            }
        }
//        return PluginManager.getInstance().getDexClassLoader() != null ?
//                PluginManager.getInstance().getDexClassLoader() :
//                super.getClassLoader();
    }

    @Override
    public Resources getResources() {
        if(getIntent() == null) {
            return super.getResources();
        }
        String stringExtra = getIntent().getStringExtra(PluginConst.DEX_PATH);
        if(stringExtra == null) {
             return super.getResources();
        } else{
            return PluginManager.getInstance().getPluginItem(stringExtra).getResources();
        }
//        return PluginManager.getInstance().getResources() != null ?
//                PluginManager.getInstance().getResources() :
//                super.getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
