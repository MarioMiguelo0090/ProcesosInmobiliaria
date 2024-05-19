package InterfazGrafica.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
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
    private Button btn_Cancelar;
    @FXML
    private Button btn_Guardar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosPropiedad();
        cargarComboboxs();
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
     
    private Propiedad obtenerDatosPropiedad(){
        DAOPropietario daoPropietario = new DAOPropietario();
        DAOUsuario daoUsuario = new  DAOUsuario();
        Propiedad propiedad = new Propiedad();
        Direccion direccion = new Direccion();
        Propietario propietario = new Propietario();
        Usuario usuario = new Usuario();
        PropiedadAuxiliar propiedadAuxiliar = PropiedadAuxiliar.getInstancia();
        try{
            propiedad.setIdPropiedad(propiedadAuxiliar.getIdPropiedad());
            propiedad.setAntiguedad(spn_Antiguedad.getValue());
            propiedad.setEstadoPropiedad((String) cmb_EstadoPropiedad.getSelectionModel().getSelectedItem());
            propiedad.setMetrosDeTerreno(Float.parseFloat(txfd_MetrosDeTerreno.getText()));
            propiedad.setNumeroDeBanios(spn_NoBanios.getValue());
            propiedad.setNumeroDeHabitaciones(spn_NoHabitaciones.getValue());
            propiedad.setNumeroDePisos(spn_NoPisos.getValue());
            propiedad.setPrecio(Float.parseFloat(txfd_Precio.getText()));
            propiedad.setNombre(txfd_Nombre.getText());
            direccion.setIdDireccion(propiedadAuxiliar.getDireccion().getIdDireccion());
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
        }catch(NullPointerException | IllegalArgumentException excepcion){
           Logger.getLogger(Ventana_RegistrarPropiedadControlador.class.getName()).log(Level.SEVERE, null, excepcion);
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
            System.out.println(numModificaciones);
        }
        
    }
    
    public void validarModificacion(){
       Propiedad propiedadNueva = obtenerDatosPropiedad();
       Propiedad propiedadAntigua = obtenerDatosPropiedadAAuxiliar();
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
            Logger.getLogger(Ventana_ModificarPropiedadControlador.class.getName()).log(Level.SEVERE, null, excepcion);
        }
    }
}
