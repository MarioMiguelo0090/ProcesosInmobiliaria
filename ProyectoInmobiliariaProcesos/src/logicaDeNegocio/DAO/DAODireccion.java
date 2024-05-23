package logicaDeNegocio.DAO;

import logicaDeNegocio.Clases.Direccion;
import logicaDeNegocio.Interfaces.DireccionInterface;
import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Ubicacion;

public class DAODireccion implements DireccionInterface {
    
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;
    
    @Override
    public int insertarDireccion(Direccion direccion) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into direccion "
                    + "(codigoPostal,calle,ciudad,colonia,idUbicacion)"
                    + " values (?,?,?,?,?);");
            declaracion.setString(1, direccion.getCodigoPostal());
            declaracion.setString(2, direccion.getCalle());
            declaracion.setString(3, direccion.getCiudad());
            declaracion.setString(4,direccion.getColonia());
            declaracion.setInt(5, direccion.getUbicacion().getIdUbicacion());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;       
    }

    @Override
    public int modificarCodigoPostal(Direccion direccion, String codigoPostal) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE direccion set codigoPostal = ? where idDireccion = ?");
            sentencia.setString(1, codigoPostal);
            sentencia.setInt(2, direccion.getIdDireccion());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarCalle(Direccion direccion, String calle) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE direccion set calle = ? where idDireccion = ?");
            sentencia.setString(1, calle);
            sentencia.setInt(2, direccion.getIdDireccion());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarEstado(Direccion direccion, String estado) {
         PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT idUbicacion FROM ubicacion WHERE Estado = ?");
            sentencia.setString(1, estado);
            ResultSet resultado = sentencia.executeQuery();
            int idUbicacion=0;
            if (resultado.next()) {
                idUbicacion = resultado.getInt("idUbicacion");
            }
            sentencia = conexion.prepareStatement("UPDATE direccion set idUbicacion = ? where idDireccion = ?");
            sentencia.setInt(1, idUbicacion);
            sentencia.setInt(2,direccion.getIdDireccion());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarCiudad(Direccion direccion, String ciudad) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE direccion set ciudad = ? where idDireccion = ?");
            sentencia.setString(1, ciudad);
            sentencia.setInt(2, direccion.getIdDireccion());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int modificarColonia(Direccion direccion, String colonia) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE direccion set colonia = ? where idDireccion = ?");
            sentencia.setString(1, colonia);
            sentencia.setInt(2, direccion.getIdDireccion());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public Direccion obtenerDireccionPorId(int idDireccion){
        Direccion direccion = new Direccion();
        ResultSet resultadoConsulta;
        PreparedStatement sentencia;
        DAOUbicacion daoUbicacion = new DAOUbicacion();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("select * from direccion where idDireccion = ?");
            sentencia.setInt(1, idDireccion);
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                direccion.setIdDireccion(resultadoConsulta.getInt("idDireccion"));
                direccion.setCalle(resultadoConsulta.getString("calle"));
                direccion.setCiudad(resultadoConsulta.getString("ciudad"));
                direccion.setCodigoPostal(resultadoConsulta.getString("codigoPostal"));
                direccion.setColonia(resultadoConsulta.getString("colonia"));
                int idUbicacion = resultadoConsulta.getInt("idUbicacion");
                Ubicacion ubicacion = new Ubicacion();
                ubicacion = daoUbicacion.consultarUbicacionPorID(idUbicacion);
                direccion.setUbicacion(ubicacion);
            }
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
        }
        return direccion;
    }
    
    @Override
    public Direccion ObtenerUltimaDireccionRegistrada(){
         Direccion direccion = new Direccion();
        ResultSet resultadoConsulta;
        PreparedStatement sentencia;
        DAOUbicacion daoUbicacion = new DAOUbicacion();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("select * from direccion order by idDireccion desc limit 1");
            resultadoConsulta = sentencia.executeQuery();
            while(resultadoConsulta.next()){
                direccion.setIdDireccion(resultadoConsulta.getInt("idDireccion"));
                direccion.setCalle(resultadoConsulta.getString("calle"));
                direccion.setCodigoPostal(resultadoConsulta.getString("codigoPostal"));
                int idUbicacion = resultadoConsulta.getInt("idUbicacion");
                Ubicacion ubicacion = new Ubicacion();
                ubicacion = daoUbicacion.consultarUbicacionPorID(idUbicacion);
                direccion.setUbicacion(ubicacion);
            }
        }catch(SQLException excepcion){
            Logger.getLogger(DAODireccion.class.getName()).log(Level.SEVERE, null, excepcion);
        }
        return direccion;
    }
    
}
