package com.example.datta.barterapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    String[] monthName = { "January", "February", "March", "April", "May", "June", "July","August", "September", "October", "November", "December" };
    EditText etname;
    EditText etemail;
    EditText passwrd;
    EditText rppasswrd;
    EditText phnum;
    EditText dob;
    RadioButton rb1;
    RadioButton rb2;
    RadioGroup rg;
    TextView cityname;
    TextView addrs;
    ImageButton ibphoto;
    String genderChoosed;
    DBHelper dbHelper=new DBHelper(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);    // we can make it as child for LoginActivity...
        etname= (EditText) findViewById(R.id.pername);
        etemail= (EditText) findViewById(R.id.editemail);
        passwrd= (EditText) findViewById(R.id.editpasswrd);
        rppasswrd= (EditText) findViewById(R.id.RepeatPass);
        phnum= (EditText) findViewById(R.id.EditPhoneNum);
        dob= (EditText) findViewById(R.id.editDOB);
        rb1= (RadioButton) findViewById(R.id.radioButton1);
        rb2= (RadioButton) findViewById(R.id.radioButton2);
        rg= (RadioGroup) findViewById(R.id.radioGroup);
        cityname= (TextView) findViewById(R.id.regtextView);
        addrs= (TextView) findViewById(R.id.addrstxt);
        ibphoto= (ImageButton) findViewById(R.id.editphoto);




         /*------------- using of radio group here-----------------------------*/


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton1)
                {
                    genderChoosed=rb1.getText().toString();
                }
                else if(checkedId==R.id.radioButton2)
                {
                    genderChoosed=rb2.getText().toString();
                }

            }
        });

         /*------------- using of radio group ends here-----------------------------*/



    }
    // on create ends here..


   /* ---------------for selecting Date from user of the app------------------------------*/

    public void selectDate(View view) {

        DateDialogHelper dh=new DateDialogHelper();
        dh.show(getFragmentManager(), "holdu");

    }




    // inner class..

    @SuppressLint("ValidFragment")
    class DateDialogHelper extends DialogFragment implements DatePickerDialog.OnDateSetListener {    // error ( warning) // ignore..


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {


            int day= Calendar.DAY_OF_MONTH;
            int month=Calendar.MONTH;
            int year=Calendar.YEAR;
            DatePickerDialog dialog=new DatePickerDialog(getActivity(),this,year,month,day);  // inside we have to pass context,on date Set listner callback,year,month,day
            return dialog;

        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String DOB =  monthName[monthOfYear]+" "+dayOfMonth+", "+year;
            dob.setText(DOB);

        }

    }// inner class ends here...

 /* ---------------for selecting Date from user of the app ends------------------------------*/


    /* ---------------for selecting Place from Google  map (plz see manifest file(chk gradle also)-----------------------------*/

// instance variables
    public  static final int MAP_REQ_CODE=5;
    LatLngBounds Default_loc_select=new LatLngBounds(new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090)); // default location..

    public void pickPlace(View view) {


        PlacePicker.IntentBuilder intentBuilder=new PlacePicker.IntentBuilder();
        try {
            Intent intent=intentBuilder.build(this);
            intentBuilder.setLatLngBounds(Default_loc_select);
            startActivityForResult(intent,MAP_REQ_CODE);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    // please see the On Activity Result Overridden code below..



      /* ---------------for selecting Place from Google  map ends here...(plz see manifest file)(chk gradle also)-----------------------------*/


    /*--------------------------------getting Photo------------------------------------------------------------*/
    public static final int CAMERA_CODE=1;
    public static final int GALARY_CODE=2;
    boolean rescam=false;
    Bitmap photo;
    public void uploadImage(View view) {
        final String[] arr={"Camera","Galary","Cancel"};   // final because inner class going to acess this one   // actually we have to use character sequence here..

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Choose YOUR Option");
        builder.setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (arr[which].equals("Camera")) {
                    Intent v = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);            // for camera action..
                    startActivityForResult(v, CAMERA_CODE);

                }
                if (arr[which].equals("Galary")) {                              // for galary action...
                    Intent in = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    in.putExtra("crop", "true");
                    in.putExtra("outputX", 200);
                    in.putExtra("outputY", 200);
                    startActivityForResult(in, GALARY_CODE);

                }
                if (arr[which].equals("Cancel")) {
                    dialog.dismiss();
                }


            }
        });
        builder.show();             // very imp
    }

/*------ overriddien on activity Result method here...................*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == MAP_REQ_CODE) {
                Place jaaga = PlacePicker.getPlace(this, data);
                String pname = "" + jaaga.getName();              // it returns in character sequence so used ""
                String adrs = "" + jaaga.getAddress();
                cityname.setText(pname);
                addrs.setText(adrs);
            }

            if (requestCode == CAMERA_CODE) {
                rescam = true;                                            // to make sure getting image from camera anta
                photo = (Bitmap) data.getExtras().get("data");
                Log.d("AAAAAA",photo.getHeight()+"    "+photo.getWidth());
                photo =Bitmap.createBitmap(photo,0,0,105,133);  // croping image... we cant give more dan 133 bcz here bitmap width is 133 and heigth is 235
                ibphoto.setImageBitmap(photo);                     // for front camera height is 186 and width is 105



            }
            if (requestCode == GALARY_CODE) {
                rescam = false;
                photo = (Bitmap) data.getExtras().get("data");
                ibphoto.setImageBitmap(photo);

            }
        }
    }
   /*------ overriddien on activity Result method ends here...................*/



      /*---------------------------------Registering your account-----------------------*/

    public void registerMadu(View view) {

        String name=etname.getText().toString();
        String email=etemail.getText().toString();
        String password=passwrd.getText().toString();
        String rpwd=rppasswrd.getText().toString();
        String ph=phnum.getText().toString();
        String dateofB=dob.getText().toString();
        String city=cityname.getText().toString();
        String addrssu=addrs.getText().toString();

        Personholder phold=new Personholder(name,email,password,rpwd,dateofB,genderChoosed,ph,city,addrssu,photo);

        String res=phold.validate();
        if(res.equals(""))
        {
                boolean result=dbHelper.registertoDB(phold);
            if(result)
            {
                Toast.makeText(this,"You have Successfully Registered to your Accout",Toast.LENGTH_LONG).show();
                Intent p=new Intent(this,MenuActivity.class);
                p.putExtra("hold",email);
                startActivity(p);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
        }

    }

/*---------------------------------Registering your account ends here-----------------------*/





}// RegisteActvity Ends here....
