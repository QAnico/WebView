package com.lin.webview;


import android.app.Notification;
import android.os.Message;

import java.util.logging.Handler;

/**
 * Created by lin on 2016/10/9.
 */

//webView同步Cookie
public class HttpCookie extends Thread{

    Handler handler;//用于通信
    public HttpCookie(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {  //模拟登录
/*
        HttptCLinent client=new DefaultHttpClient();

        HttpPost post=new HttpPost("http://192.168.0.18.8080/web/login"); //请求  参数为URL
        //192.168.0.18计算机的本地地址   index.jsp为已经编写的web案例   http://192.168.0.18.8080/web/login就是一个web案例的访问地址

        List<NameValuePair> list=new ArayList<NameValuePair>();

        list.add(new BasicNameValuePair("name","nates"));//nates为服务器民政
        list.add(new BasiNamValuePair("age","12"));

        try {
            post.setEntity(new UrlEncodedFormEntity(list));

            HttpResponse responce=client.execute(post);

            if(responce.getStatusCode()==200){ //说明正常返回
                AbstractHttpClient absClient=(AbstractHttpClient) client;//强转
                List<Cookie> cookies=absClient.getCookieStore().getCookie(); //得到服务器返回的数据

                fot(Cookie cookie:cookies){

                    System.out.print("name="+cookie.getName()+"age="+cookie.getValue());  //对的到的数据进行遍历

                    //通信
                    Message message=new Message();
                    message.obj=cookie;
                    handler.sendMessage(message);
                    return;//通信结束return就行
                }

            }

        }catch (Exception e){

        }//catch
        */
    }
}
