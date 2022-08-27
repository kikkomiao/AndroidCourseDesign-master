package com.example.viewnews.adapter.usermodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.viewnews.R;
import com.example.viewnews.activity.usermodel.ChatActivity;
import com.example.viewnews.bean.Msg;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter {

    private List<Msg> mMsgList;
    private ChatActivity context;
    private TextView textView_chat_left_msg;
    private TextView textView_chat_right_msg;

    public MsgAdapter(List msgList, ChatActivity context) {
        System.out.println("1111111111111111111111111111111111 context is " + context);
        mMsgList = msgList;
        this.context = context;
        System.out.println("1111111111111111111111111111111111 MsgAdapter says this.context is " + this.context);
    }

    /**
     * 创建 ViewHolder 加载 RecycleView 子项的布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);

        float sizeValue = this.context.getFontScaleValue();
        String userImgIconPath = this.context.getUserImgIconPath();

        System.out.println("userImgIconPath is " + userImgIconPath);
        System.out.println("----------------MsgAdapter says the sizeValue is " + sizeValue);

        if (sizeValue != 0.0f) {
            return new ViewHolder(view, userImgIconPath, sizeValue);
        } else {
            return new ViewHolder(view, userImgIconPath);
        }
    }

    /**
     * 为 RecycleView 子项赋值
     * 赋值通过 position 判断子项位置
     * 当子项进入界面时执行
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            ViewHolder.leftLayout.setVisibility(View.VISIBLE);
            ViewHolder.rightLayout.setVisibility(View.GONE);
            ViewHolder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SENT) {
            // 如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            ViewHolder.rightLayout.setVisibility(View.VISIBLE);
            ViewHolder.leftLayout.setVisibility(View.GONE);
            ViewHolder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    static LinearLayout leftLayout;

    static LinearLayout rightLayout;

    static TextView leftMsg;

    static TextView rightMsg;

    static ImageView rightIcon;

    static TextView textView_chat_left_msg;
    static TextView textView_chat_right_msg;

    // view表示父类的布局，用其获取子项
    public ViewHolder(View view, String userImgIconPath, float sizeValue) {
        super(view);
        leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
        rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
        leftMsg = (TextView) view.findViewById(R.id.left_msg);
        rightMsg = (TextView) view.findViewById(R.id.right_msg);
        rightIcon = (ImageView) view.findViewById(R.id.iv_consult_right_avatar);
        System.out.println("userImgIconPath is " + userImgIconPath);
        displayImage(userImgIconPath);

        System.out.println("----------------MsgAdapter says the fontScaleValue is " + sizeValue);
        // 设置TextView的字体
        textView_chat_left_msg = (TextView) view.findViewById(R.id.left_msg);
        textView_chat_right_msg = (TextView) view.findViewById(R.id.right_msg);
        System.out.println("MsgAdapter direct get textView left_msg is " + textView_chat_left_msg);
        System.out.println("MsgAdapter direct get textView right_msg is " + textView_chat_right_msg);
        textView_chat_left_msg.setTextSize(sizeValue);
        textView_chat_left_msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeValue);
        textView_chat_right_msg.setTextSize(sizeValue);
        textView_chat_right_msg.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sizeValue);
        System.out.println("0000000000000000000000000000000+++++++++++++++++++++++++++++++++++000000000000000000000000000000000finish");
    }

    public ViewHolder(View view, String userImgIconPath) {
        super(view);
        leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
        rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
        leftMsg = (TextView) view.findViewById(R.id.left_msg);
        rightMsg = (TextView) view.findViewById(R.id.right_msg);
        rightIcon = (ImageView) view.findViewById(R.id.iv_consult_right_avatar);
        System.out.println("userImgIconPath is " + userImgIconPath);
        displayImage(userImgIconPath);
    }

    /**
     * 解析、展示图片
     *
     * @param imagePath
     */
    private void displayImage(String imagePath) {
        System.out.println("MsgAdapter says display icon image");
        if (!TextUtils.isEmpty(imagePath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (rightIcon == null) {
                Log.d("MsgAdapter", "userIcon is null");
            } else {
                Log.d("MsgAdapter", "set userIcon from imagePath.");
                rightIcon.setImageBitmap(bitmap);
            }
        } else {
            System.out.println("yes system resource!!!");
            // Android系统资源图标
            rightIcon.setImageResource(R.drawable.no_login_avatar);
        }
    }
}
