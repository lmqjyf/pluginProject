package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.Log;

public class StubActivity extends Activity {

    private StubActivityImp stubActivityImp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stubActivityImp = new StubActivityImp(getIntent(), this);
    }


    @Override
    protected void onStart() {
        stubActivityImp.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        stubActivityImp.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        stubActivityImp.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        stubActivityImp.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stubActivityImp.onDestroy();
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
        String className = intent.getComponent().getClassName();
        //此时是需要跳到宿主APP中
        if(!"com.bitcoin.juwan.baselibrary.StubActivity".equals(className)) {
            ActivityStackManager.getInstance().clearActivityList();
        }
        super.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityStackManager.getInstance().removeLastActivity();
    }
}
