/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author g33k5qu4d
 */
public class ImageOps {
    
    static String dir = System.getProperty("user.home");
    static File selectedFile;
    static Stage stage=new Stage();
    
     private File getSelectedFile() {
        return selectedFile;
    }
    private static void setSelectedFile(File file){
        selectedFile=file;
    }
    
    public File uploadAccountImage(Button button ,ImageView imageDisplay) {
        
        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Choose Image");
                    fileChooser.setInitialDirectory(new File(dir));
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                    
                    File chosenFile = fileChooser.showOpenDialog(stage);
                    if (chosenFile == null) {
                        return;
                    }
                   setSelectedFile(chosenFile);
                    
                    // System.out.println(chosenFile.toString());
                    setOnChange(imageDisplay,chosenFile);
                } catch (IOException ex) {
                    Logger.getLogger(ImageOps.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        return getSelectedFile();
    }

    private void setOnChange(ImageView imageDisplay,File selectedFile) throws FileNotFoundException {
        imageDisplay.setImage(new Image(new FileInputStream(selectedFile)));
        
        
                   
    }
   
    public String aveFile(File fileName, String saveAs) throws IOException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        String fileSaveLocation=null;
        try {
          
            fis = new FileInputStream(fileName);
            bis = new BufferedInputStream(fis);
            String ext=null;
            File saveLoc = null;
            File saveFile = null;
            if (getSelectedFile() != null) {
                int pointLoc = getSelectedFile().getAbsolutePath().lastIndexOf(".");
                ext = getSelectedFile().getAbsolutePath().substring(pointLoc);
                saveLoc = new File(dir + File.separator + ".res appfolder" + File.separator);
                saveFile = new File(saveLoc + File.separator + saveAs + ext);
                fileSaveLocation=saveLoc + File.separator + saveAs + ext;
                if (!saveLoc.exists()) {
                    saveLoc.mkdirs();
                }
                if (!saveFile.exists()) {
                    saveFile.createNewFile();
                }
            }
            fos = new FileOutputStream(saveFile);
            bos = new BufferedOutputStream(fos);
            int i=0;
            int c;
            while ((c = bis.read()) != -1) {
                
                bos.write(c);
               // System.out.print(c);
                i++;
            }
            
           // System.out.println("byteLength: "+i);
            bos.flush();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageOps.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }

        }
        return fileSaveLocation;
    }

}
