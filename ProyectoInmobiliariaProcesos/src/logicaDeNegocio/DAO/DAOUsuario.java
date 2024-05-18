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
        }
        return usuario;
    }
}
