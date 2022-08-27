package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;
import com.example.viewnews.bean.Article;
import com.example.viewnews.adapter.usermodel.ArticleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends BaseActivity {

    private List<Article> articleList = new ArrayList<>();

    private ArticleAdapter adapter;

    private String userIdNumber;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        userIdNumber = getIntent().getStringExtra("user_article_id");
        System.out.println("当前用户的账号为" + userIdNumber);
        Toolbar toolbar = findViewById(R.id.article_toolbar);
        toolbar.setTitle("我的文章");
        setSupportActionBar(toolbar);
        //获取到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //将导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置一个导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }

        //监听点击事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.article_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(ArticleActivity.this, "点击编辑文章", Toast.LENGTH_SHORT).show();
                //跳转到编辑文章页面，返回来之后重新刷新列表
//                Intent editArticleIntent = new Intent(ArticleActivity.this, ArticleAdapter.class);
                Intent editArticleIntent = new Intent(ArticleActivity.this, EditArticleActivity.class);
                editArticleIntent.putExtra("userId", userIdNumber);
                startActivityForResult(editArticleIntent, 7);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        initArticles();


//        recyclerView.setOnClickListener(new SlideRecyclerView.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = getCurrentFocus().getVerticalScrollbarPosition();
//                articleList.remove(position);
//                System.out.println(position);
//                initArticles();
//            }
//        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        System.out.println("start");
//        initArticles();
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        super.onStart();
        System.out.println("reseume");
        initArticles();
    }

//    @Override
//    protected void onRestart() {
////        super.onResume();
//        super.onRestart();
//        System.out.println("restart");
//        initArticles();
//    }

    private void initArticles() {
        System.out.println("begin");
        articleList.clear();
        List<Article> articles = LitePal.where("userId = ?", userIdNumber).find(Article.class);
        articleList.addAll(articles);
        adapter = new ArticleAdapter(articleList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        System.out.println("end");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 销毁当前活动
                ArticleActivity.this.finish();
                break;
        }
        return true;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        System.out.println("1");
//        switch (requestCode) {
//            case 7:
//                System.out.println("2");
////                initArticles();
//                break;
//            default:
//                System.out.println("default");
//        }
//    }


    public String getUserIdNumber() {
        return userIdNumber;
    }
}