/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaction;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author g33k5qu4d
 */
public class Transaction {
    String acct;
    Date date;
    public Transaction(String acct,Date date){
        this.acct=acct;
        this.date=date;
    }
    TransactionModels etrans=new TransactionModels(acct);
    
    
    public String withdraw(double amount) throws SQLException, IOException{
        double balance=etrans.getBalance();
        String msg="";
        if(amount > balance || balance <=0){
            msg="Balance too low for withdrawal";
        }else if(balance >= amount){
            etrans.setWithdraw(balance);
            double newBalance=balance-amount;
            etrans.updateBalance(newBalance);
            msg="Withdrawal transaction sucessfull completed";
        }
        return msg;
    }
    
    public String deposit(double amount, String AcctType) throws SQLException, IOException{
        etrans.setDeposit(amount);
        double balance=etrans.getBalance();
        double newBalance=balance+amount;
        etrans.updateBalance(newBalance);
        etrans.updateWithdrawalTransactionDetails(acct, date);
        return "Deposit transaction sucessfuly completed";
    }
    
    
}
