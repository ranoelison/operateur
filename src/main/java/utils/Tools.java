/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author PC
 */
public class Tools {

    public Tools() {
    }
     public String[] lesServices(){
        String[] service = new String[3];
        service[0] = "VOIX";
        service[1] = "SMS";
        service[2] = "INTERNET";
        return service;
    }
    public String[] unitesTemps(){
        String[] unitt = new String[2];
        unitt[0] = "J";
      //  unitt[1] = "SEM";
        unitt[1] = "MOIS";
        return unitt;
    }
    public String[] lesOperateurs(){
        String[] op = new String[5];
        op[0] = "SITEL";
        op[1] = "NATIONAL";
        op[3] = "INTERNATIONAL";
        op[4] = "TOUS";
        return op;
    }
    public String[] lesSites(){
        String[] site = new String[2];
        site[0]="TOUS";
        site[1]="FACEBOOK";
        return site;
    }
}
