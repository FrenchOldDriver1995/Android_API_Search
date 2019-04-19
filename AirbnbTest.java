package com.example.listviewtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AirbnbTest extends Activity {

    ListView listview = null;
    EditText edittext = null;
    Button button =null;
    ArrayAdapter<String> adapter =null;
    ReadURL readurl =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airbnbtest_layout);
        setViews();
    }






    public void setViews() {//初始化控件
        listview = (ListView) findViewById(R.id.listView_1);
        edittext = (EditText) findViewById(R.id.editText_1);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittext.length()<1) {
                    Toast.makeText(AirbnbTest.this, "Insert API please!", Toast.LENGTH_SHORT).show();
                }
                String s = getTextContent();//获取text内容
                readurl = new ReadURL(s);//发送给URLsearch
                System.out.println(readurl.str);
                setData();
            }
        });
    }

    public String getTextContent(){
        String str = edittext.getText().toString();
        return str;
    }

    public void setData(){
        if(readurl.getDataList().isEmpty() && edittext.getText().toString()!=""){
            Toast.makeText(AirbnbTest.this, "No result! Check your input!", Toast.LENGTH_SHORT).show();

        }
        adapter = new ArrayAdapter<String>(AirbnbTest.this, android.R.layout.simple_list_item_1, readurl.getDataList());
        listview.setAdapter(adapter);//返回的数据装进adapter
    }


}
