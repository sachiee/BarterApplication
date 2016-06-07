package com.example.datta.barterapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dattatreya on 1/25/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter {
    static ArrayList<ItemHold> list=new ArrayList<ItemHold>(); // static here bcz i want to acess dis 1 inside static innner class..
    static Context m;

    public ItemAdapter(ArrayList<ItemHold> list, Context ctx)           // here context is helping for intent..
    {
        this.list = list;
        this.m=ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("AAAAAAAAA","-------------> onCreteViewHolder");
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_helper_layout,parent,false);
        Holdu h=new Holdu(v);
        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d("AAAAAAAAA","-------------> onBindViewHolder "+position);
        Holdu h= (Holdu) holder;
        ItemHold dh=list.get(position);
        h.iv.setImageBitmap(dh.getItemPhoto());
        h.tv1.setText("Catgeory: "+dh.getSelectedCat());
        h.tv2.setText("Item: "+dh.getItemname());
        h.tv3.setText("Status: Avilable");


    }

    @Override
    public int getItemCount() {
       // Log.d("AAAAAAAAA","-------------> getItemCount");

        return list.size();
    }

    // static Inner Class..

    public static  class Holdu extends RecyclerView.ViewHolder
    {

        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
                         // after invoking dis control will go back to onBindView Holder...
        public Holdu(View itemView) {
            super(itemView);
            Log.d("AAAAAAAAA", "-------------> onHoldu");
            iv= (ImageView) itemView.findViewById(R.id.imageView);
            tv1= (TextView) itemView.findViewById(R.id.textView);
            tv2= (TextView) itemView.findViewById(R.id.textView2);
            tv3= (TextView) itemView.findViewById(R.id.textView3);


            // attaching listner....
            // after clicking on item ..will fire Final Activity...
            // since der is no dedault onclick item listner for recyclerview..(v imp)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();                        // this one is very imp...
                    Log.d("AAAAAA", "---------------------------uclicked " + pos);
                    ItemHold dh = list.get(pos);
                    Intent p = new Intent(m, FinalyItemDisplayActivity.class);

                    p.putExtra("ITEMNAME",dh.getItemname());
                    m.startActivity(p);

                }
            });

        }
    }

}
