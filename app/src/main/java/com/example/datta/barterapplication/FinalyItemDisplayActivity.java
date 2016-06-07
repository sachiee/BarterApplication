package com.example.datta.barterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalyItemDisplayActivity extends AppCompatActivity {
    ImageView iv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    ItemDBHelper idh=new ItemDBHelper(this);
    ArrayList<ItemHold> listu=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaly_item_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv= (ImageView) findViewById(R.id.imageView5);
        tv1= (TextView) findViewById(R.id.textView4);
        tv2= (TextView) findViewById(R.id.textView5);
        tv3= (TextView) findViewById(R.id.textView6);
        tv4= (TextView) findViewById(R.id.textView8);

          Intent r=getIntent();
        String itemname=r.getStringExtra("ITEMNAME");      // getting from Item Adapter.......
        listu=idh.getItemList();
        for(ItemHold e:listu)
        {
            if(e.getItemname().equals(itemname))
            {
                iv.setImageBitmap(e.getItemPhoto());
                tv1.setText(e.getItemname());
                tv2.setText(e.getPrice());
                tv3.setText(e.getYearOfPurchase());
                tv4.setText(e.getDesc());
            }
        }




    }

}
