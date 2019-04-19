package com.example.listviewtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class asyncTasktest extends Activity {
    TextView text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_layout);
        text = (TextView) findViewById(R.id.textView1);
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadURL("https://api.github.com/users/airbnb/repos?page=1");
            }
        });
    }

    public void ReadURL(String url){
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... strings) {


                try {
                    URL url  = new URL(strings[0]);
                    URLConnection connection = url.openConnection();
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br= new BufferedReader(isr);
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line=br.readLine())!=null){
                        sb.append(line);//把网页上的流依次读取
                    }
                    //依次关闭
                    br.close();
                    isr.close();
                    is.close();
                    return sb.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            //回调方法
            @Override
            protected void onCancelled(){
                super.onCancelled();
            }
            @Override
            protected void onCancelled(String result){
                super.onCancelled(result);
            }
            @Override
            protected void onPostExecute(String result){
                text.setText(result);
                super.onPostExecute(result);
            }
            @Override
            protected void onPreExecute(){
                Toast.makeText(asyncTasktest.this, "start reading", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected void onProgressUpdate(Void... values){
                super.onProgressUpdate(values);
            }


        }.execute(url);
    }
}
