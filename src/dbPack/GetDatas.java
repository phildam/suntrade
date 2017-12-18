/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author g33k5qu4d
 */
public class GetDatas {
     private static final SimpleDateFormat sdf=new SimpleDateFormat("EEEE, dd MMMM");
       
   public static void WithDrawalDetailsExtractor(Connection conn) throws SQLException {
        String sql = "Select * from suntrade.depositor ";
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                Double amount=rs.getDouble("amount");
             //   System.out.println(amount);
                String deposittype=printNonNullString(rs.getString("deposittype"));
               // System.out.println(deposittype);
                Date date=rs.getDate("modifieddate");
               // System.out.println(date);
                String modifiedDate=dateFormatter(date);
                
                Double balance=rs.getDouble("balance");
                
                
                
               // AccountTransaction m=new AccountTransaction(amount, deposittype, date, balance);
                System.out.println("Am printing " +amount.toString());
              //  TableGenerator.addToTableItems(m);
            }
            JDBCUtil.commit(conn);
        } finally {
            if (conn != null) {
                JDBCUtil.closeStatement(stmt);
//                JDBCUtil.closeConnection(conn);
            }
        }

    }
   private static String dateFormatter(Date dt){
       if(dt == null){
           return " ";
       }
       String formattedDate=sdf.format(dt);
       return formattedDate;
   }
   private static String printNonNullString(String str){
       if(str == null){
           return " ";
           
       }
       return str;
   }
   
}
