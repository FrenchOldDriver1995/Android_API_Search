package com.example.listviewtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

//先实现过滤数据功能，再实现链接外部api功能
public class MainActivity extends AppCompatActivity {

    ListView lview = null;
    EditText etext = null;
    SearchView schview =null;
    TextView text_view = null;
    ArrayAdapter<String> adapter =null;

    private List<String> list = new ArrayList<String>();
    private String[] test_data = {"apple", "banana", "cherry", "orange", "pineapple", "strawberry", "watermalon",
            "mango", "litchi", "peer", "papaya", "mango", "coco", "kiwi","mountain","language","warrior","american","bad","boy",
    "buy", "bus", "coffee", "coach"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setData();
        setListeners();
        //getJSONdata();
        //createJSONdata();

    }

    protected void setItemClick(final List<String> filter_lists) {//给listView添加item的单击事件
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
                Toast.makeText(MainActivity.this, filter_lists.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        setItemClick(list);

        etext.addTextChangedListener(new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                if(adapter != null){
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void setViews(){//初始化控件
        lview =(ListView) findViewById(R.id.listView_1);
        etext =(EditText) findViewById(R.id.editText_1);
        schview = (SearchView) findViewById(R.id.searchView_1);
        text_view = (TextView) findViewById(R.id.textView_1);
    }

    public void setData(){//初始化数据，设置adapter
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, test_data);
        lview.setAdapter(adapter);
    }

    public void getJSONdata(){
        try{
            InputStreamReader isr = new InputStreamReader(getAssets().open("testAirbnbData.json"  //这里注意加文件格式
            ),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            br.close();
            isr.close();
            JSONObject root = new JSONObject(sb.toString());
            //System.out.println("cat" + root.getString("cat"));
            JSONArray arr= root.getJSONArray("testAirbnbData");

            for(int i=0; i<arr.length(); i++){
                JSONObject dyn = arr.getJSONObject(i);
                System.out.println("--------------------");
                System.out.println("node_id = "+dyn.getString("node_id"));
                System.out.println("name = "+dyn.getString("name"));
                //System.out.println("owner = "+dyn.getString("owner"));
                //JSONObject sub = dyn.getJSONObject("owner");
                //System.out.print(sub);
            }

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }
    }


    public void createJSONdata(){

        try {
            JSONObject root = new JSONObject();
            root.put("cat","it");

            JSONObject o1 = new JSONObject();
            o1.put("name", "zhang");

            JSONObject o2 = new JSONObject();
            o2.put("name","liu");

            JSONObject o3 = new JSONObject();
            o3.put("name", "li");
            JSONArray total = new JSONArray();
            total.put(o1);
            total.put(o2);
            total.put(o3);
            root.put("test", total);
            System.out.print(root.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
