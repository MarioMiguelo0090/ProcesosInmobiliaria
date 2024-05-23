package InterfazGrafica.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOPropietario;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_ActualizarPropietarioController {

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
    private TextField txfd_RFC;
    @FXML
    private TextField txt_EstadoPropietario;
    @FXML
    private Button btn_Guardar;
    @FXML
    private Button btn_Cancelar;

    private DAOPropietario daoPropietario = new DAOPropietario();
    private DAOUsuario daoUsuario = new DAOUsuario();
    private Propietario propietario;

    @FXML
    private void initialize() {
        btn_Guardar.setOnAction(event -> guardarPropietario());
    }

    public void cargarDatosPropietario(int idPropietario) {
        propietario = daoPropietario.consultarPropietarioPorID(idPropietario);
        if (propietario != null) {
            Usuario usuario = propietario.getUsuario();
            txfd_Nombre.setText(usuario.getNombre());
            txfd_ApellidoP.setText(usuario.getApellidoPaterno());
            txfd_ApellidoM.setText(usuario.getApellidoMaterno());
            txfd_Telefono.setText(usuario.getTelefono());
            txfd_Correo.setText(usuario.getCorreo());
            txfd_RFC.setText(usuario.getRFC());
            txt_EstadoPropietario.setText(propietario.getEstadoPropietario());
        } else {
            mostrarAlerta("Error", "Propietario no encontrado");
        }
    }

    private void guardarPropietario() {
        try {
            String nombre = txfd_Nombre.getText();
            String apellidoPaterno = txfd_ApellidoP.getText();
            String apellidoMaterno = txfd_ApellidoM.getText();
            String telefono = txfd_Telefono.getText();
            String correo = txfd_Correo.getText();
            String rfc = txfd_RFC.getText();
            String estadoPropietario = txt_EstadoPropietario.getText();

            propietario.setEstadoPropietario(estadoPropietario);

            Usuario usuario = propietario.getUsuario();
            usuario.setNombre(nombre);
            usuario.setApellidoPaterno(apellidoPaterno);
            usuario.setApellidoMaterno(apellidoMaterno);
            usuario.setTelefono(telefono);
            usuario.setCorreo(correo);
            usuario.setRFC(rfc);

            int resultadoUsuario = daoUsuario.modificarUsuario(usuario);
            int resultadoPropietario = daoPropietario.cambiarEstadoPropietario(propietario, estadoPropietario);

            if (resultadoUsuario > 0 && resultadoPropietario > 0) {
                mostrarAlerta("Éxito", "Propietario actualizado correctamente");
            } else {
                mostrarAlerta("Error", "No se pudo actualizar el propietario");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "ID de propietario inválido");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
