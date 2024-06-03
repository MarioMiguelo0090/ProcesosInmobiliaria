package InterfazGrafica.Controladores;

import InterfazGrafica.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import logicaDeNegocio.DAO.DAOUsuario;


public class Ventana_ConsultaDeClientesController implements Initializable {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(Ventana_ConsultaDeClientesController.class);
            
    @FXML
    private TableColumn<Usuario, String> column_ApellidoMaterno;
 
    @FXML
    private TableColumn<Usuario, String> column_ApellidoPaterno;

    @FXML
    private TableColumn<Usuario, String> column_Correo;

    @FXML
    private TableColumn column_Detalles;

    @FXML
    private TableColumn<Usuario, String> column_Nombre;

    @FXML
    private TableView<Usuario> taleView_Clientes;
    @FXML
    private AnchorPane anchor_ConsultaClientes;
    @FXML
    private Button btn_Regresar;
    
    private Stage stage_ventana;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_ApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        column_ApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        column_Correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        List<Usuario> usuarios=obtenerClientes();
        if(usuarios.isEmpty()){
            Alertas.mostrarMensajeErrorEnLaConexion();        
        }else{
            taleView_Clientes.getItems().addAll(usuarios);
            agregarBoton();        
        }
       btn_Regresar.setOnAction(event->regresarVentanaPrincipal());
    }    
    
    public List<Usuario> obtenerClientes(){
        DAOCliente daoCliente=new DAOCliente();        
        List<Integer> idUsuarios=daoCliente.consultarIdUsuarioDeClientesActivos();
        List<Usuario> usuarios=new ArrayList<>();
        DAOUsuario daoUsuario=new DAOUsuario();
        for(Integer idUsuario : idUsuarios){
            usuarios.add(daoUsuario.consultarUsuarioPorId(idUsuario));            
        }
        return usuarios;        
    }
    
     private void agregarBoton() {        
        Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> cellFactory = (final TableColumn<Usuario, Void> param) -> {
            final TableCell<Usuario, Void> cell = new TableCell<Usuario, Void>() {                
                private final Button btn_Detalles = new Button();                                
                {
                    btn_Detalles.setText("Detalles");
                    btn_Detalles.setBackground(new Background(new BackgroundFill(Color.web("#3498db"), new CornerRadii(5), null)));                    
                    btn_Detalles.setTextFill(Color.WHITE);
                    btn_Detalles.setOnAction((ActionEvent event) -> {
                        Usuario usuario=getTableView().getItems().get(getIndex());
                        int idUsuario=usuario.getIdUsuario();
                        desplegarVentanaDetallesDeCliente(idUsuario);                       
                    });
                }                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn_Detalles);
                    }
                }
            };
            return cell;
        };
        column_Detalles.setCellFactory(cellFactory);       
    }
     
    
    public void desplegarVentanaDetallesDeCliente(int idUsuario){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Ventana_DetallesDeCliente.fxml"));
            Parent root = loader.load();
            Ventana_DetallesDeClienteController controlador= loader.getController();
            controlador.inicializar(idUsuario);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        }catch(IOException excepcion){
            LOG.error(excepcion);        
            Alertas.mostrarMensajeErrorEnLaConexion();
        }                        
    }
    
    public void regresarVentanaPrincipal(){
        String rutaVentanaFXML="/interfazGrafica/Ventana_MenuPrincipalAdministrador.fxml";
        desplegarVentanaCorrespondiente(rutaVentanaFXML); 
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
    
    public void cerrarVentana(){
        stage_ventana=(Stage) anchor_ConsultaClientes.getScene().getWindow();
        stage_ventana.close();
    }
    
}
