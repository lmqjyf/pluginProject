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
                startOtherPluginActivity(null, PluginConst.Plugin_1_ApkDex, "com.bitcoin.juwan.pluginmodule.MainActivity", 2);
            }
        });
    }
}
