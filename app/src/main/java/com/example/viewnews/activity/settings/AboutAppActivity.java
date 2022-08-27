package com.example.viewnews.activity.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.bean.SettingInfo;
import com.example.viewnews.adapter.settings.AboutAppAdapter;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AboutAppActivity extends BaseActivity {

    private List<SettingInfo> settingInfoList = new ArrayList<SettingInfo>();
    private ImageView icon_return_aboutapp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutapp);

        icon_return_aboutapp = findViewById(R.id.ic_return_left_aboutapp);
        //返回事件
        icon_return_aboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initSettingInfos();

//        布局管理器 --- 决定RecyclerView里面的呈现方式
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_aboutapp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//
        AboutAppAdapter adapter = new AboutAppAdapter(settingInfoList, getBaseContext());
        recyclerView.setAdapter(adapter);

    }

    public void initSettingInfos() {
        SettingInfo functionItro = new SettingInfo("功能介绍", R.drawable.ic_enter_right);
        settingInfoList.add(functionItro);
        SettingInfo servicePolicy = new SettingInfo("服务协议", R.drawable.ic_enter_right);
        settingInfoList.add(servicePolicy);
        SettingInfo privacyPolicy = new SettingInfo("隐私权政策", R.drawable.ic_enter_right);
        settingInfoList.add(privacyPolicy);
    }
}
