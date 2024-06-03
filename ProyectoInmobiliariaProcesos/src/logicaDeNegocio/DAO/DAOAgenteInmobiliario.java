package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.Interfaces.IAgenteInmobiliario;

public class DAOAgenteInmobiliario implements IAgenteInmobiliario {
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;
    
    private static final String AGREGAR_AGENTEN = """
                                                  INSERT INTO agente (idUsuario, estadoAgente)
                                                         VALUES (?, ?);""";
    private static final String AGREGAR_USUARIO = """
                                                  INSERT INTO usuario (nombre, apellidoPaterno, apellidoMaterno, telefono, correo, RFC)
                                                         VALUES
                                                         (?, ?, ?, ?, ?, ?);""";
    private static final String AGREGAR_LOGIN = """
                                                  INSERT INTO login (usuario, contraseña, idUsuario,tipoUsuario )
                                                  VALUES (?, ?, ?,?);""";  
    private static final String VERIFICAR_EXISTENCIA_CORREO = """
                                                  SELECT COUNT(*) AS number_of_matches FROM usuario WHERE correo = ?""";
    private static final String VERIFICAR_EXISTENCIA_RFC = """
                                                  SELECT COUNT(*) AS number_of_matches FROM usuario WHERE RFC = ?""";
    private static final String obtenerTodosLosAgenteInmobiliario = """
                                                  INSERT INTO agente (Usuario_idCliente, estadoAgente)
                                                         VALUES (?, ?);""";
    private static final String obtenerListaInstitucionesPorNombre = """
                                                  INSERT INTO agente (Usuario_idCliente, estadoAgente)
                                                         VALUES (?, ?);""";
    
    @Override
    public int insertarAgenteInmobiliario(Usuario usuario, Login login) throws SQLException {
        int numeroFilasAfectada = -1;
       int idAccesoGenerado = -1;
       int idUsuarioGenerado = -1;

                conexion=BASE_DE_DATOS.getConexion();
                PreparedStatement statement = conexion.prepareStatement(AGREGAR_USUARIO, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getApellidoPaterno());
                statement.setString(3, usuario.getApellidoMaterno());
                statement.setString(4, usuario.getTelefono());
                statement.setString(5, usuario.getCorreo());
                statement.setString(6, usuario.getRFC());
                numeroFilasAfectada = statement.executeUpdate();
                
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idUsuarioGenerado = generatedKeys.getInt(1);
                    usuario.setIdUsuario(idUsuarioGenerado);
                }
                
                PreparedStatement loginStatement = conexion.prepareStatement(AGREGAR_LOGIN);
                loginStatement.setString(1, login.getUsuario());
                loginStatement.setString(2, login.getContrasenia());
                loginStatement.setInt(3, idUsuarioGenerado);
                loginStatement.setString(4, "AgenteInmobiliario");
                
                numeroFilasAfectada += loginStatement.executeUpdate();
                
                PreparedStatement agenteStatement = conexion.prepareStatement(AGREGAR_AGENTEN);
                agenteStatement.setInt(1, idUsuarioGenerado);
                agenteStatement.setString(2, "Activo");
                numeroFilasAfectada += agenteStatement.executeUpdate();
                conexion.close();
       
       return numeroFilasAfectada;
    }

    @Override
    public boolean verificarSiExisteElCorreo(String correo) throws SQLException {
        boolean existeCorreo = true;
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(VERIFICAR_EXISTENCIA_CORREO);
                
        statement.setString(1, correo);
        ResultSet resultado = statement.executeQuery();
        
        if (resultado.next()) {
            int NOT_MATCHES = 0;
            
            if (resultado.getInt("number_of_matches") == NOT_MATCHES) {
                existeCorreo = false;
            }
        }
        conexion.close();
        return existeCorreo;
    }

    @Override
    public boolean verificarSiExisteRFC(String RFC) throws SQLException {
        boolean existeRFC = true;
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(VERIFICAR_EXISTENCIA_RFC);
                
        statement.setString(1, RFC);
        ResultSet resultado = statement.executeQuery();
        
        if (resultado.next()) {
            int NOT_MATCHES = 0;
            
            if (resultado.getInt("number_of_matches") == NOT_MATCHES) {
                existeRFC = false;
            }
        }
        conexion.close();
        return existeRFC;
    }

    @Override
    public List<Integer> consultarIdUsuarioDeAgentesActivos() throws SQLException{
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Integer> idUsuarios=new ArrayList<>();
        
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT idUsuario from agente where estadoAgente='Activo';");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                int idUsuario=resultado.getInt("idUsuario");
                idUsuarios.add(idUsuario);
            }
            conexion.close();
       
        return idUsuarios;        
    }
    
         @Override
     public List<Usuario> obtenerListaAgentesPorNombre(String criterioBusqueda) throws SQLException {
        String consulta = """
            SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno, telefono, correo, 
                RFC FROM usuario
            WHERE nombre LIKE ? OR apellidoPaterno LIKE ? OR apellidoMaterno LIKE ? ; """;
            List<Usuario> listaAgentesPorNombre = new ArrayList<>();
            conexion=BASE_DE_DATOS.getConexion();
            PreparedStatement statement = conexion.prepareStatement(consulta);
                String criterioBusquedaLike = "%" + criterioBusqueda + "%";
                statement.setString(1, criterioBusquedaLike);
                statement.setString(2, criterioBusquedaLike);
                statement.setString(3, criterioBusquedaLike);
            ResultSet resultado = statement.executeQuery(); 
            listaAgentesPorNombre = obtenerListaAgentes(resultado);
         
            conexion.close();
        return listaAgentesPorNombre;
     }
     
         private List<Usuario> obtenerListaAgentes(ResultSet result) throws SQLException {
        List<Usuario> listaAgentes = new ArrayList<>();
        
        while (result.next()) {
            Usuario Usuario = new Usuario();
            Usuario.setIdUsuario(result.getInt("idUsuario"));
            Usuario.setNombre(result.getString("nombre"));
            Usuario.setApellidoPaterno(result.getString("apellidoPaterno"));
            Usuario.setApellidoMaterno(result.getString("apellidoMaterno"));
            Usuario.setTelefono(result.getString("telefono"));
            Usuario.setCorreo(result.getString("correo"));
            Usuario.setRFC(result.getString("RFC"));
            listaAgentes.add(Usuario);
        }
        return listaAgentes;
    }
}
