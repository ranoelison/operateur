/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author ASUS
 */
public class Appel {
    String idCompte;
    String token;
    String numeroAutre ;
    String monNumero;
    String  typeAppel;
    double  duree;
    String unite;
    String  operateurAutre;
    Date  dateAppel;

    public Appel() {
    }

    public void setToken(String token) throws Exception {
        this.token = token;
        Connection con = new Connexion().getConn();
        String id = new Fonction().getIdToken(con, "client", token);
        this.setIdCompte(id);
        this.token = token;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public void setNumeroAutre(String numeroAutre) throws Exception {
        if(numeroAutre.length()>10 || numeroAutre.length()<10){
            throw new Exception("le numero n'existe pas");
        }
        this.numeroAutre = numeroAutre;
    }

    public void setTypeAppel(String typeAppel) {
        this.typeAppel = typeAppel;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public void setUnite(String unite) {
        System.out.println("unite");
        this.unite = unite;
    }
    public void setOperateurAutre2(String op){
        this.operateurAutre = op;
    }
    public void setOperateurAutre(String numeroAutre) throws Exception {
         String op=null;
//        MongoDatabase db= new Connexion().getConnexionMongodb();
        MongoDatabase db= new Connexion().getConnexionMongodbEnLigne();
        MongoCollection<Document> coll=db.getCollection("Prefixe");
        String p = null;
        char[] num = numeroAutre.toCharArray();
        char[] pf = new char[3];
        pf[0] = num[0];
        pf[1] = num[1];
        pf[2] = num[2];
        String pref = pf.toString();
        MongoCursor<Document> curs=coll.find().iterator();
        while(curs.hasNext())
        {
            Document d=curs.next();
            p = d.getString("prefixe");
        }
         if(p.compareTo(pref)==0){
            op = "intraOperateur";
        }
        else{
            op = "extraOperateur";
        }
        this.operateurAutre = op;
    }

    public void setMonNumero(Connection con) throws Exception {
        String num = new Compte().getCompteById(con, idCompte);
        this.monNumero = num;
    }
    public void setMonNumero(String num){
        this.monNumero = num;
    }
    public void setDateAppel(Date dateAppel) {
        this.dateAppel = dateAppel;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public String getNumeroAutre() {
        return numeroAutre;
    }

    public String getTypeAppel() {
        return typeAppel;
    }

    public double getDuree() {
        return duree;
    }

    public String getOperateurAutre() {
        return operateurAutre;
    }

    public Date getDateAppel() {
        return dateAppel;
    }

    public String getUnite() {
        return unite;
    }

    public String getMonNumero() {
        return monNumero;
    }

    public Appel(String idCompte, String numeroAutre, String typeAppel, double duree,String unite) throws Exception {
         Connection con = null;
        con = new Connexion().getConn();
        this.setIdCompte(idCompte);
        this.setNumeroAutre(numeroAutre);
        this.setOperateurAutre(numeroAutre);
        this.setMonNumero(con);
        this.setTypeAppel(typeAppel);
//        this.setDateAppel(dateAppel);
        this.setDuree(duree);
        this.setUnite(unite);
//        this.setOperateurAutre(numeroAutre);
    }
    public Appel(String idCompte, String monNum,String numeroAutre, String typeAppel, double duree,String operateurAutre,  Date dateAppel) throws Exception {
        this.setIdCompte(idCompte);
        this.setNumeroAutre(numeroAutre);
        this.setMonNumero(monNum);
        this.setTypeAppel(typeAppel);
        this.setDateAppel(dateAppel);
        this.setDuree(duree);
        this.setOperateurAutre(operateurAutre);
//        this.setUnite(unite);
//        this.setOperateurAutre(numeroAutre);
    }
    //MONGO 
    public void insert_appel()throws Exception
    {
        try{
            MongoDatabase db= new Connexion().getConnexionMongodbEnLigne();
            MongoCollection<Document> coll=db.getCollection("Appel");
            Document d=new Document();
            d.put("idCompte",idCompte);
            d.put("monNumero", monNumero);
            d.put("numeroAutre", numeroAutre);
            d.put("typeAppel", typeAppel);
            d.put("duree",duree);
            d.put("operateurAutre",operateurAutre);
            d.put("dateAppel",new Timestamp(System.currentTimeMillis()));
            coll.insertOne(d);
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }
     public ArrayList<Appel> getHistoriques(String token)throws Exception
    {
        Connection con = new Connexion().getConn();
        String idCompte = new Fonction().getIdToken(con, "client", token);
        MongoDatabase db= new Connexion().getConnexionMongodbEnLigne();
        MongoCollection<Document> coll=db.getCollection("Appel");
        Document filter = new Document("idCompte",idCompte);
        FindIterable<Document> iterDoc = coll.find(filter);
        ArrayList<Appel> appels=new ArrayList<Appel>();
        MongoCursor<Document> curs=coll.find().iterator();
        while(curs.hasNext())
        {
            Document d=curs.next();
            Appel temp=new Appel(d.getString("idCompte"),d.getString("monNumero"),d.getString("numeroAutre"),d.getString("typeAppel"),d.getDouble("duree"),d.getString("operateurAutre"),d.getDate("dateAppel"));
            appels.add(temp);
        }
        return appels;
    }
      public void insert_depense( Connection con ) throws Exception{
        try{
            if(con==null){ con = new Connexion().getConn(); }
            con.setAutoCommit(false);
            int d = new Fonction().convertTosecond(duree, unite);
            new Consommation().deduireConsommation(con,idCompte,"VOIX",d,this.operateurAutre);
            con.commit();
        }
        catch(Exception ex){
            con.rollback();
            throw ex;
        }
         finally {
        }
    }
}
