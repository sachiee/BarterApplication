package com.example.datta.barterapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MenuActivity extends AppCompatActivity   implements NavigationView.OnNavigationItemSelectedListener { // very imp
    TextView tv;
    ImageView iv;
    DBHelper helper=new DBHelper(this);
    String emailu;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);        // very imp ......

        tv= (TextView) findViewById(R.id.menutextView1);
        iv= (ImageView) findViewById(R.id.menuimg);

        Intent g=getIntent();
         emailu=g.getStringExtra("hold");

        List<Personholder> persons=helper.getPersons();

        for(Personholder manushya :persons)
        {
            if(manushya.getEmail().equals(emailu))
            {
                tv.setText("WelCome "+manushya.getName());                      // for setting photo and name...
                iv.setImageBitmap(manushya.getBeanphoto());
            }
        }


        //Naviagtion related Code..

        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle= new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // drawer.openDrawer(GravityCompat.START);   // defaultly navigation drawer will be opened




    } // on create Ends here..



    // action after pressing back button...  ( used for Navigatio drawer ..............)
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_logout) {
           Intent v=new Intent(this,LogInActivity.class);
            startActivity(v);
            Toast.makeText(this,"U successfully LoggedOut",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // After Clicking on Manage Catlog TextView

    public void manageCat(View view) {
        Intent p=new Intent(this,CatLogCardActivity.class);
        p.putExtra("AUTHEMAIL",emailu);
        startActivity(p);

    }

    /*--------------------------Navigation Item selected related--------------------------------------*/  // it is calling from on create method
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.Login)
        {
            Intent q=new Intent(this,LogInActivity.class);
            startActivity(q);
        }
        else if(id==R.id.signup)
        {
            Intent q=new Intent(this,RegisterActivity.class);
            startActivity(q);

        }
        else if(id==R.id.profile)
        {
            // profile
        }
        else if(id==R.id.nav_manage)
        {
               // tools related.......
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
