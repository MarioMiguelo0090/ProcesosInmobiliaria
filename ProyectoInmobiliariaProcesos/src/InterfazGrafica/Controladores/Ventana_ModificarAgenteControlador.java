package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_ModificarAgenteControlador implements Initializable {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Ventana_ModificarAgenteControlador.class);
    private Stage escenario;
    @FXML private AnchorPane anchor_Ventana;
    @FXML private TextField txfd_ApellidoMaterno;
    @FXML private TextField txfd_ApellidoPaterno;
    @FXML private TextField txfd_Correo;
    @FXML private TextField txfd_Nombre;
    @FXML private TextField txfd_RFC;
    @FXML private TextField txfd_Telefono;
    @FXML private Label label_ErrorNombre;
    @FXML private Label label_ErrorApellidoMaterno;
    @FXML private Label label_ErrorApellidoPaterno;
    @FXML private Label label_ErrorCorreo;
    @FXML private Label label_ErrorRfc;
    @FXML private Label label_ErrorTelefono;
    private int idUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void inicializar(int idUsuario, Stage stage) {
        this.idUsuario = idUsuario;
        this.escenario = stage;
        llenarDatosUsuario();
        etiquetasDeError();

        escenario.setOnCloseRequest(event -> {
            event.consume();

        });
    }

    public void llenarDatosUsuario() {
        DAOUsuario daoUsuario = new DAOUsuario();
        Usuario usuario = daoUsuario.consultarUsuarioPorId(idUsuario);
        txfd_Nombre.setText(usuario.getNombre());
        txfd_ApellidoPaterno.setText(usuario.getApellidoPaterno());
        txfd_ApellidoMaterno.setText(usuario.getApellidoMaterno());
        txfd_Correo.setText(usuario.getCorreo());
        txfd_RFC.setText(usuario.getRFC());       
        txfd_Telefono.setText(usuario.getTelefono());
    }

    public Usuario obtenerUsuario() {
        Usuario usuario = new Usuario();

        if (!estaVacio()) {
            boolean valid = true;

            try {
                usuario.setNombre(txfd_Nombre.getText());
            } catch (IllegalArgumentException exception) {
                LOG.warn(exception);
                label_ErrorNombre.setVisible(true);
                valid = false;
            }

            try {
                usuario.setApellidoPaterno(txfd_ApellidoPaterno.getText());
            } catch (IllegalArgumentException nombreException) {
                LOG.warn(nombreException);
                label_ErrorApellidoPaterno.setVisible(true);
                valid = false;
            }

            try {
                usuario.setApellidoMaterno(txfd_ApellidoMaterno.getText());
            } catch (IllegalArgumentException nombreException) {
                LOG.warn(nombreException);
                label_ErrorApellidoMaterno.setVisible(true);
                valid = false;
            } 

            try {
                usuario.setCorreo(txfd_Correo.getText());
            } catch (IllegalArgumentException correoException) {
                LOG.warn(correoException);
                label_ErrorCorreo.setVisible(true);
                valid = false;
            } 

            try {
                usuario.setTelefono(txfd_Telefono.getText());
            } catch (IllegalArgumentException nombrePaisException) {
                LOG.warn(nombrePaisException);
                label_ErrorTelefono.setVisible(true);
                valid = false;
            }

            try {
                usuario.setRFC(txfd_RFC.getText());
            } catch (IllegalArgumentException nombrePaisException) {
                LOG.warn(nombrePaisException);
                label_ErrorRfc.setVisible(true);
                valid = false;
            }

            if (!valid) {
                return null;
            }

        } else {
            Alertas.mostrarMensajeCamposVacios();
            return null;
        }

        return usuario;
    }

    public void cerrarVentana() {
        escenario = (Stage) anchor_Ventana.getScene().getWindow();
        escenario.close();
    }

    public void regresarDeVentana() {
        String rutaVentanaFXML = "/interfazGrafica/Ventana_ConsultarAngenteInmobiliario.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }

    public void desplegarVentanaCorrespondiente(String rutaVentanaFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            LOG.error(excepcion);
        }
    }

    public void actualizarAgente() {
        DAOUsuario daoUsuario = new DAOUsuario();
        Usuario usuarioAntiguo = daoUsuario.consultarUsuarioPorId(idUsuario);
        Usuario usuarioActualizado = obtenerUsuario();

        if (usuarioActualizado == null) {
            Alertas.mostrarMensajeDatosInvalidos();
            return;
        }

        try {
            boolean datosModificados = false;
            boolean datosDuplicados = false;

            if (!usuarioActualizado.getNombre().equals(usuarioAntiguo.getNombre())) {
                daoUsuario.modificarNombrePorIdUsuario(idUsuario, usuarioActualizado.getNombre());
                datosModificados = true;
            }
            if (!usuarioActualizado.getApellidoPaterno().equals(usuarioAntiguo.getApellidoPaterno())) {
                daoUsuario.modificarApellidoPaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoPaterno());
                datosModificados = true;
            }
            if (!usuarioActualizado.getApellidoMaterno().equals(usuarioAntiguo.getApellidoMaterno())) {
                daoUsuario.modificarApellidoMaternoPorIdUsuario(idUsuario, usuarioActualizado.getApellidoMaterno());
                datosModificados = true;
            }
            if (!usuarioActualizado.getCorreo().equals(usuarioAntiguo.getCorreo())) {
                int resultadoCorreo = daoUsuario.modificarCorreoPorIdUsuario(idUsuario, usuarioActualizado.getCorreo());
                if (resultadoCorreo != 1) {
                    Alertas.mostrarMensajeDatosDuplicados();
                    datosDuplicados = true;
                } else {
                    datosModificados = true;
                }
            }
            if (!usuarioActualizado.getRFC().equals(usuarioAntiguo.getRFC())) {
                int resultadoRFC = daoUsuario.modificarRFCPorIdUsuario(idUsuario, usuarioActualizado.getRFC());
                if (resultadoRFC != 1) {
                    Alertas.mostrarMensajeDatosDuplicados();
                    datosDuplicados = true;
                } else {
                    datosModificados = true;
                }
            }
            if (!usuarioActualizado.getTelefono().equals(usuarioAntiguo.getTelefono())) {
                daoUsuario.modificarTelefonoPorIdUsuario(idUsuario, usuarioActualizado.getTelefono());
                datosModificados = true;
            }

            if (datosModificados) {
                Alertas.mostrarMensajeActualizacionExitosa();
                regresarDeVentana();
            } else if (datosDuplicados) {
                // Maneja los datos duplicados si es necesario
            } else {
                Alertas.mostrarMensajeSinCambios();
            }
        } catch (Exception e) {
            LOG.warn(e);
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }

    private boolean estaVacio() {
        return txfd_Nombre.getText().isEmpty() ||
               txfd_ApellidoPaterno.getText().isEmpty() ||
               txfd_ApellidoMaterno.getText().isEmpty() ||
               txfd_RFC.getText().isEmpty() ||
               txfd_Telefono.getText().isEmpty() ||
               txfd_Correo.getText().isEmpty();
    }

    private void etiquetasDeError() {
        label_ErrorNombre.setVisible(false);
        label_ErrorApellidoPaterno.setVisible(false);
        label_ErrorApellidoMaterno.setVisible(false);
        label_ErrorCorreo.setVisible(false);
        label_ErrorTelefono.setVisible(false);
        label_ErrorRfc.setVisible(false);
    }
}
