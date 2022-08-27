package com.example.viewnews.activity.subscribe;

import android.content.Intent;
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

public class AddIndestryActivity extends BaseActivity {
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
        setContentView(R.layout.activity_subscribe_add1);
        ymText = findViewById(R.id.text_yangmei1);
        hmText = findViewById(R.id.txt_hangmei1);
        localText = findViewById(R.id.txt_fangmei1);
        ymText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIndestryActivity.this.finish();
                Intent MySub = new Intent(AddIndestryActivity.this, AddSubscribeActivity.class);
                startActivity(MySub);
            }
        });
        hmText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //不变
            }
        });
        localText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIndestryActivity.this.finish();
                Intent MySub = new Intent(AddIndestryActivity.this, AddLocalActivity.class);
                startActivity(MySub);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.add_subscribe_recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(AddIndestryActivity.this);
        initView();
        recyclerView.setLayoutManager(layoutManager);
        imageView = findViewById(R.id.img_subscribe);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddIndestryActivity.this.finish();
                Intent MySub = new Intent(AddIndestryActivity.this, MySubscribeActivity.class);
                startActivity(MySub);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        subscribeList.clear();
        induestryAssignment();
        System.out.println(subscribeList.size());
        addSubcribeAdapter = new AddSubcribeAdapter(subscribeList);
        recyclerView.setAdapter(addSubcribeAdapter);
        addSubcribeAdapter.notifyDataSetChanged();
    }

    private void induestryAssignment() {
        String[] subName = getResources().getStringArray(R.array.Industry_media_name);
        String[] subintro = getResources().getStringArray(R.array.Industry_media_intro);
        String[] subAddress = getResources().getStringArray(R.array.Industry_media_address);
        for (int i = 0; i < subName.length; i++) {
            int ImagePath = 0;
            switch (i) {
                case 0:
                    ImagePath = R.drawable.art_news;
                    break;
                case 1:
                    ImagePath = R.drawable.chinaart_news;
                    break;
                case 2:
                    ImagePath = R.drawable.scientist_news;
                    break;
                case 3:
                    ImagePath = R.drawable.eg_news;
                    break;
                case 4:
                    ImagePath = R.drawable.zgwwb_news;
                    break;
                case 5:
                    ImagePath = R.drawable.zgdyb_news;
            }
            Subscribe subscribe = new Subscribe(subName[i], subintro[i], ImagePath, subAddress[i]);
            System.out.println("subscribe" + subName[i] + subintro[i] + ImagePath + subAddress[i]);
            subscribeList.add(subscribe);
        }
    }
}
