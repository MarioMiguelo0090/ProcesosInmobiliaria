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
    
    public void abrirVentanaPropiedades(){
         String rutaVentanaFXML="/interfazGrafica/Ventana_Propiedades.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    public void abrirVentanaPropietarios(){
         String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultarPropietarios.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    public void abrirVentanaAgentes(){
         String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultarAngenteInmobiliario.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    public void abrirVentanaRegistrarCliente(){
         String rutaVentanaFXML="/interfazGrafica/Ventana_DarDeAltaCliente.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML);
    }
    
    public void abrirVentanaConsultarClientes(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultaDeClientes.fxml";
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
            LOG.error(excepcion);
        }
    }
}

