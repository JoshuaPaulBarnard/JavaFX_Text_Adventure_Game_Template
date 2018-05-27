/*
 *  Author:        Joshua Paul Barnard
 *  GitHub:        https://github.com/JoshuaPaulBarnard
 *  License:       MIT
 */



package JavaFX_Text_Adventure_Game_Template;


import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Joshua Paul Barnard
 */
public class Main extends Application
{
    
    @Override
    public void start( Stage stage ) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("UI_Layout.fxml") );

        Button submitButton = new Button("Enter");
        
        Scene scene = new Scene( root );
        
        stage.setScene( scene );
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
