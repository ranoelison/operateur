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
 * @author ASUS
 */
public class Forfait {
    String idForfait ;
    String idCompte;
    Offre offre;
    Date dateAchat;
    Date dateExp ;
    double reste;

    public void setIdForfait(String idForfait) {
        this.idForfait = idForfait;
    }

    public void setIdCompte(String idCompte) {
        this.idCompte = idCompte;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public void setDateExp(Date dateExp) {
        this.dateExp = dateExp;
    }

    public String getIdForfait() {
        return idForfait;
    }

    public String getIdCompte() {
        return idCompte;
    }

    public Offre getOffre() {
        return offre;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public Forfait(String idForfait, String idCompte, Offre offre, Date dateAchat, Date dateExp) {
        this.setIdForfait(idForfait);
        this.setIdCompte(idCompte);
        this.setOffre(offre);
        this.setDateAchat(dateAchat);
        this.setDateExp(dateExp);
    }

    public Forfait() {
    }
    public void updateDateExp(String idForfait,Date d){
        
    }
    public Offre getOffre(Connection con) throws Exception{
         Offre of = null;
        String sql = "select * from Forfait join Offre on Forfait.idOffre = Offre.idOffre where idForfait=?";
        PreparedStatement pst = null;
        ResultSet rs  =  null;
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1,this.idForfait);
            rs = pst.executeQuery();
            while(rs.next()){
                of = new Offre(rs.getString("idOffre"),rs.getString("intitule"),rs.getInt("validite"),rs.getString("uniteTemps"),rs.getDouble("cout"),rs.getDouble("qteVoixInt"),rs.getDouble("qteVoixExt"),rs.getDouble("qteVoixMixte"),rs.getString("unitVI"),rs.getString("unitVE"),rs.getString("unitVM"),rs.getInt("nbSmsI"),rs.getInt("nbSmsE"),rs.getInt("nbSmsM"),rs.getDouble("qteNet"),rs.getString("siteAcces")); 
            }
        }catch(Exception e){
            throw new Exception("find offre error "+e);
        }finally{
            pst.close();
            rs.close();
        }
        return of;
    }
//    public Forfait checkForPriorite(Forfait[] forfaits){
//        double cumule =0;
//        for(int i=0;i<forfaits.length;i++){
//            if(forfaits[i].getOffre().getIdOffre().compareTo(forfaits[i+1].getOffre().getIdOffre())==0){
////                cumule = forfaits[i].getOffre()
//                updateDateExp(forfaits[i].getIdForfait(),forfaits[i+1].getDateExp());
//
//            }
//        }
//        return new Forfait();
//    }
//    public void setReste(Connection con,String typeForfait) throws Exception{
//        double d =0;
//        double reste =0;
//        Offre offre =null;
//        DetailOffre[] details = null;
//        if(typeForfait.compareTo("VOIX")==0){
//            d = getDepense(con,this.getIdForfait(),typeForfait);
//            offre = this.getOffre();
//            details = offre.getDetails();
//             if(details[0].getUnite().compareTo("Ar/s")==0){
//                reste = offre.getCout()-d;
//                details[0].setReste(reste);
//                offre.setDetails(details);
//                this.setOffre(offre);
//             }
//        }
//        else{
//            d = getDepense(con,this.getIdForfait(),typeForfait);
//            offre = this.getOffre();
//           details = offre.getDetails();
//            reste = details[0].getQte()-d;
//            details[0].setReste(reste);
//            offre.setDetails(details);
//            this.setOffre(offre);
//        }
//    }
//    public double getDepense(Connection con,String idF,String typeForfait) throws Exception{
//        double dep =0;
//        PreparedStatement pst = null;
//        try{
//            if(con==null){ con = new Connexion().getConn(); }
//             String sql = "select sum(qte) from ConsommationForfait where idForfait=? and typeService=?";
//             pst = con.prepareStatement(sql);
//            pst.setString(1,idF);
//            pst.setString(2, typeForfait);
//            ResultSet res = pst.executeQuery();
//            while(res.next()){
//                dep = res.getDouble("sum");
//            }
//        }
//        catch(Exception ex){
//            throw ex;
//        }
//         finally {
//            if (pst != null) {
//                pst.close();
//            }
//        }
//        return dep;
//    }
}
