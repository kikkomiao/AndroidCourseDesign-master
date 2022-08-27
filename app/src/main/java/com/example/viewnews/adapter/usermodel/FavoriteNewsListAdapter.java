package com.example.viewnews.adapter.usermodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.bean.NewsCollectBean;
import com.example.viewnews.bean.NewsInfoBean;
import com.example.viewnews.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNewsListAdapter extends ArrayAdapter<NewsInfoBean> {

    private String userID = MainActivity.currentUserId;
    private int resourceId;
    public final int TYPE_EMPTY = 0;
    public final int TYPE_NORMAL = 1;
    private List<NewsCollectBean> sonNewList = new ArrayList<>();
    private List<NewsInfoBean> contentList = new ArrayList<>();

    public FavoriteNewsListAdapter(Context context, int textViewResourceId, List<NewsInfoBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsInfoBean newsInfoBean = getItem(position);
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }
        TextView newsName = (TextView) view.findViewById(R.id.title);
        newsName.setText(newsInfoBean.getTitle());
        TextView autherName = (TextView) view.findViewById(R.id.author_name);
        autherName.setText(newsInfoBean.getAuthor_name());
        Glide.with(view.getContext())
                .load(newsInfoBean.getThumbnail_pic_s())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.defaultbg)
                .error(R.drawable.love)
                .into(view.<ImageView>findViewById(R.id.image));

        return view;
    }
}