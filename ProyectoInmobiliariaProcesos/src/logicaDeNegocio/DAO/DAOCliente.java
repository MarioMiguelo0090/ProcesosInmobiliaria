package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.Interfaces.ClienteInterface;

public class DAOCliente implements ClienteInterface{
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;

    @Override
    public int registrarCliente(Cliente cliente) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into cliente "
                    + "(estadoCliente,rangoDePrecioMinimo,rangoDePrecioMaximo,minimoMetrosCuadrados,idUsuario,"
                    + "idUbicacion,idTipoPropiedad) values (?,?,?,?,?,?,?);");
            declaracion.setString(1, "Activo");
            declaracion.setFloat(2, cliente.getRangoDePrecioMaximo());
            declaracion.setFloat(3, cliente.getRangoDePrecioMaximo());
            declaracion.setFloat(4, cliente.getMinimoMetrosCuadrados());
            declaracion.setInt(5, cliente.getUsuario().getIdUsuario());
            declaracion.setInt(6, cliente.getUbicacion().getIdUbicacion());
            declaracion.setInt(7, cliente.getTipoPropiedad().getIdTipoPropiedad());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public List<Cliente> consultarClientes() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Cliente> clientes=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * from cliente;");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Cliente cliente=new Cliente();
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setEstadoCliente(resultado.getString("estadoCliente"));
                cliente.setRangoDePrecioMinimo(resultado.getFloat("rangoDePrecioMinimo"));
                cliente.setRangoDePrecioMaximo(resultado.getFloat("rangoDePrecioMaximo"));
                cliente.setMinimoMetrosCuadrados(resultado.getFloat("minimoMetrosCuadrados"));
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));                
                cliente.setUsuario(usuario);
                Ubicacion ubicacion=new Ubicacion();
                ubicacion.setIdUbicacion(resultado.getInt("idUbicacion"));
                cliente.setUbicacion(ubicacion);
                TipoPropiedad tipoPropiedad=new TipoPropiedad();
                cliente.setTipoPropiedad(tipoPropiedad);
                clientes.add(cliente);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    @Override
    public Cliente consultarClientePorId(int idCliente) {
        PreparedStatement declaracion;
        ResultSet resultado;
        Cliente cliente=new Cliente();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from cliente where idCliente=?;");
            declaracion.setInt(1, idCliente);
            resultado=declaracion.executeQuery();
            while(resultado.next()){                
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setEstadoCliente(resultado.getString("estadoCliente"));
                cliente.setRangoDePrecioMinimo(resultado.getFloat("rangoDePrecioMinimo"));
                cliente.setRangoDePrecioMaximo(resultado.getFloat("rangoDePrecioMaximo"));
                cliente.setMinimoMetrosCuadrados(resultado.getFloat("minimoMetrosCuadrados"));
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));                
                cliente.setUsuario(usuario);
                Ubicacion ubicacion=new Ubicacion();
                ubicacion.setIdUbicacion(resultado.getInt("idUbicacion"));
                cliente.setUbicacion(ubicacion);
                TipoPropiedad tipoPropiedad=new TipoPropiedad();
                cliente.setTipoPropiedad(tipoPropiedad);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;                
    }
    
}
