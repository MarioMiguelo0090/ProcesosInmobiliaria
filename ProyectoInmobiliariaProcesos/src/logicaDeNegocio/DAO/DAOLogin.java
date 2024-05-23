package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Interfaces.ILogin;


public class DAOLogin implements ILogin{
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;
    private final String ACCESO_EXISTENTE = "SELECT COUNT(*) FROM login WHERE usuario = ? AND contraseña = ?";
    private static final String OBTENER_ACCESO = "SELECT * FROM login WHERE usuario = ?";
    private static final String OBTENER_LOGIN = "SELECT * FROM loginchris where idUsuario = ?";

    private static final String INSERTAR_LOGIN = """
            INSERT INTO login (usuario, contraseña, idUsuario, tipoUsuario)
            VALUES ( ?, ?, ?, ?); """;
    
        
    @Override
    public int isertarLogin(Login login  ) throws SQLException{
        int resutado = -1;
        String query = INSERTAR_LOGIN;
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, login.getUsuario());
        statement.setString(2, login.getContrasenia());
        statement.setInt(3, login.getIdUsuario());
        statement.setString(4, login.getTipoUsuario());
        resutado = statement.executeUpdate();
        conexion.close();
        return resutado; 
    }
    
    @Override
    public int verificarExistenciaLogin(String usuario, String contrasenia) throws SQLException {
        int existeAcceso = 0;
        String consulta = ACCESO_EXISTENTE;
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, usuario);
        statement.setString(2, contrasenia);
        ResultSet resultado = statement.executeQuery();
        resultado.next();
        existeAcceso = resultado.getInt(1);
        conexion.close();
        return existeAcceso;
    }

    @Override
    public String obtenerTipoUsuario(String usuario) throws SQLException {
        String consulta = OBTENER_ACCESO;
        String tipoUsuario = "NoExiste";
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, usuario);
        ResultSet resultado = statement.executeQuery();
        if (resultado.next()) {
            tipoUsuario = resultado.getString("tipoUsuario");
        }

        return tipoUsuario;
    }
    
        @Override
    public int obtenerIdUsuario(String usuario) throws SQLException {
        String consulta = OBTENER_ACCESO;
        int tipoUsuario = -1;
        conexion=BASE_DE_DATOS.getConexion();
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, usuario);
        ResultSet resultado = statement.executeQuery();
        if (resultado.next()) {
            tipoUsuario = resultado.getInt("idUsuario");
        }

        return tipoUsuario;
    }
    
    @Override
    public Login obtenerLoginPorIdUsuario(int idUsuario){
        Login login = new Login();
        try{
            String consulta = OBTENER_LOGIN;
            conexion=BASE_DE_DATOS.getConexion();
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idUsuario);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                login.setContrasenia(resultado.getString("contraseña"));
                login.setIUsuario(resultado.getInt("idUsuario"));
                login.setIdLogin(resultado.getInt("idLogin"));
                login.setTipoUsuario(resultado.getString("tipoUsuario"));
                login.setUsuario(resultado.getString("usuario"));
            }
        }catch(SQLException excepcion){
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, excepcion);
            login = null;
        }
        return login;
    }
}
