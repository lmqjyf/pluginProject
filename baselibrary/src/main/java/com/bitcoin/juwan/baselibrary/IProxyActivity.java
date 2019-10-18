package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * author: liumengqiang
 * Date : 2019/10/18
 * Description :
 */
public class IProxyActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startHostActivity(Intent intent) {
        boolean checkLaunchModel = ActivityStackManager.getInstance().checkCanStartNewActivity(intent.getExtras());
        if (checkLaunchModel) {
            super.startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent() != null) {
            ActivityStackManager.getInstance().addActivity(this, getIntent().getExtras());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityStackManager.getInstance().removeLastActivity();
    }
}
