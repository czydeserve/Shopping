package com.example.czy.shopping;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListView mListView;
    private List<Goods> goods;
    private List<Goods> goods_to_buy;
    private BuyAdapter buyAdapter;
    private CommonAdapter cAdapter;
//    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] name = new String[]{"Enchated Forest", "Arla Milk", "Devondale Milk", "Kindle Oasis", "waitrose 早餐麦片", "Mcvitie's 饼干", "Ferrero Rocher", "Maltesers", "Lindt", "Borggreve"};
        final String[] price = new String[]{"¥ 5.00", "¥ 59.00", "¥ 79.00", "¥ 2399.00", "¥ 179.00", "¥ 14.00", "¥ 132.59", "¥ 141.43", "139.43", "28.90"};
        final String[] information = new String[]{"作者 Johanna Basford", "产地 德国", "产地 澳大利亚", "版本 8GB", "重量 2Kg", "产地 英国", "重量 300g", "重量 118g", "重量 249g", "重量 640g"};

        goods = new ArrayList<Goods>();
        goods_to_buy = new ArrayList<Goods>();
        for(int i=0;i<name.length;i++)
        {
            goods.add(new Goods(name[i],information[i],price[i]));
        }

        final LinearLayout buyLayer = (LinearLayout) findViewById(R.id.buy_view);
        buyLayer.setVisibility(View.INVISIBLE);

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cAdapter=new CommonAdapter(goods,MainActivity.this);


//        mRecyclerView.setAdapter(cAdapter);
        ScaleInAnimationAdapter animationAdapter=new ScaleInAnimationAdapter(cAdapter);
        animationAdapter.setDuration(1000);
        mRecyclerView.setAdapter(animationAdapter);
        mRecyclerView.setItemAnimator(new OvershootInLeftAnimator());

        cAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener()
        {
            @Override
            public void onClick(int position)
            {
                Intent intent = new Intent(MainActivity.this,Details.class);
                intent.putExtra("name",goods.get(position).getName());
                intent.putExtra("price", goods.get(position).getprice());
                intent.putExtra("information", goods.get(position).getInfo());
                startActivityForResult(intent,0);
            }
            @Override
            public void onLongClick(int position)
            {
                Toast.makeText(MainActivity.this,"移除第"+String.valueOf(position+1)+"个商品",Toast.LENGTH_SHORT).show();
                goods.remove(position);
                cAdapter.notifyDataSetChanged();
            }
        });

        buyAdapter = new BuyAdapter(MainActivity.this,goods_to_buy);

        mListView = (ListView) findViewById(R.id.item_to_buy);
        mListView.setAdapter(buyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("name", goods_to_buy.get(i).getName());
                intent.putExtra("price", goods_to_buy.get(i).getprice());
                intent.putExtra("information", goods_to_buy.get(i).getInfo());
                startActivityForResult(intent, 0);
            }
        });
//            @Override
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("移除商品").setMessage("从购物车移除" + goods_to_buy.get(i).getName() + "?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (goods_to_buy.remove(i)!=null )
                            buyAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "你选择了[取消]", Toast.LENGTH_SHORT).show();
                    }
                })
                        .show();
                return true;
            }
        });
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
//                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
//                alertDialog.setTitle("移除商品").setMessage("从购物车移除" + goods_to_buy.get(i).getName() + "?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        if (goods_to_buy.remove(i)!=null )
//                            buyAdapter.notifyDataSetChanged();
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(), "你选择了[取消]", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                        .show();
//                return true;
//            }
//        });

        final ImageButton btn = (ImageButton) findViewById(R.id.btn);
        btn.setTag("0");
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(btn.getTag()=="0")
                {
                    btn.setImageResource(R.drawable.mainpage);
                    btn.setTag("1");
                    buyLayer.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    btn.setImageResource(R.drawable.shoplist);
                    btn.setTag("0");
                    buyLayer.setVisibility(View.INVISIBLE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode==0&&resultCode==0){
            Bundle bud=intent.getExtras();
            String str=bud.getString("name");
            String  pri=bud.getString("price");
            String info=bud.getString("information");
            int cnt=bud.getInt("cnt",0);
            for(int i=0;i<cnt;i++){
                goods_to_buy.add(new Goods(str,info,pri));
            }
            buyAdapter.notifyDataSetChanged();
        }
    }
}
