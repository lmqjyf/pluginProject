package com.liumengqiang.pluginmodule2;

import android.os.Bundle;
import android.view.View;

import com.bitcoin.juwan.baselibrary.PluginBaseActivity;
import com.bitcoin.juwan.baselibrary.plugin.PluginConst;
import com.bitcoin.juwan.baselibrary.plugin.PluginManager;

public class MainActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.go_back_plugin_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(PluginConst.DEX_PATH, PluginConst.Plugin_1_ApkDex);
                bundle.putString(PluginConst.REALLY_ACTIVITY_NAME, "com.bitcoin.juwan.pluginmodule.MainActivity");
                bundle.putInt(PluginConst.LAUNCH_MODEL, PluginConst.LaunchModel.SINGLE_TASK);
                PluginManager.getInstance().startActivity(proxy, bundle);
            }
        });
    }
}
