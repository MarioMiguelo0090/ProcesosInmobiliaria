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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.EnviosDeCorreoElectronico;
import logicaDeNegocio.Clases.GeneradorDeContrasenias;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOAgenteInmobiliario;

public class Ventana_RegistrarAgenteControlador implements Initializable  {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_RegistrarAgenteControlador.class);
    private Stage escenario;
    @FXML private AnchorPane anchor_Ventana;
    @FXML private TextField txfd_ApellidoMaterno;
    @FXML private TextField txfd_ApellidoPaterno;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    @FXML private Label label_ErrorNombre;
    @FXML private Label label_ErrorApellidoMaterno;
    @FXML private Label label_ErrorApellidoPaterno;
    @FXML private Label label_ErrorCorreo;
    @FXML private Label label_ErrorRfc;
    @FXML private Label label_ErrorTelefono;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etiquetasDeError();
    }
    
    public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }
    
    public Usuario obtenerUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre(txfd_Nombre.getText());
        usuario.setApellidoPaterno(txfd_ApellidoPaterno.getText());
        usuario.setApellidoMaterno(txfd_ApellidoMaterno.getText());
        usuario.setCorreo(txfd_Correo.getText());
        usuario.setTelefono(txfd_Telefono.getText());
        usuario.setRFC(txfd_RFC.getText());
        return usuario;                        
    }
    
    public Login crearAcceso() {
        Login login = new Login();
        try {
            login.setUsuario(txfd_Correo.getText());
            login.setContrasenia(GeneradorDeContrasenias.generarContraseña());
        } catch (IllegalArgumentException excepcion) {
            login = null;
            LOG.warn(excepcion);
        }
        return login;
    }
    
    public void cerrarVentana() {
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarDeVentana() {
        String rutaVentanaFXML = "/interfazGrafica/Ventana_ConsultarAngenteInmobiliario.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
  private void desplegarVentanaCorrespondiente(String rutaVentanaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentanaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Object controlador = loader.getController();
            if (controlador instanceof Ventana_ConsultarAgenteControlador) {
                Ventana_ConsultarAgenteControlador consultarAgenteControlador = (Ventana_ConsultarAgenteControlador) controlador;
                consultarAgenteControlador.inicializar(stage);
            }

            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
    }
    
    private boolean estaVacio() {
        return txfd_Nombre.getText().isEmpty() ||
               txfd_ApellidoPaterno.getText().isEmpty() ||
               txfd_ApellidoMaterno.getText().isEmpty() ||
               txfd_RFC.getText().isEmpty() ||
               txfd_Telefono.getText().isEmpty() ||
               txfd_Correo.getText().isEmpty();
    }
    
    private boolean verificarInformacion() {
        Usuario usuario = new Usuario();
        boolean validacion = true;
        
        if (!estaVacio()) {
            try {
                usuario.setNombre(txfd_Nombre.getText());
            } catch (IllegalArgumentException exception) {
                label_ErrorNombre.setVisible(true);
                validacion = false;
            }

            try {
                usuario.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            } catch (IllegalArgumentException nombreException) {
                label_ErrorApellidoPaterno.setVisible(true);
                validacion = false;
            }
            
            try {
                usuario.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            } catch (IllegalArgumentException nombreException) {
                label_ErrorApellidoMaterno.setVisible(true);
                validacion = false;
            } 

            try {
                usuario.setCorreo(txfd_Correo.getText());
            } catch (IllegalArgumentException coreoException) {
                label_ErrorCorreo.setVisible(true);
                validacion = false;
            } 

            try {
                usuario.setTelefono(txfd_Telefono.getText());
            } catch (IllegalArgumentException nombrePaisException) {
                label_ErrorTelefono.setVisible(true);
                validacion = false;
            }
            
            try {
                usuario.setRFC(txfd_RFC.getText());
            } catch (IllegalArgumentException nombrePaisException) {
                label_ErrorRfc.setVisible(true);
                validacion = false;
            }
        } else {
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
    
    public void registrarAgente() {
        if (verificarInformacion()) {
            try {
                Usuario usuario = obtenerUsuario();
                Login login = crearAcceso();
                if (usuario == null || login == null) {
                    Alertas.mostrarMensajeDatosInvalidos();
                    return;
                }
                DAOAgenteInmobiliario daoAgenteInmobiliario = new DAOAgenteInmobiliario();
                
                if (!daoAgenteInmobiliario.verificarSiExisteRFC(usuario.getRFC())) {
                    if (!daoAgenteInmobiliario.verificarSiExisteElCorreo(usuario.getCorreo())) {
                        if (daoAgenteInmobiliario.insertarAgenteInmobiliario(usuario, login) == 3) {
                            enviarCorreo(usuario, login); 
                            Alertas.mostrarMensajeDatosIngresados();
                            regresarDeVentana();
                        } 
                    } else {
                        Alertas.mostrarMensajeDatosDuplicados();
                    }
                } else {
                    Alertas.mostrarMensajeDatosDuplicados();
                }
            } catch (SQLException ex) {
                Alertas.mostrarMensajeErrorEnLaConexion();
                LOG.warn(ex);
            }
        } else {
            Alertas.mostrarMensajeDatosInvalidos();
        }          
    }
    
    private boolean enviarCorreo(Usuario usuario, Login login) {
        String mensaje = "Estimado agente inmobiliario " + usuario.getNombre() + ",\n\n" +
                         "Lo hemos registrado exitosamente como profesor. A continuación se muestran tus credenciales de acceso:\n\n" +
                         "Usuario: " + login.getUsuario() + "\n" +
                         "Contraseña: " + login.getContrasenia() + "\n\n" +
                         "¡Gracias por su solicitud!\n" +
                         "SDGCOILVIC";
        
        return EnviosDeCorreoElectronico.verificarEnvioCorreo(usuario.getCorreo(), "Credenciales de acceso", mensaje);
    }
}
