package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import InterfazGrafica.NewFXMain;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.LoginSingleton;
import logicaDeNegocio.DAO.DAOLogin;

public class LoginController {

    @FXML
    private TextField txfd_Usuario;

    @FXML
    private PasswordField txfd_Contrasena;
    @FXML
    private Button btn_Ingresar;

    @FXML
    private Label lbl_Mensaje;

    private boolean estaVacio() {
        return txfd_Usuario.getText().isEmpty() || txfd_Contrasena.getText().isEmpty();
    }

    private int verificarAcceso() {
        if (!estaVacio()) {
            DAOLogin daoLogin = new DAOLogin();

            try {
                int existeAcceso = daoLogin.verificarExistenciaLogin(txfd_Usuario.getText(), txfd_Contrasena.getText());
                if (existeAcceso > 0) {
                    return existeAcceso;
                } else {
                    Alertas.mostrarMensajeUsuarioNoEncontrado();
                }
            } catch (SQLException sqlException) {
                Alertas.mostrarMensajeErrorEnLaConexion();
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, sqlException);
        
                }
        } else {
            Alertas.mostrarMensajeDatosVacios();
        }
        return 0;
    }
    
    private void abrirVentanaAdministradorMenu() {
        Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
        NewFXMain newFXMain = new NewFXMain();
        try {
            newFXMain.mostrarVentanaAdministradorMenu(myStage);
        } catch (IOException | NullPointerException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
            Alertas.mostrarMensajeErrorCambioPantalla();
        }
    }
    
    private void abrirVentanaAgente() {
        Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
        NewFXMain newFXMain = new NewFXMain();
        try {
            newFXMain.mostrarVentanaAdministradorMenu(myStage);
        } catch (IOException | NullPointerException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
            Alertas.mostrarMensajeErrorCambioPantalla();
        }
    }
   private void abrirVentanaCliente() {
        Stage myStage = (Stage) btn_Ingresar.getScene().getWindow();
        NewFXMain newFXMain = new NewFXMain();
        try {
            newFXMain.mostrarVentanaCliente(myStage);
        } catch (IOException | NullPointerException ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        
            Alertas.mostrarMensajeErrorCambioPantalla();
        }
    }

    
    @FXML
    private void ingresar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (verificarAcceso() != 0) {
            try {
                DAOLogin daoLogin = new DAOLogin();
                int idUsuario = daoLogin.obtenerIdUsuario(txfd_Usuario.getText());
                LoginSingleton.getInstance().setIdUsuario(idUsuario);
                try {
                    String tipoUsuario=daoLogin.obtenerTipoUsuario(txfd_Usuario.getText());
                    switch (tipoUsuario) {
                        case "AgenteInmobiliario" -> abrirVentanaAgente();
                        case "Administrador" -> abrirVentanaAdministradorMenu();
                        case "Cliente" -> abrirVentanaCliente();
                    }
                } catch (SQLException sqlException) {
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, sqlException);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
    }
    
}
