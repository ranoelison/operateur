/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import utils.Connexion;

/**
 *
 * @author 1030
 */
public class V_CliStat {
   String idcompte;
   String idclient;
   String numero;
   String nom;
   String prenom; 
   Date datenaissance;
   String sexe;
   Date dateinscrit;
   Double solde;

    public V_CliStat() {
    }

     public ArrayList find(Connection con) throws Exception{
        
        String sql = "select * from V_CliStat order by solde desc;";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
         ArrayList ar = new ArrayList();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
           
            while(rs.next()){
                V_CliStat o = new V_CliStat(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),(java.util.Date)rs.getDate(6),rs.getString(7),rs.getDate(8),rs.getDouble(9));
                ar.add(o);
            }
           
        }catch(Exception e){
            throw new Exception("find V_CliStat error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return ar;
    }
      public ArrayList find() throws Exception{
        Connection con  = new Connexion().getConn();
        ArrayList arr = find(con);
        con.close();
        return arr;
    }

    public V_CliStat(String idcompte, String idclient, String numero, String nom, String prenom, Date datenaissance, String sexe, Date dateinscrit, Double solde) {
        this.idcompte = idcompte;
        this.idclient = idclient;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.sexe = sexe;
        this.dateinscrit = dateinscrit;
        this.solde = solde;
    }

    public String getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(String idcompte) {
        this.idcompte = idcompte;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateinscrit() {
        return dateinscrit;
    }

    public void setDateinscrit(Date dateinscrit) {
        this.dateinscrit = dateinscrit;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
   
}
