package InterfazGrafica.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAO.DAOCliente;

public class Ventana_MenuPrincipalAdministradorControlador implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_MenuPrincipalAdministradorControlador.class);

    private Stage escenario;
    @FXML
    private AnchorPane pane_Principal;
    @FXML
    private Button btn_GestionPropiedad;
    @FXML
    private Button btn_GestionAgente;
    @FXML
    private Button btn_GestionPropietario;
    @FXML
    private Button btn_ConsultarCliente;
    @FXML
    private Button btn_RegistrarCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_GestionPropiedad.setOnAction(event->abrirVentanaPropiedades());
        btn_GestionAgente.setOnAction(event->abrirVentanaAgentes());
        btn_ConsultarCliente.setOnAction(event->abrirVentanaConsultarClientes());
        btn_RegistrarCliente.setOnAction(event->abrirVentanaRegistrarCliente());
        btn_GestionPropietario.setOnAction(event->abrirVentanaPropietarios());
    }
    
        public void inicializar(Stage stage) {
        this.escenario = stage;
        escenario.setOnCloseRequest(event -> {
            event.consume();
        });
    }
    
    public void abrirVentanaPropiedades(){
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_Propiedades.fxml"));
        Parent root = loader.load();
        Ventana_PropiedadesControlador controlador = loader.getController();
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
    
    public void abrirVentanaPropietarios(){
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_ConsultarPropietarios.fxml"));
        Parent root = loader.load();
        Ventana_ConsultarPropietariosController controlador = loader.getController();
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
    
    public void abrirVentanaAgentes(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_ConsultarAngenteInmobiliario.fxml"));
        Parent root = loader.load();
        Ventana_ConsultarAgenteControlador controlador = loader.getController();
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
    
    public void abrirVentanaRegistrarCliente(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_DarDeAltaCliente.fxml"));
        Parent root = loader.load();
        Ventana_DarDeAltaClienteController controlador = loader.getController();
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
    
    public void abrirVentanaConsultarClientes(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_ConsultaDeClientes.fxml"));
        Parent root = loader.load();
        Ventana_ConsultaDeClientesController controlador = loader.getController();
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
            System.out.println(excepcion.getMessage());
        }
    }

}

