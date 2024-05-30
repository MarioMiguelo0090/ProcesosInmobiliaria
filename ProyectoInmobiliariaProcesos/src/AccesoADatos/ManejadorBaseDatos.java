package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejadorBaseDatos {
    
    private static ManejadorBaseDatos instancia;
    private Connection conexion;
    private static final String NOMBRE_BASE_DE_DATOS="jdbc:mysql://localhost/inmobiliaria";;
    private final String USUARIO_BASE_DE_DATOS="root";
    private final String CONTRASENA_BASE_DE_DATOS="ChrisVZ2500";

    
    public Connection getConexion()throws SQLException{
        connect();
        return conexion;
    }
    
    public void connect() throws SQLException{
        conexion = DriverManager.getConnection(NOMBRE_BASE_DE_DATOS,USUARIO_BASE_DE_DATOS,CONTRASENA_BASE_DE_DATOS);
    }
    
     public void cerrarConexion(Connection conexion){
        if(conexion!=null){
            try{
                if(!conexion.isClosed()){
                    conexion.close();
                }
            }catch(SQLException excepcion){
                Logger.getLogger(excepcion.getMessage());
            }
        }
    }
}
