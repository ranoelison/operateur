/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import utils.Connexion;


/**
 *
 * @author ASUS
 */

public class Compte {
    Mvt_comptes[] mvt;
    String numero;
    String pwd;
    String token;
    String idCompte;
    String idOffre;

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public void setToken(String token) throws Exception {
        Connection con = new Connexion().getConn();
        String id = new Fonction().getIdToken(con, "client", token);
        this.setIdCompte(id);
        this.token = token;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNumero() {
        return numero;
    }

    public String getPwd() {
        return pwd;
    }

    public Compte(String numero, String pwd) {
        this.numero = numero;
        this.pwd = pwd;
    }
    
    public String login() throws Exception{
        Connection con = null;
        PreparedStatement pst = null;
         String token = null;
        try{
            con = new Connexion().getConn();
             String sql = "select * from Compte where numero=? and motDePasse=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,numero);
             Fonction f = new Fonction();
             pwd = f.getHash(con,pwd);
             pst.setString(2, pwd);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                token = f.setHash(con,res.getString("numero"),"client");
            }
        }
        catch(Exception ex){
            throw ex;
        }
         finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return token;
    }
    public String createAccount(Connection con ,String mdp,String idClient) throws Exception{
        PreparedStatement pst = null;
        String num = generateNum();
        try{
            if(con==null){con = new Connexion().getConn();}
            String idcompte = new Fonction().getNextSeq(con,"compte");
             String sql = "begin; insert into Compte values(?,?,?,md5('"+mdp+"'),current_timestamp)";
             pst = con.prepareStatement(sql);
             pst.setString(1,idcompte);
             pst.setString(2,idClient);
             pst.setString(3,num);
//             pst.setString(4,mdp);
            int executeUpdate = pst.executeUpdate();
        }
        catch(Exception ex){
            throw ex;
        }
        finally {
            if (pst != null) {
                pst.close();
            }
        }
        return num;
    }
    String generateNum(){
        Random r = new Random();
        Long num = r.nextLong();
        if(num<0){
            num = num*-1;
        }
        String numero = num.toString();
        int l = numero.length();
        if(l>7){
            char[] temp = numero.toCharArray();
            char[] tab = new char[7];
            int a =0;
            if(r.nextBoolean()){
                for(int j=0;j<7;j++){
                    tab[a] = temp[j];
                    a++;
                }
            }
            else{
                for(int j=7;j>0;j--){
                    tab[a] = temp[j];
                    a++;
                }
            }
            numero = new String(tab);
        }
        numero = "034"+numero;
        return numero;
    }
    public void achatOffre() throws Exception{
        Connection con = null;
        PreparedStatement pst = null;
        String idf = "";
        try{
            Fonction f = new Fonction(); 
            con = new Connexion().getConn();
            con.setAutoCommit(false);
            Offre of = new Offre().findById(idOffre);
            double coutOff = of.getCout();
            //VIA MOBILE MONEY SEULEMENT
            verifySoldeMV_credit(con,idCompte,coutOff);
            idf = f.getNextSeq(con,"forfait");
            Date exp = f.ajouterDate(new Date(), of.getValidite(), of.getUniteTemps());
            Timestamp ts=new Timestamp(exp.getTime());
            String sql = "begin; insert into Forfait values(?,?,?,current_timestamp,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, idf);
            pst.setString(2,idCompte);
            pst.setString(3, idOffre);
            pst.setTimestamp(4,ts);
            int executeUpdate = pst.executeUpdate();
            insertSoldeForfait(con,idf,idOffre);
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
            if (con != null) {
                con.close();
            }
        }
    }
     public void insertSoldeForfait(Connection con,String idForfait,String idOffre) throws Exception{
         PreparedStatement pst = null;
         try{
            Offre offre = new Offre().findById(idOffre, con);
//qtevoixint | qtevoixext | qtevoixmixte | unitvi | unitve | unitvm | nbsmsi | nbsmse | nbsmsm | qtenet
            double qtevoixint = getQteReel(offre.getQteVoixInt(),offre.getUnitVI(),offre);
            double qtevoixext = getQteReel(offre.getQteVoixExt(),offre.getUnitVE(),offre);
            double qtevoixmixte = getQteReel(offre.getQteVoixMixte(),offre.getUnitVM(),offre);
            int nbsmsi = offre.getNbSmsI();
            int nbsmse = offre.getNbSmsE();
            int nbsmsm = offre.getNbSmsM();
            double qtenet = offre.getQteNet();
            String sql = "insert into soldeForfait values(?,?,?,?,?,?,?,?)";
             pst = con.prepareStatement(sql);
            pst.setString(1, idForfait);
            pst.setDouble(2,qtevoixint);
            pst.setDouble(3,qtevoixext);
            pst.setDouble(4,qtevoixmixte);
            pst.setInt(5,nbsmsi);
            pst.setInt(6,nbsmse);
            pst.setInt(7,nbsmsm);
            pst.setDouble(8,qtenet);
            int executeUpdate = pst.executeUpdate();
         }
         catch(Exception ex){
             throw ex;
         }
     }
     public double getQteReel(double qteVoix,String unite,Offre of){
         double ret =0;
         if(qteVoix==0){
             return 0;
         }
         if(unite.compareTo("min")==0){
            ret = qteVoix;
         }
         if(unite.compareTo("ar/s")==0){
             ret = of.getCout();
         }
         if(unite.compareTo("ar")==0){
             ret = qteVoix;
         }
         return ret;
     }
    public void  verifySoldeMV_credit(Connection con,String idCompte,double coutOff) throws SQLException, Exception{
        try{
            con = new Connexion().getConn();
            double soldecredit =getSoldeCredit(con,idCompte);
            double soldeMV=getSoldeMV(con,idCompte);
            if(soldecredit<coutOff){
                throw new Exception("credit insuffisant");
            }
             if(soldeMV<coutOff){
                throw new Exception("solde mvola insuffisant");
            }
        }
        catch(Exception ex){
            throw ex;
        }
    }
    public double getSoldeMV(Connection con,String idCompte) throws Exception{
        PreparedStatement pst = null;
        double soldeMV =0;
        try{
            con = new Connexion().getConn();
             String sql = "select * from V_Solde where idCompte=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,idCompte);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                soldeMV = res.getDouble("solde");
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
        return soldeMV;
    }
    public double getSoldeCredit(Connection con,String idCompte) throws SQLException, Exception{
        PreparedStatement pst = null;
         double soldecredit =0;
        try{
            con = new Connexion().getConn();
            String sql = "select sum(montant)as resteCredit from Credit where idCompte=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,idCompte);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                soldecredit = res.getDouble("resteCredit");
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
        return soldecredit;
    }
    public String getCompteById(Connection con,String idCompte) throws Exception{
         PreparedStatement pst = null;
         Compte c = null;
         String num = null;
        try{
            con = new Connexion().getConn();
            String sql = "select numero from Compte where idCompte=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,idCompte);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                num = res.getString("numero");
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
        return num;
    }
    public double getCredit(Connection con,String idCompte) throws Exception{
         PreparedStatement pst = null;
        double credit =0;
        try{
            if(con==null){ con = new Connexion().getConn(); }
             String sql = "select sum(montant) from Credit where idCompte=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,idCompte);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                credit = res.getDouble("sum");
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
        return credit;
    }
    public ResteForfait[] getForfaits(Connection con,String idCompte,String typeForfait) throws SQLException, Exception{
        ResteForfait[] idf = null;
        PreparedStatement pst = null;
        ArrayList list = new ArrayList();
        try{
            if(con==null){ con = new Connexion().getConn(); }
//            String sql = "select * from Forfait join Offre on Forfait.idOffre=Offre.idOffre join Categorie on Offre.idCategorie=Categorie.idCategorie where Categorie.description like '%?%' and idCompte=? and dateExp>=current_timestamp";
            String sql = "select * from resteForfaitValide where idCompte=? order by dateExp asc";
            ResteForfait temp = null;
            pst = con.prepareStatement(sql);
             pst.setString(1, idCompte);
             ResultSet res = pst.executeQuery();
            while(res.next()){
//                this.restevoixint = restevoixint;
//        this.restevoixext = restevoixext;
//        this.restevoixmixte = restevoixmixte;
//        this.restesmsi = restesmsi;
//        this.restesmse = restesmse;
//        this.restesmsm = restesmsm;
//        this.resteqtenet = resteqtenet;
               temp = new ResteForfait(res.getString("idForfait"),res.getDouble(1),res.getDouble(2),res.getDouble(3),res.getInt(4),res.getInt(5),res.getInt(6),res.getDouble(7));
               list.add(temp);
            }
             Object[] obj = list.toArray();
            idf = new ResteForfait[obj.length];
            for(int j=0;j<idf.length;j++){
                idf[j] = (ResteForfait)obj[j];
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
        return idf;
    }
   //    public double getDepenseOp(Connection con,String idF,String typeForfait,String tyOp) throws Exception{
//        double dep =0;
//        PreparedStatement pst = null;
//        try{
//            if(con==null){ con = new Connexion().getConn(); }
//             String sql = "select sum(qte) from ConsommationForfait where idForfait=? and typeService=? and operateur=?";
//             pst = con.prepareStatement(sql);
//            pst.setString(1,idF);
//            pst.setString(2, typeForfait);
//            pst.setString(3,tyOp);
//            ResultSet res = pst.executeQuery();
//            while(res.next()){
//                dep = res.getDouble("sum");
//            }
//        }
//        catch(Exception ex){
//            throw ex;
//        }
//         finally {
//            if (pst != null) {
//                pst.close();
//            }
//        }
//        return dep;
//    }

//    public Forfait[] getForfaits(Connection con,String idCompte,String typeForfait) throws Exception{
//        Forfait[] forf = null;
//          PreparedStatement pst = null;
//        ArrayList list = new ArrayList();
//        try{
//            if(con==null){ con = new Connexion().getConn(); }
//             String sql = "select * from V_detailForfaits where idCompte=? and typeservice=? order by dateAChat asc";
//             pst = con.prepareStatement(sql);
//             pst.setString(1,idCompte);
//             pst.setString(2, typeForfait);
//            ResultSet res = pst.executeQuery();
//            Forfait temp = null;
//            Offre tOff = null;
//            String idf =null;
//            while(res.next()){
//                if(res.getString("idForfait").compareTo(idf)==0){
//                    tOff.setDetails(new DetailOffre(tOff.getIdOffre(),res.getString("typeService"),res.getDouble("qte"),res.getString("unite"),res.getString("siteAcces"),res.getString("operateur")));
//                }
//                else{
//                    tOff = new Offre(res.getString("idOffre"),res.getString("intitule"),res.getInt("validite"),res.getString("uniteTemps"),res.getDouble("cout"));
//                    tOff.setDetails(new DetailOffre(tOff.getIdOffre(),res.getString("typeService"),res.getDouble("qte"),res.getString("unite"),res.getString("siteAcces"),res.getString("operateur")));
//                    temp = new Forfait(res.getString("idForfait"),idCompte,tOff,res.getTimestamp("dateAchat"),res.getTimestamp("dateExp"));
//                }
//                idf = temp.getIdForfait();
//                list.add(temp);
//            }
//            Object[] obj = list.toArray();
//            forf = new Forfait[obj.length];
//            for(int j=0;j<forf.length;j++){
//                forf[j] = (Forfait)obj[j];
//            }
//        }
//        catch(Exception ex){
//            throw ex;
//        }
//         finally {
//            if (pst != null) {
//                pst.close();
//            }
//        }
//        return forf;
//    }
    public Compte[] getInfo(){
        Compte[] cpt = null;
        return cpt;
    }

    public Compte() {
    }
    
}
