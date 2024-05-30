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
import logicaDeNegocio.Clases.GeneradorDeContrasenias;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOAgenteInmobiliario;
import logicaDeNegocio.DAO.DAOCliente;

public class Ventana_RegistrarAgenteControlador implements Initializable  {
    private Stage escenario;
    @FXML private AnchorPane anchor_Ventana;
    @FXML private TextField txfd_ApellidoMaterno;
    @FXML private TextField txfd_ApellidoPaterno;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    @FXML private TextField txfd_Contrasenia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public Usuario obtenerUsuario(){
        Usuario usuario=new Usuario();
        try{
            usuario.setNombre(txfd_Nombre.getText());
            usuario.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            usuario.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            usuario.setCorreo(txfd_Correo.getText());
            usuario.setTelefono(txfd_Telefono.getText());
            usuario.setRFC(txfd_RFC.getText());
        }catch(IllegalArgumentException excepcion){
            usuario=null;
        }
        return usuario;                        
    }
    
    public Login crearAcceso() {
        Login login = new Login();
          try{
        login.setUsuario(txfd_Correo.getText());
        login.setContrasenia(GeneradorDeContrasenias.generarContraseña());
         }catch(IllegalArgumentException excepcion){
            login=null;
        }
        return login;
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarDeVentana(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultarAngenteInmobiliario.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
    
    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML){
        try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        }catch(IOException excepcion){
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, excepcion);
        }
    }
    
    public void registrarAgente(){
        try {
            Usuario usuario=obtenerUsuario();
            Login login = crearAcceso();
            if(usuario==null || login==null ){
                Alertas.mostrarMensajeDatosInvalidos();
                return;
            }
            DAOAgenteInmobiliario daoAgenteInmobiliario = new DAOAgenteInmobiliario();
            
            if (!daoAgenteInmobiliario.verificarSiExisteRFC(usuario.getRFC())) {
                if (!daoAgenteInmobiliario.verificarSiExisteElCorreo(usuario.getCorreo())) {
                    if (daoAgenteInmobiliario.insertarAgenteInmobiliario(usuario, login ) == 3) {
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
            Logger.getLogger(Ventana_RegistrarAgenteControlador.class.getName()).log(Level.SEVERE, null, ex);
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
