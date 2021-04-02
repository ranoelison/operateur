/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class Admin {
    String idAdmin ;
    String pseudo;
    String motDePasse;

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    
    public Admin() {
    }

    public Admin(String pseudo, String motDePasse) {
        this.setPseudo(pseudo);
        this.setMotDePasse(motDePasse);
    }
    
     public String login() throws Exception{
        Connection con = null;
        PreparedStatement pst = null;
         String token = null;
        try{
            Fonction f = new Fonction();
            con = new Connexion().getConn();
            System.out.print("YOU CALLED THIS FUNCTION"+pseudo+"pwd"+motDePasse);
             String sql = "select * from Administrateur where pseudo=? and motDePasse=?";
             pst = con.prepareStatement(sql);
             pst.setString(1,pseudo);
             motDePasse = f.getHash(con,motDePasse);
              System.out.print("pwd"+motDePasse);
             pst.setString(2, motDePasse);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                token = f.setHash(con,res.getString("idAdmin"),"admin");
            }
             System.out.print("token"+token);
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
}
