package com.example.viewnews.activity.usermodel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewnews.adapter.usermodel.MsgAdapter;
import com.example.viewnews.R;
import com.example.viewnews.bean.Msg;
import com.example.viewnews.tools.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private List msgList = new ArrayList();
    private String userIdNumber;
    private String userImgIconPath;
    private static int flag = 1;
    private static float recordValue = 0.0f;
    private static float fontScaleValue = 0.0f;
    private static boolean ifFontScaleValueChanged = false; // 起初没有改变 即二者一样，boolean值为false
    private ImageView userIcon;

    private ImageView icon_return;
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;

    private TextView textView_chat_left_msg;
    private TextView textView_chat_right_msg;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        System.out.println("static ----------------????????????????????????????????-----------------" + flag);
        System.out.println("static ----------------????????????????????????????????-----------------" + recordValue);
        System.out.println("static ----------------????????????????????????????????-----------------" + fontScaleValue);
        System.out.println("static ----------------????????????????????????????????-----------------" + ifFontScaleValueChanged);

        //初次的话显示默认值，非初次的话显示fontScaleValue值

        // 取value
        Intent intent = getIntent();
        if (flag == 0 || flag == 2 || fontScaleValue != recordValue) {    //是第一次进入活动 或 想修改字体大小
            if (flag == 0) {
                System.out.println("从MainActivty中进入");
            } else if (flag == 2) {
                System.out.println("从字体设置中进入");
                recordValue = fontScaleValue;   //记录改之前的字体大小值

                fontScaleValue = intent.getFloatExtra("fontScaleValue", fontScaleValue);    //赋新的字体大小值
                System.out.println("/////////////save++++++++++++++++++++ChatActivity says the ifFontScaleValueChanged is " + recordValue);
                System.out.println("//////////////save+++++++++++++++++++ChatActivity says the fontScaleValue is " + fontScaleValue);
                System.out.println("//////////////save++++++++++++++++++++ChatActivity says the ifFontScaleValueChanged is " + ifFontScaleValueChanged);
                if (fontScaleValue != recordValue) {
                    ifFontScaleValueChanged = true;
                } else {
                    ifFontScaleValueChanged = false;
                }
            }
        }
        System.out.println("///////////////////////////////////ChatActivity says the ifFontScaleValueChanged is " + recordValue);
        System.out.println("///////////////////////////////////ChatActivity says the fontScaleValue is " + fontScaleValue);
        System.out.println("///////////////////////////////////ChatActivity says the ifFontScaleValueChanged is " + ifFontScaleValueChanged);


        userIdNumber = getIntent().getStringExtra("user_consult_id");
        userImgIconPath = getIntent().getStringExtra("user_image_cion_path");
        System.out.println("当前用户的账号为" + userIdNumber);
        System.out.println("当前用户的头像图片路径为" + userImgIconPath);

        initMsgs(); // 初始化消息数据

        inputText = (EditText) findViewById(R.id.et_content);
        send = (Button) findViewById(R.id.btn_send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        icon_return = findViewById(R.id.ic_return_left);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);

        adapter = new MsgAdapter(msgList, this);
        msgRecyclerView.setAdapter(adapter);
        if (isIfFontScaleValueChanged() == true) {
            finish();
            setIfFontScaleValueChanged(false);    // ChatActivity已经知晓，已经记录下字体大小，可以finish活动，不需要显示活动界面
        }

        //点击“发送”，发送消息
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    // 当有新消息时，刷新ListView中的显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    // 将ListView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    // 清空输入框中的内容
                    inputText.setText("");
                    response(msg.getContent());
                }
            }
        });
        //返回事件
        icon_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 初始化聊天消息
     */
    private void initMsgs() {
        Msg original_msg = new Msg("你好呀！我是小智，有什么问题可以问我嗷~\n\n" +
                "\t\t1.功能说明\n" +
                "\t\t2.关于看点新闻\n" +
                "\t\t3.如何修改字体大小\n" +
                "\t\t4.如何查看新闻看点号\n\n" +
                "\t（温馨提示：请向小智发送以上问题的序号进行咨询）", Msg.TYPE_RECEIVED);
        msgList.add(original_msg);
    }

    /**
     * 小智回复
     *
     * @param content
     */
    public void response(String content) {
        switch (content) {
            case "1":
                msgList.add(new Msg("您可以点击首页下方“设置” → “关于看点新闻” → “功能介绍”进行查看~", Msg.TYPE_RECEIVED));
                break;
            case "2":
                msgList.add(new Msg("您可以点击首页下方“设置” → “关于看点新闻”查看看点新闻app的功能介绍、服务协议、隐私权政策嗷~", Msg.TYPE_RECEIVED));
                break;
            case "3":
                msgList.add(new Msg("您可以点击首页下方“设置” → “隐私和通用” → “字体大小”设置您喜欢的字体大小嗷~", Msg.TYPE_RECEIVED));
                break;
            case "4":
                msgList.add(new Msg("在“我的订阅”中查看自己已订阅的某些新闻看点号，也可以在“我的订阅” → “添加”中查看本app中提供的所有新闻看点号，新闻看点号官方具体的内容可通过点击具体的新闻看点号进行查看", Msg.TYPE_RECEIVED));
                break;
            default:
                msgList.add(new Msg("抱歉啦，这个问题我不太清楚呢~但是以下这些问题我可是了如指掌嗷\n\n" +
                        "\t\t1.功能说明\n" +
                        "\t\t2.关于看点新闻\n" +
                        "\t\t3.如何修改字体大小\n" +
                        "\t\t4.如何查看新闻看点号\n\n" +
                        "\t（温馨提示：请向小智发送以上问题的序号进行咨询）", Msg.TYPE_RECEIVED));
                break;
        }
    }

    /**
     * 解析、展示图片
     *
     * @param imagePath
     */
    private void displayImage(String imagePath) {
        System.out.println("ChatActivity says display icon image");
        userIcon = findViewById(R.id.iv_consult_right_avatar);
        if (!TextUtils.isEmpty(imagePath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (userIcon == null) {
                Log.d("ManiActivity", "userIcon is null");
            } else {
                Log.d("ManiActivity", "set userIcon from imagePath.");
                userIcon.setImageBitmap(bitmap);
            }
        } else {
            // Android系统资源图标
            userIcon.setImageResource(R.drawable.no_login_avatar);
        }
    }

    public String getUserImgIconPath() {
        return userImgIconPath;
    }

    public float getFontScaleValue() {
        return fontScaleValue;
    }

    public static boolean isIfFontScaleValueChanged() {
        return ifFontScaleValueChanged;
    }

    public static void setIfFontScaleValueChanged(boolean ifFontScaleValueChanged) {
        ChatActivity.ifFontScaleValueChanged = ifFontScaleValueChanged;
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        ChatActivity.flag = flag;
    }
}
