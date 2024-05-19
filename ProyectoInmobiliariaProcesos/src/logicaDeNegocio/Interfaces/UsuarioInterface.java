package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Usuario;

public interface UsuarioInterface {
    public int registrarUsuario(Usuario usuario);
    public List<Usuario> consultarUsuarios();

    public Usuario consultarUsuarioPorId(int idUsuario);       
    public int modificarNombrePorIdUsuario(int idUsuario,String nombre);
    public int modificarApellidoPaternoPorIdUsuario(int idUsuario, String apellidoPaterno);
    public int modificarApellidoMaternoPorIdUsuario(int idUsuario,String apellidoMaterno);
    public int modificarTelefonoPorIdUsuario(int idUsuario,String telefono);
    public int modificarCorreoPorIdUsuario(int idUsuario,String correo);
    public int modificarRFCPorIdUsuario(int idUsuario,String RFC);
    public int obtenerIdUsuarioPorCorreo(String correo);

    public Usuario consultarUsuarioPorId(int idUsuario);   
    public Usuario consultarUsuarioPorRFC(String rfc);

}
