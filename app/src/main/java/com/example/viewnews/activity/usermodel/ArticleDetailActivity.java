package com.example.viewnews.activity.usermodel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;
import com.example.viewnews.bean.Article;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ArticleDetailActivity extends BaseActivity {

    public static final String ARTICLE_NAME = "artile_name";

    public static final String ARTICLE_IMAGE_ID = "artile_image_id";

    public static final String ARTICLE_TIME = "artile_time";
    public static final String ARTICLE_ID = "art_id_test";
    private Article article = new Article();
    List<Article> articles;
    private String articleName, articleImageId, articleTime;
    private Toolbar toolbar, artToolBar;

    private long id;
    private String articleText = "";
    private EditText editText;
    TextView articleContentText;
    FloatingActionButton delFab;
    FloatingActionButton addFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        System.out.println(intent);

        articleName = intent.getStringExtra(ARTICLE_NAME);
        articleImageId = intent.getStringExtra(ARTICLE_IMAGE_ID);
        articleTime = intent.getStringExtra(ARTICLE_TIME);
        id = intent.getLongExtra(ARTICLE_ID, id);

        System.out.println("id" + id);
        System.out.println("???????????????????????????" + articleImageId);
        System.out.println("???????????????????????????" + articleName);
        System.out.println("?????????????????????????????????" + articleTime);

        if (articleImageId == null || articleImageId.equals("")) {
            System.out.println("????????????");
            setContentView(R.layout.activity_article_detail);
            toolbar = (Toolbar) findViewById(R.id.article_detail_toolbar);
            artToolBar = (Toolbar) findViewById(R.id.toba);
            //?????????????????????toolbar??????????????????
            findViewById(R.id.toba).bringToFront();
            CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

            TextView articleContentText = (TextView) findViewById(R.id.article_content_text);
            TextView articleTime1 = findViewById(R.id.article_time);
            // ????????????????????????
            List<Article> articles = LitePal.where("userId = ? AND articleTitle = ? AND articleTime = ?", MainActivity.currentUserId, articleName, articleTime).find(Article.class);
            //?????????????????????????????????????????????
            collapsingToolbar.setTitle(articleName);
            articleTime1.setText(articleTime);
            articleContentText.setText(articles.get(0).getArticleContent());

        } else {
            System.out.println("????????????");
            setContentView(R.layout.activity_detail_pic);
            toolbar = (Toolbar) findViewById(R.id.article_detail_toolbar);
            artToolBar = (Toolbar) findViewById(R.id.toba);
            //?????????????????????toolbar??????????????????
            findViewById(R.id.toba).bringToFront();
            CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            ImageView articleImageView = (ImageView) findViewById(R.id.pic);
            TextView articleContentText = (TextView) findViewById(R.id.article_content_text);
            TextView articleTime1 = findViewById(R.id.article_time);

            // ????????????????????????
            List<Article> articles = LitePal.where("userId = ? AND articleTitle = ? AND articleTime = ?", MainActivity.currentUserId, articleName, articleTime).find(Article.class);
            //?????????????????????????????????????????????
            collapsingToolbar.setTitle(articleName);
            articleTime1.setText(articleTime);
            articleContentText.setText(articles.get(0).getArticleContent());

            Glide.with(this).load(articleImageId)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.defaultbg)
                    .error(R.drawable.defaultbg)
                    .into(articleImageView);
        }
//        setContentView(R.layout.activity_article_detail);


        //????????????????????????
        FloatingActionButton delFab = (FloatingActionButton) findViewById(R.id.delete_article);
        delFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ??????????????????
                new MaterialDialog.Builder(ArticleDetailActivity.this)
                        .title("??????")
                        .content("??????????????????????????????")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                //Toast.makeText(ArticleDetailActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                                int isOk = LitePal.deleteAll(Article.class, "userId = ? AND articleTitle = ? AND articleTime = ?", MainActivity.currentUserId, articleName, articleTime);
                                if (isOk > 0) {
                                    Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(ArticleDetailActivity.this,ArticleActivity.class);
//                                    intent.putExtra("user_article_id",MainActivity.currentUserId);
                                    ArticleDetailActivity.this.finish();
                                } else {
                                    Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .positiveText("??????")
                        .negativeText("??????")
                        .show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //??????HomeAsUp??????
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }
        toolbar.setTitle("");

//        setSupportActionBar(artToolBar);
        artToolBar.inflateMenu(R.menu.toolbar_article);
        artToolBar.setTitle("");

        artToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.tab_love:
//                        Toast.makeText(ArticleDetailActivity.this, "love", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.tab_edit:
//                        Toast.makeText(ArticleDetailActivity.this, "edit", Toast.LENGTH_SHORT).show();
//                        ??????textview ?????????edittext
                        articleContentText = (TextView) findViewById(R.id.article_content_text);
                        editText = (EditText) findViewById(R.id.edit_text);
                        articleContentText.setVisibility(View.GONE);
                        editText.setVisibility(View.VISIBLE);
//?????????????????? ??????????????????
                        delFab = (FloatingActionButton) findViewById(R.id.delete_article);
                        addFab = (FloatingActionButton) findViewById(R.id.add_article);
                        delFab.setVisibility(View.GONE);
                        addFab.setVisibility(View.VISIBLE);

//                        if(!articleImageId.equals("")){
//                            ImageView image
//                        }
//?????????????????????????????? ??????editText
//                        articles = LitePal.where("userId = ? AND articleTitle = ? AND articleTime = ?", MainActivity.currentUserId, articleName, articleTime).find(Article.class);
//                        id = articles.get(0).getId();
                        final String idtest = String.valueOf(id);
                        System.out.println("sql???????????????id" + idtest);
                        articles = LitePal.where("id=?", idtest).find(Article.class);
                        articleText = articles.get(0).getArticleContent();
                        editText.setText(articleText);
//                        ????????????????????????
                        addFab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new MaterialDialog.Builder(ArticleDetailActivity.this)
                                        .title("??????")
                                        .content("????????????")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(MaterialDialog dialog, DialogAction which) {
//                                                Toast.makeText(ArticleDetailActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
//                                                int isOk2 = LitePal.deleteAll(Article.class, "userId = ? AND articleTitle = ? AND articleTime = ?", MainActivity.currentUserId, articleName, articleTime);
//                                                ????????????edittext????????????
                                                String value = editText.getText().toString();
                                                System.out.println("??????edittext" + value);
//                                                article.setArticleTitle(articleName);
                                                article.setArticleContent(value);
                                                System.out.println("get??????" + article.getArticleContent());
//                                                article.setArticleImagePath(articleImageId);
                                                Calendar calendar = Calendar.getInstance();
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                                System.out.println(sdf.format(calendar.getTime()));
                                                article.setArticleTime(sdf.format(calendar.getTime()));
                                                int jgd = article.update(id);
                                                System.out.println("update??????" + jgd);
//                                                ??????????????????
                                                if (jgd > 0) {
                                                    Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
//                                                    ???????????????
                                                    System.out.println("????????????" + editText.getText());
                                                    articleContentText.setText(editText.getText());
                                                    articleContentText.setVisibility(View.VISIBLE);
                                                    editText.setVisibility(View.GONE);
//                                                    ??????????????????
                                                    delFab.setVisibility(View.VISIBLE);
                                                    addFab.setVisibility(View.GONE);
                                                    delFab.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            // ??????????????????
                                                            new MaterialDialog.Builder(ArticleDetailActivity.this)
                                                                    .title("??????")
                                                                    .content("??????????????????????????????")
                                                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                                                        @Override
                                                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                                                            String idString = String.valueOf(id);
                                                                            System.out.println(idString);
                                                                            int isOk = LitePal.deleteAll(Article.class, "id= ?", idString);
                                                                            if (isOk > 0) {
                                                                                Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                                                                ArticleDetailActivity.this.finish();
                                                                            } else {
                                                                                Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                    })
                                                                    .positiveText("??????")
                                                                    .negativeText("??????")
                                                                    .show();
                                                        }
                                                    });
//                                                    finish();
                                                } else {
                                                    Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .positiveText("??????")
                                        .negativeText("??????")
                                        .show();
                            }
                        });
                        break;
                    case R.id.tab_delete:
//                        Toast.makeText(ArticleDetailActivity.this, "delete", Toast.LENGTH_SHORT).show();
                        new MaterialDialog.Builder(ArticleDetailActivity.this)
                                .title("??????")
                                .content("??????????????????????????????")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(MaterialDialog dialog, DialogAction which) {
                                        //Toast.makeText(ArticleDetailActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                                        String idString = String.valueOf(id);
                                        int isOk = LitePal.deleteAll(Article.class, "id=?", idString);
                                        if (isOk > 0) {
                                            Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                            ArticleDetailActivity.this.finish();
                                        } else {
                                            Toast.makeText(ArticleDetailActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .positiveText("??????")
                                .negativeText("??????")
                                .show();
                        break;
                    case R.id.tab_share:
//                        Toast.makeText(ArticleDetailActivity.this, "share", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_SUBJECT, articleName);
                        // ?????????????????????
                        intent.setType("text/plain");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent, getTitle()));
                        break;
                    default:
                        Toast.makeText(ArticleDetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //??????????????????,???????????????????????????
                ArticleDetailActivity.this.finish();
                return true;
        }
        return true;
    }
}
