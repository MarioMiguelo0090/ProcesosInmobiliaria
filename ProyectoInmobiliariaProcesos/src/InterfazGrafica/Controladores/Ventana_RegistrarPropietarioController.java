package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Ventana_RegistrarPropietarioController implements Initializable  {
    private Stage escenario;
    @FXML private AnchorPane pane_Principal;
    @FXML private TextField txfd_ApellidoM;
    @FXML private TextField txfd_ApellidoP;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    
    private static final Logger logger = Logger.getLogger(Ventana_RegistrarPropietarioController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public Usuario obtenerUsuario(){
        Usuario usuario = new Usuario();
        try {
            usuario.setNombre(txfd_Nombre.getText());
            usuario.setApellidoPaterno(txfd_ApellidoP.getText());
            usuario.setApellidoMaterno(txfd_ApellidoM.getText());
            usuario.setCorreo(txfd_Correo.getText());
            usuario.setTelefono(txfd_Telefono.getText());
            usuario.setRFC(txfd_RFC.getText());
        } catch (IllegalArgumentException excepcion) {
            usuario = null;
        }
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
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, excepcion);
        }
    }
    
   @FXML
    public void registrarPropietario() {
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
                    Alertas.mostrarMensajeDatosDuplicados();
                }
            } else {
                Alertas.mostrarMensajeDatosDuplicados();
            }
        } catch (SQLException ex) {
            Alertas.mostrarMensajeErrorEnLaConexion();
            Logger.getLogger(Ventana_RegistrarPropietarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
