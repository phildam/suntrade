/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author g33k5qu4d
 */


public class InsertData {
    
    

    public static void InsertCustomer(Connection conn,String customerId,
            String surname, String othernames, String email, String phoneNumber,
            String occupation,String gender,String maritalStat,
            String address, String groupname,String groupnumber,
            String country,String state, String lga,String issuingname,Date dob,
            Date issuingDate
            ) throws SQLException {
        
        String customerSql = "Insert into customer"
                + "(surname, othernames, email,phonenumber, "
                + "occupation,gender, maritalStat,groupname,groupnumber"
                + ",country,state,lga,issuingname,CUSTOMERACCOUNTID,address,dob,issuingDate"
                + " )"
                + "values "
                + "(?, ?, ?, ?,"
                + " ?, ?, ?, ?,"
                + " ?, ?,"
                + " ?, ?, ?, ?,"
                + " ?,?,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(customerSql);
        
        pstmt.setString(1, surname);
        pstmt.setString(2, othernames);
        pstmt.setString(3, email);
       
        pstmt.setString(4, phoneNumber);
        
        
        pstmt.setString(5, occupation);
        pstmt.setString(6, gender);
        
        pstmt.setString(7, maritalStat);
        pstmt.setString(8, groupname);
        pstmt.setString(9, groupnumber);
       
        pstmt.setString(10, country);
        pstmt.setString(11, state);
        pstmt.setString(12, lga);
        pstmt.setString(13, issuingname);
        
        pstmt.setString(14, customerId);
        pstmt.setString(15, address);
        
        if(dob==null){
            pstmt.setNull(16, Types.DATE);
        }else{
            pstmt.setDate(16, dob);
        }
        
        if(issuingDate==null){
            pstmt.setNull(17, Types.DATE);
        }else{
            pstmt.setDate(17, issuingDate);
        }
            pstmt.executeUpdate();
            JDBCUtil.commit(conn);
        } finally {
            if (conn != null) {
                JDBCUtil.closeStatement(pstmt);
//                JDBCUtil.closeConnection(conn);
            }
        }

    }
    
    public static void InsertCustomerAccountDetails(Connection conn,String accountNumber, int accountBalance,Date dateModifield)throws SQLException {
        
        String sql = "Insert into Account"
                + "(AccountNumber ,Balance ,DateModified )"
                + "values "
                + "(?, ?, ?, ,?)";
        
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            
        pstmt.setString(1, accountNumber);
        pstmt.setInt(2, accountBalance);
        
        if(dateModifield==null){
            pstmt.setNull(3, Types.DATE);
        }else{
            pstmt.setDate(3, dateModifield);
        }
     
            pstmt.executeUpdate();
            JDBCUtil.commit(conn);
        } finally {
            if (conn != null) {
                JDBCUtil.closeStatement(pstmt);
                JDBCUtil.closeConnection(conn);
            }
        }
    }    
}
