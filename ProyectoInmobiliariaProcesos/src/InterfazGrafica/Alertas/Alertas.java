package InterfazGrafica.Alertas;


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
    
        public static void mostrarMensajeErrorCambioPantalla(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("ERROR");
            mensaje.setContentText("Error al cambiar de pantalla");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeActualizacionExitosa(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Actualiyación exitosa");
            mensaje.setContentText("Datos actualizados correctamente");
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
    
        
    public static void mostrarMensajeDatosVacios(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Campos vacios");
            mensaje.setContentText("Verifique los campos");
            mensaje.showAndWait();
        });
    }
    
        public static void mostrarMensajeSinResultados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("AVISO");
            mensaje.setContentText("No se encontraron resultados");
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
    
    public static void mostrarMensajeIngresarCriterios(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("AVISO");
            mensaje.setContentText("Por favor ingresa un criterio de búsqueda.");
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
    
    public static void clienteRegistradoCorrectamente(){
         Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro de cliente correcot");
            mensaje.setContentText("El cliente se registro de forma correcta");
            mensaje.showAndWait();
        
        });
    }
    
    public static boolean confirmarEliminacion() {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Eliminación");
        mensaje.setContentText("¿Está seguro realizar esta acción?");
        Optional<ButtonType> resultado = mensaje.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
    
    public static void propietarioRegistradoCorrectamente(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Uusario Registrado");
            mensaje.setContentText("El usuario fue registrado correctamante");
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