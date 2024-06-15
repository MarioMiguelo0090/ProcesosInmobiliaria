package InterfazGrafica.Controladores;
import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import logicaDeNegocio.Clases.Direccion;
import logicaDeNegocio.Clases.Propiedad;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.ClasesAuxiliares.PropiedadAuxiliar;
import logicaDeNegocio.DAO.DAODireccion;
import logicaDeNegocio.DAO.DAOPropiedad;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;
import logicaDeNegocio.DAO.DAOUsuario;
import logicaDeNegocio.Enums.EnumPropiedad;


public class Ventana_ModificarPropiedadControlador implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_ModificarPropiedadControlador.class);

     private Stage escenario;
    @FXML
    private AnchorPane anchor_Ventana;
    @FXML
    private Spinner<Integer> spn_Antiguedad;
    @FXML
    private TextField txfd_MetrosDeTerreno;
    @FXML
    private TextField txfd_Precio;
    @FXML
    private Spinner<Integer> spn_NoHabitaciones;
    @FXML
    private Spinner<Integer> spn_NoBanios;
    @FXML
    private Spinner<Integer> spn_NoPisos;
    @FXML
    private TextField txfd_Nombre;
    @FXML
    private ComboBox<String> cmb_EstadoPropiedad;
    @FXML
    private TextField txfd_CodigoPostal;
    @FXML
    private TextField txfd_Colonia;;
    @FXML
    private TextField txfd_Ciudad;
    @FXML
    private TextField txfd_Calle;
    @FXML
    private ComboBox<String> cmb_Estado;
    @FXML
    private TextField txfd_RFCPropietario;
    @FXML
    private ComboBox<String> cmb_TipoDePropiedad;
    @FXML
    private Label lbl_ErrorNombre;
    @FXML
    private Label lbl_ErrorTipoDePropiedad;
    @FXML
    private Label lbl_ErrorTipoDeEstado;
    @FXML
    private Label lbl_ErrorMetrosDeTerreno;
    @FXML
    private Label lbl_ErrorPrecio;
    @FXML
    private Label lbl_ErrorNoHabitaciones;
    @FXML
    private Label lbl_ErrorNoBanios;
    @FXML
    private Label lbl_ErrorCodigoPostal;
    @FXML
    private Label lbl_ErrorColonia;
    @FXML
    private Label lbl_ErrorCalle;
    @FXML
    private Label lbl_ErrorCiudad;
    @FXML
    private Label lbl_ErrorRFC;
    @FXML
    private Label lbl_ErrorEstado;
    @FXML
    private Label lbl_ErrorAntiguedad;
    private static final String RFC_PATTERN = "^[A-ZÑ&]{3,4}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[A-Z\\d]{3}$";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosPropiedad();
        cargarComboboxs();
    }
    public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }
    
    
    public void cargarDatosPropiedad(){
        PropiedadAuxiliar propiedad = PropiedadAuxiliar.getInstancia();
        int antiguedad = propiedad.getAntiguedad();
        int noBanios = propiedad.getNumeroDeBanios();
        int noHabitaciones = propiedad.getNumeroDeHabitaciones();
        int noPisos = propiedad.getNumeroDePisos();
        SpinnerValueFactory<Integer> valueFactoryAntiguedad = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, antiguedad);
        spn_Antiguedad.setValueFactory(valueFactoryAntiguedad);
        SpinnerValueFactory<Integer> valueFactoryBanios = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, noBanios);
        spn_NoBanios.setValueFactory(valueFactoryBanios);
        SpinnerValueFactory<Integer> valueFactoryHabitaciones = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, noHabitaciones);
        spn_NoHabitaciones.setValueFactory(valueFactoryHabitaciones);
        SpinnerValueFactory<Integer> valueFactoryPisos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, noPisos);
        spn_NoPisos.setValueFactory(valueFactoryPisos);
        txfd_MetrosDeTerreno.setText(String.valueOf(propiedad.getMetrosDeTerreno()));
        txfd_Precio.setText(String.valueOf(propiedad.getPrecio()));
        txfd_Nombre.setText(propiedad.getNombre());
        txfd_CodigoPostal.setText(propiedad.getDireccion().getCodigoPostal());
        txfd_Colonia.setText(propiedad.getDireccion().getColonia());
        txfd_Ciudad.setText(propiedad.getDireccion().getCiudad());
        txfd_Calle.setText(propiedad.getDireccion().getCalle());
        txfd_RFCPropietario.setText(propiedad.getPropietario().getUsuario().getRFC());
        cmb_Estado.setValue(propiedad.getDireccion().getUbicacion().getEstado());
        cmb_EstadoPropiedad.setValue(propiedad.getEstadoPropiedad());
        cmb_TipoDePropiedad.setValue(propiedad.getTipoDePropiedad().getTipo());
    }
    
     public void cargarComboboxs(){
        DAOUbicacion daoUbicacion = new DAOUbicacion();
        List<Ubicacion> ubicaciones = new ArrayList();
        ubicaciones = daoUbicacion.consultarUbicaciones();
        ObservableList<String> estadosComboBox = FXCollections.observableArrayList();
        for(Ubicacion ubicacion : ubicaciones){
            estadosComboBox.add(ubicacion.getEstado());
        }
        cmb_Estado.setItems(estadosComboBox);
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        List<TipoPropiedad> tipoPropiedades = new ArrayList();
        tipoPropiedades = daoTipoPropiedad.consultarTiposPropiedad();
        ObservableList<String> tiposDePropiedad = FXCollections.observableArrayList();
        for(TipoPropiedad tipo : tipoPropiedades){
            tiposDePropiedad.add(tipo.getTipo());
        }
        cmb_TipoDePropiedad.setItems(tiposDePropiedad);
        ObservableList<String> estadosPropiedad = FXCollections.observableArrayList();
        for(EnumPropiedad propiedad : EnumPropiedad.values()){
            estadosPropiedad.add(propiedad.toString());
        }
        cmb_EstadoPropiedad.setItems(estadosPropiedad);
    }
     
     private boolean validarDatos(){
        boolean resultado = true;
        Propiedad propiedad = new Propiedad();
        Direccion direccion = new Direccion();
        try{
            propiedad.setAntiguedad(spn_Antiguedad.getValue());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorAntiguedad.setVisible(true);
           resultado = false;
        }
        try{
            propiedad.setEstadoPropiedad((String) cmb_EstadoPropiedad.getSelectionModel().getSelectedItem());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorTipoDeEstado.setVisible(true);
           resultado = false;
        }
        try{
             propiedad.setMetrosDeTerreno(Float.parseFloat(txfd_MetrosDeTerreno.getText()));
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorMetrosDeTerreno.setVisible(true);
           resultado = false;
        }
        try{
             propiedad.setMetrosDeTerreno(Float.parseFloat(txfd_MetrosDeTerreno.getText()));
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorMetrosDeTerreno.setVisible(true);
           resultado = false;
        }
        try{
            propiedad.setNumeroDeBanios(spn_NoBanios.getValue());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorNoBanios.setVisible(true);
           resultado = false;
        }
        try{
            propiedad.setNumeroDeHabitaciones(spn_NoHabitaciones.getValue());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorNoHabitaciones.setVisible(true);
           resultado = false;
        }
        try{
             String tipoDePropiedad = (String)cmb_TipoDePropiedad.getSelectionModel().getSelectedItem();
        }catch(NullPointerException | IllegalArgumentException excepcion){
            LOG.warn(excepcion);
            lbl_ErrorTipoDePropiedad.setVisible(true);
            resultado = false;
        }
        try{
            propiedad.setNumeroDePisos(spn_NoPisos.getValue());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorNombre.setVisible(true);
           resultado = false;
        }
        try{
            propiedad.setPrecio(txfd_Precio.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorPrecio.setVisible(true);
           resultado = false;
        }
        try{
            propiedad.setNombre(txfd_Nombre.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorNombre.setVisible(true);
           resultado = false;
        }
        try{
            direccion.setCalle(txfd_Calle.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorCalle.setVisible(true);
           resultado = false;
        }
        try{
           direccion.setCiudad(txfd_Ciudad.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorCiudad.setVisible(true);
           resultado = false;
        }
        try{
            direccion.setCodigoPostal(txfd_CodigoPostal.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorCodigoPostal.setVisible(true);
           resultado = false;
        }
        try{
            direccion.setColonia(txfd_Colonia.getText());
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorColonia.setVisible(true);
           resultado = false;
        }
        try{
            String estado = (String)cmb_Estado.getSelectionModel().getSelectedItem();
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorEstado.setVisible(true);
           resultado = false;
        }
        if(txfd_RFCPropietario.getText().matches(RFC_PATTERN)){
            resultado = true;
        }else{
            lbl_ErrorRFC.setVisible(true);
        }
        return resultado;
     }
     
    private Propiedad obtenerDatosPropiedad(){
        DAOPropietario daoPropietario = new DAOPropietario();
        DAOUsuario daoUsuario = new  DAOUsuario();
        Propiedad propiedad = new Propiedad();
        Direccion direccion = new Direccion();
        Propietario propietario = new Propietario();
        Usuario usuario = new Usuario();
        PropiedadAuxiliar propiedadAuxiliar = PropiedadAuxiliar.getInstancia();
        propiedad.setIdPropiedad(propiedadAuxiliar.getIdPropiedad());
        propiedad.setAntiguedad(spn_Antiguedad.getValue());
        propiedad.setEstadoPropiedad((String) cmb_EstadoPropiedad.getSelectionModel().getSelectedItem());
        propiedad.setMetrosDeTerreno(Float.parseFloat(txfd_MetrosDeTerreno.getText()));
        propiedad.setNumeroDeBanios(spn_NoBanios.getValue());
        propiedad.setNumeroDeHabitaciones(spn_NoHabitaciones.getValue());
        propiedad.setNumeroDePisos(spn_NoPisos.getValue());
        propiedad.setPrecio(txfd_Precio.getText());
        propiedad.setNombre(txfd_Nombre.getText());
        direccion.setIdDireccion(propiedadAuxiliar.getDireccion().getIdDireccion());
        direccion.setCalle(txfd_Calle.getText());
        direccion.setCiudad(txfd_Ciudad.getText());
        direccion.setCodigoPostal(txfd_CodigoPostal.getText());
        direccion.setColonia(txfd_Colonia.getText());
        usuario = daoUsuario.consultarUsuarioPorRFC(txfd_RFCPropietario.getText());
        if (Objects.nonNull(usuario)) {
            propietario = daoPropietario.consultarPropietarioPorIDUsuario(usuario.getIdUsuario());
            propiedad.setTipoDePropiedad(obtenerObjectoTipoPropiedad());
            propiedad.setDireccion(direccion);
            propiedad.setDireccion(obtenerUbicacion(propiedad.getDireccion()));
            propiedad.setPropietario(propietario);
        } else {
            propiedad = null;
        }
        return propiedad;
    }
    
    public TipoPropiedad obtenerObjectoTipoPropiedad(){
        TipoPropiedad tipoDePropiedad = new TipoPropiedad();
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        tipoDePropiedad = daoTipoPropiedad.consultarTiposPropiedadPorTipo((String)cmb_TipoDePropiedad.getSelectionModel().getSelectedItem());
        return tipoDePropiedad;
    }
    
    public Direccion obtenerUbicacion(Direccion direccion){
        DAOUbicacion daoubicacion = new DAOUbicacion();
        direccion.setUbicacion(daoubicacion.consultarUbicacionPorEstado((String)cmb_Estado.getSelectionModel().getSelectedItem()));
        return direccion;
    }
    
    public Propiedad obtenerDatosPropiedadAAuxiliar(){
        PropiedadAuxiliar propiedadAux = PropiedadAuxiliar.getInstancia();
        Propiedad propiedad = new Propiedad();
        propiedad.setAntiguedad(propiedadAux.getAntiguedad());
        propiedad.setDireccion(propiedadAux.getDireccion());
        propiedad.setEstadoPropiedad(propiedadAux.getEstadoPropiedad());
        propiedad.setIdPropiedad(propiedadAux.getIdPropiedad());
        propiedad.setMetrosDeTerreno(propiedadAux.getMetrosDeTerreno());
        propiedad.setNombre(propiedadAux.getNombre());
        propiedad.setNumeroDeBanios(propiedadAux.getNumeroDeBanios());
        propiedad.setNumeroDeHabitaciones(propiedadAux.getNumeroDeHabitaciones());
        propiedad.setNumeroDePisos(propiedadAux.getNumeroDePisos());
        propiedad.setPrecio(propiedadAux.getPrecio());
        propiedad.setTipoDePropiedad(propiedadAux.getTipoDePropiedad());
        propiedad.setPropietario(propiedadAux.getPropietario());
        return propiedad;
    }
    
    public void modificarDatos(Propiedad propiedadNueva){
        DAOPropiedad daoPropiedad = new DAOPropiedad();
        DAODireccion daoDireccion = new DAODireccion();
        Direccion direccion = propiedadNueva.getDireccion();
        int numModificaciones = daoPropiedad.modificarAntiguedadPropiedad(propiedadNueva, propiedadNueva.getAntiguedad());
        numModificaciones += daoPropiedad.modificarMetrosDeTerreno(propiedadNueva, propiedadNueva.getMetrosDeTerreno());
        numModificaciones += daoPropiedad.modificarNombrePropiedad(propiedadNueva, propiedadNueva.getNombre());
        numModificaciones += daoPropiedad.modificarNumeroDeBaniosPropiedad(propiedadNueva, propiedadNueva.getNumeroDeBanios());
        numModificaciones += daoPropiedad.modificarNumeroDePisosPropiedad(propiedadNueva, propiedadNueva.getNumeroDePisos());
        numModificaciones += daoPropiedad.modificarPrecioTerreno(propiedadNueva, propiedadNueva.getPrecio());
        numModificaciones += daoPropiedad.modificarNumeroHabitacionesPropiedad(propiedadNueva, propiedadNueva.getNumeroDeHabitaciones());
        numModificaciones += daoDireccion.modificarCalle(direccion,direccion.getCalle());
        numModificaciones += daoDireccion.modificarCiudad(direccion, direccion.getCiudad());
        numModificaciones += daoDireccion.modificarCodigoPostal(direccion, direccion.getCodigoPostal());
        numModificaciones += daoDireccion.modificarColonia(direccion, direccion.getColonia());
        numModificaciones += daoDireccion.modificarEstado(direccion, direccion.getUbicacion().getEstado());
        numModificaciones += daoPropiedad.modificarPropietario(propiedadNueva, propiedadNueva.getPropietario().getIdPropietario());
        numModificaciones += daoPropiedad.modificarTipoDePropiedad(propiedadNueva, propiedadNueva.getTipoDePropiedad().getIdTipoPropiedad());
        numModificaciones += daoPropiedad.moodificarEstadoPopiedad(propiedadNueva, propiedadNueva.getEstadoPropiedad());
        if(numModificaciones==15){
            Alertas.mostrarMensajeDatosModificados();
            regresarDeVentana();
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
        
    }
    
    public void validarModificacion(){
       boolean resultadoValidacion = validarDatos();
       Propiedad propiedadAntigua = obtenerDatosPropiedadAAuxiliar();
       if(resultadoValidacion){
           Propiedad propiedadNueva = obtenerDatosPropiedad();
        if(Objects.nonNull(propiedadNueva)){
            if(!propiedadNueva.equals(propiedadAntigua)){
                if (Objects.nonNull(propiedadNueva)) {
                    if(!Objects.isNull(propiedadNueva.getPropietario().getUsuario())){
                        modificarDatos(propiedadNueva);
                    } else {
                        Alertas.mostrarMensajeRFCoNoEncontrado();
                    }
                }else{
                    Alertas.mostrarMensajeDatosInvalidos();
                }
            }else{
                Alertas.mostrarMensajeDatosSinModificar();
            }    
            }else{
                Alertas.mostrarMensajeErrorEnLaConexion();
            }
       }else{
           Alertas.mostrarMensajeDatosInvalidos();
       }
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
    }
    
    public void regresarDeVentana(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_Propiedades.fxml";
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
}
