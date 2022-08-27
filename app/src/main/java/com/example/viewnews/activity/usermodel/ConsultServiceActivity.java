package com.example.viewnews.activity.usermodel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class ConsultServiceActivity extends BaseActivity {

    //    private Toolbar toolbar_consult;
    private String userIdNumber;
    private ImageView icon_return;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting_service);

        userIdNumber = getIntent().getStringExtra("user_consult_id");
        System.out.println("当前用户的账号为" + userIdNumber);

        findViewById();

        initView();

        setClickListener();


    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("当前ConsultingServiceActivity活动又被加载onStart");
//        if(toolbar_consult == null){
//            Log.d("ConsultServiceActivity","toolbar_consult为空");
//        } else {
//            toolbar_consult.setTitle("小智为您服务 ~");
//            setSupportActionBar(toolbar_consult);
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                //通过HomeAsUp来让导航按钮显示出来
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
//            }
//        }
    }

    private void findViewById() {
        icon_return = findViewById(R.id.ic_return_left);
    }

    private void initView() {

    }

    private void setClickListener() {
        //返回事件
        icon_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
