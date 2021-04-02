/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telephone;

import java.util.HashMap;
import model.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@CrossOrigin
public class Admin_controller {
    @PostMapping("/Admin_log")
    public Response login(@RequestBody Admin admin) throws Exception  {
        String token = null;
        Response resp = null;
        try{
            token = admin.login();
            if(token==null){
                resp = new Response("500","identification refusee",null);
            }
            else{
               resp = new Response("200","connection reussie",token);
            }
        }
         catch(Exception ex){
            resp = new Response("500",ex.getMessage(),null);
        }
        return resp;
    }
}
