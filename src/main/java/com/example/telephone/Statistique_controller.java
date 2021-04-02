/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1030
 * tout pour avoir les statistiques
 */
@RestController
@CrossOrigin
public class Statistique_controller {
    @GetMapping(value="/StatistiqueClients/")
    public Response getstatcli(){
        String message = "success";
        String status = "200";
        V_CliStat stacl = new V_CliStat();
        List<V_CliStat> val = new ArrayList();
        try {  
            val= stacl.find();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
           status = "100";
           return new Response(status,message,null);
        }
       return new Response(status,message,val);
    }
    @GetMapping(value="/StatistiqueOffres/")
    public Response getstatof(){
        String message = "success";
        String status = "200";
        V_OffreStat staof = new V_OffreStat();
        List<V_OffreStat> val = new ArrayList();
        try {  
            val= staof.find();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
           status = "100";
           return new Response(status,message,null);
        }
        return new Response(status,message,val);
    }
    
}
