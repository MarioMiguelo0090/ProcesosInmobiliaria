package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.EnviosDeCorreoElectronico;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOPropietario;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Ventana_RegistrarPropietarioController implements Initializable  {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_RegistrarPropietarioController.class);
    private Stage escenario;
    @FXML private AnchorPane pane_Principal;
    @FXML private TextField txfd_ApellidoM;
    @FXML private TextField txfd_ApellidoP;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    @FXML private Button btn_Regresar;
    @FXML private Label label_ErrorNombre;
    @FXML private Label label_ErrorApellidoMaterno;
    @FXML private Label label_ErrorApellidoPaterno;
    @FXML private Label label_ErrorCorreo;
    @FXML private Label label_ErrorRfc;
    @FXML private Label label_ErrorTelefono;
    
    private static final Logger logger = Logger.getLogger(Ventana_RegistrarPropietarioController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_Regresar.setOnAction(event -> regresarVentanaPrincipal()); 
        verificarDato();
    }
    
    public void verificarDato(){
        label_ErrorApellidoMaterno.setVisible(false);
        label_ErrorApellidoPaterno.setVisible(false);
        label_ErrorCorreo.setVisible(false);    
        label_ErrorNombre.setVisible(false);    
        label_ErrorRfc.setVisible(false);    
        label_ErrorTelefono.setVisible(false);    
        label_ErrorCorreo.setVisible(false);
    }
    
    public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }
    
    public Usuario obtenerUsuario(){
        Usuario usuario = new Usuario();
        
            usuario.setNombre(txfd_Nombre.getText());
            usuario.setApellidoPaterno(txfd_ApellidoP.getText());
            usuario.setApellidoMaterno(txfd_ApellidoM.getText());
            usuario.setCorreo(txfd_Correo.getText());
            usuario.setTelefono(txfd_Telefono.getText());
            usuario.setRFC(txfd_RFC.getText());
        
        return usuario;                        
    }
    
    public void cerrarVentana(){
        escenario = (Stage) pane_Principal.getScene().getWindow();
        escenario.close();
    }
    
    @FXML
    public void regresarDeVentana(){
        String rutaVentanaFXML = "/interfazGrafica/Ventana_ConsultarPropietarios.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
    
    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentanaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Object controlador = loader.getController();
            if (controlador instanceof Ventana_ConsultarPropietariosController) {
                Ventana_ConsultarPropietariosController ConsultarPropietariosController = (Ventana_ConsultarPropietariosController) controlador;
                ConsultarPropietariosController.inicializar(stage);
            }

            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
    }
    
   @FXML
    public void registrarPropietario() {
        if (verificarInformacion()) {
        try {
            Usuario usuario = obtenerUsuario();
            if (usuario == null) {
                Alertas.mostrarMensajeDatosInvalidos();
                return;
            }
            DAOPropietario daoPropietario = new DAOPropietario();

            if (!daoPropietario.verificarSiExisteRFC(usuario.getRFC())) {
                if (!daoPropietario.verificarSiExisteElCorreo(usuario.getCorreo())) {
                    int filasAfectadas = daoPropietario.agregarNuevoPropietario(usuario);
                    if (filasAfectadas > 0) {
                        Alertas.mostrarMensajeDatosIngresados();
                        regresarDeVentana();
                    } else {
                        Alertas.mostrarMensajeErrorEnLaConexion();
                    }
                } else {
                    Alertas.mostrarMensajeRFCDuplicados();
                }
            } else {
                Alertas.mostrarMensajeDatosDuplicados();
            }
        } catch (SQLException ex) {
            Alertas.mostrarMensajeErrorEnLaConexion();
            LOG.error(ex);
        }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        } 
    }

    public void regresarVentanaPrincipal(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultarPropietarios.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
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
                label_ErrorRfc.setVisible(true);
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
        label_ErrorRfc.setVisible(false);
    }

}
