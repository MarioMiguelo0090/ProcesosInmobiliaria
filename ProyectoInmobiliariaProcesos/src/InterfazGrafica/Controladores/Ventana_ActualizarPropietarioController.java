package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_ActualizarPropietarioController {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_ActualizarPropietarioController.class);
    private Stage escenario;

    
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_ApellidoP;
    @FXML private TextField txfd_ApellidoM;
    @FXML private TextField txfd_Telefono;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txt_EstadoPropietario;
    @FXML private Button btn_Guardar;
    @FXML private Button btn_Cancelar;    
    @FXML private Button btn_Regresar;
    @FXML private AnchorPane pane_Principal;
    @FXML private Label label_ErrorNombre;
    @FXML private Label label_ErrorApellidoMaterno;
    @FXML private Label label_ErrorApellidoPaterno;
    @FXML private Label label_ErrorCorreo;
    @FXML private Label label_ErrorRFC;
    @FXML private Label label_ErrorTelefono;



    private DAOPropietario daoPropietario = new DAOPropietario();
    private DAOUsuario daoUsuario = new DAOUsuario();
    private Propietario propietario;

    @FXML
    private void initialize() {
        btn_Guardar.setOnAction(event -> realizarRegistro());
        btn_Regresar.setOnAction(event -> regresarDeVentanabtn());
        label_ErrorApellidoMaterno.setVisible(false);
        label_ErrorApellidoPaterno.setVisible(false);
        label_ErrorCorreo.setVisible(false);    
        label_ErrorNombre.setVisible(false);    
        label_ErrorRFC.setVisible(false);    
        label_ErrorTelefono.setVisible(false);    
        label_ErrorCorreo.setVisible(false); 
    }

    
    public void verificarDato(){
        label_ErrorApellidoMaterno.setVisible(false);
        label_ErrorApellidoPaterno.setVisible(false);
        label_ErrorCorreo.setVisible(false);    
        label_ErrorNombre.setVisible(false);    
        label_ErrorRFC.setVisible(false);    
        label_ErrorTelefono.setVisible(false);    
        label_ErrorCorreo.setVisible(false);
    }
    
    public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });

    }

    public void cargarDatosPropietario(int idUsuario) {
        propietario = daoPropietario.consultarPropietarioPorIDUsuario(idUsuario);
        if (propietario != null) {
            Usuario usuario = propietario.getUsuario();
            txfd_Nombre.setText(usuario.getNombre());
            txfd_ApellidoP.setText(usuario.getApellidoPaterno());
            txfd_ApellidoM.setText(usuario.getApellidoMaterno());
            txfd_Telefono.setText(usuario.getTelefono());
            txfd_Correo.setText(usuario.getCorreo());
            txfd_RFC.setText(usuario.getRFC());
            txt_EstadoPropietario.setText(propietario.getEstadoPropietario());
        } else {
            mostrarAlerta("Error", "Propietario no encontrado");
        }
    }

    @FXML
    private void realizarRegistro() {
        if (verificarInformacion()) {
        try {
            String nombre = txfd_Nombre.getText();
            String apellidoPaterno = txfd_ApellidoP.getText();
            String apellidoMaterno = txfd_ApellidoM.getText();
            String telefono = txfd_Telefono.getText();
            String correo = txfd_Correo.getText();
            String rfc = txfd_RFC.getText();
            String estadoPropietario = txt_EstadoPropietario.getText();

            propietario.setEstadoPropietario(estadoPropietario);

            Usuario usuario = propietario.getUsuario();
            usuario.setNombre(nombre);
            usuario.setApellidoPaterno(apellidoPaterno);
            usuario.setApellidoMaterno(apellidoMaterno);
            usuario.setTelefono(telefono);
            usuario.setCorreo(correo);
            usuario.setRFC(rfc);

            int resultadoUsuario = daoUsuario.modificarUsuario(usuario);
            int resultadoPropietario = daoPropietario.cambiarEstadoPropietario(propietario, estadoPropietario);

            if (resultadoUsuario > 0 && resultadoPropietario > 0) {
                mostrarAlerta("Éxito", "Propietario actualizado correctamente");
            } else {
                mostrarAlerta("Error", "No se pudo actualizar el propietario");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "ID de propietario inválido");
            LOG.info(e);
        }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    @FXML
    private void regresarDeVentana() {
        Stage stage = (Stage) btn_Cancelar.getScene().getWindow();
        stage.close();     
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfazGrafica/Ventana_ConsultarPropietarios.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la ventana ActualizarPropietarioController.");
            LOG.warn(e);        
        }
    }
    
    @FXML
    private void regresarDeVentanabtn() {
        Stage stage = (Stage) btn_Regresar.getScene().getWindow();
        stage.close();     
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfazGrafica/Ventana_ConsultarPropietarios.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la ventana ActualizarPropietarioController.");
            LOG.warn(e);        
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }    
    
    
    private boolean estaVacio() {
        return txfd_Nombre.getText().isEmpty()||
                txfd_ApellidoP.getText().isEmpty()||
                txfd_ApellidoM.getText().isEmpty()||
                txfd_RFC.getText().isEmpty()||
                txfd_Telefono.getText().isEmpty()||
                txfd_Correo.getText().isEmpty();
    }
    
    private boolean verificarInformacion(){
        Usuario usuario=new Usuario();

        boolean validacion = true;
        
        if (!estaVacio()){
            try{
                 usuario.setNombre(txfd_Nombre.getText());
            } catch (IllegalArgumentException exception){
                label_ErrorNombre.setVisible(true);
                validacion = false;
            }

            try{
                usuario.setApellidoPaterno(txfd_ApellidoP.getText());
            } catch (IllegalArgumentException nombreException){
                label_ErrorApellidoPaterno.setVisible(true);
                validacion = false;
            }
            
            try{
                usuario.setApellidoMaterno(txfd_ApellidoM.getText());
            } catch (IllegalArgumentException nombreException){
                label_ErrorApellidoMaterno.setVisible(true);
                validacion = false;
            } 

            try{
                usuario.setCorreo(txfd_Correo.getText());
            } catch (IllegalArgumentException coreoException){
                label_ErrorCorreo.setVisible(true);
                validacion = false;
                } 

            try{
                usuario.setTelefono(txfd_Telefono.getText());
            } catch (IllegalArgumentException nombrePaisException){
                label_ErrorTelefono.setVisible(true);
                validacion = false;
            }
            
            try{
                usuario.setRFC(txfd_RFC.getText());
            } catch (IllegalArgumentException nombrePaisException){
                label_ErrorRFC.setVisible(true);
                validacion = false;
            }
        
        }else {
          Alertas.mostrarMensajeCamposVacios();

          validacion = false;  
        }
        return validacion;
        
    }
       
    private void etiquetasDeError() {
        label_ErrorNombre.setVisible(false);
        label_ErrorApellidoPaterno.setVisible(false);
        label_ErrorApellidoMaterno.setVisible(false);
        label_ErrorCorreo.setVisible(false);
        label_ErrorTelefono.setVisible(false);
        label_ErrorRFC.setVisible(false);
    }
}