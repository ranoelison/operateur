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
public class ResteForfait {
    //    idforfait | restevoixint | restevoixext | restevoixmixte | restesmsi | restesmse | restesmsm | resteqtenet
     String idForfait ;
     double restevoixint;
     double restevoixext;
     double restevoixmixte;
     int restesmsi;
     int restesmse;
     int restesmsm;
     double resteqtenet;

    public void setIdForfait(String idForfait) {
        this.idForfait = idForfait;
    }

    public void setRestevoixint(double restevoixint) {
        this.restevoixint = restevoixint;
    }

    public void setRestevoixext(double restevoixext) {
        this.restevoixext = restevoixext;
    }

    public void setRestevoixmixte(double restevoixmixte) {
        this.restevoixmixte = restevoixmixte;
    }

    public void setRestesmsi(int restesmsi) {
        this.restesmsi = restesmsi;
    }

    public void setRestesmse(int restesmse) {
        this.restesmse = restesmse;
    }

    public void setRestesmsm(int restesmsm) {
        this.restesmsm = restesmsm;
    }

    public void setResteqtenet(double resteqtenet) {
        this.resteqtenet = resteqtenet;
    }

    public String getIdForfait() {
        return idForfait;
    }

    public double getRestevoixint() {
        return restevoixint;
    }

    public double getRestevoixext() {
        return restevoixext;
    }

    public double getRestevoixmixte() {
        return restevoixmixte;
    }

    public int getRestesmsi() {
        return restesmsi;
    }

    public int getRestesmse() {
        return restesmse;
    }

    public int getRestesmsm() {
        return restesmsm;
    }

    public double getResteqtenet() {
        return resteqtenet;
    }

    public ResteForfait(String idForfait, double restevoixint, double restevoixext, double restevoixmixte, int restesmsi, int restesmse, int restesmsm, double resteqtenet) {
        this.idForfait = idForfait;
        this.restevoixint = restevoixint;
        this.restevoixext = restevoixext;
        this.restevoixmixte = restevoixmixte;
        this.restesmsi = restesmsi;
        this.restesmse = restesmse;
        this.restesmsm = restesmsm;
        this.resteqtenet = resteqtenet;
    }
     
     
}
