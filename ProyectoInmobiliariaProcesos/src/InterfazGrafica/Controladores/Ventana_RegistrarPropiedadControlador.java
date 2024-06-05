package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Direccion;
import logicaDeNegocio.Clases.Propiedad;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAODireccion;
import logicaDeNegocio.DAO.DAOPropiedad;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;
import logicaDeNegocio.DAO.DAOUsuario;
import logicaDeNegocio.Enums.EnumPropiedad;

public class Ventana_RegistrarPropiedadControlador implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_RegistrarPropiedadControlador.class);
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
    @FXML
    private Button btn_Guardar;
    private static final String RFC_PATTERN = "^[A-ZÑ&]{3,4}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[A-Z\\d]{3}$";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboboxs();
        SpinnerValueFactory<Integer> valueFactoryAntiguedad = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0);
        spn_Antiguedad.setValueFactory(valueFactoryAntiguedad);
        SpinnerValueFactory<Integer> valueFactoryBanios = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        spn_NoBanios.setValueFactory(valueFactoryBanios);
        SpinnerValueFactory<Integer> valueFactoryHabitaciones = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        spn_NoHabitaciones.setValueFactory(valueFactoryHabitaciones);
        SpinnerValueFactory<Integer> valueFactoryPisos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        spn_NoPisos.setValueFactory(valueFactoryPisos);
    }    
    
        public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }
    
    public void cerrarVentana(){
        escenario = (Stage)anchor_Ventana.getScene().getWindow();
        escenario.close();
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
             if(tipoDePropiedad.isEmpty()){
                resultado = true;
             }
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
            if(estado.isEmpty()){
                resultado = true;
             }
        }catch(NullPointerException | IllegalArgumentException excepcion){
           LOG.warn(excepcion);
           lbl_ErrorEstado.setVisible(true);
           resultado = false;
        }
        if(Pattern.matches(RFC_PATTERN, txfd_RFCPropietario.getText())){
            resultado = true;
        }else{
            lbl_ErrorRFC.setVisible(true);
            resultado = false;
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
        propiedad.setAntiguedad(spn_Antiguedad.getValue());
        propiedad.setEstadoPropiedad((String) cmb_EstadoPropiedad.getSelectionModel().getSelectedItem());
        propiedad.setMetrosDeTerreno(Float.parseFloat(txfd_MetrosDeTerreno.getText()));
        propiedad.setNumeroDeBanios(spn_NoBanios.getValue());
        propiedad.setNumeroDeHabitaciones(spn_NoHabitaciones.getValue());
        propiedad.setNumeroDePisos(spn_NoPisos.getValue());
        propiedad.setPrecio(txfd_Precio.getText());
        propiedad.setNombre(txfd_Nombre.getText());
        direccion.setCalle(txfd_Calle.getText());
        direccion.setCiudad(txfd_Ciudad.getText());
        direccion.setCodigoPostal(txfd_CodigoPostal.getText());
        direccion.setColonia(txfd_Colonia.getText());
        usuario = daoUsuario.consultarUsuarioPorRFC(txfd_RFCPropietario.getText());
        propietario = daoPropietario.consultarPropietarioPorIDUsuario(usuario.getIdUsuario());
        propiedad.setTipoDePropiedad(obtenerObjectoTipoPropiedad());
        propiedad.setDireccion(direccion);
        propiedad.setDireccion(obtenerUbicacion(propiedad.getDireccion()));
        propiedad.setPropietario(propietario);
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
    
    @FXML
    public void realizarRegistro(ActionEvent event){
        if(validarDatos()){
            Propiedad propiedadNueva = obtenerDatosPropiedad();
            if (Objects.nonNull(propiedadNueva)) {
                if(!Objects.isNull(propiedadNueva.getPropietario().getUsuario())){
                    DAODireccion daoDireccion = new DAODireccion();
                    int resultadoInsercionDireccion = daoDireccion.insertarDireccion(propiedadNueva.getDireccion());
                    if (resultadoInsercionDireccion != -1) {
                        propiedadNueva.setDireccion(daoDireccion.ObtenerUltimaDireccionRegistrada());
                        DAOPropiedad daoPropiedad = new DAOPropiedad();
                        int resultadoInsercion = daoPropiedad.registrarPropiedad(propiedadNueva);
                        if (resultadoInsercion != -1) {
                            Alertas.mostrarMensajeDatosIngresados();
                            regresarDeVentana();
                        } else {
                            Alertas.mostrarMensajeErrorEnLaConexion();
                        }
                    } else {
                        Alertas.mostrarMensajeErrorEnLaConexion();
                    }
                } else {
                    Alertas.mostrarMensajeRFCoNoEncontrado();
                }
            }else{
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
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
