package com.lin.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private Button goback;
    private Button refresh;
    private TextView text_title;
    private WebView webView;
    private TextView mTextView_error; //用于显示没网信息

    /*
    //webView同步Cookie
    //主线程中实现通讯：
    Handler handler=new Handler(){

        public void handlerMessage(android.os.Message msg){

            //String cookie= msg.obj.toString();

            CookieSyncManager.createInstance(MainActivity.this);
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);//结束Cookie
            cookieManager.setCookie("http://192.168.0.18.8080/webs",msg.obj.toString()); //第一个参数为url
            CookieSyncManager.getInstance().sync();

            //webView加载cookie就行了
            webView.loadUrl("http://192.168.0.18.8080/web/index.jsp");
        }

    };

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);  //设置无标题栏
        setContentView(R.layout.activity_main);

        goback= (Button) findViewById(R.id.bt_return);
        refresh= (Button) findViewById(R.id.bt_refresh);
        text_title= (TextView) findViewById(R.id.textView_title);
        mTextView_error= (TextView) findViewById(R.id.text_error);


        webView= (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://shouji.baidu.com/");


        //设置在本界面打开网页、

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                text_title.setText(title); //给top_textView中添加网页信息，只需这里添加一行代码
                super.onReceivedTitle(view, title);
            }

        });

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//重新传入url
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override  //当没网时给用户的提示信息  这里只用一个TExtView显示提示信息就行
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                //view.loadUrl("file:///android_asset/error.html");  //加载事先写好asset包下的error.html 的网页提示错误

                mTextView_error.setText("404 error! 网络连接失败"); //设置网络错误提示
                text_title.setVisibility(View.GONE); //让显示网页的显示框隐藏


            }
        });

        //启用javaScrip
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持javaScrip
        //webView加载页面使用优先缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存
        //settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不缓存


        webView.setDownloadListener(new Mydownload());//下载监听
        //按钮设置点击事件  调用自定义的类
        goback.setOnClickListener(new Mylistener());
        refresh.setOnClickListener(new Mylistener());


    }//onCreate

    class Mylistener implements View.OnClickListener{  //内部类实现按钮点击事件

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.bt_refresh:
                    webView.reload(); //刷新界面
                    break;
                case R.id.bt_return:
                    finish();
                    break;

            }

        }
    }  // class Mylistener

    class Mydownload implements DownloadListener {  //自定义下载类


        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            System.out.printf("url----->"+url);
            Toast.makeText(MainActivity.this,"url-->"+url,Toast.LENGTH_SHORT).show();

          //  if(url.endsWith(".apk")){//下载以 .apk 结尾的文件即安装包    这里的if 判断有时会导致不会下载

              //  new HttpThread(url).start();  // （1）用自己创建的线程下载文件


                //（2）调用系统的浏览器下载
                Uri uri=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);


        //    }//if

        }
    }  //class Mydownload

}
