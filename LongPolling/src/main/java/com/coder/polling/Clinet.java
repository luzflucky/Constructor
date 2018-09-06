package com.coder.polling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by lucky on 2018/9/6.
 */
public class Clinet {

    private static final String _URL = "http://127.0.0.1:8080/pollingServletAsyn";

    private static AtomicLong value = new AtomicLong();

    public static void main(String[] args) throws Exception {
        polling();
        System.in.read();
    }

    public static void polling(){
        while (!Thread.interrupted()){
            query();
        }
    }

    public static void query() {
        Long result = value.getAndIncrement();
        System.out.println("第" + result + "请求");
        HttpURLConnection con = null;
        try {
            URL url = new URL(_URL);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(30000);
            con.setReadTimeout(50000);
            con.setRequestMethod("GET");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            con.setRequestProperty("Accept-Charset","application/json;charset=UTF-8");
            con.connect();
            if(con.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String message = null;
                if((message = br.readLine()) != null){
                    System.out.println("返回结果是："+message);
                }
                br.close();
            }
        }catch (Exception e){
            System.out.println("请求超时 :----");
        }finally {
            if(con!= null){
                con.disconnect();
            }
        }

    }
}
