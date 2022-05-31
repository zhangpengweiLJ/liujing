package com.example.demo;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: 张鹏伟
 * @create: 2022-05-30-15:29
 **/
public class Test {
    //url抓取数据（参数URL：就是你要抓数据的地址。如：http://www.cnev.cn/）
    public static String urlClimb(String url) throws Exception{
        URL getUrl =new URL(url); //创建URl连接
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); //建立连接
        connection.setRequestProperty("Referer","http://finance.sina.com.cn");
        connection.connect(); //打开连接
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK")); //创建输入流并设置编码
        StringBuffer sb = new StringBuffer();
        String lines = null;
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes(), "utf-8"); //读取流的一行,设置编码
            sb = sb.append(lines + "\n");
        }
        reader.close(); //关闭流
        connection.disconnect(); //销毁连接
        return sb.toString(); //返回抓取的数据(注意,这里是抓取了访问的网站的全部数据)
    }


    public static void main(String[] args) throws Exception {
        String s = urlClimb("https://stock2.finance.sina.com.cn/futures/api/jsonp.php/var%20_M22092022_5_30=/InnerFuturesNewService.getDailyKLine?symbol=M2209&_=2022_5_30");
        System.out.println(s);
    }
}

