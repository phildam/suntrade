/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

import dbPack.JDBCUtil;
import entities.Account;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import suntradeapp.UtilityExtractor;

/**
 *
 * @author g33k5qu4d
 */
public class TransactionModels {

   // private double balance;
    private double deposit;
    private double withdraw;
    String customerAccID;
    UtilityExtractor utilExtract = new UtilityExtractor();
    public TransactionModels(String AcctNumber) {
        this.customerAccID = AcctNumber;
    }

    public double getBalance() throws SQLException, IOException {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select balance from SUNTRADE.ACCOUNT Where CustomerAccountId=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double balance = 0;
        try {
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerAccID);
            rs = pstmt.executeQuery();
            boolean nullValue = true;
            while (rs.next()) {
                if (rs.getDouble("balance") <= 0) {
                    balance = 0;
                } else {
                    balance = rs.getDouble("balance");
                }
            }
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return balance;
    }
    
     public Double getAccountBalance(String hql) {
        List list = utilExtract.queryTable(hql);
        Double balance = 0.0;
        for (Object o : list) {
            Account a = (Account) o;
            balance = a.getBalance();

        }
        return balance;
    }
     
    public String updateBalance(double balance) throws SQLException, IOException {
        Connection conn = JDBCUtil.getConnection();
        String sql = "update SUNTRADE.ACCOUNT set balance=? where CustomerAccountId= ? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String msg="";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setString(2, customerAccID);
            rs = pstmt.executeQuery();
            if(rs.rowUpdated()){
               msg =new String("Balance update successful");
            }
        } finally {
            JDBCUtil.closeStatement(pstmt);
        }
        return msg;
    }
    
    public void updateDepositTransactionDetails(String amount, String acctype, Date date){
        try {
            Connection conn = JDBCUtil.getConnection();
            
            String customerSql = "Insert into SUNTRADE.DEPOSITOR"
                    + "(CustomerAccountId, amount, DepositType,ModifiedDate )"
                    + "values "
                    + "(?, ?, ?, ?)";
            
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(customerSql);
                
                pstmt.setString(1, customerAccID);
                pstmt.setDouble(2, getDeposit());
                pstmt.setString(3, acctype);
                if(date==null){
                    pstmt.setNull(4, Types.DATE);
                }else{
                    pstmt.setDate(4, date);
                }
               
                pstmt.executeUpdate();
                JDBCUtil.commit(conn);
            } finally {
                if (conn != null) {
                    JDBCUtil.closeStatement(pstmt);
//                JDBCUtil.closeConnection(conn);
                }
            }
            
        }   catch (SQLException ex) {
            Logger.getLogger(TransactionModels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransactionModels.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateWithdrawalTransactionDetails(String customerAccID, Date date){
        try {
            Connection conn = JDBCUtil.getConnection();
            
            String customerSql = "Insert into SUNTRADE.WITHDRAWAL"
                    + "(CustomerAccountId, amount ,ModifiedDate )"
                    + "values "
                    + "(?, ?, ?)";
            
            PreparedStatement pstmt = null;
            try {
                pstmt = conn.prepareStatement(customerSql);
                
                pstmt.setString(1, customerAccID);
                pstmt.setDouble(2, getWithdraw());
                if(date==null){
                    pstmt.setNull(3, Types.DATE);
                }else{
                    pstmt.setDate(3, date);
                }
               
                pstmt.executeUpdate();
                JDBCUtil.commit(conn);
            } finally {
                if (conn != null) {
                    JDBCUtil.closeStatement(pstmt);
//                JDBCUtil.closeConnection(conn);
                }
            }
            
        }   catch (SQLException ex) {
            Logger.getLogger(TransactionModels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransactionModels.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }

}
