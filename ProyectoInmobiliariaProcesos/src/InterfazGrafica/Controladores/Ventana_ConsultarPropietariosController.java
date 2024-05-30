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
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOAgenteInmobiliario;
import logicaDeNegocio.DAO.DAOUsuario;

public class Ventana_ConsultarPropietariosController implements Initializable {

    @FXML
    private Button btn_Regresar;

    @FXML
    private TableView<Propietario> tableView_Propiedades;

    @FXML
    private TableColumn<Propietario, Integer> column_idPropietario;

    @FXML
    private TableColumn<Propietario, String> column_Nombre;

    @FXML
    private TableColumn<Propietario, String> column_ApellidoP;

    @FXML
    private TableColumn<Propietario, String> column_ApellidoM;

    @FXML
    private TableColumn<Propietario, String> column_RFC;

    @FXML
    private TableColumn<Propietario, String> column_Telefono;

    @FXML
    private TableColumn<Propietario, String> column_Correo;

    @FXML
    private TableColumn<Propietario, Void> column_Actualizar;

    @FXML
    private Button btn_RegistrarPropietario;

    private ObservableList<Propietario> listaPropietarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar las columnas de la tabla
        column_idPropietario.setCellValueFactory(new PropertyValueFactory<>("idPropietario"));
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_ApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        column_ApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        column_RFC.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        column_Telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Añadir botones a la columna "Actualizar"
        addButtonToTable();

        // Cargar los datos de la base de datos
        obtenerPropietarios();
    }

    
    
    public List<Usuario> obtenerPropietarios(){
        DAOPropietario daoPropietario=new DAOPropietario();
            
            List<Usuario> usuarios=new ArrayList<>();
            usuarios=daoPropietario.consultarPropietarios();
        return usuarios;
    }

    private void addButtonToTable() {
        Callback<TableColumn<Propietario, Void>, TableCell<Propietario, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Propietario, Void> call(final TableColumn<Propietario, Void> param) {
                final TableCell<Propietario, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Actualizar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Propietario propietario = getTableView().getItems().get(getIndex());
                            abrirVentanaActualizarPropietario(propietario.getIdPropietario());
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

    private void abrirVentanaActualizarPropietario(int idPropietario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Vistas/Ventana_ActualizarPropietario.fxml"));
            Parent root = loader.load();

            Ventana_ActualizarPropietarioController controlador = loader.getController();
            controlador.cargarDatosPropietario(idPropietario);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registrarPropiedad(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Vistas/Ventana_RegistrarPropietario.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void regresarDeVentana() {
        Stage stage = (Stage) btn_Regresar.getScene().getWindow();
        stage.close();     
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana_MenuPrincipaldministrador.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            
        }
    }
}
