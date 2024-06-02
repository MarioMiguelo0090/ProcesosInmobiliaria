package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.DAO.DAOPropietario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOAgenteInmobiliario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_ConsultarPropietariosController implements Initializable {
    
    private Stage escenario;
    @FXML
    private AnchorPane pane_Principal;
    @FXML
    private Button btn_Regresar;

    @FXML
    private TableView<Usuario> tableView_Propiedades;

    @FXML
    private TableColumn<Usuario, Integer> column_idPropietario;

    @FXML
    private TableColumn<Usuario, String> column_Nombre;

    @FXML
    private TableColumn<Usuario, String> column_ApellidoP;

    @FXML
    private TableColumn<Usuario, String> column_ApellidoM;

    @FXML
    private TableColumn<Usuario, String> column_RFC;

    @FXML
    private TableColumn<Usuario, String> column_Telefono;

    @FXML
    private TableColumn<Usuario, String> column_Correo;

    @FXML
    private TableColumn<Usuario, Void> column_Actualizar;

    @FXML
    private Button btn_RegistrarPropietario;

    private ObservableList<Propietario> listaPropietarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Usuario> usuarios = obtenerPropietarios();
        btn_Regresar.setOnAction(event -> regresarVentanaPrincipal());
        btn_RegistrarPropietario.setOnAction(event -> registrarPropietario());
        tableView_Propiedades.getItems().addAll(usuarios);

        column_idPropietario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_ApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        column_ApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        column_RFC.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        column_Telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        addButtonToTable();
    }

    public List<Usuario> obtenerPropietarios(){
        DAOPropietario daoPropietario = new DAOPropietario();
        return daoPropietario.consultarPropietarios();
    }

    private void addButtonToTable() {
        Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Usuario, Void> call(final TableColumn<Usuario, Void> param) {
                final TableCell<Usuario, Void> cell = new TableCell<Usuario,Void>() {

                    private final Button btn = new Button("Actualizar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Usuario usuario = getTableView().getItems().get(getIndex());
                            actualizarPropietario(usuario.getIdUsuario());

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        column_Actualizar.setCellFactory(cellFactory);
    }   
    
    public void actualizarPropietario(int idUsuario){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfazGrafica/Ventana_ActualizarPropietario.fxml"));
            Parent root = loader.load();
            Ventana_ActualizarPropietarioController controller = loader.getController();
            controller.cargarDatosPropietario(idUsuario);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        } catch (IOException excepcion) {
            Logger.getLogger(Ventana_ConsultarPropietariosController.class.getName()).log(Level.SEVERE, null, excepcion);
        }
    }

    public void registrarPropietario(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_RegistrarPropietario.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
    }

     public void regresarVentanaPrincipal(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_MenuPrincipalAdministrador.fxml";
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
}