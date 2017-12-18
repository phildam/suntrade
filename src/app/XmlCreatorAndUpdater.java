/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author g33k5qu4d
 */
public class XmlCreatorAndUpdater {
    private String root;
    private String monthyear;
    private String fileUrl;
    private static File file=null;
    private String fileName;
    private String subroot;
    
    public XmlCreatorAndUpdater(String fileName,String root) throws IOException, XMLStreamException {
        try{
        this.root = root;
        this.fileName=fileName; 
        
        String f = System.getProperty("user.home");
        File loadFile=new File(f, fileName+".xml");
        if(loadFile.exists()){
            loadFile.delete();
        }
        setFile(loadFile);
        
        staxBuilderXmlCreator(fileName,root);
        }catch(Exception e){
            e.getCause();
            System.out.println("XmlcreatorAndUpdator");
        }
    }

    public void setFile(File f){
        this.file=f;
    }
    public static File getFile(){
        return file;
    }
    
    private void staxBuilderXmlCreator(String fileName,String root) throws FileNotFoundException, IOException, XMLStreamException {
       
        File file =getFile();
        if (!file.exists()) {
            file.createNewFile();
        
        
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer
                = xMLOutputFactory.createXMLStreamWriter(new FileOutputStream(file));

        writer.writeStartDocument();
        writer.writeStartElement(root);
        //writer.writeStartElement(subRoot);
        
        

     //   writer.writeStartElement(monthyear);
      //  writer.writeAttribute("url", f);
      //  writer.writeCharacters(monthYearString);
      //  writer.writeEndElement();

      //  writer.writeStartElement(fileUrl);
      //  writer.writeCharacters(fileUrlString);
      //  writer.writeEndElement();

       /// String[] list = fileUrl.split(";");
       // for (String child : list) {
       //     writer.writeStartElement("Child");
         //   writer.writeCharacters(child);
        //    writer.writeEndElement();
       // }
        //writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
        }else{
            System.out.println("XMLCreatorAndUpdater");
            return;
        }

    }

    public static void domUpdater(String subRoot,String monthYearString,String fileUrl) {
        String f = System.getProperty("user.home");
        File files = getFile();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(files);
            
            Element rootElement = doc.getDocumentElement();

            Element subRooter = doc.createElement(subRoot);

            Element monthyearr = doc.createElement("monthyear");
            monthyearr.appendChild(doc.createTextNode(monthYearString));
            subRooter.appendChild(monthyearr);

            Element fileUrll = doc.createElement("fileUrl");
            fileUrll.appendChild(doc.createTextNode(fileUrl));
            subRooter.appendChild(fileUrll);

           // String[] list = fileUrl.split(";");
           // for (String person : list) {
            //    Element child = doc.createElement("child");
              //  child.appendChild(doc.createTextNode(person));
                //subRooter.appendChild(child);
          //  }
            rootElement.appendChild(subRooter);
            

            TransformerFactory tf
                    = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result
                    = new StreamResult(files);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | DOMException | TransformerException e) {
            e.printStackTrace();
        } catch (SAXException | IOException ex) {
            Logger.getLogger(XmlCreatorAndUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e){
            e.getCause();
            System.out.println("Xmlcretorandupdater domupdatermethod");
        }
    }

}
