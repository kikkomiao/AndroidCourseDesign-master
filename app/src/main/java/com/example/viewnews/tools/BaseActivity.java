package com.example.viewnews.tools;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

// BaseActivity不需要注册
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity----", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    private static final String TAG = "BaseActivity";
    static float fontScale = 1.0f;

//    @Override
//    public Resources getResources() {
//        Log.i(TAG, "getResources");
//        Resources resources = super.getResources();
//        return DisplayUtil.getResources(this, resources, fontScale);
//    }

//    @Override
//    public Resources getResources() {
//        System.out.println("getResource");
//        Resources resources = super.getResources();
//        if (resources != null) {
//            System.out.println("resources != null");
//            Configuration configuration = resources.getConfiguration();
//            if (configuration != null && configuration.fontScale != 1.0f) {
//                configuration.fontScale = this.fontScale;//这里只设置字体，故不使用下面注释的方法
//                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//            }
//        }
//        return resources;
//    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        Log.i(TAG, "attachBaseContext getResources");
//        super.attachBaseContext(DisplayUtil.attachBaseContext(base, fontScale));
//    }
//

    /**
     * 设置字体大小，同时通知界面重绘
     */
    public void setFontScale(float fontScale, Activity activity) {
        this.fontScale = fontScale;
        Log.i(TAG, "--------------setFontSize " + fontScale);
//        DisplayUtil.recreate(this);
    }
}

