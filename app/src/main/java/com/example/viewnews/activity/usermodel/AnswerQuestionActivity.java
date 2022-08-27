package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class AnswerQuestionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_start_page);
        Toolbar toolbar = findViewById(R.id.question_toolbar);
        toolbar.setTitle("看点答题");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_return_left);
        }
        findViewById(R.id.btn_answerQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnswerQuestionActivity.this, QuestionActivity.class));
                AnswerQuestionActivity.this.finish();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AnswerQuestionActivity.this.finish();
                break;
        }
        return true;
    }
}
