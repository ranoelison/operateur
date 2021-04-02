/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import utils.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Fonction {
    public static String getStringtmstp(Date d){
         int j = d.getDate();
        int h = d.getHours();
        int min = d.getMinutes();
        int mois = d.getMonth() + 1;
        int year = d.getYear() + 1900;
        int s = d.getSeconds();
        String sl = "-";
        String pt = ":";
        String sep = " ";
        String st = year + sl + mois + sl + j + sep + h + pt + min + pt + s + sep ;
        return st;
    }
    public String getTmstp(Date tmstp){
        String valiny = new String();
        String  temp = new String();
        Date dtemp = new Date(tmstp.getYear(),tmstp.getMonth(),tmstp.getDate(),tmstp.getHours(),tmstp.getMinutes());
        if(dtemp.getHours()<12){
            int hTimes = dtemp.getHours();
            dtemp.setHours(hTimes);
            String str = this.getStringtmstp(dtemp);
            temp = "'".concat(str);
            temp = temp.concat(" AM'");
        valiny = temp.concat(",'YYYY-MM-DD HH:MI:SS AM'");
       
        }
        if(dtemp.getHours()>12){
            int hTimes = dtemp.getHours()-12;
            dtemp.setHours(hTimes);
            String str = this.getStringtmstp(dtemp);
            temp = "'".concat(str);
            temp = temp.concat(" PM'");
        valiny = temp.concat(",'YYYY-MM-DD HH:MI:SS PM'");
      
        }
        if(dtemp.getHours()==0){
            int hTimes = 12;
            dtemp.setHours(hTimes);
            String str = this.getStringtmstp(dtemp);
            temp = "'".concat(str);
            temp = temp.concat(" AM'");
        valiny = temp.concat(",'YYYY-MM-DD HH:MI:SS AM'");
      
        }
        if(dtemp.getHours()==12){
            String str = this.getStringtmstp(dtemp);
            temp = "'".concat(str);
            temp = temp.concat(" PM'");
        valiny = temp.concat(",'YYYY-MM-DD HH:MI:SS PM'");
        
        }
        /*
        else{
            String str = this.getStringtmstp(tmstp);
            temp = "'".concat(str);
            temp = temp.concat(" AM'");
        valiny = temp.concat(",'YYYY-MM-DD HH:MI:SS AM'");
        }
        */
        return valiny;
    }
     public int soustraire(Date d1,Date d2){
        long milli = d1.getTime() - d2.getTime();
//        System.out.println("Difference between  " + d1 + " and "+ d2+" is "
//        + (milli  /(1000 * 60 * 60 * 24) )+ " days.");
        int min = (int) (milli /(1000 * 60 * 60 * 24));
        /*
        days :/(1000 * 60 * 60 * 24
        60000milli->1min
        xmilli->(x*1)/60000
        */
        return min;
    }
      public double diff_heure(Date d1,Date d2){
        int h1=d1.getHours();
        int min1 = d1.getMinutes();
        int h2=d2.getHours();
        int min2 = d2.getMinutes();
        if(min2<min1){
          h2=  h2-1;
          min2 = min2+60;
        }
        int h = h2-h1;
        int min = min2-min1;
       
//        System.out.println("Difference between  " + d1 + " and "+ d2+" is "
//        + (milli  /(1000 * 60 * 60 * 24) )+ " days.");
//        int min = (int) (milli /(1000 * 60 * 60));
        /*
        days :/(1000 * 60 * 60 * 24
        60000milli->1min
        xmilli->(x*1)/60000
        
        1min->60000milli
        60min-> 3 600 000milli
        
        1h->3 600 000milli
        */
        Time t = new Time(h,min,0);
        double d = (float)t.getMinutes()/60;
        double heure =h+d;
        return heure;
    }
    public Date getDate(String date) {
        String[] date_time = date.toString().split(" ");
        if (date_time.length < 2) {
            date_time = date.toString().split("T");
        }
        String[] str_ymd = date_time[0].split("/");
        if (str_ymd.length < 3) {
            str_ymd = date_time[0].split("-");
        }
        String[] str_hms = date_time[1].split(":");
        Integer d, m, y, h, mm;
        Date ret = new Date();
        try {
            y = new Integer(str_ymd[0]);
            m = new Integer(str_ymd[1]);
            d = new Integer(str_ymd[2]);
            h = new Integer(str_hms[0]);
            mm = new Integer(str_hms[1]);
            ret = new Date(y - 1900, m - 1, d, h, mm, 0);
        } catch (Exception ex) {
            y = new Integer(str_ymd[2]);
            m = new Integer(str_ymd[1]);
            d = new Integer(str_ymd[0]);
            h = new Integer(str_hms[0]);
            mm = new Integer(str_hms[1]);
            ret = new Date(y - 1900, m - 1, d, h, mm, 0);
        }
        return ret;
    }
    public String getNextSeq(Connection con,String seq) throws Exception{
       PreparedStatement pst = null;
       String id=null;
        try{
            if(con==null){con = new Connexion().getConn();}
            String sql = "select nextval(?)";
             pst = con.prepareStatement(sql);
            String seq_=  "seq_"+seq;
             pst.setString(1,seq_);
             
            ResultSet res = pst.executeQuery();
            int next = 0;
            while(res.next()){             
                next= res.getInt("nextval");
            }
            if(seq.compareTo("client")==0){
                id="CLI-"+next;
            }
             if(seq.compareTo("compte")==0){
                id="COMPTE-"+next;
            }
              if(seq.compareTo("forfait")==0){
                id="FRF-"+next;
            }
                if(seq.compareTo("mvt")==0){
                id="MVT-"+next;
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally {
            if (pst != null) {
                pst.close();
            }
        }
        return id;
    }
    public int convertTosecond(double time,String unite){
        int sec= 0;
        if(unite.compareTo("min")==0){
            sec= (int)time*60;
        }
        if(unite.compareTo("heure")==0){
            sec=(int)time*3600;
        }
        return sec;
    }
    public Date ajouterDate(Date d1,int validite,String unite){
        return new Date();
    }
      public String setHash(Connection con,String ident,String typelog) throws NoSuchAlgorithmException, Exception{
          String hash = null;
        PreparedStatement pst = null;
        PreparedStatement pstin = null;
        try{
            if(con==null){con = new Connexion().getConn();}
             con.setAutoCommit(false);
            String sql = "select md5(?)";
             pst = con.prepareStatement(sql);
             Date d = new Date();
             String t = ident+d;
             pst.setString(1, t);
              ResultSet res = pst.executeQuery();
            while(res.next()){
               hash= res.getString("md5");
            }
             String sqlin = null;
            if(typelog.compareTo("admin")==0){
                sqlin = "begin; insert into token_Admin values(?,?,'2021-3-30')";
            }
             if(typelog.compareTo("client")==0){
                sqlin = "begin; insert into token_Client values(?,?,'2021-3-30')";
            }
            pstin = con.prepareStatement(sqlin);
            System.out.println("ident"+ident);
            pstin.setString(1,ident);
            System.out.println("hash"+hash);
            pstin.setString(2, hash);
            
            int executeUpdate = pstin.executeUpdate();
            con.commit();
        }
         catch(Exception ex){
             con.rollback();
            throw ex;
        }
         finally {
            if (pst != null) {
                pst.close();
            }
        }
        return hash;
    }
       public String getHash(Connection con,String pwd) throws Exception{
        String hash = null;
        PreparedStatement pst = null;
        try{
            if(con==null){con = new Connexion().getConn();}
            String sql = "select md5(?)";
             pst = con.prepareStatement(sql);
             pst.setString(1, pwd);
              ResultSet res = pst.executeQuery();
            while(res.next()){
               hash= res.getString("md5");
            }
        }
         catch(Exception ex){
            throw ex;
        }
         finally {
            if (pst != null) {
                pst.close();
            }
        }
        return hash;
    }
    public String getIdToken(Connection con,String type,String token) throws Exception{
        String id = null;
        PreparedStatement pst = null;
        try{
            if(con==null){con = new Connexion().getConn();}
             String sql = null;
            if(type.compareTo("admin")==0){
                sql = "select idAdmin from Token_Admin where token=?";
            }
            if(type.compareTo("client")==0){
                sql = "select idCompte from Token_Client where token=?";
            }
            pst = con.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                id = res.getString(0);
            }
        }
         catch(Exception ex){
            throw ex;
        }
         finally {
            if (pst != null) {
                pst.close();
            }
        }
        return id;
    }
}
