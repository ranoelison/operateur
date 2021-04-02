/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;
import utils.*;
import model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@CrossOrigin
public class Compte_controller {
    @PostMapping("/Compte_log")
    public Response login(@RequestBody Compte cpt) throws Exception  {
        String token = null;
        Response t = null;
        try{
            token = cpt.login();
            t = new  Response("200","connection reussie",token);
        }
         catch(Exception ex){
            t = new Response("500",ex.getMessage(),null);
        }
        return t;
    }
    @PostMapping("/mvt_compte")
    public Response mvt_compte(@RequestBody Mvt_comptes mv) throws Exception  {
         Response t = null;
        try{
            mv.insert_mvt();
            t =  new Response("200","transaction reussie",null);
        }
         catch(Exception ex){
              t = new Response("500",ex.getMessage(),null);
        }
        return t;
    }
    @PostMapping("/achatOffre")
    public Response acheterOffre(@RequestBody Compte cpt){
        Response t = null;
        try{
            cpt.achatOffre();
            t =  new Response("200","transaction reussie",null);
        }
         catch(Exception ex){
              t = new Response("500",ex.getMessage(),null);
        }
        return t;
    }
}
