/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

/**
 *
 * @author g33k5qu4d
 */
public class XMlLoader {
    ObservableList<String> list=FXCollections.observableArrayList();
    private void loadToList() {
        try {
            File file = new File("countries.xml");

            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();

            UserHandler handler = new UserHandler();

            parser.parse(file, handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMlLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading xml file");
        }catch(Exception e){
            e.getCause();
            System.out.println("Error from xml Loader class");
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
    public ObservableList<String> getCountriesList(){
        ObservableList<String> countries=list;
        
        return list;
    }
    
}
