package InterfazGrafica;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NewFXMain extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/InterfazGrafica/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        stage.show();    
                           
    }
    
        
     public void mostrarVentanaLogin (Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sdgcoilvic/interfazDeUsuario/Login.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void mostrarVentanaAdministradorMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ventana_MenuPrincipalAdministrador.fxml"));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void mostrarVentanaCliente (Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}