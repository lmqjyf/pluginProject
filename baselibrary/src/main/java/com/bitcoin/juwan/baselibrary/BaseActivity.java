package com.bitcoin.juwan.baselibrary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

/**
 * FileName：BaseActivity
 * Create By：liumengqiang
 * Description：TODO
 */
public class BaseActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(getIntent() != null) {
//            if(getIntent().getExtras() == null) {
//                Bundle bundle = new Bundle();
//                bundle.putInt(PluginConst.LAUNCH_MODEL, 0);
//                bundle.putString(PluginConst.REALLY_ACTIVITY_NAME, getComponentName().getClassName());
////                com.bitcoin.juwan.hostappproject.MainActivity
//                ActivityStackManager.getInstance().addActivity(this, bundle);
//            } else {
//                getIntent().getExtras().putInt(PluginConst.LAUNCH_MODEL, 0);
//                getIntent().getExtras().putString(PluginConst.REALLY_ACTIVITY_NAME, getComponentName().getClassName());
//                ActivityStackManager.getInstance().addActivity(this, getIntent().getExtras());
//            }
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        ActivityStackManager.getInstance().removeLastActivity();
    }
}
