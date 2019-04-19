package com.example.listviewtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;

public class searchURL extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchurl_layout);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new AsyncTask<String, Void, Void>(){

                    @Override
                    protected Void doInBackground(String... params) {


                        try {
                            URL url = new URL(params[0]);
                            URLConnection connection = url.openConnection();
                            InputStream is = connection.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is, "utf-8");
                            BufferedReader br = new BufferedReader(isr);//可以读取一行字符串
                            String line;
                            StringBuilder sb = new StringBuilder();
                            while((line=br.readLine()) !=null ){
                                //System.out.println(line);
                                sb.append(line);
                            }

                            //关闭
                            br.close();
                            isr.close();
                            is.close();
                            try {
                                JSONObject root = new JSONObject(sb.toString());
                                JSONArray arr = root.getJSONArray("https://api.github.com/users/airbnb/repos?page=1");


                                for(int i=0; i<arr.length(); i++){
                                    JSONObject dyn = arr.getJSONObject(i);
                                    System.out.println("--------------------");
                                    System.out.println("node_id = "+dyn.getString("node_id"));
                                    System.out.println("name = "+dyn.getString("name"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                }.execute("https://api.github.com/users/airbnb/repos?page=1");
            }
        });


        ArrayList<ABdata> listABdata ;
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    InputStreamReader isr = new InputStreamReader(getAssets().open("testAirbnbData.json"), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while((line=br.readLine()) !=null ){

                        builder.append(line);
                    }
                    br.close();
                    isr.close();
                    JSONObject root = new JSONObject(builder.toString());
                    JSONArray array = root.getJSONArray("dynamic");
                    for(int i =0; i<array.length(); i++){
                        JSONObject lan = array.getJSONObject(i);
                        JSONObject ownerroot = lan.getJSONObject("owner");//继续读取子Object


                        String avatar_url = ownerroot.getString("avatar_url");//在子数组中找到这个avatar_url
                        String type = ownerroot.getString("type");
                        System.out.println("--------------------");
                        System.out.println("node_id = "+lan.getString("node_id"));
                        System.out.println("name = "+lan.getString("name"));
                        System.out.println("stargazers_count= "+ lan.getInt("stargazers_count"));
                        System.out.println("avatar_url ="+avatar_url);
                        System.out.println("type= "+type);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
