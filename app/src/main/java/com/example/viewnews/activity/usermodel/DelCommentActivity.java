package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.viewnews.bean.commentBean;
import com.example.viewnews.tools.BaseActivity;

import org.litepal.LitePal;

public class DelCommentActivity extends BaseActivity {

    public static final String CommentContent = "CommentContext";
    public static final String urlKeys = "url_Key";

    private String contain;
    private String url_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        contain = intent.getStringExtra(CommentContent);
        url_key = intent.getStringExtra(urlKeys);
        new MaterialDialog.Builder(DelCommentActivity.this)
                .title("提示")
                .content("确认是否删除此条评论")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        //Toast.makeText(ArticleDetailActivity.this, "点击了确认按钮", Toast.LENGTH_SHORT).show();
                        int isOk = LitePal.deleteAll(commentBean.class, "commentContext = ?", contain);
                        if (isOk > 0) {
                            Toast.makeText(DelCommentActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
                            Intent editIntent = new Intent(DelCommentActivity.this, CommentActivity.class);
                            editIntent.putExtra("url_Key", url_key);
                            startActivity(editIntent);
                            DelCommentActivity.this.finish();
                            CommentActivity.instance.finish();
                        } else {
                            Toast.makeText(DelCommentActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .positiveText("确认")
                .negativeText("取消")
                .show();
    }
}
