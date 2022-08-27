package com.example.viewnews.adapter.subscribe;

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
import com.example.viewnews.activity.subscribe.MySubscribeActivity;
import com.example.viewnews.activity.subscribe.SubscribeDetailActivity;
import com.example.viewnews.bean.SubBean;

import org.litepal.LitePal;

import java.util.List;

public class MySubscribeAdapter extends RecyclerView.Adapter<MySubscribeAdapter.ViewHolder> {

    private List<SubBean> subscribeList;
    private SubBean subList;
    //    private Context mContext;
    private MySubscribeActivity mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView subscribeImage;
        TextView subscribeName;
        TextView subscribeIntro;
        Button btn;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            subscribeImage = (ImageView) view.findViewById(R.id.icon_newsImage);
            subscribeName = (TextView) view.findViewById(R.id.txt_subscribe_newsName);
            subscribeIntro = (TextView) view.findViewById(R.id.txt_subscribe_intro);
            btn = view.findViewById(R.id.btn_subscribe);
        }
    }

    public MySubscribeAdapter(MySubscribeActivity mContext, List<SubBean> fruitList) {
        this.mContext = mContext;
        subscribeList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_subscribe, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final SubBean sub = subscribeList.get(position);
        holder.subscribeName.setText(sub.getSubName());
        holder.subscribeIntro.setText(sub.getSubIntroduction());
        holder.subscribeImage.setImageResource(sub.getSubImage());
        holder.btn.setText("已关注");
        holder.btn.setBackgroundColor(Color.parseColor("#EFECEB"));
        holder.btn.setTextColor(Color.parseColor("#B6B2B2"));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SubscribeDetailActivity.class);
                intent.putExtra(SubscribeDetailActivity.subName, sub.getSubName());
                intent.putExtra(SubscribeDetailActivity.intro, sub.getSubIntroduction());
                intent.putExtra(SubscribeDetailActivity.address, sub.getSubAddress());
                intent.putExtra(SubscribeDetailActivity.ImagePage, String.valueOf(sub.getSubImage()));
                mContext.startActivity(intent);
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(holder.btn.getText());
                if (holder.btn.getText().equals("已关注")) {
                    System.out.println(holder.btn.getText());
                    holder.btn.setText("未关注");
                    holder.btn.setBackgroundColor(Color.parseColor("#F44336"));
                    holder.btn.setTextColor(Color.parseColor("#F8F5F5"));
                    //去数据库查一下，如果有相同的就删除
                    int isOk = LitePal.deleteAll(SubBean.class, "subName = ?", sub.getSubName());
                    if (isOk > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                } else if (holder.btn.getText().equals("未关注")) {
                    holder.btn.setText("已关注");
                    holder.btn.setBackgroundColor(Color.parseColor("#EFECEB"));
                    holder.btn.setTextColor(Color.parseColor("#B6B2B2"));
                    //在数据库中添加
                    //System.out.println(subscribe1.getSubscribeImage());
                    SubBean subBean = new SubBean();
                    subBean.setSubName(sub.getSubName());
                    subBean.setSubIntroduction(sub.getSubIntroduction());
                    subBean.setSubUserID(MainActivity.currentUserId);
                    subBean.setSubMine("已关注");
                    subBean.setSubAddress(sub.getSubAddress());
                    subBean.setSubImage(sub.getSubImage());
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
