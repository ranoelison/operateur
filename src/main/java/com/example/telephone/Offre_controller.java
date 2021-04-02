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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author 1030
 */
@RestController
@CrossOrigin
public class Offre_controller {
    
   /* @GetMapping(value="/OffresSansDetails")
    public  Response OffresSansDetails(){
        String message = "success";
        String status = "200";
        List<Offre> listO = new ArrayList();
        try {
            listO = new Offre().getOffreToDetails();
        } catch (Exception ex) {
           message = "error"+ex.getMessage();
           status = "100";
           return new Response(status,message);
        }
        return  new Response(status,message,listO);
    }*/
    
    @PostMapping("/CreationOffre")
    public Response insert(@RequestBody Offre offre){
        String message = "créer avec succes";
        String status = "200";
        try {
            offre.save();
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
        }
        return new Response(status,message);
    }
    @PutMapping("/EditOffre")
    public Response edit(@RequestBody Offre toed){
        String message = " modifié avec succes";
        String status = "200";
         try {
            toed.update();  
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
        }
        return new Response(status,message);
    }
    @DeleteMapping("/SupprOffre/{idOffre}")
    public Response delete(@PathVariable("idOffre") String id){
        String message = "supprimé avec succes";
        String status = "200";
        try {
            Offre todel = new Offre();
            todel = todel.findById(id);
            todel.delete();
            
        } catch (Exception ex) {
            message = "error"+ex.getMessage();
            status = "100";
        }
       return new Response(status,message);
    }
     /*@GetMapping("/categorie/{idCategorie}")
    public Response categId(@PathVariable("idCategorie") String id){
        String message = "success";
       String status ="200";
       Categorie ca = new Categorie();
        try {
           ca = ca.categById(id);    
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           Response resp = new Response(status,message,null);
           return resp;
        }
       return new Response(status,message,ca);
    }*/
    @GetMapping("/Offre/{idOffre}")
    public Response get(@PathVariable("idOffre") String idOffre){
       Offre of=null;
        String message = "success";
       String status ="200";
        try {
            of = new Offre().findById(idOffre);
            
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           Response resp = new Response(status,message,of);
           return resp;
        }
       return new Response(status,message,of);
    }
    @RequestMapping(path="/nosOffres",method=RequestMethod.GET)
    public Response offreListe(){
       ArrayList listO = new ArrayList();
       String message = "success";
       String status ="200";
       Response resp = null;
        try {
            listO = new Offre().listeData();
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           resp = new Response(status,message);
           return resp;
        }     
        resp =  new Response(status,message);
        resp.setData(listO);
        return resp;
    } 
  /*  @RequestMapping(path="/categories",method=RequestMethod.GET)
    public Response nosCategories(){
        ArrayList arCat = new ArrayList();
         String message = "success";
       String status ="200";
       Response resp = null;
        try {
            arCat = new Categorie().lesCategories();
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           resp = new Response(status,message);
           return resp;
        }     
        resp =  new Response(status,message);
        resp.setData(arCat);
        return resp;
    }*/
}
