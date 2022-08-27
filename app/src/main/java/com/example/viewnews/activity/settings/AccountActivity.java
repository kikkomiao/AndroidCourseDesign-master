package com.example.viewnews.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.bean.SettingInfo;
import com.example.viewnews.adapter.settings.AccountManageAdapter;
import com.example.viewnews.tools.MyPopWin;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends BaseActivity {

    private ImageView icon_return_account;
    private RecyclerView recycler_view_account_manage;

    private List<SettingInfo> settingInfoList = new ArrayList<SettingInfo>();

    private View.OnClickListener onModifyClickListener;
    private MyPopWin myPopWin;

    private String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        userID = intent.getStringExtra("user_id");
        System.out.println("AccountActivity onCreate: userID is " + userID);

        icon_return_account = findViewById(R.id.ic_return_left_account);
        //返回事件
        icon_return_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initSettingInfos();

        recycler_view_account_manage = (RecyclerView) findViewById(R.id.recycler_view_account_manage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view_account_manage.setLayoutManager(layoutManager);

        AccountManageAdapter accountManageAdapter = new AccountManageAdapter(this, settingInfoList, getBaseContext());
        recycler_view_account_manage.setAdapter(accountManageAdapter);
    }

    private void initSettingInfos() {
        //      账号与安全
        //      （更改登录密码）
        //      （注销账号）
        System.out.println("3333");
        SettingInfo modifyPwd = new SettingInfo("更改登录密码", R.drawable.ic_enter_right);
        settingInfoList.add(modifyPwd);
        SettingInfo cancelAccount = new SettingInfo("注销账号", R.drawable.ic_enter_right);
        settingInfoList.add(cancelAccount);
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
