package com.example.viewnews.adapter.settings;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.R;
import com.example.viewnews.bean.SettingInfo;
import com.example.viewnews.activity.settings.FunctionIntroActivity;
import com.example.viewnews.activity.settings.PrivacyPolicyActivity;
import com.example.viewnews.activity.settings.ServicePolicyActivity;

import java.util.List;

public class AboutAppAdapter extends RecyclerView.Adapter<AboutAppAdapter.ViewHolder> {

    private List<SettingInfo> mSettingInfoList;
    private Context context;

    public AboutAppAdapter() {
    }

    public AboutAppAdapter(Context context) {
        this.context = context;
    }

    public AboutAppAdapter(List<SettingInfo> settingList, Context context) {
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
        holder.settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SettingInfo settingItem = mSettingInfoList.get(position);
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
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        SettingInfo settingItem = mSettingInfoList.get(position);
        viewHolder.settingItemImage.setImageResource(settingItem.getSettingId());
        viewHolder.settingItemName.setText(settingItem.getSettingName());
    }

    @Override
    public int getItemCount() {
        return mSettingInfoList.size();
    }

    public void enterActivity(String settingItemName) {
        switch (settingItemName) {
            case "功能介绍":
                Log.d("AboutAppAdapter:", "功能介绍");
                Intent functionIntro = new Intent(context, FunctionIntroActivity.class);
                context.startActivity(functionIntro);
                break;
            case "服务协议":
                Log.d("AboutAppAdapter:", "服务协议");
                Intent servicePolicy = new Intent(context, ServicePolicyActivity.class);
                context.startActivity(servicePolicy);
                break;
            case "隐私权政策":
                Log.d("AboutAppAdapter:", "隐私权政策");
                Intent privacyPolicy = new Intent(context, PrivacyPolicyActivity.class);
                context.startActivity(privacyPolicy);
                break;
            default:
                break;
        }
    }
}
