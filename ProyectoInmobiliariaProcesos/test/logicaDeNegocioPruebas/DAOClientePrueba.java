package logicaDeNegocioPruebas;

import java.math.BigDecimal;
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
        cliente.setMinimoMetrosCuadrados(new BigDecimal(100));
        cliente.setRangoDePrecioMinimo(new BigDecimal(400000));
        cliente.setRangoDePrecioMaximo(new BigDecimal(1000000));
        cliente.setTipoPropiedad(tipoPropiedad);
        cliente.setUbicacion(ubicacion);
        cliente.setUsuario(usuario);
        cliente.setCiudad("Xalapa");
        DAOCliente daoCliente=new DAOCliente();
        int resultadoEsperado=1;
        //int resultadoObtenido=daoCliente.registrarCliente(cliente);
        //assertEquals(resultadoEsperado,resultadoObtenido);                                        
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
    
    @Test
    public void pruebaModificarRangoDePrecioMinimoPorIdExitosa(){
       int idCliente=2;
       BigDecimal rangoDePrecioMinimo=new BigDecimal(12345678);
       DAOCliente daoCliente=new DAOCliente();
       int resultadoEsperado=1;
       int resultadoObtenido=daoCliente.modificarRangoDePrecioMinimoPorId(idCliente, rangoDePrecioMinimo);
       assertEquals(resultadoEsperado,resultadoObtenido);             
    }
    
    @Test
    public void pruebaModificarRangoDePrecioMaximoPorIdExitosa(){
       int idCliente=2;
       BigDecimal rangoDePreciomaximo=new BigDecimal(123456780);
       DAOCliente daoCliente=new DAOCliente();
       int resultadoEsperado=1;
       int resultadoObtenido=daoCliente.modificarRangoDePrecioMaximoPorId(idCliente, rangoDePreciomaximo);
       assertEquals(resultadoEsperado,resultadoObtenido);             
    }
    
    @Test
    public void pruebaModificarMinimoMetrosCuadradosPorIdExitosa(){
       int idCliente=2;
       BigDecimal minimoMetrosCuadrados=new BigDecimal(150);
       DAOCliente daoCliente=new DAOCliente();
       int resultadoEsperado=1;
       int resultadoObtenido=daoCliente.modificarMinimoMetrosCuadradosPorId(idCliente, minimoMetrosCuadrados);
       assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    @Test
    public void pruebaModificarCiudadPorIdExitosa(){
        int idCliente=2;
        String ciudad="Cordoba";
        DAOCliente daoCliente=new DAOCliente();
        int resultadoEsperado=1;
        int resultadoObtenido=daoCliente.modificarCiudadPorId(idCliente, ciudad);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    @Test
    public void pruebaObtenerIdClientePorIdUsuarioExitosa(){
        int idUsuario=1;
        int resultadoEsperado=2;
        DAOCliente daoCliente=new DAOCliente();
        int resultadoObtenido=daoCliente.obtenerIdClientePorIdUsuario(idUsuario);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
    
    @Test
    public void pruebaConsultarIdUsuarioDeClientesActivosExitosa(){
        List<Integer> resultadoEsperado=new ArrayList<>();
        int idUsuario1=1;
        int idUsuario2=2;
        resultadoEsperado.add(idUsuario1);resultadoEsperado.add(idUsuario2);
        List<Integer> resultadoObtenido=new ArrayList<>();
        DAOCliente daoCliente=new DAOCliente();
        resultadoObtenido=daoCliente.consultarIdUsuarioDeClientesActivos();
        assertEquals(resultadoEsperado,resultadoObtenido);                       
    }
    
    @Test
    public void pruebaConsultarClientePorIdUsuarioExitosa(){
        int idUsuario=1;
        DAOCliente daoCliente=new DAOCliente();
        Cliente resultadoEsperado=new Cliente();
        resultadoEsperado.setIdCliente(2);
        Cliente resultadoObtenido=daoCliente.consultarClientePorIdUsuario(idUsuario);
        System.out.println(resultadoObtenido.getRangoDePrecioMinimo());
        assertEquals(resultadoEsperado,resultadoObtenido); 
    }
}


