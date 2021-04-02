/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.*;
/**
 *
 * @author ASUS
 */
public class Internet {
    int poids;
    public void insert_depense(String idCompte) throws Exception{
          Connection con = null;
        PreparedStatement pst = null;
        try{
            con = new Connexion().getConn();
            con.setAutoCommit(false);
            new Consommation().deduireConsommation(con,idCompte,"internet",poids,null);
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
