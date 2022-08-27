package com.example.viewnews.adapter.settings;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.activity.settings.SettingActivity;
import com.example.viewnews.activity.settings.AboutAppActivity;
import com.example.viewnews.activity.settings.AccountActivity;
import com.example.viewnews.activity.settings.FeedbackActivity;
import com.example.viewnews.activity.settings.PrivacyAndCurrencyActivity;
import com.example.viewnews.bean.SettingInfo;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private List<SettingInfo> mSettingInfoList;
    //    private Context context;
    private SettingActivity context;

    public SettingAdapter() {
    }

    public SettingAdapter(SettingActivity context) {
        this.context = context;
    }

    public SettingAdapter(List<SettingInfo> settingList, SettingActivity context) {
        mSettingInfoList = settingList;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View settingItemView;
        ImageView settingItemImage;
        TextView settingItemName;

        public ViewHolder(View view) {
            super(view);
            settingItemView = view;
            settingItemImage = (ImageView) view.findViewById(R.id.setting_image);
            settingItemName = (TextView) view.findViewById(R.id.setting_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settinginfo, parent, false);
        final ViewHolder holder = new ViewHolder(view);

//        holder.settingItemView.setOnHoverListener(new View.OnHoverListener() {
//            @Override
//            public boolean onHover(View v, MotionEvent event) {
//                int action = event.getAction();
//
//                switch (action) {
//                    case MotionEvent.ACTION_HOVER_ENTER:
//                        v.setBackgroundColor(500070);//鼠标悬停的selector
//                        break;
//                    case MotionEvent.ACTION_HOVER_EXIT:
//                        v.setBackgroundColor(500172);//没有鼠标时背景透明
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
        holder.settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SettingInfo settingItem = mSettingInfoList.get(position);
//                v.setBackgroundColor(500070);
//                Toast.makeText(v.getContext(), "you clicked view " + settingItem.getSettingName(), Toast.LENGTH_SHORT).show();
                enterActivity(settingItem.getSettingName());
            }
        });
        holder.settingItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SettingInfo settingItem = mSettingInfoList.get(position);
//                Toast.makeText(v.getContext(), "you clicked image " + settingItem.getSettingName(), Toast.LENGTH_SHORT).show();
                enterActivity(settingItem.getSettingName());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingInfo settingItem = mSettingInfoList.get(position);
        holder.settingItemImage.setImageResource(settingItem.getSettingId());
        holder.settingItemName.setText(settingItem.getSettingName());
    }

    @Override
    public int getItemCount() {
        return mSettingInfoList.size();
    }

    public void enterActivity(String settingItemName) {
        switch (settingItemName) {
            case "账号与安全":
                Log.d("SettingAdapter:", "账号与安全");
                String userID = context.getUserID();
                System.out.println("SettingAdapter says userID is " + userID);
                Intent accountManage = new Intent(context, AccountActivity.class);
                accountManage.putExtra("user_id", userID);
                context.startActivity(accountManage);
                break;
            case "隐私和通用":
                Log.d("SettingAdapter:", "隐私和通用");
                Intent privacyAndCurrency = new Intent(context, PrivacyAndCurrencyActivity.class);
                context.startActivity(privacyAndCurrency);
                break;
            case "关于看点新闻":
                Log.d("SettingAdapter:", "关于看点新闻");
                Intent aboutApp = new Intent(context, AboutAppActivity.class);
                context.startActivity(aboutApp);
                break;
            case "意见反馈":
                Log.d("SettingAdapter:", "意见反馈");
                Intent feedback = new Intent(context, FeedbackActivity.class);
                context.startActivity(feedback);
                break;
            default:
                break;
        }
    }
}
