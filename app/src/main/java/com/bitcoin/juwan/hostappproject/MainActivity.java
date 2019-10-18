package com.bitcoin.juwan.hostappproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bitcoin.juwan.baselibrary.ActivityStackManager;
import com.bitcoin.juwan.baselibrary.BaseActivity;
import com.bitcoin.juwan.baselibrary.PluginConst;
import com.bitcoin.juwan.baselibrary.PluginManager;

import static com.bitcoin.juwan.baselibrary.PluginConst.Plugin_1_ApkDex;

public class MainActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);
        PluginManager.getInstance().loadPluginApk(Plugin_1_ApkDex);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPluginActivity();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startPluginActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(PluginConst.DEX_PATH, PluginConst.Plugin_1_ApkDex);
        bundle.putString(PluginConst.REALLY_ACTIVITY_NAME, "com.bitcoin.juwan.pluginmodule.MainActivity");
        bundle.putInt(PluginConst.LAUNCH_MODEL, 2);
        PluginManager.getInstance().startActivity(MainActivity.this, bundle);
    }
}
