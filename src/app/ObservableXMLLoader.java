/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ObservableXMLLoader {
    static ObservableXMLLoader loader = new ObservableXMLLoader();
       
    private static List<String> list = new ArrayList<>();

    public void loadToList() {
        try {
            System.getProperty("user.home");
            InputStream file = getClass().getResourceAsStream("countries.xml");
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();

            UserHandler handler = new UserHandler();

            parser.parse(file, handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMlLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading xml file");
        }catch(Exception e){
            e.getMessage();
            System.out.println("Error in observableXMLLoader");
        }
    }

    private class UserHandler extends DefaultHandler {

        // boolean bArea=false;

        @Override
        public void startElement(String uri, String localname,
                String qName, Attributes attr) throws SAXException {
            if (qName.equalsIgnoreCase("area")) {
                String countries = attr.getValue("title");
                list.add(countries);
            }
        }
    }


    public ObservableList<String> addCountries() {
        ObservableXMLLoader loader = new ObservableXMLLoader();
        loader.loadToList();
        
        ObservableList<String> oList=FXCollections.observableArrayList(list);
        return oList;
    }
    
}
