package com.bitcoin.juwan.baselibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * FileName：IActivity
 * Create By：liumengqiang
 * Description：TODO
 */
public interface IActivity {
    /**
     * 给插件Activity指定上下文
     *
     * @param activity
     */
    void attach(Activity activity);

    // 以下全都是Activity生命周期函数,
    // 插件Activity本身 在被用作"插件"的时候不具备生命周期，由宿主里面的代理Activity类代为管理
    void onCreate(Bundle saveInstanceState);

    void onStart();

    void onResume();

    void onRestart();

    void onPause();

    void onStop();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
