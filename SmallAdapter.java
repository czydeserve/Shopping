package com.example.czy.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by czy on 2017/10/23.
 */


public class SmallAdapter extends BaseAdapter {

    private Context context;
    List<String> mDatas;

    public SmallAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }
    @Override
    public int getCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else return 0;
    }
    @Override
    public Object getItem(int i) {
        if (mDatas == null) {
            return null;
        } else {
            return mDatas.get(i);
        }
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder holder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.information, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.choices);
            convertView.setTag(holder);
        } else {
            convertView = view;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(mDatas.get(position));
        return convertView;
    }

    private class ViewHolder {
        public TextView tv;
    }
}
