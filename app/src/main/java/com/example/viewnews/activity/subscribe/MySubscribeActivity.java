package com.example.viewnews.activity.subscribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.adapter.subscribe.MySubscribeAdapter;
import com.example.viewnews.bean.SubBean;
import com.example.viewnews.tools.BaseActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MySubscribeActivity extends BaseActivity {

    private List<SubBean> subscribeList = new ArrayList<>();
    private MySubscribeAdapter mySubscribeAdapter;
    private String userIdNumber;
    private RecyclerView recyclerView;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
//        userIdNumber = getIntent().getStringExtra("user_subscribe");
        textView = findViewById(R.id.txt_subscribe_add);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySubscribeActivity.this.finish();
                Intent editIntent = new Intent(MySubscribeActivity.this, AddSubscribeActivity.class);
                editIntent.putExtra("add_user_subscribe", userIdNumber);
                startActivity(editIntent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_subscribe_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initView();
        imageView = findViewById(R.id.img_MySubscribe);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySubscribeActivity.this.finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //initArticles();
    }

    private void initView() {
        subscribeList.clear();
        List<SubBean> subscribe = LitePal.where("subMine = ? AND subUserID=?", "已关注", MainActivity.currentUserId).find(SubBean.class);
        if (subscribe.isEmpty()) {
            setContentView(R.layout.activity_subscribe);
            textView = findViewById(R.id.txt_subscribe_add);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MySubscribeActivity.this.finish();
                    Intent editIntent = new Intent(MySubscribeActivity.this, AddSubscribeActivity.class);
                    editIntent.putExtra("add_user_subscribe", userIdNumber);
                    startActivity(editIntent);
                }
            });
            imageView = findViewById(R.id.img_MySubscribe);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MySubscribeActivity.this.finish();
                }
            });
            System.out.println("empty");
        } else {
            subscribeList.addAll(subscribe);
            System.out.println(subscribeList.size());
            mySubscribeAdapter = new MySubscribeAdapter(this, subscribeList);
            recyclerView.setAdapter(mySubscribeAdapter);
            mySubscribeAdapter.notifyDataSetChanged();
        }

    }

}
