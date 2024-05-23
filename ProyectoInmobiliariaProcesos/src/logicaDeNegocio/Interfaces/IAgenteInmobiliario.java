package logicaDeNegocio.Interfaces;

import java.sql.SQLException;
import java.util.List;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.Usuario;

public interface IAgenteInmobiliario {
    int insertarAgenteInmobiliario(Usuario usuario, Login login)throws SQLException;
    public boolean verificarSiExisteElCorreo(String correo) throws SQLException;
    public boolean verificarSiExisteRFC(String RFC) throws SQLException;
    public List<Integer> consultarIdUsuarioDeAgentesActivos()throws SQLException;
    public List<Usuario> obtenerListaAgentesPorNombre(String criterioBusqueda) throws SQLException;
     
}
