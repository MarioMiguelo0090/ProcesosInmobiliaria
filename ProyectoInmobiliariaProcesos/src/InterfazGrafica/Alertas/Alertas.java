package interfazDeUsuario.Alertas;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Alertas extends Application {
    
    public static void mostrarMensajeUsuarioNoEncontrado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Usuario no encontrado");
            mensaje.setContentText("El usuario que ha ingresado no se encuentra registrado");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeErrorEnLaConexion(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en la conexion");
            mensaje.setContentText("Se ha perdido la conexión en la base de datos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeRFCoNoEncontrado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("RFC no encontrado");
            mensaje.setContentText("El RFC registrado no pertenece a ningún propietario");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosInvalidos(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Datos inválidos");
            mensaje.setContentText("Verifique que los datos ingresados sean los correctos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosDuplicados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Duplicado de datos");
            mensaje.setContentText("Los datos que desea ingresar ya han sido previamente insertados");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosIngresados(){
         Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos correctos");
            mensaje.setContentText("Los datos han sido insertados correctamente");
            mensaje.showAndWait();
        
        });
    }
    public static void mostrarMensajeDatosIncompletos(){
        Platform.runLater(() ->{
        Alert mensaje = new Alert (AlertType.ERROR);
        mensaje.setTitle("Información invalida");
        mensaje.setContentText("Asegúrese de llenar correctamente todos los campos");
        mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosModificados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos modificados");
            mensaje.setContentText("Los datos han sido modificados correctamente");
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeNoSeHanEncontradoPropiedades(String mensajeTexto){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Sin resultados");
            mensaje.setContentText(mensajeTexto);
            mensaje.showAndWait();
        
        });
    }
    
    public static void mostrarMensajeDatosSinModificar(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos sin modificar");
            mensaje.setContentText("Modifique algún campo para realizar una modificacion");
            mensaje.showAndWait();
        
        });
    }
    
    @Override
    public void start(Stage stage){
        try{
            stage.show();
        }catch(Exception excepcion){
            System.out.println("Hola");
        }
     
    }
    
}