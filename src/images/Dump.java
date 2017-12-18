/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images;

/**
 *
 * @author g33k5qu4d
 */
public class Dump {
    
    /** 

    private void getImagePath() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        String dir = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(dir));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File chosenFile = fileChooser.showOpenDialog(stage);
        this.selectedFile=chosenFile;
        
    }
    * 
    *  private static final String hospStr = "hospitality.png";
    private static final String choirStr = "choir.jpg";
    private static final String secStr = "security.jpg";
    private static final String techStr = "technical.jpg";
    private static final String childStr = "children.jpg";
    private static final String mediaStr = "media.jpg";
    private static final String ushStr = "usher.jpg";
    private static final String santStr = "santuary.jpg";
   
    public File getFromLoad(){
        return selectedFile;
    }

    public File setExternalImage(String unit) throws FileNotFoundException, IOException {
         //External saving method
         saveImagetoFile(unit);
         File gotcha=new File("restoration.jpg");
         String userHome = System.getProperty("user.home");
         File file = new File(userHome+File.separator+"RestorationApp"+File.pathSeparator + unit);
         System.out.println(file.getAbsoluteFile());
         File[] list=file.listFiles();
         
         for(File f:list){
             if(f.getName().matches(unit+".[jpg|png|gif]"));
             return file;
         }
        return gotcha;    
    }
    public Image setInternalImage(String unit){
       Image image=new Image(ClassLoader.getSystemResourceAsStream(unit));  
       return image;
    }

    public void setButtonListener() {
        setImage.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                getImagePath();
            }
        });
    }

    private void ToggleListener() throws FileNotFoundException, IOException {

        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            Toggle tog = group.getSelectedToggle();
            String userHome=System.getProperty("user.home");
            try {
                if (tog == hospi_head) {
                     
                    if(setExternalImage("hospitality").exists()){
                           imageDisplay=new ImageView(setExternalImage("hospitality").getAbsolutePath());
                    }else{
                         imageDisplay=new ImageView(setInternalImage("hospitality.jpg"));
                    }
                    
                } else if (tog == choir_head) {
                    
                    //imageDisplay = new ImageView(" ");
                } else if (tog == kid_head) {
                    //saveImagetoFile("children");
                   // imageDisplay = new ImageView(" ");
                } else if (tog == media_head) {
                    //saveImagetoFile("media");
                   // imageDisplay = new ImageView(" ");
                } else if (tog == santuary_head) {
                    //saveImagetoFile("santuary");
                    //imageDisplay = new ImageView(" ");
                } else if (tog == secure_head) {
                    //saveImagetoFile("ccso");
                    //imageDisplay = new ImageView(" ");
                } else if (tog == tech_head) {
                    
                    //imageDisplay = new ImageView(" ");
                } else if (tog == usher_head) {
                    
                    //imageDisplay = new ImageView(" ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void saveImagetoFile(String unit) throws FileNotFoundException, IOException {
        //run in
        FileInputStream fStream = null;
        FileOutputStream fOutStream = null;
        try {
            System.out.println(getFromLoad().getAbsolutePath());
            fStream = new FileInputStream(getFromLoad().getAbsoluteFile());

            String ext = null;
            if (getFromLoad().getCanonicalPath().endsWith(".png")) {
                ext = File.pathSeparator + ".png";
            } else if (getFromLoad().getCanonicalPath().endsWith(".jpg")) {
                ext = File.pathSeparator + ".jpg";
            } else if (getFromLoad().getCanonicalPath().endsWith(".jpg")) {
                ext = File.pathSeparator + ".gif";
            } else {
                System.out.println("Scream!!!!!!!!!");
            }
            String userHome = System.getProperty("user.home");
            File file = new File(userHome+File.separator+"RestorationApp" + File.pathSeparator + unit + ext);
            if (!file.exists()) {
                file.createNewFile();
            }
            fOutStream = new FileOutputStream(file);
            int c;
            while ((c = fStream.read()) != -1) {
                fOutStream.write(c);
            }
        } finally {
            if (fStream != null) {
                fStream.close();
            }
            if (fOutStream != null) {
                fOutStream.close();
            }
        }
    }
    public void dumped(String unit){
        Image image;
        if(unit.equalsIgnoreCase("Tech")){
            image=new Image(ClassLoader.getSystemResourceAsStream("Technical.jpg"));
        }else if(unit.equalsIgnoreCase("choir")){
            image=new Image(ClassLoader.getSystemResourceAsStream("choir.jpg"));
        }else if(unit.equalsIgnoreCase("security")){
            image=new Image(ClassLoader.getSystemResourceAsStream("security.jpg"));
        }else if(unit.equalsIgnoreCase("hospitality")){
            image=new Image(ClassLoader.getSystemResourceAsStream("hospitality.jpg"));
        }else if(unit.equalsIgnoreCase("media")){
            image=new Image(ClassLoader.getSystemResourceAsStream("media.jpg"));
        }else if(unit.equalsIgnoreCase("choir")){
            image=new Image(ClassLoader.getSystemResourceAsStream("choir.jpg"));
        }
    }
    **/
}
