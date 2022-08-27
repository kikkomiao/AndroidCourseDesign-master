package com.example.viewnews.activity.subscribe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.adapter.subscribe.AddSubcribeAdapter;
import com.example.viewnews.bean.Subscribe;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AddSubscribeActivity extends BaseActivity {
    private RecyclerView recyclerView;

    private AddSubcribeAdapter addSubcribeAdapter;
    private List<Subscribe> subscribeList = new ArrayList<>();

    private TextView ymText;
    private ImageView imageView;
    private TextView hmText;
    private TextView localText;
    private Subscribe subscribe;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_add);
        ymText = findViewById(R.id.text_yangmei);
        hmText = findViewById(R.id.txt_hangmei);
        localText = findViewById(R.id.txt_fangmei);
        ymText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ymText.setBackgroundColor(Color.parseColor("#FDF8F8"));
                hmText.setBackgroundColor(Color.parseColor("#E3E0E0"));
                localText.setBackgroundColor(Color.parseColor("#E3E0E0"));
            }
        });
        hmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转页面
                AddSubscribeActivity.this.finish();
                Intent MySub = new Intent(AddSubscribeActivity.this, AddIndestryActivity.class);
                startActivity(MySub);
            }
        });
        localText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转页面
                AddSubscribeActivity.this.finish();
                Intent MySub = new Intent(AddSubscribeActivity.this, AddLocalActivity.class);
                startActivity(MySub);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.add_subscribe_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AddSubscribeActivity.this);
        initView();
        recyclerView.setLayoutManager(layoutManager);
        imageView = findViewById(R.id.img_subscribe);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubscribeActivity.this.finish();
                Intent MySub = new Intent(AddSubscribeActivity.this, MySubscribeActivity.class);
                startActivity(MySub);
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
        assignment();
        System.out.println(subscribeList.size());
        addSubcribeAdapter = new AddSubcribeAdapter(subscribeList);
        recyclerView.setAdapter(addSubcribeAdapter);
        addSubcribeAdapter.notifyDataSetChanged();
    }

    private void assignment() {
        String[] subName = getResources().getStringArray(R.array.CCTV_media_name);
        String[] subintro = getResources().getStringArray(R.array.CCTV_media_intro);
        String[] subAddress = getResources().getStringArray(R.array.CCTV_media_address);
        for (int i = 0; i < subName.length; i++) {
            int ImagePath = 0;
            switch (i) {
                case 0:
                    ImagePath = R.drawable.xinhuashe_news;
                    break;
                case 1:
                    ImagePath = R.drawable.rm_new;
                    break;
                case 2:
                    ImagePath = R.drawable.ys_new;
                    break;
                case 3:
                    ImagePath = R.drawable.gm_news;
                    break;
                case 4:
                    ImagePath = R.drawable.arm_news;
                    break;
                case 5:
                    ImagePath = R.drawable.women_news;
                    break;
            }
            Subscribe subscribe = new Subscribe(subName[i], subintro[i], ImagePath, subAddress[i]);
            System.out.println("subscribe" + subName[i] + subintro[i] + ImagePath + subAddress[i]);
            subscribeList.add(subscribe);
        }
    }

}
