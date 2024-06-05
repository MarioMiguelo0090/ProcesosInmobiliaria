package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOAgenteInmobiliario;
import logicaDeNegocio.DAO.DAOUsuario;


public class Ventana_ConsultarAgenteControlador implements Initializable{
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_ConsultarAgenteControlador.class);
        
    @FXML private TableColumn<Usuario, String> column_Nombre;
    @FXML private TableColumn<Usuario, String> column_ApellidoMaterno;
    @FXML private TableColumn<Usuario, String> column_ApellidoPaterno;
    @FXML private TableColumn<Usuario, String> column_Correo;
    @FXML private TableColumn<Usuario, String> column_Telefono;
    @FXML private TableColumn<Usuario, String> column_RFC;
    @FXML private TableColumn column_Modificar;
    @FXML private TableView<Usuario> tableView_Agentes;
    @FXML private TextField textField_BuscarAgente;
    @FXML private Button button_Buscar;
    @FXML private Button button_RegistrarAgente;
    @FXML private Button button_Regresar;
    @FXML private AnchorPane pane_Principal;
    private Stage escenario;
    
    private void aplicarValidacion(TextField textField, String expresionRegular) {
        UnaryOperator<TextFormatter.Change> filtro = cambio -> {
            String nuevoTexto = cambio.getControlNewText();
            return (nuevoTexto.matches(expresionRegular) || nuevoTexto.isEmpty()) ? cambio : null;
        };

        textField.setTextFormatter(new TextFormatter<>(filtro));
        button_Regresar.setOnAction(event->button_Regresar());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aplicarValidacion(textField_BuscarAgente, "^[\\p{L}áéíóúÁÉÍÓÚüÜ\\s',;\\-_:\\.]{1,200}$");
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        column_Telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        column_RFC.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        List<Usuario> usuarios=obtenerAgentes();
        tableView_Agentes.getItems().addAll(usuarios);
        agregarBoton();
    }
    
    public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }

    
    public List<Usuario> obtenerAgentes(){
        DAOAgenteInmobiliario daoAgenteInmobiliario=new DAOAgenteInmobiliario();
            
            List<Usuario> usuarios=new ArrayList<>();
        try {
            List<Integer> idUsuarios=daoAgenteInmobiliario.consultarIdUsuarioDeAgentesActivos();
            DAOUsuario daoUsuario=new DAOUsuario();
            for(Integer idUsuario : idUsuarios){            
                usuarios.add(daoUsuario.consultarUsuarioPorId(idUsuario));
            }
            
        } catch (SQLException ex) {
            Alertas.mostrarMensajeErrorEnLaConexion();
            LOG.error(ex);
        }
        return usuarios;
    }
    
         private void agregarBoton() {        
        Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> cellFactory = (final TableColumn<Usuario, Void> param) -> {
            final TableCell<Usuario, Void> cell = new TableCell<Usuario, Void>() {                
                private final Button btn_Modificar = new Button();                                
                {
                    btn_Modificar.setText("Modificar");
                    btn_Modificar.setBackground(new Background(new BackgroundFill(Color.web("#3498db"), new CornerRadii(5), null)));                    
                    btn_Modificar.setTextFill(Color.WHITE);
                    btn_Modificar.setOnAction((ActionEvent event) -> {
                        Usuario usuario=getTableView().getItems().get(getIndex());
                        int idUsuario=usuario.getIdUsuario();
                        desplegarVentanaDetallesDeCliente(idUsuario);
                       
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_Modificar);
                    }
                }
            };
            return cell;
        };
        column_Modificar.setCellFactory(cellFactory);       
    }
         
public void desplegarVentanaDetallesDeCliente(int idUsuario) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_ModificarAgente.fxml"));
        Parent root = loader.load();
        Ventana_ModificarAgenteControlador controlador = loader.getController();
        Stage stage = new Stage();
        controlador.inicializar(idUsuario, stage);
        stage.setScene(new Scene(root));
        stage.show();
        cerrarVentana();
    } catch (IOException excepcion) {
        LOG.error(excepcion);
        System.out.println("Error de conexion de BD");
    }
}

    
    public void button_RegistrarAgente(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_RegistrarAgente.fxml"));
        Parent root = loader.load();
        Ventana_RegistrarAgenteControlador controlador = loader.getController();
        Stage stage = new Stage();
        controlador.inicializar(stage);
        stage.setScene(new Scene(root));
        stage.show();
        cerrarVentana();
    } catch (IOException excepcion) {
        LOG.error(excepcion);
        System.out.println("Error de conexion de BD");
    }
    }
   
    public void cerrarVentana(){
        escenario = (Stage)pane_Principal.getScene().getWindow();
        escenario.close();
    }
    
    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentanaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            Object controlador = loader.getController();
            if (controlador instanceof Ventana_MenuPrincipalAdministradorControlador) {
                Ventana_MenuPrincipalAdministradorControlador MenuPrincipalAdministradorControlador = (Ventana_MenuPrincipalAdministradorControlador) controlador;
                MenuPrincipalAdministradorControlador.inicializar(stage);
            }

            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
    }
    
    public void button_Regresar(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_MenuPrincipalAdministrador.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
        cerrarVentana();
    }
    
    @FXML
    private void button_Buscar(ActionEvent event) {
        String criterioBusqueda = textField_BuscarAgente.getText();
        if (criterioBusqueda.isEmpty()) {
            Alertas.mostrarMensajeIngresarCriterios();
            return;
        }
        buscarAgentePorNombre(criterioBusqueda);
    }
    
    private void buscarAgentePorNombre(String criterioBusqueda) {
        try {
            DAOAgenteInmobiliario daoAgenteInmobiliario = new DAOAgenteInmobiliario();
            
            List<Usuario> agentesEncontrados = daoAgenteInmobiliario.obtenerListaAgentesPorNombre(criterioBusqueda);
            actualizarTablaConAgentesEncontrados(agentesEncontrados);
            
            
            
        } catch (SQLException ex) {
            LOG.error(ex);
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
        
    }
    
    private void actualizarTablaConAgentesEncontrados(List<Usuario> agentesEncontrados) {
        if (agentesEncontrados.isEmpty()) {
            Alertas.mostrarMensajeSinResultados();
            return;
        }
        ObservableList<Usuario> items = FXCollections.observableArrayList(agentesEncontrados);
        tableView_Agentes.setItems(items);
    }

}
