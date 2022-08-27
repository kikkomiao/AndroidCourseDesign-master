package com.example.viewnews.adapter.usermodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.activity.usermodel.DelCommentActivity;
import com.example.viewnews.bean.commentBean;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private static final String TAG = "ArticleAdapter";


    private Context mContext;

    private List<commentBean> mCommentList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView userImage;
        TextView userName;
        TextView CommentTime;
        TextView commentContent;
        TextView delComment;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            userImage = (ImageView) view.findViewById(R.id.comment_image);
            userName = (TextView) view.findViewById(R.id.comment_name);
            CommentTime = (TextView) view.findViewById(R.id.comment_time);
            commentContent = (TextView) view.findViewById(R.id.txt_comment_view);
            delComment = (TextView) view.findViewById(R.id.txt_comment_delete);
        }
    }

    public CommentAdapter(List<commentBean> fruitList) {
        mCommentList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d(String.valueOf(position), "12");
        System.out.println("comment" + position);
        final commentBean commentbean = mCommentList.get(position);
        Log.d(commentbean.getCommentContext(), "9090");
        holder.userName.setText(commentbean.getUserName());
        holder.CommentTime.setText(commentbean.getCommentTime());
        holder.commentContent.setText(commentbean.getCommentContext());
        if (commentbean.getUserId().equals(MainActivity.currentUserId)) {
            holder.delComment.setText("删除");
            holder.delComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    commentBean commentContext = mCommentList.get(position);
                    Intent intent = new Intent(mContext, DelCommentActivity.class);
                    intent.putExtra(DelCommentActivity.CommentContent, commentContext.getCommentContext());
                    intent.putExtra(DelCommentActivity.urlKeys, commentContext.getUrlKey());
                    mContext.startActivity(intent);
                }
            });
        }
        //使用Glide来加载图片，with方法传入一个Context、Activity或Fragment参数，最后调用load()方法去加载图片在
        Glide.with(mContext)
                .load(commentbean.getImagePath())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.ic_launcher_foreground)
                .error(R.mipmap.ic_launcher_foreground)
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }
}
