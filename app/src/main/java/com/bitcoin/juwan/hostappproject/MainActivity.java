package com.bitcoin.juwan.hostappproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bitcoin.juwan.baselibrary.LaunchModelManager;
import com.bitcoin.juwan.baselibrary.PluginConst;
import com.bitcoin.juwan.baselibrary.PluginManager;

import static com.bitcoin.juwan.baselibrary.PluginConst.Plugin_1_ApkDex;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LaunchModelManager.getInstance().addActivity(MainActivity.this, 0, "com.bitcoin.juwan.hostappproject.MainActivity");


        PluginManager.getInstance().init(this);
        PluginManager.getInstance().loadPluginApk(Plugin_1_ApkDex);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intentStart();
                startPluginActivity();
            }
        });
    }

    private void startPluginActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(PluginConst.DEX_PATH, PluginConst.Plugin_1_ApkDex);
        bundle.putString(PluginConst.REALLY_ACTIVITY_NAME, "com.bitcoin.juwan.pluginmodule.MainActivity");
        bundle.putInt(PluginConst.LAUNCH_MODEL, 1);
        PluginManager.getInstance().startActivity(MainActivity.this, bundle);
    }

    private void intentStart() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.bitcoin.juwan.pluginproject.TestService");
        startService(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LaunchModelManager.getInstance().removeLastActivity();
    }
}
