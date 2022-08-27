package com.example.viewnews.adapter.settings;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.bean.SettingInfo;
import com.example.viewnews.activity.settings.AccountActivity;
import com.example.viewnews.tools.MyPopWin;
import com.example.viewnews.tools.ActivityCollector;
import com.example.viewnews.bean.UserInfo;

import org.litepal.LitePal;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AccountManageAdapter extends RecyclerView.Adapter<AccountManageAdapter.ViewHolder> {

    private List<SettingInfo> mSettingInfoList;
    private Context context;
    private AccountActivity activity;
    private MyPopWin myPopWin;

    private String userID;
    private UserInfo userInfo;

    public AccountManageAdapter() {
    }

    public AccountManageAdapter(AccountActivity context) {
        this.context = context;
    }

    public AccountManageAdapter(AccountActivity accountActivity, List<SettingInfo> settingList, Context context) {
        this.activity = accountActivity;
        this.mSettingInfoList = settingList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View settingItemView;
        ImageView settingItemImage;
        TextView settingItemName;

        public ViewHolder(View view) {
            super(view);
            System.out.println("ViewHolder");
            settingItemView = view;
            settingItemImage = (ImageView) view.findViewById(R.id.setting_image);
            settingItemName = (TextView) view.findViewById(R.id.setting_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settinginfo, parent, false);
        final AccountManageAdapter.ViewHolder holder = new ViewHolder(view);

        holder.settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SettingInfo settingItem = mSettingInfoList.get(position);
//                v.setBackgroundColor(500070);
//                Toast.makeText(v.getContext(), "you clicked view " + settingItem.getSettingName(), Toast.LENGTH_SHORT).show();
                showPopWindow(settingItem.getSettingName());
            }
        });
        holder.settingItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SettingInfo settingItem = mSettingInfoList.get(position);
//                Toast.makeText(v.getContext(), "you clicked image " + settingItem.getSettingName(), Toast.LENGTH_SHORT).show();
                showPopWindow(settingItem.getSettingName());
            }
        });

        userID = activity.getUserID();
        System.out.println("AccountManageAdapter says userID is " + userID);
        //此处用litepal去查询数据库，查询用户输入的密码是否正确，其中账号是唯一标识
        List<UserInfo> userInfos = LitePal.where("userAccount = ?", userID).find(UserInfo.class);
        userInfo = userInfos.get(0);

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

    public void showPopWindow(String settingItemName) {
        switch (settingItemName) {
            case "更改登录密码":
                Log.d("AccountManageAdapter:", "更改登录密码");
                //背景变暗

                //弹出自定义界面的弹出框
                myPopWin = new MyPopWin(activity, onModifyClickListener);
                myPopWin.showAtLocation(activity.findViewById(R.id.view_account_manage), Gravity.CENTER, 0, 0);
                break;
            case "注销账号":
                Log.d("AccountManageAdapter:", "注销账号");
                SweetAlertDialog mDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("提示")
                        .setContentText("您确定注销当前的看点新闻APP账号吗？此操作将永久删除您的账号信息")
                        .setCustomImage(null)
                        .setCancelText("取消")
                        .setConfirmText("确定")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                int isOk = LitePal.deleteAll(UserInfo.class, "userAccount = ?", userID);
                                if (isOk > 0) {
                                    if (deleteData()) {
                                        Toast.makeText(activity, "注销成功！", Toast.LENGTH_SHORT).show();
                                        ActivityCollector.finishAll();
                                        Intent intent = new Intent(activity, MainActivity.class);
                                        intent.putExtra("user_id_default", "");
                                        intent.putExtra("user_nick_default", "请先登录");
                                        intent.putExtra("user_sign_default", "请先登录");
                                        intent.putExtra("image_path_default", "");
                                        activity.setResult(RESULT_OK, intent);
//                                        activity.startActivityForResult(intent, 0);
                                        activity.startActivity(intent);
                                    } else {
                                        Log.d("AccountManageAdapter", "delete occurres wrong");
                                    }
                                } else {
                                    Toast.makeText(activity, "注销失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                mDialog.show();
                break;
            default:
                break;
        }
    }

    public View.OnClickListener onModifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_conf_modify:
                    Boolean checkResult = checkContent(v);
                    if (checkResult) {
                        //修改密码
                        String newPwd = myPopWin.et_new_pwd.getText().toString().trim();
                        userInfo.setUserPwd(newPwd);
                        if (userInfo.save()) {
                            Toast.makeText(v.getContext(), "修改成功！", Toast.LENGTH_SHORT).show();
                            myPopWin.et_old_pwd.setText("");
                            myPopWin.et_new_pwd.setText("");
                            myPopWin.et_conf_pwd.setText("");
                            myPopWin.dismiss();
                        } else {
                            Toast.makeText(v.getContext(), "修改失败！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                    }
                    break;
                case R.id.btn_cancel_modify:
                    System.out.println("you click cancel!");
                    myPopWin.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    public boolean checkContent(View v) {
        Boolean flag = false;
        String oldPwd = myPopWin.et_old_pwd.getText().toString().trim();
        String newPwd = myPopWin.et_new_pwd.getText().toString().trim();
        String confPwd = myPopWin.et_conf_pwd.getText().toString().trim();

        System.out.println(oldPwd + "——" + newPwd + "——" + confPwd);

        String password = userInfo.getUserPwd();
        System.out.println("查到的用户密码为：" + password);

        if (oldPwd.equals("")) {
            Toast.makeText(v.getContext(), "原密码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (newPwd.equals("")) {
            Toast.makeText(v.getContext(), "新密码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (confPwd.equals("")) {
            Toast.makeText(v.getContext(), "确认密码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (!newPwd.equals(confPwd)) {
            Toast.makeText(v.getContext(), "新密码与确认密码不一致！", Toast.LENGTH_SHORT).show();
        } else if (!oldPwd.equals(password)) {
            Toast.makeText(v.getContext(), "原密码错误！", Toast.LENGTH_SHORT).show();
        } else {
            flag = true;
        }

        return flag;
    }

    public Boolean deleteData() {
        //  /data/data/com.example.viewnews/files/data
        File file = new File("/data/data/com.example.viewnews/files/data");
        if (file != null) {
            System.out.println("enter66666666666666");
            file.delete();
        }
        System.out.println("是否存在？" + file.exists());
        return !file.exists();
    }

}
