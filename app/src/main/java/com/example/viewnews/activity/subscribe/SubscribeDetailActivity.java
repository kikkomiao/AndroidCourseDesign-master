package com.example.viewnews.activity.subscribe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewnews.activity.MainActivity;
import com.example.viewnews.R;
import com.example.viewnews.bean.SubBean;
import com.example.viewnews.tools.BaseActivity;

import org.litepal.LitePal;

import java.util.List;

@SuppressLint("SetJavaScriptEnabled")
public class SubscribeDetailActivity extends BaseActivity {
    public static final String subName = "青年";
    public static final String intro = "演示新闻";
    public static final String ImagePage = "";
    public static final String address = "网站地址";

    private WebView webView;
    private ImageView imageView;
    private ImageView returnView;
    private TextView textViewName;
    private TextView textViewIntro;
    private Button button;

    private String name, path, introduction, subAddress;
    private int path1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_web);
        webView = (WebView) findViewById(R.id.subscribe_web_View);

        textViewName = findViewById(R.id.txt_web_newsName);
        textViewIntro = findViewById(R.id.txt_web_intro);
        imageView = findViewById(R.id.icon_web_newsImage);
        returnView = findViewById(R.id.img_web_MySubscribe);
        returnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubscribeDetailActivity.this.finish();
            }
        });

        //接收前面页面传过来的值
        Intent intent = getIntent();
        name = intent.getStringExtra(subName);
        introduction = intent.getStringExtra(intro);
        subAddress = intent.getStringExtra(address);

        path = intent.getStringExtra(ImagePage);
        path1 = Integer.valueOf(path);

        textViewName.setText(name);
        textViewIntro.setText(introduction);
        imageView.setImageResource(path1);

        //点击已关注和未关注
        button = findViewById(R.id.btn_web_subscribe);
        List<SubBean> sub1 = LitePal.where("subUserID = ? AND subName=?", MainActivity.currentUserId, name).find(SubBean.class);
        if (sub1.isEmpty()) {
            System.out.println(sub1);
        } else {
            button.setText("已关注");
            button.setBackgroundColor(Color.parseColor("#EFECEB"));
            button.setTextColor(Color.parseColor("#B6B2B2"));
        }
        ClickButton();
    }

    public void ClickButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (button.getText().equals("已关注")) {
                    System.out.println(button.getText());
                    button.setText("未关注");
                    button.setBackgroundColor(Color.parseColor("#F44336"));
                    button.setTextColor(Color.parseColor("#F8F5F5"));
                    //去数据库查一下，如果有相同的就删除
                    int isOk = LitePal.deleteAll(SubBean.class, "subName = ?", name);
                    if (isOk > 0) {
                        System.out.println("删除成功");
                    } else {
                        System.out.println("删除失败");
                    }
                } else if (button.getText().equals("未关注")) {
                    button.setText("已关注");
                    button.setBackgroundColor(Color.parseColor("#EFECEB"));
                    button.setTextColor(Color.parseColor("#B6B2B2"));
                    //在数据库中添加
                    //System.out.println(subscribe1.getSubscribeImage());
                    SubBean subBean = new SubBean();
                    subBean.setSubName(name);
                    subBean.setSubIntroduction(introduction);
                    subBean.setSubUserID(MainActivity.currentUserId);
                    subBean.setSubMine("已关注");
                    subBean.setSubAddress(subAddress);
                    subBean.setSubImage(path1);
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
    protected void onStart() {
        super.onStart();
        System.out.println("网站地址" + subAddress);
        // 通过WebView中的getSettings方法获得一个WebSettings对象
        WebSettings settings = webView.getSettings();
        // 开启javascript：h5页要一般都有js,设置为true才允许h5页面执行js，但开启js非常耗内存，经常会导致oom，
        // 为了解决这个问题，可以在onStart方法中开启，在onStop中关闭。
        settings.setJavaScriptEnabled(true);
        //设置支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具。若为false，则该WebView不可缩放
        settings.setBuiltInZoomControls(true);
        // 设置允许js弹出alert对话框
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 设置webview推荐使用的窗口，使html界面自适应屏幕
        // 原因讲解：https://blog.csdn.net/SCHOLAR_II/article/details/80614486
        settings.setUseWideViewPort(true);
        // 设置WebView底层的布局算法，参考LayoutAlgorithm#NARROW_COLUMNS，将会重新生成WebView布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 设置缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        // 隐藏webview缩放按钮
        settings.setDisplayZoomControls(false);
        // 加载网页连接
        webView.loadUrl(subAddress);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            // 在页面加载结束时调用
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 通过查看每个新闻的网页发现网页广告的div样式的选择器为body > div.top-wrap.gg-item.J-gg-item 然后去除这个样式，使其加载网页时去掉广告
                view.loadUrl("javascript:function setTop1(){document.querySelector('body > div.top-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop1();");
                view.loadUrl("javascript:function setTop4(){document.querySelector('body > a.piclick-link').style.display=\"none\";}setTop4();");
                view.loadUrl("javascript:function setTop2(){document.querySelector('#news_check').style.display=\"none\";}setTop2();");
                view.loadUrl("javascript:function setTop3(){document.querySelector('body > div.articledown-wrap gg-item J-gg-item').style.display=\"none\";}setTop3();");
            }

            // 重写此方法可以让webView处理https请求。
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 默认的处理方式，WebView变成空白页
                // handler.cancel();
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler.proceed();
            }
        });

        // 重写执行执行去广告的js代码
        webView.setWebChromeClient(new WebChromeClient() {
            // 每次网页加载进度改变时，就会执行一次js代码，保证广告一出来就被干掉
            // 缺点也很明显，会执行很多次无效的js代码。
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // 去除加载热点新闻
                view.loadUrl("javascript:function setTop1(){document.querySelector('body > div.top-wrap.gg-item.J-gg-item').style.display=\"none\";}setTop1();");
                view.loadUrl("javascript:function setTop4(){document.querySelector('body > a.piclick-link').style.display=\"none\";}setTop4();");
                view.loadUrl("javascript:function setTop2(){document.querySelector('#news_check').style.display=\"none\";}setTop2();");
                view.loadUrl("javascript:function setTop3(){document.querySelector('body > div.articledown-wrap gg-item J-gg-item').style.display=\"none\";}setTop3();");
            }
        });
    }

    // 活动不可见时关闭脚本，否则可能会导致oom
    @Override
    protected void onStop() {
        super.onStop();
        webView.getSettings().setJavaScriptEnabled(false);
    }
}
