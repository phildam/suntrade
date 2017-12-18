/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

/**
 *
 * @author g33k5qu4d
 */
import dbPack.JDBCUtil;
import static dbPack.JDBCUtil.getUrl;
import java.sql.Date;
import entities.Account;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import entities.Customer;
import entities.Groups;
import entities.Loan;
import entities.Loanrepayment;
import entities.Transact;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilityExtractor {

    private static final String QUERY_BASED_ON_ACOUNTNUMBER = "from Account a where a.accountnumber like '";
    private static final String QUERY_BASED_ON_BALANCE = "from Account a where a.balance like '";

    public void addAccount(String accNumber, Double balance, Date modDate, Boolean active) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Account account = new Account(accNumber, balance, modDate,active);
            session.save(account);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    public void addLoanTransaction(String loanId, Date date, Double amount) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Loanrepayment lp = new Loanrepayment(loanId, date, amount);
            session.save(lp);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    public void addTransaction(String accNumber, String transactionType, Double amount, Double previousBalance, Date modDate) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Transact transaction = new Transact(accNumber, modDate, transactionType, amount, previousBalance);
            session.save(transaction);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

    }

    public void createAccount(String surname, String otherNames, String gender, String maritalStatus, String email, String occupation, String groupName,
            String phoneNumber, String address, String country, String state, String lga, java.util.Date dob, java.util.Date isuingDate, String pictureurl, String issuingName, String accnumber,
            Date modifiedDate, Double openingBalance, Boolean active) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Account account = new Account(accnumber, openingBalance, modifiedDate, active);
            session.save(account);
            tx.commit();
            tx = session.beginTransaction();
            Customer customer = new Customer(surname, otherNames, gender,
                    maritalStatus, email, occupation, groupName, phoneNumber, address, country,
                    state, lga, dob, isuingDate, pictureurl, issuingName, account);
            // customer.setAccountNumber(account);
            session.save(customer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public void createGroup(String GroupName) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Groups group=new Groups(GroupName);
            session.save(group);
            tx.commit();
            } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List queryTable(String hql) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery(hql);
            //  q.setMaxResults(1);
            list = q.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return list;
    }

    public List queryTableWithMax(String hql) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();

            Query q = session.createQuery(hql);
            q.setMaxResults(1);
            list = q.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return list;
    }

    public void executeHQLQuery(String hql) {
        try {
            SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
            Session session = sessionfactory.openSession();
            session.beginTransaction();
            Query q = session.createQuery(hql);
            List resultList = q.list();
            for (Object s : resultList) {
                Account acc = (Account) s;
                System.out.println(acc.getAccountNumber());
                System.out.println(acc.getBalance());
                System.out.println(acc.getDateModified());
            }
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void updateLoanAccountBalance(String customerLoanNumber, Double loanAccountBalance, String status) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();

        try {
            session.beginTransaction();
            Loan loan = (Loan) session.get(Loan.class, customerLoanNumber);
            loan.setCurrentLoanBalance(loanAccountBalance);
            loan.setLoanCompleteStatus(status);
            session.update(loan);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void updateGroup(String groupName, String NewgroupName) throws SQLException, IOException {
        Driver derbyEmbeddedDriver = new org.apache.derby.jdbc.EmbeddedDriver();
        DriverManager.registerDriver(derbyEmbeddedDriver);
        
        String link =JDBCUtil.getUrl()+";create=true;";
        String UserID = "suntrade";
        String PassWord = "suntrade";
        
        Connection connection = DriverManager.getConnection(link, UserID, PassWord);
        connection.setAutoCommit(false);
         String updateSql="update groups set GROUP_NAME=? where GROUP_NAME=?";
         
        PreparedStatement pstmt=null;
        try {
            pstmt = connection.prepareStatement(updateSql);
        
        pstmt.setString(1, NewgroupName);
        pstmt.setString(2, groupName);
         pstmt.executeUpdate();
         
            JDBCUtil.commit(connection);
        } finally {
            if (connection != null) {
                JDBCUtil.closeStatement(pstmt);
//                JDBCUtil.closeConnection(conn);
            }
        }
   
    }

    public void updateAccountBalance(String accountNumber, double balance, Date date) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();

        try {
            session.beginTransaction();
            Account account = (Account) session.get(Account.class, accountNumber);
            account.setBalance(balance);
            account.setDateModified(date);
            session.update(account);
            
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    public void updateAccountActiveStatus(String accNo, Boolean status){
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();

        try {
            session.beginTransaction();
            Account account = (Account) session.get(Account.class, accNo);
            account.setActive(status);
            session.update(account);
            
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public void deleteAccount(String accountNumber) {
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            session.beginTransaction();
            Account account = (Account) session.get(Account.class, accountNumber);
            session.delete(account);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    public void deleteGroup(String groupName){
        SessionFactory sessionfactory = HibernateUtil.getSessionFactory();
        Session session = sessionfactory.openSession();
       // session.beginTransaction();
        try {
            session.beginTransaction();
            Groups group=(Groups)session.get(Groups.class, groupName);
            session.delete(group);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}
