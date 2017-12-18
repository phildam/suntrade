/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author g33k5qu4d
 */
public class selectFromDb {
    
    public static ObservableList<String> getGroupsName() throws SQLException, IOException{
        ObservableList<String> olist=FXCollections.observableArrayList();
        Connection conn=JDBCUtil.getConnection();
        String sql="select groupname from SUNTRADE.GROUPS";
        Statement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conn.createStatement();
            rs=pstmt.executeQuery(sql);
            boolean nullValue=true;
            while (rs.next()) {
                if(rs.getString(1).equals("")){
                    System.out.println("");
                }else{
                    olist.add(rs.getString(1));
                }
                
             }
        }finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return olist;
    }
   // public static void main(String args[]) throws SQLException, IOException{
    //    ObservableList<String> s=getGroupsName();
        
       //     System.out.print(s);
        
  //  }
    
}

