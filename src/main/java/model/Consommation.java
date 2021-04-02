/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.Connexion;

/**
 *
 * @author ASUS
 */
public class Consommation {
    ResteForfait forfait;
    double qteConsommee;

    public void setForfait(ResteForfait forfait) {
        this.forfait = forfait;
    }

    public void setQteConsommee(double qteConsommee) {
        this.qteConsommee = qteConsommee;
    }

    public ResteForfait getForfait() {
        return forfait;
    }

    public double getQteConsommee() {
        return qteConsommee;
    }
    
//      public void deduireConsommation(Connection con,String idCompte,String typeforf,int poids,String unite,String operateur) throws Exception{
//        try{
//             if(con==null){ con = new Connexion().getConn(); }
//            ResteForfait[] frf = new Compte().getForfaits(con, idCompte,typeforf);
////            for(int i=0;i<frf.length;i++){
//            Consommation c = new Consommation(frf[0],poids);
//            int i =0;
//            while(c.qteConsommee!=0){
//                c.deduireForfait(con, typeforf, unite, operateur);
//            }
//            if(frf==null){
//               deduireCredit(con, idCompte, typeforf, poids, unite,operateur);
//            }
//        }
//        catch(Exception ex){
//            throw ex;
//        }
//         finally {}
//    }
          public void deduireConsommation(Connection con,String idCompte,String typeforf,int poids,String operateur) throws Exception{
        try{
             if(con==null){ con = new Connexion().getConn(); }
            ResteForfait[] frf = new Compte().getForfaits(con, idCompte,typeforf);
            int i =0;
            this.qteConsommee = poids;
            while(this.qteConsommee!=0 ){
                Forfait f = new Forfait();
                f.setIdForfait(frf[i].getIdForfait());
                Offre offre = f.getOffre(con);
                double dep =0;
                double depreste = 0;
                if(typeforf.compareTo("VOIX")==0){
                    if(operateur.compareTo("intraOperateur")==0){
                        if(frf[i].getRestevoixint()==0 && frf[i].getRestevoixmixte()==0){}
                        else{
                            double qteVo = offre.getQteVoixInt();
                            String unitp = offre.getUnitVI();
                            if(unitp.compareToIgnoreCase("min")==0){
                                dep = poids;
                                dep = dep/60;
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixint();
                                    dep = frf[i].getRestevoixint();
                                    if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixint();
                                }
                                double dt = dep*60+depreste*60;
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            if(unitp.compareToIgnoreCase("ar/s")==0){
                                dep = poids*qteVo;
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixint();
                                    dep = frf[i].getRestevoixint();
                                    if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixint();
                                }
                                double dt = (dep/qteVo)+(depreste/qteVo);
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            if(unitp.compareToIgnoreCase("ar")==0){
                                Tarifs t= new Tarifs();
                                t= t.getTarifsByServiceType(con,typeforf,operateur);
                                dep = poids*t.getCout()/t.getQte_unit();
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixint();
                                    dep = frf[i].getRestevoixint();
                                    if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixint() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixint();
                                }
                                double dt = (dep*t.getQte_unit()/t.getCout())+(depreste*t.getQte_unit()/t.getCout());
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            dep = dep*-1;
                            depreste = depreste*-1;
                            SoldeForfait s = new SoldeForfait(frf[i].getIdForfait(),dep,0,depreste,0,0,0,0);
                            deduireForfait(con,s);
                        }
                    }
                    if(operateur.compareTo("extraOperateur")==0){
                        if(frf[i].getRestevoixext()==0){}
                        else{
                            double qteVo = offre.getQteVoixExt();
                            String unitp = offre.getUnitVE();
                            if(unitp.compareToIgnoreCase("min")==0){
                                dep = poids;
                                dep = dep/60;
                                if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixext();
                                    dep = frf[i].getRestevoixext();
                                     if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixext();
                                }
                                double dt = dep*60+depreste*60;
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            if(unitp.compareToIgnoreCase("ar/s")==0){
                                dep = poids*qteVo;
                                if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixext();
                                    dep = frf[i].getRestevoixext();
                                     if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixext();
                                }
                                 double dt = (dep/qteVo)+(depreste/qteVo);
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            if(unitp.compareToIgnoreCase("ar")==0){
                                Tarifs t= new Tarifs();
                                t= t.getTarifsByServiceType(con,typeforf,operateur);
                                dep = poids*t.getCout()/t.getQte_unit();
                                 if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()!=0){
                                    depreste = dep-frf[i].getRestevoixext();
                                    dep = frf[i].getRestevoixext();
                                     if(depreste>frf[i].getRestevoixmixte()){
                                        depreste = frf[i].getRestevoixmixte();
                                    }
                                }
                                if(dep>frf[i].getRestevoixext() && frf[i].getRestevoixmixte()==0){
                                    dep = frf[i].getRestevoixext();
                                }
                                double dt = (dep*t.getQte_unit()/t.getCout())+(depreste*t.getQte_unit()/t.getCout());
                                this.qteConsommee = this.qteConsommee-dt;
                            }
                            dep = dep*-1;
                            depreste = depreste*-1;
                            SoldeForfait s = new SoldeForfait(frf[i].getIdForfait(),0,dep,depreste,0,0,0,0);
                            deduireForfait(con,s);
                        }
                    }
                }
                if(typeforf.compareTo("INTERNET")==0){
//                    String idForfait, double qteVoixInt, double qteVoixExt, double qteVoixMixte, int nbSmsI, int nbSmsE, int nbSmsM, double qteNet
                    if(frf[i].getResteqtenet()==0){}
                    else{
                        dep = poids;
                        if(dep>frf[i].getResteqtenet()){
                            dep = frf[i].getResteqtenet();
                        }
                        this.qteConsommee = this.qteConsommee-dep;
                        dep=dep*-1;
                        SoldeForfait s = new SoldeForfait(frf[i].getIdForfait(),0,0,0,0,0,0,dep);
                        deduireForfait(con,s);
                    }
                }
                if(typeforf.compareTo("SMS")==0){
//                    String idForfait, double qteVoixInt, double qteVoixExt, double qteVoixMixte, int nbSmsI, int nbSmsE, int nbSmsM, double qteNet
                    if(operateur.compareTo("intraOperateur")==0){
                        if(frf[i].getRestesmsi()==0 && frf[i].getRestesmsm()==0){}
                        else{
                            dep = poids;
                            if(dep>frf[i].getRestesmsi() && frf[i].getRestesmsm()!=0){
                                depreste = dep-frf[i].getRestesmsi();
                                dep = frf[i].getRestesmsi();
                                if(depreste>frf[i].getRestesmsm()){
                                    depreste = frf[i].getRestesmsm();
                                }
                            }
                            if(dep>frf[i].getRestesmsi() && frf[i].getRestesmsm()==0){
                                dep = frf[i].getRestesmsi();
                            }
                            double t = dep+depreste;
                            this.qteConsommee = this.qteConsommee-t;
                        }
                        dep=dep*-1;
                        depreste = depreste*-1;
                        SoldeForfait s = new SoldeForfait(frf[i].getIdForfait(),0,0,0,(int)dep,0,(int)depreste,0);
                        deduireForfait(con,s);
                    }
                    if(operateur.compareTo("extraOperateur")==0){
                        if(frf[i].getRestesmse()==0 && frf[i].getRestesmsm()==0){}
                        else{
                            dep = poids;
                            if(dep>frf[i].getRestesmse() && frf[i].getRestesmsm()!=0){
                                depreste = dep-frf[i].getRestesmse();
                                dep = frf[i].getRestesmse();
                                if(depreste>frf[i].getRestesmsm()){
                                    depreste = frf[i].getRestesmsm();
                                }
                            }
                            if(dep>frf[i].getRestesmse() && frf[i].getRestesmsm()==0){
                                dep = frf[i].getRestesmse();
                            }
                            double t = dep+depreste;
                            this.qteConsommee = this.qteConsommee-t;
                        }
                        dep=dep*-1;
                        depreste = depreste*-1;
                        SoldeForfait s = new SoldeForfait(frf[i].getIdForfait(),0,0,0,0,(int)dep,(int)depreste,0);
                        deduireForfait(con,s);
                    }
                }
                i++;
            }
            if(frf==null || i==frf.length){
               deduireCredit(con, idCompte, typeforf, poids,operateur);
            }
        }
        catch(Exception ex){
            throw ex;
        }
         finally {}
    }

    public Consommation(ResteForfait forfait, double qteConsommee) {
        this.forfait = forfait;
        this.qteConsommee = qteConsommee;
    }

    public Consommation() {
    }
    
    public void deduireCredit(Connection con,String idCompte,String typeforf,int poids,String operateur) throws Exception{
        double d=0;
        PreparedStatement pst = null;
        try{
            double credit = new Compte().getCredit(con, idCompte);
            Tarifs t= new Tarifs();
            t= t.getTarifsByServiceType(con,typeforf,operateur);
            d = poids*t.getCout()/t.getQte_unit();
            if(d>credit){
                d = credit;
            }
            d = d*-1;
            String sql ="begin; insert into credit values(?,?,current_timestamp)";
             pst = con.prepareStatement(sql);
            pst.setString(1, idCompte);
           pst.setDouble(2, d);
           int executeUpdate = pst.executeUpdate();
           con.commit();
        }
        catch(Exception e){
            con.rollback();
            throw e;
        }
        finally {
            
        }
    }
    
    public void deduireForfait(Connection con,SoldeForfait sf) throws Exception{
        PreparedStatement pst = null;
        try{
            String sql = "begin; insert into soldeForfait values(?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1,sf.getIdForfait());
            pst.setDouble(2, sf.getQteVoixInt());
            pst.setDouble(3, sf.getQteVoixExt());
            pst.setDouble(4, sf.getQteVoixMixte());
            pst.setDouble(5, sf.getNbSmsI());
            pst.setDouble(6, sf.getNbSmsE());
            pst.setDouble(7, sf.getNbSmsM());
            pst.setDouble(8, sf.getQteNet());
            int executeUpdate = pst.executeUpdate();
            con.commit();
        }
         catch(Exception e){
            con.rollback();
            throw e;
        }
        finally {
             if (pst != null) {
                pst.close();
            }
        }
    }
}
