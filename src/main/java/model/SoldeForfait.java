/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class SoldeForfait {
    String  idForfait;
    double qteVoixInt;
    double qteVoixExt ;
    double qteVoixMixte ;
    int nbSmsI;
    int nbSmsE ;
     int nbSmsM;
    double qteNet;

    public void setIdForfait(String idForfait) {
        this.idForfait = idForfait;
    }

    public void setQteVoixInt(double qteVoixInt) {
        this.qteVoixInt = qteVoixInt;
    }

    public void setQteVoixExt(double qteVoixExt) {
        this.qteVoixExt = qteVoixExt;
    }

    public void setQteVoixMixte(double qteVoixMixte) {
        this.qteVoixMixte = qteVoixMixte;
    }

    public void setNbSmsI(int nbSmsI) {
        this.nbSmsI = nbSmsI;
    }

    public void setNbSmsE(int nbSmsE) {
        this.nbSmsE = nbSmsE;
    }

    public void setNbSmsM(int nbSmsM) {
        this.nbSmsM = nbSmsM;
    }

    public void setQteNet(double qteNet) {
        this.qteNet = qteNet;
    }

    public SoldeForfait(String idForfait, double qteVoixInt, double qteVoixExt, double qteVoixMixte, int nbSmsI, int nbSmsE, int nbSmsM, double qteNet) {
        this.idForfait = idForfait;
        this.qteVoixInt = qteVoixInt;
        this.qteVoixExt = qteVoixExt;
        this.qteVoixMixte = qteVoixMixte;
        this.nbSmsI = nbSmsI;
        this.nbSmsE = nbSmsE;
        this.nbSmsM = nbSmsM;
        this.qteNet = qteNet;
    }

    public String getIdForfait() {
        return idForfait;
    }

    public double getQteVoixInt() {
        return qteVoixInt;
    }

    public double getQteVoixExt() {
        return qteVoixExt;
    }

    public double getQteVoixMixte() {
        return qteVoixMixte;
    }

    public int getNbSmsI() {
        return nbSmsI;
    }

    public int getNbSmsE() {
        return nbSmsE;
    }

    public int getNbSmsM() {
        return nbSmsM;
    }

    public double getQteNet() {
        return qteNet;
    }
    
    
}
