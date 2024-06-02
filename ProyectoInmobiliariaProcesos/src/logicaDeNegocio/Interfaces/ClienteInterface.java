package logicaDeNegocio.Interfaces;

import java.math.BigDecimal;
import java.util.List;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.Login;

public interface ClienteInterface {
    public int registrarCliente(Cliente cliente,Login login) ;    
    public List<Cliente> consultarClientes();    
    public Cliente consultarClientePorId(int idCliente);    
    public int modificarRangoDePrecioMinimoPorId(int idCliente,BigDecimal rangoDePrecioMinimo);
    public int modificarRangoDePrecioMaximoPorId(int idCliente,BigDecimal rangoDePrecioMaximo); 
    public int modificarMinimoMetrosCuadradosPorId(int idCliente,BigDecimal minimoMetrosCuadrados);
    public int modificarCiudadPorId(int idCliente,String ciudad);
    public int modificarTipoDePropiedadPorId(int idCliente,int idTipoPropiedad);
    public int modificarUbicacionPorId(int idCliente,int idUbicacion);   
    public int obtenerIdClientePorIdUsuario(int idUsuario);
    public List<Integer> consultarIdUsuarioDeClientesActivos();
    public Cliente consultarClientePorIdUsuario(int idUsuario);
    public int archivarClientePorIdUsario(int idUsuario);
}
