import java.io.* ;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage primaryStage ;
    static  AnchorPane conLayout;
	@Override
	public void start(Stage primaryStage) throws IOException
       {
		Main.primaryStage = primaryStage ;
		Main.primaryStage.setTitle("Analyseur réaliser par ZEEROUK MADJDA SIL1");
		Main.primaryStage.setResizable(false);
		try  
		{   
		Main.showConnection("fileUpload.fxml");
		} 
		catch	(Exception e)
		{     
	     e.getCause().printStackTrace();	
		 System.out.println(e.getMessage());
		} 
		
     	}


    
    static void showConnection(String fxml) throws IOException
    {
    FXMLLoader loader = new FXMLLoader ();
    loader.setLocation(Main.class.getResource(fxml));
    conLayout = loader.load();
    Scene scene = new Scene(conLayout);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();	
    } 

public static void main(String[] args)   {
    
    launch(args);
}








}
