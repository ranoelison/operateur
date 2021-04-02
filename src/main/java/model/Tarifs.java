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
import java.util.logging.Logger;
import utils.Connexion;

/**
 *
 * @author ASUS
 */
public class Tarifs {
    String typeService ;
    double cout;
    int qte_unit ;
    String unite;

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setQte_unit(int qte_unit) {
        this.qte_unit = qte_unit;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getTypeService() {
        return typeService;
    }

    public double getCout() {
        return cout;
    }

    public int getQte_unit() {
        return qte_unit;
    }

    public String getUnite() {
        return unite;
    }

    public Tarifs(String typeService, double cout, int qte_unit, String unite) {
        this.setTypeService(typeService);
        this.setCout(cout);
        this.setQte_unit(qte_unit);
        this.setUnite(unite);
    }

    public Tarifs() {
    }
   
    public Tarifs getTarifsByServiceType(Connection con,String typeService,String operateur) throws Exception{
         PreparedStatement pst = null;
         Tarifs t =null;
        try{
            if(con==null){ con = new Connexion().getConn(); }
            if(operateur!=null){
                String sql = "select * from Tarifs where typeService=? and operateur=?";
                pst = con.prepareStatement(sql);
               pst.setString(1,typeService);
               pst.setString(2, operateur);
            }
            else{
                String sql = "select * from Tarifs where typeService=?";
                pst = con.prepareStatement(sql);
                pst.setString(1,typeService);
            }
            ResultSet res = pst.executeQuery();
            while(res.next()){
                t = new Tarifs(res.getString("typeService"),res.getDouble("cout"),res.getInt("qte_unit"),res.getString("unite"));
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
        return t;
    }
}
