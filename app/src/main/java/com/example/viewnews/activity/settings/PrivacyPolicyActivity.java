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

public class PrivacyPolicyActivity extends BaseActivity {

    private ImageView ic_return_left_privacy_policy;
    private TextView textview_content_privacy_policy;

    public final String content = "<html>\n" +
            "\t<head>\n" +
            "\t\t<meta charset=\"utf-8\">\n" +
            "\t\t<title></title>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<div style=\"text-align: right;\">发布日期：2021年 12月 23日<br>生效日期：2021年 12月 30日</div>\n" +
            "\t\t<div><b>提示条款：</b></div>\n" +
            "\t\t\n" +
            "\t\t<div style=\"text-indent: 37px;\">\n" +
            "\t\t您的信任对我们非常重要，我们深知个人信息对您的重要性，我们将按法律法规要求，采取相应安全保护措施，尽力保护您的个人信息安全可控。鉴于此，“看点新闻”服务提供者（或简称“我们”）制定本《隐私权政策》（下称“本政策/本隐私权政策”）并提醒您：</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b style=\"text-decoration: underline;\">\n" +
            "\t\t本政策适用于“看点新闻”提供的所有产品和服务，包括但不限于电脑端、移动智能终端、车载端等的应用程序、网页、供第三方网站和应用程序使用的软件开发工具包（SDK）和应用程序编程接口（API）</b>\n" +
            "以及不断创新研发的产品及服务。如您使用“看点新闻”提供的某项或某几项服务有其单独的隐私权政策的，该等服务对应的隐私权政策将与本隐私权政策一起构成一套完整的隐私权政策。\n" +
            "\t\t</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b style=\"text-decoration: underline;\">\n" +
            "\t\t需要特别说明的是，本政策不适用于其他第三方向您提供的服务，为确保流畅的产品体验，您可能会收到来自第三方提供的内容或网络链接。请您谨慎选择是否访问第三方提供的链接、内容、产品和服务。在向第三方提交个人信息之前，请认真阅读这些第三方的隐私政策。\n" +
            "\t\t</b></div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b style=\"text-decoration: underline;\">\n" +
            "\t\t在使用我们各项产品或服务前，请您务必仔细阅读并透彻理解本政策。特别是以粗体/粗体下划线标识的条款，请您重点阅读，在确认充分理解并同意后再开始使用。\n" +
            "</b>如对本政策内容有任何疑问、意见或建议，您可通过“看点新闻”提供的各种联系方式与我们联系。\n" +
            "\t\t</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t第一部分&nbsp;定义</b></div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t“看点新闻”：</b>指“看点新闻”新闻平台，包括但不限于电脑端（域名为www.kandian.com）和手机APP端。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t“看点新闻”服务提供者：</b>指研发并提供“看点新闻”新闻平台产品和服务的法人主体，即中央宣传部宣传舆情研究中心。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t儿童：</b>指不满十四周岁的未成年人。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t个人信息：</b>指以电子或者其他方式记录的与已识别或者可识别的自然人有关的各种信息，不包括匿名化处理后的信息。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t敏感个人信息：</b>敏感个人信息是一旦泄露或者非法使用，容易导致自然人的人格尊严受到侵害或者人身、财产安全受到危害的个人信息，包括生物识别、宗教信仰、特定身份、医疗健康、金融账户、行踪轨迹等信息，以及不满十四周岁未成年人的个人信息。（我们将在本隐私权政策中对具体敏感个人信息以粗体进行显著标识）。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t去标识化：</b>指个人信息经过处理，使其在不借助额外信息的情况下无法识别特定自然人的过程。</div>\n" +
            "\t\t<div style=\"text-indent: 37px;\"><b>\t\t匿名化：</b>指个人信息经过处理无法识别特定自然人且不能复原</div>\n" +
            "\t</body>\n" +
            "</html>\n";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);

//        System.out.println(R.id.ic_return_left_privacy);

        ic_return_left_privacy_policy = (ImageView) findViewById(R.id.ic_return_left_privacy_policy);
        //返回事件
        ic_return_left_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textview_content_privacy_policy = (TextView) findViewById(R.id.textview_content_privacy_policy);
        textview_content_privacy_policy.setText(Html.fromHtml(content));//这是显示段落文本的,通过解析html
        textview_content_privacy_policy.setMovementMethod(ScrollingMovementMethod.getInstance());//段落文本的话要加这个
    }
}
