package com.example.czy.shopping;

/**
 * Created by czy on 2017/10/23.
 */



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    private String Nam;
    Activity act;
    private Context context;
    private String Price;
    private String Info;
    private int cnt=0;
    private List<String> ss;
    public DetailAdapter(String Nam,String Price,String Info,Context context,Activity act){
        this.Nam=Nam;
        this.context=context;
        this.Price=Price;
        this.Info=Info;
        this.act=act;
    }

    public DetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(context).inflate(R.layout.details,parent,false);
        DetailAdapter.MyViewHolder holder=new DetailAdapter.MyViewHolder(view);
        return holder;
    }
    @Override
    public int getItemCount(){
        return 1;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position){
        holder.tv.setText(Nam);
        holder.tv2.setText(Price);
        holder.tv3.setText(Info);

        String[] s=new String[]{"一键下单","分享商品","不感兴趣","查看更多促销信息"};
        ss=new ArrayList<String>();
        for(int i=0;i<s.length;i++)
            ss.add(s[i]);

        SmallAdapter fun=new SmallAdapter(context,ss);
        holder.llv.setAdapter(fun);

        final Intent intent=act.getIntent();
        act.setResult(1,intent);

        holder.imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.finish();
            }
        });
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag=holder.star.getTag();
                if(tag=="0"){
                    holder.star.setTag("1");
                    holder.star.setImageResource(R.drawable.full_star);
                }
                else
                {
                    holder.star.setTag("0");
                    holder.star.setImageResource(R.drawable.empty_star);
                }
            }
        });
        holder.addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"商品已添加到购物车",Toast.LENGTH_SHORT).show();
                cnt++;
                intent.putExtra("cnt",cnt);
                intent.putExtra("name",Nam);
                intent.putExtra("price",Price);
                intent.putExtra("information",Info);
                act.setResult(0,intent);
            }
        });
        switch (Nam){
            case "Enchated Forest":
                holder.iv.setImageResource(R.drawable.enchatedforest);
                break;
            case "Arla Milk":
                holder.iv.setImageResource(R.drawable.arla);
                break;
            case "Devondale Milk":
                holder.iv.setImageResource(R.drawable.devondale);
                break;
            case "Kindle Oasis":
                holder.iv.setImageResource(R.drawable.kindle);
                break;
            case "waitrose 早餐麦片":
                holder.iv.setImageResource(R.drawable.waitrose);
                break;
            case "Mcvitie's 饼干":
                holder.iv.setImageResource(R.drawable.mcvitie);
                break;
            case "Ferrero Rocher":
                holder.iv.setImageResource(R.drawable.ferrero);
                break;
            case "Maltesers":
                holder.iv.setImageResource(R.drawable.maltesers);
                break;
            case "Lindt":
                holder.iv.setImageResource(R.drawable.lindt);
                break;
            case "Borggreve":
                holder.iv.setImageResource(R.drawable.borggreve);
                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        TextView tv2;
        TextView tv3;
        ImageView iv;
        ImageButton imb;
        ImageButton star;
        ImageButton addshop;
        ListView llv;
        public MyViewHolder(View view){
            super(view);
            tv=(TextView)view.findViewById(R.id.item_name);
            tv2=(TextView)view.findViewById(R.id.price);
            iv=(ImageView) view.findViewById(R.id.picture);
            tv3=(TextView)view.findViewById(R.id.category);
            imb=(ImageButton)view.findViewById(R.id.back);
            star=(ImageButton)view.findViewById(R.id.star);
            addshop=(ImageButton)view.findViewById(R.id.addshopcar);
            llv=(ListView)view.findViewById(R.id.function);
            star.setTag("0");
        }
    }
}

