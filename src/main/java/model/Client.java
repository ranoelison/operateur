/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Connexion;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author ASUS
 */

public class Client{
    private String idClient;
    private String nom;
    private String prenom ;
    private String dateNaissance ;
    private String sexe;
    private String mdp;
    
    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Client() {
    }

    public String getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }
    
    public Client(String nom, String prenom, String dateNaissance, String sexe,String mdp) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setSexe(sexe);
        this.setMdp(mdp);
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String inscription() throws SQLException, Exception{
        Connection con = null;
        PreparedStatement pst = null;
        String numero = null;
        String idClient = "";
        String sql =null;
        try{
            con = new Connexion().getConn();
            con.setAutoCommit(false);
            idClient = new Fonction().getNextSeq(con,"client");
           sql = "begin; insert into client values(?,?,?,'"+dateNaissance+"',?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, idClient);
            pst.setString(2, (String)(nom));
            pst.setString(3, prenom);
            pst.setString(4, sexe);
            int executeUpdate = pst.executeUpdate();
            numero = new Compte().createAccount(con, mdp, idClient);
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
        return numero;
    }
}
