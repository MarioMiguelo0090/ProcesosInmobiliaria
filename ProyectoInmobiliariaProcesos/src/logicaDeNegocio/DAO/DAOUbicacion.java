package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Interfaces.UbicacionInterface;

public class DAOUbicacion implements UbicacionInterface{
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;

    @Override
    public int registrarUbicacion(Ubicacion ubicacion) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into ubicacion (estado,ciudad) values (?,?);");
            declaracion.setString(1, ubicacion.getEstado());
            declaracion.setString(2,ubicacion.getCiudad());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public List<Ubicacion> consultarUbicaciones() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Ubicacion> ubicaciones=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from Ubicacion;");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Ubicacion ubicacion=new Ubicacion();
                ubicacion.setIdUbicacion(resultado.getInt("idUbicacion"));
                ubicacion.setEstado(resultado.getString("estado"));
                ubicacion.setCiudad(resultado.getString("ciudad"));
                ubicaciones.add(ubicacion);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ubicaciones;
    }
    
}
