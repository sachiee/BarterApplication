package com.example.datta.barterapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Datta on 2/17/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    List<Personholder> list=new ArrayList<>();

    public static final String TAG="DATABASE";
    public static final String DBNAME="RegisterDB";
    public static final int VERSION=1;
    public static final String TBL_NAME="RegisterTable";
    public static final String COL_ID="_id";
    public static final String COL_PHOTO="Photo";
    public static final String COL_NAME="Name";
    public static final String COL_EMAIL="Email";
    public static final String COL_PWD="PassWord";
    public static final String COL_RPWD="RepeatPWD";
    public static final String COL_PHONENUM="PhoneNum";
    public static final String COL_DOB="DOB";
    public static final String COL_GENDER="gender";
    public static final String COL_CITY="City";
    public static final String COL_ADDRESS="Adress";

    public DBHelper(Context ctx)

    {
        super(ctx,DBNAME,null,VERSION);
        Log.d(TAG, "--->onarg constructor()");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TBL_NAME+"("+COL_ID+" integer primary key autoincrement, "+COL_PHOTO+" text,"+COL_NAME+" text,"+COL_EMAIL+" text,"+COL_PWD+" text,"+COL_RPWD+" text,"+COL_PHONENUM+" text,"+COL_DOB+" text,"+COL_GENDER+" text,"+COL_CITY+" text,"+COL_ADDRESS+" text )";
        Log.d(TAG, "--->oncreate()--->"+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"--->onupgrade() old ver= " + oldVersion + " new ver = "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS"+TBL_NAME);
        onCreate(db);

    }

    // inserting into db

    public boolean registertoDB(Personholder personholder)
    {
        SQLiteDatabase database=this.getWritableDatabase();

        //converting bitmap image to String by using BASE64 algorithm technique...

        Bitmap imgu=personholder.getBeanphoto();
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        imgu.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] arr=bos.toByteArray();
        String encodedimgstr=Base64.encodeToString(arr,Base64.DEFAULT);            // here photo is converted into string

        ContentValues cv=new ContentValues();
        cv.put(COL_PHOTO,encodedimgstr);
        cv.put(COL_NAME,personholder.getName());
        cv.put(COL_EMAIL,personholder.getEmail());
        cv.put(COL_PWD,personholder.getPwd());
        cv.put(COL_RPWD,personholder.getRpwd());
        cv.put(COL_PHONENUM,personholder.getPhonnum());
        cv.put(COL_DOB,personholder.getDob());
        cv.put(COL_GENDER,personholder.getGendertxt());
        cv.put(COL_CITY,personholder.getCity());
        cv.put(COL_ADDRESS, personholder.getAddrs());

        long id=database.insert(TBL_NAME,null,cv);
        if(id!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }

    } // Inserting IntoDB Ends here.....


    //Accessing List Of PersonHolder From DataBase....

    public List<Personholder> getPersons()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c=database.query(TBL_NAME,null,null,null,null,null,null,null);
        if(c.getCount()>0)
        {
            c.moveToFirst();
            do {

                 String photoString=c.getString(c.getColumnIndex(COL_PHOTO));
                  byte[]arr=Base64.decode(photoString, Base64.DEFAULT);
                  Bitmap fetchedphoto= BitmapFactory.decodeByteArray(arr, 0, arr.length);


                String fename=c.getString(c.getColumnIndex(COL_NAME));
                String femail=c.getString(c.getColumnIndex(COL_EMAIL));
                String fpass=c.getString(c.getColumnIndex(COL_PWD));
                String frpass=c.getString(c.getColumnIndex(COL_RPWD));
                String fphon=c.getString(c.getColumnIndex(COL_PHONENUM));
                String fdob=c.getString(c.getColumnIndex(COL_DOB));
                String fgender=c.getString(c.getColumnIndex(COL_GENDER));
                String fcity=c.getString(c.getColumnIndex(COL_CITY));
                String adress=c.getString(c.getColumnIndex(COL_ADDRESS));

                Personholder personholder=new Personholder();
                personholder.setBeanphoto(fetchedphoto);
                personholder.setName(fename);
                personholder.setEmail(femail);
                personholder.setPwd(fpass);
                personholder.setRpwd(frpass);
                personholder.setPhonnum(fphon);
                personholder.setDob(fdob);
                personholder.setGendertxt(fgender);
                personholder.setCity(fcity);
                personholder.setAddrs(adress);

                list.add(personholder);




            }
            while (c.moveToNext());
        }

         return list;
    }





}
