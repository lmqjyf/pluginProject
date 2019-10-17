package com.bitcoin.juwan.pluginmodule;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bitcoin.juwan.baselibrary.PluginBaseActivity;
import com.bitcoin.juwan.baselibrary.PluginConst;
import com.bitcoin.juwan.baselibrary.PluginManager;

public class ThirdActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.jump_host_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHostMainActivity();
            }
        });
        findViewById(R.id.plugin_third_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(proxy, TestActivity.class));
            }
        });
    }

    /**
     * 跳转到宿主MainActivity
     */
    private void startHostMainActivity() {
//            Intent reallyIntent = new Intent();
//            reallyIntent.putExtra(PluginConst.REALLY_ACTIVITY_NAME, intent.getComponent().getClassName());
//            proxy.startActivity(reallyIntent);
//        PluginManager.getInstance().startActivity(proxy, );
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.bitcoin.juwan.hostappproject", "com.bitcoin.juwan.hostappproject.MainActivity");
        intent.setComponent(componentName);
        proxy.startActivity(intent);
    }
}
