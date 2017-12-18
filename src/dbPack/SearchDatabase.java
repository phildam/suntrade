/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author g33k5qu4d
 */
public class SearchDatabase {

    private static String firstname;
    private static String lastname;
    private static String email;
    private static Date dob;
    

    
    public static String getFirstname() {
        return firstname;
    }

   
    public static void setFirstname(String aFirstname) {
        firstname = aFirstname;
    }

    
    public static String getLastname() {
        return lastname;
    }

    
    public static void setLastname(String aLastname) {
        lastname = aLastname;
    }

   
    public static String getEmail() {
        return email;
    }

    
    public static void setEmail(String aEmail) {
        email = aEmail;
    }

    
    public static Date getDob() {
        return dob;
    }

   
    public static void setDob(Date aDob) {
        dob = aDob;
    }

    public void searchByName(Connection conn, String firstname, String lastname) throws SQLException {
        String Sql = "select firstname, lastname,email, phone,address,"
                + "dob from person"
                + "where firstname= ? || lastname= ? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            printDetails(rs);
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
    }

    public static void printDetails(ResultSet rs) throws SQLException {
        while (rs.next()) {
            SearchDatabase.setFirstname(rs.getString("firstname"));
            SearchDatabase.setLastname(rs.getString("lastname"));
            SearchDatabase.setEmail(rs.getString("email"));
            SearchDatabase.setDob(rs.getDate("dob"));
            boolean isDobNull = rs.wasNull();
            String formattedDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            if (!isDobNull) {
                formattedDate = sdf.format(getDob());
            }
        }
    }

}
