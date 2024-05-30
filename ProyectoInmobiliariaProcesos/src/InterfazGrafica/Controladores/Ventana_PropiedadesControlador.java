package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.LoginSingleton;
import logicaDeNegocio.Clases.Propiedad;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.ClasesAuxiliares.PropiedadAuxiliar;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOLogin;
import logicaDeNegocio.DAO.DAOPropiedad;
import logicaDeNegocio.DAO.DAOTipoPropiedad;
import logicaDeNegocio.DAO.DAOUbicacion;


public class Ventana_PropiedadesControlador implements Initializable {

    private Stage escenario;
    @FXML
    private AnchorPane pane_Principal;
    @FXML
    private TextField txfd_EstadoPropiedad;
    @FXML
    private TableView<Propiedad> tableView_Propiedades;
    @FXML
    private TableColumn<Propiedad, Integer> column_idPropiedad;
    @FXML
    private TableColumn<Propiedad, String> column_Nombre;
    @FXML
    private TableColumn<Propiedad, String> column_TipoDePropiedad;
    @FXML
    private TableColumn<Propiedad, Float> column_Metros;
    @FXML
    private TableColumn<Propiedad, Integer> column_Habitaciones;
    @FXML
    private TableColumn<Propiedad, Integer> column_Pisos;
    @FXML
    private TableColumn<Propiedad, Integer> column_Banios;
    @FXML
    private TableColumn<Propiedad, String> column_EstadoPropiedad;
    @FXML
    private TableColumn<Propiedad, String> column_Calle;
    @FXML
    private TableColumn<Propiedad, String> column_Precio;
    @FXML
    private TableColumn<Propiedad, String> column_CodigoPostal;
    @FXML
    private TableColumn<Propiedad, String> column_Ciudad;
    @FXML
    private TableColumn<Propiedad, String> column_Estado;
    @FXML
    private TableColumn<Propiedad, String> column_Propietario;
    @FXML
    private TableColumn<Propiedad, Void> column_Modificar;
    @FXML
    private Button btn_RegistrarPropiedad;
    @FXML
    private ComboBox<String> cmb_tipoDePropiedad;
    @FXML
    private Button btn_Regresar;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboboxs();
        mostrarPropiedades();
        asignarBotonesDeModificarPropiedad();
        DAOLogin daoLogin = new DAOLogin();
        LoginSingleton login = LoginSingleton.getInstance();
        Login loginUsuario = daoLogin.obtenerLoginPorIdUsuario(login.getIdUsuario());
        if(loginUsuario.getTipoUsuario().equals("Cliente")){
            column_Modificar.setVisible(false);
            btn_RegistrarPropiedad.setVisible(false);
        }
        btn_Regresar.setOnAction(event->regresarVentanaPrincipal());
        btn_RegistrarPropiedad.setOnAction(event->registrarPropiedad());
    }    
    
    public void cargarComboboxs(){
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        List<TipoPropiedad> tipoPropiedades = new ArrayList();
        tipoPropiedades = daoTipoPropiedad.consultarTiposPropiedad();
        ObservableList<String> tiposDePropiedad = FXCollections.observableArrayList();
        for(TipoPropiedad tipo : tipoPropiedades){
            tiposDePropiedad.add(tipo.getTipo());
        }
        cmb_tipoDePropiedad.setItems(tiposDePropiedad);
    }
    
    public void mostrarPropiedadesPorEstado(){
        String estado = txfd_EstadoPropiedad.getText();
        if(estado!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(estado))){
            DAOUbicacion daoUbicacion = new DAOUbicacion();
            Ubicacion ubicacion = daoUbicacion.consultarUbicacionPorEstado(estado);
            if(ubicacion.getEstado()!=null){
                DAOPropiedad daoPropiedad = new DAOPropiedad();
                List<Propiedad> propiedadesObtenidas = daoPropiedad.consultarPropiedadPorUbicacion(estado);
                if(!propiedadesObtenidas.isEmpty()){
                    mostrarPropiedades(propiedadesObtenidas);
                }else{
                    Alertas.mostrarMensajeNoSeHanEncontradoPropiedades("No se han encontrado propiedades registradas de "+estado);
                }
            }else{
                Alertas.mostrarMensajeDatosInvalidos();
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }
    
    public void mostrarPropiedadesPorFiltro(){
        String seleccion = (String) cmb_tipoDePropiedad.getSelectionModel().getSelectedItem();
        DAOPropiedad daoPropiedad = new DAOPropiedad();
        List<Propiedad> propiedadesObtenidas = daoPropiedad.consultarPropiedadPorTipo(seleccion);
        if(!propiedadesObtenidas.isEmpty()){
            mostrarPropiedades(propiedadesObtenidas);
        }else{
            Alertas.mostrarMensajeNoSeHanEncontradoPropiedades("No se han encontrado propiedades de tipo "+seleccion+" registradas");
        }
    }
    
    public void mostrarPropiedades(){
        DAOPropiedad daoPropiedad = new DAOPropiedad();
        List<Propiedad> propiedadesObtenidas = daoPropiedad.consultarPropiedades();
        if(!propiedadesObtenidas.isEmpty()){
            mostrarPropiedades(propiedadesObtenidas);
        }else{
            Alertas.mostrarMensajeNoSeHanEncontradoPropiedades("No se han encontrado propiedades registradas");
        }
    }
    
    public void mostrarPropiedades(List<Propiedad> propiedades){
        tableView_Propiedades.getItems().clear();
        try{
            tableView_Propiedades.getItems().addAll(propiedades);
            column_idPropiedad.setCellValueFactory(new PropertyValueFactory<>("idPropiedad"));
            column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            column_Metros.setCellValueFactory(new PropertyValueFactory<>("metrosDeTerreno"));
            column_Habitaciones.setCellValueFactory(new PropertyValueFactory<>("numeroDeHabitaciones"));
            column_Pisos.setCellValueFactory(new PropertyValueFactory<>("numeroDePisos"));
            column_Banios.setCellValueFactory(new PropertyValueFactory<>("numeroDeBanios"));
            column_EstadoPropiedad.setCellValueFactory(new PropertyValueFactory<>("estadoPropiedad"));
            column_Precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            column_TipoDePropiedad.setCellValueFactory(cellData->{
                    Propiedad propiedad = cellData.getValue();
                    String tipoDePropiedad = propiedad.getTipoDePropiedad().getTipo();
                    return new SimpleStringProperty(tipoDePropiedad);
            });
            column_Calle.setCellValueFactory(cellData->{
                    Propiedad propiedad = cellData.getValue();
                    String calle = propiedad.getDireccion().getCalle();
                    return new SimpleStringProperty(calle);
            });
            column_CodigoPostal.setCellValueFactory(cellData->{
                    Propiedad propiedad = cellData.getValue();
                    String codigoPostal = propiedad.getDireccion().getCodigoPostal();
                    return new SimpleStringProperty(codigoPostal);
            });
            column_Ciudad.setCellValueFactory(cellData->{
                    Propiedad propiedad = cellData.getValue();
                    String ciudad = propiedad.getDireccion().getCiudad();
                    return new SimpleStringProperty(ciudad);
            });
            column_Estado.setCellValueFactory(cellData->{  
                    Propiedad propiedad = cellData.getValue();
                    String estado = propiedad.getDireccion().getUbicacion().getEstado();
                    return new SimpleStringProperty(estado);
            });
            column_Propietario.setCellValueFactory(cellData->{
                Propiedad propiedad = cellData.getValue();
                String nombre = propiedad.getPropietario().getUsuario().getNombre();
                String apellidoPaterno = propiedad.getPropietario().getUsuario().getApellidoPaterno();
                String apellidoMaterno = propiedad.getPropietario().getUsuario().getApellidoMaterno();
                String nombreCompleto = nombre+" "+apellidoPaterno+" "+apellidoMaterno;
                return new SimpleStringProperty(nombreCompleto);
            });
            
        }catch(IllegalArgumentException excepcion){
            
        }
    }
    
    public void asignarBotonesDeModificarPropiedad(){
        Callback<TableColumn<Propiedad, Void>, TableCell<Propiedad, Void>> frabricaDeCelda = (final TableColumn<Propiedad, Void> param) -> {
                final TableCell<Propiedad, Void> cell = new TableCell<Propiedad, Void>() {                
                    private final Button btn_Modificar = new Button();{
                        btn_Modificar.setText("Actualizar perfil");
                        btn_Modificar.setOnAction((ActionEvent event) -> {
                            Propiedad propiedadSeleccionada = getTableView().getItems().get(getIndex());
                            PropiedadAuxiliar.setInstancia(propiedadSeleccionada);
                            String ruta = "/InterfazGrafica/Ventana_ModificarPropiedad.fxml";
                            desplegarVentanaCorrespondiente(ruta);
                        });
                    }                
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        }else{ 
                            setGraphic(btn_Modificar);
                        }
                    }
                };
            return cell;
            };
            column_Modificar.setCellFactory(frabricaDeCelda);
    }
    
    public void registrarPropiedad(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_RegistrarPropiedad.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
    
    public void regresarVentanaPrincipal(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_MenuPrincipalAdministrador.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }
   
    public void cerrarVentana(){
        escenario = (Stage)pane_Principal.getScene().getWindow();
        escenario.close();
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
}
