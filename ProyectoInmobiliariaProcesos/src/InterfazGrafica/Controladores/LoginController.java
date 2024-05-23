package InterfazGrafica.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logicaDeNegocio.DAO.DAOUsuario;
import logicaDeNegocio.Clases.Usuario;

public class LoginController {

    @FXML
    private TextField txfd_Usuario;

    @FXML
    private PasswordField txfd_Contrasena;

    @FXML
    private Button btn_Ingresar;

    @FXML
    private Label lbl_Mensaje;

    // Método para manejar el evento del botón de ingresar
    @FXML
    void ingresar(MouseEvent event) {
        String usuario = txfd_Usuario.getText();
        String contrasena = txfd_Contrasena.getText();

        // Verificar si el usuario y la contraseña son válidos
        if (validarCredenciales(usuario, contrasena)) {
            // Aquí iría el código para abrir la siguiente ventana del sistema
            // Por ejemplo, podrías cambiar a la ventana principal del sistema
            // Utilizando un FXMLLoader y cambiando de escena
            System.out.println("Credenciales válidas. Ingresando al sistema...");
        } else {
            // Mostrar un mensaje de error si las credenciales son incorrectas
            lbl_Mensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    // Método para validar las credenciales del usuario
    private boolean validarCredenciales(String usuario, String contrasena) {
        // Aquí iría la lógica para verificar las credenciales en la base de datos
        // Por ejemplo, puedes utilizar el DAOUsuario para buscar el usuario en la base de datos
        DAOUsuario daoUsuario = new DAOUsuario();
        Usuario usuarioBD = daoUsuario.consultarUsuarioPorRFC(usuario);

        // Verificar si se encontró un usuario y si la contraseña coincide
        return usuarioBD != null && usuarioBD.getRFC().equals(contrasena);
    }
}
