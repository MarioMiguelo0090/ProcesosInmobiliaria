package InterfazGrafica.Controladores;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventana_RegistrarPropietarioController implements Initializable {

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

        Propietario propietario = new Propietario();
        propietario.setUsuario(usuario);

        DAOPropietario daoPropietario = new DAOPropietario();
        int resultado = daoPropietario.agregarNuevoPropietario(propietario);

        if (resultado > 0) {
            mostrarAlerta("Éxito", "El propietario se ha registrado correctamente.");
        } else {
            mostrarAlerta("Error", "Ha ocurrido un error al intentar registrar el propietario.");
        }
    }

    @FXML
    private void regresarDeVentana() {
        Stage stage = (Stage) btn_Cancelar.getScene().getWindow();
        stage.close();     
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana_ConsultarPropietarios.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la ventana ActualizarPropietarioController.");
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
