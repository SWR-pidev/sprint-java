/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.test;


import com.swr.Entite.Comments;
import com.swr.Entite.Posts;
import com.swr.Service.ServiceComments;
import com.swr.Service.ServicePosts;
import com.swr.Utilis.DataBase;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Soulah
 */
public class test {
     public static void main(String[] args) {
        ServicePosts ser = new ServicePosts();
        ServiceComments cmt= new ServiceComments();
        String x="pppp";
        String l="Am";
        Posts p1;
         p1 = new Posts("wesh", 31, 10, 99, 50, 2);
        Posts p2 = new Posts("aloo", 20,108,10,52,11);
        Posts p3 = new Posts("holo soy dora and am finna kill a colero", 30,8,10,52,1);
        Comments c1 = new Comments(2,"behya behya ",52,1);
        Comments c2 = new Comments(2,"i feel most alive when rapidly approching my death ",1,2);
        Comments c3 = new Comments(2," Am a man of scienceee",1,1);
        try {
            /**** POSTS*****/
         // ser.ajouter1(p3);
        //  ser.ajouter1(p2);
         //  ser.ajouter1(p1);
            //ser.delete(p2);
              //List<Posts> sortedlist= ser.trieln();
          //System.out.println(sortedlist);
            
         /*  List<Posts> searchList= ser.recherche(x);
           if (searchList.isEmpty()){System.out.println("it doesnt exist");}
           else
           {System.out.println(searchList);}*/
          // List<Posts> list = ser.readAll();
           // System.out.println(list);
            
                         // ser.like();
          
           
            /**** Comments*****/
           // cmt.ajouter(c2); 
            //cmt.update(c3);
           // cmt.delete(c2);
            //List<Comments> listc = cmt.readAll();
          //  System.out.println(listc);
            //List<Integer> y=ser.countid();
            //System.out.println(y);
            
            /* List<Comments> searchListC= cmt.recherche(l);
           if (searchListC.isEmpty()){System.out.println("it doesnt exist");}
           else
           {System.out.println(searchListC);}*/
              List<Comments> sortedlistC= cmt.trie();
          System.out.println(sortedlistC);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        //ser.sendmail();
        
    }
    
}
