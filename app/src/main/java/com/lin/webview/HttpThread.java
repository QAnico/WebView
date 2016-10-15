package com.lin.webview;

import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


/**
 * Created by lin on 2016/9/29.
 */
public class HttpThread extends Thread {

    private String murl;

    public HttpThread(String url){
        this.murl=url;
    }

    @Override
    public void run() {  //重写此方法执行下载
        try {  //异常捕获
            System.out.print("start Download");  //开始下载提示

            URL httpUrl=new URL(murl);
            HttpsURLConnection conn= (HttpsURLConnection) httpUrl.openConnection();
            conn.setDoInput(true); //设定输入流
            conn.setDoOutput(true);
            InputStream in=conn.getInputStream();//创建输入流
            FileOutputStream out=null;

            File downloadFile; //下载目录
            File sdFile;
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  //判断SD可是否存在
                downloadFile=Environment.getExternalStorageDirectory();//得到外部存储目录
                sdFile=new File(downloadFile,"text.apk");//知道存储目录及文件名
                out=new FileOutputStream(sdFile);//写出流
            }

            //创建缓存
            byte[] b=new byte[6*1024];  //6k的空间
            int len;
            while ((len=in.read(b))!=-1){  //-1标志流终止
                if(out!=null){
                    out.write(b,0,len);
                }

            }

            if(out!=null){  //关闭流
                out.close();
            }
            if(in!=null){  //关闭流
                in.close();
            }

            System.out.print("-----download success------");  //下载完成提示

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
