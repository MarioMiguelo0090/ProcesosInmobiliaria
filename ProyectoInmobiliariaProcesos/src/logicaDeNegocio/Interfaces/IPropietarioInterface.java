package logicaDeNegocio.Interfaces;

import java.sql.SQLException;
import java.util.List;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.Usuario;


public interface IPropietarioInterface {
    public int agregarNuevoPropietario(Usuario usuario) throws SQLException;
    public boolean verificarSiExisteElCorreo(String correo) throws SQLException;
    public boolean verificarSiExisteRFC(String RFC) throws SQLException;
    public int cambiarEstadoPropietario(Propietario propietario, String estado);
    public int modificarNombrePropietario(Propietario propietario, String nombre);
    public int modificarApellidoPaternoPropietario(Propietario propietario, String apellidoPaterno);
    public int modificarApellidoMaternoPropietario(Propietario propietario, String apellidoMaterno);
    public int modificarCorreoPropietario(Propietario propietario, String correo);
    public int modificarRFCPropietario(Propietario propietario, String rfc);
    public Propietario consultarPropietarioPorID(int idPropietario);
    public Propietario consultarPropietarioPorIDUsuario(int idUsuario);
    public List<Usuario> consultarPropietarios();
    public List<Propietario> consultarPropietariosPorEstado(String estadoProfesor);
}
