package com.example.viewnews.activity.settings;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class QusResolvementActivity extends BaseActivity {

    private ImageView ic_return_left_question;
//    private BottomAppBar bottomAppBar;
//    private FloatingActionButton floatingActionButton;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String layoutID = intent.getStringExtra("layoutID");
        System.out.println("QusResolvementActivity says the current layoutID is" + layoutID);

        setContentView(Integer.valueOf(layoutID));

        ic_return_left_question = (ImageView) findViewById(R.id.ic_return_left_question);

        if (Integer.valueOf(layoutID) == R.layout.question_modify_pwd) {
            System.out.println("如何修改密码？");
            TextView tv_resolution_modifypwd_first = (TextView) findViewById(R.id.tv_resolution_clean_cache);
            String content_first = "<h3>第一步：点击app首页右下方“设置”</h3>";
            tv_resolution_modifypwd_first.setText(Html.fromHtml(content_first));
            TextView tv_resolution_modifypwd_second = (TextView) findViewById(R.id.tv_resolution_modifypwd_second);
            String content_second = "<h3>第二步：点击“设置”中的“账号与安全”</h3>";
            tv_resolution_modifypwd_second.setText(Html.fromHtml(content_second));
            TextView tv_resolution_modifypwd_third = (TextView) findViewById(R.id.tv_resolution_modifypwd_third);
            String content_third = "<h3>第三步：点击“账号与安全”中的“更改登录密码”</h3>";
            tv_resolution_modifypwd_third.setText(Html.fromHtml(content_third));
        } else if (Integer.valueOf(layoutID) == R.layout.question_cancel_account) {
            System.out.println("如何注销账号？");
            TextView tv_resolution_cancel_account_first = (TextView) findViewById(R.id.tv_resolution_cancel_account_first);
            String content_first = "<h3>第一步：点击app首页右下方“设置”</h3>";
            tv_resolution_cancel_account_first.setText(Html.fromHtml(content_first));
            TextView tv_resolution_cancel_account_second = (TextView) findViewById(R.id.tv_resolution_cancel_account_second);
            String content_second = "<h3>第二步：点击“设置”中的“账号与安全”</h3>";
            tv_resolution_cancel_account_second.setText(Html.fromHtml(content_second));
            TextView tv_resolution_cancel_account_third = (TextView) findViewById(R.id.tv_resolution_cancel_account_third);
            String content_third = "<h3>第三步：点击“账号与安全”中的“注销账号”</h3>";
            tv_resolution_cancel_account_third.setText(Html.fromHtml(content_third));
        } else if (Integer.valueOf(layoutID) == R.layout.question_change_text_size) {
            System.out.println("如何修改字体大小？");
            TextView tv_resolution_change_text_size_first = (TextView) findViewById(R.id.tv_resolution_change_text_size_first);
            String content_first = "<h3>第一步：点击app首页右下方“设置”</h3>";
            tv_resolution_change_text_size_first.setText(Html.fromHtml(content_first));
            TextView tv_resolution_change_text_size_second = (TextView) findViewById(R.id.tv_resolution_change_text_size_second);
            String content_second = "<h3>第二步：点击“设置”中的“隐私和通用”</h3>";
            tv_resolution_change_text_size_second.setText(Html.fromHtml(content_second));
            TextView tv_resolution_change_text_size_third = (TextView) findViewById(R.id.tv_resolution_change_text_size_third);
            String content_third = "<h3>第三步：点击“隐私和通用”中的“字体大小”</h3>";
            tv_resolution_change_text_size_third.setText(Html.fromHtml(content_third));
            TextView tv_resolution_change_text_size_forth = (TextView) findViewById(R.id.tv_resolution_change_text_size_forth);
            String content_forth = "<h3>第四步：拖动滑块，设置为自己满意的字体大小后，点击左上角返回即可立即生效（注意：该字体大小设置只在咨询聊天窗口中生效）</h3>";
            tv_resolution_change_text_size_forth.setText(Html.fromHtml(content_forth));
        } else if (Integer.valueOf(layoutID) == R.layout.question_clean_cache) {
            System.out.println("关于清除缓存？");
            TextView tv_resolution_clean_cache = (TextView) findViewById(R.id.tv_resolution_clean_cache);
            String content = "<h3>\t\t将自动计算您当前的缓存大小。若确定删除所有缓存，离线内容及其图片均会被清除。</h3>";
            tv_resolution_clean_cache.setText(Html.fromHtml(content));
        }

        //返回事件
        ic_return_left_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
