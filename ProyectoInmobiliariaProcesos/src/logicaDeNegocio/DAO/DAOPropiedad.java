package logicaDeNegocio.DAO;

import java.util.List;
import logicaDeNegocio.Clases.Propiedad;
import logicaDeNegocio.Interfaces.PropiedadInterface;
import AccesoADatos.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Clases.Direccion;
import logicaDeNegocio.Clases.Propietario;
import logicaDeNegocio.Clases.TipoPropiedad;

public class DAOPropiedad implements PropiedadInterface{
    
    public static final ManejadorBaseDatos BASE_DE_DATOS=new ManejadorBaseDatos();
    private Connection conexion;
    
    @Override
    public int registrarPropiedad(Propiedad propiedad) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Insert into propiedad "
                    + "(nombre,numeroDeHabitaciones,numeroDeBanios,numeroDePisos,antiguedad,metrosDeTerreno"
                    + ",precio,estadoPropiedad,Propietario_idPropietario,idDireccion,idTipoPropiedad)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?);");
            declaracion.setString(1, propiedad.getNombre());
            declaracion.setInt(2, propiedad.getNumeroDeHabitaciones());
            declaracion.setInt(3, propiedad.getNumeroDeBanios());
            declaracion.setInt(4, propiedad.getNumeroDePisos());
            declaracion.setInt(5, propiedad.getAntiguedad());
            declaracion.setFloat(6, propiedad.getMetrosDeTerreno());
            declaracion.setFloat(7, propiedad.getPrecio());
            declaracion.setString(8,propiedad.getEstadoPropiedad());
            declaracion.setInt(9, propiedad.getPropietario().getIdPropietario());
            declaracion.setInt(10,propiedad.getDireccion().getIdDireccion());
            declaracion.setInt(11, propiedad.getTipoDePropiedad().getIdTipoPropiedad());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }
    
    @Override
    public int modificarNombrePropiedad(Propiedad propiedad, String nombre) {
       PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET nombre = ? where idPropiedad = ?");
            sentencia.setString(1, nombre);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarNumeroHabitacionesPropiedad(Propiedad propiedad, int numeroHabitaciones) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET numeroDeHabitaciones = ? where idPropiedad = ?");
            sentencia.setInt(1, numeroHabitaciones);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarNumeroDeBaniosPropiedad(Propiedad propiedad, int numeroDeBanios) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET numeroDeBanios = ? where idPropiedad = ?");
            sentencia.setInt(1, numeroDeBanios);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarNumeroDePisosPropiedad(Propiedad propiedad, int numeroDePisos) {
       PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET numeroDePisos = ? where idPropiedad = ?");
            sentencia.setInt(1, numeroDePisos);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarAntiguedadPropiedad(Propiedad propiedad, int antiguedad) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET antiguedad = ? where idPropiedad = ?");
            sentencia.setInt(1, antiguedad);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarMetrosDeTerreno(Propiedad propiedad, float metrosDeTerreno) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET metrosDeTerreno = ? where idPropiedad = ?");
            sentencia.setFloat(1, metrosDeTerreno);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarPrecioTerreno(Propiedad propiedad, float precio) {
       PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET precio = ? where idPropiedad = ?");
            sentencia.setFloat(1, precio);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int moodificarEstadoPopiedad(Propiedad propiedad, String estadoPropiedad) {
        PreparedStatement sentencia;
        int resultadoModificacion = 0;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("UPDATE propiedad SET estadoPropiedad = ? where idPropiedad = ?");
            sentencia.setString(1, estadoPropiedad);
            sentencia.setInt(2, propiedad.getIdPropiedad());
            resultadoModificacion = sentencia.executeUpdate();
            conexion.close();
        }catch(SQLException excepcion){
              Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
              resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public List<Propiedad> consultarPropiedades() {
        List<Propiedad> propiedadesObtenidas = new ArrayList();
        PreparedStatement sentencia;
        ResultSet resultado;
        DAOPropietario daoPropietario = new DAOPropietario();
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        DAODireccion daoDireccion = new DAODireccion();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("Select * from propiedad");
            resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Propiedad propiedadObtenida = new Propiedad();
                    propiedadObtenida.setIdPropiedad(resultado.getInt("idPropiedad"));
                    propiedadObtenida.setAntiguedad(resultado.getInt("antiguedad"));
                    propiedadObtenida.setEstadoPropiedad(resultado.getString("estadoPropiedad"));
                    propiedadObtenida.setMetrosDeTerreno(resultado.getFloat("metrosDeTerreno"));
                    propiedadObtenida.setNombre(resultado.getString("nombre"));
                    propiedadObtenida.setNumeroDeBanios(resultado.getInt("numeroDeBanios"));
                    propiedadObtenida.setNumeroDeHabitaciones(resultado.getInt("numeroDeHabitaciones"));
                    propiedadObtenida.setNumeroDePisos(resultado.getInt("numeroDePisos"));
                    propiedadObtenida.setPrecio(resultado.getFloat("precio"));
                    int idPropietario = resultado.getInt("Propietario_idPropietario");
                    int idTipoDePropiedad = resultado.getInt("idTipoPropiedad");
                    int idDireccion = resultado.getInt("idDireccion");
                    Direccion direccion = daoDireccion.obtenerDireccionPorId(idDireccion);
                    TipoPropiedad tipoPropiedad = daoTipoPropiedad.consultarTiposPropiedadPorID(idTipoDePropiedad);
                    Propietario propietario = daoPropietario.consultarPropietarioPorID(idPropietario);
                    propiedadObtenida.setDireccion(direccion);
                    propiedadObtenida.setTipoDePropiedad(tipoPropiedad);
                    propiedadObtenida.setPropietario(propietario);
                    propiedadesObtenidas.add(propiedadObtenida);
                }
            }
        }catch(SQLException excepcion){
             Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
             propiedadesObtenidas = null;
        }
        return propiedadesObtenidas;
    }

    @Override
    public List<Propiedad> consultarPropiedadPorTipo(int tipoDePropiedad) {
       List<Propiedad> propiedadesObtenidas = new ArrayList();
        PreparedStatement sentencia;
        ResultSet resultado;
        DAOPropietario daoPropietario = new DAOPropietario();
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        DAODireccion daoDireccion = new DAODireccion();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("Select * from propiedad where idTipoPropiedad = ?");
            sentencia.setInt(1, tipoDePropiedad);
            resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Propiedad propiedadObtenida = new Propiedad();
                    propiedadObtenida.setIdPropiedad(resultado.getInt("idPropiedad"));
                    propiedadObtenida.setAntiguedad(resultado.getInt("antiguedad"));
                    propiedadObtenida.setEstadoPropiedad(resultado.getString("estadoPropiedad"));
                    propiedadObtenida.setMetrosDeTerreno(resultado.getFloat("metrosDeTerreno"));
                    propiedadObtenida.setNombre(resultado.getString("nombre"));
                    propiedadObtenida.setNumeroDeBanios(resultado.getInt("numeroDeBanios"));
                    propiedadObtenida.setNumeroDeHabitaciones(resultado.getInt("numeroDeHabitaciones"));
                    propiedadObtenida.setNumeroDePisos(resultado.getInt("numeroDePisos"));
                    propiedadObtenida.setPrecio(resultado.getFloat("precio"));
                    int idPropietario = resultado.getInt("Propietario_idPropietario");
                    int idTipoDePropiedad = resultado.getInt("idTipoPropiedad");
                    int idDireccion = resultado.getInt("idDireccion");
                    Direccion direccion = daoDireccion.obtenerDireccionPorId(idDireccion);
                    TipoPropiedad tipoPropiedad = daoTipoPropiedad.consultarTiposPropiedadPorID(idTipoDePropiedad);
                    Propietario propietario = daoPropietario.consultarPropietarioPorID(idPropietario);
                    propiedadObtenida.setDireccion(direccion);
                    propiedadObtenida.setTipoDePropiedad(tipoPropiedad);
                    propiedadObtenida.setPropietario(propietario);
                    propiedadesObtenidas.add(propiedadObtenida);
                }
            }
        }catch(SQLException excepcion){
             Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
             propiedadesObtenidas = null;
        }
        return propiedadesObtenidas;
    }

    @Override
    public List<Propiedad> consultarPropiedadPorUbicacion(String ubicacion) {
        List<Propiedad> propiedadesObtenidas = new ArrayList();
        PreparedStatement sentencia;
        ResultSet resultado;
        DAOPropietario daoPropietario = new DAOPropietario();
        DAOTipoPropiedad daoTipoPropiedad = new DAOTipoPropiedad();
        DAODireccion daoDireccion = new DAODireccion();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            sentencia = conexion.prepareStatement("SELECT propiedad.* FROM propiedad "
             + "JOIN direccion ON propiedad.idDirecion = direccion.idDirecion "
             + "JOIN ubicacion ON direccion.idUbicacion = ubicacion.idUbicacion "
             + "WHERE ubicacion.Estado = ?;");
            sentencia.setString(1, ubicacion);
            resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Propiedad propiedadObtenida = new Propiedad();
                    propiedadObtenida.setIdPropiedad(resultado.getInt("idPropiedad"));
                    propiedadObtenida.setAntiguedad(resultado.getInt("antiguedad"));
                    propiedadObtenida.setEstadoPropiedad(resultado.getString("estadoPropiedad"));
                    propiedadObtenida.setMetrosDeTerreno(resultado.getFloat("metrosDeTerreno"));
                    propiedadObtenida.setNombre(resultado.getString("nombre"));
                    propiedadObtenida.setNumeroDeBanios(resultado.getInt("numeroDeBanios"));
                    propiedadObtenida.setNumeroDeHabitaciones(resultado.getInt("numeroDeHabitaciones"));
                    propiedadObtenida.setNumeroDePisos(resultado.getInt("numeroDePisos"));
                    propiedadObtenida.setPrecio(resultado.getFloat("precio"));
                    int idPropietario = resultado.getInt("Propietario_idPropietario");
                    int idTipoDePropiedad = resultado.getInt("idTipoPropiedad");
                    int idDireccion = resultado.getInt("idDireccion");
                    Direccion direccion = daoDireccion.obtenerDireccionPorId(idDireccion);
                    TipoPropiedad tipoPropiedad = daoTipoPropiedad.consultarTiposPropiedadPorID(idTipoDePropiedad);
                    Propietario propietario = daoPropietario.consultarPropietarioPorID(idPropietario);
                    propiedadObtenida.setDireccion(direccion);
                    propiedadObtenida.setTipoDePropiedad(tipoPropiedad);
                    propiedadObtenida.setPropietario(propietario);
                    propiedadesObtenidas.add(propiedadObtenida);
                }
            }
        }catch(SQLException excepcion){
             Logger.getLogger(DAOPropiedad.class.getName()).log(Level.SEVERE, null, excepcion);
             propiedadesObtenidas = null;
        }
        return propiedadesObtenidas;
    }
    
}
