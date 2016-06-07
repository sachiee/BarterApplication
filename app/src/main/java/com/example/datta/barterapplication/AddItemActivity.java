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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {
    String autem;

    ItemDBHelper idb=new ItemDBHelper(this);
    String[] monthName = { "January", "February", "March", "April", "May", "June", "July","August", "September", "October", "November", "December" };
    public static final String TAG="ADDITEM";
    Spinner sp;
    List<String> category=new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText itemname;
    EditText condofItem;
    EditText priceofitem;
    EditText yearofpur;
    EditText Description;
    ImageView itemphoto;
    String selCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AddItems");  // very Imp here..

        itemname= (EditText) findViewById(R.id.editText3);
        condofItem= (EditText) findViewById(R.id.editText4);
        priceofitem= (EditText) findViewById(R.id.editText5);
        yearofpur= (EditText) findViewById(R.id.editText6);
        Description= (EditText) findViewById(R.id.editText7);
        itemphoto= (ImageView) findViewById(R.id.imageView3);


        Intent z=getIntent();
        autem=z.getStringExtra("LogMail");            // getting from CatLogCardActivity.....


        sp= (Spinner) findViewById(R.id.spinner);
        category.add("Mobiles and Accessories");
        category.add("Watches");
        category.add("Men Related");
        category.add("Women Related");
        category.add("General");



        Log.d(TAG, "AddItemActivty------>" + category.toString());
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
        sp.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Spinner", "onItemSelected");
                Toast.makeText(getApplicationContext(), "data selected=" + category.get(position), Toast.LENGTH_SHORT).show();
                selCat=category.get(position);
           }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // if nothin selected

            }});
    } // on create ends here..

     // ..............................uploading Photo Of Item.....................................

    public static final int CAMERA_CODE=1;
    public static final int GALARY_CODE=2;
    boolean rescam=false;
    Bitmap photo;
    public void uploadItemPhoto(View view) {

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


            if (requestCode == CAMERA_CODE) {
                rescam = true;                                            // to make sure getting image from camera anta
                photo = (Bitmap) data.getExtras().get("data");
                Log.d("AAAAAA",photo.getHeight()+"    "+photo.getWidth());
                photo =Bitmap.createBitmap(photo,0,0,105,133);  // croping image... we cant give more dan 133 bcz here bitmap width is 133 and heigth is 235
                itemphoto.setImageBitmap(photo);                     // for front camera height is 186 and width is 105



            }
            if (requestCode == GALARY_CODE) {
                rescam = false;
                photo = (Bitmap) data.getExtras().get("data");
                itemphoto.setImageBitmap(photo);

            }
        }
    }
   /*------ overriddien on activity Result method ends here...................*/

    // Purchasing Date ........
    public void purchDate(View view) {
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
            yearofpur.setText(DOB);

        }

    }// inner class ends here...

    // Adding of Item Into DataBase........
    public void addItem(View view) {
        Bitmap snap=photo;
        String itname=  itemname.getText().toString();
        String itcondition= condofItem.getText().toString();
        String itprice= priceofitem.getText().toString();
        String ityop=  yearofpur.getText().toString();
        String itdesc= Description.getText().toString();
        String itcatgry= selCat;

        ItemHold itemHold=new ItemHold(itname,itcondition,itprice,ityop,itdesc,itcatgry,autem,snap);

        String res=itemHold.validate();
        if(res.equals(""))
        {
            boolean result=idb.addItemToDB(itemHold);
            if(result)
            {

                Snackbar.make(view, "You have Successfully Added Your Item to database", Snackbar.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
        }

    } // adding of Item Ends here.........




} // Main Class Ends Here.......






