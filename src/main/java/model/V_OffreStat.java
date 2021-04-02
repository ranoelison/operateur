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
import utils.Connexion;

/**
 *
 * @author 1030
 * cette classe est une representation O.O de la vue V_offreStat 
 * elle comporte les métiers utiles pour avoir le(s) statistique(s) sur les offres
 */
public class V_OffreStat {
    //idoffre | idcategorie |    description    | intitule | coutunitaire | nbachat | gain
    String idOffre;
    String idCategorie;
    String description;
    String intitule;
    Double coutUnitaire;
    int nbachat;
    Double gain;

    public V_OffreStat() {
    }
    
    public  ArrayList find(Connection con) throws Exception{
        String sql = "select * from V_OffreStat order by nbAchat DESC;";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        ArrayList ar = new ArrayList();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                V_OffreStat o = new V_OffreStat(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getDouble(7));
                ar.add(o);
            }   
        }catch(Exception e){
            throw new Exception("find V_OffreStat error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return ar;
    }
      public ArrayList find() throws Exception{
        Connection con  = new Connexion().getConn();
        ArrayList of = find(con);
        con.close();
        return of;
    }

    public V_OffreStat(String idOffre, String idCategorie, String description, String intitule, Double coutUnitaire, int nbachat, Double gain) {
        this.idOffre = idOffre;
        this.idCategorie = idCategorie;
        this.description = description;
        this.intitule = intitule;
        this.coutUnitaire = coutUnitaire;
        this.nbachat = nbachat;
        this.gain = gain;
    }
   

    public String getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public int getNbachat() {
        return nbachat;
    }

    public void setNbachat(int nbachat) {
        this.nbachat = nbachat;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }
    
}
