package com.example.viewnews.adapter.usermodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.activity.usermodel.ArticleDetailActivity;
import com.example.viewnews.bean.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private static final String TAG = "ArticleAdapter";

    private Context mContext;
    public final int TYPE_EMPTY = 0;
    public final int TYPE_NORMAL = 1;
    private List<Article> mArticleList;
    private long id;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView articleImage;
        TextView articleTitle;
        TextView articleTime;
        TextView articleContext;
        TextView pageChange;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            articleImage = (ImageView) view.findViewById(R.id.article_image);
            articleTitle = (TextView) view.findViewById(R.id.article_name);
            articleTime = (TextView) view.findViewById(R.id.article_time);
            articleContext = (TextView) view.findViewById(R.id.article_context);
            pageChange = (TextView) view.findViewById(R.id.page_change);
        }
    }

    public ArticleAdapter(List<Article> fruitList) {
        mArticleList = fruitList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mArticleList.size() == 0) {
            System.out.println("empty");
            return TYPE_EMPTY;
        } else {
            System.out.println("normal");
            return TYPE_NORMAL;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view;
        if (viewType == TYPE_EMPTY) {
            view = LayoutInflater.from(mContext).inflate(R.layout.page_empty, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_article, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            //给cardView注册了一个监听器,当点击时,构造一个Intent并带到ArticleActivity活动中
            holder.pageChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("getAdapterPosition!!!!!" + holder.getAdapterPosition());
                    int position = holder.getAdapterPosition();
                    Article article = mArticleList.get(position);
                    Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                    intent.putExtra(ArticleDetailActivity.ARTICLE_IMAGE_ID, article.getArticleImagePath());
                    System.out.println("图片!!!!!!!!!!!!!!!!" + article.getArticleImagePath());
                    intent.putExtra(ArticleDetailActivity.ARTICLE_NAME, article.getArticleTitle());
                    intent.putExtra(ArticleDetailActivity.ARTICLE_TIME, article.getArticleTime());
                    intent.putExtra(ArticleDetailActivity.ARTICLE_ID, article.getId());
                    System.out.println("adapterID" + article.getId());
                    mContext.startActivity(intent);
                    System.out.println("点击！！！！！");
                }
            });
            return holder;
        }
    }

    @Override
    public int getItemCount() {
        System.out.println(mArticleList.size() + "size");
        if (mArticleList.size() == 0) {
            return 1;
        }
        return mArticleList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println("position" + position);
        if (mArticleList.size() == 0) {
            System.out.println(holder);
        } else {
            System.out.println(position + "wenzhang");
            Article article = mArticleList.get(position);
            holder.articleTitle.setText(article.getArticleTitle());
            holder.articleTime.setText(article.getArticleTime());
            holder.articleContext.setText(article.getArticleContent());
            //使用Glide来加载图片，with方法传入一个Context、Activity或Fragment参数，最后调用load()方法去加载图片在
//            Glide.with(mContext)
//                    .load(article.getArticleImagePath())
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .placeholder(R.drawable.defaultbg)
//                    .error(R.drawable.defaultbg)
//                    .into(holder.articleImage);
        }
    }
}
