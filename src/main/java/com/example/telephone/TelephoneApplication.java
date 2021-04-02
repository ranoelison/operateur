package com.example.telephone;

import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import model.Admin;
import model.Appel;
import model.Fonction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utils.Connexion;

@SpringBootApplication
public class TelephoneApplication {
    public static void main(String[] args) throws Exception {
//         String idCompte;
//    String numeroAutre ;
//    String  typeAppel;
//    double  duree;
//    String  operateurAutre;
//    Date  dateAppel;
//        Appel ap = new Appel("COMPTE-3","0351234567","S",20.0,"min");
//        ArrayList<Appel> app = ap.getHistoriques("COMPTE-3");
//        for(int i=0;i<app.size();i++){
//            System.out.print(app.get(i).getMonNumero());
//        }
//        ap.insert_appel();
//    try{
//        Appel[] app = new Appel().getHistoriques("COMPTE-3");
//        for(int i=0;i<app.length;i++){
//            System.out.print(app[i].getDuree());
//        }
//    }
//    catch(Exception e){
//        e.printStackTrace();
//        
//    }
//    Mvt_comptes mv = new Mvt_comptes("COMPTE-3",1,9000);
//    mv.insert_mvt();
//        try{
//            MongoDatabase mn= new Connexion().getConnexionMongodbEnLigne();
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//          String p =   new Fonction().getHash(new Connexion().getConn(),"123456");
//          System.out.print( new Fonction().setHash(new Connexion().getConn(),"ADMIN-1","admin"));
        SpringApplication.run(TelephoneApplication.class, args);
    }
}
