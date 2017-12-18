/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author g33k5qu4d
 */
public class JDBCUtil {
    //jdbc:derby:suntrade;
    //jdbc:derby://localhost:1527/suntrade
    private static String url="jdbc:derby:suntrade";
    private static Properties props=null;
    public static void setConnectionURI(String url){
        url=url;
    }
    public static String getUrl(){
        return url;
    }
    
    public static Connection getConnection() throws SQLException, IOException {
        //org.apache.derby.jdbc.EmbeddedDriver
        String userHomeDir = System.getProperty("user.home", ".");
        String systemDir = userHomeDir + "/.suntradeDb";

    // Set the db system directory.
        System.setProperty("derby.system.home", systemDir);
        
        
        Driver derbyEmbeddedDriver = new org.apache.derby.jdbc.EmbeddedDriver();
        DriverManager.registerDriver(derbyEmbeddedDriver);
        
        String link = getUrl()+";create=true;";
       // System.out.println(link);
        String UserID = "suntrade";
        String PassWord = "suntrade";
        
        //addProperty("url", link);
       // addProperty("userId", link);
       // addProperty("password", link);
      //  addProperty("create", false);
        Connection connection = DriverManager.getConnection(link, UserID, PassWord);
        connection.setAutoCommit(false);

        return connection;
    }
    
    public static void addProperty(String key, Object value) throws IOException{
        InputStream stream=ClassLoader.getSystemResourceAsStream("/Database/database.properties");
        props=new Properties();
        props.put(key, value);
        props.load(stream);
        stream.close();
        
        
    }
    public static Properties getProperties(){
        return props;
    }
    

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  //  public static void main(String[] args) throws IOException {
     //   Connection conn = null;
     //   try {
      //      conn = JDBCUtil.getConnection();
    //        System.out.println("Connetced to the database.");
   //     } catch (SQLException e) {
       //     e.printStackTrace();
  //      } finally {
    //        JDBCUtil.closeConnection(conn);
  //      }
 //   }

}
