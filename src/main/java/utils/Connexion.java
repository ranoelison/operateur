/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author ASUS
 */
public class Connexion {
    public Connection getConn()throws Exception
    {
        String url = "jdbc:postgresql://ec2-34-198-31-223.compute-1.amazonaws.com/ddl85loc6bhajm?user=ectyngvtxymmzs&password=f6fe3007faf8880f672131be7cf1bb6395818aaef0e2c84fe9c9e7d0926b9543";
        Connection con = DriverManager.getConnection(url);
        return con;
    }
	/*
    public MongoDatabase getConnexionMongodb() throws Exception {
        String url = "mongodb://localhost:27017/?readPreference=primary&ssl=false";
        MongoClient mongo = null;
        MongoDatabase database = null;
        try {
            mongo = MongoClients.create(url);
            database = mongo.getDatabase("bddmongo");
        } catch(Exception ex) {
	    	throw ex;
	    }
        System.out.println("Opened MongoDb database successfully");
	    return database;
    }
	*/
    public MongoDatabase getConnexionMongodbEnLigne() throws Exception {
//        MongoClientURI uri = new MongoClientURI(
//        "mongodb+srv://admin:admin@sitel.xmhb2.mongodb.net/sitel?retryWrites=true&w=majority");
//
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("bddmongo");
//        return database;
        
          String url = "mongodb+srv://admin:admin@sitel.xmhb2.mongodb.net/sitel?retryWrites=true&w=majority";
        MongoClient mongo = null;
        MongoDatabase database = null;
        try {
            mongo = MongoClients.create(url);
            database = mongo.getDatabase("bddmongo");
        } catch(Exception ex) {
	    	throw ex;
	    }
        System.out.println("Opened MongoDb database successfully");
	    return database;
     }
}
