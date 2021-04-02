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
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author ASUS
 */
@RestController
@CrossOrigin
public class Client_controller {
//     @GetMapping("/Client/{nom}/{numero}")
//    public String helloWolrd(@PathVariable("nom") String nom,@PathVariable("numero") int numero){
//        return "hello"+nom+numero;
//    }
//    @PostMapping
//      @RequestMapping(value = "/foo", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value="/Client" , method =RequestMethod.POST, produces="application/json")
//    @ResponseBody
//    public ArrayList kalo(@RequestParam String nom) throws Exception{
//        Connection con = new Connexion().getConn();
//        PreparedStatement pst = null;
//         ArrayList list = new ArrayList();
//        try{
//            String sql = "select * from client";
//            pst = con.prepareStatement(sql);
//            ResultSet res = pst.executeQuery();
//            int i=0;
//            while(res.next()){
//                list.add( res.getString("idclient"));
//            }
//        }
//        catch(Exception ex){
//            throw ex;
//        }
//         finally {
//            if (pst != null) {
//                pst.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//       return list;
//    }
//    @PostMapping("/Client/{nom}/{prenom}/{dateNaissance}/{sexe}/{mdp}")
//    /Client_controller/inscription
    @PostMapping("/client_inscription")
    public Response inscription(@RequestBody Client client) throws Exception{
        String num = "";
        Response t = null;
       try{
           num = client.inscription();
           t = new Response("200","",num);
       }
        catch(Exception ex){
            t = new Response("500",ex.getMessage(),null);
        }
       return t;
    }
}
