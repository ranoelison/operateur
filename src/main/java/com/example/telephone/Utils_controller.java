/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;

import java.util.ArrayList;
import model.Categorie;
import model.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utils.Tools;

/**
 *
 * @author 1030
 */
@RestController
@CrossOrigin
public class Utils_controller {
    @RequestMapping(path="/services",method=RequestMethod.GET)
    public Response lesTypesServices(){
        ArrayList ar = new ArrayList();
         String message = "success";
       String status ="200";
       Response resp = null;
        try {
            String[] s = new Tools().lesServices();
            for(int i=0;i<s.length;i++){
                ar.add(s[i]);
            }
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           resp = new Response(status,message);
           return resp;
        }     
        resp =  new Response(status,message);
        resp.setData(ar);
        return resp;
    }
    @RequestMapping(path="/unitTemps",method=RequestMethod.GET)
    public Response lesUnitesTemps(){
        ArrayList ar = new ArrayList();
         String message = "success";
       String status ="200";
       Response resp = null;
        try {
            String[] s = new Tools().unitesTemps();
            for(int i=0;i<s.length;i++){
                ar.add(s[i]);
            }
        } catch (Exception ex) {
            message = "error "+ex.getMessage();
            status = "100";
           resp = new Response(status,message);
           return resp;
        }     
        resp =  new Response(status,message);
        resp.setData(ar);
        return resp;
    }
    
}
