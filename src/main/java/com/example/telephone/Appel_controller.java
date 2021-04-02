/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import model.Appel;
import model.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import utils.Connexion;

/**
 *
 * @author ASUS
 */
@RestController
@CrossOrigin
public class Appel_controller {
     @PostMapping("/simul_appel")
    public Response simul_appel(@RequestBody Appel appel) throws Exception{
//        String num = "";
       Response t = null;
       Connection con = null;
       try{
            con = new Connexion().getConn();
            con.setAutoCommit(false);
            appel.insert_appel();
            appel.insert_depense(con);
            t = new Response("200","appel insere",null);
            con.commit();
       }
        catch(Exception ex){
            t = new Response("500",ex.getMessage(),null);
        }
       finally{  
        if (con != null) {
             con.close();
         }
       }
        return t;
    }
    @GetMapping("/historiques")
    public Response historiques(@PathVariable String token) throws Exception{
         ArrayList list = new ArrayList();
         Response t = null;
        try{
            list = new Appel().getHistoriques(token);
            t =  new Response("200","",list);
        }
        catch(Exception ex){
            t = new Response("500",ex.getMessage(),null);
        }
        return t;
    }
}
