package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {



    //to avoid having the variable url, username and password in all of the dao classes,
    // we are creating a method that will get the connection for us and then calling this method
    // in our dao methods
   public static Connection getConnection(){
       Connection conn = null;
       /// System.getenv() is how we retrieve environment variables from our machine
       String url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/grocerylist";
       String username = System.getenv("RDS_USERNAME");
       String password = System.getenv("RDS_PASSWORD");
       try {
           conn = DriverManager.getConnection(url, username, password);
       }catch (SQLException sqle){
           sqle.printStackTrace();
       }

       return conn;
   }

}
