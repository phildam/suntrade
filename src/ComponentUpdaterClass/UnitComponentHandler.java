/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComponentUpdaterClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author g33k5qu4d
 */
public class UnitComponentHandler {

   
    private static final List<String> groupList = new ArrayList<>();
    private static final Map<String, String> bossMap = new HashMap<>();
    private static final Map<String, String> emailMap = new HashMap<>();
    private static final Map<String, String> contactMap = new HashMap<>();
   
       

    public void loadToUnit() {
        try {
            File unitXML = new File("SunTradeGroups.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            UnitHandler unitHandler = new UnitHandler();
            parser.parse(unitXML, unitHandler);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(UnitComponentHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(UnitComponentHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UnitComponentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
           // System.out.println("Error from unitComponenentHandler class in componentUpdaterclass");
            e.getCause();
        }

    }

    private class UnitHandler extends DefaultHandler {

        boolean bGroup = false;
        boolean bGroupId = false;
        

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("groupName")) {
                String name = attributes.getValue("name");//get unit names hers
                groupList.add(name);
                UnitLoader.setUnit(name);
                bGroup = true;
            } else if (qName.equalsIgnoreCase("groupId")) {
                String boss = attributes.getValue("id");
                bossMap.put(UnitLoader.getUnit(), boss);
                bGroupId = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("unit")) {
               // System.out.println("operation comlete in updateComponentClass");
            }
        }

    }
    
    public ObservableList<String> getUnitList() {
         ObservableList<String> oList = FXCollections.observableArrayList(groupList);
         return oList;
    }

    public ObservableMap<String, String> getObservableBossMap() {

        ObservableMap<String, String> oMap = FXCollections.observableMap(bossMap);
        return oMap;
    }

    public ObservableMap<String, String> getObservableEmailMap() {

        ObservableMap<String, String> oMap = FXCollections.observableMap(emailMap);
        return oMap;
    }

    public ObservableMap<String, String> getObservableContactMap() {

        ObservableMap<String, String> oMap = FXCollections.observableMap(contactMap);
        return oMap;
    }
    
    

    public static void main(String args[]) {
        UnitComponentHandler loader=new UnitComponentHandler();
        loader.loadToUnit();
        ObservableList<String> ol = loader.getUnitList();
        ol.stream().forEach((s) -> {
            System.out.print(s + " ");
        });

         System.out.println("\n"+loader.getObservableBossMap().keySet());
       //System.out.println(UnitLoader.getUnit());
    }
}
