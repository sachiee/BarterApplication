package com.example.datta.barterapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Datta on 2/17/2016.
 */
public class Personholder implements Serializable{
    String name,email,pwd,rpwd,dob,gendertxt,phonnum,city,addrs;
    Bitmap beanphoto;


    //i/p validation........

    public String validate()
    {
        String msg="";
        if(name==null||name.trim().equals(""))
        {
            msg="Name Can't be Empty";
        }
       else if(email==null||email.trim().equals(""))
        {
            msg="Email Can't be Empty";
        }
        else if(!email.contains("@gmail")&&!email.contains("@yahoo"))
        {
            msg="Please Provide Valid Email Id";
        }
        else if(pwd==null||pwd.trim().equals(""))
        {
            msg="Password Can't be Empty";
        }
        else if(!rpwd.equals(pwd))
        {
            msg="Please Enter Same PassWord";
        }
        else if(dob==null||dob.trim().equals(""))
        {
            msg="Date Of Birth Can't be Empty";
        }
        else if(gendertxt==null||gendertxt.trim().equals(""))
        {
            msg="Choose Your Gender Dude";
        }
        else if(phonnum==null||phonnum.trim().equals(""))
        {
            msg="Please Provide your PhoneNumber";
        }
        else if(city==null||city.trim().equals(""))
        {
            msg="Please Choose Your City";
        }else if(addrs==null||addrs.trim().equals(""))
        {
            msg="Address Can't be Empty";
        }
        else if(beanphoto==null)
        {
            msg="Please Set your Account Picture";
        }


        return msg;

    }




    public Personholder() {
        // no arg constructor....
    }

    public Personholder(String name, String email, String pwd, String rpwd, String dob, String gendertxt, String phonnum, String city, String addrs, Bitmap beanphoto) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.rpwd = rpwd;
        this.dob = dob;
        this.gendertxt = gendertxt;
        this.phonnum = phonnum;
        this.city = city;
        this.addrs = addrs;
        this.beanphoto = beanphoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRpwd() {
        return rpwd;
    }

    public void setRpwd(String rpwd) {
        this.rpwd = rpwd;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGendertxt() {
        return gendertxt;
    }

    public void setGendertxt(String gendertxt) {
        this.gendertxt = gendertxt;
    }

    public String getPhonnum() {
        return phonnum;
    }

    public void setPhonnum(String phonnum) {
        this.phonnum = phonnum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }

    public Bitmap getBeanphoto() {
        return beanphoto;
    }

    public void setBeanphoto(Bitmap beanphoto) {
        this.beanphoto = beanphoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personholder that = (Personholder) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (rpwd != null ? !rpwd.equals(that.rpwd) : that.rpwd != null) return false;
        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        if (gendertxt != null ? !gendertxt.equals(that.gendertxt) : that.gendertxt != null)
            return false;
        if (phonnum != null ? !phonnum.equals(that.phonnum) : that.phonnum != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (addrs != null ? !addrs.equals(that.addrs) : that.addrs != null) return false;
        return !(beanphoto != null ? !beanphoto.equals(that.beanphoto) : that.beanphoto != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (rpwd != null ? rpwd.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gendertxt != null ? gendertxt.hashCode() : 0);
        result = 31 * result + (phonnum != null ? phonnum.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (addrs != null ? addrs.hashCode() : 0);
        result = 31 * result + (beanphoto != null ? beanphoto.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Personholder{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", rpwd='" + rpwd + '\'' +
                ", dob='" + dob + '\'' +
                ", gendertxt='" + gendertxt + '\'' +
                ", phonnum='" + phonnum + '\'' +
                ", city='" + city + '\'' +
                ", addrs='" + addrs + '\'' +
                ", beanphoto=" + beanphoto +
                '}';
    }
}
