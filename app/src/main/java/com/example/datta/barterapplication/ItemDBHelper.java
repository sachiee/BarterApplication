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
 * Created by Datta on 2/29/2016.
 */
public class ItemDBHelper extends SQLiteOpenHelper {

    List<ItemHold> list=new ArrayList<>();

    public static final String TAG="ITEMDATABASE";
    public static final String DBNAME="ITEMSRegisterDBTKPPQY";
    public static final int VERSION=1;
    public static final String TBL_NAME="ITEMRegisterTable";
    public static final String COL_ID="_id";
    public static final String COL_ItemAuthenticatedMail="ItemAuthenticatedMail";
    public static final String COL_ItemPhoto="ItemPhoto";
    public static final String COL_ItemName="ItemName";
    public static final String COL_ItemCondition="ItemCondition";
    public static final String COL_ItemPrice="ItemPrice";
    public static final String COL_ItemPurYear="ItemPurYear";
    public static final String COL_ItemDesc="ItemDesc";
    public static final String COL_Itemcat="Itemcat";

    public ItemDBHelper(Context ctx)

    {
        super(ctx,DBNAME,null,VERSION);
        Log.d(TAG, "--->onarg constructor()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TBL_NAME+"("+COL_ID+" integer primary key autoincrement, "+COL_ItemAuthenticatedMail+" text,"+COL_ItemPhoto+" text,"+COL_ItemName+" text,"+COL_ItemCondition+" text,"+COL_ItemPrice+" text,"+COL_ItemPurYear+" text,"+COL_ItemDesc+" text,"+COL_Itemcat+" text )";
        Log.d(TAG, "--->oncreate()--->" + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"--->onupgrade() old ver= " + oldVersion + " new ver = "+newVersion);
        db.execSQL("DROP TABLE IF EXISTS" + TBL_NAME);
        onCreate(db);

    }

    public boolean addItemToDB(ItemHold itemHold) {
        SQLiteDatabase database=this.getWritableDatabase();

        //converting bitmap image to String by using BASE64 algorithm technique...

        Bitmap imgu=itemHold.getItemPhoto();
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        imgu.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] arr=bos.toByteArray();
        String encodedimgstr= Base64.encodeToString(arr, Base64.DEFAULT);            // here photo is converted into string


        ContentValues cv=new ContentValues();
        cv.put(COL_ItemPhoto,encodedimgstr);
        cv.put(COL_ItemAuthenticatedMail,itemHold.AuthMailId);
        cv.put(COL_ItemName,itemHold.getItemname());
        cv.put(COL_ItemCondition,itemHold.getCondition());
        cv.put(COL_ItemPrice,itemHold.getPrice());
        cv.put(COL_ItemPurYear,itemHold.getYearOfPurchase());
        cv.put(COL_ItemDesc,itemHold.getDesc());
        cv.put(COL_Itemcat,itemHold.getSelectedCat());


        long id=database.insert(TBL_NAME,null,cv);
        if(id!=-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }   // inserting intodb ends here.......


    //Listing of Items

    public ArrayList<ItemHold> getItemList()
    {
        ArrayList<ItemHold> list=new ArrayList<>();
     SQLiteDatabase db=this.getReadableDatabase();
       Cursor c= db.query(TBL_NAME, null, null, null, null, null, null, null);
        if(c.getCount()>0)
        {
            c.moveToFirst();
            do {
                   ItemHold it=new ItemHold();
                String itname=c.getString(c.getColumnIndex(COL_ItemName));
                String itAutmail=c.getString(c.getColumnIndex(COL_ItemAuthenticatedMail));
                String itphoto=c.getString(c.getColumnIndex(COL_ItemPhoto));
                String itcond=c.getString(c.getColumnIndex(COL_ItemCondition));
                String itprice=c.getString(c.getColumnIndex(COL_ItemPrice));
                String itpuryear=c.getString(c.getColumnIndex(COL_ItemPurYear));
                String itdesc=c.getString(c.getColumnIndex(COL_ItemDesc));
                String itcat=c.getString(c.getColumnIndex(COL_Itemcat));

                byte[]arr=Base64.decode(itphoto, Base64.DEFAULT);
                Bitmap fetchedphoto= BitmapFactory.decodeByteArray(arr, 0, arr.length);

                it.setItemname(itname);
                it.setAuthMailId(itAutmail);
                it.setCondition(itcond);
                it.setDesc(itdesc);
                it.setItemPhoto(fetchedphoto);
                it.setPrice(itprice);
                it.setSelectedCat(itcat);
                it.setYearOfPurchase(itpuryear);
                list.add(it);

            }while (c.moveToNext());
        }
        return  list;
    }

}
