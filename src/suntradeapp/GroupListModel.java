/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;

/**
 *
 * @author g33k5qu4d
 */
public class GroupListModel {
    private static GroupListModel uim=new GroupListModel();
    private ToggleGroup group;
    private TreeItem item;
    
    private static ObservableList<String> groupNames;
    private ListView<String> listv;
    
    
    public static GroupListModel getInstance(){
        return uim;
    }
    
    public void setGroup(ToggleGroup group){
        this.group=group;
    }
    
    public ToggleGroup getGroup(){
        return group;
    }
    
    public TreeItem getSelectedTreeItem(){
        return item;
    }
    
    public void setSelectedTreeItem(TreeItem item){
        this.item=item;
        
    }

    public ListView<String> getListv() {
        return listv;
    }

    public void setListv(ListView<String> listv) {
        this.listv = listv;
    }

    public static ObservableList<String> getGroupNames() {
        return groupNames;
    }

    public static void SetGroupNames(ObservableList<String> listOList) {
        groupNames = listOList;
    }
}
