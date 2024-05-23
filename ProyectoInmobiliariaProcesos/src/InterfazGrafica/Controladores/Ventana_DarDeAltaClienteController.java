package InterfazGrafica.Controladores;

import interfazGrafica.Alertas.Alertas;
import java.math.BigDecimal;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_DarDeAltaClienteController implements Initializable {
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
    private Stage stage_ventana;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxEstado();
        llenarComboBoxTipoPropiedad();
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
            Logger.getLogger(Ventana_DarDeAltaClienteController.class.getName()).log(Level.SEVERE, null, excepcion);                        
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
            Logger.getLogger(Ventana_DarDeAltaClienteController.class.getName()).log(Level.SEVERE, null, excepcion);                        
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
        if(usuario==null||cliente==null){
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
                int resultadoInsercionCliente=daoCliente.registrarCliente(cliente);
                if(resultadoInsercionCliente==1){
                    Alertas.clienteRegistradoCorrectamente();                       
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
    
    
}
