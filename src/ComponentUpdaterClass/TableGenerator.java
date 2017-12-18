
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUpdaterClass;

import ComponentUpdaterClass.AccountTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author g33k5qu4d
 */
public class TableGenerator {
    private static TableView table=new TableView();
    private static TableView tab=new TableView();
    private ObservableList<String> columnDetails=FXCollections.observableArrayList();
    private static ObservableList tableItems=FXCollections.observableArrayList();
    private static ObservableList loanTableItem=FXCollections.observableArrayList();
    
    public TableGenerator(ObservableList<String> ol,TableView tv){
        this.columnDetails=ol;
        
        TableGenerator.table=tv;
    }
    
    public TableGenerator(ObservableList<String> ol){
        this.columnDetails=ol;
        
    }
    
    public static TableView table(){
        return table;
    }
  //  public static void addToTableItems(AccountTransaction transaction){
    //    tableItems.addAll(transaction);
    //}
    
     public static void addToTableItems(Object obj){
        tableItems.addAll(obj);
    }
    public static void addToLoanTab(Object obj){
        loanTableItem.addAll(obj);
    }
    
    private static TableColumn tableColumn(String name){
        return new TableColumn(name);
    }
    
    public void generatorCol(){
         //System.out.println();
        for(String s:columnDetails){
            TableColumn col=tableColumn(s.toUpperCase());
            
            System.out.println(s+ " column");
            col.setCellValueFactory(new PropertyValueFactory<>(s));
            Double width=table.getWidth()/columnDetails.size();
            col.setUserData(s);
            
            col.setSortType(TableColumn.SortType.ASCENDING);
            col.setResizable(true);
            col.setPrefWidth(150);
            col.setMinWidth(100);
            table.getColumns().add(col); //getColumns().add(col);
        }
        table.setItems(tableItems);
    }
    
    public void generatorCol(TableView table){
         //System.out.println();
        tab=table;
        for(String s:columnDetails){
            TableColumn col=tableColumn(s.toUpperCase());
            
            System.out.println(s+ " column");
            col.setCellValueFactory(new PropertyValueFactory<>(s));
            Double width=table.getWidth()/columnDetails.size();
            col.setUserData(s);
            
            col.setSortType(TableColumn.SortType.ASCENDING);
            col.setResizable(true);
            col.setPrefWidth(150);
            col.setMinWidth(100);
            table.getColumns().add(col); //getColumns().add(col);
        }
       // return table;
    }
    
    public static void setTabGenColItem(ObservableList items){
        tab.setItems(items);
    }
    
}
