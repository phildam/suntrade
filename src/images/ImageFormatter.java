/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package churchtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author g33k5qu4d
 */
public class ImageFormatter {

   
    File file;
    String month;
    static Set<Integer> set = new HashSet<Integer>();
    private List<String> list = new ArrayList<>();
    Label label=new Label();
    VBox vbox=new VBox();
    public void setInformations(Label label,VBox vbox){
        this.label=label;
        this.vbox=vbox;
    }
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");

        try {
            Stage stage = new Stage();
            DirectoryChooser dc = new DirectoryChooser();
            String dir = System.getProperty("user.home");
            dc.setInitialDirectory(new File(dir));
            this.file = dc.showDialog(stage);
            load(vbox);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFile(File f) {
        this.file = f;
    }

    public List<String> URLToList() {
        return list;
    }
    
    public void clickImage(ImageView im, File selectedFile) {
        im.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    String name = selectedFile.getName();
                    FileTime ft = getMetadata(selectedFile);

                    long lon = ft.toMillis();
                    final Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(lon);

                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                    Month mo = Month.of(month);
                    LocalDate ld = LocalDate.of(year, mo, dayOfMonth);
                    set.add(ld.getYear());
                    label.setText(mo.getDisplayName(TextStyle.FULL, Locale.ROOT)
                            + " " + ld.getYear());
                } catch (IOException ex) {
                    Logger.getLogger(ImageFormatter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public FileTime getMetadata(File file) throws IOException {
        Path path = file.toPath();
        System.out.println(path.toAbsolutePath());
        BasicFileAttributes attr = Files.readAttributes(
                path, BasicFileAttributes.class);
        FileTime ft = attr.lastModifiedTime();

        return ft;
    }

    public void setSize(ImageView iv) {
        iv.setFitHeight(100.0);
        iv.setFitWidth(100.0);
    }

    public File getFile() {
        return file;
    }

    private HBox BoxFactory() {
        HBox newBox = new HBox();
        return newBox;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void load(VBox vBox) throws FileNotFoundException, IOException {
        HBox hbox = BoxFactory();
        vBox.setSpacing(50.0);
        vBox.autosize();
        vBox.setAlignment(Pos.TOP_LEFT);

        File list[] = getFile().listFiles();
        for (File in : list) {

            Image im = new Image(new FileInputStream(in));
            ImageView iv = new ImageView(im);

            this.list.add(in.getAbsolutePath());

            clickImage(iv, in);
            setSize(iv);

            hbox.setSpacing(5.0);
            hbox.setScaleShape(true);

            hbox.getChildren().addAll(iv);

        }
        vBox.getChildren().add(hbox);
        
    }

   
}
