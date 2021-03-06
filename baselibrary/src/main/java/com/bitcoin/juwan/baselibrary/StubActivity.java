package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;

/**
 * FileName：StubActivity
 * Create By：liumengqiang
 * Description：占位Activity（跳转到插件中的Activity，实际操作者都是这个占位Activity）
 */
import com.bitcoin.juwan.baselibrary.launchmodel.ActivityStackManager;

public class StubActivity extends Activity {

    private StubActivityImpl stubActivityImp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        stubActivityImp = new StubActivityImpl();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stubActivityImp.handleIntent(getIntent(), this);
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
        }
        return stubActivityImp.getClassLoader() == null ? super.getClassLoader() : stubActivityImp.getClassLoader();
    }

    @Override
    public Resources getResources() {
        if(getIntent() == null) {
            return super.getResources();
        }
        return stubActivityImp.getResources() == null ? super.getResources() : stubActivityImp.getResources();
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
