/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Month;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author g33k5qu4d
 */
public class FileDater {
   private Label label;
   
    public FileDater(Label label,String s){
        this.label=label;
    }
    
    private static FileTime getMetadata(File file) throws IOException {
        Path path = file.toPath();
        //System.out.println(path.toAbsolutePath());
        BasicFileAttributes attr = Files.readAttributes(
                path, BasicFileAttributes.class);
        FileTime ft = attr.lastModifiedTime();
        FileTime createdDate=attr.creationTime();
        
        //attr.lastAccessTime();
        
        return ft;
    }
        public static void loadImageInfo(File selectedFile) throws IOException {
            Calendar cal = getCalender(selectedFile);

        }

        public static Calendar getCalender(File selectedFile) throws IOException {
            String name = selectedFile.getName();
            FileTime ft = getMetadata(selectedFile);
            long lon = ft.toMillis();
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(lon);
            return cal;
        }

        public static Month getMonth(Calendar cal) {
            
            int month = cal.get(Calendar.MONTH)+1;
            Month mo=null;
            try{
             mo= Month.of(month);
            }catch(Exception e){
                e.getMessage();
            }
            return mo;
        }

        public static Year getyear(Calendar cal) {
            int yea = cal.get(Calendar.YEAR);
            // LocalDate ld = LocalDate.of(yea, mo, dayOfMonth);
            Year year = Year.of(yea);
            return year;
        }

        public static int getDayInMOnth(Calendar cal) {
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
            return dayOfMonth;
        }
        public String getImInfo(Calendar cal) throws IOException{
            
            int dayOfMonth = getDayInMOnth(cal);
            Month mo = getMonth(cal);
            Year year = getyear(cal);
            label.setText("hello world");
           return (mo.getDisplayName(TextStyle.FULL, Locale.ROOT)
                    + " " + year.getValue());
        
    }
    
        public static void setSize(ImageView iv) {
        iv.setFitHeight(80.0);
        iv.setFitWidth(80.0);
        iv.setPreserveRatio(true);
    }
}
