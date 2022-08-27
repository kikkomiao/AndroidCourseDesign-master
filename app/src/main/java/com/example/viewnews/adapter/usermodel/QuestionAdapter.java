package com.example.viewnews.adapter.usermodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.activity.usermodel.QuestionActivity;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private String[] question;
    private String[] answerA;
    private String[] answerB;
    private String[] answerC;

    private OnItemClickListenter onItemClickListenter;
    private ImageView imageView;
    private LayoutInflater layoutInflater;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_content;
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv_fin;

        public ViewHolder(View view) {
            super(view);
            tv_content = view.findViewById(R.id.tv_content);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
            tv3 = view.findViewById(R.id.tv3);
            tv_fin = view.findViewById(R.id.tv_fin);
            imageView = view.findViewById(R.id.img_answer_question);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestionActivity.questionActivity.finish();
                }
            });
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            tv_fin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }

    public QuestionAdapter(Context context, String[] question, String[] answerA, String[] answerB, String[] answerC) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View viewHolder = layoutInflater.inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_content.setText((position + 1) + "." + question[position]);
        holder.tv1.setText(("A" + "." + answerA[position]));
        holder.tv2.setText("B" + "." + answerB[position]);
        holder.tv3.setText("C" + "." + answerC[position]);
        if (position == getItemCount() - 1) {
            holder.tv_fin.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("question" + question.length);
        return question.length;
    }
}
