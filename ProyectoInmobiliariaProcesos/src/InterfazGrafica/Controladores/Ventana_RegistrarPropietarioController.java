package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.Clases.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.DAO.DAOCliente;

public class Ventana_RegistrarPropietarioController implements Initializable {
    
    private Stage escenario;
    @FXML
    private AnchorPane pane_Principal;
    @FXML
    private TextField txfd_RFC;
    @FXML
    private TextField txfd_Nombre;
    @FXML
    private TextField txfd_ApellidoP;
    @FXML
    private TextField txfd_ApellidoM;
    @FXML
    private TextField txfd_Telefono;
    @FXML
    private TextField txfd_Correo;
    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void realizarRegistro() {
        Stage stage = (Stage) btn_Guardar.getScene().getWindow();
        String rfc = txfd_RFC.getText().trim();
        String nombre = txfd_Nombre.getText().trim();
        String apellidoP = txfd_ApellidoP.getText().trim();
        String apellidoM = txfd_ApellidoM.getText().trim();
        String telefono = txfd_Telefono.getText().trim();
        String correo = txfd_Correo.getText().trim();

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidoPaterno(apellidoP);
        usuario.setApellidoMaterno(apellidoM);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setRFC(rfc);

        

        DAOPropietario daoPropietario = new DAOPropietario();
        int resultado = daoPropietario.agregarNuevoPropietario(usuario);

        if (resultado > 0) {
            Alertas.propietarioRegistradoCorrectamente();    
        } else {
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }

    public void regresarDeVentana(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_ConsultarPropietarios.fxml";
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
