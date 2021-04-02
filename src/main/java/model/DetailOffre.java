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
public class DetailOffre {
    String idOffre;
    String typeService;
    double qte;
    String unite;
    String siteAcces;
    String operateur;
    double reste;

    public DetailOffre() {
    }

    public void setIdOffre(String idOffre) {
        this.idOffre = idOffre;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public void setSiteAcces(String siteAcces) {
        this.siteAcces = siteAcces;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public String getIdOffre() {
        return idOffre;
    }

    public String getTypeService() {
        return typeService;
    }

    public double getQte() {
        return qte;
    }

    public String getUnite() {
        return unite;
    }

    public String getSiteAcces() {
        return siteAcces;
    }

    public String getOperateur() {
        return operateur;
    }

    public double getReste() {
        return reste;
    }

    public DetailOffre(String idOffre, String typeService, double qte, String unite, String siteAcces, String operateur) {
        this.idOffre = idOffre;
        this.typeService = typeService;
        this.qte = qte;
        this.unite = unite;
        this.siteAcces = siteAcces;
        this.operateur = operateur;
    }
    
    
}
