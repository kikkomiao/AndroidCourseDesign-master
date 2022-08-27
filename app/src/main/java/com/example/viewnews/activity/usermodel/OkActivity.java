package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viewnews.R;

/**
 * Created by zhangqie on 2020/2/18
 * Describe:
 */
public class OkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_activity);

        String stringType = getIntent().getStringExtra("stringType");

        TextView textView = findViewById(R.id.txt_percent);
        textView.setText(stringType);

        Button button = findViewById(R.id.btn_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(OkActivity.this, AnswerQuestionActivity.class);
                startActivity(settingsIntent);
                OkActivity.this.finish();
            }
        });
    }
}
