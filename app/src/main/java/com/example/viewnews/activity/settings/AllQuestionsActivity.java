package com.example.viewnews.activity.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.bean.QuestionInfo;
import com.example.viewnews.adapter.settings.QuestionInfoAdapter;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AllQuestionsActivity extends BaseActivity {

    private ImageView ic_return_left_all_questions;

    public final static int ALL_QUESTION = 2;

    private List<QuestionInfo> questionInfos = new ArrayList<QuestionInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_questions);

        ic_return_left_all_questions = (ImageView) findViewById(R.id.ic_return_left_all_questions);
        //返回事件
        ic_return_left_all_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initQuestionInfos();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_all_questions);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        QuestionInfoAdapter adapter = new QuestionInfoAdapter(questionInfos, this, ALL_QUESTION);
        recyclerView.setAdapter(adapter);

    }

    public void initQuestionInfos() {
        QuestionInfo modifyPwd = new QuestionInfo("如何修改密码？", R.layout.question_modify_pwd, R.drawable.ic_nav_light, R.drawable.ic_enter_right);
        questionInfos.add(modifyPwd);
        QuestionInfo cancelAccount = new QuestionInfo("如何注销账号？", R.layout.question_cancel_account, R.drawable.ic_nav_light, R.drawable.ic_enter_right);
        questionInfos.add(cancelAccount);
        QuestionInfo changeTextSize = new QuestionInfo("如何修改字体大小？", R.layout.question_change_text_size, R.drawable.ic_nav_light, R.drawable.ic_enter_right);
        questionInfos.add(changeTextSize);
        QuestionInfo cleanCache = new QuestionInfo("关于清除缓存？", R.layout.question_clean_cache, R.drawable.ic_nav_light, R.drawable.ic_enter_right);
        questionInfos.add(cleanCache);
    }

}
