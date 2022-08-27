package com.example.viewnews.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.viewnews.activity.usermodel.ChatActivity;
import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.tools.ActivityCollector;
import com.example.viewnews.tools.BaseActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FontSizeActivity extends BaseActivity {

    private ImageView ic_return_left_font_size;
    private TextView tv_note;
    private SeekBar seekBar_text_size;

    private TextView textView_chat_left_msg;
    private TextView textView_chat_right_msg;

    private static int seekbarCurrentProgress = 1;
    private static int currentProgress = 1;
    private static int recordProgress = 1;
    private boolean isChanged = false;  //默认没有改变(值为false)  为true时代表有改变
    private float value = 0.5f + 0.5f * 35;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_size);

        ic_return_left_font_size = (ImageView) findViewById(R.id.ic_return_left_font_size);
        tv_note = (TextView) findViewById(R.id.tv_note);
        seekBar_text_size = (SeekBar) findViewById(R.id.seekBar_text_size);

        // 加载msg_item布局 使其布局中的TextView的控件ID值传给当前活动 --- 以便设置字体大小
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View mView = inflater.inflate(R.layout.msg_item,null);
//        textView_chat_left_msg = (TextView) mView.findViewById(R.id.left_msg);
//        textView_chat_right_msg = (TextView) mView.findViewById(R.id.right_msg);
//        System.out.println("FontSizeActivity direct get textView left_msg is " + textView_chat_left_msg);
//        System.out.println("FontSizeActivity direct get textView right_msg is " + textView_chat_right_msg);

        System.out.println("66666666666666666666666666666666666666666666 ischanged is " + isChanged + " and current enter original seekbarCurrentProgress is " + seekbarCurrentProgress);

        // 返回事件
        ic_return_left_font_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("555555kkkkkkkkkk");
//                finish();
                if (seekbarCurrentProgress != recordProgress) {
                    isChanged = true;
                } else {
                    isChanged = false;
                }
                if (isChanged == true) { // 修改了字体大小
                    SweetAlertDialog mDialog = new SweetAlertDialog(FontSizeActivity.this, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("提示")
                            .setContentText("确定保存更改？点击返回将自动保存字体大小设置，并返回至看点新闻首页。")
                            .setCustomImage(null)
                            .setCancelText("取消")
                            .setConfirmText("确定")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
//                                    isChanged = false;
                                }
                            }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    // 保存当前设置的字体大小
                                    System.out.println("seekbarCurrentProgress is " + seekbarCurrentProgress);
                                    recordProgress = seekbarCurrentProgress;  //记录当前的字体大小 用户滑动的seekbar当前的progress 用于与下次设置字体时对比是否变化
                                    setCurrentProgress(seekbarCurrentProgress);
                                    sweetAlertDialog.dismiss();
                                    ActivityCollector.finishAll();
                                    System.out.println("yyyyyyyyyy finishAll");
//                                    // 存value -- FontSizeActivity传给MainActivity
                                    Intent intent = new Intent(FontSizeActivity.this, MainActivity.class);
                                    intent.putExtra("value", value);
                                    // 存value -- FontSizeActivity传给BeginActivity
//                                    Intent intent = new Intent(FontSizeActivity.this, BeginActivity.class);
//                                    intent.putExtra("value_to_begin", value);
                                    float fontScale = 0;
                                    fontScale = intent.getFloatExtra("value", fontScale);
                                    if (fontScale != 0) {
                                        FontSizeActivity.this.finish();
                                        setResult(RESULT_OK, intent);
                                        startActivity(intent);
                                        ChatActivity.setFlag(2);    // flag = 2 代表是修改字体大小后进入的ChatActivity
                                        System.out.println("****************************************************************** start");
                                    } else {
                                        System.out.println("fontscale is 0 hhhhhh");
                                    }
                                }
                            });
                    mDialog.show();
                } else { // 未修改字体大小
                    System.out.println("FontSizeActivity says the user dont't change text size.");
                    finish();
                }
            }
        });

        seekBar_text_size.setProgress(currentProgress);

        // 滑动条拖动事件
        seekBar_text_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //主要是用于监听进度值的改变
                System.out.println(progress);
                seekbarCurrentProgress = progress;
                if (progress == 0) {
//                    setCurrentProgress(0);
//                    if(currentProgress != recordProgress){
//                        isChanged = true;
//                        recordProgress = getCurrentProgress();  //记录当前的字体大小 seekbar的progress
//                    } else {
//                        isChanged = false;
//                    }
                    value = 0.5f + 0.5f * 20;
                    System.out.println("seekbarCurrentProgress is " + seekbarCurrentProgress + "and value is " + value);
                    tv_note.setTextSize(value);
                } else if (progress == 1) {
//                    setCurrentProgress(1);
//                    if(currentProgress != recordProgress){
//                        isChanged = true;
//                        recordProgress = getCurrentProgress();  //记录当前的字体大小 seekbar的progress
//                    } else {
//                        isChanged = false;
//                    }
                    value = 0.5f + 0.5f * 35;
                    System.out.println("seekbarCurrentProgress is " + seekbarCurrentProgress + "and value is " + value);
                    tv_note.setTextSize(value);
                } else if (progress == 2) {
//                    setCurrentProgress(2);
//                    if(currentProgress != recordProgress){
//                        isChanged = true;
//                        recordProgress = getCurrentProgress();  //记录当前的字体大小 seekbar的progress
//                    } else {
//                        isChanged = false;
//                    }
                    value = 0.5f + 0.5f * 65;
                    System.out.println("seekbarCurrentProgress is " + seekbarCurrentProgress + "and value is " + value);
                    tv_note.setTextSize(value);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //监听用户开始拖动进度条的时候
                System.out.println("start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //监听用户结束拖动进度条的时候
                System.out.println("stop");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 4:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(FontSizeActivity.this, "ChatActivity passData 传值成功", Toast.LENGTH_SHORT).show();

                    LayoutInflater inflater = LayoutInflater.from(this);
                    View mView = inflater.inflate(R.layout.msg_item, null);

                    textView_chat_left_msg = (TextView) mView.findViewById(R.id.left_msg);
                    textView_chat_right_msg = (TextView) mView.findViewById(R.id.right_msg);
                    System.out.println("FontSizeActivity direct get left_msg is " + textView_chat_left_msg);
                    System.out.println("FontSizeActivity direct get right_msg is " + textView_chat_right_msg);

                    if (textView_chat_left_msg == null) {
                        System.out.println("textView_chat_left_msg is null");
                    } else if (textView_chat_right_msg == null) {
                        System.out.println("textView_chat_right_msg is null");
                    } else {
                        System.out.println("two textview are both not null");
                    }
                }
                break;
            default:
                break;
        }
    }

    public static int getCurrentProgress() {
        return currentProgress;
    }

    public static void setCurrentProgress(int currentProgress) {
        FontSizeActivity.currentProgress = currentProgress;
    }
}
