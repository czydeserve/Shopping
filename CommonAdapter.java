package com.example.czy.shopping;

/**
 * Created by czy on 2017/10/22.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder>
{
//    public CommonAdapter(Context context,int layoutId,List datas)
    private OnItemClickListener cOnItemClickListener=null;
    private List<Goods> goods;
    private Context context;
    public CommonAdapter(List<Goods> goods,Context context)
    {
        this.goods=goods;
        this.context=context;
    }

    public interface OnItemClickListener
    {
        void onClick(int position);
        void onLongClick(int position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(final ViewGroup parent,int viewType)
    {
        View view=LayoutInflater.from(context).inflate(R.layout.item_to_show,parent,false);
        CommonViewHolder holder=new CommonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position){
        holder.tv1.setText(goods.get(position).getName());
        holder.tv2.setText(goods.get(position).getName().substring(0,1).toUpperCase());
//        holder.tv2.setText("A");
        if(cOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    cOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    cOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return goods.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.cOnItemClickListener=listener;
    }

    class CommonViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1;
        TextView tv2;
        public CommonViewHolder(View view)
        {
            super(view);
            tv1=(TextView)view.findViewById(R.id.item_name);
            tv2=(TextView)view.findViewById(R.id.letter);
        }
    }

}
