package com.example.datta.barterapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Datta on 2/26/2016.
 */
public class ItemHold {
    String itemname,Condition,Price,YearOfPurchase,Desc,SelectedCat,AuthMailId;
    Bitmap itemPhoto;

    public ItemHold() {
        // no arg Constructor...
    }

    public ItemHold(String itemname, String condition, String price, String yearOfPurchase, String desc, String selectedCat, String authMailId, Bitmap itemPhoto) {
        this.itemname = itemname;
        Condition = condition;
        Price = price;
        YearOfPurchase = yearOfPurchase;
        Desc = desc;
        SelectedCat = selectedCat;
        AuthMailId = authMailId;
        this.itemPhoto = itemPhoto;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getYearOfPurchase() {
        return YearOfPurchase;
    }

    public void setYearOfPurchase(String yearOfPurchase) {
        YearOfPurchase = yearOfPurchase;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getSelectedCat() {
        return SelectedCat;
    }

    public void setSelectedCat(String selectedCat) {
        SelectedCat = selectedCat;
    }

    public String getAuthMailId() {
        return AuthMailId;
    }

    public void setAuthMailId(String authMailId) {
        AuthMailId = authMailId;
    }

    public Bitmap getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(Bitmap itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemHold itemHold = (ItemHold) o;

        if (itemname != null ? !itemname.equals(itemHold.itemname) : itemHold.itemname != null)
            return false;
        if (Condition != null ? !Condition.equals(itemHold.Condition) : itemHold.Condition != null)
            return false;
        if (Price != null ? !Price.equals(itemHold.Price) : itemHold.Price != null) return false;
        if (YearOfPurchase != null ? !YearOfPurchase.equals(itemHold.YearOfPurchase) : itemHold.YearOfPurchase != null)
            return false;
        if (Desc != null ? !Desc.equals(itemHold.Desc) : itemHold.Desc != null) return false;
        if (SelectedCat != null ? !SelectedCat.equals(itemHold.SelectedCat) : itemHold.SelectedCat != null)
            return false;
        if (AuthMailId != null ? !AuthMailId.equals(itemHold.AuthMailId) : itemHold.AuthMailId != null)
            return false;
        return !(itemPhoto != null ? !itemPhoto.equals(itemHold.itemPhoto) : itemHold.itemPhoto != null);

    }

    @Override
    public int hashCode() {
        int result = itemname != null ? itemname.hashCode() : 0;
        result = 31 * result + (Condition != null ? Condition.hashCode() : 0);
        result = 31 * result + (Price != null ? Price.hashCode() : 0);
        result = 31 * result + (YearOfPurchase != null ? YearOfPurchase.hashCode() : 0);
        result = 31 * result + (Desc != null ? Desc.hashCode() : 0);
        result = 31 * result + (SelectedCat != null ? SelectedCat.hashCode() : 0);
        result = 31 * result + (AuthMailId != null ? AuthMailId.hashCode() : 0);
        result = 31 * result + (itemPhoto != null ? itemPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemHold{" +
                "itemname='" + itemname + '\'' +
                ", Condition='" + Condition + '\'' +
                ", Price='" + Price + '\'' +
                ", YearOfPurchase='" + YearOfPurchase + '\'' +
                ", Desc='" + Desc + '\'' +
                ", SelectedCat='" + SelectedCat + '\'' +
                ", AuthMailId='" + AuthMailId + '\'' +
                ", itemPhoto=" + itemPhoto +
                '}';
    }
    // i/p validation........

    public String validate()
    {
        String msg="";
        if(itemname==null||itemname.trim().equals(""))
        {
            msg="ItemName Can't be Empty";
        }
        else if(Condition==null||Condition.trim().equals(""))
        {
            msg="Condition Can't be Empty";
        }

        else if(Price==null||Price.trim().equals(""))
        {
            msg="Please tell the price of the item";
        }

        else if(YearOfPurchase==null||YearOfPurchase.trim().equals(""))
        {
            msg="Year Of Purchase Can't be Empty";
        }
        else if(Desc==null||Desc.trim().equals(""))
        {
            msg="Please Write Description abt product";
        }
        else if(SelectedCat==null||SelectedCat.trim().equals(""))
        {
            msg="Please Select the catgerory";
        }

        else if(itemPhoto==null)
        {
            msg="Please Add Item Picture";
        }


        return msg;

    }


}
