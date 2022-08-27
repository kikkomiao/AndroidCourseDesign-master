package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.adapter.usermodel.OnItemClickListenter;
import com.example.viewnews.adapter.usermodel.QuestionAdapter;
import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionActivity extends BaseActivity {
    RecyclerView recycler_view;
    QuestionAdapter questionAdapter;
    Map<Integer, String> map = new HashMap<>();
    public static QuestionActivity questionActivity;
    int answerCount = 0; //总数量

    private int ACount = 0; //选项A

    private int BCount = 0; //选项B

    private int CCount = 0;  //选型C

    List<String> StringList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionActivity = this;
        setContentView(R.layout.answer_activity);
        recycler_view = findViewById(R.id.recycler_view);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(linearLayoutManager);


        String[] questions = getResources().getStringArray(R.array.app_question);
        String[] answerA = getResources().getStringArray(R.array.app_answer_A);
        String[] answerB = getResources().getStringArray(R.array.app_answer_B);
        String[] answerC = getResources().getStringArray(R.array.app_answer_C);

        answerCount = questions.length;
        questionAdapter = new QuestionAdapter(this, questions, answerA, answerB, answerC);
        recycler_view.setAdapter(questionAdapter);

        questionAdapter.setOnItemClickListenter(new OnItemClickListenter() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.tv_fin) {
                    if (position != 0) {
                        recycler_view.scrollToPosition(position - 1);
                        questionAdapter.notifyItemChanged(position - 1);
                    }
                } else {
                    if (view.getId() == R.id.tv1) {
                        map.put(position + 1, "A");
                    } else if (view.getId() == R.id.tv2) {
                        map.put(position + 1, "B");
                    } else if (view.getId() == R.id.tv3) {
                        map.put(position + 1, "C");
                    }
                    if (position < answerCount - 1) {
                        recycler_view.scrollToPosition(position + 1);
                        questionAdapter.notifyItemChanged(position + 1);
                    } else {
                        //完成测试
                        showCalculation();
                    }
                }
            }
        });
    }

    private void showCalculation() {
        String[] answer = {"A", "A", "B", "A", "C", "B", "C", "C", "A", "C"};
        String result = null;
        Integer count = 0;
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        System.out.println(entrySet);
        for (Map.Entry<Integer, String> a : entrySet) { // 遍历
            for (int i = 0; i < answer.length; i++) {
                if ((i + 1) == a.getKey()) {
                    System.out.println(a.getKey());
                    if (a.getValue() == answer[i]) {
                        count++;
                    }
                }
            }
        }
        switch (count) {
            case 0:
                result = "0%";
                break;
            case 1:
                result = "10%";
                break;
            case 2:
                result = "20%";
                break;
            case 3:
                result = "30%";
                break;
            case 4:
                result = "40%";
                break;
            case 5:
                result = "50%";
                break;
            case 6:
                result = "60%";
                break;
            case 7:
                result = "70%";
                break;
            case 8:
                result = "80%";
                break;
            case 9:
                result = "90%";
                break;
            case 10:
                result = "100%";
                break;
        }
        Intent intent = new Intent(QuestionActivity.this, OkActivity.class);
        intent.putExtra("stringType", result);
        startActivity(intent);
        QuestionActivity.this.finish();
    }

}
