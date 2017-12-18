/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author g33k5qu4d
 */
public class UpdateandInsert {
    
    
    public static void InsertPerson(PreparedStatement pstmt,String firstname,String lastname
            ,String phone,String email,String dob,String address) throws SQLException{
        pstmt.setString(1, firstname);
        pstmt.setString(2, lastname);
        pstmt.setString(3, phone);
        pstmt.setString(4, email);
        pstmt.setString(5, dob);
        pstmt.setString(6, address);
        
        pstmt.execute();
    }
    public static  PreparedStatement insertStuffs(Connection conn) throws SQLException{
        String sql="insert into person "+
                "(firstname, lastname,phone, email,dob,address) "+
                "values "+
                "(?,?,?,?,?,?)";
                
        PreparedStatement pstmt=conn.prepareStatement(sql);
        return pstmt;        
    }
    public static PreparedStatement updateStuffs(Connection conn) throws SQLException{
        String updateSql="update person "+
                "set firstname=?,set lastname=?,set phone=?,"
                + "set email=?, set dob=?,set address=?, where firstname=?";
        PreparedStatement updateStmt=conn.prepareStatement(updateSql);
        return updateStmt; 
    }
}
