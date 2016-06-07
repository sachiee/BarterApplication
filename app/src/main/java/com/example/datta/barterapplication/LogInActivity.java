package com.example.datta.barterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LogInActivity extends AppCompatActivity {
    TextView tv;
    EditText et1;
    EditText et2;
    Button ib;
    boolean hold;
    DBHelper helper=new DBHelper(this);
    public static  final String TAG="LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        tv= (TextView) findViewById(R.id.logintxt);
        et1= (EditText) findViewById(R.id.editText);
        et2= (EditText) findViewById(R.id.editText2);
        ib= (Button) findViewById(R.id.imageButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // button listener ..used fa switching to register activity..
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setTitle("LogIn");  // very Imp here..






    } // oncreate ends here..



    /* ------------- login-------------------*/

    public void loginAgu(View v)
    {
        //i/p validations...

        String emailu=et1.getText().toString();
        String pwd=et2.getText().toString();
        if(emailu==null||emailu.trim().equals(""))
        {
            Toast.makeText(this, "Plz Give your Email Id", Toast.LENGTH_SHORT).show();


        }
       else if(pwd==null||pwd.trim().equals(""))
        {
            Toast.makeText(this, "Plz Give your PassWord", Toast.LENGTH_SHORT).show();
        }
        else {
            List<Personholder> persons=helper.getPersons();
            Log.d(TAG,"persons-----> "+persons);

            for(Personholder manushya :persons)
            {
                if(manushya.getEmail().equals(emailu)&&manushya.getPwd().equals(pwd))
                {
                    hold=true;
                    Intent p=new Intent(this,MenuActivity.class);
                    p.putExtra("hold",manushya.getEmail());
                    startActivity(p);
                    break;
                }
                else {
                    hold=false;
                }
            }

            if(!hold)
            {
                Toast.makeText(this,"YOU ARE NOT REGISTERED YET",Toast.LENGTH_SHORT).show();
            }

        }


    }
  /* ------------- login ends here-------------------*/



}
