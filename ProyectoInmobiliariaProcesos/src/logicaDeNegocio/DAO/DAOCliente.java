package logicaDeNegocio.DAO;

import AccesoADatos.ManejadorBaseDatos;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Cliente;
import logicaDeNegocio.Clases.Login;
import logicaDeNegocio.Clases.TipoPropiedad;
import logicaDeNegocio.Clases.Ubicacion;
import logicaDeNegocio.Clases.Usuario;
import logicaDeNegocio.Interfaces.ClienteInterface;

public class DAOCliente implements ClienteInterface{
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOCliente.class);
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;
    private static final String AGREGAR_LOGIN = """
                                                  INSERT INTO login (usuario, contraseña, idUsuario,tipoUsuario )
                                                  VALUES (?, ?, ?,?);"""; 
    
    @Override
    public int registrarCliente(Cliente cliente,Login login) {
        PreparedStatement declaracion;
        PreparedStatement loginDeclaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into cliente "
                    + "(estadoCliente,rangoDePrecioMinimo,rangoDePrecioMaximo,minimoMetrosCuadrados,idUsuario,"
                    + "idUbicacion,idTipoPropiedad,ciudad) values (?,?,?,?,?,?,?,?);");
            declaracion.setString(1, "Activo");
            declaracion.setBigDecimal(2, cliente.getRangoDePrecioMinimo());
            declaracion.setBigDecimal(3, cliente.getRangoDePrecioMaximo());
            declaracion.setBigDecimal(4, cliente.getMinimoMetrosCuadrados());
            declaracion.setInt(5, cliente.getUsuario().getIdUsuario());
            declaracion.setInt(6, cliente.getUbicacion().getIdUbicacion());
            declaracion.setInt(7, cliente.getTipoPropiedad().getIdTipoPropiedad());
            declaracion.setString(8, cliente.getCiudad());
            declaracion.executeUpdate();
            
            loginDeclaracion=conexion.prepareStatement(AGREGAR_LOGIN);
            loginDeclaracion.setString(1, login.getUsuario());
            loginDeclaracion.setString(2, login.getContrasenia());
            loginDeclaracion.setInt(3, login.getIdUsuario());
            loginDeclaracion.setString(4, "Cliente");
            numeroFilasAfectadas=loginDeclaracion.executeUpdate();            
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
            numeroFilasAfectadas=-1;
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
                cliente.setRangoDePrecioMinimo(resultado.getBigDecimal("rangoDePrecioMinimo"));
                cliente.setRangoDePrecioMaximo(resultado.getBigDecimal("rangoDePrecioMaximo"));
                cliente.setMinimoMetrosCuadrados(resultado.getBigDecimal("minimoMetrosCuadrados"));
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));                
                cliente.setUsuario(usuario);
                Ubicacion ubicacion=new Ubicacion();
                ubicacion.setIdUbicacion(resultado.getInt("idUbicacion"));
                cliente.setUbicacion(ubicacion);
                TipoPropiedad tipoPropiedad=new TipoPropiedad();
                cliente.setTipoPropiedad(tipoPropiedad);
                cliente.setCiudad(resultado.getString("ciudad"));
                clientes.add(cliente);                
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
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
                cliente.setRangoDePrecioMinimo(resultado.getBigDecimal("rangoDePrecioMinimo"));
                cliente.setRangoDePrecioMaximo(resultado.getBigDecimal("rangoDePrecioMaximo"));
                cliente.setMinimoMetrosCuadrados(resultado.getBigDecimal("minimoMetrosCuadrados"));
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));                
                cliente.setUsuario(usuario);
                Ubicacion ubicacion=new Ubicacion();
                ubicacion.setIdUbicacion(resultado.getInt("idUbicacion"));
                cliente.setUbicacion(ubicacion);
                TipoPropiedad tipoPropiedad=new TipoPropiedad();
                cliente.setTipoPropiedad(tipoPropiedad);     
                cliente.setCiudad(resultado.getString("ciudad"));
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
        }
        return cliente;                
    }

    @Override
    public int modificarRangoDePrecioMinimoPorId(int idCliente,BigDecimal rangoDePrecioMinimo) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set rangoDePrecioMinimo=? where idCliente=?");
            declaracion.setBigDecimal(1, rangoDePrecioMinimo);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int modificarRangoDePrecioMaximoPorId(int idCliente, BigDecimal rangoDePrecioMaximo) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set rangoDePrecioMaximo=? where idCliente=?");
            declaracion.setBigDecimal(1, rangoDePrecioMaximo);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int modificarMinimoMetrosCuadradosPorId(int idCliente, BigDecimal minimoMetrosCuadrados) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set minimoMetrosCuadrados=? where idCliente=?");
            declaracion.setBigDecimal(1, minimoMetrosCuadrados);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int modificarCiudadPorId(int idCliente, String ciudad) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set ciudad=? where idCliente=?");
            declaracion.setString(1, ciudad);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int modificarTipoDePropiedadPorId(int idCliente,int idTipoPropiedad) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set idTipoPropiedad=? where idCliente=?");
            declaracion.setInt(1, idTipoPropiedad);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;                 
    }

    @Override
    public int modificarUbicacionPorId(int idCliente,int idUbicacion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set idUbicacion=? where idCliente=?");
            declaracion.setInt(1, idUbicacion);
            declaracion.setInt(2, idCliente);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }
    
    @Override
    public int obtenerIdClientePorIdUsuario(int idUsuario){
        int idCliente=0;
        PreparedStatement declaracion;
        ResultSet resultado;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select idCliente from Cliente where idUsuario=?;");
            declaracion.setInt(1, idUsuario);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                idCliente=resultado.getInt("idCliente");
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
            idCliente=-1;
        }
        return idCliente; 
    }
    
    @Override
    public List<Integer> consultarIdUsuarioDeClientesActivos(){
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Integer> idUsuarios=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT idUsuario from cliente where estadoCliente='Activo';");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                int idUsuario=resultado.getInt("idUsuario");
                idUsuarios.add(idUsuario);
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
        }
        return idUsuarios;        
    }
    
    @Override
    public Cliente consultarClientePorIdUsuario(int idUsuario){
        PreparedStatement declaracion;
        ResultSet resultado;
        Cliente cliente=new Cliente();
        DAOTipoPropiedad daoTipoPropiedad=new DAOTipoPropiedad();
        DAOUbicacion daoUbicacion=new DAOUbicacion();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select * from cliente where idUsuario=?;");
            declaracion.setInt(1, idUsuario);
            resultado=declaracion.executeQuery();
            while(resultado.next()){                
                cliente.setIdCliente(resultado.getInt("idCliente"));
                cliente.setEstadoCliente(resultado.getString("estadoCliente"));                               
                cliente.setRangoDePrecioMinimo(resultado.getBigDecimal("rangoDePrecioMinimo"));
                cliente.setRangoDePrecioMaximo(resultado.getBigDecimal("rangoDePrecioMaximo"));
                cliente.setMinimoMetrosCuadrados(resultado.getBigDecimal("minimoMetrosCuadrados"));               
                Usuario usuario=new Usuario();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));                
                cliente.setUsuario(usuario);                
                int idUbicacion=resultado.getInt("idUbicacion");
                Ubicacion ubicacion=daoUbicacion.obtenerUbicacionPorId(idUbicacion);
                cliente.setUbicacion(ubicacion);                
                int idTipoPropiedad=(resultado.getInt("idTipoPropiedad"));
                TipoPropiedad tipoPropiedad=daoTipoPropiedad.consultarTiposPropiedadPorID(idTipoPropiedad);
                cliente.setTipoPropiedad(tipoPropiedad);     
                cliente.setCiudad(resultado.getString("ciudad"));
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
        }
        return cliente;                 
    }
    
    @Override
    public int archivarClientePorIdUsario(int idUsuario){
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE cliente set estadoCliente='Archivado' where idUsuario=?");
            declaracion.setInt(1, idUsuario);            
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }
    
}
