package com.example.datta.barterapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CatLogCardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ItemDBHelper itemDBHelper;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemHold> listu=new ArrayList<>();
    ArrayList<ItemHold> listy=new ArrayList<>();


    String autem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_log_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CatLog");  // very Imp here..

        Intent z=getIntent();
        autem=z.getStringExtra("AUTHEMAIL");             // getting from menu Activity........

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       // on clicking of floating button its going to Addition of Item...
                Intent v=new Intent(getApplicationContext(),AddItemActivity.class);
                v.putExtra("LogMail",autem);
               startActivityForResult(v,25);
            }
        });


        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);

        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        itemDBHelper=new ItemDBHelper(this);
        listu=itemDBHelper.getItemList();


        // getting valid Items Per User.....
        for(ItemHold u:listu)
        {
            if(u.getAuthMailId().equals(autem))
            {
                listy.add(u);
                adapter=new ItemAdapter(listy,this);         // passing context here to help intent in adapter class
                recyclerView.setAdapter(adapter);
            }
        }


    }  // on create ends here..


    //overriding on Actvity  Result..


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
           if(requestCode==25)
             {
                 ArrayList<ItemHold> listy2=new ArrayList<>();
                 listu=itemDBHelper.getItemList();

                 for(ItemHold u:listu)
                 {
                     if(u.getAuthMailId().equals(autem))
                     {
                         listy2.add(u);
                         adapter=new ItemAdapter(listy2,this);         // passing context here to help intent in adapter class
                         recyclerView.setAdapter(adapter);
                     }
                 }

             }

    }  // on Activty Result Ends Here.......
}   // CatLogCard Ends here.....
