/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author g33k5qu4d
 */
public class LoadFromXml {
    //static LoadFromXml loader = new LoadFromXml();   
    private List<String> monthyearList = new ArrayList<>();
    private List<String> fileUrlList = new ArrayList<>(); 
    private String xmlFileName="ImageInfo.xml";
    
    public String getXmlfileName(){
        return this.xmlFileName;
    }
    
    public LoadFromXml(){
        
        loadToList(getXmlfileName());
        
    }
    
    private  void loadToList(String xmlFileName) {
        
        this.xmlFileName=xmlFileName;
        
        try {
            
            
            File file=new File(System.getProperty("user.home"), xmlFileName);
            InputStream iStream = new FileInputStream(file);
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();

            UserHandler handler = new UserHandler();

            parser.parse(iStream, handler);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMlLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error loading xml file");
        }catch(Exception e){
            e.getCause();
            System.out.println("Error in loadFromXMlclass");
        }
    }

    
    private class UserHandler extends DefaultHandler {

         boolean bMonthYear=false;
         boolean bFileUrl=false;

        @Override
        public void startElement(String uri, String localname,
                String qName, Attributes attr) throws SAXException {
            if (qName.equalsIgnoreCase("history")) {
                   // System.out.println("History found");
            }else if(qName.equalsIgnoreCase("monthyear")){
                bMonthYear=true;
            }else if(qName.equalsIgnoreCase("fileUrl")){
                bFileUrl=true;
            }
        }

        @Override
        public void characters(char[] chars, int start, int lenght) throws SAXException {
            if(bMonthYear){
                String monthYear=new String(chars, start, lenght);
                monthyearList.add(monthYear);
               // System.out.print(monthYear);
                bMonthYear=false;
            }else if(bFileUrl){
                String fileUrl=new String(chars, start, lenght);
             //   fileUrlList.add(fileUrl);
                bFileUrl=false;
            }
        }
        
    }


    public ObservableList<String> monthYearOL() {
        
        ObservableList<String> oList=FXCollections.observableArrayList(monthyearList);
        return oList;
    }
    
    public ObservableList<String> fileUrlOL() {
        
        ObservableList<String> oList=FXCollections.observableArrayList(fileUrlList);
        return oList;
    }
    public Set<String> monthYearSet(){
        Set<String> set=new HashSet<>(monthyearList);
        return set;
    }
   // public static void main(String args[]){
     //    LoadFromXml loader=new LoadFromXml();
     //    ObservableList<String> ol=loader.monthYearOL();
         
    //     ol.stream().forEach((s) -> {
     //        System.out.println(s+" ");
 //       });
         
  //  }
    
    public List<String> domGetELementByName(String name){
        List<String> similarly=new ArrayList<>();
        try {
            DocumentBuilderFactory dbBuilder=DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder=dbBuilder.newDocumentBuilder();
            Document doc=dbuilder.parse(System.getProperty("user.home")+File.separator+xmlFileName);
            doc.getDocumentElement().normalize();
            NodeList nList=doc.getElementsByTagName("history");
            for(int i=0; i<nList.getLength(); i++){
                Node node=nList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element=(Element)node;
                    String compare=element.getElementsByTagName("monthyear").item(0).getTextContent();
                    if(compare.equals(name)){
                        String url=element.getElementsByTagName("fileUrl").item(0).getTextContent();
                        similarly.add(url);
                    }
                }       
            }
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(LoadFromXml.class.getName()).log(Level.SEVERE, null, ex);
        }
       return similarly;
    }
}
