
package com.swr.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author House
 */

public class DataBase {
     String url = "jdbc:mysql://localhost:3306/swr?useTimezone=true&serverTimezone=UTC";
     String login = "root";
     String pwd = "";
    public  static DataBase db;
    public Connection con;
    private DataBase() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
            
            
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DataBase getInstance()
    {if(db==null)
        db=new DataBase();
    return db;
    }     
     
     
     
     
}
