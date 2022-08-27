package com.example.viewnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class BeginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_show);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                float valueFromFontSize = 0.0f;
//                valueFromFontSize = getIntent().getFloatExtra("value_to_begin", valueFromFontSize);
                Intent intent = new Intent(BeginActivity.this, MainActivity.class);
//                intent.putExtra("value", valueFromFontSize);
//                setResult(RESULT_OK, intent);
                BeginActivity.this.startActivity(intent);
                BeginActivity.this.finish();
            }
        }, 3000);
    }
}
