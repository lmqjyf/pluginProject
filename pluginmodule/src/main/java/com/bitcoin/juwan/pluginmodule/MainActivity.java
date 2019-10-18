package com.bitcoin.juwan.pluginmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bitcoin.juwan.baselibrary.PluginBaseActivity;

public class MainActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.plugin_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpt();
            }
        });
    }

    private void jumpt() {
        startActivity(new Intent(proxy, SecondActivity.class));
    }
}
