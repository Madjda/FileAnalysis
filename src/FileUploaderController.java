

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileUploaderController implements Initializable {
	 Stage primaryStage ;
	 FileChooser fileChooser ;
	 DirectoryChooser dirChooser;
     String filePath ;
	 public static String dirPath="C:\\\\" ;

      @FXML
      private Button retour;
	   @FXML
	    private Button choose;
	   @FXML
	    private Label fileLabel;
	   @FXML
	    private Button confirm;
	   @FXML
	    private Label dirLabel;
	    @FXML
	    private Button save;
	
	
	@FXML
	void choose () 
	{   
		fileChooser = new FileChooser () ;
		fileChooser.getExtensionFilters().addAll(
	     new FileChooser.ExtensionFilter("IHM", "*.ihm"));

        //Show open file dialog
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
           filePath=file.getPath() ; 
           fileLabel.setText(filePath);
          
           	confirm.setDisable(false);
           
            	
          
            }
      
	}
	@FXML
	void saveTo()
	{
		dirChooser = new DirectoryChooser () ;
        //Show open file dialog
        File file = dirChooser.showDialog(primaryStage);
        if (file != null) {
           dirPath=file.getPath() ;
           dirLabel.setText(dirPath);
 
            if ((filePath != null ))
            {
            	confirm.setDisable(false);
            }
            } 
	}
	
	
	@FXML
	void confirm () 
	{   
		
	
	 try {
         File fileIn = new File(filePath) ;
           analyse.analyze (fileIn);
           confirm.setDisable(true);
           choose.setDisable(true);
           save.setDisable(true);
           Alert dialogD = new Alert(AlertType.CONFIRMATION);
			dialogD.setTitle("Result");
			dialogD.setHeaderText(null);
			dialogD.setContentText(" Your files have been successfully created ! check them  in the specified directory");
			Optional<ButtonType> answer1 = dialogD.showAndWait();
           
        }   catch (Exception e) {
            confirm.setDisable(true);
            choose.setDisable(true);
            save.setDisable(true);
           
            Alert dialogD = new Alert(AlertType.CONFIRMATION);
 			dialogD.setTitle("Result");
 			dialogD.setHeaderText(null);
 			dialogD.setContentText(" Your file is syntactically worng");
 			Optional<ButtonType> answer1 = dialogD.showAndWait();
 			 e.printStackTrace();
          }
 

}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		confirm.setDisable(true);
		Tooltip t1= new Tooltip("Select the IHM file");
		Tooltip t2= new Tooltip("Select the directory where to save the files");
		Tooltip t3= new Tooltip("lanch the compilation");
		choose.setTooltip(t1);
		save.setTooltip(t2);
		confirm.setTooltip(t3);
		
	}	
	
}
