/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connexion;

/**
 *
 * @author 1030
 * la classe V_Depot est l'objet representant la vue V_Depot et est 
 * la classe contenant les fonctionalites tournant autour du mouvement Depot (typeMvt=1)
 */
public class V_Depot implements Serializable {
   // idmvt | idcompte | typemvt | montant | datemvt | etat
    String idMvt;
    String idCompte;
    String typeMvt;
    Double montant;
    Date dateMvt;
    String etat;

    public V_Depot(String idCompte, Double montant, Date dateMvt) {
        this.idCompte = idCompte;
        this.montant = montant;
        this.dateMvt = dateMvt;
    }

    public V_Depot(String idCompte, Double montant) {
        this.idCompte = idCompte;
        this.montant = montant;
    }
    
     public void save(Connection con)throws Exception{
        String sql = "begin;insert into MvtCompte values(CONCAT('Mvt-',nextval('seq_mvt')),'"+this.idCompte+"','1',"+this.montant+",CURRENT_DATE,'-1');";
        System.out.println(sql);
        PreparedStatement pst = null;
        try{
            pst = con.prepareStatement(sql);
           pst.executeUpdate();
        }catch(Exception e){
            throw new Exception ("erreur insertion offre"+e);
        }finally{
            pst.close();
        }
    }
    public void save() throws Exception{
        Connection con = new Connexion().getConn();
        con.setAutoCommit(false);
        try{
            this.save(con);
            con.commit();
        }catch(Exception e){
            throw e;
        }finally{
            con.close();
        }
    }
    public void valider(String id,Connection con) throws Exception{
        String sql = "update MvtCompte set etat='1' where idMvt='"+id+"';";
        PreparedStatement pst = null;
        try{
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            pst.close();
        }     
    }
    public void valider(String id) throws Exception{
        Connection con = new Connexion().getConn();
        try {
            valider(id,con);
        } catch (Exception ex) {
            Logger.getLogger(V_Depot.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.close();
        }
    }
    
    
    
    public ArrayList<V_Depot> listeAttentes() throws Exception{
         Connection con = new Connexion().getConn();
        String sql  = "select * from V_DepotAttente";
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<V_Depot> arr = new ArrayList<V_Depot>();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          while(rs.next()){
                V_Depot vd = new V_Depot(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),(java.util.Date)rs.getDate(5),rs.getString(6));
                System.out.println(vd.getIdMvt());
                arr.add(vd);
            }
          System.out.println(rs.getRow());
        }catch(Exception e){
            throw new Exception("Erreur getDepotAttente() :: "+e);
        }finally{
            rs.close();
            pst.close();
            con.close();
        }  
        return arr;
    }

  /*  public ArrayList<V_Depot> getDepotAttente() throws Exception{
          ArrayList depot = new ArrayList();
         
          depot = getDepotAttentes(con);
          
          return depot;
    }*/
    /* public V_Depot[] getDepotAttente() throws Exception{
          V_Depot[] depot = null;
          Connection con = new Connexion().getConn();
          depot = this.getDepotAttente(con);
          con.close();
          return depot;
    }
    public V_Depot[] getDepotAttente(Connection con) throws Exception{
        V_Depot[] depot = null;
        String sql  = "select * from V_DepotAttente";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            ArrayList arr = new ArrayList();
            while(rs.next()){
                V_Depot vd = new V_Depot(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),(java.util.Date)rs.getDate(5),rs.getString(6));
                 System.out.println(vd.getIdMvt());
                arr.add(vd);
            }
            Object[] obj = arr.toArray();
            depot = new V_Depot[obj.length];
            for(int i=0;i<depot.length;i++){
                depot[i] = (V_Depot) obj[i];
            }
            
        }catch(Exception e){
            throw new Exception("Erreur getDepotAttente() :: "+e);
        }finally{
            rs.close();
            pst.close();
        }
        
        return depot;
    }*/
    public V_Depot() {
    }

    public V_Depot(String idMvt, String idCompte, String typeMvt, Double montant, Date dateMvt, String etat) {
        this.idMvt = idMvt;
        this.idCompte = idCompte;
        this.typeMvt = typeMvt;
        this.montant = montant;
        this.dateMvt = dateMvt;
        this.etat = etat;
    }

    public String getIdMvt() {
        return idMvt;
    }

    public void setIdMvt(String idMvt) {
        this.idMvt = idMvt;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public String getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(String typeMvt) {
        this.typeMvt = typeMvt;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
