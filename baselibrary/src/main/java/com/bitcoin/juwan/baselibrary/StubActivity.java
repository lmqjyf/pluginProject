package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;

public class StubActivity extends Activity {

    private IPluginActivity iPluginActivity;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onStart() {
        if(iPluginActivity != null) {
            iPluginActivity.onStart();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        if(iPluginActivity != null) {
            iPluginActivity.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(iPluginActivity != null) {
            iPluginActivity.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(iPluginActivity != null) {
            iPluginActivity.onStop();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(iPluginActivity != null) {
            iPluginActivity.onDestroy();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityStackManager.getInstance().removeLastActivity();
    }
}
