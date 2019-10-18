package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * author: liumengqiang
 * Date : 2019/10/18
 * Description :
 */
public class IProxyActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startHostActivity(Intent intent) {
        int launchModel = intent.getExtras().getInt(PluginConst.LAUNCH_MODEL);
        String realActivityName = intent.getExtras().getString(PluginConst.REALLY_ACTIVITY_NAME);
        boolean checkLaunchModel = LaunchModelManager.getInstance().checkLaunchModel(launchModel, realActivityName);
        if (!checkLaunchModel) {

        } else {
            super.startActivity(intent);
        }
    }
}
