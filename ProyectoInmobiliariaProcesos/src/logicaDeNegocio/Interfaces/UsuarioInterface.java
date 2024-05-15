package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Usuario;

public interface UsuarioInterface {
    public int registrarUsuario(Usuario usuario);
    public List<Usuario> consultarUsuarios();
    public Usuario consultarUsuarioPorId(int idUsuario);                
}
