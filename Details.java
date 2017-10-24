package com.example.czy.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Details extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout sec=(LinearLayout)findViewById(R.id.buy_view);
        sec.setVisibility(View.INVISIBLE);
        FloatingActionButton flt=(FloatingActionButton)findViewById(R.id.btn);
        flt.setVisibility(View.INVISIBLE);
        Intent intent=getIntent();

        final String ShowName=intent.getStringExtra("name");
        final String Price=intent.getStringExtra("price");
        final String info=intent.getStringExtra("information");

        DetailAdapter mAdapter;
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new DetailAdapter(ShowName,Price,info,Details.this,Details.this));

    }
}
