/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.Connexion;
/**
 *
 * @author 1030
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")

public class Depot_controller {
   @PostMapping("/Deposer")
    public Response deposer(@RequestBody V_Depot toins){
        String message = "success";
        String status = "200";
        try {
            toins.save();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
            return new Response(status,message);
        }
        return new Response(status,message);
    }
    @PutMapping(value="/ValidationDepot")
    public Response validation(@RequestBody String id){
        String message = "success";                                                                      
        String status = "200";
        try {
            new V_Depot().valider(id);
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
            return new Response(status,message);
        }
        return new Response(status,message);
    }
    @RequestMapping(path="/DepotAttente",method=RequestMethod.GET)
    public Response getD(){
        String message = "success";
        String status = "200";
         Response resp = null;
        V_Depot v = new V_Depot();
        ArrayList vdata= new ArrayList();
        try {
            vdata = v.listeAttentes();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
            resp = new Response(status,message,null);
            return resp;
        }
        resp =  new Response(status,message);
        resp.setData(vdata);
        return resp;
    }
    
   /* @GetMapping(value="/DepotAttente")
    public Response getEnAttente(){
        String message = "success";
        String status = "200";
        V_Depot v = new V_Depot();
        ArrayList vdata= new ArrayList();
        try{
            vdata = v.getDepotAttente();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
            return new Response(status,message);
        }
         return new Response(status,message,vdata);
    }*/
    
    
}
