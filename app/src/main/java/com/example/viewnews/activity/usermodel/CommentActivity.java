package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.bean.commentBean;
import com.example.viewnews.tools.BaseActivity;
import com.example.viewnews.adapter.usermodel.CommentAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {

    private List<commentBean> commentBeanList = new ArrayList<>();
    public static CommentActivity instance;
    private CommentAdapter adapter;

    private String userIdNumber;
    private String urlKey = null;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.acticity_comment);
        urlKey = getIntent().getStringExtra("url_Key");
        Log.d(urlKey, "567");
        Toolbar toolbar = findViewById(R.id.comment_toolbar);
        toolbar.setTitle("全部评论");
        setSupportActionBar(toolbar);
        //获取到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //将导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        recyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initComments();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //initArticles();
    }

    private void initComments() {
        commentBeanList.clear();
        List<commentBean> comments = LitePal.where("urlkey = ?", urlKey).find(commentBean.class);
        if (comments.isEmpty()) {
            setContentView(R.layout.acticity_comment_empty);
            Toolbar toolbar = findViewById(R.id.comment_toolbar);
            toolbar.setTitle("全部评论");
            setSupportActionBar(toolbar);
            //获取到ActionBar的实例
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //将导航按钮显示出来
                actionBar.setDisplayHomeAsUpEnabled(true);
                //设置一个导航按钮图标
                actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
            }
        }
        commentBeanList.addAll(comments);
        adapter = new CommentAdapter(commentBeanList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 销毁当前活动
                CommentActivity.this.finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    initComments();
                }
                break;
        }
    }
}