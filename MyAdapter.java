package com.example.listviewtest;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterListener;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter implements Filterable {


    private List<String> list = new ArrayList<String>();
    private Context context;
    private MyFilter filter = null;// 创建MyFilter对象
    public static FilterListener listener = null;// 接口对象

    public MyAdapter(List<String> list, Context context, FilterListener filterListener) {
        this.list = list;
        this.context = context;
        this.listener = filterListener;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new MyFilter(list);
        }
        return filter;
    }

    class MyFilter extends Filter {//添加功能比如，从搜索字段只为中间一部分

        // 创建集合保存原始数据
        private List<String> original = new ArrayList<String>();

        public MyFilter(List<String> list) {
            this.original = list;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}