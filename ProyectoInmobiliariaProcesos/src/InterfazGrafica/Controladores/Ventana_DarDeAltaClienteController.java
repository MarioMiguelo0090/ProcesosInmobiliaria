package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.math.BigDecimal;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.EnviosDeCorreoElectronico;
import logicaDeNegocio.Clases.GeneradorDeContrasenias;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_DarDeAltaClienteController implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_DarDeAltaClienteController.class);
    @FXML
    private ComboBox cmb_Estado;

    @FXML
    private ComboBox cmb_TipoPropiedad;

    @FXML
    private TextField txfd_ApellidoMaterno;

    @FXML
    private TextField txfd_ApellidoPaterno;

    @FXML
    private TextField txfd_Ciudad;

    @FXML
    private TextField txfd_Correo;

    @FXML
    private TextField txfd_MinimoMetros;

    @FXML
    private TextField txfd_Nombre;

    @FXML
    private TextField txfd_PrecioMaximo;

    @FXML
    private TextField txfd_PrecioMinimo;

    @FXML
    private TextField txfd_RFC;

    @FXML
    private TextField txfd_Telefono;
    
    @FXML
    private AnchorPane anchor_DarDeAltaCliente;
    @FXML
    private Button btn_cancelar;
    private Stage stage_ventana;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxEstado();
        llenarComboBoxTipoPropiedad();
        btn_cancelar.setOnAction(event->regresarVentanaPrincipal());
    }
    
    public void regresarVentanaPrincipal(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_MenuPrincipalAdministrador.fxml";
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
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_DarDeAltaCliente.getScene().getWindow();
        stage_ventana.close();
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
            LOG.info(excepcion);
        }
        return usuario;                        
    }
    
    public Cliente obtenerCliente(){
        Cliente cliente=new Cliente();
        String estado=(String) cmb_Estado.getSelectionModel().getSelectedItem();
        String tipo=(String) cmb_TipoPropiedad.getSelectionModel().getSelectedItem();
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        Ubicacion ubicacion=new Ubicacion();
        TipoPropiedad tipoPropiedad=new TipoPropiedad();        
        try{
            cliente.setCiudad(txfd_Ciudad.getText());
            cliente.setRangoDePrecioMinimo(new BigDecimal(txfd_PrecioMinimo.getText()));
            cliente.setRangoDePrecioMaximo(new BigDecimal(txfd_PrecioMaximo.getText()));
            cliente.setMinimoMetrosCuadrados(new BigDecimal(txfd_MinimoMetros.getText()));
            ubicacion.setEstado(estado);
            ubicacion.setIdUbicacion(daoUbicacion.consultarIdUbicacionPorEstado(estado));
            tipoPropiedad.setTipo(tipo);
            tipoPropiedad.setIdTipoPropiedad(daoTipoPropiedad.consultarIdPropiedadPorTipo(tipo));
            cliente.setUbicacion(ubicacion);
            cliente.setTipoPropiedad(tipoPropiedad);            
        }catch(IllegalArgumentException excepcion){
            cliente=null;
            LOG.info(excepcion);
        }
        return cliente;        
    }
    
    public void llenarComboBoxEstado(){
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        List<Ubicacion> ubicaciones=daoUbicacion.consultarUbicaciones();
        ObservableList<String> ubicacionesVisibles = FXCollections.observableArrayList();
        for(Ubicacion ubicacion : ubicaciones){
            ubicacionesVisibles.add(ubicacion.getEstado());            
        }
        cmb_Estado.setItems(ubicacionesVisibles);        
    }
    
    public void llenarComboBoxTipoPropiedad(){
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        List<TipoPropiedad> tiposPropiedad=daoTipoPropiedad.consultarTiposPropiedad();
        if(tiposPropiedad.isEmpty()){
            Alertas.mostrarMensajeErrorEnLaConexion();
        }else{
            ObservableList<String> tiposPropiedadVisibles = FXCollections.observableArrayList();
            for(TipoPropiedad tipoPropiedad : tiposPropiedad){
                tiposPropiedadVisibles.add(tipoPropiedad.getTipo());
            }
            cmb_TipoPropiedad.setItems(tiposPropiedadVisibles);        
        }        
    }
    
    public void registrarCliente(){
        Usuario usuario=obtenerUsuario();
        Cliente cliente=obtenerCliente();
        Login login=crearAcceso();
        if(usuario==null||cliente==null||login==null){
            Alertas.mostrarMensajeDatosInvalidos();
            return;            
        }
        cliente.setEstadoCliente("Activo");
        DAOUsuario daoUsuario=new DAOUsuario();
        int resultadoInsercion=daoUsuario.registrarUsuario(usuario);
        switch (resultadoInsercion) {
            case 1:
                int idUsuario=daoUsuario.obtenerIdUsuarioPorCorreo(usuario.getCorreo());
                usuario.setIdUsuario(idUsuario);
                cliente.setUsuario(usuario);
                DAOCliente daoCliente=new DAOCliente();                
                login.setIUsuario(idUsuario);
                int resultadoInsercionCliente=daoCliente.registrarCliente(cliente,login);
                if(resultadoInsercionCliente==1){
                    enviarCorreo(usuario, login);
                    Alertas.clienteRegistradoCorrectamente();  
                    regresarVentanaPrincipal();
                }else{                    
                    Alertas.mostrarMensajeErrorEnLaConexion();
                }   break;
            case 0:
                Alertas.mostrarMensajeDatosDuplicados();                
                break;
            default:
                Alertas.mostrarMensajeErrorEnLaConexion();                
                break;
        }        
    }
    
    public Login crearAcceso() {
        Login login = new Login();
          try{
        login.setUsuario(txfd_Correo.getText());
        login.setContrasenia(GeneradorDeContrasenias.generarContraseña());
         }catch(IllegalArgumentException excepcion){
            login=null;
            LOG.info(excepcion);
        }
        return login;
    }
    
    private boolean enviarCorreo(Usuario usuario, Login login) {
        String mensaje = "Estimado cliente " + usuario.getNombre() + ",\n\n" +
                "Lo hemos registrado exitosamente como cliente en el sistema de gestion inmobiliaria el encanto. A continuación se muestran tus credenciales de acceso:\n\n" +
                "Usuario: " + login.getUsuario() + "\n" +
                "Contraseña: " + login.getContrasenia() + "\n\n" +
                "¡Gracias por ser parte de esto!\n" +
                "Sistema de gestion inmobiliaria el encanto";
        
        return EnviosDeCorreoElectronico.verificarEnvioCorreo(usuario.getCorreo(), "Credenciales de acceso", mensaje);
    }
    
}
