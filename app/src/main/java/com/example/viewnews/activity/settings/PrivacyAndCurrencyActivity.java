package com.example.viewnews.activity.settings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class PrivacyAndCurrencyActivity extends BaseActivity {

    private ImageView ic_return_left_privacy;
    private Switch switch_permission_image;
    private TextView textview_permission_text_size;
    private ImageView imgview_enter_textsize;

    private final String[] PERMISSIONS = {   //app需要获取的权限
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private final int REQUEST_CLOSE = 0;     //定义关闭权限请求码是 0
    private final int REQUEST_GET_IMAGE = 1; //定义相册权限请求码是 1

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_and_currency);

        ic_return_left_privacy = (ImageView) findViewById(R.id.ic_return_left_privacy);
        switch_permission_image = (Switch) findViewById(R.id.switch_permission_image);
        textview_permission_text_size = (TextView) findViewById(R.id.textview_permission_text_size);
        imgview_enter_textsize = (ImageView) findViewById(R.id.imgview_enter_textsize);

        init(new View(this));

        // 返回事件
        ic_return_left_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 开关状态转换事件
        switch_permission_image.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("buttonView.isPressed()--------" + buttonView.isPressed());
                if (!buttonView.isPressed()) {  //代码修改 设置switch状态
                    System.out.println("else 代码修改 设置switch状态!!!!!!");
                } else {   // 人为点击 设置switch状态
                    if (isChecked == true) {
                        // false 改为 true --- 开启权限
//                        Toast.makeText(getBaseContext(), "原先为FALSE，现在开启权限", Toast.LENGTH_SHORT).show();
                        Log.d("PrivacyActivity>>>>>>>", "onCheckedChanged: 原先为FALSE，现在开启权限");
                        ActivityCompat.requestPermissions(PrivacyAndCurrencyActivity.this, PERMISSIONS, REQUEST_GET_IMAGE);
                    } else {
                        // true 改为 false --- 关掉权限
//                        Toast.makeText(getBaseContext(), "原先为TRUE，现在关闭权限", Toast.LENGTH_SHORT).show();
                        Log.d("PrivacyActivity>>>>>>>", "onCheckedChanged: 原先为TRUE，现在关闭权限");
                        ActivityCompat.requestPermissions(PrivacyAndCurrencyActivity.this, PERMISSIONS, REQUEST_CLOSE);
                    }
                }
            }
        });

        // 点击文字 TextView 进入字体设置页面
        textview_permission_text_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyAndCurrencyActivity.this, FontSizeActivity.class);
                startActivity(intent);
            }
        });

        // 点击图片 ImageViw 进入字体设置页面
        imgview_enter_textsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyAndCurrencyActivity.this, FontSizeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void init(View v) {
        //相机权限 switch
        if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("有权限，设置为true");
            switch_permission_image.setChecked(true);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                switch_permission_image.setTrackResource(R.color.colorPrimary);
//            }
        } else {
            System.out.println("无权限，设置为false");
            switch_permission_image.setChecked(false);
        }
    }

    // 响应权限申请
    //第一个参数是请求码 根据请求码可以确定是做哪一个权限的申请
    //对于不同的请求依据请求码区分 并结合if else做对应处理
    //  PackageManager.PERMISSION_GRANTED 权限授权成功
    //  PackageManager.PERMISSION_DENIED  权限授权失败
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_GET_IMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(getBaseContext(), "权限请求成功", Toast.LENGTH_SHORT).show();
                Log.d("PrivacyActivity::", "权限请求成功");
            } else {
//                Toast.makeText(getBaseContext(), "权限请求失败", Toast.LENGTH_SHORT).show();
                Log.d("PrivacyActivity::", "权限请求失败");
            }
        } else if (requestCode == REQUEST_CLOSE) {
            Log.d("PrivacyActivity::", "关闭权限");
        }
    }
}

