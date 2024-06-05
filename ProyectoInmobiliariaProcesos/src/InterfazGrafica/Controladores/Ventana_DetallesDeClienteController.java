package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

public class Ventana_DetallesDeClienteController implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_DetallesDeClienteController.class);
    @FXML
    private AnchorPane anchor_DetallesDeCliente;
    private Stage stage_ventana;

    @FXML
    private ComboBox cmb_Estado;

    @FXML
    private ComboBox cmb_TipoPropiedad;

    @FXML
    private TextField txfd_PrecioMinimo;

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
    private TextField txfd_RFC;

    @FXML
    private TextField txfd_Telefono;
    private int idUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        llenarComboBoxEstado();
        llenarComboBoxTipoPropiedad();
        
    }    
    public void inicializar(int idUsuario, Stage stage){
        this.idUsuario=idUsuario;
        llenarDatosUsuario();
        llenarDatosCliente();
        this.stage_ventana = stage;
        stage_ventana.setOnCloseRequest(event -> {
            event.consume();
        });
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
    
    public void llenarDatosCliente(){
        DAOCliente daoCliente=new DAOCliente();
        Cliente cliente=daoCliente.consultarClientePorIdUsuario(idUsuario);
        txfd_Ciudad.setText(cliente.getCiudad());
        txfd_MinimoMetros.setText(String.valueOf(cliente.getMinimoMetrosCuadrados()));
        txfd_PrecioMaximo.setText(String.valueOf(cliente.getRangoDePrecioMaximo()));
        txfd_PrecioMinimo.setText(String.valueOf(cliente.getRangoDePrecioMinimo()));        
        cmb_TipoPropiedad.setValue(cliente.getTipoPropiedad().getTipo());
        cmb_Estado.setValue(cliente.getUbicacion().getEstado());        
    }
    
    public void llenarComboBoxEstado(){
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        List<Ubicacion> ubicaciones=daoUbicacion.consultarUbicaciones();
        if(ubicaciones.isEmpty()){
            Alertas.mostrarMensajeErrorEnLaConexion();        
        }else{
            ObservableList<String> ubicacionesVisibles = FXCollections.observableArrayList();
            for(Ubicacion ubicacion : ubicaciones){
                ubicacionesVisibles.add(ubicacion.getEstado());            
            }
            cmb_Estado.setItems(ubicacionesVisibles);
        }                
    }
    
    public void llenarComboBoxTipoPropiedad(){
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        List<TipoPropiedad> tiposPropiedad=daoTipoPropiedad.consultarTiposPropiedad();
        ObservableList<String> tiposPropiedadVisibles = FXCollections.observableArrayList();
        for(TipoPropiedad tipoPropiedad : tiposPropiedad){
            tiposPropiedadVisibles.add(tipoPropiedad.getTipo());
        }
        cmb_TipoPropiedad.setItems(tiposPropiedadVisibles);
    }
    
    public void actualizar(){
        boolean valor=true;
        DAOUsuario daoUsuario=new DAOUsuario();
        Usuario usuarioAntiguo=daoUsuario.consultarUsuarioPorId(idUsuario);
        DAOCliente daoCliente=new DAOCliente();
        Cliente clienteAntiguo=daoCliente.consultarClientePorIdUsuario(idUsuario);        
        int idCliente=clienteAntiguo.getIdCliente();
        Usuario usuarioActualizado=obtenerUsuario();        
        Cliente clienteActualizado=obtenerCliente();
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        if(usuarioActualizado==null||clienteActualizado==null){
            Alertas.mostrarMensajeDatosInvalidos();
            return;
        }
        if(!usuarioActualizado.getNombre().equals(usuarioAntiguo.getNombre())){
            daoUsuario.modificarNombrePorIdUsuario(idUsuario, usuarioActualizado.getNombre());
        }
        if(!usuarioActualizado.getApellidoPaterno().equals(usuarioAntiguo.getApellidoPaterno())){
            daoUsuario.modificarApellidoPaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoPaterno());
        }
        if(!usuarioActualizado.getApellidoMaterno().equals(usuarioAntiguo.getApellidoMaterno())){
            daoUsuario.modificarApellidoMaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoMaterno());            
        }
        if(!usuarioActualizado.getCorreo().equals(usuarioAntiguo.getCorreo())){
            int resultadoCorreo=daoUsuario.modificarCorreoPorIdUsuario(idUsuario, usuarioActualizado.getCorreo());
            if(resultadoCorreo!=1){                 
                valor=false;
            }
        }
        if(!usuarioActualizado.getRFC().equals(usuarioAntiguo.getRFC())){
            int resultadoRFC=daoUsuario.modificarRFCPorIdUsuario(idUsuario, usuarioActualizado.getRFC());
            if(resultadoRFC!=1){                
                valor=false;
            }
        }
        if(!usuarioActualizado.getTelefono().equals(usuarioAntiguo.getTelefono())){
            daoUsuario.modificarTelefonoPorIdUsuario(idUsuario, usuarioActualizado.getTelefono());
        }
        if(!clienteActualizado.getCiudad().equals(clienteAntiguo.getCiudad())){
            daoCliente.modificarCiudadPorId(idCliente, clienteActualizado.getCiudad());
        }
        if(clienteActualizado.getMinimoMetrosCuadrados()!=clienteAntiguo.getMinimoMetrosCuadrados()){
            daoCliente.modificarMinimoMetrosCuadradosPorId(idCliente, clienteActualizado.getMinimoMetrosCuadrados());        
        }
        if(clienteActualizado.getRangoDePrecioMinimo()!=clienteAntiguo.getRangoDePrecioMinimo()){
            daoCliente.modificarRangoDePrecioMinimoPorId(idCliente, clienteActualizado.getRangoDePrecioMinimo());
        }
        if(clienteActualizado.getRangoDePrecioMaximo()!=clienteAntiguo.getRangoDePrecioMaximo()){
            daoCliente.modificarRangoDePrecioMaximoPorId(idCliente, clienteActualizado.getRangoDePrecioMaximo());        
        }
        if(!clienteActualizado.getTipoPropiedad().getTipo().equals(clienteAntiguo.getTipoPropiedad().getTipo())){
            int idTipoPropiedad=daoTipoPropiedad.consultarIdPropiedadPorTipo(clienteActualizado.getTipoPropiedad().getTipo());
            daoCliente.modificarTipoDePropiedadPorId(idCliente, idTipoPropiedad);
        }
        if(!clienteActualizado.getUbicacion().getEstado().equals(clienteAntiguo.getUbicacion().getEstado())){
            int idUbicacion=daoUbicacion.consultarIdUbicacionPorEstado(clienteActualizado.getUbicacion().getEstado());
            daoCliente.modificarUbicacionPorId(idCliente, idUbicacion);            
        }
        if(valor){
            Alertas.mostrarMensajeDatosModificados();        
        }else{
            Alertas.mostrarMensajeDatosDuplicados();            
        }
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
            LOG.info(excepcion);
            usuario=null;            
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
    
    public void eliminarCliente(){
        if(Alertas.confirmarEliminacion()){
            DAOCliente daoCliente=new DAOCliente();
            daoCliente.archivarClientePorIdUsario(idUsuario); 
            salirDeLaVentana();        
        }        
    }
    
    public void salirDeLaVentana(){
         String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/InterfazGrafica/Ventana_ConsultaDeClientes.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            LOG.error(excepcion);
        }
        cerrarVentana();                
    }
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_DetallesDeCliente.getScene().getWindow();
        stage_ventana.close();
    }       
}
