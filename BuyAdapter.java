package com.example.czy.shopping;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chendehe on 2017/10/17.
 */

public class BuyAdapter extends BaseAdapter {
    private Context context;
    private List<Goods> goods;

    public BuyAdapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods=goods;
    }
    @Override
    public int getCount() {
        if (goods != null) {
            return goods.size();
        } else return 0;
    }
    @Override
    public Object getItem(int i) {
        if (goods == null) {
            return null;
        } else {
            return goods.get(i);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_to_buy, null);
            holder = new ViewHolder();
            holder.icon=(TextView)convertView.findViewById(R.id.icon);
            holder.tv1 = (TextView) convertView.findViewById(R.id.item_name);
            holder.tv2 = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(holder);
        } else {
            convertView = view;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.icon.setText(goods.get(position).getName().substring(0,1).toUpperCase());
        holder.tv1.setText(goods.get(position).getName());
        holder.tv2.setText(goods.get(position).getprice());
        return convertView;
    }
    private class ViewHolder {
        public TextView tv1;
        public TextView tv2;
        public TextView icon;
    }

}
