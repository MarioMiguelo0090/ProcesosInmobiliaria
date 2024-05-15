package logicaDeNegocioPruebas;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.DAO.DAOCliente;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DAOClientePrueba {
    
    @Test
    public void pruebaRegistrarClienteExitosa(){
        Ubicacion ubicacion=new Ubicacion();
        ubicacion.setIdUbicacion(1);
        TipoPropiedad tipoPropiedad=new TipoPropiedad();
        tipoPropiedad.setIdTipoPropiedad(1);
        Usuario usuario=new Usuario();
        usuario.setIdUsuario(1);
        Cliente cliente=new Cliente();
        cliente.setMinimoMetrosCuadrados(100);
        cliente.setRangoDePrecioMinimo(400000);
        cliente.setRangoDePrecioMaximo(1000000);
        cliente.setTipoPropiedad(tipoPropiedad);
        cliente.setUbicacion(ubicacion);
        cliente.setUsuario(usuario);
        DAOCliente daoCliente=new DAOCliente();
        int resultadoEsperado=1;
        int resultadoObtenido=daoCliente.registrarCliente(cliente);
        assertEquals(resultadoEsperado,resultadoObtenido);                                        
    }
    
    @Test
    public void pruebaConsultarClientesExitosa(){        
        Cliente cliente=new Cliente();
        cliente.setIdCliente(1);              
        List<Cliente> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(cliente);
        List<Cliente> resultadoObtenido=new ArrayList<>();
        DAOCliente daoCliente=new DAOCliente();
        resultadoObtenido=daoCliente.consultarClientes();
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    @Test
    public void pruebaConsultarClientePorIdExitosa(){
        Cliente clienteEsperado=new Cliente();
        clienteEsperado.setIdCliente(1);
        int idCliente=1;
        Cliente clienteObtenido=new Cliente();
        DAOCliente daoCliente=new DAOCliente();
        clienteObtenido=daoCliente.consultarClientePorId(idCliente);
        assertEquals(clienteEsperado,clienteObtenido);        
    }
    
}
