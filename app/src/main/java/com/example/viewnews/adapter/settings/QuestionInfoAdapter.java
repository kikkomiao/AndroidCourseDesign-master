package com.example.viewnews.adapter.settings;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.activity.settings.AllQuestionsActivity;
import com.example.viewnews.activity.settings.FeedbackActivity;
import com.example.viewnews.activity.settings.QusResolvementActivity;
import com.example.viewnews.bean.QuestionInfo;

import java.util.List;

public class QuestionInfoAdapter extends RecyclerView.Adapter<QuestionInfoAdapter.ViewHolder> {

    private List<QuestionInfo> questionInfos;
    //    private Context context;
    private FeedbackActivity context;
    private AllQuestionsActivity allQuestionsActivity;
    private int viewType;

    public QuestionInfoAdapter() {
    }

    public QuestionInfoAdapter(FeedbackActivity context) {
        this.context = context;
    }

    public QuestionInfoAdapter(List<QuestionInfo> questionInfos, FeedbackActivity context, int viewType) {
        this.questionInfos = questionInfos;
        this.context = context;
        this.viewType = viewType;
    }

    public QuestionInfoAdapter(List<QuestionInfo> questionInfos, AllQuestionsActivity context, int viewType) {
        this.questionInfos = questionInfos;
        this.allQuestionsActivity = context;
        this.viewType = viewType;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View questionItemView;
        TextView questionItemName;
        ImageView imgviewQuestionIcon;
        ImageView imgviewQuestionEnter;

        public ViewHolder(View view) {
            super(view);
            questionItemView = view;
            questionItemName = (TextView) view.findViewById(R.id.tv_questioninfo_name);
            imgviewQuestionIcon = (ImageView) view.findViewById(R.id.imgview_question_icon);
            imgviewQuestionEnter = (ImageView) view.findViewById(R.id.imgview_question_enter);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("viewType is " + viewType);

        if (this.viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questioninfo, parent, false);
            final ViewHolder holder = new ViewHolder(view);

            holder.questionItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    QuestionInfo qusestionItem = questionInfos.get(position);
//                    Toast.makeText(v.getContext(), "you clicked view " + qusestionItem.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("name is " + qusestionItem.getName() + " and the viewType is 1");
                    System.out.println("from FeedbackActivity enter");
                    Intent qusResolve = new Intent(context, QusResolvementActivity.class);
                    qusResolve.putExtra("layoutID", String.valueOf(qusestionItem.getLayoutID()));
                    String layoutID = qusResolve.getStringExtra("layoutID");
                    System.out.println("ID is " + layoutID);
                    context.startActivity(qusResolve);
                }
            });

            return holder;
        } else if (this.viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_questioninfo, parent, false);
            final ViewHolder holder = new ViewHolder(view);

            holder.questionItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    QuestionInfo qusestionItem = questionInfos.get(position);
//                    Toast.makeText(v.getContext(), "you clicked view " + qusestionItem.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("name is " + qusestionItem.getName() + " and the viewType is 1");
                    System.out.println("from AllQuestionsActivity enter");
                    Intent qusResolve = new Intent(allQuestionsActivity, QusResolvementActivity.class);
                    qusResolve.putExtra("layoutID", String.valueOf(qusestionItem.getLayoutID()));
                    String layoutID = qusResolve.getStringExtra("layoutID");
                    System.out.println("ID is " + layoutID);
                    allQuestionsActivity.startActivity(qusResolve);
                }
            });
            holder.imgviewQuestionIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    QuestionInfo qusestionItem = questionInfos.get(position);
//                    Toast.makeText(v.getContext(), "you clicked view " + qusestionItem.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("name is " + qusestionItem.getName() + " and the viewType is 1");
                    System.out.println("from AllQuestionsActivity enter");
                    Intent qusResolve = new Intent(allQuestionsActivity, QusResolvementActivity.class);
                    qusResolve.putExtra("layoutID", String.valueOf(qusestionItem.getLayoutID()));
                    String layoutID = qusResolve.getStringExtra("layoutID");
                    System.out.println("ID is " + layoutID);
                    allQuestionsActivity.startActivity(qusResolve);
                }
            });
            holder.imgviewQuestionEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    QuestionInfo qusestionItem = questionInfos.get(position);
//                    Toast.makeText(v.getContext(), "you clicked view " + qusestionItem.getName(), Toast.LENGTH_SHORT).show();
                    System.out.println("name is " + qusestionItem.getName() + " and the viewType is 1");
                    System.out.println("from AllQuestionsActivity enter");
                    Intent qusResolve = new Intent(allQuestionsActivity, QusResolvementActivity.class);
                    qusResolve.putExtra("layoutID", String.valueOf(qusestionItem.getLayoutID()));
                    String layoutID = qusResolve.getStringExtra("layoutID");
                    System.out.println("ID is " + layoutID);
                    allQuestionsActivity.startActivity(qusResolve);
                }
            });

            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QuestionInfo questionItem = questionInfos.get(position);
        holder.questionItemName.setText(questionItem.getName());
        if (viewType == 2) {
            holder.imgviewQuestionIcon.setImageResource(questionItem.getImageID());
            holder.imgviewQuestionEnter.setImageResource(questionItem.getEnterID());
        }
    }

    @Override
    public int getItemCount() {
//        System.out.println("size is " + questionInfos.size());
        return questionInfos.size();
    }
}
