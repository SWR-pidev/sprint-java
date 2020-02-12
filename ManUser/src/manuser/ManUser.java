/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manuser;
import Entite.User;
import Service.ServiceUser;
import Entite.Categorie;
import Service.ServiceNews;
import Entite.News;
import Service.ServiceCategorie;
import Utils.DataBase;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Asus X550V
 */
public class ManUser {

    public static void main(String[] args) {
        ServiceUser ser = new ServiceUser();
        ServiceCategorie cat = new ServiceCategorie();
        ServiceNews news = new ServiceNews();
        
        User u1 = new User("sana", "ben fadhel","tunisie","adjad@dajd.com","dahdua",12522,"dhadiah","admin","2019-08-08");
        User u2 = new User("Karray", "Gargouri","egypte","ahduad@adaida.com","dagdya",154151,"dhadha","user","2008-07-12");
        User u3 = new User("Kaady", "Gadadaouri","egypte","ahduadddaida.com","dadagdya",14151,"ddakdha","user","2020-02-12");

        Categorie c1 = new Categorie("Sociale");
        Categorie c2 = new Categorie("Business");
        
        News n1 = new News("Hello", "hiiiiiiii","2020-08-19","Business");
        News n2 = new News("Hellooo", "hiiiiiidadoaii","2020-02-12","Business");
        News n3 = new News("Hellooadadjao", "hidaidaiiiiidadoaii","2020-02-12","social");
        
        try {
//         
            
            //ser.ajouter(u3);
            //ser.delete("adia");
            //cat.ajouter(c2);
           //news.ajouter(n3);
           // news.update("Hello", "kakakakk", "haahhahaaha","social");
           // cat.update("Sociale", "social");
           
           List<News> listn = news.readAll();
            System.out.println(listn);
            
            System.out.println("****************** LES NOUVEAUTES ********************");
            
            List<News> listo = news.nouveaute();
            System.out.println(listo);
            
            System.out.println("****************** STATISTIQUES ********************");
            
            float a = news.stats("social");
            System.out.println(a+"%");
           
           System.out.println("**************************************");
            
            List<Categorie> listc = cat.readAll();
            System.out.println(listc);
            
            System.out.println("**************************************");
            
            ser.update("adia", "adnaida/djad.tn", "bdbajamine");
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
            System.out.println(listd);
            
            System.out.println("********** LES NOUVEAUX MEMBRES *********");
            int i = ser.moyenne();
            System.out.println(i);
        
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
}
