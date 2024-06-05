package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;
import logicaDeNegocio.DAO.DAOUsuario;


public class Ventana_ModificarAgenteControlador  implements Initializable{
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_ModificarAgenteControlador.class);
    private Stage escenario;
    @FXML private AnchorPane anchor_Ventana;
    @FXML private TextField txfd_ApellidoMaterno;
    @FXML private TextField txfd_ApellidoPaterno;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    private int idUsuario;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void inicializar(int idUsuario){
        this.idUsuario=idUsuario;
        llenarDatosUsuario();               
    }
    
    public void llenarDatosUsuario(){
        DAOUsuario daoUsuario=new DAOUsuario();
        Usuario usuario=daoUsuario.consultarUsuarioPorId(idUsuario);
        txfd_Nombre.setText(usuario.getNombre());
        txfd_ApellidoPaterno.setText(usuario.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(usuario.getApellidoMaterno());
        txfd_Correo.setText(usuario.getCorreo());
        txfd_RFC.setText(usuario.getRFC());       
        txfd_Telefono.setText(usuario.getTelefono());
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
            System.out.println("Error usuario");
            LOG.info(excepcion);
        }
        return usuario;                        
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
            LOG.error(excepcion);
        }
    }
    
public void actualizarAgente() {
    DAOUsuario daoUsuario = new DAOUsuario();
    Usuario usuarioAntiguo = daoUsuario.consultarUsuarioPorId(idUsuario);
    Usuario usuarioActualizado = obtenerUsuario();
    
    if (usuarioActualizado == null) {
        Alertas.mostrarMensajeDatosInvalidos();
        return;
    }
    
    try {
        boolean datosModificados = false;
        boolean datosDuplicados = false;
        
        if (!usuarioActualizado.getNombre().equals(usuarioAntiguo.getNombre())) {
            daoUsuario.modificarNombrePorIdUsuario(idUsuario, usuarioActualizado.getNombre());
            datosModificados = true;
        }
        if (!usuarioActualizado.getApellidoPaterno().equals(usuarioAntiguo.getApellidoPaterno())) {
            daoUsuario.modificarApellidoPaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoPaterno());
            datosModificados = true;
        }
        if (!usuarioActualizado.getApellidoMaterno().equals(usuarioAntiguo.getApellidoMaterno())) {
            daoUsuario.modificarApellidoMaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoMaterno());
            datosModificados = true;
        }
        if (!usuarioActualizado.getCorreo().equals(usuarioAntiguo.getCorreo())) {
            int resultadoCorreo = daoUsuario.modificarCorreoPorIdUsuario(idUsuario, usuarioActualizado.getCorreo());
            if (resultadoCorreo != 1) {
                Alertas.mostrarMensajeDatosDuplicados();
                datosDuplicados = true;
            } else {
                datosModificados = true;
            }
        }
        if (!usuarioActualizado.getRFC().equals(usuarioAntiguo.getRFC())) {
            int resultadoRFC = daoUsuario.modificarRFCPorIdUsuario(idUsuario, usuarioActualizado.getRFC());
            if (resultadoRFC != 1) {
                Alertas.mostrarMensajeDatosDuplicados();
                System.out.println("RFC repetido");
                datosDuplicados = true;
            } else {
                datosModificados = true;
            }
        }
        if (!usuarioActualizado.getTelefono().equals(usuarioAntiguo.getTelefono())) {
            daoUsuario.modificarTelefonoPorIdUsuario(idUsuario, usuarioActualizado.getTelefono());
            datosModificados = true;
        }
        
        if (datosModificados) {
            Alertas.mostrarMensajeActualizacionExitosa();
            regresarDeVentana();
        } else if (datosDuplicados) {

        } else {
            Alertas.mostrarMensajeSinCambios();
        }
    } catch (Exception e) {
        LOG.warn(e);
        Alertas.mostrarMensajeErrorEnLaConexion();
    }
}

}
