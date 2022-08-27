package com.example.viewnews.adapter.subscribe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.activity.subscribe.SubscribeDetailActivity;
import com.example.viewnews.bean.SubBean;
import com.example.viewnews.bean.Subscribe;

import org.litepal.LitePal;

import java.util.List;

public class AddSubcribeAdapter extends RecyclerView.Adapter<AddSubcribeAdapter.ViewHolder> {

    private List<Subscribe> subscribeList;
    private SubBean subList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView subscribeImage;
        TextView subscribeName;
        TextView subscribeIntro;
        Button button;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            subscribeImage = (ImageView) view.findViewById(R.id.icon_newsImage);
            subscribeName = (TextView) view.findViewById(R.id.txt_subscribe_newsName);
            subscribeIntro = (TextView) view.findViewById(R.id.txt_subscribe_intro);
            button = view.findViewById(R.id.btn_subscribe);
        }
    }

    public AddSubcribeAdapter(List<Subscribe> fruitList) {
        subscribeList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_subscribe, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Subscribe subscribe1 = subscribeList.get(position);
        holder.subscribeName.setText(subscribe1.getSubscribeName());
        holder.subscribeIntro.setText(subscribe1.getSubscribeIntroduction());
        holder.subscribeImage.setImageResource(subscribe1.getSubscribeImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubscribeDetailActivity.class);
                intent.putExtra(SubscribeDetailActivity.subName, subscribe1.getSubscribeName());
                intent.putExtra(SubscribeDetailActivity.intro, subscribe1.getSubscribeIntroduction());
                intent.putExtra(SubscribeDetailActivity.address, subscribe1.getSubscribeAddress());
                intent.putExtra(SubscribeDetailActivity.ImagePage, String.valueOf(subscribe1.getSubscribeImage()));
                System.out.println(subscribe1.getSubscribeName() + "123456");
                System.out.println(subscribe1.getSubscribeIntroduction() + "123456");
                mContext.startActivity(intent);
            }
        });
        //button的显示如果在数据库中找到相同的Name，button设置为已关注
        List<SubBean> sub1 = LitePal.where("subUserID = ? AND subName=?", MainActivity.currentUserId, subscribe1.getSubscribeName()).find(SubBean.class);
        if (sub1.isEmpty()) {
            System.out.println(sub1);
        } else {
            holder.button.setText("已关注");
            holder.button.setBackgroundColor(Color.parseColor("#EFECEB"));
            holder.button.setTextColor(Color.parseColor("#B6B2B2"));
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(holder.button.getText());
                if (holder.button.getText().equals("已关注")) {
                    System.out.println(holder.button.getText());
                    holder.button.setText("未关注");
                    holder.button.setBackgroundColor(Color.parseColor("#F44336"));
                    holder.button.setTextColor(Color.parseColor("#F8F5F5"));
                    //去数据库查一下，如果有相同的就删除
                    int isOk = LitePal.deleteAll(SubBean.class, "subName = ?", subscribe1.getSubscribeName());
                    if (isOk > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                } else if (holder.button.getText().equals("未关注")) {
                    holder.button.setText("已关注");
                    holder.button.setBackgroundColor(Color.parseColor("#EFECEB"));
                    holder.button.setTextColor(Color.parseColor("#B6B2B2"));
                    //在数据库中添加
                    //System.out.println(subscribe1.getSubscribeImage());
                    SubBean subBean = new SubBean();
                    subBean.setSubName(subscribe1.getSubscribeName());
                    subBean.setSubIntroduction(subscribe1.getSubscribeIntroduction());
                    subBean.setSubUserID(MainActivity.currentUserId);
                    subBean.setSubMine("已关注");
                    subBean.setSubAddress(subscribe1.getSubscribeAddress());
                    subBean.setSubImage(subscribe1.getSubscribeImage());
                    boolean isSave = subBean.save();
                    if (isSave == true) {
                        System.out.println("订阅成功");
                    } else {
                        System.out.println("订阅失败");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subscribeList.size();
    }
}
