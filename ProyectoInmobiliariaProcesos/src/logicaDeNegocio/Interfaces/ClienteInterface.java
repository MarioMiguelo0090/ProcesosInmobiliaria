package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Cliente;

public interface ClienteInterface {
    public int registrarCliente(Cliente cliente);
    
    public List<Cliente> consultarClientes();
    
    public Cliente consultarClientePorId(int idCliente);
    
}
