package com.example.viewnews.tools;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.viewnews.R;

public class MyPopWin extends PopupWindow {

    private Context mContext;

    private View view;

    private Button btn_conf_modify;
    private Button btn_cancel_modify;
    public EditText et_old_pwd;
    public EditText et_new_pwd;
    public EditText et_conf_pwd;

    public MyPopWin(Activity mContext, View.OnClickListener itemsOnClick) {

        this.mContext = mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.modify_pwd, null);

        et_old_pwd = (EditText) view.findViewById(R.id.et_old_pwd);
        et_new_pwd = (EditText) view.findViewById(R.id.et_new_pwd);
        et_conf_pwd = (EditText) view.findViewById(R.id.et_conf_pwd);

        btn_conf_modify = (Button) view.findViewById(R.id.btn_conf_modify);
        btn_cancel_modify = (Button) view.findViewById(R.id.btn_cancel_modify);

        // 设置按钮监听
        btn_conf_modify.setOnClickListener(itemsOnClick);
        btn_cancel_modify.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);

        // 设置弹出窗体的宽和高
        /*
         * 获取窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window对象,
         * 这样就可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = mContext.getWindow();

        WindowManager m = mContext.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
//        this.setWidth((int) (d.getWidth() * 0.8));
        this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

    }

}
