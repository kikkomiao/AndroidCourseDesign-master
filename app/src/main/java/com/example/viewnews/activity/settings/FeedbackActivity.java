package com.example.viewnews.activity.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.viewnews.R;
import com.example.viewnews.bean.QuestionInfo;
import com.example.viewnews.adapter.settings.QuestionInfoAdapter;
import com.example.viewnews.tools.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends BaseActivity {

    private ImageView ic_return_left_feedback;
    private TextView tv_feedback_all;
    private BottomNavigationView bottomNavigation;
    private FloatingActionButton floatingActionButton;

    public final static int HOME_QUESTION = 1;

    private List<QuestionInfo> questionInfos = new ArrayList<QuestionInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ic_return_left_feedback = (ImageView) findViewById(R.id.ic_return_left_feedback);
        tv_feedback_all = (TextView) findViewById(R.id.tv_feedback_all);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomAppBar_feedback);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.navigation_message);

        initQuestionInfos();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_feedback_question);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        QuestionInfoAdapter adapter = new QuestionInfoAdapter(questionInfos, this, HOME_QUESTION);
        recyclerView.setAdapter(adapter);

        //????????????
        ic_return_left_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // ???????????????????????????
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_app_bar_navigation_home:
                        System.out.println("QusResolvementActivity says you click the home nav");
//                        Intent homeIntent = new Intent(FeedbackActivity.this, FeedbackActivity.class);
//                        startActivity(homeIntent);
//                        FeedbackActivity.this.finish();
                        recreate();
                        return true;
                    case R.id.navigation_discover:
                        System.out.println("QusResolvementActivity says you click the normal questions nav");
                        Intent allQuestionsIntent = new Intent(FeedbackActivity.this, AllQuestionsActivity.class);
                        startActivity(allQuestionsIntent);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

        // ???????????? ???????????????????????????
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //??????????????????
                new MaterialDialog.Builder(FeedbackActivity.this)
                        .title("????????????")
                        .inputRangeRes(1, 50, R.color.colorBlack)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                System.out.println("?????????????????????" + input);
                                String to = "1747052449@qq.com";
                                String subject = "????????????";
                                String message = input.toString();
                                // Android????????????????????? android.content.Intent.ACTION_SENDTO
                                Intent email = new Intent(android.content.Intent.ACTION_SENDTO);
                                email.setData(Uri.parse("mailto:1747052449i@qq.com"));
                                email.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                                email.putExtra(android.content.Intent.EXTRA_TEXT, message);
                                if (email.resolveActivity(getPackageManager()) != null) {
                                    startActivity(email);
                                } else {
                                    System.out.println("No application that can handle this link found");
                                }
                                Toast.makeText(FeedbackActivity.this, "?????????????????????????????????" + input, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .positiveText("??????")
                        .negativeText("??????")
                        .show();
            }
        });

        // "??????"?????? ????????????
        tv_feedback_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????????????????
                System.out.println("you click the all normal qustions");
                Intent allQuestionsIntent = new Intent(FeedbackActivity.this, AllQuestionsActivity.class);
                startActivity(allQuestionsIntent);
            }
        });
    }

    public void initQuestionInfos() {
        QuestionInfo modifyPwd = new QuestionInfo("?????????????????????", R.layout.question_modify_pwd);
        questionInfos.add(modifyPwd);
        QuestionInfo cancelAccount = new QuestionInfo("?????????????????????", R.layout.question_cancel_account);
        questionInfos.add(cancelAccount);
        QuestionInfo changeTextSize = new QuestionInfo("???????????????????????????", R.layout.question_change_text_size);
        questionInfos.add(changeTextSize);
        QuestionInfo cleanCache = new QuestionInfo("?????????????????????", R.layout.question_clean_cache);
        questionInfos.add(cleanCache);
    }

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        Context context = view.getContext();
//        if (url.startsWith("tel:")) {
//            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
//            startActivity(intent);
//            view.reload();
//            return true;
//        } else if (url.contains("mailto:") ||url.contains("geo:") || url.startsWith("sms:")) {
//            Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            //context.startActivity(intent);
//            //???????????????????????????????????????Activity?????????????????????????????????????????????????????????
//            if (intent.resolveActivity(context.getPackageManager()) != null){
//                context.startActivity(intent);
//            }else {
//                System.out.println("No application that can handle this link found");
//            }
//            return true;
//        } else{
//            view.loadUrl(url);
//            return true;
//        }
//    }
}
