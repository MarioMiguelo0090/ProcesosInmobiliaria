package logicaDeNegocio.DAO;

import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Interfaces.PropietarioInterface;
import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPropietario implements PropietarioInterface {

    public static final ManejadorBaseDatos BASE_DE_DATOS = new ManejadorBaseDatos();
    private Connection conexion;

    @Override
    public int agregarNuevoPropietario(Propietario propietario) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into propietario "
                    + "(Usuario_idCliente,estadoPropietario)"
                    + " values (?,?);");
            declaracion.setInt(1, propietario.getUsuario().getIdUsuario());
            declaracion.setString(2, propietario.getEstadoPropietario());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int cambiarEstadoPropietario(Propietario propietario, String estado) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("{ CALL actualizarEstadoPropietario(?, ?) }");
            sentencia.setInt(1, propietario.getIdPropietario());
            sentencia.setString(2, estado);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }


    @Override
    public int modificarNombrePropietario(Propietario propietario, String nombre) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        int idUsuario = propietario.getUsuario().getIdUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE usuario SET nombre = ? where idUsuario = ?");
            sentencia.setString(1, nombre);
            sentencia.setInt(2, idUsuario);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarApellidoPaternoPropietario(Propietario propietario, String apellidoPaterno) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        int idUsuario = propietario.getUsuario().getIdUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE usuario SET apellidoPaterno = ? where idUsuario = ?");
            sentencia.setString(1, apellidoPaterno);
            sentencia.setInt(2, idUsuario);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarApellidoMaternoPropietario(Propietario propietario, String apellidoMaterno) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        int idUsuario = propietario.getUsuario().getIdUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE usuario SET apellidoMaterno = ? where idUsuario = ?");
            sentencia.setString(1, apellidoMaterno);
            sentencia.setInt(2, idUsuario);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarCorreoPropietario(Propietario propietario, String correo) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        int idUsuario = propietario.getUsuario().getIdUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE usuario SET correo = ? where idUsuario = ?");
            sentencia.setString(1, correo);
            sentencia.setInt(2, idUsuario);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarRFCPropietario(Propietario propietario, String rfc) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        int idUsuario = propietario.getUsuario().getIdUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE usuario SET rfc = ? where idUsuario = ?");
            sentencia.setString(1, rfc);
            sentencia.setInt(2, idUsuario);
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public List<Propietario> consultarPropietarios() {
        PreparedStatement sentencia;
        ResultSet resultado;
        List<Propietario> propietariosObtenidos = new ArrayList();
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT * FROM propietario");
            resultado = sentencia.executeQuery();
            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Propietario propietario = new Propietario();
                    propietario.setIdPropietario(resultado.getInt("idPropietario"));
                    propietario.setEstadoPropietario(resultado.getString("estadoPropietario"));
                    int idUsuario = resultado.getInt("Usuario_idCliente");
                    propietario.setUsuario(daoUsuario.consultarUsuarioPorId(idUsuario));
                    propietariosObtenidos.add(propietario);
                }
            }
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            propietariosObtenidos = null;
        }
        return propietariosObtenidos;
    }

    @Override
    public List<Propietario> consultarPropietariosPorEstado(String estadoProfesor) {
        PreparedStatement sentencia;
        ResultSet resultado;
        List<Propietario> propietariosObtenidos = new ArrayList();
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT * FROM propietario where estadoProfesor = ?");
            sentencia.setString(1, estadoProfesor);
            resultado = sentencia.executeQuery();
            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Propietario propietario = new Propietario();
                    propietario.setIdPropietario(resultado.getInt("idPropietario"));
                    propietario.setEstadoPropietario(resultado.getString("estadoPropietario"));
                    int idUsuario = resultado.getInt("Usuario_idCliente");
                    propietario.setUsuario(daoUsuario.consultarUsuarioPorId(idUsuario));
                    propietariosObtenidos.add(propietario);
                }
            }
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            propietariosObtenidos = null;
        }
        return propietariosObtenidos;
    }

    @Override
    public Propietario consultarPropietarioPorID(int idPropietario) {
        PreparedStatement sentencia;
        ResultSet resultado;
        Propietario propietarioObtenido = new Propietario();
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT * FROM propietario where idPropietario = ?");
            sentencia.setInt(1, idPropietario);
            resultado = sentencia.executeQuery();
             while(resultado.next()){
                propietarioObtenido.setIdPropietario(resultado.getInt("idPropietario"));
                propietarioObtenido.setEstadoPropietario(resultado.getString("estadoPropietario")); 
                int idUsuario = resultado.getInt("Usuario_idCliente");
                propietarioObtenido.setUsuario(daoUsuario.consultarUsuarioPorId(idUsuario));
            }
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            propietarioObtenido = null;
        }
        return propietarioObtenido;
    }
    
    @Override
    public Propietario consultarPropietarioPorIDUsuario(int idUsuario){
        PreparedStatement sentencia;
        ResultSet resultado;
        Propietario propietarioObtenido = new Propietario();
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT * FROM propietario where Usuario_idCliente = ?");
            sentencia.setInt(1, idUsuario);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                propietarioObtenido.setIdPropietario(resultado.getInt("idPropietario"));
                propietarioObtenido.setEstadoPropietario(resultado.getString("estadoPropietario")); 
                int idUsuarioObtenido = resultado.getInt("Usuario_idCliente");
                propietarioObtenido.setUsuario(daoUsuario.consultarUsuarioPorId(idUsuarioObtenido));
            }
            conexion.close();
        } catch (SQLException excepcion) {
            Logger.getLogger(DAOPropietario.class.getName()).log(Level.SEVERE, null, excepcion);
            propietarioObtenido = null;
        }
        return propietarioObtenido;
     }

}
