package com.example.listviewtest;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ReadURL {
    String str;
    List<String> list = null;
    public ReadURL(String string){
        list = new ArrayList<>();
        this.str =  "https://api.github.com/users/" + string +  "/repos?page=1";//补全链接
        sendURLSearch(this.str);
    }

    public void sendURLSearch(String str){

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    URLConnection connection = url.openConnection();
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "utf-8");
                    BufferedReader br = new BufferedReader(isr);//可以读取一行字符串
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        System.out.println(line);
                        JSONObject root = new JSONObject(line.toString());
                        System.out.println("test for"+ root.toString());
                    }
                    br.close();
                    isr.close();
                    is.close();


                    //JSONArray arr = root.getJSONArray("Value");
                    /*for(int i=0; i<root.length(); i++) {
                        JSONObject cur = arr.getJSONObject(i);
                        JSONObject ownerroot = cur.getJSONObject("owner");
                        String avatar_url = ownerroot.getString("avatar_url");//在子数组中找到这个avatar_url
                        System.out.println("node_id = "+cur.getString("node_id"));
                        System.out.println("name = "+cur.getString("name"));
                        System.out.println("avatar_url ="+avatar_url);
                        System.out.println("stargazers_count= "+ lan.getInt("stargazers_count"));
                        list.add(cur.getString("name")+ "          "+ "*"+ cur.getInt("stargazers_count"));//把数据全部都加进去

                    }*/


                }catch (IOException | JSONException e){
                    e.printStackTrace();
                }

                return null;
            }

        }.execute(str);
    }

    public List<String> getDataList(){

        return list;//暴露接口返回list，方便传递给ListView展示
    }
}
