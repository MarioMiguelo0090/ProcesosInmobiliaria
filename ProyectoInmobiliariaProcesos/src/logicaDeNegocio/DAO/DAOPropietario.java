package logicaDeNegocio.DAO;

import logicaDeNegocio.Clases.Propietario;
import AccesoADatos.ManejadorBaseDatos;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.Interfaces.IPropietarioInterface;

public class DAOPropietario implements IPropietarioInterface {
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOPropietario.class);

    public static final ManejadorBaseDatos BASE_DE_DATOS = new ManejadorBaseDatos();
    private Connection conexion;

    private static final String VERIFICAR_EXISTENCIA_CORREO = "SELECT COUNT(*) AS number_of_matches \n" +
"FROM usuario \n" +
"JOIN propietario ON usuario.idUsuario = propietario.Usuario_idCliente \n" +
"WHERE usuario.correo = ?;";
    private static final String VERIFICAR_EXISTENCIA_RFC = """
        SELECT COUNT(*) AS number_of_matches FROM usuario WHERE RFC = ?""";
    
    @Override
    public int agregarNuevoPropietario(Usuario usuario) throws SQLException {
        int filasAfectadas = -1;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            // Inserta al usuario y obtiene el ID
            String insertarUsuario = """
                INSERT INTO usuario (nombre, apellidoPaterno, apellidoMaterno, telefono, correo, RFC)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
            PreparedStatement statementUsuario = conexion.prepareStatement(insertarUsuario, Statement.RETURN_GENERATED_KEYS);
            statementUsuario.setString(1, usuario.getNombre());
            statementUsuario.setString(2, usuario.getApellidoPaterno());
            statementUsuario.setString(3, usuario.getApellidoMaterno());
            statementUsuario.setString(4, usuario.getTelefono());
            statementUsuario.setString(5, usuario.getCorreo());
            statementUsuario.setString(6, usuario.getRFC());
            statementUsuario.executeUpdate();
            ResultSet generatedKeys = statementUsuario.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idUsuario = generatedKeys.getInt(1);
                // Inserta al propietario con el ID del usuario recién creado
                String insertarPropietario = """
                    INSERT INTO propietario (Usuario_idCliente, estadoPropietario)
                    VALUES (?, ?)
                """;
                PreparedStatement statementPropietario = conexion.prepareStatement(insertarPropietario);
                statementPropietario.setInt(1, idUsuario);
                statementPropietario.setString(2, "Activo"); // Asigna el estado apropiado
                filasAfectadas = statementPropietario.executeUpdate();
            }
        } catch (SQLException ex) {
            LOG.fatal(ex);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }
        return filasAfectadas;
    }


    @Override
    public boolean verificarSiExisteElCorreo(String correo) throws SQLException {
        boolean existeCorreo = true;
        conexion = BASE_DE_DATOS.getConexion();
        CallableStatement statement = (CallableStatement) conexion.prepareCall(VERIFICAR_EXISTENCIA_CORREO);
        statement.setString(1, correo);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            existeCorreo = resultado.getInt("number_of_matches") > 0;
        }
        conexion.close();
        return existeCorreo;
    }

    @Override
    public boolean verificarSiExisteRFC(String RFC) throws SQLException {
        boolean existeRFC = true;
        conexion = BASE_DE_DATOS.getConexion();
        CallableStatement statement = (CallableStatement) conexion.prepareCall(VERIFICAR_EXISTENCIA_RFC);
        statement.setString(1, RFC);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            existeRFC = resultado.getInt("number_of_matches") > 0;
        }
        conexion.close();
        return existeRFC;
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public List<Usuario> consultarPropietarios() {
        PreparedStatement sentencia;
        ResultSet resultado;
        List<Usuario> propietariosObtenidos = new ArrayList();
        DAOUsuario daoUsuario = new DAOUsuario();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT \n" +

"    usuario.idUsuario,\n" +
"    usuario.nombre,\n" +
"    usuario.apellidoPaterno,\n" +
"    usuario.apellidoMaterno,\n" +
"    usuario.telefono,\n" +
"    usuario.correo,\n" +
"    usuario.RFC,\n" +
"    propietario.estadoPropietario\n" +
"FROM \n" +
"    propietario\n" +
"INNER JOIN \n" +
"    usuario ON propietario.Usuario_idCliente = usuario.idUsuario;");

            resultado = sentencia.executeQuery();
            if (resultado.isBeforeFirst()) {
                while (resultado.next()) {
                    Usuario usuario = new Usuario();
                        usuario.setIdUsuario(resultado.getInt("idUsuario"));
                        usuario.setNombre(resultado.getString("nombre"));
                        usuario.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                        usuario.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                        usuario.setTelefono(resultado.getString("telefono"));
                        usuario.setCorreo(resultado.getString("correo"));
                        usuario.setRFC(resultado.getString("RFC"));
                        propietariosObtenidos.add(usuario);
                }
            }
            conexion.close();
        } catch (SQLException excepcion) {
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
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
            LOG.error(excepcion);
            propietarioObtenido = null;
        }
        return propietarioObtenido;
     }

}
