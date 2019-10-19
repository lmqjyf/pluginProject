package com.bitcoin.juwan.hostappproject;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bitcoin.juwan.baselibrary.plugin.PluginConst;
import com.bitcoin.juwan.baselibrary.plugin.PluginManager;

import static com.bitcoin.juwan.baselibrary.plugin.PluginConst.Plugin_1_ApkDex;
import static com.bitcoin.juwan.baselibrary.plugin.PluginConst.Plugin_2_ApkDex;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);
        PluginManager.getInstance().loadPluginApk(Plugin_1_ApkDex);
        PluginManager.getInstance().loadPluginApk(Plugin_2_ApkDex);

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
        bundle.putInt(PluginConst.LAUNCH_MODEL, PluginConst.LaunchModel.SINGLE_TASK);
        PluginManager.getInstance().startActivity(MainActivity.this, bundle);
    }
}
