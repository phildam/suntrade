/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import ComponentUpdaterClass.AccountTransaction;
import ComponentUpdaterClass.LoanRepayment;
import ComponentUpdaterClass.TableGenerator;
import app.FileDater;
import entities.Account;
import entities.Customer;
import entities.Loan;
import entities.Loanrepayment;
import entities.Setting;
import entities.Transact;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author g33k5qu4d
 */
public class SunTradeTransactionController implements Initializable {

    @FXML
    private TableView<Transact> depositTv;
    @FXML
    private DatePicker dateOfWithdrawal;
    @FXML
    private TextField amounttoWithdraw;
    @FXML
    private DatePicker depositorWithdrawalDate;
    @FXML
    private TextField depositAmount;
    @FXML
    private DatePicker loanPaybackDate;
    @FXML
    private Button payLoan;
    @FXML
    private Button get;
    @FXML
    private TextField loanBalance;
    @FXML
    private Label getInformation;
    @FXML
    private ProgressIndicator progress;
    @FXML
    private TextField entityAccountNumber;

    UtilityExtractor utilExtract = new UtilityExtractor();

    @FXML
    private TextField surname;
    @FXML
    private TextField othername;
    @FXML
    private TextField groupname;
    @FXML
    private TextField accountbalance;
    @FXML
    private ImageView customerImage;
    @FXML
    private Label withdrawInfoLabel;
    @FXML
    private Button withdraw;
    @FXML
    private Button deposit;
    @FXML
    private Tab payloan;

    private Double balance;

    private Double loanAccountBalance;

    @FXML
    private Label loanInfoLabel;
    @FXML
    private Label depositInfoLabel;
    @FXML
    private ChoiceBox<String> depositType;
    @FXML
    private AnchorPane addable;
    @FXML
    private ListView<String> loanList;
    @FXML
    private PieChart chart;
    @FXML
    private TableView<Loanrepayment> loanTable;
    @FXML
    private TableColumn<LoanerDetails, String> loanIdCol;
    @FXML
    private TableColumn<LoanerDetails, String> loanTotalCol;
    @FXML
    private TableColumn<LoanerDetails, String> loanBalanceCol;
    @FXML
    private TableView<Object> loanminitab;

    private static ObservableList<LoanerDetails> ol = FXCollections.observableArrayList();
    private static ObservableList<PieChart.Data> chartdatas = FXCollections.observableArrayList();
    private ObservableList loanTableItem = FXCollections.observableArrayList();
    @FXML
    private TextField loanAmount;

    private Double loanTotal;
    private String loanerId;
    @FXML
    private Button printSavings;
    @FXML
    private Button snapShotSaving;
    @FXML
    private Label fullname;
    @FXML
    private Label accNumber;
    @FXML
    private Label fullname1;
    @FXML
    private Label accNumber1;
    @FXML
    private Button printSavings1;
    @FXML
    private Button snapShotSaving1;
    @FXML
    private AnchorPane printWithdrawal;
    @FXML
    private AnchorPane printLoan;
    @FXML
    private VBox wvb;
    @FXML
    private VBox lvb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableGenerator.table().getItems().clear();
        chart.setLegendSide(Side.TOP);
        withDrawButtonDefault();
        TableLoader();
        styleVb(wvb);
        styleVb(lvb);
        depositType.getItems().addAll("Mandatory Savings", "Voluntary Savings");
        depositType.getSelectionModel().select("Voluntary Savings");
    }
    public void styleVb(Node node){
        node.setStyle("-fx-background-color:rgba(0,0,0,0.1);\n" +
"    -fx-background-image:url(icons/whiteBg.png);\n" +
"    -fx-background-repeat:no-repeat;\n" +
"    -fx-background-size:contain;\n" //+
//"    -fx-background-radius:50;\n" +
//"    -fx-border-radius:50;"
                );
    }

    public void withDrawButtonDefault() {
        if (dateOfWithdrawal.getValue() == null || amounttoWithdraw.getText() == null) {
            // withdraw.setDisable(true);
        }

    }

    private void TableLoader() {
        ObservableList<String> transactionList = FXCollections.observableArrayList("accountNumber", "transactionType", "transactionAmount", "transactionDate", "previousBalance");

        TableGenerator depositTable = new TableGenerator(transactionList, depositTv);
        depositTable.generatorCol();

        ObservableList<String> loanTransactionList = FXCollections.observableArrayList("customerLoanNumber", "paydate", "amount");
        TableGenerator loanTab = new TableGenerator(loanTransactionList);
        loanTab.generatorCol(loanTable);

        ///   GetDatas.WithDrawalDetailsExtractor(JDBCUtil.getConnection());
    }

    public void loadLoanView() {
        String loanQuery = "FROM Loan l where l.accountNumber ='" + entityAccountNumber.getText() + "' and l.loanCompleteStatus='false' ";
        List li = utilExtract.queryTable(loanQuery);
        if (!li.isEmpty()) {
            String loanNumber = null;
            for (Object o : li) {
                Loan loan = (Loan) o;
                loanNumber = loan.getCustomerLoanNumber();
            }

            String hql = "FROM Loanrepayment l where l.customerLoanNumber = '" + loanNumber + "'";

            List list = utilExtract.queryTable(hql);
            System.err.print(list);
            if (!list.isEmpty()) {
                for (Object o : list) {
                    Loanrepayment lr = (Loanrepayment) o;
                    double amount = lr.getAmount();
                    System.err.print("Amount:" + amount);
                    String customerLoanNo = lr.getCustomerLoanNumber();
                    Date date = (Date) lr.getPaydate();
                    LoanRepayment lrp = new LoanRepayment(customerLoanNo, Common.stringedDate(date), Common.formatDouble(amount));
                    loanTableItem.add(lrp);
                }

                TableGenerator.setTabGenColItem(loanTableItem);
            }
        }
    }

    public void loadTransactionView(String hql) {

        TableGenerator.table().getItems().clear();

        // UtilityExtractor utilExtract = new UtilityExtractor();
        List list = utilExtract.queryTable(hql);

        for (Object o : list) {
            Transact t = (Transact) o;
            String accNumber = t.getAccountNumber();
            System.out.println("Account Number: " + accNumber);

            java.util.Date date = t.getTransactionDate();
            System.out.println(date.toString());

            Double previousBalance = t.getPreviousBalance();
            if (previousBalance.isNaN()) {
                previousBalance = 0.0;
            }
            Double transactionAmount = t.getTransactionAmount();
            if (transactionAmount.isNaN()) {
                transactionAmount = 0.0;
            }
            String transactionType = t.getTransactionType();

            AccountTransaction at = new AccountTransaction(accNumber, Common.formatDouble(transactionAmount), transactionType, Common.stringedDate((Date) t.getTransactionDate()), Common.formatDouble(previousBalance));

            TableGenerator.addToTableItems(at);
        }
    }

    public void loander() {
        loanIdCol.setCellValueFactory(new PropertyValueFactory<LoanerDetails, String>("loanId"));
        loanTotalCol.setCellValueFactory(new PropertyValueFactory<LoanerDetails, String>("loanTotal"));
        loanBalanceCol.setCellValueFactory(new PropertyValueFactory<LoanerDetails, String>("balance"));

        UtilityExtractor util = new UtilityExtractor();
        List li = util.queryTableWithMax("From Loan l where l.accountNumber = '" + entityAccountNumber.getText() + "' and l.loanCompleteStatus='false'");
        if (!li.isEmpty()) {
            for (Object o : li) {
                Loan loan = (Loan) o;
                LoanerDetails ld = new LoanerDetails(loan.getCustomerLoanNumber(), Common.formatDouble(loan.getLoantotal()), Common.formatDouble(loan.getCurrentLoanBalance()));
                ol.add(ld);

                loanList.getItems().add("PassBook_Number: " + loan.getAccountNumber());
                loanList.getItems().add("Customer_Loan_Number: " + loan.getCustomerLoanNumber());
                loanList.getItems().add("Loan Complete Status: " + loan.getLoanCompleteStatus());
                loanList.getItems().add("Loan Type: " + loan.getLoantype());
                loanList.getItems().add("Number of installment: " + loan.getTotalistallmentdays());
                loanList.getItems().add("Current_Loan_Balance: NGN " + Common.formatDouble(loan.getCurrentLoanBalance()) + " K");
                loanList.getItems().add("Date of Disbursement: " + Common.stringedDate((Date) loan.getDateofdisbursement()));
                loanList.getItems().add("Loan Amount: NGN " + Common.formatDouble(loan.getLoanAmount()));
                loanList.getItems().add("Service Charge: NGN " + Common.formatDouble(loan.getServiceCharge()) + " K");
                loanList.getItems().add("Loan Total: NGN " + Common.formatDouble(loan.getLoantotal()) + " K");
                loanList.getItems().add(" ");
                //loanList.getItems().add(" ");
                loanList.getItems().add(" ");
                Double t = loan.getLoantotal();
                chartdatas.add(new PieChart.Data("Balance", (loan.getCurrentLoanBalance() / t) * 100));
                chartdatas.add(new PieChart.Data("Paid", ((loan.getLoantotal() - loan.getCurrentLoanBalance()) / t) * 100));

            }
            loanminitab.getItems().addAll(ol);
            chart.setData(chartdatas);
        }
    }

    @FXML
    private void checkValidWithdrawalAmount(KeyEvent event) {

    }

    @FXML
    private void transactWithdrawal(ActionEvent event) {
        withdrawInfoLabel.setText("Withdrawals are made here");
        if (entityAccountNumber.getText().isEmpty()) {
            withdrawInfoLabel.setText("Account number not set");
        } else {
            if (dateOfWithdrawal.getValue() == null) {
                withdrawInfoLabel.setText("Transaction failed: DATE not set");
                withdrawInfoLabel.setStyle("-fx-text-fill:red");
            } else if (amounttoWithdraw.getText() == null || amounttoWithdraw.getText().matches("\\D")) {
                withdrawInfoLabel.setText("Transaction Failed: Only Integer digits are allowed");
                withdrawInfoLabel.setStyle("-fx-text-fill:red");
            } else {

                Date date = Date.valueOf(dateOfWithdrawal.getValue());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                Month month = FileDater.getMonth(cal);
                int day = FileDater.getDayInMOnth(cal);
                Year year = FileDater.getyear(cal);
                String dateString = day + " " + month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + year.getValue();
                System.out.println("Date: " + dateString);

                Double amount = 0.0; // Double.parseDouble(amounttoWithdraw.getText());
                if (amounttoWithdraw.getText() == null) {
                    amount = 0.0;
                } else if(amounttoWithdraw.getText().matches("\\d*")){
                    amount = Double.parseDouble(amounttoWithdraw.getText());
                }
                //  Double balance = Double.parseDouble(accountbalance.getText());
                Double bal = getAccountBalance();

                if (bal < amount) {
                    withdrawInfoLabel.setText("Balance too low for withdrawal");
                    withdrawInfoLabel.setStyle("-fx-text-fill:blue; -fx-background-color:white;");
                } else if (amount > bal) {

                    withdrawInfoLabel.setText("Amount greater than available Balance");
                    withdrawInfoLabel.setStyle("-fx-text-fill:red; -fx-background-color:white;");
                } else if (bal >= amount) {
                    double newBalance = bal - amount;
                    withdrawInfoLabel.setText("Withdrawal transaction successfully completed");
                    withdrawInfoLabel.setStyle("-fx-text-fill:teal; -fx-background-color:white;");

                    utilExtract.addTransaction(entityAccountNumber.getText(), "Withdrawal", amount, bal, date);
                    utilExtract.updateAccountBalance(entityAccountNumber.getText(), newBalance, date);
                    loadBalance();
                    refreshTable();
                }
            }
        }
    }

    @FXML
    private void TransactionDeposit(ActionEvent event) {

        if (entityAccountNumber.getText().isEmpty()) {
            depositInfoLabel.setText("Account number not set");
        } else {
            if (depositorWithdrawalDate.getValue() == null) {
                depositInfoLabel.setText("Transaction failed: DATE not set");
                depositInfoLabel.setStyle("-fx-text-fill:red");
            } else if (depositAmount.getText() == null || depositAmount.getText().matches("\\D")) {
                depositInfoLabel.setText("Transaction Failed: Only Integer digits are allowed");
                depositInfoLabel.setStyle("-fx-text-fill:red");
            } else {

                Date date = Date.valueOf(depositorWithdrawalDate.getValue());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                Month month = FileDater.getMonth(cal);
                int day = FileDater.getDayInMOnth(cal);
                Year year = FileDater.getyear(cal);
                String dateString = day + " " + month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + year.getValue();
                System.out.println("Date: " + dateString);

                Double amount = null; // Double.parseDouble(amounttoWithdraw.getText());
                if (depositAmount.getText() == null || depositAmount.getText() == "") {
                    amount = 0.0;
                } else if(!amounttoWithdraw.getText().matches("\\d*")) {
                    depositInfoLabel.setText("Only digits are allowed");
                }else{
                    amount = Double.parseDouble(depositAmount.getText());
                }

                Double bal = getAccountBalance();

                double newBalance = bal + amount;

                utilExtract.addTransaction(entityAccountNumber.getText(), depositType.getValue(), amount, bal, date);
                utilExtract.updateAccountBalance(entityAccountNumber.getText(), newBalance, date);

                depositInfoLabel.setText("Withdrawal transaction sucessfull completed");
                depositInfoLabel.setStyle("-fx-text-fill:teal; -fx-background-color:white;");

                loadBalance();
                refreshTable();
            }
        }
    }

    @FXML
    private void checkLoanAmountValidity(KeyEvent event) {

    }

    public double refreshloanBal() {
        UtilityExtractor utilityExtract = new UtilityExtractor();
        Double bal = null;
        List li = this.setLoanInfo("FROM Loan l where l.accountNumber ='" + entityAccountNumber.getText() + "' and l.loanCompleteStatus='false'");
        for (Object o : li) {
            Loan loan = (Loan) o;
            bal = loan.getCurrentLoanBalance();
        }
        return bal;
    }

    @FXML
    private void TransactionLoanPayement(ActionEvent event) {

        UtilityExtractor utilityExtract = new UtilityExtractor();
        List li = this.setLoanInfo("FROM Loan l where l.accountNumber ='" + entityAccountNumber.getText() + "' and l.loanCompleteStatus='false'");
        if (!li.isEmpty()) {
            reset();
            loanInfoLabel.setText("Loan Payments are made here");
            if (entityAccountNumber.getText().isEmpty()) {
                loanInfoLabel.setText("Account number not set");
            } else {
                if (loanPaybackDate.getValue() == null) {
                    loanInfoLabel.setStyle("-fx-text-fill:red;");
                    loanInfoLabel.setText("Transaction failed: date not set");

                } else if (loanAmount.getText() == null || loanAmount.getText().matches("\\D")) {
                    loanInfoLabel.setText("Transaction Failed: Only Integer digits are allowed");
                    loanInfoLabel.setStyle("-fx-text-fill:red");
                } else {
                    for (Object o : li) {
                        Loan loan = (Loan) o;
                        Date date = Date.valueOf(loanPaybackDate.getValue());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        Month month = FileDater.getMonth(cal);
                        int day = FileDater.getDayInMOnth(cal);
                        Year year = FileDater.getyear(cal);
                        String dateString = day + " " + month.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + year.getValue();
                        System.out.println("Date: " + dateString);

                        Double bal = getAccountBalance();

                        Double loanBal = loan.getCurrentLoanBalance();
                        Double loanTotal = loan.getLoantotal();
                        String loanId = loan.getCustomerLoanNumber();

                        Double amount = 0.0;
                        if (loanAmount.getText() == null) {
                            amount = 0.0;
                        } else {
                            amount = Double.parseDouble(loanAmount.getText());
                        }
                        //  Double balance = Double.parseDouble(accountbalance.getText());

                        Double loanBalance = loanBal - amount;

                        if (loanBalance > 0.0) {

                            utilExtract.addLoanTransaction(loanId, date, amount);
                            utilExtract.updateLoanAccountBalance(loanId, loanBalance, "false");
                            loanInfoLabel.setStyle("-fx-text-fill:red; -fx-background-color:white;");
                            loanInfoLabel.setText("Balance remains: NGN " + Common.formatDouble(refreshloanBal()) + " K");

                        } else if (loanBalance == 0.0) {
                            utilExtract.addLoanTransaction(loanId, date, amount);
                            utilExtract.updateLoanAccountBalance(loanId, loanBalance, "true");
                            loanInfoLabel.setStyle("-fx-text-fill:red; -fx-background-color:white;");
                            loanInfoLabel.setText("Loan payment completed,appply for new");

                        } else if (amount > loanBal) {
                            Double remainder = amount - loanBal;
                            Double remit = loanBal;
                            // utilExtract.addLoanTransaction(dateString, date, amount);
                            // utilExtract.updateLoanAccountBalance(entityAccountNumber.getText(), remit, "true");
                            // double newBalance = bal + remainder;
                            loanInfoLabel.setStyle("-fx-text-fill:red; -fx-background-color:white;");
                            loanInfoLabel.setText("Overpayment, due payment remains: NGN" + Common.formatDouble(loan.getCurrentLoanBalance()) + " K");

                            //  utilExtract.addTransaction(entityAccountNumber.getText(), "Balance From Loan payment", remainder, bal, date);
                            //  utilExtract.updateAccountBalance(entityAccountNumber.getText(), newBalance, date);
                        }

                    }
                    loander();
                    loadLoanView();
                    refreshTable();
                    loadBalance();
                }
            }
        } else {
            loanInfoLabel.setText("No active Loan");
        }
    }

    private void TableRefresh(ActionEvent event) {
        refreshTable();
    }

    public void setAcccountBalance(String hql) {
        UtilityExtractor utilExtract = new UtilityExtractor();
        List list = utilExtract.queryTable(hql);

        for (Object o : list) {

            Account a = (Account) o;
            System.out.println("Transa date: " + a.getDateModified());
            this.balance = a.getBalance();
            System.out.println(this.balance);
        }

    }

    public Double getAccountBalance() {
        return this.balance;
    }

    public List setLoanInfo(String hql) {
        UtilityExtractor utilExtract = new UtilityExtractor();
        List list = utilExtract.queryTable(hql);
        return list;
    }

    @FXML
    private void getAccountDetails(ActionEvent event) throws FileNotFoundException {
        // if(entityAccountNumber.getText().matches("//d")){
        reset();

        String hql = "FROM Customer c where c.accountNumber = '";
        // "from Customer c where c.country like '";
        List list = utilExtract.queryTable(hql + entityAccountNumber.getText() + "'");
        System.out.println(list);
        if (!list.isEmpty()) {
            if (Common.activeAccount(entityAccountNumber.getText(), utilExtract)) {
                for (Object o : list) {
                    Customer c = (Customer) o;
                    InputStream pictureurl = null;
                    if (c.getPictureurl() == null) {
                        pictureurl = getClass().getResourceAsStream("/icons/business_user_edt.png");
                    } else if (c.getPictureurl() != null) {

                        try {
                            pictureurl = new FileInputStream(new File(c.getPictureurl()));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(SunTradeTransactionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    Image image = new Image(pictureurl);
                    customerImage.setImage(image);
               // System.out.println(c.getSurname());

                    surname.setText(c.getSurname());
                    othername.setText(c.getOtherNames());
                    groupname.setText(c.getGroupName());
                }
                fullname.textProperty().bind(new SimpleStringProperty(surname.getText() + " " + othername.getText()));
                fullname1.textProperty().bind(new SimpleStringProperty(surname.getText() + " " + othername.getText()));
                accNumber.textProperty().bind(new SimpleStringProperty(entityAccountNumber.getText()));
                accNumber1.textProperty().bind(new SimpleStringProperty(entityAccountNumber.getText()));

                loander();
                loadLoanView();
                refreshTable();
                loadBalance();
            } else {
                getInformation.setText("Account Deactivated");
            }
        } else {

            getInformation.setText("Account Number does not exist in record");
        }

    }

    public void resetWithdrawal() {

    }

    public void loadBalance() {
        String bal = "from Account a where a.accountNumber = '";
        setAcccountBalance(bal + entityAccountNumber.getText() + "'");
        Double customerBalance = getAccountBalance();
        String formatedBalance = String.format("%,.2f", customerBalance);
        accountbalance.setText("NGN " + formatedBalance + " K");
    }

    public String formatCurrency(Locale locale) {
        Currency currency = Currency.getInstance(locale);
        return currency.getDisplayName();
    }

    public void refreshTable() {

        String acc = "from Transact t where t.accountNumber ='";
        loadTransactionView(acc + entityAccountNumber.getText() + "'");

        depositAmount.setText("");
        depositorWithdrawalDate.setValue(null);
        amounttoWithdraw.setText("");
        dateOfWithdrawal.setValue(null);
    }

    public void reset() {
        customerImage.setImage(null);
        TableGenerator.table().getItems().clear();
        loanTable.getItems().clear();
        surname.setText("");
        othername.setText("");
        groupname.setText("");
        amounttoWithdraw.setText("");
        getInformation.setText("");
        ol.clear();
        chartdatas.clear();
        loanminitab.getItems().clear();
        loanList.getItems().clear();
        chart.getData().clear();
    }

    @FXML
    private void checkDigit(KeyEvent event) {
        if (entityAccountNumber.getText().matches(".?\\D")) {
            entityAccountNumber.setStyle("-fx-border-color: red;");
            getInformation.setText("Only digits are allowed inThis field.");
        }
    }

    @FXML
    private void printSavings(ActionEvent event) {
        if (event.getSource() == printSavings) {
            printJob(printWithdrawal, "Saving's Transactions view Printed");
        } else if (event.getSource() == printSavings1) {
            printJob(printLoan, "Loan Transactions view Printed");
        }
    }

    public void printJob(Node node, String msg) {
        PrinterJob job = PrinterJob.createPrinterJob();
        
        if (job != null) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
                AppMainModel.getInstance().getLeftStatusBind().setText(msg);
            }
        }
    }

    @FXML
    private void snapShotSaving(ActionEvent event) throws IOException {
        if (event.getSource() == snapShotSaving) {
            snapShot(printWithdrawal);
        } else if (event.getSource() == snapShotSaving1) {
            snapShot(printLoan);
        }
    }

    public void snapShot(AnchorPane node) throws IOException {
        //SnapshotParameters parameters = new SnapshotParameters();
        WritableImage wi = new WritableImage((int)node.getWidth(),(int) node.getHeight());
        WritableImage snapshot = node.snapshot(new SnapshotParameters(), wi);
        UtilityExtractor util=new UtilityExtractor();
        String string="";
        List li=util.queryTable("FROM Setting");
        if(!li.isEmpty()){
            for(Object o: li){
                Setting s=(Setting)o;
               string=s.getPictureurl();
            }
        }
        
        File output = new File(string+File.separator+"SunTradeSnapShot" + new java.util.Date().getTime() + ".png");
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", output);
    }

    public class LoanerDetails {

        private final SimpleStringProperty loanId;
        private final SimpleStringProperty loanTotal;
        private final SimpleStringProperty balance;

        public LoanerDetails(String loanId, String loanTotal, String balance) {
            this.loanId = new SimpleStringProperty(loanId);
            this.loanTotal = new SimpleStringProperty(loanTotal);
            this.balance = new SimpleStringProperty(balance);
        }

        public String getLoanId() {
            return loanId.get();
        }

        public void setLoanId(String newValue) {
            loanId.set(newValue);
        }

        public String getLoanTotal() {
            return loanTotal.get();
        }

        public void setLoanTotal(String newValue) {
            loanTotal.set(newValue);
        }

        public String getBalance() {
            return balance.get();
        }

        public void setBalance(String newValue) {
            balance.set(newValue);
        }

    }

}
