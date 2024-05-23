package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.Interfaces.UsuarioInterface;

public class DAOUsuario implements UsuarioInterface {
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;

    @Override
    public int registrarUsuario(Usuario usuario) {
        int resultadoInsercion=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            CallableStatement sentencia = conexion.prepareCall("Call InsertarUsuario(?,?,?,?,?,?,?)");
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getApellidoPaterno());
            sentencia.setString(3, usuario.getApellidoMaterno());
            sentencia.setString(4, usuario.getTelefono());
            sentencia.setString(5, usuario.getCorreo());
            sentencia.setString(6, usuario.getRFC());
            sentencia.registerOutParameter(7, Types.INTEGER);
            sentencia.executeUpdate();
            resultadoInsercion=sentencia.getInt(7);
            conexion.close();            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            resultadoInsercion=-1;
        }
        return resultadoInsercion;
    }

    @Override
    public List<Usuario> consultarUsuarios() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Usuario> usuarios=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from usuario");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                usuario.setTelefono(resultado.getString("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setRFC(resultado.getString("RFC"));
                usuarios.add(usuario);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    @Override
    public Usuario consultarUsuarioPorId(int idUsuario) {
        PreparedStatement declaracion;
        ResultSet resultado;
        Usuario usuario=new Usuario();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from usuario where idUsuario=?");
            declaracion.setInt(1, idUsuario);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                usuario.setTelefono(resultado.getString("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setRFC(resultado.getString("RFC"));                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;        
    }

    @Override
    public int modificarNombrePorIdUsuario(int idUsuario, String nombre) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE usuario set nombre=? where idUsuario=?");
            declaracion.setString(1, nombre);
            declaracion.setInt(2, idUsuario);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int modificarApellidoPaternoPorIdUsuario(int idUsuario, String apellidoPaterno) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE usuario set apellidoPaterno=? where idUsuario=?");
            declaracion.setString(1, apellidoPaterno);
            declaracion.setInt(2, idUsuario);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int modificarApellidoMaternoPorIdUsuario(int idUsuario, String apellidoMaterno) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE usuario set apellidoMaterno=? where idUsuario=?");
            declaracion.setString(1, apellidoMaterno);
            declaracion.setInt(2, idUsuario);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int modificarTelefonoPorIdUsuario(int idUsuario, String telefono) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE usuario set telefono=? where idUsuario=?");
            declaracion.setString(1, telefono);
            declaracion.setInt(2, idUsuario);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int modificarCorreoPorIdUsuario(int idUsuario, String correo) {
        int resultadoModificacion=0;
        CallableStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareCall("Call actualizarCorreo(?,?,?);");
            declaracion.setInt(1, idUsuario);
            declaracion.setString(2, correo);
            declaracion.registerOutParameter(3, Types.INTEGER);
            declaracion.execute();
            resultadoModificacion=declaracion.getInt(3);
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            resultadoModificacion=-1;
        }
        return resultadoModificacion;        
    }

    @Override
    public int modificarRFCPorIdUsuario(int idUsuario, String RFC) {
        int resultadoModificacion=0;
        CallableStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareCall("Call actualizarRFC(?,?,?);");
            declaracion.setInt(1, idUsuario);
            declaracion.setString(2, RFC);
            declaracion.registerOutParameter(3, Types.INTEGER);
            declaracion.execute();
            resultadoModificacion=declaracion.getInt(3);
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            resultadoModificacion=-1;
        }
        return resultadoModificacion;        
    }

    @Override
    public int obtenerIdUsuarioPorCorreo(String correo) {
        int idUsuario=0;
        PreparedStatement declaracion;
        ResultSet resultado;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select idUsuario from Usuario where correo=?;");
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                idUsuario=resultado.getInt("idUsuario");
            }
            conexion.close();
        }catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return idUsuario;
    }
    
    @Override
    public Usuario consultarUsuarioPorRFC(String rfc){
        PreparedStatement declaracion;
        ResultSet resultado;
        Usuario usuario=new Usuario();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from usuario where RFC=?");
            declaracion.setString(1, rfc);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                usuario.setTelefono(resultado.getString("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setRFC(resultado.getString("RFC"));                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            usuario=null;
        }
        return usuario;                
    }
     

 
}
