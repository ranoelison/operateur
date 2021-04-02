/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */

public class Mvt_comptes {
    String idMvt ;
    String token;
    String idCompte;
    int typeMvt ;
    double montant;
    Date dateMvt;

    public void setIdMvt(String idMvt) {
        this.idMvt = idMvt;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public void setTypeMvt(int typeMvt) {
        this.typeMvt = typeMvt;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public String getIdMvt() {
        return idMvt;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public int getTypeMvt() {
        return typeMvt;
    }

    public double getMontant() {
        return montant;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public Mvt_comptes() {
    }

    public Mvt_comptes(String idMvt, String idCompte, int typeMvt, double montant, Date dateMvt) {
        this.setIdMvt(idMvt);
        this.setIdCompte(idCompte);
        this.setTypeMvt(typeMvt);
        this.setMontant(montant);
        this.setDateMvt(dateMvt);
    }

    public void setToken(String token) throws Exception {
        this.token = token;
        Connection con = null;
        this.idCompte = new Fonction().getIdToken(con, "client", this.token);
        con = new Connexion().getConn();
    }

    public Mvt_comptes(String token, int typeMvt, double montant) throws Exception {
        this.token = token;
        this.typeMvt = typeMvt;
        this.montant = montant;
    }
    
    public void insert_mvt() throws Exception{
         Connection con = null;
        PreparedStatement pst = null;
        try{
            if(typeMvt==-1){
                montant = montant*-1;
            }
            con = new Connexion().getConn();
            con.setAutoCommit(false);
            String idmvt = new Fonction().getNextSeq(con, "mvt");
            String sql = "begin; insert into MvtCompte values(?,?,?,"+montant+",current_timestamp)";
            pst = con.prepareStatement(sql);
             pst.setString(1, idmvt);
            pst.setString(2, idCompte);
            pst.setInt(3, typeMvt);
            int executeUpdate = pst.executeUpdate();
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
}
