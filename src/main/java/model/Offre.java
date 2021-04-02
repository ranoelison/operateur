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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connexion;


/**
 *
 * @author 1030
 */
public class Offre implements Serializable{
//idoffre | intitule | validite | unitetemps | cout | qtevoixint | qtevoixext | qtevoixmixte | unitvi | unitve | unitvm | nbsmsi | nbsmse | nbsmsm | qtenet | siteacces
    String idOffre;
    String intitule;
    int validite;
    String uniteTemps;
    Double cout;
    Double qteVoixInt;
    Double qteVoixExt;
    Double qteVoixMixte;
    String unitVI;
    String unitVE;
    String unitVM;
    int nbSmsI;
    int nbSmsE;
    int nbSmsM;
    Double qteNet;
    String siteAcces;

    public Double getQteVoixInt() {
        return qteVoixInt;
    }

    public void setQteVoixInt(Double qteVoixInt) {
        this.qteVoixInt = qteVoixInt;
    }

    public Double getQteVoixExt() {
        return qteVoixExt;
    }

    public void setQteVoixExt(Double qteVoixExt) {
        this.qteVoixExt = qteVoixExt;
    }

    public Double getQteVoixMixte() {
        return qteVoixMixte;
    }

    public void setQteVoixMixte(Double qteVoixMixte) {
        this.qteVoixMixte = qteVoixMixte;
    }

    public String getUnitVI() {
        return unitVI;
    }

    public void setUnitVI(String unitVI) {
        this.unitVI = unitVI;
    }

    public String getUnitVE() {
        return unitVE;
    }

    public void setUnitVE(String unitVE) {
        this.unitVE = unitVE;
    }

    public String getUnitVM() {
        return unitVM;
    }

    public void setUnitVM(String unitVM) {
        this.unitVM = unitVM;
    }

    public int getNbSmsI() {
        return nbSmsI;
    }

    public void setNbSmsI(int nbSmsI) {
        this.nbSmsI = nbSmsI;
    }

    public int getNbSmsE() {
        return nbSmsE;
    }

    public void setNbSmsE(int nbSmsE) {
        this.nbSmsE = nbSmsE;
    }

    public int getNbSmsM() {
        return nbSmsM;
    }

    public void setNbSmsM(int nbSmsM) {
        this.nbSmsM = nbSmsM;
    }

    public Double getQteNet() {
        return qteNet;
    }

    public void setQteNet(Double qteNet) {
        this.qteNet = qteNet;
    }

    public String getSiteAcces() {
        return siteAcces;
    }

    public void setSiteAcces(String siteAcces) {
        this.siteAcces = siteAcces;
    }

    public Offre(String idOffre, String intitule, int validite, String uniteTemps, Double cout, Double qteVoixInt, Double qteVoixExt, Double qteVoixMixte, String unitVI, String unitVE, String unitVM, int nbSmsI, int nbSmsE, int nbSmsM, Double qteNet, String siteAcces) {
        this.idOffre = idOffre;
        this.intitule = intitule;
        this.validite = validite;
        this.uniteTemps = uniteTemps;
        this.cout = cout;
        this.qteVoixInt = qteVoixInt;
        this.qteVoixExt = qteVoixExt;
        this.qteVoixMixte = qteVoixMixte;
        this.unitVI = unitVI;
        this.unitVE = unitVE;
        this.unitVM = unitVM;
        this.nbSmsI = nbSmsI;
        this.nbSmsE = nbSmsE;
        this.nbSmsM = nbSmsM;
        this.qteNet = qteNet;
        this.siteAcces = siteAcces;
    }
    
   /*
    public boolean haveDet(String id) throws Exception{
        boolean b = false;
        Connection con = new Connexion().getConn();
        String sql = "select count(idOffre) from DetailOffre where idOffre='"+id+"'";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        try{
            con = new Connexion().getConn();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.getRow()>0){
                b=true;
            }
        }catch(Exception e){
            throw e;
        }finally{
            pst.close();
            rs.close();
            con.close();
        }
        return b;
    }*/
    public void update(Connection con){
     //   String idOffre, String intitule, int validite, String uniteTemps, Double cout, 
        String sql = "begin;update Offre set intitule='"+this.intitule+"',validite="+this.validite+",uniteTemps='"+this.uniteTemps+"',cout="+this.cout+",qteVoixInt="+this.qteVoixInt+",qteVoixExt="+this.qteVoixExt+",qteVoixMixte="+this.qteVoixMixte+",unitVI='"+this.unitVI+"',uniteVE='"+this.unitVE+"',unitVM='"+this.unitVM+"',nbSmsI="+this.nbSmsI+",nbSmsE="+this.nbSmsE+",nbSmsM="+this.nbSmsM+",qteNet="+this.qteNet+",siteAcces='"+this.siteAcces+"'where idOffre='"+this.idOffre+"';";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Offre.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void update() throws Exception{
        Connection con = new Connexion().getConn();
        try{
            this.update(con);
            con.commit();
        }catch(Exception e){
            throw e;
        }finally{
            con.close();
        } 
        
    }
    public void delete(Connection con) throws Exception{
        String sql = "delete from Offre where idOffre='"+this.idOffre+"';";
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
    public void delete() throws Exception{
        Connection con = new Connexion().getConn();
        try{
            this.delete(con);
        }catch(Exception e){
            throw e;
        }finally{
            con.close();
        } 
    }
    ///Double qteVoixInt, Double qteVoixExt, Double qteVoixMixte, String unitVI, String unitVE, String unitVM, int nbSmsI, int nbSmsE, int nbSmsM, Double qteNet, String siteAcces
    public void save(Connection con)throws Exception{
        String sql = "insert into Offre values(CONCAT('OF-',nextval('seq_offre')),'"+this.intitule+"',"+this.validite+",'"+this.uniteTemps+"',"+this.cout+","+this.qteVoixInt+","+this.qteVoixExt+","+this.qteVoixMixte+",'"+this.unitVI+"','"+this.unitVE+"','"+this.unitVM+"',"+this.nbSmsI+","+this.nbSmsE+","+this.nbSmsM+","+this.qteNet+",'"+this.siteAcces+"');";
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
     public ArrayList listeData() throws Exception{
        Connection con  = null;
        String sql = "select * from Offre";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        ArrayList ar = new ArrayList();
        try{
            con = new Connexion().getConn();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          
            while(rs.next()){
              //  Offre o = new Offre(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDouble(5),rs.getInt(6));
              //String idOffre, String intitule, int validite, String uniteTemps, Double cout, Double qteVoixInt, Double qteVoixExt, Double qteVoixMixte, String unitVI, String unitVE, String unitVM, int nbSmsI, int nbSmsE, int nbSmsM, Double qteNet, String siteAcces
              Offre o = new Offre(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getDouble(15),rs.getString(16));        
                ar.add(o);
            }
        }catch(Exception e){
            throw new Exception("find offre error "+e);
        }finally{
            pst.close();
            rs.close();
            con.close();
        }
        return ar;
    }
      /*public Offre(String idOffre,String intitule, int validite, String uniteTemps, Double cout) throws Exception {
        this.idOffre = idOffre;
        this.intitule = intitule;
        this.validite = validite;
        this.uniteTemps = uniteTemps;
        this.cout = cout;
    }

    
    
    
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setValidite(int validite) {
        this.validite = validite;
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

   

    public void setUniteTemps(String uniteTemps) {
        this.uniteTemps = uniteTemps;
    }

    public void setCout(Double cout) {
        this.cout = cout;
    }

    public Offre(String intitule, int validite) {
        this.intitule = intitule;
        this.validite = validite;
        
    }

    
    public Offre(String intitule, int validite, String uniteTemps, Double cout) {
        this.intitule = intitule;
        this.validite = validite;
        this.uniteTemps = uniteTemps;
        this.cout = cout;
      
    }
   /* public ArrayList getOffreToDetails() throws Exception{
         Connection con  = new Connexion().getConn();
        String sql = "select * from V_OffreToDet";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        ArrayList ar = new ArrayList();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          
            while(rs.next()){
                Offre o = new Offre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
                ar.add(o);
            }
        }catch(Exception e){
            throw new Exception("offre A detailler: "+e);
        }finally{
            pst.close();
            rs.close();
             con.close();
        }
        return ar;
    }*/
    /* public ArrayList getOffreToDetails() throws Exception{
        Connection con  = new Connexion().getConn();
        ArrayList of = getOffreToDetails(con);
        con.close();
        return of;
     }*/

   /* public void setAdetail(String id) throws Exception{
         boolean b = false;
        Connection con = new Connexion().getConn();
        String sql = "select count(idOffre) from DetailOffre where idOffre='"+id+"'";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        try{
            con = new Connexion().getConn();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.getRow()>0){
                b=true;
            }
        }catch(Exception e){
            throw e;
        }finally{
            pst.close();
            rs.close();
            con.close();
        }
       
    }
    */
    /*public ArrayList find(Connection con) throws Exception{
        String sql = "select * from Offre;";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        ArrayList ar = new ArrayList();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
          
            while(rs.next()){
                Offre o = new Offre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
                ar.add(o);
            }
        }catch(Exception e){
            throw new Exception("find offre error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return ar;
    }*/
    
   /* public Offre[] find(Connection con)throws Exception{
        Offre[] of = null;
        String sql = "select * from offre;";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            ArrayList ar = new ArrayList();
            while(rs.next()){
                Offre o = new Offre(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
                ar.add(o);
            }
            Object[] ob = ar.toArray();
            of=new Offre[ob.length];
            for(int i=0;i<ob.length;i++){
                of[i] = (Offre) ob[i];
            }
        }catch(Exception e){
            throw new Exception("find offre error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return of;
    }
    public Offre[] find() throws Exception{
        Connection con  = new Connexion().getConn();
        Offre[] of = find(con);
        con.close();
        return of;
    }*/
     public Offre findById(String id,Connection con)throws Exception{
        Offre of = null;
        String sql = "select * from Offre where idOffre='"+id+"';";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                of = new Offre(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getDouble(15),rs.getString(16)); 
            }
        }catch(Exception e){
            throw new Exception("find offre error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return of;
    }
    public Offre findById(String id) throws Exception{
        Connection con  = new Connexion().getConn();
        Offre of = findById(id,con);
        con.close();
        return of;
    }

    public Offre() {
    }

    public String getIdOffre() {
        return idOffre;
    }
    public String getIntitule() {
        return intitule;
    }

    public int getValidite() {
        return validite;
    }

    public String getUniteTemps() {
        return uniteTemps;
    }

    public Double getCout() {
        return cout;
    }
    
}
