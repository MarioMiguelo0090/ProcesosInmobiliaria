package logicaDeNegocio.Interfaces;

import java.sql.SQLException;
import logicaDeNegocio.Clases.Login;


public interface ILogin {
   public int verificarExistenciaLogin(String usuario, String contrasenia)throws SQLException ; 
   public int isertarLogin(Login login) throws SQLException;
   public String obtenerTipoUsuario(String usuario)throws SQLException ; 
   public int obtenerIdUsuario(String usuario) throws SQLException ;
}
