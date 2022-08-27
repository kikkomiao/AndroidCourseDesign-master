package com.example.viewnews.activity.settings;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.adapter.settings.SettingAdapter;
import com.example.viewnews.bean.SettingInfo;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends BaseActivity {

    private List<SettingInfo> settingInfoList = new ArrayList<SettingInfo>();

    private ImageView icon_return;

    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        userID = intent.getStringExtra("user_id");
        System.out.println("SettingActivity onCreate: userID is " + userID);

        icon_return = findViewById(R.id.ic_return_left);
        //返回事件
        icon_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initSettingInfos();

//        布局管理器 --- 决定RecyclerView里面的呈现方式
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SettingAdapter adapter = new SettingAdapter(settingInfoList, this);
        recyclerView.setAdapter(adapter);

    }

    private void initSettingInfos() {
        //      账号与安全（注销账号）
        //      隐私（授权管理、系统权限管理） & 通用（字体大小、当前设备信息）
        //      关于看点新闻（功能介绍、服务协议、隐私权政策）
        //      意见反馈（首页、常见问题）
        SettingInfo accountManagement = new SettingInfo("账号与安全", R.drawable.ic_enter_right);
        settingInfoList.add(accountManagement);
        SettingInfo privacy = new SettingInfo("隐私和通用", R.drawable.ic_enter_right);
        settingInfoList.add(privacy);
//        SettingInfo currency = new SettingInfo("通用", R.drawable.ic_enter_right);
//        settingInfoList.add(currency);
        SettingInfo aboutApp = new SettingInfo("关于看点新闻", R.drawable.ic_enter_right);
        settingInfoList.add(aboutApp);
        SettingInfo feedback = new SettingInfo("意见反馈", R.drawable.ic_enter_right);
        settingInfoList.add(feedback);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
