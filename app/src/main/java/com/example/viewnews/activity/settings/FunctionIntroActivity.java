package com.example.viewnews.activity.settings;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.viewnews.R;
import com.example.viewnews.tools.BaseActivity;

public class FunctionIntroActivity extends BaseActivity {

    private ImageView icon_return_function_intro;
    private TextView textview_content_function_intro;

    public final String content = "<h3>1.查看新闻</h3>\n" +
            "<p>\t\t看点新闻首页将展示来源于各大新闻网站及新闻公众号的新闻头条，并提供了社会、国内、国际、体育、娱乐等多种类别的新闻看点，进入看点新闻，每一位app使用者均可自行浏览新闻概要，或点击查看新闻内容详情。</p>\n" +
            "<h3>2.查看新闻评论</h3>\n" +
            "<p>\t\t针对某篇新闻文章来说，看点新闻支持每位已登录的账号用户，查看已注册本app的所有用户所发表的所有评论（包含当前已登录账号用户曾发表的评论）。</p>\n" +
            "<h3>3.评论新闻</h3>\n" +
            "<p>\t\t看点新闻为每位已登录的账号用户，提供为某篇新闻文章发表自我评论的功能。</p>\n" +
            "<h3>4.删除新闻评论</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，删除曾经自己对某篇新闻文章所发表的自我评论。</p>\n" +
            "<h3>5.收藏新闻文章</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，收藏自己所感兴趣的某篇新闻文章，收藏后该新闻文章将会被加入至“个人收藏”中。</p>\n" +
            "<h3>6.查看新闻文章收藏</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“个人收藏”中查看自己收藏的所有新闻文章。</p>\n" +
            "<h3>7.取消收藏新闻文章</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“个人收藏”中取消自己曾经收藏的某些新闻文章。</p>\n" +
            "<h3>8.查看新闻看点号</h3>\n" +
            "<p>\t\t看点新闻提供了“主要央媒”、“行业媒体”、“地方媒体”三个类别的看点号，支持每位已登录的账号用户，在“我的订阅”中查看自己已订阅的某些新闻看点号，也可以在“我的订阅” → “添加”中查看本app中提供的所有新闻看点号，新闻看点号官方具体的内容可通过点击具体的新闻看点号进行查看。</p>\n" +
            "<h3>9.订阅新闻看点号</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“我的订阅” → “添加”中，订阅更多的新闻看点号。</p>\n" +
            "<h3>10.取消订阅新闻看点号</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“我的订阅”或者“我的订阅”→“添加”中，取消订阅更多的新闻看点号。</p>\n" +
            "<h3>11.发布文章</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“我的文章”中，选择编辑内容后发布自己的文章，也可以选择修改内容后重新发布（此修改操作将会覆盖原有文章内容，并非重新发布一篇新文章）。</p>\n" +
            "<h3>12.查看个人发布的文章</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“我的文章”中，查看自己已发布的文章概要，选择某篇文章可查看文章详情。</p>\n" +
            "<h3>13.删除个人发布的文章</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“我的文章”中，删除自己已发布的文章。</p>\n" +
            "<h3>14.知识答题</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部选择“答题”，进入自测答题，提交答卷后本app会自动计算您的成绩。</p>\n" +
            "<h3>15.咨询</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部或app首页右上角收缩菜单项中点击“咨询”，询问与本app相关的疑惑，本app的咨询顾问“小智”将会为您解决部分的疑问。</p>\n" +
            "<h3>16.提交意见反馈</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页右上角收缩菜单项中点击“意见反馈”或在app首页底部“设置” → “意见反馈”中，针对本app的优点与缺点提交反馈信息。</p>\n" +
            "<h3>17.查看使用过程中的常见问题</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “意见反馈” → “常见问题”中，查看使用本app过程中遇到的常见问题，以及解决办法。</p>\n" +
            "<h3>18.编辑个人资料</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在“编辑个人资料”中，编辑当前看点新闻账号的用户头像、昵称、性别、生日、个性签名。</p>\n" +
            "<h3>19.更改登录密码</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “账号与安全” → “更改登录密码”中，设置登录新密码成功后，即可更改当前登录账号的登录密码。</p>\n" +
            "<h3>20.注销账号</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “账号与安全” → “注销账号”中，注销当前看点新闻账号。</p>\n" +
            "<h3>21.退出登录</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页右上角点击退出登录图标，退出登录当前看点新闻账号。</p>\n" +
            "<h3>22.设置字体大小</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “隐私和通用” → “字体大小”中，设置满意的字体大小（注意：当前字体大小设置只适用于“咨询”聊天窗口）。</p>\n" +
            "<h3>23.系统权限管理</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “隐私和通用” → “系统权限管理”中，查看对看点新闻已授权的系统权限，针对未授权的系统权限可以申请授予权限。</p>\n" +
            "<h3>24.查看看点新闻app功能介绍</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “关于看点新闻” → “功能介绍”中，查看对看点新闻已授权的系统权限，针对未授权的系统权限可以申请授予权限。</p>\n" +
            "<h3>25.查看看点新闻app相关服务协议</h3>\n" +
            "<p>\t\t看点新闻支持每位已登录的账号用户，在app首页底部“设置” → “关于看点新闻” → “服务协议”和“隐私权政策”中，查看对看点新闻的服务协议和隐私权政策。</p>\n";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_intro);

        icon_return_function_intro = findViewById(R.id.ic_return_left_function_intro);
        //返回事件
        icon_return_function_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textview_content_function_intro = (TextView) findViewById(R.id.textview_content_function_intro);
        textview_content_function_intro.setText(Html.fromHtml(content));//显示段落文本
        textview_content_function_intro.setMovementMethod(ScrollingMovementMethod.getInstance());//段落文本

    }
}
