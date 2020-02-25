/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.manuser;
import com.swr.Entite.User;
import com.swr.Service.ServiceUser;
import com.swr.Entite.Categorie;
import com.swr.Service.ServiceNews;
import com.swr.Entite.News;
import com.swr.Service.ServiceCategorie;
import com.swr.Utils.DataBase;
import static java.lang.Double.max;
import static java.lang.Double.min;
import static java.lang.Integer.min;
import static java.lang.Math.min;
import static java.lang.Math.random;
import java.security.SecureRandom;

import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.security.krb5.Confounder.bytes;
/**
 *
 * @author Asus X550V
 */
public class ManUser {

    public static void main(String[] args) {
        ServiceUser ser = new ServiceUser();
        ServiceCategorie cat = new ServiceCategorie();
        ServiceNews news = new ServiceNews();
        
        User u1 = new User("sana", "ben fadhel","tunisie","adjad@dajd.com","dahdua","12522","dhadiah","admin","2019-08-08");
        User u2 = new User("Karray", "Gargouri","egypte","ahduad@adaida.com","dagdya","154151","dhadha","user","2008-07-12");
        User u3 = new User("Kaady", "Gadadaouri","egypte","ahduadddaida.com","dadagdya","14151","ddakdha","user","2020-02-12");
       // User u4 = new User("no", "Gadadri","Algerie","8151","151181");

        Categorie c1 = new Categorie("Sociale");
        Categorie c2 = new Categorie("Business");
        
        News n1 = new News("Hello", "hiiiiiiii","2020-08-19","Business");
        News n2 = new News("Hellooo", "hiiiiiidadoaii","2020-02-12","Business");
        News n3 = new News("Hellooadadjao", "hidaidaiiiiidadoaii","2020-02-12","social");
        
        try {
//         
            
           //ser.ajouter(u4);
            //ser.delete("sana");
            //cat.ajouter(c2);
           //news.ajouter(n3);
           // news.update("Hello", "kakakakk", "haahhahaaha","social");
           // cat.update("Sociale", "social");
           System.out.println(ser.auth("adda.com", "dddya"));
           List<News> listn = news.readAll();
            System.out.println(listn);
            
            System.out.println("****************** LES NOUVEAUTES ********************");
            Random rand = new Random();
            int nombreAleatoire = rand.nextInt(200000 - 10 + 1) + 200000;
            System.out.println(nombreAleatoire);
            List<News> listo = news.nouveaute();
            System.out.println(listo);
            
            System.out.println("****************** STATISTIQUES ********************");
            
            float a = news.stats("social");
            System.out.println(a+"%");
           
           System.out.println("**************************************");
            
            List<Categorie> listc = cat.readAll();
            System.out.println(listc);
            
            System.out.println("**************************************");
            
           // ser.modify(u4,"10");
            List<User> list = ser.readAll();
            System.out.println(list);
            
            System.out.println("********** RECHERCHE AVANCEE *********");
            
            List<User> liste = ser.rechercheavance("a");
            System.out.println(liste);
            
            System.out.println("********** TRI PAR PAYS *********");
            
            List<User> listt = ser.tri();
            System.out.println(listt);
            
            System.out.println("********** RECHERCHE ENTRE DEUX DATES *********");
            
            List<User> listd = ser.rechercheentredate("2019-08-07", "2019-08-09");
            System.out.println(ser.forgotpasss("adda.com"));
            
            System.out.println("********** LES NOUVEAUX MEMBRES *********");
           
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
}
