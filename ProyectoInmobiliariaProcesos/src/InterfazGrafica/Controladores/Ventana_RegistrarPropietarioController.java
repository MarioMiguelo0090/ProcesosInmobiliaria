package InterfazGrafica.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.Clases.Usuario;

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
        String rfc = txfd_RFC.getText().trim();
        String nombre = txfd_Nombre.getText().trim();
        String apellidoP = txfd_ApellidoP.getText().trim();
        String apellidoM = txfd_ApellidoM.getText().trim();
        String telefono = txfd_Telefono.getText().trim();
        String correo = txfd_Correo.getText().trim();

        Usuario usuario = new Usuario();

        Propietario propietario = new Propietario();

        DAOPropietario daoPropietario = new DAOPropietario();
        int resultado = daoPropietario.agregarNuevoPropietario(propietario);

        if (resultado > 0) {
            System.out.println("Propietario registrado correctamente.");
            // Alert exitoso
        } else {
            // Error al guardar el propietario
            //alert fallido
            // Aquí puedes manejar el error, mostrar un mensaje de error o realizar alguna otra acción necesaria
        }
    }

    @FXML
    private void regresarDeVentana() {
        // Aquí puedes definir la acción a realizar al presionar el botón de cancelar
        // Por ejemplo, cerrar la ventana actual
    }
}
