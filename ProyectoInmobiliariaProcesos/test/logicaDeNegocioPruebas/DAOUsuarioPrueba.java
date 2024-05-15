package logicaDeNegocioPruebas;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOUsuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DAOUsuarioPrueba {
    
    @Test
    public void pruebaRegistrarUsuarioExitosa(){
        Usuario usuario=new Usuario();
        usuario.setNombre("Mario");
        usuario.setApellidoPaterno("Limon");
        usuario.setApellidoMaterno("Cabrera");
        usuario.setCorreo("mario@gmail.com");
        usuario.setRFC("LICM123456YYY");
        usuario.setTelefono("2281870034");
        DAOUsuario daoUsuario=new DAOUsuario();
        int resultadoEsperado=1;
        int resultadoObtenido=daoUsuario.registrarUsuario(usuario);
        assertEquals(resultadoEsperado,resultadoObtenido);    
    }
    
    @Test   
    public void pruebaRegistrarUsuarioFracaso(){
        Usuario usuario=new Usuario();
        usuario.setNombre("Oscar");
        usuario.setApellidoPaterno("Apodaca");
        usuario.setApellidoMaterno("Garcia");
        usuario.setCorreo("mario@gmail.com");
        usuario.setRFC("LICM123456YYY");
        usuario.setTelefono("2281870034");
        DAOUsuario daoUsuario=new DAOUsuario();
        int resultadoEsperado=0;
        int resultadoObtenido=daoUsuario.registrarUsuario(usuario);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarUsuariosExitosa(){
        Usuario usuario=new Usuario();       
        usuario.setCorreo("mario@gmail.com");
        usuario.setRFC("LICM123456YYY");
        List<Usuario> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(usuario);
        DAOUsuario daoUsuario=new DAOUsuario();
        List<Usuario> resultadoObtenido=new ArrayList<>();
        resultadoObtenido=daoUsuario.consultarUsuarios();
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
    
    @Test
    public void pruebaConsultarUsuarioPorIdExitosa(){
        Usuario usuarioEsperado=new Usuario();       
        usuarioEsperado.setCorreo("mario@gmail.com");
        usuarioEsperado.setRFC("LICM123456YYY");
        int idUsuario=1;
        Usuario usuarioObtenido=new Usuario();
        DAOUsuario daoUsuario=new DAOUsuario();
        usuarioObtenido=daoUsuario.consultarUsuarioPorId(idUsuario);
        assertEquals(usuarioEsperado,usuarioObtenido);                        
    }
    
}
